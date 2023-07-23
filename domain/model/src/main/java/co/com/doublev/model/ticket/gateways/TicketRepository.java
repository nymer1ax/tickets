package co.com.doublev.model.ticket.gateways;

import co.com.doublev.model.ticket.Ticket;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TicketRepository {
    Ticket createTicket(Ticket ticket);
    Optional<Ticket> getTicketById(Long ticketId);
    Ticket updateTicket(Long ticketId, Long userId, LocalDateTime creationDate, Boolean status);
    void deleteTicket(Long ticketId);

    List<Ticket> getAllTickets();
}
