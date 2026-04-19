package cl.allan.signal.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

/**
 * Repository interface for {@link SignalEntity} persistence.
 * Provides abstraction for database operations on signals.
 */
@Repository
public interface SignalRepository extends JpaRepository<SignalEntity, UUID> {

    /**
     * Retrieves all signals that have not yet been successfully processed.
     * Used by the background dispatcher to identify pending outbox entries.
     *
     * @return A list of unprocessed signal entities.
     */
    java.util.List<SignalEntity> findByProcessedFalse();
}
