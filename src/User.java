public abstract class User
{
    protected int userID;
    protected String username;
    protected String password;
    protected String firstName;
    protected String middleName;
    protected String lastName;

    public User()
    {
        userID = 0;
        username = "";
        password = "";
        firstName = "";
        middleName = "";
        lastName = "";
    }

    public User(int userID, String username, String password, String firstName, String middleName, String lastName)
    {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    public abstract void logout();
}
