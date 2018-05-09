package atm.daoimpl;

import atm.model.Operations;
import atm.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class OperationsImpl {
    private Connection connection;

    public void saveBalanceOperation (ArrayList<Operations> operationsList) {
        PreparedStatement statement = null;
        connection = DatabaseConnection.getConnection();

        try {
            statement = connection.prepareStatement("INSERT INTO operations (cardId, datetime) VALUES (?,?)");

            for (Operations operations : operationsList) {
                statement.setLong(1, operations.getCardId());
                statement.setString(2, operations.getDateTime());

                statement.addBatch();
            }
            statement.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }


    public void saveWithdrawalOperation (ArrayList<Operations> operationsList) {
        PreparedStatement statement = null;

        connection = DatabaseConnection.getConnection();

        try {
            statement = connection.prepareStatement("INSERT INTO operations (cardId, datetime,amountwithdrawn) VALUES (?,?,?)");

            for (Operations operations : operationsList) {
                statement.setLong(1, operations.getCardId());
                statement.setString(2, operations.getDateTime());
                statement.setDouble(3, operations.getAmountWithdraw());

                statement.addBatch();
            }
            statement.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        }

}
