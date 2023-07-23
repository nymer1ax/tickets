package co.com.doublev.jpa.ticketadapter;

import co.com.doublev.jpa.helper.AdapterOperations;
import co.com.doublev.model.ticket.Ticket;
import co.com.doublev.model.ticket.gateways.TicketRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
public class TicketDataAdapterRepository extends AdapterOperations<Ticket, TicketData, Long, TicketDataRepository> implements TicketRepository {

    public TicketDataAdapterRepository(TicketDataRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Ticket.class));
    }

    @Override
    public Ticket createTicket(Ticket ticket) {
        return null;
    }

    @Override
    public Optional<Ticket> getTicketById(Long ticketId) {
        return Optional.empty();
    }

    @Override
    public Ticket updateTicket(Long ticketId, Long userId, Boolean status) {
        return null;
    }

    @Override
    public void deleteTicket(Long ticketId) {

    }

    @Override
    public List<Ticket> getAllTickets() {
        return null;
    }
}
