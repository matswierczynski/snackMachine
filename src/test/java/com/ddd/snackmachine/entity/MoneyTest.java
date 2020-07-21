package com.ddd.snackmachine.entity;


import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.*;

@RunWith(JUnitParamsRunner.class)
public class MoneyTest {

    private static final int NUMBER_OF_ONE_CENT_COINS = 1;
    private static final int NUMBER_OF_TEN_CENT_COINS = 2;
    private static final int NUMBER_OF_QUARTER_CENT_COINS = 3;
    private static final int NUMBER_OF_ONE_DOLLAR_BILLS = 4;
    private static final int NUMBER_OF_FIVE_DOLLAR_BILLS = 5;
    private static final int NUMBER_OF_TWENTY_DOLLAR_BILLS = 6;
    private static final int NUMBER_OF_ONE_CENT_COINS_IN_DOLLAR = 100;
    private static final int NUMBER_OF_TEN = 10;
    private static final int MONEY_MULTIPLIER = 2;

    @Test
    public void shouldCorrectlySumTwoMoneyObjects() {
        // given
        Money money = new Money(NUMBER_OF_ONE_CENT_COINS, NUMBER_OF_TEN_CENT_COINS, NUMBER_OF_QUARTER_CENT_COINS, NUMBER_OF_ONE_DOLLAR_BILLS,
                NUMBER_OF_FIVE_DOLLAR_BILLS, NUMBER_OF_TWENTY_DOLLAR_BILLS);
        Money moneyToAdd = new Money(NUMBER_OF_ONE_CENT_COINS, NUMBER_OF_TEN_CENT_COINS, NUMBER_OF_QUARTER_CENT_COINS, NUMBER_OF_ONE_DOLLAR_BILLS,
                NUMBER_OF_FIVE_DOLLAR_BILLS, NUMBER_OF_TWENTY_DOLLAR_BILLS);

        // when
        Money summedMoney = Money.add(money, moneyToAdd);

        // then
        assertThat(summedMoney).isNotNull();
        assertThat(summedMoney.getOneCentCount()).isEqualTo(NUMBER_OF_ONE_CENT_COINS * MONEY_MULTIPLIER);
        assertThat(summedMoney.getTenCentCount()).isEqualTo(NUMBER_OF_TEN_CENT_COINS * MONEY_MULTIPLIER);
        assertThat(summedMoney.getQuarterCentCount()).isEqualTo(NUMBER_OF_QUARTER_CENT_COINS * MONEY_MULTIPLIER);
        assertThat(summedMoney.getOneDollarCount()).isEqualTo(NUMBER_OF_ONE_DOLLAR_BILLS * MONEY_MULTIPLIER);
        assertThat(summedMoney.getFiveDollarCount()).isEqualTo(NUMBER_OF_FIVE_DOLLAR_BILLS * MONEY_MULTIPLIER);
        assertThat(summedMoney.getTwentyDollarCount()).isEqualTo(NUMBER_OF_TWENTY_DOLLAR_BILLS * MONEY_MULTIPLIER);
    }

    @Test
    public void shouldDetermineTheStructuralEqualityBetweenTwoMoneyObjectsIfTheyContainTheSameAmountOfMoney() {
        // given
        Money money = new Money(NUMBER_OF_ONE_CENT_COINS, NUMBER_OF_TEN_CENT_COINS, NUMBER_OF_QUARTER_CENT_COINS, NUMBER_OF_ONE_DOLLAR_BILLS,
                NUMBER_OF_FIVE_DOLLAR_BILLS, NUMBER_OF_TWENTY_DOLLAR_BILLS);
        Money moneyToCompare = new Money(NUMBER_OF_ONE_CENT_COINS, NUMBER_OF_TEN_CENT_COINS, NUMBER_OF_QUARTER_CENT_COINS, NUMBER_OF_ONE_DOLLAR_BILLS,
                NUMBER_OF_FIVE_DOLLAR_BILLS, NUMBER_OF_TWENTY_DOLLAR_BILLS);

        // when
        boolean areMoneyObjectsEqual = money.equals(moneyToCompare);

        // then
        assertThat(areMoneyObjectsEqual).isTrue();
    }

    @Test
    public void shouldDetermineThatTwoMoneyObjectsAreNotEqualIfTheContainTheSameAmountOfMoneyInDifferentCurrency() {
        // given
        Money oneDollarBillMoney = new Money(0, 0, 0, NUMBER_OF_TEN_CENT_COINS, 0, 0);
        Money oneDollarCoinsMoney = new Money(NUMBER_OF_ONE_CENT_COINS_IN_DOLLAR, 0, 0, 0, 0, 0);

        // when
        boolean areMoneyObjectsEqual = oneDollarBillMoney.equals(oneDollarCoinsMoney);

        // then
        assertThat(areMoneyObjectsEqual).isFalse();
    }

    @Test
    public void shouldReturnTheSameHashCodeForTwoMoneyObjectsIfTheyContainTheSameAmountOfMoney() {
        // given
        Money money = new Money(NUMBER_OF_ONE_CENT_COINS, NUMBER_OF_TEN_CENT_COINS, NUMBER_OF_QUARTER_CENT_COINS, NUMBER_OF_ONE_DOLLAR_BILLS,
                NUMBER_OF_FIVE_DOLLAR_BILLS, NUMBER_OF_TWENTY_DOLLAR_BILLS);
        Money moneyToCompare = new Money(NUMBER_OF_ONE_CENT_COINS, NUMBER_OF_TEN_CENT_COINS, NUMBER_OF_QUARTER_CENT_COINS, NUMBER_OF_ONE_DOLLAR_BILLS,
                NUMBER_OF_FIVE_DOLLAR_BILLS, NUMBER_OF_TWENTY_DOLLAR_BILLS);

        // when
        int moneyHashCode = money.hashCode();

        // then
        assertThat(moneyHashCode).isEqualTo(moneyToCompare.hashCode());
    }

