package com.epam.by.dao;

import com.epam.by.beans.BankAccount;
import com.epam.by.beans.User;

import java.util.List;

public interface BankAccountDao {

    BankAccount getBankAccountByUserId(Integer userId);

    List<BankAccount> getAll();

    BankAccount create(BankAccount bankAccount);

    BankAccount update(BankAccount bankAccount);

    void delete(BankAccount bankAccount);
}
