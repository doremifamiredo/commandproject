package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BankTest {

    @Test
    public void test() {
        CreditAccount account1 = new CreditAccount(1_000,5_000,15);
        SavingAccount account2 = new SavingAccount(1_000,1_000,10_000,5);
        Bank Abank = new Bank();
        int difference1 = account1.getBalance() - account2.getBalance(); //0

        Abank.transfer(account1, account2, 100);

        int difference2 = account1.getBalance() - account2.getBalance(); // 900 - 1100 = 200

        Assertions.assertEquals(difference2, 200);
    }

}