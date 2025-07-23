package com.example.EventDomain.order;

import lombok.Getter;

/**
 * 구매 주문서 도메인
 * 상품이 어떻게 팔렸는지?
 */
@Getter
public class OrderLine {
    /**
     * OrderItem 대신, OrderLine or LineItem 이란 용어로 사용 \n
     * OrderLine: 주문과 관련하여 사용
     * ├─ OrderLine 1 → 상품 A, 수량 2개
     * ├─ OrderLine 2 → 상품 B, 수량 1개
     * LineItem: 주문이 아닌 곳에서도 사용 가능(?)
     * ├─ LineItem 1 → 부품 A, 단가 5,000
     * ├─ LineItem 2 → 서비스 B, 단가 30,000
     */

    private Long id;
    private Product product; // 상품 정보
    private Money price;     // 상품 단가
    private int quantity;    // 상품 수량
    private Money amounts;   // 상품 총액 (단가 * 수량)

    public OrderLine(Product product, Money price, int quantity) {
        this.product = product;
        this.price = price;
        this.quantity = quantity;
        this.amounts = calculateAmounts();
    }

    private Money calculateAmounts() {
        return new Money(price.getValue() * quantity);
    }

    public Money getAmounts() {
        return calculateAmounts();
    }

    /**
     * 의문점
     * 1. 왜 amounts 필드를 따로 두는가?
     */
}
