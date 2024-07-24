package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class CreditAccountTest {

    @Test
    public void shouldAddToPositiveBalance() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        account.add(3_000);

        Assertions.assertEquals(3_000, account.getBalance());
    }

    //неотрицательный баланс, ставка и лимит.
    // Должен быть отдельный класс для исключения IllegalArgumentException ?
    // отрицательная ставка
    @Test
    public void ShouldThrowAnExceptionIfTheRateIsNegative() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CreditAccount account = new CreditAccount(100, 50, -1);
        });
    }

    // отрицательный начальный баланс
    @Test
    public void ShouldThrowAnExceptionIfTheInitialBalanceIsNegative() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CreditAccount account = new CreditAccount(-16, 5_000_000, 10);
        });
    }

    // отрицательный кредитный лимит
    @Test
    public void ShouldThrowAnExceptionIfTheCreditLimitIsNegative() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CreditAccount account = new CreditAccount(80, -30, 12);
        });
    }

    // нулевой баланс
    @Test
    public void doesNotThrowAnExceptionIfInitialBalanceIsZero() {
        assertDoesNotThrow(() -> {
            CreditAccount account = new CreditAccount(0, 5_000, 10);
        });
    }

    // нулевой кредитный лимит
    @Test
    public void doesNotThrowAnExceptionIfCreditLimitIsZero() {
        assertDoesNotThrow(() -> {
            CreditAccount account = new CreditAccount(40, 0, 10);
        });
    }

    // нулевая ставка
    @Test
    public void doesNotThrowAnExceptionIfRateIsZero() {
        assertDoesNotThrow(() -> {
            CreditAccount account = new CreditAccount(30, 500, 0);
        });
    }

    //-------Test PAY-------
    //после списания остаётся положительный баланс
    @Test
    public void successfulPaymentShouldBeCompletedAndBalanceRemainsPositive() {
        CreditAccount account = new CreditAccount(80, 13_000, 12);

        account.pay(79);

        Assertions.assertEquals(1, account.getBalance());
    }

    //сняли все деньги, в долг не ушли
    @Test
    public void successfulPaymentShouldBeCompletedAndBalanceIsZero() {
        CreditAccount account = new CreditAccount(800, 13_000, 12);

        account.pay(800);

        Assertions.assertEquals(0, account.getBalance());
    }

    // !!неправильно!! считает задолжность (в балансе отражается платёж, но со знаком -
    // это будет работать только при нулевом балансе)
    // снимаем меньше кредитного лимита
    @Test
    public void paymentDoesNotExceedCreditLimit() {
        CreditAccount account = new CreditAccount(5, 50, 5);

        account.pay(54);

        Assertions.assertEquals(-49, account.getBalance());
    }

    // снимает все деньги до кредитного лимита
    @Test
    public void balanceReachesCreditLimit() {
        CreditAccount account = new CreditAccount(10, 100, 5);

        account.pay(110);

        Assertions.assertEquals(-100, account.getBalance());
    }

    // !!можно!! протратить больше кредитного лимита
    @Test
    public void balanceDoesNotChangeWhenPayingMoreThanCreditLimit() {
        CreditAccount account = new CreditAccount(80, 500, 13);

        account.pay(581);

        Assertions.assertEquals(80, account.getBalance());
    }

    // проверки на true и false
    //после списания остаётся положительный баланс, возвращает TRUE
    @Test
    public void returnTrueIfBalanceRemainsPositive() {
        CreditAccount account = new CreditAccount(80_000, 13_000, 12);

        Assertions.assertTrue(account.pay(409));
    }

    //сняли все деньги, в долг не ушли, возвращает TRUE
    @Test
    public void returnTrueIfBalanceIsZero() {
        CreditAccount account = new CreditAccount(800, 13_000, 12);

        Assertions.assertTrue(account.pay(800));
    }

    // возвращает true при снятии не больше кредитного лимита
    @Test
    public void returnTrueIfSuccessfulPayment() {
        CreditAccount account = new CreditAccount(400, 10, 10);

        Assertions.assertTrue(account.pay(409));
    }

    // возвращает false при попытке снять больше лимита
    @Test
    public void returnsFalseIfPaymentExceedsCreditLimit() {
        CreditAccount account = new CreditAccount(100, 500, 13);

        Assertions.assertFalse(account.pay(601));
    }


    // если достигаем кредитного лимита, то возвращается false ,
    // как будто платёж не прошёл, хотя баланс меняется !!!!!!!
    @Test
    public void returnsTrueIfPaymentDoesNotExceedCreditLimit() {
        CreditAccount account = new CreditAccount(11, 111, 11);

        Assertions.assertTrue(account.pay(122));
    }

    //-------Test ADD-------
    // пополняет на отрицательное число - баланс не меняется и возвращает false
    @Test
    public void replenishesByNegativeNumber() {
        CreditAccount account = new CreditAccount(200, 100, 10);

        account.add(-1);

        Assertions.assertEquals(200, account.getBalance());
    }

    @Test
    public void returnsFalseWhenReplenishesByNegativeNumber() {
        CreditAccount account = new CreditAccount(20, 10, 10);

        Assertions.assertFalse(account.add(-2));
    }

    // пополняет на 0 - баланс не меняется и возвращает false
    @Test
    public void replenishesByZero() {
        CreditAccount account = new CreditAccount(234, 112, 10);

        account.add(0);

        Assertions.assertEquals(234, account.getBalance());
    }

    @Test
    public void returnsFalseWhenReplenishesByZero() {
        CreditAccount account = new CreditAccount(220, 120, 10);

        Assertions.assertFalse(account.add(0));
    }

    // пополнение на положительное число - меняется баланс и возвращается true
    // !! неправильно считает !! в баланс записывает пополнение
    @Test
    public void replenishesByPositiveNumber() {
        CreditAccount account = new CreditAccount(212, 134, 10);

        account.add(1);

        Assertions.assertEquals(213, account.getBalance());
    }

    @Test
    public void returnsFalseWhenReplenishesByPositiveNumber() {
        CreditAccount account = new CreditAccount(0, 1, 10);

        Assertions.assertTrue(account.add(200));
    }

    //-------Test yearChange-------
    // на счёте долг
    @Test
    public void percentCalculationWhenBalanceIsNegative() {
        CreditAccount account = new CreditAccount(0, 1000, 10);
        account.pay(1000);

        Assertions.assertEquals(-100, account.yearChange());
    }

    // на счёте 0
    // всё равно высчитывает процент !!! хотя не должен
    @Test
    public void percentCalculationWhenBalanceIsZero() {
        CreditAccount account = new CreditAccount(1234, 100, 10);
        account.pay(1234);

        Assertions.assertEquals(0, account.yearChange());
    }

    // на счёте положительное число
    // всё равно высчитывает процент !!! хотя не должен
    @Test
    public void percentCalculationWhenBalanceIsPositive() {
        CreditAccount account = new CreditAccount(4321, 100, 10);

        Assertions.assertEquals(0, account.yearChange());
    }
}
