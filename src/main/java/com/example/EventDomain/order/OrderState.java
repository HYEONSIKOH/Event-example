package com.example.EventDomain.order;

/**
 * 주문 상태
 * 결제 대기 -> 준비중 -> 배송준비 -> 배송중 -> 배송완료
 *                 -> 주문 취소
 */
public enum OrderState {

    // 결제 대기
    PAYMENT_WAITING {
        // 배송 변경 가능 여부
        public boolean isShippingChangeable() { return true; }
    },

    // 준비중
    PREPARING {
        // 배송 변경 가능 여부
        public boolean isShippingChangeable() { return true; }
    },

    SHIPPED,
    CANCELED,
    DELIVERING,
    DELIVERING_COMPLETED;

    // 배송 변경 가능 여부
    public boolean isShippingChangeable() { return false; }

}
