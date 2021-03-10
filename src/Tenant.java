import java.util.ArrayList;

public class Tenant extends User
{
    public Tenant()
    {
        super();
    }

    public Tenant(int userID, String username, String firstName, String middleName, String lastName)
    {
        super(userID, username, firstName, middleName, lastName);
        System.out.println("tenant");
    }
    @Override
    public void logout()
    {

    }
}
