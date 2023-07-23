package co.com.doublev.jpa.ticketadapter;

import co.com.doublev.jpa.helper.AdapterOperations;
import co.com.doublev.model.ticket.Ticket;
import co.com.doublev.model.ticket.gateways.TicketRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class TicketDataAdapterRepository extends AdapterOperations<Ticket, TicketData, Long, TicketDataRepository> implements TicketRepository {

    public TicketDataAdapterRepository(TicketDataRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Ticket.class));
    }

    @Override
    public Ticket createTicket(Ticket ticket) {
        TicketData ticketData = mapper.map(ticket, TicketData.class);
        TicketData ticketSave = repository.save(ticketData);
        return mapper.map(ticketSave, Ticket.class);
    }

    @Override
    public Optional<Ticket> getTicketById(Long ticketId) {

        Optional<TicketData> ticketData = repository.findById(ticketId);

        if (ticketData.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(mapper.map(ticketData.get(), Ticket.class));
    }

    @Override
    public Ticket updateTicket(Long ticketId, Long userId, Boolean status) {
        TicketData ticketData = TicketData
                .builder()
                .id(ticketId)
                .userId(userId)
                .status(status)
                .updateDate(LocalDateTime.now())
                .build();
        repository.save(ticketData);
        return mapper.map(ticketData, Ticket.class);
    }

    @Override
    public void deleteTicket(Long ticketId) {
        repository.deleteById(ticketId);
    }

    @Override
    public List<Ticket> getAllTickets() {
        return  repository.findAll().stream().map(o -> mapper.map(o, Ticket.class)).collect(Collectors.toList());
    }
}
