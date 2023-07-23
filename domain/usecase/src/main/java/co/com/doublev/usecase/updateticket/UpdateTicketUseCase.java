package co.com.doublev.usecase.updateticket;

import co.com.doublev.model.ticket.Ticket;
import co.com.doublev.model.ticket.gateways.TicketRepository;
import co.com.doublev.usecase.exceptions.custom.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UpdateTicketUseCase {
    private final TicketRepository ticketRepository;
    public Ticket updateTicket(Long ticketId, Long userId, Boolean status) {

        Optional<Ticket> ticketExists = ticketRepository.getTicketById(ticketId);

        if (ticketExists.isEmpty()) {
            throw new ResourceNotFoundException("Ticket could not be found.");
        }

        return ticketRepository.updateTicket(ticketId, userId, status);
    }
}
