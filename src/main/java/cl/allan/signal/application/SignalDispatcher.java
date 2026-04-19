package cl.allan.signal.application;

import cl.allan.signal.application.port.out.SignalPublisher;
import cl.allan.signal.infrastructure.persistence.SignalEntity;
import cl.allan.signal.infrastructure.persistence.SignalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Background worker responsible for polling the transactional outbox and dispatching pending signals.
 * Implements the Outbox Pattern by ensuring signals are reliably delivered to external systems 
 * after the local transaction has completed.
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class SignalDispatcher {

    private final SignalRepository signalRepository;
    private final List<SignalPublisher> signalPublishers;

    /**
     * Polls the signals table for unprocessed entries and attempts to publish them using available strategies.
     * Runs periodically according to the configured fixed delay.
     */
    @Scheduled(fixedDelay = 5000)
    @Transactional
    public void dispatchPendingSignals() {
        List<SignalEntity> pendingSignals = signalRepository.findByProcessedFalse();

        if (pendingSignals.isEmpty()) {
            return;
        }

        log.info("Found {} pending signals in the outbox. Processing...", pendingSignals.size());

        for (SignalEntity signal : pendingSignals) {
            try {
                for (SignalPublisher publisher : signalPublishers) {
                    if (publisher.publish(signal)) {
                        signal.setProcessed(true);
                        signalRepository.save(signal);
                        break;
                    }
                }
            } catch (Exception e) {
                log.error("Error processing signal [{}]: {}", signal.getId(), e.getMessage(), e);
            }
        }
    }
}
