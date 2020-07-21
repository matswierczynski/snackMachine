package com.ddd.snackmachine.entity;

public abstract class ValueObject<T> {

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        T that = (T) o;
        return equalsCore(that);
    }

    @Override
    public int hashCode(){
        return hashCodeCore();
    }

    protected abstract boolean equalsCore(T other);

    protected abstract int hashCodeCore();


}
