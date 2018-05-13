package atm.daoimpl;

import atm.dao.OperationsDAO;
import atm.model.Operations;
import atm.util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;

public class OperationsImpl implements OperationsDAO {
    private Connection connection;

    @Override
    public void saveBalanceOperation(ArrayList<Operations> operationsList) {
        PreparedStatement statement = null;
        connection = DatabaseConnection.getConnection();

        try {
            statement = connection.prepareStatement("INSERT INTO operations (cardId,operationtype, datetime,balance) VALUES (( SELECT id FROM card WHERE cardnumber = (?)),?,?,?)");

            for (Operations operations : operationsList) {
                statement.setLong(1, operations.getCardId());
                statement.setString(2, operations.getOperationType());
                statement.setString(3, operations.getDateTime());
                statement.setDouble(4, operations.getBalance());

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

    @Override
    public void saveWithdrawalOperation(ArrayList<Operations> operationsList) {
        PreparedStatement statement = null;

        connection = DatabaseConnection.getConnection();

        try {
            statement = connection.prepareStatement("INSERT INTO operations (cardId,operationtype, datetime,balance,amountwithdrawn) VALUES (( SELECT id FROM card WHERE cardnumber = (?)),?,?,?,?)");

            for (Operations operations : operationsList) {
                statement.setLong(1, operations.getCardId());
                statement.setString(2, operations.getOperationType());
                statement.setString(3, operations.getDateTime());
                statement.setDouble(4, operations.getBalance());
                statement.setDouble(5, operations.getAmountWithdrawn());

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

    @Override
    public ArrayList<Operations> getOperations(long cardNumber, String time) {

        ArrayList<Operations> operations = new ArrayList<>();
        ResultSet resultSet;
        connection = DatabaseConnection.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT  operations.operationcode, operations.datetime, operations.operationtype," +
                    "operations.balance,operations.amountwithdrawn,card.cardnumber AS cardid FROM operations INNER JOIN card ON operations.cardid = card.id WHERE card.cardnumber=(?) AND datetime=(?)");
            preparedStatement.setLong(1, cardNumber);
            preparedStatement.setString(2, time);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                int operationCode = resultSet.getInt("operationcode");
                long cardId = resultSet.getLong("cardid");
                String operationType = resultSet.getString("operationtype");
                String dateTime = resultSet.getString("datetime");
                double balance = resultSet.getDouble("balance");
                double amountWithdrawn = resultSet.getDouble("amountwithdrawn");

                operations.add(new Operations(operationCode, cardId, operationType, dateTime, balance, amountWithdrawn));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return operations;
    }

}
