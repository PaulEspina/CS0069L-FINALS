public abstract class User
{
    private int userID; // 5 digits xyyyy, x=usertype, y=id
    private String username;
    private String firstName;
    private String middleName;
    private String lastName;
    private String imagePath;

    protected User(int userID, String username, String firstName, String middleName, String lastName, String imagePath)
    {
        this.userID = userID;
        this.username = username;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.imagePath = imagePath;
    }

    // Setters
    protected void setUserID(int value)
    {
        userID = value;
    }

    protected void setUsername(String username)
    {
        this.username = username;
    }

    protected void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    protected void setMiddleName(String middleName)
    {
        this.middleName = middleName;
    }

    protected void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    protected void setImagePath(String imagePath)
    {
        this.imagePath = imagePath;
    }

    // Getters
    protected int getUserID()
    {
        return userID;
    }

    protected String getUsername()
    {
        return username;
    }

    protected String getFirstName()
    {
        return firstName;
    }

    protected String getMiddleName()
    {
        return middleName;
    }

    protected String getLastName()

    {
        return lastName;
    }

    protected String getImagePath()
    {
        return imagePath;
    }
}
