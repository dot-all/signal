package cl.allan.signal.interfaces.rest;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

/**
 * Standardized response contract for signal emission requests.
 * Contains the unique identifier, source metadata, and the processing status or data payload.
 */
public record SignalResponse(
        UUID id,
        String source,
        String type,
        Instant time,
        Map<String, Object> data) {
}
