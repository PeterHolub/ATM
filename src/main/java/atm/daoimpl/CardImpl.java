package atm.daoimpl;

import atm.dao.CardDAO;
import atm.util.DatabaseConnection;
import java.sql.*;

public class CardImpl implements CardDAO {

    private Connection connection;

    @Override
    public boolean cardExist(long cardNumber) {

        boolean result = false;

        ResultSet resultSet = null;

        connection = DatabaseConnection.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM card WHERE cardnumber=(?)");

            statement.setLong(1, cardNumber);

            resultSet = statement.executeQuery();

            if (resultSet.first()) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (resultSet != null)
                    resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;

    }

    @Override
    public boolean cardStatus(long cardNumber) {
        String status = "";
        boolean result = false;

        ResultSet resultSet = null;

        connection = DatabaseConnection.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("SELECT cardstatus.status AS status FROM card INNER JOIN cardstatus ON card.status=cardstatus.id WHERE cardnumber=(?)");

            statement.setLong(1, cardNumber);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                status = (resultSet.getString("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (resultSet != null)
                    resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (status.equals("normal")) {
            result = true;
        }

        return result;

    }

    @Override
    public boolean isPinMatch(long cardNumber, int pincode) {

        //setting variable for pin stored from database to match with pin from user input
        int pin = 0;

        boolean result = false;

        ResultSet resultSet = null;

        connection = DatabaseConnection.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("SELECT pin FROM card WHERE cardnumber=(?)");

            statement.setLong(1, cardNumber);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                pin = (resultSet.getInt("pin"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                if (resultSet != null)
                    resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (pin == pincode) {
            result = true;
        }

        return result;


    }

    @Override
    public void blockTheCard(long cardNumber) {

        connection = DatabaseConnection.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE  card SET status =(SELECT id FROM cardstatus WHERE status='blocked') WHERE cardnumber=(?)");

            statement.setLong(1, cardNumber);

            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public double getBalance(long cardNumber) {

        double balance = 0.0;

        ResultSet resultSet = null;

        connection = DatabaseConnection.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("SELECT balance FROM card WHERE cardnumber=(?)");

            statement.setLong(1, cardNumber);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                balance = (resultSet.getDouble("balance"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                if (resultSet != null)
                    resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        return balance;


    }

    @Override
    public void balanceUpdate(long cardNumber, double balanceUpdate) {

        connection = DatabaseConnection.getConnection();


        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE  card SET balance =(?)  WHERE cardnumber=(?)");

            statement.setDouble(1, balanceUpdate);

            statement.setLong(2, cardNumber);


            statement.executeUpdate();

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

}
