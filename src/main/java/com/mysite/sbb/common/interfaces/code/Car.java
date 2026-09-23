package com.mysite.sbb.common.interfaces.code;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Car {

    private String name; // 브랜드 이름

    // Getter & Setter & Constructor -> Alt + Insert or Use lombok (@Data)

    // 지역변수 vs 전역변수 -> 지역변수 우선
}
