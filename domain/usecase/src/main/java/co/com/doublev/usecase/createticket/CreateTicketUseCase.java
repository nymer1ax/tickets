package co.com.doublev.usecase.createticket;

import co.com.doublev.model.ticket.Ticket;
import co.com.doublev.model.ticket.gateways.TicketRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateTicketUseCase {

    private final TicketRepository ticketRepository;

    public Ticket createNewTicket(Ticket ticket) {
        return ticketRepository.createTicket(ticket);
    }

}
