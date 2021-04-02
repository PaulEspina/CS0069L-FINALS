import javax.swing.*;
import java.awt.*;

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
        frame = new JFrame();
        frame.setTitle("Apartment Management System");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setIconImage(new ImageIcon("icon48.png").getImage());
        sidePanel = new JPanel();
        sidePanel.setPreferredSize(new Dimension(140, 600));
        sidePanel.setLayout(new GridLayout(3, 1));
        sidePanel.setBackground(Color.WHITE);
        sidePanel.setOpaque(true);
        contentPanel = new JPanel();
        contentPanel.setOpaque(true);
        contentPanel.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        contentPanel.setLayout(null);
    }

    protected abstract void side();

    protected abstract void content();

    protected abstract void refresh();

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

    public JButton getProfileButton()
    {
        return profileButton;
    }
}
