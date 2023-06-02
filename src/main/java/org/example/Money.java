package org.example;

class Money implements Expression{
    protected int amount;
    protected String currency;
    //abstract Money times(int multiplier);
    Money(int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }
    String currency() {
        return currency;
    }
    static Money dollar(int amount) {
        return new Money(amount, "USD");
    }
    static Money franc(int amount) {
        return new Money(amount, "CHF");
    }
    public boolean equals(Object object) {
        Money money = (Money) object;
        return amount == money.amount
                && currency().equals(money.currency());
    }

    public Expression plus(Expression addend) {
        return new Sum(this, addend);
    }
    public Expression times(int multiplier) {
        return new Money(amount * multiplier, currency);
    }
    public Money reduce(Bank bank, String to) {
        int rate = bank.rate(currency, to);
        return new Money(amount / rate, to);
    }
    public String toString() {
        return amount + " " + currency;
    }
}
