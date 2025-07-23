package com.example.EventDomain.order;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class Receiver {
    private String name;
    private String phone;

    // StringUtils.isEmpty() -> String.isBlank() 를 주로 사용하는 듯

    /**
     * 수신자 정보 유무 메소드
     */
    public boolean isBlank() {
        return name.isBlank() || phone.isBlank();
    }
}
