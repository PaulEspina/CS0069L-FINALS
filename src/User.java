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

    // Setters
    public void setUserID(int value)
    {
        userID = value;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public void setMiddleName(String middleName)
    {
        this.middleName = middleName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    // Getters
    public int getUserID()
    {
        return userID;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getMiddleName()
    {
        return middleName;
    }

    public String getLastName()

    {
        return lastName;
    }
}
