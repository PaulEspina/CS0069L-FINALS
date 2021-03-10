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
            resultSet = statement.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='users'");
            if(!resultSet.next())
            {
                statement.execute("CREATE TABLE users" +
                                  "(" +
                                  "key INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                  "username CHAR(32) NOT NULL UNIQUE," +
                                  "password CHAR(32) NOT NULL," +
                                  "first_name CHAR(32)," +
                                  "middle_name CHAR(32), " +
                                  "last_name CHAR(32)" +
                                  ")");
            }
            resultSet.close();

            // create default admin account if none
            resultSet = statement.executeQuery("SELECT username FROM users WHERE username='default_admin'");
            if(!resultSet.next())
            {
                statement.execute("INSERT INTO users(key, username, password, first_name, middle_name, last_name) VALUES('10000', 'default_admin', 'admin', '', '', '')");
            }
            resultSet.close();

            // create tenants table if none
            resultSet = statement.executeQuery("SELECT name FROM sqlite_master WHERE type='table' and name='tenants'");
            if(!resultSet.next())
            {
                statement.execute("CREATE TABLE tenants" +
                                  "(" +
                                  "key INTEGER PRIMARY KEY, " +
                                  "room INT NOT NULL," +
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
                                  "amount_paid DECIMAL(65, 30) NOT NULL" +
                                  ")");
            }
            resultSet.close();

            // create rooms table if none
            resultSet = statement.executeQuery("SELECT name FROM sqlite_master WHERE type='table' and name='rooms'");
            if(!resultSet.next())
            {
                statement.execute("CREATE TABLE rooms" +
                                  "(" +
                                  "key INTEGER PRIMARY KEY AUTOINCREMENT," +
                                  "room_number INT NOT NULL," +
                                  "rent_amount DECIMAL(65, 30) NOT NULL" +
                                  ")");
            }
            resultSet.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public ResultSet getResult(String query)
    {
        try
        {
            return statement.executeQuery(query);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public void execute(String query)
    {
        try
        {
            statement.execute(query);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
