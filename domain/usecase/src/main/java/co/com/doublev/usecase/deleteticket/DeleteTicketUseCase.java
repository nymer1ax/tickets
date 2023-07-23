package co.com.doublev.usecase.deleteticket;

import co.com.doublev.model.ticket.Ticket;
import co.com.doublev.model.ticket.gateways.TicketRepository;
import co.com.doublev.usecase.exceptions.custom.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class DeleteTicketUseCase {

    private final TicketRepository ticketRepository;

    public void deleteTicket(Long ticketId) {

        Optional<Ticket> ticketExist = ticketRepository.getTicketById(ticketId);

        if (ticketExist.isEmpty()) {
            throw new ResourceNotFoundException("Ticket could not be delete.");
        }

        ticketRepository.deleteTicket(ticketId);

    }
}
