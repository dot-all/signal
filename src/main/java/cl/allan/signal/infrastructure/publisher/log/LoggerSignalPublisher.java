package cl.allan.signal.infrastructure.publisher.log;

import cl.allan.signal.application.port.out.SignalPublisher;
import cl.allan.signal.infrastructure.persistence.SignalEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Concrete implementation of {@link SignalPublisher} that logs signal dispatching.
 * This strategy is used for debugging and providing trace visibility of processed signals.
 */
@Component
@Slf4j
public class LoggerSignalPublisher implements SignalPublisher {

    @Override
    public boolean publish(SignalEntity signal) {
        log.info("🚀 Dispatching signal [{}] with ID [{}] to external system...", 
                signal.getType(), signal.getId());
        return true;
    }
}
