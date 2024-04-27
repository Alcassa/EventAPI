package rocktseat.passinn.domain.attende;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rocktseat.passinn.domain.event.Event;

import java.time.LocalDateTime;

@Entity
@Table(name = "attendes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Attendee {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;

    @ManyToOne
    @JoinColumn(name = "event_id",nullable = false)
    private Event event;

    @Column(name = "created_at",nullable = false)
    private LocalDateTime createdAt;

}
