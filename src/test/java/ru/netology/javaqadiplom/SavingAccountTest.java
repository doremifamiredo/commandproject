package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SavingAccountTest {

    private SavingAccount account = new SavingAccount(
            2_000,
            1_000,
            10_000,
            5
    );

    //-------Test ADD-------

    //Пополнение не превышает Max!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    @Test
    public void shouldAddLessThanMaxBalance() {

        account.add(3_000);

        Assertions.assertEquals(2_000 + 3_000, account.getBalance());
    }

    //Пополнение превышает Max
    @Test
    public void shouldAddGreatThanMaxBalance() {

        account.add(8_001);

        Assertions.assertEquals(2_000, account.getBalance());
    }

    //Пополнение равно Max!!!!!!!!!!!!!!!!!!!!!!!!
    @Test
    public void shouldAddEqualsThanMaxBalance() {

        account.add(8_000);

        Assertions.assertEquals(2_000 + 8_000, account.getBalance());
    }

    //Возвращает при пополнении равном Max!!!!!!!!!!!!!!!!!!!!!!!!
    @Test
    public void returnTrueIfAddEqualsThanMaxBalance() {

        Assertions.assertTrue(account.add(8_000));
    }

    //Возвращает при пополнении меньше Max
    @Test
    public void returnTrueIfAddLessThanMaxBalance() {

        Assertions.assertTrue(account.add(3_000));
    }

    //Возвращает при пополнении больше Max
    @Test
    public void returnIfAddLessThanMaxBalance() {

        Assertions.assertFalse(account.add(8_001));
    }

    //Возвращает при отрицательном пополнении
    @Test
    public void returnIfAddNegative() {

        Assertions.assertFalse(account.add(-1));
    }

    //Возвращает при нулевом пополнении (результат в ТЗ явно не задан)
    @Test
    public void returnIfAddZero() {

        Assertions.assertFalse(account.add(0));
    }


    //-------Test PAY-------

    //Остаток после оплаты не ниже Min
    @Test
    public void shouldPayNotLessThanMinBalance() {

        account.pay(999);

        Assertions.assertEquals(2_000 - 999, account.getBalance());
    }

    //Остаток после оплаты ниже Min!!!!!!!!!!!!!!!
    @Test
    public void shouldPayLessThanMinBalance() {

        account.pay(1_001);

        Assertions.assertEquals(2_000, account.getBalance());
    }

    //Остаток после оплаты равен Min
    @Test
    public void shouldPayEqualsThanMinBalance() {

        account.pay(1_000);

        Assertions.assertEquals(2_000 - 1_000, account.getBalance());
    }

    //Возвращает при остатке равном Min!!!!!!!!!!!!!!!!!!!!!!!!
    @Test
    public void returnTrueIfPayEqualsThanMinBalance() {

        Assertions.assertTrue(account.pay(1_000));
    }

    //Возвращает при остатке более Min
    @Test
    public void returnTrueIfPayGreatThanMinBalance() {

        Assertions.assertTrue(account.pay(999));
    }

    //Возвращает при остатке менее Min
    @Test
    public void returnIfPayLessThanMinBalance() {

        Assertions.assertFalse(account.pay(1_001));
    }

    //Возвращает при отрицательной сумме оплаты
    @Test
    public void returnIfPayNegative() {

        Assertions.assertFalse(account.pay(-1));
    }

    //Возвращает при нулевой сумме оплаты (результат в ТЗ явно не задан)
    @Test
    public void returnIfPayZero() {

        Assertions.assertFalse(account.pay(0));
    }


    //-------Test SavingAccount-------

    //Минимальный баланс больше максимального!!!!!!!!!!!!!!
    @Test
    public void minimumBalanceIsGreaterThanMaximum() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account2 = new SavingAccount(
                    2_000,
                    10_001,
                    10_000,
                    5
            );
        });
    }

    //Начальный баланс больше максимального!!!!!!!!!!!!!!
    @Test
    public void initialBalanceIsGreaterThanMaximum() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account2 = new SavingAccount(
                    10_001,
                    1_000,
                    10_000,
                    5
            );
        });
    }

    //Начальный баланс меньше минимального!!!!!!!!!!!!!!
    @Test
    public void initialBalanceIsLessThanMinimum() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account2 = new SavingAccount(
                    999,
                    1_000,
                    10_000,
                    5
            );
        });
    }

    //Минимальный баланс отрицательный!!!!!!!!!!!!!!!!!
    @Test
    public void minBalanceNegative() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account2 = new SavingAccount(
                    2_000,
                    -1,
                    10_000,
                    5
            );
        });
    }

    //Ставка отрицательная
    @Test
    public void rateNegative() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account2 = new SavingAccount(
                    2_000,
                    1_000,
                    10_000,
                    -1
            );
        });
    }




    //-------Test yearChange-------

    @Test
    public void percentCalculation(){
        SavingAccount account2 = new SavingAccount(
                2_222,
                1_000,
                10_000,
                5
        );

        int expected = 111;
        int actual = account.yearChange();

        Assertions.assertEquals(expected, actual);
    }



    //-------Test getters-------

    @Test
    public void shouldGetMinBalance(){

        int expected = 1000;
        int actual = account.getMinBalance();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldGetMaxBalance(){

        int expected = 10000;
        int actual = account.getMaxBalance();

        Assertions.assertEquals(expected, actual);
    }




}
