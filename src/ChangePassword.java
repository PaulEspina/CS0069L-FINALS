import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChangePassword extends JFrame implements ActionListener
{
    private final User user;

    private final JButton confirm = new JButton();
    private final JPasswordField currentPasswordField;
    private final JPasswordField newPasswordField;
    private final JPasswordField confirmPasswordField;

    ChangePassword(User user)
    {
        this.user = user;

        // Frame Settings
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(400, 200);
        setLayout(null);
        setLocationRelativeTo(null);
        setTitle("Change Password");

        // Current Password Message
        JLabel currentPassLabel = new JLabel();
        currentPassLabel.setText("Enter current password: ");
        currentPassLabel.setBounds(25, 20, 150, 25);

        // New Password Message
        JLabel newPassLabel = new JLabel();
        newPassLabel.setText("Enter new password: ");
        newPassLabel.setBounds(25, 50, 150, 25);

        // Confirm Password Message
        JLabel confirmPassLabel = new JLabel();
        confirmPassLabel.setText("Confirm new password: ");
        confirmPassLabel.setBounds(25, 80, 150, 25);

        // Textfield for Current Password
        currentPasswordField = new JPasswordField();
        currentPasswordField.setBounds(175, 20, 175, 25);

        // Textfield for New Password
        newPasswordField = new JPasswordField();
        newPasswordField.setBounds(175, 50, 175, 25);

        // Textfield for Confirm Password
        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(175, 80, 175, 25);

        // Confirm Button
        confirm.setText("Confirm");
        confirm.setBackground(Color.WHITE);
        confirm.setBounds(290,130, 80,25);
        confirm.setFont(new Font("Arial", Font.PLAIN,10));
        confirm.setFocusable(false);
        confirm.setOpaque(true);
        confirm.addActionListener(this);

        add(currentPassLabel);
        add(newPassLabel);
        add(confirmPassLabel);
        add(currentPasswordField);
        add(newPasswordField);
        add(confirmPasswordField);
        add(confirm);

        setVisible(true);
    }

    //Francis - All actions are here
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == confirm)
        {
            if(!String.valueOf(currentPasswordField.getPassword()).isEmpty() &&
               !String.valueOf(newPasswordField.getPassword()).isEmpty() &&
               !String.valueOf(confirmPasswordField.getPassword()).isEmpty())
            {
                String password = "";
                DatabaseConnection connection = DatabaseConnection.getInstance();
                ResultSet resultSet = connection.getResult("SELECT password FROM users WHERE key ='" + user.getUserID() + "'");
                try
                {
                    password = resultSet.getString("password");
                    resultSet.close();
                }
                catch(SQLException throwables)
                {
                    throwables.printStackTrace();
                }
                if(String.valueOf(currentPasswordField.getPassword()).equals(password))
                {
                    if(String.valueOf(newPasswordField.getPassword()).equals(String.valueOf(confirmPasswordField.getPassword())))
                    {
                        connection.execute("UPDATE users SET password='" + String.valueOf(currentPasswordField.getPassword()) + "' WHERE key ='" + user.getUserID() + "'");
                        JOptionPane.showMessageDialog(null, "Password Updated!", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Password did not match!", "Password Mismatch", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Current password entered, did not match!", "Wrong Password", JOptionPane.ERROR_MESSAGE);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Please fill all of the fields.", "Insuffiecient Data", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}
