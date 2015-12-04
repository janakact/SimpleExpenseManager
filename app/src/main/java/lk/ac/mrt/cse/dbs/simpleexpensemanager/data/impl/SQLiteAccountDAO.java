package lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.AccountDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.exception.InvalidAccountException;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Account;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.ExpenseType;

import android.database.Cursor;
import  android.database.sqlite.*;
import android.support.annotation.NonNull;

/**
 * Created by Janaka on 04/12/2015.
 */
public class SQLiteAccountDAO implements AccountDAO {
    SQLiteDatabase db;

    public SQLiteAccountDAO()
    {
        db = SQLiteDatabase.openOrCreateDatabase("130594B",null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Account(accountNo VARCHAR,bankName VARCHAR,accountHolderName VARCHAR, balance NUMERIC(10,2));");
    }

    @Override
    public List<String> getAccountNumbersList() {
        Cursor resultSet = db.rawQuery("Select accountNo from Account",null);
        List<String> result = new ArrayList<String>();
        while(!resultSet.isAfterLast())
        {
            result.add(resultSet.getString(1));
            resultSet.moveToNext();
        }
        return result;
    }

    @Override
    public List<Account> getAccountsList() {
        Cursor resultSet = db.rawQuery("Select * from Account",null);
        List<Account> result = new ArrayList<Account>();
        while(!resultSet.isAfterLast())
        {

            result.add( new Account(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3), Double.parseDouble(resultSet.getString(4) ) ));
            resultSet.moveToNext();
        }
        return result;
    }

    @Override
    public Account getAccount(String accountNo) throws InvalidAccountException {
            Cursor resultSet = db.rawQuery("Select * from Account where accountNo=" + accountNo, null);
            if (resultSet.isAfterLast()) {
                throw new InvalidAccountException("Account No:" + accountNo + " is not valid!");
            }
            return new Account(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), Double.parseDouble(resultSet.getString(4)));
    }

    @Override
    public void addAccount(Account account) {
        db.execSQL("INSERT INTO Account VALUES('"+account.getAccountNo()+"','"+account.getBankName()+"','"+account.getAccountHolderName()+"','"+account.getBalance()+"');");
    }

    @Override
    public void removeAccount(String accountNo) throws InvalidAccountException {
        db.execSQL("DELETE FROM Account WHERE accountNo="+accountNo);
    }

    @Override
    public void updateBalance(String accountNo, ExpenseType expenseType, double amount) throws InvalidAccountException {
        Account account = getAccount(accountNo);

        double balance = account.getBalance();
        if (ExpenseType.INCOME == expenseType) {
            balance += amount;
        } else
            balance-=amount;
        db.execSQL("UPDATE Account SET balance="+balance+" WHERE accountNo="+accountNo);
    }
}
