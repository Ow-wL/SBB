package com.mysite.sbb.common.basic.main;

import com.mysite.sbb.common.basic.code.OrderManager;

public class BasicMain {
    public static void main(String[] args) {
        OrderManager orderManager = new OrderManager(); // ordermanager -> 가게(판매점)
        orderManager.order();
    }
}
