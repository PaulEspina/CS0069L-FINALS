import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


public class Profile extends JFrame implements WindowListener, ActionListener
{
    DatabaseConnection connection;
    private final User user;

    private final JTextField firstNameField;
    private final JTextField middleNameField;
    private final JTextField lastNameField;
    private final JLabel firstNameValue;
    private final JLabel middleNameValue;
    private final JLabel lastNameValue;
    private final JButton editFirstNameButton;
    private final JButton confirmFirstNameButton;
    private final JButton editMiddleNameButton;
    private final JButton confirmMiddleNameButton;
    private final JButton editLastNameButton;
    private final JButton confirmLastNameButton;
    private final JButton changePassButton;
    private final JButton closeButton;

    public Profile(User user)
    {
        connection = DatabaseConnection.getInstance();
        this.user = user;

        JLabel profileLabel = new JLabel();
        profileLabel.setBounds(25, 25, 100, 100);
        profileLabel.setIcon(new ImageIcon(new ImageIcon("image/" + user.getUserID()).getImage().getScaledInstance(profileLabel.getWidth(), profileLabel.getHeight(), Image.SCALE_SMOOTH)));
        profileLabel.setVerticalAlignment(JLabel.TOP);
        profileLabel.setHorizontalAlignment(JLabel.CENTER);
        profileLabel.setHorizontalTextPosition(JLabel.CENTER);
        profileLabel.setVerticalTextPosition(JLabel.BOTTOM);
        profileLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(500, 300);
        setLayout(null);
        setLocationRelativeTo(null);
        addWindowListener(this);
        setTitle("Profile");
        setIconImage(new ImageIcon("icon48.png").getImage());

        JLabel userIDText = new JLabel("User ID:");
        userIDText.setBounds(150, 30, 100, 25);
        JLabel userIDValue = new JLabel(String.valueOf(user.getUserID()));
        userIDValue.setBounds(250, 30, 200, 25);


        JLabel usernameText = new JLabel("Username:");
        usernameText.setBounds(150, 60, 100, 25);
        JLabel usernameValue = new JLabel(user.getUsername());
        usernameValue.setBounds(250, 60, 200, 25);

        JLabel userTypeText = new JLabel("User Type:");
        userTypeText.setBounds(150, 90, 100, 25);
        JLabel userTypeValue = new JLabel(user.getUserID() / 100000 == 1 ? "Admin" : "Tenant");
        userTypeValue.setBounds(250, 90, 200, 25);

        JLabel firstNameText = new JLabel("First Name:");
        firstNameText.setBounds(25, 140, 100, 25);
        firstNameValue = new JLabel(user.getFirstName());
        firstNameValue.setBounds(125, 140, 200, 25);

        JLabel middleNameText = new JLabel("Middle Name:");
        middleNameText.setBounds(25,170,100,25);
        middleNameValue = new JLabel(user.getMiddleName());
        middleNameValue.setBounds(125, 170, 200, 25);

        JLabel lastNameText = new JLabel("Last Name:");
        lastNameText.setBounds(25,200,100,25);
        lastNameValue = new JLabel(user.getLastName());
        lastNameValue.setBounds(125, 200, 200, 25);

        firstNameField = new JTextField();
        firstNameField.setBounds(125, 140, 200, 25);
        firstNameField.setVisible(false);
        middleNameField = new JTextField();
        middleNameField.setBounds(125, 170, 200, 25);
        middleNameField.setVisible(false);
        lastNameField = new JTextField();
        lastNameField.setBounds(125, 200, 200, 25);
        lastNameField.setVisible(false);

        editFirstNameButton = new JButton("Edit");
        editFirstNameButton.setBackground(Color.WHITE);
        editFirstNameButton.setFont(new Font("Arial", Font.BOLD, 10));
        editFirstNameButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        editFirstNameButton.setFocusPainted(false);
        editFirstNameButton.setBounds(340, 145, 55, 20);
        editFirstNameButton.addActionListener(this);

        confirmFirstNameButton = new JButton("Confirm");
        confirmFirstNameButton.setBackground(Color.WHITE);
        confirmFirstNameButton.setFont(new Font("Arial", Font.BOLD, 10));
        confirmFirstNameButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        confirmFirstNameButton.setFocusPainted(false);
        confirmFirstNameButton.setVisible(false);
        confirmFirstNameButton.setBounds(340, 143, 80, 20);
        confirmFirstNameButton.addActionListener(this);

        editMiddleNameButton = new JButton("Edit");
        editMiddleNameButton.setBackground(Color.WHITE);
        editMiddleNameButton.setFont(new Font("Arial", Font.BOLD, 10));
        editMiddleNameButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        editMiddleNameButton.setFocusPainted(false);
        editMiddleNameButton.setBounds(340, 175, 55, 20);
        editMiddleNameButton.addActionListener(this);

        confirmMiddleNameButton = new JButton("Confirm");
        confirmMiddleNameButton.setBackground(Color.WHITE);
        confirmMiddleNameButton.setFont(new Font("Arial", Font.BOLD, 10));
        confirmMiddleNameButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        confirmMiddleNameButton.setFocusPainted(false);
        confirmMiddleNameButton.setVisible(false);
        confirmMiddleNameButton.setBounds(340, 173, 80, 20);
        confirmMiddleNameButton.addActionListener(this);

        editLastNameButton = new JButton("Edit");
        editLastNameButton.setBackground(Color.WHITE);
        editLastNameButton.setFont(new Font("Arial", Font.BOLD, 10));
        editLastNameButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        editLastNameButton.setFocusPainted(false);
        editLastNameButton.setBounds(340, 205, 55, 20);
        editLastNameButton.addActionListener(this);

        confirmLastNameButton = new JButton("Confirm");
        confirmLastNameButton.setBackground(Color.WHITE);
        confirmLastNameButton.setFont(new Font("Arial", Font.BOLD, 10));
        confirmLastNameButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        confirmLastNameButton.setFocusPainted(false);
        confirmLastNameButton.setVisible(false);
        confirmLastNameButton.setBounds(340, 203, 80, 20);
        confirmLastNameButton.addActionListener(this);

        //Change password
        changePassButton = new JButton("Change Password");
        changePassButton.setBackground(Color.WHITE);
        changePassButton.setFont(new Font("Arial", Font.BOLD, 10));
        changePassButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        changePassButton.setFocusPainted(false);
        changePassButton.setBounds(25, 230, 120, 20);
        changePassButton.addActionListener(this);

        //Close Button
        closeButton = new JButton("Close");
        closeButton.setBackground(Color.WHITE);
        closeButton.setFont(new Font("Arial", Font.BOLD, 10));
        closeButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        closeButton.setFocusPainted(false);
        closeButton.setBounds(410, 230, 60, 20);
        closeButton.addActionListener(this);

        setVisible(true);

        //added list
        add(profileLabel);
        add(userIDText);
        add(usernameText);
        add(userTypeText);
        add(firstNameText);
        add(middleNameText);
        add(lastNameText);
        add(editFirstNameButton);
        add(confirmFirstNameButton);
        add(firstNameField);
        add(editMiddleNameButton);
        add(confirmMiddleNameButton);
        add(middleNameField);
        add(confirmLastNameButton);
        add(editLastNameButton);
        add(lastNameField);
        add(changePassButton);
        add(closeButton);
        add(userIDValue);
        add(usernameValue);
        add(userTypeValue);
        add(firstNameValue);
        add(middleNameValue);
        add(lastNameValue);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == editFirstNameButton)
        {
            editFirstNameButton.setVisible(false);
            confirmFirstNameButton.setVisible(true);
            firstNameField.setVisible(true);
            firstNameValue.setVisible(false);
        }

