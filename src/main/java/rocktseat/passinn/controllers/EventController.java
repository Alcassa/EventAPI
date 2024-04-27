package rocktseat.passinn.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;
import rocktseat.passinn.domain.event.Event;
import rocktseat.passinn.dto.event.EventIdDTO;
import rocktseat.passinn.dto.event.EventRequestDTO;
import rocktseat.passinn.dto.event.EventResponseDTO;
import rocktseat.passinn.services.EventService;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService service;
    @GetMapping(value = "/{id}")
    public ResponseEntity<EventResponseDTO> getEvent(@PathVariable String id){
       EventResponseDTO responseDTO= this.service.getEventDetail(id);
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping
    public ResponseEntity<EventIdDTO> createEvent(@RequestBody EventRequestDTO body, UriComponentsBuilder uriBuilder){
        EventIdDTO eventIdDTO =this.service.createEvent(body);
        var uri=uriBuilder.path("/events/{id}").buildAndExpand(eventIdDTO.eventId()).toUri();
        return ResponseEntity.created(uri).body(eventIdDTO);
    }

    @PutMapping("edit/{id}")
    public ResponseEntity<Event> editEvent(@PathVariable String id, @RequestBody EventRequestDTO eventRequestDTO, UriComponentsBuilder uriBuilder){
        Event event=this.service.editEvent(id,eventRequestDTO);
        var uri=uriBuilder.path("/events/{id}").buildAndExpand(event.getId()).toUri();
        return ResponseEntity.created(uri).body(event);
    }
}
