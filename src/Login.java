import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener
{
    final private JTextField usernameTextField;
    final private JPasswordField passwordField;
    final private JButton submitButton;

    private int attempts;

    public Login()
    {
        attempts = 0;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(400, 200);
        setLocationRelativeTo(null);
        setTitle("Login");

        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(null);
        loginPanel.setOpaque(true);
        loginPanel.setBackground(new Color(175, 175, 175));
        loginPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.lightGray, Color.BLACK));

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(50, 40, 75, 25);
        usernameTextField = new JTextField();
        usernameTextField.setToolTipText("Enter your username here.");
        usernameTextField.setBounds(125, 40, 200, 25);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 70, 75, 25);
        passwordField = new JPasswordField();
        passwordField.setToolTipText("Enter your password here.");
        passwordField.setBounds(125, 70, 200, 25);

        submitButton = new JButton("SUBMIT");
        submitButton.setFont(new Font("Arial", Font.BOLD, 10));
        submitButton.setFocusPainted(false);
        submitButton.setBackground(Color.WHITE);
        submitButton.setBounds(125, 100, 75, 20);
        submitButton.addActionListener(this);

        loginPanel.add(usernameLabel);
        loginPanel.add(usernameTextField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(submitButton);

        add(loginPanel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == submitButton)
        {
            if(!authenticate(usernameTextField.getText(), String.valueOf(passwordField.getPassword())))
            {
                attempts += 1;
                JOptionPane.showMessageDialog(null, "Incorrect username or password!");
                switch(attempts)
                {
                    case 3:
                        JOptionPane.showMessageDialog(null, "You have 2 attempts left.", "Warning", JOptionPane.WARNING_MESSAGE);
                        break;
                    case 4:
                        JOptionPane.showMessageDialog(null, "You have 1 attempt left.", "Warning", JOptionPane.WARNING_MESSAGE);
                        break;
                    case 5:
                        JOptionPane.showMessageDialog(null, "Too many incorrect attempts! Closing the program.", "Closing", JOptionPane.ERROR_MESSAGE);
                        dispose();
                        break;
                }
                usernameTextField.setText("");
                passwordField.setText("");
            }
            else
            {
                dispose();
            }
        }
    }

    private boolean authenticate(String username, String password)
    {
        DatabaseConnection dbCon = DatabaseConnection.getInstance();
        ResultSet result = dbCon.getResult("SELECT * FROM users WHERE username='" + username + "' and password='" + password + "'");
        try
        {
            if(result.next())
            {
                int id = result.getInt("key");
                String firstName = result.getString("first_name");
                String middleName = result.getString("middle_name");
                String lastName = result.getString("last_name");
                switch((id / 100000))
                {
                    case 1:
                        new Admin(id, username, firstName, middleName, lastName);
                        break;
                    case 2:
                        new Tenant(id, username, firstName, middleName, lastName);
                        break;
                }
                return true;
            }
            return false;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }
}
