public class Main
{
    public boolean loggedIn;

    public static void main(String[] args)
    {
        DatabaseConnection dbCon = DatabaseConnection.getInstance();
        dbCon.init();
//        dbCon.execute("INSERT INTO users(key, username, password, first_name, middle_name, last_name) VALUES('200000', 'tenant', 'tenant', 'Paul', 'Rendon', 'Espina')");
//        dbCon.execute("INSERT INTO tenants(key, room) VALUES('200000', '1')");
//        dbCon.execute("INSERT INTO rooms(room_number, rent_amount) VALUES('1', '3000')");
        new Login();
    }
}
