package co.com.doublev.usecase.getalltickets;

import static org.junit.jupiter.api.Assertions.*;

import co.com.doublev.model.ticket.Ticket;
import co.com.doublev.model.ticket.gateways.TicketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
class GetAllTicketsUseCaseTest {
    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private GetAllTicketsUseCase getAllTicketsUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllTickets() {
        Ticket ticket1 = Ticket.builder()
                .id(1L)
                .userId(1L)
                .creationDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .status(true)
                .build();

        Ticket ticket2 = Ticket.builder()
                .id(2L)
                .userId(2L)
                .creationDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .status(true)
                .build();

        List<Ticket> expectedTickets = Arrays.asList(ticket1, ticket2);

        when(ticketRepository.getAllTickets()).thenReturn(expectedTickets);

        List<Ticket> actualTickets = getAllTicketsUseCase.getAllTickets();

        assertEquals(expectedTickets, actualTickets);
        verify(ticketRepository, times(1)).getAllTickets();
    }
}