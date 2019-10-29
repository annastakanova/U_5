package com.epam.by.beans;

import java.math.BigDecimal;
import java.util.Objects;

public class BankAccount {
    private String bankAccountName;
    private Integer userMoney;
    private Integer userId;

    public BankAccount() {
    }

    public BankAccount(String bankAccountName, Integer userMoney, Integer userId) {
        this.bankAccountName = bankAccountName;
        this.userMoney = userMoney;
        this.userId = userId;
    }

    public String getBankAccountName() {
        return bankAccountName;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }

    public Integer getUserMoney() {
        return userMoney;
    }

    public void setUserMoney(Integer userMoney) {
        this.userMoney = userMoney;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        BankAccount that = (BankAccount) o;
        return Objects.equals(bankAccountName, that.bankAccountName) &&
                Objects.equals(userMoney, that.userMoney) &&
                Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bankAccountName, userMoney, userId);
    }

    @Override
    public String toString() {
        return userId + " " + bankAccountName + " " + userMoney;
    }
}
