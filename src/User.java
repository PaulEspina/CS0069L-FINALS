import javax.swing.*;
import java.awt.*;

public abstract class User
{
    private int userID; // 5 digits xyyyy, x=usertype, y=id
    private String username;
    private String firstName;
    private String middleName;
    private String lastName;
    protected JFrame window;
    protected JPanel headerPanel;
    protected JPanel navPanel;
    protected JPanel contentPanel;

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
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(800, 600);
        window.setMinimumSize(new Dimension(800, 600));
//        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setTitle("Apartment Management System");
        header();
        nav();
        content();
        window.add(headerPanel, BorderLayout.NORTH);
        window.add(navPanel, BorderLayout.WEST);
        window.add(contentPanel, BorderLayout.CENTER);
        window.setVisible(true);
    }

    private void header()
    {
        headerPanel = new JPanel();
        headerPanel.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        headerPanel.setPreferredSize(new Dimension(window.getWidth(), window.getHeight() / 8));
        headerPanel.setLayout(new BorderLayout());

        JLabel title = new JLabel("Apartment Management System");
        title.setFont(new Font("Arial", Font.BOLD, 24));

        JPanel userInfoPanel = new JPanel();
        userInfoPanel.setPreferredSize(new Dimension(window.getWidth() / 6, window.getHeight()));
        userInfoPanel.setBackground(Color.BLACK);
        userInfoPanel.setOpaque(true);

        headerPanel.add(title, BorderLayout.CENTER);
        headerPanel.add(userInfoPanel, BorderLayout.EAST);
    }

    protected abstract void nav();

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
