package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BankTest {
    // два saving аккаунта
    // баланс плательщика уменьшается
    @Test
    public void payersBalanceIsDecreasing() {
        SavingAccount account1 = new SavingAccount(1_432,500,15_000, 5);
        SavingAccount account2 = new SavingAccount(1_876,1_000,10_000,5);
        Bank Abank = new Bank();
        int beforeTransfer = account1.getBalance();
        int amount = 123;

        Abank.transfer(account1, account2, amount);

        int afterTransfer = account1.getBalance();
        Assertions.assertEquals(amount, beforeTransfer - afterTransfer);
    }

    // баланс получателя увеличивается
    @Test
    public void recipientsBalanceIsIncreasing() {
        SavingAccount account1 = new SavingAccount(5_135,500,15_000, 5);
        SavingAccount account2 = new SavingAccount(1_246,1_000,10_000,5);
        Bank Abank = new Bank();
        int beforeTransfer = account2.getBalance();
        int amount = 975;

        Abank.transfer(account1, account2, amount);

        int afterTransfer = account2.getBalance();
        Assertions.assertEquals(amount, afterTransfer - beforeTransfer);
    }

    // операция не прошла, потому что получатель превысил максимальный баланс
    // проверяем отправителя
    @Test
    public void recipientHasExceededMaximumPayersBalanceNotChange() {
        SavingAccount account1 = new SavingAccount(1500,0,15_000, 5);
        SavingAccount account2 = new SavingAccount(500,0,1_000,5);
        Bank Abank = new Bank();
        int beforeTransfer = account1.getBalance();
        int amount = 501;

        Abank.transfer(account1, account2, amount);

        int afterTransfer = account1.getBalance();
        Assertions.assertEquals(0 , afterTransfer - beforeTransfer);
    }

    // операция не прошла, потому что получатель превысил максимальный баланс
    // проверяем получателя
    @Test
    public void recipientHasExceededMaximum() {
        SavingAccount account1 = new SavingAccount(5_135,500,15_000, 5);
        SavingAccount account2 = new SavingAccount(1_246,1_000,10_000,5);
        Bank Abank = new Bank();
        int beforeTransfer = account2.getBalance();
        int amount = 9_075;

        Abank.transfer(account1, account2, amount);

        int afterTransfer = account2.getBalance();
        Assertions.assertEquals(0, afterTransfer - beforeTransfer);
    }

    // два credit аккаунта
    // отправитель не смог перевести из-за кредитного лимита
    @Test
    public void operationFailedBecauseCreditLimit() {
        CreditAccount account1 = new CreditAccount(55,500,15);
        CreditAccount account2 = new CreditAccount(1_246,1_000,15);
        Bank Abank = new Bank();
        int beforeTransfer = account1.getBalance();
        int amount = 975;

        Abank.transfer(account1, account2, amount);

        int afterTransfer = account1.getBalance();
        Assertions.assertEquals(0, afterTransfer - beforeTransfer);
    }

    // отправитель достиг кредитного лимита
    @Test
    public void operationWasCompletedUpToCreditLimit() {
        CreditAccount account1 = new CreditAccount(0,500,15);
        CreditAccount account2 = new CreditAccount(1_246,1_000,15);
        Bank Abank = new Bank();
        int beforeTransfer = account1.getBalance();
        int amount = 500;

        Abank.transfer(account1, account2, amount);

        int afterTransfer = account1.getBalance();
        Assertions.assertEquals(-amount, afterTransfer - beforeTransfer);
    }

    // далее тесты, где два разных аккаунта
    // credit уменьшается
}