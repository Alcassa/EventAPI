package rocktseat.passinn.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import rocktseat.passinn.domain.attende.Attendee;
import rocktseat.passinn.domain.event.Event;
import rocktseat.passinn.dto.event.EventIdDTO;
import rocktseat.passinn.dto.event.EventRequestDTO;
import rocktseat.passinn.dto.event.EventResponseDTO;
import rocktseat.passinn.repositories.AttendeeRepository;
import rocktseat.passinn.repositories.EventRepository;

import java.text.Normalizer;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final AttendeeRepository attendeeRepository;
    public EventResponseDTO getEventDetail(String eventId){
        Event event = this.eventRepository.findById(eventId).orElseThrow(()-> new RuntimeException("Event not found with ID"+eventId));
        List<Attendee> attendeeList=this.attendeeRepository.findByEventId(eventId);
        return new EventResponseDTO(event,attendeeList.size());
    }
    public EventIdDTO createEvent(EventRequestDTO requestDTO){
        Event newEvent=new Event();
        newEvent.setTitle(requestDTO.title());
        newEvent.setDetail(requestDTO.details());
        newEvent.setMaximumAttendees(requestDTO.maximumAttendees());
        newEvent.setSlug(createSlug(requestDTO.title()));
        this.eventRepository.save(newEvent);
        return new EventIdDTO(newEvent.getId());
    }
    public Event editEvent(String id,EventRequestDTO requestDTO){
      Event editedEvent = eventRepository.findById(id).orElseThrow(()-> new RuntimeException("Event not found"));
        editedEvent.setTitle(requestDTO.title());
        editedEvent.setDetail(requestDTO.details());
        editedEvent.setMaximumAttendees(requestDTO.maximumAttendees());
        editedEvent.setSlug(createSlug(requestDTO.title()));
        this.eventRepository.save(editedEvent);
        return editedEvent;
    }
    private String createSlug(String text){
        String normalize= Normalizer.normalize(text,Normalizer.Form.NFD);
        return normalize.replaceAll("[\\p{InCOMBINING_DIACRITICAL_MARKS}]","")
                .replaceAll("[^\\w\\s]","")
                .replaceAll("[\\s+]","-")
                .toLowerCase();
    }
}
