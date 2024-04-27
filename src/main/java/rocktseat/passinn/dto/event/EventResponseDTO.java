package rocktseat.passinn.dto.event;

import lombok.Getter;
import rocktseat.passinn.domain.event.Event;

@Getter
public class EventResponseDTO {
    EventDetailDTO eventDetailDTO;
    public EventResponseDTO(Event event, Integer numberOfAttendees){
        this.eventDetailDTO =new EventDetailDTO(event.getId(),
                event.getTitle(),
                event.getDetail(),
                event.getSlug(),
                event.getMaximumAttendees(),
                numberOfAttendees);

    }
}
