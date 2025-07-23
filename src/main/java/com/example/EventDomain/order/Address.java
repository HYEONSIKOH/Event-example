package com.example.EventDomain.order;

public class Address {
    private String address1; // 도로명 주소
    private String address2; // 상세 주소
    private String zipCode;  // 우편번호

    public Boolean isBlank() {
        return address1.isBlank() || zipCode.isBlank() || address2.isBlank();
    }
}
