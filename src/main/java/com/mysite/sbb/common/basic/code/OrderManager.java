package com.mysite.sbb.common.basic.code;

public class OrderManager {

    private HyundaiMaker hyundaiMaker;


    public OrderManager() {
        hyundaiMaker = new HyundaiMaker();
    }

    public void order(){
        Money money = new Money(1000);
        System.out.println("판매상(입금) : " + money.getAmount());
        // Shift + Alt + L
        Car car = hyundaiMaker.sell(money);
        System.out.println("판매상(인수) : " + car.getName());
    }
}
