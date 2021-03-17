import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChangePassword extends JFrame implements WindowListener, ActionListener {

    JButton confirm = new JButton();
    JTextField currentpassword = new JTextField();
    JTextField newpassword = new JTextField();
    JTextField confirmpassword = new JTextField();
    JLabel currentpass = new JLabel();
    JLabel newpass = new JLabel();
    JLabel confirmpass = new JLabel();

    DatabaseConnection connection;

    UserView user;
    ChangePassword(UserView user){

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
        currentpass.setText("Please enter current password: ");
        currentpass.setFont(new Font("Courier", Font.BOLD,12));
        currentpass.setLayout(null);
        currentpass.setBounds(15,20,250,25);

        //Francis - New Password Message
        newpass.setText("Please enter new password: ");
        newpass.setFont(new Font("Courier", Font.BOLD,12));
        newpass.setLayout(null);
        newpass.setBounds(15,50,250,25);

        //Francis - Confirm Password Message
        confirmpass.setText("Please confirm your password: ");
        confirmpass.setFont(new Font("Courier", Font.BOLD,12));
        confirmpass.setLayout(null);
        confirmpass.setBounds(15,80,250,25);

        //Francis - Textfield for Current Password
        currentpassword.setBounds(200,20,180,25);
        currentpassword.setVisible(true);

        //Francis - Textfield for New Password
        newpassword.setBounds(200,50,180,25);
        newpassword.setVisible(true);

        //Francis - Textfield for Confirm Password
        confirmpassword.setBounds(200,80,180,25);
        confirmpassword.setVisible(true);

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
        add(currentpass);
        add(newpass);
        add(confirmpass);
        add(currentpassword);
        add(newpassword);
        add(confirmpassword);
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
            if(currentpassword.getText().equals(password))
            {
                if(confirmpassword.getText().equals(newpassword.getText()))
                {
                    connection.execute("UPDATE users SET password='" + confirmpassword.getText() + "' WHERE key ='" + user.user.getUserID()+ "'");
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
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
