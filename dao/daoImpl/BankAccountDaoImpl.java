package com.epam.by.dao.daoImpl;

import com.epam.by.beans.BankAccount;
import com.epam.by.beans.User;
import com.epam.by.constant.Constant;
import com.epam.by.dao.BankAccountDao;
import com.epam.by.service.FileService;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BankAccountDaoImpl implements BankAccountDao {

    private FileService fileService = new FileService();

    @Override
    public BankAccount getBankAccountByUserId(Integer userId) {
        List<BankAccount> bankAccounts = getAll();
        return bankAccounts.stream()
                .filter(bankAccount -> bankAccount.getUserId().equals(userId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<BankAccount> getAll() {
        final List<String> bankAccountValues = fileService.getFileContent(Constant.BANK_ACCOUNT_FILE);
        final List<BankAccount> list = new ArrayList<>();
        for (int i = 0; i < bankAccountValues.size(); i = i + 3) {
            BankAccount bankAccount = new BankAccount();
            bankAccount.setUserId(Integer.valueOf(bankAccountValues.get(i)));
            bankAccount.setBankAccountName(bankAccountValues.get(i+1));
            bankAccount.setUserMoney(Integer.valueOf(bankAccountValues.get(i + 2)));
            list.add(bankAccount);
        }
        return list;
    }

    @Override
    public BankAccount create(BankAccount bankAccount) {
        List<BankAccount> bankAccounts = getAll();
        FileOutputStream outputStream;
        try {
            outputStream = new FileOutputStream(Constant.BANK_ACCOUNT_FILE);
            StringBuilder outputString = new StringBuilder();
            for (BankAccount account : bankAccounts) {
                outputString.append(account.toString()).append("\n");
            }
            outputString.append(bankAccount.toString());
            outputStream.write(outputString.toString().getBytes());
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bankAccount;
    }

    @Override
    public BankAccount update(BankAccount bankAccount){
        List<BankAccount> bankAccounts = getAll();
        FileOutputStream outputStream;
        try {
            outputStream = new FileOutputStream(Constant.BANK_ACCOUNT_FILE);
            StringBuilder outputString = new StringBuilder();
            for (BankAccount account : bankAccounts) {
                if (account.getUserId().equals(bankAccount.getUserId())) {
                    account = bankAccount;
                }
                outputString.append(account.toString()).append("\n");
            }
            outputStream.write(outputString.toString().getBytes());
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bankAccount;
    }

    @Override
    public void delete(BankAccount bankAccount) {
        List<BankAccount> bankAccounts = getAll();
        bankAccounts.remove(bankAccount);
        FileOutputStream outputStream;
        try {
            outputStream = new FileOutputStream(Constant.BANK_ACCOUNT_FILE);
            StringBuilder outputString = new StringBuilder();
            for (BankAccount account : bankAccounts) {
                outputString.append(account.toString()).append("\n");
            }
            outputStream.write(outputString.toString().getBytes());
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
