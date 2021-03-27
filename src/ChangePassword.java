import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChangePassword extends JFrame implements ActionListener
{
    private DatabaseConnection connection;
    private final User user;

    private final JButton confirm = new JButton();
    private final JPasswordField currentPassword = new JPasswordField();

    ChangePassword(User user)
    {
        this.user = user;

        //Francis - Frame Settings
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(400, 200);
        setLayout(null);
        setLocationRelativeTo(null);
        setTitle("Change Password");

        //Francis - Current Password Message
        JLabel currentPass = new JLabel();
        currentPass.setText("Please enter current password: ");
        currentPass.setFont(new Font("Courier", Font.BOLD, 12));
        currentPass.setLayout(null);
        currentPass.setBounds(15, 20, 250, 25);

        //Francis - New Password Message
        JLabel newPass = new JLabel();
        newPass.setText("Please enter new password: ");
        newPass.setFont(new Font("Courier", Font.BOLD, 12));
        newPass.setLayout(null);
        newPass.setBounds(15, 50, 250, 25);

        //Francis - Confirm Password Message
        JLabel confirmPass = new JLabel();
        confirmPass.setText("Please confirm your password: ");
        confirmPass.setFont(new Font("Courier", Font.BOLD, 12));
        confirmPass.setLayout(null);
        confirmPass.setBounds(15, 80, 250, 25);

        //Francis - Textfield for Current Password
        currentPassword.setBounds(200,20,180,25);
        currentPassword.setVisible(true);

        //Francis - Textfield for New Password
        JPasswordField newPassword = new JPasswordField();
        newPassword.setBounds(200, 50, 180, 25);
        newPassword.setVisible(true);

        //Francis - Textfield for Confirm Password
        JPasswordField confirmPassword = new JPasswordField();
        confirmPassword.setBounds(200, 80, 180, 25);
        confirmPassword.setVisible(true);

        //Francis - Confirm Button
        confirm.setText("Confirm");
        confirm.setBackground(Color.GRAY);
        confirm.setBounds(290,130, 80,25);
        confirm.setFont(new Font("Courier", Font.PLAIN,10));
        confirm.setFocusable(false);
        confirm.setOpaque(true);
        confirm.setVisible(true);
        confirm.addActionListener(this);

        //Francis - add to the frame
        add(currentPass);
        add(newPass);
        add(confirmPass);
        add(currentPassword);
        add(newPassword);
        add(confirmPassword);
        add(confirm);

    }

    //Francis - All actions are here
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == confirm)
        {
            String password = null;
            connection = DatabaseConnection.getInstance();
            ResultSet rs = connection.getResult("SELECT password FROM users WHERE key ='" + user.getUserID()+ "'");
            try
            {
                password = rs.getString("password");
            }
            catch (SQLException throwables)
            {
                throwables.printStackTrace();
            }
            if(String.valueOf(currentPassword.getPassword()).equals(password))
            {
                if(String.valueOf(currentPassword.getPassword()).equals(String.valueOf(currentPassword.getPassword())))
                {
                    connection.execute("UPDATE users SET password='" + String.valueOf(currentPassword.getPassword()) + "' WHERE key ='" + user.getUserID()+ "'");
                    JOptionPane.showMessageDialog(null,"Password Updated!","Congratulations", JOptionPane.WARNING_MESSAGE);
                    dispose();
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Password Did Not Match!","ERROR",JOptionPane.WARNING_MESSAGE);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Wrong Password!","ERROR",JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}
