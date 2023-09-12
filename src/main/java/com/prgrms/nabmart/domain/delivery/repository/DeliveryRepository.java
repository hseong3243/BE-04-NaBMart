package com.prgrms.nabmart.domain.delivery.repository;

import com.prgrms.nabmart.domain.delivery.Delivery;
import com.prgrms.nabmart.domain.delivery.DeliveryStatus;
import com.prgrms.nabmart.domain.delivery.Rider;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

    @Query("select d from Delivery d join fetch d.order where d.order.orderId = :orderId")
    Optional<Delivery> findByOrderIdWithOrder(@Param("orderId") Long orderId);

    @Query(value = "select d from Delivery d"
        + " join fetch d.order"
        + " where d.deliveryStatus"
        + " = com.prgrms.nabmart.domain.delivery.DeliveryStatus.ACCEPTING_ORDER",
    countQuery = "select d.deliveryId from Delivery d"
        + " where d.deliveryStatus"
        + " = com.prgrms.nabmart.domain.delivery.DeliveryStatus.ACCEPTING_ORDER")
    Page<Delivery> findWaitingDeliveries(Pageable pageable);

    @Query(value = "select d from Delivery d"
        + " join fetch d.order"
        + " where d.rider = :rider and d.deliveryStatus in :statuses",
    countQuery = "select d.deliveryId from Delivery d"
        + " where d.rider = :rider and d.deliveryStatus in :statuses")
    Page<Delivery> findRiderDeliveries(
        @Param("rider") Rider rider,
        @Param("statuses") List<DeliveryStatus> deliveryStatuses,
        Pageable pageable);
}
