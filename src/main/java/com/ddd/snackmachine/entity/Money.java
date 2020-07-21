package com.ddd.snackmachine.entity;

import java.util.Objects;

public class Money extends ValueObject<Money> {

    private static final float ONE_CENT_DENOMINATION = 0.01f;
    private static final float TEN_CENT_DENOMINATION = 0.1f;
    private static final float QUARTER_CENT_DENOMINATION = 0.25f;
    private static final float ONE_DOLLAR_DENOMINATION = 1;
    private static final float FIVE_DOLLAR_DENOMINATION = 5;
    private static final float TWENTY_DOLLAR_DENOMINATION = 20;

    private int oneCentCount;
    private int tenCentCount;
    private int quarterCentCount;
    private int oneDollarCount;
    private int fiveDollarCount;
    private int twentyDollarCount;

    public Money(int oneCentCount, int tenCentCount, int quarterCentCount, int oneDollarCount, int fiveDollarCount, int twentyDollarCount) {
        if (oneCentCount < 0 || tenCentCount < 0 || quarterCentCount < 0 || oneDollarCount < 0 || fiveDollarCount < 0 || twentyDollarCount < 0) {
            throw new IllegalArgumentException("Value provided as an amount of coins or bills cannot be negative");
        }
        this.oneCentCount = oneCentCount;
        this.tenCentCount = tenCentCount;
        this.quarterCentCount = quarterCentCount;
        this.oneDollarCount = oneDollarCount;
        this.fiveDollarCount = fiveDollarCount;
        this.twentyDollarCount = twentyDollarCount;
    }

    public static Money add(Money basicMoney, Money moneyToAdd) {
        return new Money(
                basicMoney.oneCentCount + moneyToAdd.getOneCentCount(),
                basicMoney.tenCentCount + moneyToAdd.getTenCentCount(),
                basicMoney.quarterCentCount + moneyToAdd.getQuarterCentCount(),
                basicMoney.oneDollarCount + moneyToAdd.getOneDollarCount(),
                basicMoney.fiveDollarCount + moneyToAdd.getFiveDollarCount(),
                basicMoney.twentyDollarCount + moneyToAdd.getTwentyDollarCount());
    }

    public static Money subtract(Money basicMoney, Money moneyToSubtract) {
        return new Money(
                basicMoney.oneCentCount - moneyToSubtract.getOneCentCount(),
                basicMoney.tenCentCount - moneyToSubtract.getTenCentCount(),
                basicMoney.quarterCentCount - moneyToSubtract.getQuarterCentCount(),
                basicMoney.oneDollarCount - moneyToSubtract.getOneDollarCount(),
                basicMoney.fiveDollarCount - moneyToSubtract.getFiveDollarCount(),
                basicMoney.twentyDollarCount - moneyToSubtract.getTwentyDollarCount());
    }

    public int getOneCentCount() {
        return oneCentCount;
    }


    public int getTenCentCount() {
        return tenCentCount;
    }


    public int getQuarterCentCount() {
        return quarterCentCount;
    }


    public int getOneDollarCount() {
        return oneDollarCount;
    }

    public int getFiveDollarCount() {
        return fiveDollarCount;
    }


    public int getTwentyDollarCount() {
        return twentyDollarCount;
    }

    public float getAmount() {
        return oneCentCount * ONE_CENT_DENOMINATION
                + tenCentCount * TEN_CENT_DENOMINATION
                + quarterCentCount * QUARTER_CENT_DENOMINATION
                + oneDollarCount * ONE_DOLLAR_DENOMINATION
                + fiveDollarCount * FIVE_DOLLAR_DENOMINATION
                + twentyDollarCount * TWENTY_DOLLAR_DENOMINATION;
    }

    @Override
    protected boolean equalsCore(Money other) {
        return this.oneCentCount == other.getOneCentCount()
        && this.tenCentCount == other.getTenCentCount()
        && this.quarterCentCount == other.getQuarterCentCount()
        && this.oneDollarCount == other.getOneDollarCount()
        && this.fiveDollarCount == other.getFiveDollarCount()
        && this.twentyDollarCount == other.getTwentyDollarCount();
    }

    @Override
    protected int hashCodeCore() {
        return Objects.hash(oneCentCount, tenCentCount, quarterCentCount, oneDollarCount, fiveDollarCount, twentyDollarCount);
    }
}
