package com.example.EventDomain.order;

import com.example.EventDomain.common.ErrorMessages;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

/**
 * 주문 도메인
 */
@Getter
public class Order {

    private String id;
    private List<OrderLine> orderLines;
    private OrderState state;
    private Money totalAmounts;
    private ShippingInfo shippingInfo;

    public Order(List<OrderLine> orderLines, ShippingInfo shippingInfo) {
        id = UUID.randomUUID().toString();
        setOrderLines(orderLines);
        setShippingInfo(shippingInfo);
        state = OrderState.PAYMENT_WAITING;
    }

    /**
     * 배송 정보 설정
     */
    private void setOrderLines(List<OrderLine> orderLines) {
        if (isEmptyLOrderLines(orderLines)) {
            throw new IllegalStateException(ErrorMessages.NO_ORDER_LINE.getMsg());
        }

        this.orderLines = orderLines;
        calculateTotalAmounts();
    }

    private boolean isEmptyLOrderLines(List<OrderLine> orderLines) {
        return orderLines == null || orderLines.isEmpty();
    }

    /**
     * 수신자 설정
     */
    private void setShippingInfo(ShippingInfo shippingInfo) {
        if (isBlankShippingInfo(shippingInfo)) {
            throw new IllegalStateException(ErrorMessages.NO_SHIPPING_INFO.getMsg());
        }

        this.shippingInfo = shippingInfo;
    }

    private boolean isBlankShippingInfo(ShippingInfo shippingInfo) {
        return shippingInfo == null || shippingInfo.isBlank();
    }

    /**
     * 주문 상태 변경 메소드들
     */
    public void payment() {
        if (state != OrderState.PAYMENT_WAITING) {
            throw new IllegalStateException(ErrorMessages.ALREADY_PAID.getMsg());
        }

        this.state = OrderState.PREPARING;
    }

    public void shipped() {
        if (state != OrderState.PREPARING) {
            throw new IllegalStateException(ErrorMessages.NOT_READY.getMsg());
        }

        this.state = OrderState.SHIPPED;
    }

    public void startDelivery() {
        if (state != OrderState.SHIPPED) {
            throw new IllegalStateException(ErrorMessages.NOT_SHIPPED.getMsg());
        }

        this.state = OrderState.DELIVERING;
    }

    public void completeDelivery() {
        if (state != OrderState.DELIVERING) {
            throw new IllegalStateException(ErrorMessages.NOT_DELIVERING.getMsg());
        }

        this.state = OrderState.DELIVERING_COMPLETED;
    }

    public void changeShippingInfo(ShippingInfo newShippingInfo) {
        verifyBeforeShipping();
        this.shippingInfo = newShippingInfo;
    }

    public void cancel() {
        verifyBeforeShipping();
        this.state = OrderState.CANCELED;
    }

    /**
     * 배송 전 상태 확인 메소드
     */
    private void verifyBeforeShipping() {
        if (state != OrderState.PAYMENT_WAITING && state != OrderState.PREPARING) {
            throw new IllegalStateException(ErrorMessages.ALREADY_SHIPPED.getMsg());
        }
    }

    /**
     * 주문 총액 계산 메소드
     */
    private void calculateTotalAmounts() {
        this.totalAmounts = new Money(orderLines.stream()
                .mapToInt(x -> x.getAmounts().getValue()).sum());
    }

}