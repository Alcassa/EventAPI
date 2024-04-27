package rocktseat.passinn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rocktseat.passinn.domain.event.Event;

import java.util.List;
import java.util.UUID;

public interface EventRepository  extends JpaRepository<Event, String> {
}
