package cl.allan.signal.infrastructure.persistence;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;
import java.util.UUID;

/**
 * Represents the persistent state of a signal within the system.
 * This entity acts as the contract for the 'signals' table, often used in the context
 * of an Outbox pattern for reliable event distribution.
 */
@Entity
@Table(name = "signals")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignalEntity {

    @Id
    private UUID id;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String source;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String data;

    @Column(nullable = false)
    private Instant occurredAt;

    @Builder.Default
    @Column(nullable = false)
    private boolean processed = false;
}
