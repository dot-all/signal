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
}
