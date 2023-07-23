package co.com.doublev.usecase.updateticket;

import co.com.doublev.model.ticket.Ticket;
import co.com.doublev.model.ticket.gateways.TicketRepository;
import co.com.doublev.usecase.exceptions.custom.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UpdateTicketUseCase {
    private final TicketRepository ticketRepository;
    public Ticket updateTicket(Ticket ticket) {

        Optional<Ticket> ticketExists = ticketRepository.getTicketById(ticket.getId());

        if (ticketExists.isEmpty()) {
            throw new ResourceNotFoundException("Ticket could not be found.");
        }

        return ticketRepository.updateTicket(ticket.getId(), ticket.getUserId(), ticket.getStatus());
    }
}
