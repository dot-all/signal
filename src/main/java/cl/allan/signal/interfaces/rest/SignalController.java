package cl.allan.signal.interfaces.rest;

import cl.allan.signal.application.SignalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * REST controller for managing signal-related operations.
 * Provides endpoints for emitting and registering signals within the platform.
 */
@RestController
@RequestMapping("/api/v1/signals")
@RequiredArgsConstructor
public class SignalController {

    private final SignalService signalService;

    @PostMapping("/emit")
    public ResponseEntity<SignalResponse> emit(@RequestBody EmitSignalRequest request) {
        var savedSignal = signalService.registerSignal(request);

        var response = new SignalResponse(
                savedSignal.getId(),
                savedSignal.getSource(),
                savedSignal.getType(),
                savedSignal.getOccurredAt(),
                Map.of("status", "ACCEPTED")
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}