import javax.swing.*;

public abstract class User
{
    protected int userID; // 5 digits xyyyy, x=usertype, y=id
    protected String username;
    protected String firstName;
    protected String middleName;
    protected String lastName;

    protected JFrame frame;
    protected JPanel sidePanel;
    protected JPanel contentPanel;
    protected JButton profileButton;
    protected JButton logoutButton;
    protected JButton exitButton;

    protected User(int userID, String username, String firstName, String middleName, String lastName)
    {
        this.userID = userID;
        this.username = username;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    protected abstract void side();

    protected abstract void content();

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

    public JFrame getFrame()
    {
        return frame;
    }

    public JPanel getSidePanel()
    {
        return sidePanel;
    }

    public JPanel getContentPanel()
    {
        return contentPanel;
    }

    public JButton getProfileButton()
    {
        return profileButton;
    }
}
