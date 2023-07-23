package co.com.doublev.usecase.getalltickets;

import co.com.doublev.model.ticket.Ticket;
import co.com.doublev.model.ticket.gateways.TicketRepository;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class GetAllTicketsUseCase {

    private final TicketRepository ticketRepository;

    public List<Ticket> getAllTickets(Optional<Long> ticketId) {
        if (ticketId.isEmpty()) {
            return ticketRepository.getAllTickets();
        }
        return getTicketById(ticketId);

    }

    private List<Ticket> getTicketById(Optional<Long> ticketId) {
        Optional<Ticket> ticket = ticketRepository.getTicketById(ticketId.get());
        if (ticket.isEmpty()) {
            return Collections.emptyList();
        }
        return List.of(ticket.get());

    }
}
