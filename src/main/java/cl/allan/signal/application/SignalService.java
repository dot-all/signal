package cl.allan.signal.application;

import cl.allan.signal.infrastructure.persistence.SignalEntity;
import cl.allan.signal.infrastructure.persistence.SignalRepository;
import cl.allan.signal.interfaces.rest.EmitSignalRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

/**
 * Service class for handling signal business logic.
 * Responsible for registering incoming signals, serializing payload data,
 * and persisting them to the database.
 */
@Service
@RequiredArgsConstructor
public class SignalService {

    private final SignalRepository signalRepository;
    private final ObjectMapper objectMapper;

    @Transactional
    public SignalEntity registerSignal(EmitSignalRequest request) {
        try {
            String jsonData = objectMapper.writeValueAsString(request.data());
            
            SignalEntity signal = SignalEntity.builder()
                    .id(UUID.randomUUID())
                    .type(request.type())
                    .source(request.source())
                    .data(jsonData)
                    .occurredAt(Instant.now())
                    .processed(false)
                    .build();

            return signalRepository.save(signal);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error serializing signal data to JSON", e);
        }
    }
}
