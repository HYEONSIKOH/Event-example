package com.example.EventDomain.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 배송 정보 도메인
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShippingInfo {
    private Receiver receiver;
    private Address address;

    public boolean isBlank() {
        return receiver.isBlank() || address.isBlank();
    }
}
