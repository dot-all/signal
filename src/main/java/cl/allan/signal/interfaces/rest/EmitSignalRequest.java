package cl.allan.signal.interfaces.rest;

import jakarta.validation.constraints.NotBlank;
import java.util.Map;

/**
 * Data Transfer Object for signal emission requests.
 * Encapsulates the signal type, its source, and any associated contextual data payload.
 */
public record EmitSignalRequest(
        @NotBlank String type,
        @NotBlank String source,
        Map<String, Object> data) {
}