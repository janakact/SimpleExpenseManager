package lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.TransactionDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Account;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.ExpenseType;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Transaction;

/**
 * Created by Janaka on 04/12/2015.
 */
public class SQLiteTransactionDAO implements TransactionDAO {

    SQLiteDatabase db;

    public SQLiteTransactionDAO()
    {
        db = SQLiteDatabase.openOrCreateDatabase("130594B",null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Transaction(accountNo VARCHAR,expenseType VARCHAR,amount numeric(10,2), _date Date);");
    }


    @Override
    public void logTransaction(Date date, String accountNo, ExpenseType expenseType, double amount) {
        db.execSQL("INSERT INTO Transaction VALUES('"+accountNo+"','"+((expenseType==ExpenseType.INCOME)?"INCOME":"EXPENSE")+"','"+amount+"','"+date.toString()+"');");
    }

    @Override
    public List<Transaction> getAllTransactionLogs() {
        Cursor resultSet = db.rawQuery("Select * from Account",null);
        List<Transaction> result = new ArrayList<Transaction>();
        while(!resultSet.isAfterLast())
        {
            result.add( new Transaction(new Date(resultSet.getString(4)),resultSet.getString(1),((resultSet.getString(2)=="INCOME")?ExpenseType.INCOME:ExpenseType.EXPENSE), Double.parseDouble(resultSet.getString(3) ) ));
            resultSet.moveToNext();
        }
        return result;
    }

    @Override
    public List<Transaction> getPaginatedTransactionLogs(int limit) {
        Cursor resultSet = db.rawQuery("Select * from Account ORDER BY DATE LIMIT "+limit,null);
        List<Transaction> result = new ArrayList<Transaction>();
        while(!resultSet.isAfterLast())
        {
            result.add( new Transaction(new Date(resultSet.getString(4)),resultSet.getString(1),((resultSet.getString(2)=="INCOME")?ExpenseType.INCOME:ExpenseType.EXPENSE), Double.parseDouble(resultSet.getString(3) ) ));
            resultSet.moveToNext();
        }
        return result;
    }
}
