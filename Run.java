package com.epam.by;

import com.epam.by.beans.BankAccount;
import com.epam.by.beans.User;
import com.epam.by.service.BankAccountService;
import com.epam.by.service.UserService;

import java.util.Scanner;

public class Run {

    public static void main(String[] args) {
        UserService userService = new UserService();
        BankAccountService bankAccountService = new BankAccountService();
        Scanner scanner = new Scanner(System.in);
        String command;
        User authenticatedUser = null;
        while (!(command = scanner.next()).equals("exit")) {
            switch (command) {
                case "login":
                    System.out.println("Enter login");
                    String login = scanner.next();
                    System.out.println("Enter password");
                    String password = scanner.next();
                    authenticatedUser = userService.authenticate(login, password);
                    if (authenticatedUser != null) {
                        System.out.println(authenticatedUser.getLogin() + " -  Successfully logged in!");
                    } else {
                        System.out.println("User not found");
                    }
                    break;
                case "register":
                    System.out.println("Enter login");
                    login = scanner.next();
                    System.out.println("Enter password");
                    password = scanner.next();
                    User registeredUser = userService.register(login, password);
                    System.out.println(registeredUser.getLogin() + " - Successfully registered");
                    break;
                case "showBankAccount":
                    if (authenticatedUser != null) {
                        BankAccount bankAccount = bankAccountService.getBankAccount(authenticatedUser);
                        if (bankAccount!=null) {
                            System.out.println(bankAccount.getBankAccountName() + " " + bankAccount.getUserMoney());
                        } else {
                            System.out.println("You dont have account yet!");
                        }
                    } else {
                        System.out.println("You should login first!");
                    }
                    break;
                case "addMoneyToAccount":
                    if (authenticatedUser != null) {
                        BankAccount bankAccount = bankAccountService.getBankAccount(authenticatedUser);
                        if (bankAccount != null) {
                            System.out.println("Your bank account is - " + bankAccount.getBankAccountName() + " - " + bankAccount.getUserMoney());
                            System.out.println("Enter amount of money to put into the account");
                            Integer money = scanner.nextInt();
                            bankAccount = bankAccountService.addMoneyToBankAccount(authenticatedUser, money);
                            System.out.println("Money successfully added - " + bankAccount.getBankAccountName() + " - " + bankAccount.getUserMoney());
                        } else {
                            System.out.println("You dont have account yet!");
                        }
                    } else {
                        System.out.println("You should login first!");
                    }
                    break;
                case "createBankAccount":
                    if (authenticatedUser != null) {
                        if (bankAccountService.isUserHaveAccount(authenticatedUser)) {
                            System.out.println("You already have account!");
                        } else {
                            System.out.println("Enter bank");
                            String bankAccountName = scanner.next();
                            System.out.println("Enter money");
                            Integer money = scanner.nextInt();
                            BankAccount bankAccount = bankAccountService.createBankAccount(authenticatedUser, bankAccountName, money);
                            System.out.println("Account succesffully created - " + bankAccount);
                        }
                    } else {
                        System.out.println("You should login first!");
                    }
                    break;
                case "deleteBankAccount":
                    if (authenticatedUser != null) {
                        bankAccountService.deleteBankAccount(authenticatedUser);
                        System.out.println("Account successfully deleted!");
                    } else {
                        System.out.println("You should login first");
                    }
                    break;
                default:
                    System.out.println("wrong command");
                    break;
            }
        }
    }
}
