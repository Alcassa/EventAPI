package rocktseat.passinn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rocktseat.passinn.domain.checkin.Checkin;

public interface CheckInRepository extends JpaRepository<Checkin, Integer> {
}
