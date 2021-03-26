import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Admin extends User implements ActionListener, WindowListener
{
    private JLabel icon;
    private JLabel billDate;
    private JLabel billNum;
    private JLabel billFee;
    private JLabel billMiscLabel;
    private JTextField billMisc;
    private JLabel billTotal;
    private JLabel billUname;
    private JLabel billLname;
    private JLabel billFname;
    private JLabel billMname;
    private JButton[] navButtons;
    private CardLayout contentCard;
    private JComboBox tenants;
    private JTextField lastName;
    private JTextField firstName;
    private JTextField middleName;
    private JTextField userName;
    private JTextField password;
    private JTextField confirmPass;
    private JTextField search;
    private JLabel lName;
    private JLabel fName;
    private JLabel mName;
    private JLabel uName;
    private JLabel pass;
    private JLabel cpass;
    private JButton create;
    private JButton reset;
    private JButton create1;
    private JButton reset1;
    private JButton uploadImg;
    private JRadioButton adm;
    private JRadioButton ten;

    public Admin(int userID, String username, String firstName, String middleName, String lastName, String imagePath)
    {
        super(userID, username, firstName, middleName, lastName, imagePath);
        start();
    }

    DatabaseConnection connection;

    public void start()
    {
        connection = DatabaseConnection.getInstance();
        contentCard = new CardLayout();
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Apartment Management System");
        side();
        content();
        frame.add(sidePanel, BorderLayout.WEST);
        frame.add(contentPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    protected void side()
    {
        sidePanel = new JPanel();
        sidePanel.setPreferredSize(new Dimension(frame.getWidth() / 6, frame.getHeight()));
        sidePanel.setLayout(new GridLayout(3, 1));
        sidePanel.setBackground(Color.WHITE);
        sidePanel.setOpaque(true);

        // PROFILE PANEL STARTS
        JPanel profilePanel = new JPanel();
        profilePanel.setLayout(new FlowLayout());

        JLabel profileLabel = new JLabel();
        profileLabel.setFont(new Font("Arial", Font.BOLD, 10));
        profileLabel.setIcon(new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(frame.getWidth() / 8, frame.getWidth() / 8, Image.SCALE_SMOOTH)));
        profileLabel.setVerticalAlignment(JLabel.TOP);
        profileLabel.setHorizontalAlignment(JLabel.CENTER);
        profileLabel.setHorizontalTextPosition(JLabel.CENTER);
        profileLabel.setVerticalTextPosition(JLabel.BOTTOM);

        //profileButton = new JButton(admin.getFirstName() + " " + admin.getLastName());
        profileButton = new JButton("Paul Espina");
        profileButton.setBackground(Color.WHITE);
        profileButton.setFont(new Font("Arial", Font.BOLD, 10));
        profileButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        profileButton.setFocusPainted(false);
        profileButton.addActionListener(this);

        profilePanel.add(profileLabel);
        profilePanel.add(profileButton);
        // PROFILE PANEL ENDS

        // NAVIGATION PANEL STARTS
        JPanel navigationPanel = new JPanel();
        navigationPanel.setLayout(new GridLayout(4, 1));

        navButtons = new JButton[4];
        navButtons[0] = new JButton("Manage Apartment");
        navButtons[1] = new JButton("Manage Tenants");
        navButtons[2] = new JButton("Create Bill");
        navButtons[3] = new JButton("Create User");
        for(JButton button : navButtons)
        {
            button.setBackground(Color.WHITE);
            button.setFont(new Font("Arial", Font.PLAIN, 10));
            button.setHorizontalAlignment(JButton.LEFT);
            button.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            button.setFocusPainted(false);
            navigationPanel.add(button);
            button.addActionListener(this);
        }
        // NAVIGATION PANEL ENDS

        // LOGOUT PANEL STARTS
        JPanel logoutPanel = new JPanel();
        // LOGOUT PANEL ENDS
        //TODO

        sidePanel.add(profilePanel);
        sidePanel.add(navigationPanel);
        sidePanel.add(logoutPanel);
    }

    private void manageApartment()
    {
        JPanel manageApartmentPanel = new JPanel();
        manageApartmentPanel.setBackground(Color.RED);
        manageApartmentPanel.setOpaque(true);
        //TODO

        contentPanel.add("manageApartmentPanel", manageApartmentPanel);
    }

    private void manageTenants()
    {
        JPanel manageTenantsPanel = new JPanel();
        manageTenantsPanel.setBackground(Color.GREEN);
        manageTenantsPanel.setOpaque(true);
        //TODO

        contentPanel.add("manageTenantsPanel", manageTenantsPanel);
    }

    private void createBill()
    {
        JPanel createBillPanel = new JPanel();
        createBillPanel.setLayout(null);
        createBillPanel.setBackground(Color.LIGHT_GRAY);
        createBillPanel.setOpaque(true);

        //Seach Bar
        JTextField search = new JTextField();
        search.setBounds(250,50,300,30);

        //Username
        JLabel billUname = new JLabel();
        billUname.setForeground(Color.DARK_GRAY);
//        billUname.setText("Username: ");
        billUname.setBounds(370,100,100,20);

        //Last Name
        JLabel billLname = new JLabel();
        billLname.setForeground(Color.DARK_GRAY);
//        billLname.setText("Last Name: ");
        billLname.setBounds(370,120,100,20);
        //First Name
        JLabel billFname = new JLabel();
        billFname.setForeground(Color.DARK_GRAY);
//        billFname.setText("First Name: ");
        billFname.setBounds(370,140,100,20);
        //Middle Name
        JLabel billMname = new JLabel();
        billMname.setForeground(Color.DARK_GRAY);
//        billMname.setText("Middle Name: ");
        billMname.setBounds(370,160,100,20);
        // Image
        icon = new JLabel();
        ResultSet resultSet = connection.getResult("SELECT image FROM users WHERE username = 'admin' ");
        String image_path = "";
        try
        {
            image_path = resultSet.getString("image");
            resultSet.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        icon.setIcon(new ImageIcon(image_path));
        //Date
//        LocalDate date = LocalDate.now();
        JLabel billDate = new JLabel();
        billDate.setForeground(Color.DARK_GRAY);
//        billDate.setFont(new Font("Times New Roman", Font.BOLD, 12));
//        billDate.setText("Date: " + date);
        billDate.setBounds(100,250, 200, 50);
        //Room Number
        JLabel billNum = new JLabel();
        billNum.setForeground(Color.DARK_GRAY);
//        billNum.setFont(new Font("Times New Roman", Font.BOLD, 12));
//        billNum.setText("Room Number: ");
        billNum.setBounds(100,270, 200, 50);
        //Room Fee
        JLabel billFee = new JLabel();
        billFee.setForeground(Color.DARK_GRAY);
//        billFee.setFont(new Font("Times New Roman", Font.BOLD, 12));
//        billFee.setText("Room Rent Fee: ");
        billFee.setBounds(100,290, 200, 50);
        //Miscellaneous
        JLabel billMiscLabel = new JLabel();
        billMiscLabel.setForeground(Color.DARK_GRAY);
//        billMiscLabel.setText("Miscellaneous Fee: ");
        billMiscLabel.setBounds(100, 310, 150, 50);

        JTextField billMisc = new JTextField();
        billMisc.setForeground(Color.DARK_GRAY);
        billMisc.setBounds(220,325,100, 20);
//        billMisc.setFont(new Font("Times New Roman", Font.BOLD, 12));
        //Total Bill
        JLabel billTotal = new JLabel();
        billTotal.setForeground(Color.DARK_GRAY);
//        billTotal.setFont(new Font("Times New Roman", Font.BOLD, 12));
//        billTotal.setText("Total Fee: ");
        billTotal.setBounds(100,330, 200, 50);
        //Create Button
        JButton create1 = new JButton("Create");
        create1.setBounds(500,470,100,30);
        //Reset Button
        JButton reset1 = new JButton("Reset");
        reset1.setBounds(380,470,100,30);

        createBillPanel.add(search);
        createBillPanel.add(billUname);
        createBillPanel.add(billFname);
        createBillPanel.add(billLname);
        createBillPanel.add(billMname);
        createBillPanel.add(billTotal);
        createBillPanel.add(billMiscLabel);
        createBillPanel.add(billMisc);
        createBillPanel.add(billFee);
        createBillPanel.add(billNum);
        createBillPanel.add(billDate);
        createBillPanel.add(create1);
        createBillPanel.add(reset1);

        contentPanel.add("createBillPanel", createBillPanel);
    }

    private void createUser()
    {
        JPanel createUser = new JPanel();
        createUser.setLayout(null);
        createUser.setBackground(Color.LIGHT_GRAY);
        createUser.setOpaque(true);
        //TODO

        //Last name

        JLabel lName = new JLabel();
        lName.setForeground(Color.DARK_GRAY);
        lName.setFont(new Font("Times New Roman", Font.BOLD, 12));
        lName.setText("Last Name: ");
        lName.setBounds(120, 300, 100,20);

        JTextField lastName = new JTextField();
        lastName.setBounds(225, 300, 100, 20);

        //First Name

        JLabel fName = new JLabel();
        fName.setForeground(Color.DARK_GRAY);
        fName.setFont(new Font("Times New Roman", Font.BOLD, 12));
        fName.setText("First Name: ");
        fName.setBounds(120, 325, 100, 20);

        JTextField firstName = new JTextField();
        firstName.setBounds(225,325, 100, 20);

        //Middle Name

        JLabel middleName = new JLabel();
        middleName.setForeground(Color.DARK_GRAY);
        middleName.setFont(new Font("Times New Roman", Font.BOLD, 12));
        middleName.setText("Middle Name: ");
        middleName.setBounds(120, 350, 100, 20);

        JTextField mName = new JTextField();
        mName.setBounds(225, 350, 100, 20);

        //Username

        JLabel uName = new JLabel();
        uName.setForeground(Color.DARK_GRAY);
        uName.setFont(new Font("Times New Roman", Font.BOLD, 12));
        uName.setText("Username: ");
        uName.setBounds(120, 375, 100, 20);

        JTextField userName = new JTextField();
        userName.setBounds(225, 375, 100, 20);


        //Password

        JLabel pass = new JLabel();
        pass.setForeground(Color.DARK_GRAY);
        pass.setFont(new Font("Times New Roman", Font.BOLD, 12));
        pass.setText("Password: ");
        pass.setBounds(120, 400, 100, 20);

        JTextField password = new JTextField();
        password.setBounds(225,400,100,20);


        //Confirm Password

        JLabel cpass = new JLabel();
        cpass.setForeground(Color.DARK_GRAY);
        cpass.setFont(new Font("Times New Roman", Font.BOLD, 12));
        cpass.setText("Confirm Password: ");
        cpass.setBounds(120, 425, 150,20);

        JTextField confirmPass = new JTextField();
        confirmPass.setBounds(225, 425,100,20);

        //Create Button

        JButton create = new JButton("Create");
        create.setBounds(500,470,100,30);

        //Reset Button

        JButton reset = new JButton("Reset");
        reset.setBounds(380,470,100,30);

        //Upload BUtton

        JButton uploadImg = new JButton("Upload Image");
        uploadImg.setBounds(150,220,150,50);

        //Admin Button

        JRadioButton adm = new JRadioButton("Admin");
        adm.setBackground(Color.LIGHT_GRAY);
        adm.setForeground(Color.DARK_GRAY);
        adm.setBounds(500, 310, 100,20);

        //Tenant Button

        JRadioButton ten = new JRadioButton("Tenant");
        ten.setBackground(Color.LIGHT_GRAY);
        ten.setForeground(Color.DARK_GRAY);
        ten.setBounds(500, 330,100,20);

        createUser.add(lName);
        createUser.add(lastName);
        createUser.add(fName);
        createUser.add(firstName);
        createUser.add(middleName);
        createUser.add(mName);
        createUser.add(uName);
        createUser.add(userName);
        createUser.add(pass);
        createUser.add(password);
        createUser.add(cpass);
        createUser.add(confirmPass);
        createUser.add(create);
        createUser.add(reset);
        createUser.add(uploadImg);
        createUser.add(adm);
        createUser.add(ten);

        contentPanel.add("createUser", createUser);
    }

    protected void content()
    {
        contentPanel = new JPanel();
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setOpaque(true);
        contentPanel.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        contentPanel.setLayout(contentCard);
        manageApartment();
        manageTenants();
        createBill();
        createUser();
    }

    private void logout()
    {
        DatabaseConnection dbCon = DatabaseConnection.getInstance();
        dbCon.close();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == navButtons[0])
        {
            for(JButton button : navButtons)
            {
                button.setBackground(Color.WHITE);
            }
            navButtons[0].setBackground(Color.LIGHT_GRAY);
            contentCard.show(contentPanel, "manageApartmentPanel");
        }
        if(e.getSource() == navButtons[1])
        {
            for(JButton button : navButtons)
            {
                button.setBackground(Color.WHITE);
            }
            navButtons[1].setBackground(Color.LIGHT_GRAY);
            contentCard.show(contentPanel, "manageTenantsPanel");
        }
        if(e.getSource() == navButtons[2])
        {
            for(JButton button : navButtons)
            {
                button.setBackground(Color.WHITE);
            }
            navButtons[2].setBackground(Color.LIGHT_GRAY);
            contentCard.show(contentPanel, "createBillPanel");
        }
        if(e.getSource() == navButtons[3])
        {
            for(JButton button : navButtons)
            {
                button.setBackground(Color.WHITE);
            }
            navButtons[3].setBackground(Color.LIGHT_GRAY);
            contentCard.show(contentPanel, "createUser");
        }
        if(e.getSource() == profileButton)
        {
            profileButton.setEnabled(false);
            new Profile(this);
        }
    }

    @Override
    public void windowOpened(WindowEvent e)
    {

    }

    @Override
    public void windowClosing(WindowEvent e)
    {
        logout();
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
