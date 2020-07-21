package com.ddd.snackmachine.entity;

public enum MoneyType {

    EMPTY(new Money(0, 0, 0, 0, 0, 0)),
    CENT(new Money(1, 0, 0, 0, 0, 0)),
    TEN_CENT(new Money(0, 1, 0, 0, 0, 0)),
    QUARTER(new Money(0, 0, 1, 0, 0, 0)),
    DOLLAR(new Money(0, 0, 0, 1, 0, 0)),
    FIVE_DOLLAR(new Money(0, 0, 0, 0, 1, 0)),
    TWENTY_DOLLAR(new Money(0, 0, 0, 0, 0, 1));

    private Money money;

    MoneyType(Money money) {
        this.money = money;
    }

    public Money getMoney() {
        return money;
    }
}
