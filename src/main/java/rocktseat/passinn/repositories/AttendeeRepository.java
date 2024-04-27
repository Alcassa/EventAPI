package rocktseat.passinn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rocktseat.passinn.domain.attende.Attendee;

import java.util.List;

public interface AttendeeRepository extends JpaRepository<Attendee, String> {
    List<Attendee> findByEventId(String eventId);
}
