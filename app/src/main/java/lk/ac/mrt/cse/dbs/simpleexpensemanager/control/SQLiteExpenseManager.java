package lk.ac.mrt.cse.dbs.simpleexpensemanager.control;

import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.AccountDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.TransactionDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl.InMemoryAccountDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl.InMemoryTransactionDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl.SQLiteAccountDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl.SQLiteTransactionDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Account;

/**
 * Created by Janaka on 04/12/2015.
 */
public class SQLiteExpenseManager extends ExpenseManager {

    public SQLiteExpenseManager() {
        setup();
    }

    @Override
    public void setup() {
        /*** Begin generating dummy data for In-Memory implementation ***/

        AccountDAO sqliteMemoryAccountDAO = new SQLiteAccountDAO();
        setAccountsDAO(sqliteMemoryAccountDAO);

        TransactionDAO sqliteTransactionDAO = new SQLiteTransactionDAO();
        setTransactionsDAO(sqliteTransactionDAO);



        // dummy data
        Account dummyAcct1 = new Account("12345eA", "Yoda Bank", "Anakin Skywalker", 10000.0);
        Account dummyAcct2 = new Account("78945Z", "Clone BC", "Obi-Wan Kenobi", 80000.0);
       getAccountsDAO().addAccount(dummyAcct1);
      getAccountsDAO().addAccount(dummyAcct2);

        /*** End ***/
    }
}
