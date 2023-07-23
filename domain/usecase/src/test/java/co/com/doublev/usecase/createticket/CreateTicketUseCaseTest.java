package co.com.doublev.usecase.createticket;

import co.com.doublev.model.ticket.Ticket;
import co.com.doublev.model.ticket.gateways.TicketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateTicketUseCaseTest {
    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private CreateTicketUseCase createTicketUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateNewTicket() {
        Ticket inputTicket = Ticket.builder()
                .userId(1L)
                .creationDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .status(true)
                .build();

        Ticket expectedTicket = Ticket.builder()
                .id(1L)
                .userId(1L)
                .creationDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .status(true)
                .build();

        when(ticketRepository.createTicket(inputTicket)).thenReturn(expectedTicket);

        Ticket createdTicket = createTicketUseCase.createNewTicket(inputTicket);

        assertEquals(expectedTicket, createdTicket);
        verify(ticketRepository, times(1)).createTicket(inputTicket);
    }

    @Test
    void testCreateNewTicketNullInput() {
        Ticket inputTicket = null;

        Ticket createdTicket = createTicketUseCase.createNewTicket(inputTicket);

        assertEquals(null, createdTicket);
        verify(ticketRepository, times(0)).createTicket(any(Ticket.class));
    }
}