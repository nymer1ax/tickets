package co.com.doublev.usecase.deleteticket;

import co.com.doublev.model.ticket.Ticket;
import co.com.doublev.model.ticket.gateways.TicketRepository;
import co.com.doublev.usecase.exceptions.custom.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeleteTicketUseCaseTest {
    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private DeleteTicketUseCase deleteTicketUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDeleteExistingTicket() {
        Long ticketId = 1L;
        Ticket existingTicket = Ticket.builder()
                .id(ticketId)
                .userId(1L)
                .creationDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .status(true)
                .build();

        when(ticketRepository.getTicketById(ticketId)).thenReturn(Optional.of(existingTicket));

        deleteTicketUseCase.deleteTicket(ticketId);

        verify(ticketRepository, times(1)).deleteTicket(ticketId);
    }

    @Test
    public void testDeleteNonExistingTicket() {

        Long ticketId = 2L;

        when(ticketRepository.getTicketById(ticketId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> deleteTicketUseCase.deleteTicket(ticketId));
        verify(ticketRepository, times(0)).deleteTicket(ticketId);
    }
}