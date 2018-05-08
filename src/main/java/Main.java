import atm.util.DatabaseConnection;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {
        Connection  connection = DatabaseConnection.getConnection();
        Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE newsparser(style VARCHAR(8),parsedatetime VARCHAR(25),time VARCHAR(6)," +
                    "headerurl VARCHAR(600),subtitle VARCHAR (600), mark VARCHAR(30)) ");
        }

    }

