package com.ddd.snackmachine.entity;

public class SnackMachine extends BaseEntity{

    private Money moneyInside;
    private Money moneyInTransaction;

    public SnackMachine() {
        this.moneyInside = MoneyType.EMPTY.getMoney();
        this.moneyInTransaction = MoneyType.EMPTY.getMoney();
    }

    public void insertMoney(MoneyType insertedMoney) {
      this.moneyInTransaction = Money.add(moneyInTransaction, insertedMoney.getMoney());
    }

    public void returnMoney() {
        this.moneyInTransaction = MoneyType.EMPTY.getMoney();
    }

    public void buySnack() {
        this.moneyInside = Money.add(moneyInside, moneyInTransaction);
        this.moneyInTransaction = MoneyType.EMPTY.getMoney();
    }

    public Money getMoneyInside() {
        return moneyInside;
    }

    public Money getMoneyInTransaction() {
        return moneyInTransaction;
    }
}
