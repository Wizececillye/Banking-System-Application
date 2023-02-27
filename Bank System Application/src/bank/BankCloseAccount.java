package bank;

import bank.structure.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BankCloseAccount {
    private BankCloseAccount() {
    }

    protected static void run(UserBankAccount userBankAccount) throws SQLException {
        // insert this log
        BankUtil.insertIntoLogTable(new Log(
                userBankAccount.getUsername(), ActivityType.CloseBankAccount, null
        ));
        // the run body
        String SQLStatement = "update userBankAccount set statusID = ? where id = ?";
        PreparedStatement statement = BankUtil.connection.prepareStatement(SQLStatement);
        statement.setString(1, Integer.toString(BankAccountStatus.CLOSED.ordinal() + 1));
        statement.setString(2, Integer.toString(userBankAccount.getBankAccountID()));
        statement.executeUpdate();
    }
}