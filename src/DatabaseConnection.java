import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseConnection
{
    private static DatabaseConnection instance = null;
    private static Connection connection;
    private Statement statement;

    private DatabaseConnection()
    {
        connection = null;
        statement = null;
    }

    public static DatabaseConnection getInstance()
    {
        if(instance == null)
        {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public void init()
    {
        try
        {
            // CONNECT
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:data.db");
            statement = connection.createStatement();
            // INIT
            ResultSet resultSet;

            // Create a users table if none
            resultSet = statement.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='users");
            if(!resultSet.next())
            {
                statement.execute("CREATE TABLE users" +
                                  "(" +
                                  "key INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                  "username CHAR(32) NOT NULL," +
                                  "password CHAR(32) NOT NULL," +
                                  "first_name CHAR(32) NOT NULL," +
                                  "middle_name CHAR(32) NOT NULL, " +
                                  "last_name CHAR(32) NOT NULL, " +
                                  "user_id INT NOT NULL" +
                                  ")");
            }
            resultSet.close();

            // create default admin account if none
            resultSet = statement.executeQuery("SELECT username FROM user WHERE username='default_admin'");
            if(!resultSet.next())
            {
                statement.execute("INSERT INTO user(username, password, user_id) VALUES('default_admin', 'admin', '10000");
            }
            resultSet.close();

            // create tenants table if none
            resultSet = statement.executeQuery("SELECT name FROM sqlite_master WHERE type='table' and name='tenants");
            if(!resultSet.next())
            {
                statement.execute("CREATE TABLE tenants" +
                                  "(" +
                                  "key INTEGER PRIMARY KEY, " +
                                  "room INT NOT NULL" +
                                  "bills INT NOT NULL" +
                                  ")");
            }
            resultSet.close();

            // create bills table if none
            resultSet = statement.executeQuery("SELECT name FROM sqlite_master WHERE type='table' and name='bills'");
            if(!resultSet.next())
            {
                statement.execute("CREATE TABLE bills" +
                                  "(" +
                                  "key INTEGER PRIMARY KEY AUTOINCREMENT," +
                                  "recipient_id INT NOT NULL," +
                                  "dateIssued CHAR(11) NOT NULL," +
                                  "total_amount DECIMAL(65, 30) NOT NULL," +
                                  "amount_paid DECIMAL(65, 30) NOT NULL," +
                                  ")");
            }
            resultSet.close();

            // create rooms table if none
            resultSet = statement.executeQuery("SELECT name FROM sqlite_master WHERE type='table' and name='rooms");
            if(!resultSet.next())
            {
                statement.execute("CREATE TABLE rooms" +
                                  "(" +
                                  "key INTEGER PRIMARY KEY AUTOINCREMENT," +
                                  "room_number INT NOT NULL," +
                                  "rent_amount DECIMAL(65, 30) NOT NULL," +
                                  ")");
            }
            resultSet.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public ResultSet getTable(String tableName)
    {
        try
        {
            return statement.executeQuery("SELECT * from sqlite_master WHERE type='table' and name='" + tableName + "'");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet getTableEntry(String tableName, int key)
    {
        try
        {
            return statement.executeQuery("SELECT * from '" + tableName + "' WHERE key='" + key + "'");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
