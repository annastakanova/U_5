package com.epam.by.service;

import com.epam.by.beans.BankAccount;
import com.epam.by.beans.User;
import com.epam.by.dao.BankAccountDao;
import com.epam.by.dao.daoImpl.BankAccountDaoImpl;

public class BankAccountService {

    private BankAccountDao bankAccountDao = new BankAccountDaoImpl();

    public BankAccount getBankAccount(User user) {
        return bankAccountDao.getBankAccountByUserId(user.getId());
    }

    public BankAccount createBankAccount(User user, String bankAccountName, Integer money) {
        BankAccount bankAccount = new BankAccount(bankAccountName, money, user.getId());
        return bankAccountDao.create(bankAccount);
    }

    public BankAccount addMoneyToBankAccount(User user, Integer money) {
        BankAccount bankAccount = bankAccountDao.getBankAccountByUserId(user.getId());
        bankAccount.setUserMoney(bankAccount.getUserMoney() + money);
        return bankAccountDao.update(bankAccount);
    }

    public void deleteBankAccount(User user) {
        BankAccount bankAccount = bankAccountDao.getBankAccountByUserId(user.getId());
        bankAccountDao.delete(bankAccount);
    }

    public boolean isUserHaveAccount(User user) {
        return bankAccountDao.getBankAccountByUserId(user.getId()) != null;
    }
}