    @Test
    public void shouldNotReturnTwoHashCodeForTwoMoneyObjectsIfTheyContainsTheSameAmountOfMoneyInDifferentCurrency() {
        Money oneDollarBillMoney = new Money(0, 0, 0, NUMBER_OF_TEN_CENT_COINS, 0, 0);
        Money oneDollarCoinsMoney = new Money(NUMBER_OF_ONE_CENT_COINS_IN_DOLLAR, 0, 0, 0, 0, 0);

        // when
        int moneyHashCode = oneDollarBillMoney.hashCode();

        // then
        assertThat(moneyHashCode).isNotEqualTo(oneDollarCoinsMoney.hashCode());
    }

    @Test
    @Parameters({
            "-1, 0, 0, 0, 0, 0",
            "0, -1, 0, 0, 0, 0",
            "0, 0, -1, 0, 0, 0",
            "0, 0, 0, -1, 0, 0",
            "0, 0, 0, 0, -1, 0",
            "0, 0, 0, 0, 0, -1"
    })
    public void shouldNotAllowToCreateMoneyWithNegativeValuesOfCoinsOrBills(int numberOfOneCentCoins, int numberOfTenCentCoins, int numberOfQuarterCentCoins,
                                                                            int numberOfOneDollarBills, int numberOfFiveDollarBills,
                                                                            int numberOfTwentyDollarBills) {
        // when
        Throwable thrown = catchThrowable(() -> new Money(numberOfOneCentCoins, numberOfTenCentCoins, numberOfQuarterCentCoins, numberOfOneDollarBills,
                numberOfFiveDollarBills, numberOfTwentyDollarBills));

        // then
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @Parameters({
            "0, 0, 0, 0, 0, 0, 0",
            "1, 0, 0, 0, 0, 0, 0.01",
            "1, 2, 0, 0, 0, 0, 0.21",
            "1, 2, 3, 0, 0, 0, 0.96",
            "1, 2, 3, 4, 0, 0, 4.96",
            "1, 2, 3, 4, 5, 0, 29.96",
            "1, 2, 3, 4, 5, 6, 149.96",
            "11, 0, 0, 0, 0, 0, 0.11",
            "110, 0, 0, 0, 100, 0, 501.1"
    })
    public void shouldCorrectlySumTheTotalAmountOfMoneyForASingleMoneyObject(int numberOfOneCentCoins, int numberOfTenCentCoins, int numberOfQuarterCentCoins,
                                                                             int numberOfOneDollarBills, int numberOfFiveDollarBills,
                                                                             int numberOfTwentyDollarBills, float totalAmountOfMoney) {
        // given
        Money money = new Money(numberOfOneCentCoins, numberOfTenCentCoins, numberOfQuarterCentCoins, numberOfOneDollarBills,
                numberOfFiveDollarBills, numberOfTwentyDollarBills);

        // when
        double amountOfCalculatedMoney = money.getAmount() ;

        // then
        assertThat(amountOfCalculatedMoney).isEqualTo(totalAmountOfMoney);
    }

    public void shouldCorrectlySubtractTwoAmountOfMoneyObjects() {
        // given
        Money money = new Money(NUMBER_OF_TEN, NUMBER_OF_TEN, NUMBER_OF_TEN, NUMBER_OF_TEN, NUMBER_OF_TEN, NUMBER_OF_TEN);
        Money moneyToSubtract = new Money(NUMBER_OF_ONE_CENT_COINS, NUMBER_OF_TEN_CENT_COINS, NUMBER_OF_QUARTER_CENT_COINS, NUMBER_OF_ONE_DOLLAR_BILLS,
                NUMBER_OF_FIVE_DOLLAR_BILLS, NUMBER_OF_TWENTY_DOLLAR_BILLS);

        // when
        Money subtractedMoney = Money.subtract(money, moneyToSubtract);

        // then
        assertThat(subtractedMoney.getOneCentCount()).isEqualTo(NUMBER_OF_TEN - NUMBER_OF_ONE_CENT_COINS);
        assertThat(subtractedMoney.getTenCentCount()).isEqualTo(NUMBER_OF_TEN - NUMBER_OF_TEN_CENT_COINS);
        assertThat(subtractedMoney.getQuarterCentCount()).isEqualTo(NUMBER_OF_TEN - NUMBER_OF_QUARTER_CENT_COINS);
        assertThat(subtractedMoney.getOneDollarCount()).isEqualTo(NUMBER_OF_TEN - NUMBER_OF_ONE_DOLLAR_BILLS);
        assertThat(subtractedMoney.getFiveDollarCount()).isEqualTo(NUMBER_OF_TEN - NUMBER_OF_FIVE_DOLLAR_BILLS);
        assertThat(subtractedMoney.getTwentyDollarCount()).isEqualTo(NUMBER_OF_TEN - NUMBER_OF_TWENTY_DOLLAR_BILLS);
    }

}