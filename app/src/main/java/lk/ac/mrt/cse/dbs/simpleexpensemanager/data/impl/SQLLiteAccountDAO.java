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
public class SQLLiteAccountDAO implements AccountDAO {
    SQLiteDatabase db;

    public  SQLLiteAccountDAO()
    {
        db = SQLiteDatabase.openOrCreateDatabase("account_db",null);
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

           // result.add( new Account());
            resultSet.moveToNext();
        }
        return result;
    }

    @Override
    public Account getAccount(String accountNo) throws InvalidAccountException {
        return null;
    }

    @Override
    public void addAccount(Account account) {

    }

    @Override
    public void removeAccount(String accountNo) throws InvalidAccountException {

    }

    @Override
    public void updateBalance(String accountNo, ExpenseType expenseType, double amount) throws InvalidAccountException {

    }
}
