package com.ddd.snackmachine.entity;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class SnackMachineTest {

    private static final float DOLLAR_AND_CENT_AMOUNT_OF_MONEY = 1.01f;
    private static final float TWO_DOLLAR_AMOUNT_OF_MONEY = 2f;

    @Test
    public void shouldReturnAllMoneyGivenToTheCashMachineOnReturnMoneyInvocation() {
        // given
        SnackMachine snackMachine = new SnackMachine();
        snackMachine.insertMoney(MoneyType.DOLLAR);

        // when
        snackMachine.returnMoney();

        // then
        assertThat(snackMachine.getMoneyInTransaction().getAmount()).isZero();
    }

    @Test
    public void shouldAddInsertedMoneyToTheAmountOfMoneyInTransaction() {
        // given
        SnackMachine snackMachine = new SnackMachine();
        snackMachine.insertMoney(MoneyType.CENT);
        snackMachine.insertMoney(MoneyType.DOLLAR);

        // when
        float moneyInTransaction = snackMachine.getMoneyInTransaction().getAmount();

        // then
        assertThat(moneyInTransaction).isEqualTo(DOLLAR_AND_CENT_AMOUNT_OF_MONEY);
    }

    @Test
    public void shouldTransferMoneyFromTransactionIntoMoneyInsideMachineAfterPurchase() {
        // given
        SnackMachine snackMachine = new SnackMachine();
        snackMachine.insertMoney(MoneyType.DOLLAR);
        snackMachine.insertMoney(MoneyType.DOLLAR);

        // when
        snackMachine.buySnack();

        // then
        assertThat(snackMachine.getMoneyInTransaction().getAmount()).isZero();
        assertThat(snackMachine.getMoneyInside().getAmount()).isEqualTo(TWO_DOLLAR_AMOUNT_OF_MONEY);
    }
}