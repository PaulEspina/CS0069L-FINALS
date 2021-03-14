public class Main
{
    public boolean loggedIn;

    public static void main(String[] args)
    {
        DatabaseConnection dbCon = DatabaseConnection.getInstance();
        dbCon.init();
        new Login();
    }
}
