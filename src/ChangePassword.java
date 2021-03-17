import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChangePassword extends JFrame implements WindowListener, ActionListener
{

    JButton confirm = new JButton();
    JTextField currentPassword = new JTextField();
    JTextField newPassword = new JTextField();
    JTextField confirmPassword = new JTextField();
    JLabel currentPass = new JLabel();
    JLabel newPass = new JLabel();
    JLabel confirmPass = new JLabel();

    DatabaseConnection connection;

    UserView user;
    ChangePassword(UserView user)
    {

        this.user = user;

        //Francis - Frame Settings
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(400, 200);
        setLayout(null);
        setLocationRelativeTo(null);
        addWindowListener(this);
        setTitle("Change Password");

        //Francis - Current Password Message
        currentPass.setText("Please enter current password: ");
        currentPass.setFont(new Font("Courier", Font.BOLD,12));
        currentPass.setLayout(null);
        currentPass.setBounds(15,20,250,25);

        //Francis - New Password Message
        newPass.setText("Please enter new password: ");
        newPass.setFont(new Font("Courier", Font.BOLD,12));
        newPass.setLayout(null);
        newPass.setBounds(15,50,250,25);

        //Francis - Confirm Password Message
        confirmPass.setText("Please confirm your password: ");
        confirmPass.setFont(new Font("Courier", Font.BOLD,12));
        confirmPass.setLayout(null);
        confirmPass.setBounds(15,80,250,25);

        //Francis - Textfield for Current Password
        currentPassword.setBounds(200,20,180,25);
        currentPassword.setVisible(true);

        //Francis - Textfield for New Password
        newPassword.setBounds(200,50,180,25);
        newPassword.setVisible(true);

        //Francis - Textfield for Confirm Password
        confirmPassword.setBounds(200,80,180,25);
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
            ResultSet rs = connection.getResult("SELECT password FROM users WHERE key ='" + user.user.getUserID()+ "'");
            try
            {
                password = rs.getString("password");
            }
            catch (SQLException throwables)
            {
                throwables.printStackTrace();
            }
            if(currentPassword.getText().equals(password))
            {
                if(confirmPassword.getText().equals(newPassword.getText()))
                {
                    connection.execute("UPDATE users SET password='" + confirmPassword.getText() + "' WHERE key ='" + user.user.getUserID()+ "'");
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
        dispose();
        JOptionPane.showMessageDialog(null,"Password Updated!","Congratulations", JOptionPane.WARNING_MESSAGE);
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
