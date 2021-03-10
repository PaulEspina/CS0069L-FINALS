public class Admin extends User
{
    public Admin()
    {
        super();
    }

    public Admin(int userID, String username, String firstName, String middleName, String lastName)
    {
        super(userID, username, firstName, middleName, lastName);
        System.out.println("admin");
    }

    @Override
    public void logout()
    {

    }
}
