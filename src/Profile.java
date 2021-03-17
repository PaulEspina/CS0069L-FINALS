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
//    JButton changepass = new JButton();

    UserView user;
    Profile(UserView user)
    {

        this.user = user;

        //Frame Settings
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(500, 300);
        setLayout(null);
        setLocationRelativeTo(null);
        addWindowListener(this);
        setTitle("Profile");

        //Details of the User
        userid.setText("User ID: " + user.user.getUserID());
        userid.setBounds(180,30,1000,25);
        userid.setFont(new Font("Courier",Font.PLAIN,14));

        username.setText("Username: "+ user.user.getUsername());
        username.setBounds(180,60,200,25);
        username.setFont(new Font("Courier",Font.PLAIN,14));

        usertype.setBounds(180,90,200,25);
        usertype.setFont(new Font("Courier",Font.PLAIN,14));
        if(user.user.getUserID() / 100000 == 1){
            usertype.setText("User Type: Admin");
        }
        else{
            usertype.setText("User Type: Tenant");
        }

        firstname.setText("First Name: "+ user.user.getFirstName());
        firstname.setBounds(30,140,200,25);
        firstname.setFont(new Font("Courier",Font.PLAIN,14));

        middlename.setText("Middle Name: "+ user.user.getMiddleName());
        middlename.setBounds(30,170,200,25);
        middlename.setFont(new Font("Courier",Font.PLAIN,14));

        lastname.setText("Last Name: "+ user.user.getLastName());
        lastname.setBounds(30,200,200,25);
        lastname.setFont(new Font("Courier",Font.PLAIN,14));

        //edit button for first name
        newfirstname.setBounds(110,140,200,25);
        newfirstname.setVisible(false);
        edit1.setText("Edit");
        edit1.setFocusable(false);
        edit1.setLayout(null);
        edit1.setFont(new Font("Courier",Font.PLAIN,10));
        edit1.setBounds(320,145,55,20);
        edit1.setBackground(Color.GRAY);
        edit1.setOpaque(true);
        edit1.addActionListener(this);
        confirm1.setText("Confirm");
        confirm1.setFocusable(false);
        confirm1.setLayout(null);
        confirm1.setFont(new Font("Courier",Font.PLAIN,10));
        confirm1.setBounds(320,143,80,20);
        confirm1.setBackground(Color.GRAY);
        confirm1.setOpaque(true);
        confirm1.addActionListener(this);

        //edit button for middle name
        newmiddlename.setBounds(130,170,100,25);
        newmiddlename.setVisible(false);
        edit2.setText("Edit");
        edit2.setFocusable(false);
        edit2.setLayout(null);
        edit2.setFont(new Font("Courier",Font.PLAIN,10));
        edit2.setBounds(320,175,55,20);
        edit2.setBackground(Color.GRAY);
        edit2.setOpaque(true);
        edit2.addActionListener(this);
        confirm2.setText("Confirm");
        confirm2.setFocusable(false);
        confirm2.setLayout(null);
        confirm2.setFont(new Font("Courier",Font.PLAIN,10));
        confirm2.setBounds(320,173,80,20);
        confirm2.setBackground(Color.GRAY);
        confirm2.setOpaque(true);
        confirm2.addActionListener(this);

        //edit button for last name
        newlastname.setBounds(110,200,200,25);
        newlastname.setVisible(false);
        edit3.setText("Edit");
        edit3.setFocusable(false);
        edit3.setLayout(null);
        edit3.setFont(new Font("Courier",Font.PLAIN,10));
        edit3.setBounds(320,205,55,20);
        edit3.setBackground(Color.GRAY);
        edit3.setOpaque(true);
        edit3.addActionListener(this);
        confirm3.setText("Confirm");
        confirm3.setFocusable(false);
        confirm3.setLayout(null);
        confirm3.setFont(new Font("Courier",Font.PLAIN,10));
        confirm3.setBounds(320,203,80,20);
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
        add(newfirstname);
        add(edit2);
        add(newmiddlename);
        add(edit3);
        add(newlastname);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == edit1){
            newfirstname.setVisible(true);
            edit1.setVisible(false);
            firstname.setText("First Name: ");
            add(confirm1);
            confirm1.setVisible(true);

        }
        if(e.getSource() == confirm1){
            edit1.setEnabled(true);
            edit1.setVisible(true);
            confirm1.setVisible(false);
            newfirstname.setVisible(false);
            firstname.setText("First Name: " + newfirstname.getText());

            connection = DatabaseConnection.getInstance();
            connection.execute("UPDATE * FROM users WHERE name='tenants'" + firstname + "'");

        }
        if(e.getSource() == edit2){
            newmiddlename.setVisible(true);
            edit2.setVisible(false);
            middlename.setText("Middle Name: ");
            add(confirm2);
            confirm2.setVisible(true);
        }
        if(e.getSource() == confirm2){
            edit2.setEnabled(true);
            edit2.setVisible(true);
            confirm2.setVisible(false);
            newmiddlename.setVisible(false);
            middlename.setText("Middle Name: " + newmiddlename.getText());

            DatabaseConnection cn = DatabaseConnection.getInstance();
            //cn.execute();
        }
        if(e.getSource() == edit3){
            newlastname.setVisible(true);
            edit3.setVisible(false);
            lastname.setText("Last Name: ");
            add(confirm3);
            confirm3.setVisible(true);
        }
        if(e.getSource() == confirm3){
            edit3.setEnabled(true);
            edit3.setVisible(true);
            confirm3.setVisible(false);
            newlastname.setVisible(false);
            lastname.setText("Last Name: " + newlastname.getText());

            DatabaseConnection cn = DatabaseConnection.getInstance();
            //cn.execute();
        }
    }

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
