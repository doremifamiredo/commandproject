package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BankTest {
    SavingAccount saving1 = new SavingAccount(1_000,100,3_000,5);
    SavingAccount saving2 = new SavingAccount(2_000,100,3_000,5);
    CreditAccount credit1 = new CreditAccount(1_000,100,5);
    CreditAccount credit2 = new CreditAccount(2_000,100,5);
    Bank Abank = new Bank();
    // два saving аккаунта
    // баланс плательщика уменьшается
    @Test
    public void payersBalanceIsDecreasing() {
        int beforeTransfer = saving1.getBalance();
        int amount = 123;

        Abank.transfer(saving1, saving2, amount);

        int afterTransfer = saving1.getBalance();
        Assertions.assertEquals(amount, beforeTransfer - afterTransfer);
    }

    // баланс получателя увеличивается
    @Test
    public void recipientsBalanceIsIncreasing() {
        int beforeTransfer = saving2.getBalance();
        int amount = 95;

        Abank.transfer(saving1, saving2, amount);

        int afterTransfer = saving2.getBalance();
        Assertions.assertEquals(amount, afterTransfer - beforeTransfer);
    }

    // операция не прошла, у отправителя достигнут min
    @Test
    public void test1() {
        int beforeTransfer = saving2.getBalance();
        int amount = 2500;

        Abank.transfer(saving1, saving2, amount);

        int afterTransfer = saving2.getBalance();
        Assertions.assertEquals(0, afterTransfer - beforeTransfer);
    }

    @Test
    public void test2() {
        int amount = 2500;

        Assertions.assertFalse(Abank.transfer(saving1, saving2, amount));
    }

    // операция не прошла, потому что получатель превысил максимальный баланс
    // проверяем отправителя
    @Test
    public void recipientHasExceededMaximumPayersBalanceNotChange() {
        SavingAccount account1 = new SavingAccount(1500,0,15_000, 5);
        SavingAccount account2 = new SavingAccount(500,0,1_000,5);
        int beforeTransfer = account1.getBalance();
        int amount = 501;

        Abank.transfer(account1, account2, amount);

        int afterTransfer = account1.getBalance();
        Assertions.assertEquals(0 , afterTransfer - beforeTransfer);
    }

    @Test
    public void test3() {
        SavingAccount account1 = new SavingAccount(10_500,0,15_000, 5);
        SavingAccount account2 = new SavingAccount(500,0,1_000,5);

        Assertions.assertFalse(Abank.transfer(account1, account2, 2_000));
    }
    // операция не прошла, потому что получатель превысил максимальный баланс
    // проверяем получателя
    @Test
    public void recipientHasExceededMaximum() {
        SavingAccount account1 = new SavingAccount(5_135,500,15_000, 5);
        SavingAccount account2 = new SavingAccount(1_246,1_000,10_000,5);
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
        int beforeTransfer = account1.getBalance();
        int amount = 500;

        Abank.transfer(account1, account2, amount);

        int afterTransfer = account1.getBalance();
        Assertions.assertEquals(-amount, afterTransfer - beforeTransfer);
    }

    public void test4() {

    }

    // далее тесты, где два разных аккаунта
    // credit уменьшается
    @Test
    public void test5() {
        int beforeTransfer = credit1.getBalance();
        int amount = 500;

        Abank.transfer(credit1, saving2, amount);

        int afterTransfer = credit1.getBalance();
        Assertions.assertEquals(-amount, afterTransfer - beforeTransfer);
    }

    @Test
    public void test6() {
        Assertions.assertFalse(Abank.transfer(credit1, saving1, 1_500));
    }

    @Test
    public void test7() {
        Assertions.assertFalse(Abank.transfer(credit1, saving2, 1_050));
    }

    // у отправителя saving нельзя столько списать
    @Test
    public void test8() {
        int beforeTransfer = saving1.getBalance();
        int amount = 901;

        Abank.transfer(saving1, credit2, amount);

        int afterTransfer = saving1.getBalance();
        Assertions.assertEquals(0, beforeTransfer - afterTransfer);
    }

    @Test
    public void test9() {
        int beforeTransfer = saving1.getBalance();
        int amount = 550;

        Abank.transfer(saving1, credit2, amount);

        int afterTransfer = saving1.getBalance();
        Assertions.assertEquals(amount, beforeTransfer - afterTransfer);
    }

    @Test
    public void test10() {
        Assertions.assertFalse(Abank.transfer(saving2, credit2, 10_050));
    }
}