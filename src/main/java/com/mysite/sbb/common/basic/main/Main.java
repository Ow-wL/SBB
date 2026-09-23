package com.mysite.sbb.common.basic.main;

import com.mysite.sbb.common.basic.code.Car;
import com.mysite.sbb.common.basic.code.Money;

public class Main {
    public static void main(String[] args) {
        Car car = new Car("소나타");
        Car car1 = new Car();
        Money money = new Money(1000);
        money.getAmount();
        System.out.println(money.toString());
    }
}
