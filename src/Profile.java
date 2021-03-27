import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


public class Profile extends JFrame implements WindowListener, ActionListener
{
    private DatabaseConnection connection;
    private final User user;

    private final JLabel firstName = new JLabel();
    private final JLabel middleName = new JLabel();
    private final JLabel lastName = new JLabel();
    private final JTextField newFirstname = new JTextField();
    private final JTextField newMiddlename = new JTextField();
    private final JTextField newLastname = new JTextField();
    private final JButton edit1 = new JButton();
    private final JButton confirm1 = new JButton();
    private final JButton edit2 = new JButton();
    private final JButton confirm2 = new JButton();
    private final JButton edit3 = new JButton();
    private final JButton confirm3 = new JButton();
    private final JButton changePass = new JButton();
    private final JButton close = new JButton();

    public Profile(User user)
    {

        this.user = user;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(500, 300);
        setLayout(null);
        setLocationRelativeTo(null);
        addWindowListener(this);
        setTitle("Profile");

        JLabel userId = new JLabel();
        userId.setText("User ID: " + user.getUserID());
        userId.setBounds(180, 30, 1000, 25);
        userId.setFont(new Font("Courier", Font.PLAIN, 14));

        JLabel userName = new JLabel();
        userName.setText("Username: " + user.getUsername());
        userName.setBounds(180, 60, 200, 25);
        userName.setFont(new Font("Courier", Font.PLAIN, 14));

        JLabel userType = new JLabel();
        userType.setBounds(180, 90, 200, 25);
        userType.setFont(new Font("Courier", Font.PLAIN, 14));
        if(user.getUserID() / 100000 == 1)
        {
            userType.setText("User Type: Admin");
        }
        else
        {
            userType.setText("User Type: Tenant");
        }

        firstName.setText("First Name: "+ user.getFirstName());
        firstName.setBounds(30,140,200,25);
        firstName.setFont(new Font("Courier",Font.PLAIN,14));

        middleName.setText("Middle Name: "+ user.getMiddleName());
        middleName.setBounds(30,170,200,25);
        middleName.setFont(new Font("Courier",Font.PLAIN,14));

        lastName.setText("Last Name: "+ user.getLastName());
        lastName.setBounds(30,200,200,25);
        lastName.setFont(new Font("Courier",Font.PLAIN,14));

        //edit button for first name
        newFirstname.setBounds(130,140,200,25);
        newFirstname.setVisible(false);
        edit1.setText("Edit");
        edit1.setFocusable(false);
        edit1.setLayout(null);
        edit1.setFont(new Font("Courier",Font.PLAIN,10));
        edit1.setBounds(340,145,55,20);
        edit1.setBackground(Color.GRAY);
        edit1.setOpaque(true);
        edit1.addActionListener(this);
        confirm1.setVisible(false);
        confirm1.setText("Confirm");
        confirm1.setFocusable(false);
        confirm1.setLayout(null);
        confirm1.setFont(new Font("Courier",Font.PLAIN,10));
        confirm1.setBounds(340,143,80,20);
        confirm1.setBackground(Color.GRAY);
        confirm1.setOpaque(true);
        confirm1.addActionListener(this);

        //edit button for middle name
        newMiddlename.setBounds(130,170,200,25);
        newMiddlename.setVisible(false);
        edit2.setText("Edit");
        edit2.setFocusable(false);
        edit2.setLayout(null);
        edit2.setFont(new Font("Courier",Font.PLAIN,10));
        edit2.setBounds(340,175,55,20);
        edit2.setBackground(Color.GRAY);
        edit2.setOpaque(true);
        edit2.addActionListener(this);
        confirm2.setVisible(false);
        confirm2.setText("Confirm");
        confirm2.setFocusable(false);
        confirm2.setLayout(null);
        confirm2.setFont(new Font("Courier",Font.PLAIN,10));
        confirm2.setBounds(340,173,80,20);
        confirm2.setBackground(Color.GRAY);
        confirm2.setOpaque(true);
        confirm2.addActionListener(this);

        //edit button for last name
        newLastname.setBounds(130,200,200,25);
        newLastname.setVisible(false);
        edit3.setText("Edit");
        edit3.setFocusable(false);
        edit3.setLayout(null);
        edit3.setFont(new Font("Courier",Font.PLAIN,10));
        edit3.setBounds(340,205,55,20);
        edit3.setBackground(Color.GRAY);
        edit3.setOpaque(true);
        edit3.addActionListener(this);
        confirm3.setVisible(false);
        confirm3.setText("Confirm");
        confirm3.setFocusable(false);
        confirm3.setLayout(null);
        confirm3.setFont(new Font("Courier",Font.PLAIN,10));
        confirm3.setBounds(340,203,80,20);
        confirm3.setBackground(Color.GRAY);
        confirm3.setOpaque(true);
        confirm3.addActionListener(this);

        //Change password
        changePass.setText("Change Password");
        changePass.setFocusable(false);
        changePass.setFont(new Font("Courier", Font.PLAIN,10));
        changePass.setBounds(30,230,120,20);
        changePass.setBackground(Color.GRAY);
        changePass.setOpaque(true);
        changePass.addActionListener(this);

        //Close Button
        close.setText("Close");
        close.setFocusable(false);
        close.setFont(new Font("Courier", Font.PLAIN,10));
        close.setBounds(410,230,60,20);
        close.setBackground(Color.GRAY);
        close.setOpaque(true);
        close.addActionListener(this);

        setVisible(true);

        //added list
        add(userId);
        add(userName);
        add(userType);
        add(firstName);
        add(middleName);
        add(lastName);
        add(edit1);
        add(confirm1);
        add(newFirstname);
        add(edit2);
        add(confirm2);
        add(newMiddlename);
        add(confirm3);
        add(edit3);
        add(newLastname);
        add(changePass);
        add(close);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == edit1)
        {
            edit1.setVisible(false);
            confirm1.setVisible(true);
            newFirstname.setVisible(true);
            firstName.setText("First Name: ");

        }
        if(e.getSource() == confirm1)
        {
            if(newFirstname.getText().length() < 1)
            {
                JOptionPane.showMessageDialog(null,"Please newpass atleast 1 characters !","ERROR", JOptionPane.WARNING_MESSAGE);
            }
            else
            {
                edit1.setEnabled(true);
                edit1.setVisible(true);
                confirm1.setVisible(false);
                newFirstname.setVisible(false);
                firstName.setText("First Name: " + newFirstname.getText());
                user.setFirstName(newFirstname.getText());

                connection = DatabaseConnection.getInstance();
                connection.execute("UPDATE users SET first_name='" + newFirstname.getText() + "' WHERE key= '" + user.getUserID() + "'");
            }
        }
        if(e.getSource() == edit2)
        {
            edit2.setVisible(false);
            confirm2.setVisible(true);
            newMiddlename.setVisible(true);
            middleName.setText("Middle Name: ");
        }
        if(e.getSource() == confirm2)
        {
            if(newMiddlename.getText().length() < 1)
            {
                JOptionPane.showMessageDialog(null,"Please newpass atleast 1 characters !","ERROR", JOptionPane.WARNING_MESSAGE);
            }
            else
            {
                edit2.setEnabled(true);
                edit2.setVisible(true);
                confirm2.setVisible(false);
                newMiddlename.setVisible(false);
                middleName.setText("Middle Name: " + newMiddlename.getText());
                user.setMiddleName(newMiddlename.getText());

                connection = DatabaseConnection.getInstance();
                connection.execute("UPDATE users SET middle_name='" + newMiddlename.getText() + "' WHERE key= '" + user.getUserID() + "'");
            }
        }
        if(e.getSource() == edit3)
        {
            edit3.setVisible(false);
            confirm3.setVisible(true);
            newLastname.setVisible(true);
            lastName.setText("Last Name: ");

        }
        if(e.getSource() == confirm3)
        {
            if(newLastname.getText().length() < 1)
            {
                JOptionPane.showMessageDialog(null,"Please newpass atleast 1 characters !","ERROR", JOptionPane.WARNING_MESSAGE);
            }
            else
            {
                edit3.setEnabled(true);
                edit3.setVisible(true);
                confirm3.setVisible(false);
                newLastname.setVisible(false);
                lastName.setText("Last Name: " + newLastname.getText());
                user.setLastName(newLastname.getText());

                connection = DatabaseConnection.getInstance();
                connection.execute("UPDATE users SET last_name='" + newLastname.getText() + "' WHERE key= '" + user.getUserID() + "'");
            }
        }
        if(e.getSource() == changePass)
        {
            new ChangePassword(user);
        }
        if(e.getSource() == close)
        {
            dispose();
        }
    }

    @Override
    public void windowOpened(WindowEvent e)
    {

    }

    @Override
    public void windowClosing(WindowEvent e)
    {
    }

    @Override
    public void windowClosed(WindowEvent e)
    {
        user.getProfileButton().setEnabled(true);
        user.getProfileButton().setText(user.getFirstName() + " " + user.getLastName());
    }

    @Override
    public void windowIconified(WindowEvent e)
    {

    }

    @Override
    public void windowDeiconified(WindowEvent e)
    {

    }

    @Override
    public void windowActivated(WindowEvent e)
    {

    }

    @Override
    public void windowDeactivated(WindowEvent e)
    {

    }
}
