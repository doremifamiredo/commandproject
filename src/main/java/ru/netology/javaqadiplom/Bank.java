package ru.netology.javaqadiplom;

public class Bank {

    /**
     * Операция перевода указанной суммы с одного счёта на другой.
     * Если операция прошла успешно, то баланс счёта from должен
     * уменьшиться на эту сумму, а баланс счёта to увеличиться.
     * Если операция прошла неуспешно, балансы обоих счетов никак
     * измениться не должны.
     *
     * @param from   - счёт с которого переводим
     * @param to     - счёт на который переводим
     * @param amount - сумма перевода
     * @return - true если операция прошла успешно, false иначе
     * <p>
     * <p>
     * public Bank(SavingAccount savingFrom, SavingAccount savingTo, int amount) {
     * <p>
     * }
     * <p>
     * public Bank(CreditAccount creditFrom, CreditAccount creditTo, int amount) {
     * <p>
     * }
     * <p>
     * public Bank(SavingAccount savingFrom, CreditAccount creditTo, int amount) {
     * <p>
     * }
     * <p>
     * public Bank(CreditAccount creditFrom, SavingAccount savingTo, int amount) {
     * <p>
     * }
     */
    public boolean transfer(SavingAccount from, SavingAccount to, int amount) {
        if (amount <= 0) {
            return false;
        }
        if (from.getBalance() - amount < from.minBalance) {
            return false;
        }
        if (to.getBalance() + amount > to.maxBalance) {
            return false;
        }
        from.pay(amount);
        to.add(amount);
        return true;
    }

    public boolean transfer(CreditAccount from, CreditAccount to, int amount) {
        if (amount <= 0) {
            return false;
        }
        if (from.getBalance() - amount < -from.creditLimit) {
            return false;
        }
        from.pay(amount);
        to.add(amount);
        return true;
    }

    public boolean transfer(CreditAccount from, SavingAccount to, int amount) {
        if (amount <= 0) {
            return false;
        }
        if (from.getBalance() - amount < -from.creditLimit) {
            return false;
        }
        if (to.getBalance() + amount > to.maxBalance) {
            return false;
        }
        from.pay(amount);
        to.add(amount);
        return true;
    }

    public boolean transfer(SavingAccount from, CreditAccount to, int amount) {
        if (amount <= 0) {
            return false;
        }
        if (from.getBalance() - amount < from.minBalance) {
            return false;
        }
        from.pay(amount);
        to.add(amount);
        return true;
    }
}
