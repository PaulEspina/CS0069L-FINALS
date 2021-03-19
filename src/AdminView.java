import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class AdminView extends UserView
{
    private Admin admin;
    private JFrame frame;
    private JPanel sidePanel;
    private JPanel contentPanel;
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
    private JButton profileButton;
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


    public AdminView(Admin admin)
    {
        this.admin = admin;
    }

    DatabaseConnection connection;
    public void start()
    {
        connection = DatabaseConnection.getInstance();
        contentCard = new CardLayout();
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setMinimumSize(new Dimension(800, 600));
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
        profileLabel.setIcon(new ImageIcon(new ImageIcon(admin.getImagePath()).getImage().getScaledInstance(frame.getWidth() / 8, frame.getWidth() / 8, Image.SCALE_SMOOTH)));
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

    protected void content()
    {
        contentPanel = new JPanel();
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setOpaque(true);
        contentPanel.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        contentPanel.setLayout(contentCard);

        JPanel manageApartmentPanel = new JPanel();
        manageApartmentPanel.setBackground(Color.RED);
        manageApartmentPanel.setOpaque(true);
        //TODO

        JPanel manageTenantsPanel = new JPanel();
        manageTenantsPanel.setBackground(Color.GREEN);
        manageTenantsPanel.setOpaque(true);
        //TODO

        JPanel createBillPanel = new JPanel();
        createBillPanel.setLayout(null);
        createBillPanel.setBackground(Color.LIGHT_GRAY);
        createBillPanel.setOpaque(true);
        //TODO

        //Seach Bar
        JTextField search = new JTextField();
        search.setBounds(250,50,300,30);



        //Username

        JLabel billUname = new JLabel();
        billUname.setForeground(Color.DARK_GRAY);
        billUname.setText("Username: ");
        billUname.setBounds(370,100,100,20);

        //Last Name

        JLabel billLname = new JLabel();
        billLname.setForeground(Color.DARK_GRAY);
        billLname.setText("Last Name: ");
        billLname.setBounds(370,120,100,20);

        //First Name

        JLabel billFname = new JLabel();
        billFname.setForeground(Color.DARK_GRAY);
        billFname.setText("First Name: ");
        billFname.setBounds(370,140,100,20);

        //Middle Name

        JLabel billMname = new JLabel();
        billMname.setForeground(Color.DARK_GRAY);
        billMname.setText("Middle Name: ");
        billMname.setBounds(370,160,100,20);



       /* ResultSet rs1 = connection.getResult("SELECT * FROM users");
        try {
            while (rs1.next()) {
                System.out.println("ID: " + rs1.getString(1));
                System.out.println("Username: " + rs1.getString(2));
                System.out.println("First Name: " + rs1.getString(4));
                System.out.println("Middle Name: " + rs1.getString(5));
                System.out.println("Last Name: " + rs1.getString(6));
                System.out.println("Image: " + rs1.getString(7));

                rs1.close();
            }
        }
        catch (SQLException e2){} */



        // Image

        icon = new JLabel();
        LocalDate date = LocalDate.now();

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

        JLabel billDate = new JLabel();
        billDate.setForeground(Color.DARK_GRAY);
        billDate.setFont(new Font("Times New Roman", Font.BOLD, 12));
        billDate.setText("Date: " + date);
        billDate.setBounds(100,250, 200, 50);


        //Room Number

        JLabel billNum = new JLabel();
        billNum.setForeground(Color.DARK_GRAY);
        billNum.setFont(new Font("Times New Roman", Font.BOLD, 12));
        billNum.setText("Room Number: ");
        billNum.setBounds(100,270, 200, 50);


        //Room Fee

        JLabel billFee = new JLabel();
        billFee.setForeground(Color.DARK_GRAY);
        billFee.setFont(new Font("Times New Roman", Font.BOLD, 12));
        billFee.setText("Room Rent Fee: ");
        billFee.setBounds(100,290, 200, 50);


        //Miscellaneous

        JLabel billMiscLabel = new JLabel();
        billMiscLabel.setForeground(Color.DARK_GRAY);
        billMiscLabel.setText("Miscellaneous Fee: ");
        billMiscLabel.setBounds(100, 310, 150, 50);


        JTextField billMisc = new JTextField();
        billMisc.setForeground(Color.DARK_GRAY);
        billMisc.setBounds(220,325,100, 20);
        billMisc.setFont(new Font("Times New Roman", Font.BOLD, 12));


        //Total Bill

        JLabel billTotal = new JLabel();
        billTotal.setForeground(Color.DARK_GRAY);
        billTotal.setFont(new Font("Times New Roman", Font.BOLD, 12));
        billTotal.setText("Total Fee: ");
        billTotal.setBounds(100,330, 200, 50);


        //Create Button

        JButton create1 = new JButton("Create");
        create1.setBounds(500,470,100,30);

        //Reset Button

        JButton reset1 = new JButton("Reset");
        reset1.setBounds(380,470,100,30);





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
        create.addActionListener((ActionListener) this);

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

        contentPanel.add("manageApartmentPanel", manageApartmentPanel);
        contentPanel.add("manageTenantsPanel", manageTenantsPanel);
        contentPanel.add("createBillPanel", createBillPanel);
        contentPanel.add("createUser", createUser);
    }

    public Admin getModel()
    {
        return admin;
    }

    public JFrame getFrame()
    {
        return frame;
    }

    public JPanel getSidePanel()
    {
        return sidePanel;
    }

    public JLabel getIcon()
    {
        return icon ;
    }

    public JPanel getContentPanel()
    {
        return contentPanel;
    }

    public JButton[] getNavButtons()
    {
        return navButtons;
    }

    public CardLayout getContentCard()
    {
        return contentCard;
    }

    public JButton getProfileButton()
    {
        return profileButton;
    }
}
