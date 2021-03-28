import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChangePassword extends JFrame implements ActionListener
{
    private final User user;

    private final JPasswordField currentPasswordField;
    private final JPasswordField newPasswordField;
    private final JPasswordField confirmPasswordField;
    private final JButton confirmButton;
    private final JButton cancelButton;

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
        confirmButton = new JButton("Confirm");
        confirmButton.setBackground(Color.WHITE);
        confirmButton.setBounds(275, 130, 75, 25);
        confirmButton.setFont(new Font("Arial", Font.PLAIN, 10));
        confirmButton.setFocusable(false);
        confirmButton.setOpaque(true);
        confirmButton.addActionListener(this);

        // Cancel Button
        cancelButton = new JButton("Cancel");
        cancelButton.setBackground(Color.WHITE);
        cancelButton.setBounds(200, 130, 75, 25);
        cancelButton.setFont(new Font("Arial", Font.PLAIN, 10));
        cancelButton.setFocusable(false);
        cancelButton.setOpaque(true);
        cancelButton.addActionListener(this);

        add(currentPassLabel);
        add(newPassLabel);
        add(confirmPassLabel);
        add(currentPasswordField);
        add(newPasswordField);
        add(confirmPasswordField);
        add(confirmButton);
        add(cancelButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == confirmButton)
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

        if(e.getSource() == cancelButton)
        {
            dispose();
        }
    }
}
