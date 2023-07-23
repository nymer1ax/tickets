package co.com.doublev.usecase.getalltickets;

import co.com.doublev.model.ticket.Ticket;
import co.com.doublev.model.ticket.gateways.TicketRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GetAllTicketsUseCase {

    private final TicketRepository ticketRepository;

    public List<Ticket> getAllTickets() {
        return ticketRepository.getAllTickets();
    }
}
