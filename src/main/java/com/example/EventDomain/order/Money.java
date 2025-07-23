package com.example.EventDomain.order;

import lombok.Getter;

@Getter
public class Money {
    private int value;

    public Money(int value) { this.value = value; }

    // 금액 추가
    public Money add(Money money) {
        return new Money(this.value + money.getValue());
    }
}
