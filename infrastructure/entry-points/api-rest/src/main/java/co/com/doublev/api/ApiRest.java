package co.com.doublev.api;
import co.com.doublev.api.dtos.TicketId;
import co.com.doublev.model.ticket.Ticket;
import co.com.doublev.usecase.createticket.CreateTicketUseCase;
import co.com.doublev.usecase.deleteticket.DeleteTicketUseCase;
import co.com.doublev.usecase.getalltickets.GetAllTicketsUseCase;
import co.com.doublev.usecase.updateticket.UpdateTicketUseCase;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ApiRest {
    private final CreateTicketUseCase createTicketUseCase;
    private final DeleteTicketUseCase deleteTicketUseCase;
    private final GetAllTicketsUseCase getAllTicketsUseCase;
    private final UpdateTicketUseCase updateTicketUseCase;

    @PostMapping(path = "/tickets")
    public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket) {
        return ResponseEntity.status(HttpStatus.CREATED).body(createTicketUseCase.createNewTicket(ticket));
    }

    @PutMapping(path = "/tickets")
    public ResponseEntity<Ticket> updateTicket(@RequestBody Ticket ticket) {
        return ResponseEntity.status(HttpStatus.OK).body(updateTicketUseCase.updateTicket(ticket));
    }

    @DeleteMapping(path = "/tickets")
    public ResponseEntity deleteTicket(@RequestBody TicketId ticket) {
        deleteTicketUseCase.deleteTicket(ticket.getTicketId());
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @GetMapping(path = "/tickets")
    public ResponseEntity<Page<Ticket>> getAllTickets(@RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "10") int size,
                                                      @RequestParam(required = false) Optional<Long> ticketId
    ) {
        List<Ticket> tickets = getAllTicketsUseCase.getAllTickets(ticketId);
        Page<Ticket> pageList = new PageImpl<>(tickets, PageRequest.of(page, size), tickets.size());
        return ResponseEntity.status(HttpStatus.CREATED).body(pageList);
    }


}
