package co.com.doublev.jpa.ticketadapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface TicketDataRepository extends JpaRepository<TicketData, Long>, QueryByExampleExecutor<TicketData> {
}
