package com.prgrms.nabmart.domain.delivery;

import static java.util.Objects.nonNull;

import com.prgrms.nabmart.domain.delivery.exception.InvalidDeliveryException;
import com.prgrms.nabmart.domain.order.Order;
import com.prgrms.nabmart.domain.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Delivery {

    private static final int ADDRESS_LENGTH = 500;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deliveryId;

    @OneToOne
    @JoinColumn(name = "orderId", nullable = false)
    private Order order;

    @Column(nullable = false)
    private DeliveryStatus deliveryStatus;

    @Column
    private LocalDateTime arriveTime;

    @Column(nullable = false)
    private String address;

    @Builder
    public Delivery(final Order order, final LocalDateTime arriveTime, final String address) {
        validateAddress(address);
        this.order = order;
        this.arriveTime = arriveTime;
        this.address = address;
        this.deliveryStatus = DeliveryStatus.ACCEPTING_ORDERS;
    }

    private void validateAddress(String address) {
        if (nonNull(address) && address.length() > ADDRESS_LENGTH) {
            throw new InvalidDeliveryException("주소의 길이는 500자를 넘을 수 없습니다.");
        }
    }

    public boolean isOwnByUser(final User user) {
        return this.order.isOwnByUser(user);
    }
}
