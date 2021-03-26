import javafx.scene.chart.PieChart;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.time.LocalDate;

public class Admin extends User implements ActionListener, WindowListener
{
    DatabaseConnection connection;
    private JButton[] navButtons;
    private CardLayout contentCard;

    // Create Bill Components
    JTextField searchField;
    JButton searchButton;
    JLabel recipientPicture;
    JLabel recipientUsername;
    JLabel recipientFirstName;
    JLabel recipientLastName;
    JLabel recipientMiddleName;
    String dateString;
    JLabel recipientRoomNumber;
    JLabel recipientRoomFee;
    JTextField recipientMiscFee;
    JLabel recipientTotalFee;
    JButton createBillResetButton;
    JButton createBillButton;

    // Create User Components


    public Admin(int userID, String username, String firstName, String middleName, String lastName, String imagePath)
    {
        super(userID, username, firstName, middleName, lastName, imagePath);
        start();
    }

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

        JLabel recipientLabel = new JLabel();
        recipientLabel.setText("Recipient ID:");
        recipientLabel.setBounds(50, 25, 75, 25);

        searchField = new JTextField();
        searchField.setBounds(125, 25, 350, 25);
        searchField.setToolTipText("Enter tenant ID here.");

        searchButton = new JButton();
        searchButton.setText("SELECT");
        searchButton.setFont(new Font("Arial", Font.BOLD, 10));
        searchButton.setFocusPainted(false);
        searchButton.setBackground(Color.WHITE);
        searchButton.setBounds(480, 25, 75, 24);

        recipientPicture = new JLabel();
        recipientPicture.setIcon(new ImageIcon(new ImageIcon("default_pic.png").getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
        recipientPicture.setBounds(50, 100, recipientPicture.getIcon().getIconWidth(), recipientPicture.getIcon().getIconHeight());
        recipientPicture.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        recipientUsername = new JLabel("Username:");
        recipientUsername.setBounds(300, 100, 300, 25);
        recipientFirstName = new JLabel("First Name:");
        recipientFirstName.setBounds(300, 125, 300, 25);
        recipientMiddleName = new JLabel("Middle Name:");
        recipientMiddleName.setBounds(300, 150, 300, 25);
        recipientLastName = new JLabel("Last Name:");
        recipientLastName.setBounds(300, 175, 300, 25);

        LocalDate localDate = LocalDate.now();
        int month = localDate.getMonthValue();
        int day = localDate.getDayOfMonth();
        int year = localDate.getYear();
        dateString = String.format("%2d/%2d/%4d", month, day, year);
        JLabel date = new JLabel("Date Issued: " + dateString);
        date.setBounds(60, 325, 500, 25);

        recipientRoomNumber = new JLabel("Room Number:");
        recipientRoomNumber.setBounds(60, 350, 500, 25);
        recipientRoomFee = new JLabel("Room Rent Fee:");
        recipientRoomFee.setBounds(60, 375, 500, 25);
        JLabel miscFeeLabel = new JLabel("Miscellaneous Fee:");
        miscFeeLabel.setBounds(60, 400, 150, 25);
        recipientMiscFee = new JTextField();
        recipientMiscFee.setEnabled(false);
        recipientMiscFee.setToolTipText("Enter miscellaneous fees here.");
        recipientMiscFee.setBounds(175, 400, 385, 25);
        recipientTotalFee = new JLabel("Total Fee:");
        recipientTotalFee.setBounds(60, 425, 500, 25);

        createBillResetButton = new JButton("Reset");
        createBillResetButton.setFont(new Font("Arial", Font.BOLD, 10));
        createBillResetButton.setFocusPainted(false);
        createBillResetButton.setBackground(Color.WHITE);
        createBillResetButton.setBounds(475, 525, 75, 25);
        createBillResetButton.addActionListener(this);

        createBillButton = new JButton("Create");
        createBillButton.setFont(new Font("Arial", Font.BOLD, 10));
        createBillButton.setFocusPainted(false);
        createBillButton.setBackground(Color.WHITE);
        createBillButton.setBounds(560, 525, 75, 25);
        createBillButton.addActionListener(this);

        createBillPanel.add(recipientLabel);
        createBillPanel.add(searchField);
        createBillPanel.add(searchButton);
        createBillPanel.add(recipientPicture);
        createBillPanel.add(recipientUsername);
        createBillPanel.add(recipientFirstName);
        createBillPanel.add(recipientMiddleName);
        createBillPanel.add(recipientLastName);
        createBillPanel.add(date);
        createBillPanel.add(recipientRoomNumber);
        createBillPanel.add(recipientRoomFee);
        createBillPanel.add(miscFeeLabel);
        createBillPanel.add(recipientMiscFee);
        createBillPanel.add(recipientTotalFee);
        createBillPanel.add(createBillResetButton);
        createBillPanel.add(createBillButton);
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
