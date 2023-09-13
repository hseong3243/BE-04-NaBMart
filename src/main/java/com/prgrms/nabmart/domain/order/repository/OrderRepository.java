package com.prgrms.nabmart.domain.order.repository;

import com.prgrms.nabmart.domain.order.Order;
import com.prgrms.nabmart.domain.order.OrderStatus;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findByOrderIdAndUser_UserId(Long orderId, Long userId);

    Page<Order> findByUser_UserId(Long userId, Pageable pageable);

    @Query("SELECT o FROM Order o WHERE o.createdAt <= :expiredTime AND o.status IN :statusList")
    List<Order> findByStatusInBeforeExpiredTime(LocalDateTime expiredTime,
        List<OrderStatus> statusList);
}
