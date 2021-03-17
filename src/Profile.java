import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Profile extends JFrame implements WindowListener, ActionListener
{

    //Francis - Declare variables
    DatabaseConnection connection;

    JLabel userid = new JLabel();
    JLabel username = new JLabel();
    JLabel usertype = new JLabel();
    JLabel firstname = new JLabel();
    JLabel middlename = new JLabel();
    JLabel lastname = new JLabel();
    JTextField newfirstname = new JTextField();
    JTextField newmiddlename = new JTextField();
    JTextField newlastname = new JTextField();
    JButton edit1 = new JButton();
    JButton confirm1 = new JButton();
    JButton edit2 = new JButton();
    JButton confirm2 = new JButton();
    JButton edit3 = new JButton();
    JButton confirm3 = new JButton();
    JButton changepass = new JButton();

    UserView user;
    Profile(UserView user)
    {

        this.user = user;

        //Francis - Frame Settings
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(500, 300);
        setLayout(null);
        setLocationRelativeTo(null);
        addWindowListener(this);
        setTitle("Profile");

        //Francis - Details of the User
        userid.setText("User ID: " + user.user.getUserID());
        userid.setBounds(180,30,1000,25);
        userid.setFont(new Font("Courier",Font.PLAIN,14));

        //Francis
        username.setText("Username: "+ user.user.getUsername());
        username.setBounds(180,60,200,25);
        username.setFont(new Font("Courier",Font.PLAIN,14));

        //Francis
        usertype.setBounds(180,90,200,25);
        usertype.setFont(new Font("Courier",Font.PLAIN,14));
        if(user.user.getUserID() / 100000 == 1){
            usertype.setText("User Type: Admin");
        }
        else{
            usertype.setText("User Type: Tenant");
        }
        //Francis
        firstname.setText("First Name: "+ user.user.getFirstName());
        firstname.setBounds(30,140,200,25);
        firstname.setFont(new Font("Courier",Font.PLAIN,14));

        //Francis
        middlename.setText("Middle Name: "+ user.user.getMiddleName());
        middlename.setBounds(30,170,200,25);
        middlename.setFont(new Font("Courier",Font.PLAIN,14));

        //Francis
        lastname.setText("Last Name: "+ user.user.getLastName());
        lastname.setBounds(30,200,200,25);
        lastname.setFont(new Font("Courier",Font.PLAIN,14));

        //Francis
        //edit button for first name
        newfirstname.setBounds(130,140,200,25);
        newfirstname.setVisible(false);
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

        //Francis
        //edit button for middle name
        newmiddlename.setBounds(130,170,200,25);
        newmiddlename.setVisible(false);
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

        //Francis
        //edit button for last name
        newlastname.setBounds(130,200,200,25);
        newlastname.setVisible(false);
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

        setVisible(true);

        //added list
        add(userid);
        add(username);
        add(usertype);
        add(firstname);
        add(middlename);
        add(lastname);
        add(edit1);
        add(confirm1);
        add(newfirstname);
        add(edit2);
        add(confirm2);
        add(newmiddlename);
        add(confirm3);
        add(edit3);
        add(newlastname);
    }

    //Francis
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == edit1){
            edit1.setVisible(false);
            confirm1.setVisible(true);
            newfirstname.setVisible(true);
            firstname.setText("First Name: ");

        }
        if(e.getSource() == confirm1){
            if(newfirstname.getText().length() < 1){
                JOptionPane.showMessageDialog(null,"Please enter atleast 1 characters !","ERROR", JOptionPane.WARNING_MESSAGE);
            }
            else {
                edit1.setEnabled(true);
                edit1.setVisible(true);
                confirm1.setVisible(false);
                newfirstname.setVisible(false);
                firstname.setText("First Name: " + newfirstname.getText());
                user.user.setFirstName(newfirstname.getText());

                connection = DatabaseConnection.getInstance();
                connection.execute("UPDATE users SET first_name='" + newfirstname.getText() + "' WHERE key= '" + user.user.getUserID() + "'");
            }
        }
        if(e.getSource() == edit2){
            edit2.setVisible(false);
            confirm2.setVisible(true);
            newmiddlename.setVisible(true);
            middlename.setText("Middle Name: ");

        }
        if(e.getSource() == confirm2){
            if(newmiddlename.getText().length() < 1){
                JOptionPane.showMessageDialog(null,"Please enter atleast 1 characters !","ERROR", JOptionPane.WARNING_MESSAGE);
            }
            else {
                edit2.setEnabled(true);
                edit2.setVisible(true);
                confirm2.setVisible(false);
                newmiddlename.setVisible(false);
                middlename.setText("Middle Name: " + newmiddlename.getText());
                user.user.setMiddleName(newmiddlename.getText());

                connection = DatabaseConnection.getInstance();
                connection.execute("UPDATE users SET middle_name='" + newmiddlename.getText() + "' WHERE key= '" + user.user.getUserID() + "'");
            }
        }
        if(e.getSource() == edit3){
            edit3.setVisible(false);
            confirm3.setVisible(true);
            newlastname.setVisible(true);
            lastname.setText("Last Name: ");

        }
        if(e.getSource() == confirm3){
            if(newlastname.getText().length() < 1){
                JOptionPane.showMessageDialog(null,"Please enter atleast 1 characters !","ERROR", JOptionPane.WARNING_MESSAGE);
            }
            else {
                edit3.setEnabled(true);
                edit3.setVisible(true);
                confirm3.setVisible(false);
                newlastname.setVisible(false);
                lastname.setText("Last Name: " + newlastname.getText());
                user.user.setLastName(newlastname.getText());

                connection = DatabaseConnection.getInstance();
                connection.execute("UPDATE users SET last_name='" + newlastname.getText() + "' WHERE key= '" + user.user.getUserID() + "'");
            }
        }
    }
    // -- End of Francis' Coding --
    @Override
    public void windowOpened(WindowEvent e)
    {

    }

    @Override
    public void windowClosing(WindowEvent e)
    {

        user.getProfileButton().setEnabled(true);
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
