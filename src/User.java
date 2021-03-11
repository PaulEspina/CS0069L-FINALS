import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class User implements ActionListener
{
    private int userID; // 5 digits xyyyy, x=usertype, y=id
    private String username;
    private String firstName;
    private String middleName;
    private String lastName;
    protected JFrame window;
    protected JPanel sidePanel;
    protected JPanel contentPanel;
    CardLayout contentCard;

    protected User(int userID, String username, String firstName, String middleName, String lastName)
    {
        this.userID = userID;
        this.username = username;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    public void start()
    {
        contentCard = new CardLayout();
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(800, 600);
        window.setMinimumSize(new Dimension(800, 600));
        window.setLocationRelativeTo(null);
        window.setTitle("Apartment Management System");
        header();
        content();
        window.add(sidePanel, BorderLayout.WEST);
        window.add(contentPanel, BorderLayout.CENTER);
        window.setVisible(true);
    }

    protected abstract void header();

    protected abstract void content();

    private void logout()
    {
        DatabaseConnection dbCon = DatabaseConnection.getInstance();
        dbCon.close();
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

    // Getters
    protected int getUserID()
    {
        return userID;
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
}
