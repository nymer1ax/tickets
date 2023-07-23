package co.com.doublev.usecase.updateticket;


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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
class UpdateTicketUseCaseTest {
    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private UpdateTicketUseCase updateTicketUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testUpdateExistingTicket() {

        Long ticketId = 1L;
        Ticket existingTicket = Ticket.builder()
                .id(ticketId)
                .userId(1L)
                .creationDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .status(true)
                .build();

        Ticket updatedTicket = Ticket.builder()
                .id(ticketId)
                .userId(2L)
                .creationDate(existingTicket.getCreationDate())
                .updateDate(LocalDateTime.now())
                .status(false)
                .build();

        when(ticketRepository.getTicketById(ticketId)).thenReturn(Optional.of(existingTicket));

        when(ticketRepository.updateTicket(ticketId, updatedTicket.getUserId(), existingTicket.getCreationDate(), updatedTicket.getStatus())).thenReturn(updatedTicket);


        Ticket resultTicket = updateTicketUseCase.updateTicket(updatedTicket);

        assertEquals(updatedTicket, resultTicket);
        verify(ticketRepository, times(1)).getTicketById(ticketId);
        verify(ticketRepository, times(1)).updateTicket(ticketId, updatedTicket.getUserId(), existingTicket.getCreationDate(), updatedTicket.getStatus());
    }

    @Test
    void testUpdateNonExistingTicket() {
        Long ticketId = 2L;
        Ticket updatedTicket = Ticket.builder()
                .id(ticketId)
                .userId(2L)
                .creationDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .status(false)
                .build();

        when(ticketRepository.getTicketById(ticketId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> updateTicketUseCase.updateTicket(updatedTicket));
        verify(ticketRepository, times(1)).getTicketById(ticketId);
        verify(ticketRepository, times(0)).updateTicket(anyLong(), anyLong(), any(LocalDateTime.class), anyBoolean());
    }

}