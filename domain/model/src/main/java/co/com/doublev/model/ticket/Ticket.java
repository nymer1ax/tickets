package co.com.doublev.model.ticket;
import co.com.doublev.model.user.User;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Ticket {
    private Long id;
    private Long userId;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;
    private Boolean status;
}