        if(e.getSource() == confirmFirstNameButton)
        {
            if(!firstNameField.getText().isEmpty())
            {
                connection.execute("UPDATE users SET first_name='" + firstNameField.getText() + "' WHERE key= '" + user.getUserID() + "'");
                firstNameValue.setText(firstNameField.getText());
                user.setFirstName(firstNameField.getText());
            }
            editFirstNameButton.setEnabled(true);
            editFirstNameButton.setVisible(true);
            confirmFirstNameButton.setVisible(false);
            firstNameField.setVisible(false);
            firstNameValue.setVisible(true);
            firstNameField.setText("");
        }

        if(e.getSource() == editMiddleNameButton)
        {
            editMiddleNameButton.setVisible(false);
            confirmMiddleNameButton.setVisible(true);
            middleNameField.setVisible(true);
            middleNameValue.setVisible(false);
        }

        if(e.getSource() == confirmMiddleNameButton)
        {
            if(!middleNameField.getText().isEmpty())
            {
                connection.execute("UPDATE users SET middle_name='" + middleNameField.getText() + "' WHERE key= '" + user.getUserID() + "'");
                middleNameValue.setText(middleNameField.getText());
                user.setMiddleName(middleNameField.getText());
            }
            editMiddleNameButton.setEnabled(true);
            editMiddleNameButton.setVisible(true);
            confirmMiddleNameButton.setVisible(false);
            middleNameField.setVisible(false);
            middleNameValue.setVisible(true);
            middleNameField.setText("");
        }

        if(e.getSource() == editLastNameButton)
        {
            editLastNameButton.setVisible(false);
            confirmLastNameButton.setVisible(true);
            lastNameField.setVisible(true);
            lastNameValue.setVisible(false);
        }

        if(e.getSource() == confirmLastNameButton)
        {
            if(!lastNameField.getText().isEmpty())
            {
                connection.execute("UPDATE users SET last_name='" + lastNameField.getText() + "' WHERE key= '" + user.getUserID() + "'");
                lastNameValue.setText(lastNameField.getText());
                user.setLastName(lastNameField.getText());
            }
            editLastNameButton.setEnabled(true);
            editLastNameButton.setVisible(true);
            confirmLastNameButton.setVisible(false);
            lastNameField.setVisible(false);
            lastNameValue.setVisible(true);
            lastNameField.setText("");
        }

        if(e.getSource() == changePassButton)
        {
            new ChangePassword(user);
        }

        if(e.getSource() == closeButton)
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
