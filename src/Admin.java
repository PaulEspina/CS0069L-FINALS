import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class Admin extends User implements ActionListener, DocumentListener
{
    DatabaseConnection connection;
    private JButton[] navButtons;
    private CardLayout contentCard;

    // Create Bill Components
    JTextField recipientSearchField;
    JButton recipientSearchButton;
    JLabel recipientPicture;
    JLabel recipientUsername;
    JLabel recipientFirstName;
    JLabel recipientLastName;
    JLabel recipientMiddleName;
    JLabel recipientRoomNumber;
    JLabel recipientRoomFee;
    JTextField recipientMiscFee;
    JLabel recipientTotalFee;
    JButton createBillResetButton;
    JButton createBillButton;
    JLabel date;
    JLabel dbFirstName;
    JLabel dbMiddleName;
    JLabel dbLastName;
    JLabel dbUsername;
    JLabel dbRoomNumber;
    JLabel dbRoomFee;
    JLabel dbTotalFee;
    JLabel dbDate;
    // Create Bill Variables
    double rentFee = 0;
    double totalFee = 0;
    String dateString;

    // Create User Components
    JLabel newPicture;
    JButton uploadPictureButton;
    JTextField enterFirstName;
    JTextField enterMiddleName;
    JTextField enterLastName;
    JTextField enterUsername;
    JPasswordField enterPassword;
    JPasswordField enterConfirmPassword;
    JRadioButton tenantTypeButton;
    JRadioButton adminTypeButton;
    ButtonGroup accountTypeButton;
    JButton createUserResetButton;
    JButton createUserButton;
    // Create User Variables
    File image;

    // Manage Apartment Components
    DefaultTableModel defaultTableModeltt;
    JTextField tenantSearchField;
    JButton tenantSearchButton;

    public Admin(int userID, String username, String firstName, String middleName, String lastName)
    {
        super(userID, username, firstName, middleName, lastName);
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
        sidePanel.setPreferredSize(new Dimension(140, 600));
        sidePanel.setLayout(new GridLayout(3, 1));
        sidePanel.setBackground(Color.WHITE);
        sidePanel.setOpaque(true);

        // PROFILE PANEL STARTS
        JPanel profilePanel = new JPanel();
        profilePanel.setLayout(null);

        JLabel profileLabel = new JLabel();
        profileLabel.setFont(new Font("Arial", Font.BOLD, 10));
        profileLabel.setIcon(new ImageIcon(new ImageIcon("image/" + userID).getImage().getScaledInstance(130, 130, Image.SCALE_SMOOTH)));
        profileLabel.setVerticalAlignment(JLabel.TOP);
        profileLabel.setHorizontalAlignment(JLabel.CENTER);
        profileLabel.setHorizontalTextPosition(JLabel.CENTER);
        profileLabel.setVerticalTextPosition(JLabel.BOTTOM);
        profileLabel.setBounds(5, 5, 130, 130);

        profileButton = new JButton(firstName + " " + lastName);
        profileButton.setBackground(Color.WHITE);
        profileButton.setFont(new Font("Arial", Font.BOLD, 10));
        profileButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        profileButton.setFocusPainted(false);
        profileButton.setBounds(25, 140, 90, 20);
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
            button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 5));
            button.setFocusPainted(false);
            navigationPanel.add(button);
            button.addActionListener(this);
        }
        // NAVIGATION PANEL ENDS

        // LOGOUT PANEL STARTS
        JPanel logoutPanel = new JPanel();
        logoutPanel.setLayout(null);

        logoutButton = new JButton("Logout");
        logoutButton.setHorizontalAlignment(JButton.LEFT);
        logoutButton.setBounds(0,120,80,20);
        logoutButton.setBorderPainted(false);
        logoutButton.setContentAreaFilled(false);
        logoutButton.setOpaque(false);
        logoutButton.setFocusable(false);
        logoutButton.addActionListener(this);

        exitButton = new JButton("Exit");
        exitButton.setHorizontalAlignment(JButton.LEFT);
        exitButton.setBounds(0,150,80,20);
        exitButton.setBorderPainted(false);
        exitButton.setContentAreaFilled(false);
        exitButton.setOpaque(false);
        exitButton.setFocusable(false);
        exitButton.addActionListener(this);

        logoutPanel.add(logoutButton);
        logoutPanel.add(exitButton);
        // LOGOUT PANEL ENDS
        //TODO

        sidePanel.add(profilePanel);
        sidePanel.add(navigationPanel);
        sidePanel.add(logoutPanel);
    }

    private void manageApartment()
    {
        JButton continuousButton;
        JButton continuousButton1;
        JButton continuousButton2;
        JButton continuousButton3;
        JButton continuousButton4;
        JButton continuousButton5;
        JButton continuousButton6;
        JButton continuousButton7;
        JButton continuousButton8;
        JButton continuousButton9;
        JButton continuousButton10;
        JButton continuousButton11;
        JButton continuousButton12;
        JButton continuousButton13;
        JButton continuousButton14;
        JButton continuousButton15;
        JButton continuousButton16;
        JScrollPane panelScroll;
        JPanel panelButton;
        JPanel continuousPanel;

        JPanel manageApartmentPanel = new JPanel();
        manageApartmentPanel.setLayout(null);
        manageApartmentPanel.setOpaque(true);

        continuousButton = new JButton();
        continuousButton.setPreferredSize(new Dimension(100,100));
        continuousButton.setBackground(Color.LIGHT_GRAY);
        continuousButton.setOpaque(true);
        continuousButton.setFocusable(false);

        continuousButton1 = new JButton();
        continuousButton1.setPreferredSize(new Dimension(100,100));
        continuousButton1.setBackground(Color.LIGHT_GRAY);
        continuousButton1.setOpaque(true);
        continuousButton1.setFocusable(false);

        continuousButton2 = new JButton();
        continuousButton2.setPreferredSize(new Dimension(100,100));
        continuousButton2.setBackground(Color.LIGHT_GRAY);
        continuousButton2.setOpaque(true);
        continuousButton2.setFocusable(false);

        continuousButton3 = new JButton();
        continuousButton3.setPreferredSize(new Dimension(100,100));
        continuousButton3.setBackground(Color.LIGHT_GRAY);
        continuousButton3.setOpaque(true);
        continuousButton3.setFocusable(false);

        continuousButton4 = new JButton();
        continuousButton4.setPreferredSize(new Dimension(100,100));
        continuousButton4.setBackground(Color.LIGHT_GRAY);
        continuousButton4.setOpaque(true);
        continuousButton4.setFocusable(false);

        continuousButton5 = new JButton();
        continuousButton5.setPreferredSize(new Dimension(100,100));
        continuousButton5.setBackground(Color.LIGHT_GRAY);
        continuousButton5.setOpaque(true);
        continuousButton5.setFocusable(false);

        continuousButton6 = new JButton();
        continuousButton6.setPreferredSize(new Dimension(100,100));
        continuousButton6.setBackground(Color.LIGHT_GRAY);
        continuousButton6.setOpaque(true);
        continuousButton6.setFocusable(false);

        continuousButton7 = new JButton();
        continuousButton7.setPreferredSize(new Dimension(100,100));
        continuousButton7.setBackground(Color.LIGHT_GRAY);
        continuousButton7.setOpaque(true);
        continuousButton7.setFocusable(false);

        continuousButton8 = new JButton();
        continuousButton8.setPreferredSize(new Dimension(100,100));
        continuousButton8.setBackground(Color.LIGHT_GRAY);
        continuousButton8.setOpaque(true);
        continuousButton8.setFocusable(false);

        continuousButton9 = new JButton();
        continuousButton9.setPreferredSize(new Dimension(100,100));
        continuousButton9.setBackground(Color.LIGHT_GRAY);
        continuousButton9.setOpaque(true);
        continuousButton9.setFocusable(false);

        continuousButton10 = new JButton();
        continuousButton10.setPreferredSize(new Dimension(100,100));
        continuousButton10.setBackground(Color.LIGHT_GRAY);
        continuousButton10.setOpaque(true);
        continuousButton10.setFocusable(false);

        continuousButton11 = new JButton();
        continuousButton11.setPreferredSize(new Dimension(100,100));
        continuousButton11.setBackground(Color.LIGHT_GRAY);
        continuousButton11.setOpaque(true);
        continuousButton11.setFocusable(false);

        continuousButton12 = new JButton();
        continuousButton12.setPreferredSize(new Dimension(100,100));
        continuousButton12.setBackground(Color.LIGHT_GRAY);
        continuousButton12.setOpaque(true);
        continuousButton12.setFocusable(false);

        continuousButton13 = new JButton();
        continuousButton13.setPreferredSize(new Dimension(100,100));
        continuousButton13.setBackground(Color.LIGHT_GRAY);
        continuousButton13.setOpaque(true);
        continuousButton13.setFocusable(false);

        continuousButton14 = new JButton();
        continuousButton14.setPreferredSize(new Dimension(100,100));
        continuousButton14.setBackground(Color.LIGHT_GRAY);
        continuousButton14.setOpaque(true);
        continuousButton14.setFocusable(false);

        continuousButton15 = new JButton();
        continuousButton15.setPreferredSize(new Dimension(100,100));
        continuousButton15.setBackground(Color.LIGHT_GRAY);
        continuousButton15.setOpaque(true);
        continuousButton15.setFocusable(false);

        continuousButton16 = new JButton();
        continuousButton16.setPreferredSize(new Dimension(100,100));
        continuousButton16.setBackground(Color.LIGHT_GRAY);
        continuousButton16.setOpaque(true);
        continuousButton16.setFocusable(false);

        panelButton = new JPanel();
        panelButton.setLayout(new FlowLayout(FlowLayout.LEADING,25,25));
        panelButton.setBorder(BorderFactory.createEmptyBorder(20,20,20,1));
        panelButton.setBounds(0,100,550,Integer.MAX_VALUE);

        JLabel manageApartmentHeader = new JLabel("MANAGE APARTMENT");
        manageApartmentHeader.setBounds(50, 25, 400, 50);
        manageApartmentHeader.setFont(new Font("Arial", Font.BOLD, 32));

        panelScroll = new JScrollPane(panelButton);
        panelScroll.setBounds(620,70,15,485);
        panelButton.setBackground(Color.black);

        manageApartmentPanel.add(panelScroll);

        panelButton.add(continuousButton);
        panelButton.add(continuousButton1);
        panelButton.add(continuousButton2);
        panelButton.add(continuousButton3);
        panelButton.add(continuousButton4);
        panelButton.add(continuousButton5);
        panelButton.add(continuousButton6);
        panelButton.add(continuousButton7);
        panelButton.add(continuousButton8);
        panelButton.add(continuousButton9);
        panelButton.add(continuousButton10);
        panelButton.add(continuousButton11);
        panelButton.add(continuousButton12);
        panelButton.add(continuousButton13);
        panelButton.add(continuousButton14);
        panelButton.add(continuousButton15);
        panelButton.add(continuousButton16);

        manageApartmentPanel.add(panelButton);
        manageApartmentPanel.add(manageApartmentHeader);
        contentPanel.add("manageApartmentPanel", manageApartmentPanel);
    }

    private void manageTenants()
    {
        JPanel manageTenantsPanel = new JPanel();
        manageTenantsPanel.setLayout(null);
        manageTenantsPanel.setOpaque(true);

        JLabel manageTenantsHeader= new JLabel("MANAGE TENANT");
        manageTenantsHeader.setBounds(50, 25, 400, 50);
        manageTenantsHeader.setFont(new Font("Arial", Font.BOLD, 32));

        JLabel searchLabel = new JLabel("Search:");
        searchLabel.setBounds(50, 75, 75, 25);

        tenantSearchField = new JTextField();
        tenantSearchField.setBounds(125, 75, 350, 25);
        tenantSearchField.setToolTipText("Enter tenant ID here.");

        tenantSearchButton = new JButton("SELECT");
        tenantSearchButton.setFont(new Font("Arial", Font.BOLD, 10));
        tenantSearchButton.setFocusPainted(false);
        tenantSearchButton.setBackground(Color.WHITE);
        tenantSearchButton.setBounds(480, 75, 75, 24);
        tenantSearchButton.addActionListener(this);

        //Table Settings
        defaultTableModeltt = new DefaultTableModel()
        {
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
        defaultTableModeltt.setRowCount(0);
        defaultTableModeltt.addColumn("ID");
        defaultTableModeltt.addColumn("LAST NAME");
        defaultTableModeltt.addColumn("FIRST NAME");
        defaultTableModeltt.addColumn("MIDDLE NAME");
        defaultTableModeltt.addColumn("ROOM");

        DefaultTableCellRenderer defaultTableCellRenderer = new DefaultTableCellRenderer();
        defaultTableCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        JTable detailTable = new JTable(defaultTableModeltt);
        detailTable.getTableHeader().setResizingAllowed(false);
        detailTable.getTableHeader().setReorderingAllowed(false);
        detailTable.setOpaque(true);
        detailTable.setModel(defaultTableModeltt);
        detailTable.setBackground(Color.LIGHT_GRAY);
        detailTable.setBounds(20,115,585,Integer.MAX_VALUE);
        for(int i = 0; i < 5; i++)
        {
            detailTable.getColumnModel().getColumn(i).setCellRenderer(defaultTableCellRenderer);
        }

        JScrollPane panelScroll = new JScrollPane(detailTable);
        panelScroll.setBounds(26,115,585,400);
        panelScroll.setBackground(Color.LIGHT_GRAY);

        ResultSet resultSet = connection.getResult("SELECT * FROM users'" + "'");
        try
        {
            ArrayList<ArrayList<String>> users = new ArrayList<>();
            while(resultSet.next())
            {
                ArrayList<String> user = new ArrayList<>();
                user.add(String.valueOf(resultSet.getInt("key")));
                user.add(resultSet.getString("last_name"));
                user.add(resultSet.getString("first_name"));
                user.add(resultSet.getString("middle_name"));
                users.add(user);
            }
            resultSet.close();

            for(ArrayList<String> user : users)
            {
                int key = Integer.parseInt(user.get(0));
                if(key / 100000 == 2)
                {
                    int roomID = 0;
                    int tenantRoom = 0;
                    String lastName = user.get(1);
                    String firstName = user.get(2);
                    String middleName = user.get(3);

                    resultSet = connection.getResult("SELECT * FROM tenants WHERE key='" + key + "'");
                    if(resultSet.next())
                    {
                        roomID = resultSet.getInt("room");
                    }
                    resultSet.close();

                    resultSet = connection.getResult("SELECT * FROM rooms WHERE key='" + roomID + "'");
                    if(resultSet.next())
                    {
                        tenantRoom = resultSet.getInt("room_number");
                    }
                    resultSet.close();
                    defaultTableModeltt.addRow(new Object[]{key, lastName, firstName, middleName, tenantRoom});
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        manageTenantsPanel.add(panelScroll);
        manageTenantsPanel.add(searchLabel);
        manageTenantsPanel.add(tenantSearchField);
        manageTenantsPanel.add(tenantSearchButton);
        manageTenantsPanel.add(manageTenantsHeader);

        contentPanel.add("manageTenantsPanel", manageTenantsPanel);
    }

    private void createBill()
    {
        JPanel createBillPanel = new JPanel();
        createBillPanel.setLayout(null);

        JLabel createBillHeader = new JLabel("CREATE BILL");
        createBillHeader.setBounds(50, 25, 400, 50);
        createBillHeader.setFont(new Font("Arial", Font.BOLD, 32));

        JLabel recipientLabel = new JLabel("Recipient ID:");
        recipientLabel.setBounds(50, 75, 75, 25);

        recipientSearchField = new JTextField();
        recipientSearchField.setBounds(125, 75, 350, 25);
        recipientSearchField.setToolTipText("Enter tenant ID here.");

        recipientSearchButton = new JButton("SELECT");
        recipientSearchButton.setFont(new Font("Arial", Font.BOLD, 10));
        recipientSearchButton.setFocusPainted(false);
        recipientSearchButton.setBackground(Color.WHITE);
        recipientSearchButton.setBounds(480, 75, 75, 24);
        recipientSearchButton.addActionListener(this);

        //picture
        recipientPicture = new JLabel();
        recipientPicture.setIcon(new ImageIcon(new ImageIcon("image/default_pic.png").getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
        recipientPicture.setBounds(50, 140, recipientPicture.getIcon().getIconWidth(), recipientPicture.getIcon().getIconHeight());
        recipientPicture.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        recipientUsername = new JLabel("Username:");
        recipientUsername.setBounds(300, 140, 300, 25);
        recipientFirstName = new JLabel("First Name:");
        recipientFirstName.setBounds(300, 165, 300, 25);
        recipientMiddleName = new JLabel("Middle Name:");
        recipientMiddleName.setBounds(300, 190, 300, 25);
        recipientLastName = new JLabel("Last Name:");
        recipientLastName.setBounds(300, 215, 300, 25);

        dbUsername = new JLabel();
        dbUsername.setBounds(380,140,300,25);
        dbFirstName = new JLabel();
        dbFirstName.setBounds(380,165,300,25);
        dbMiddleName = new JLabel();
        dbMiddleName.setBounds(380, 190,300,25);
        dbLastName = new JLabel();
        dbLastName.setBounds(380,215,300,25);


        date = new JLabel("Date Issued: ");
        date.setBounds(60, 350, 500, 25);
        recipientRoomNumber = new JLabel("Room Number:");
        recipientRoomNumber.setBounds(60, 375, 500, 25);
        recipientRoomFee = new JLabel("Room Rent Fee:");
        recipientRoomFee.setBounds(60, 400, 500, 25);
        JLabel miscFeeLabel = new JLabel("Miscellaneous Fee:");
        miscFeeLabel.setBounds(60, 425, 150, 25);
        recipientMiscFee = new JTextField();
        recipientMiscFee.setToolTipText("Enter miscellaneous fees here.");
        recipientMiscFee.setEnabled(false);
        recipientMiscFee.setBounds(175, 425, 385, 25);
        recipientMiscFee.getDocument().addDocumentListener(this);
        recipientTotalFee = new JLabel("Total Fee:");
        recipientTotalFee.setBounds(60, 450, 500, 25);

        dbDate = new JLabel();
        dbDate.setBounds(172,350,500,25);
        dbRoomNumber = new JLabel();
        dbRoomNumber.setBounds(175,375,500,25);
        dbRoomFee = new JLabel();
        dbRoomFee.setBounds(175,400,500,25);
        dbTotalFee = new JLabel();
        dbTotalFee.setBounds(175,450,500,25);

        createBillResetButton = new JButton("Reset");
        createBillResetButton.setFont(new Font("Arial", Font.BOLD, 10));
        createBillResetButton.setFocusPainted(false);
        createBillResetButton.setBackground(Color.WHITE);
        createBillResetButton.setBounds(475, 525, 75, 25);
        createBillResetButton.setEnabled(false);
        createBillResetButton.addActionListener(this);

        createBillButton = new JButton("Create");
        createBillButton.setFont(new Font("Arial", Font.BOLD, 10));
        createBillButton.setFocusPainted(false);
        createBillButton.setBackground(Color.WHITE);
        createBillButton.setBounds(560, 525, 75, 25);
        createBillButton.setEnabled(false);
        createBillButton.addActionListener(this);

        createBillPanel.add(dbDate);
        createBillPanel.add(dbRoomNumber);
        createBillPanel.add(dbRoomFee);
        createBillPanel.add(dbTotalFee);
        createBillPanel.add(dbFirstName);
        createBillPanel.add(dbMiddleName);
        createBillPanel.add(dbLastName);
        createBillPanel.add(dbUsername);
        createBillPanel.add(createBillHeader);
        createBillPanel.add(recipientLabel);
        createBillPanel.add(recipientSearchField);
        createBillPanel.add(recipientSearchButton);
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

        JLabel createUserHeader = new JLabel("CREATE USER");
        createUserHeader.setBounds(50, 25, 400, 50);
        createUserHeader.setFont(new Font("Arial", Font.BOLD, 32));

        newPicture = new JLabel();
        newPicture.setIcon(new ImageIcon(new ImageIcon("image/default_pic.png").getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
        newPicture.setBounds(50, 100, newPicture.getIcon().getIconWidth(), newPicture.getIcon().getIconHeight());
        newPicture.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        uploadPictureButton = new JButton("Upload Image");
        uploadPictureButton.setFont(new Font("Arial", Font.BOLD, 9));
        uploadPictureButton.setFocusPainted(false);
        uploadPictureButton.setBackground(Color.WHITE);
        uploadPictureButton.setBounds(75, 260, 100, 24);
        uploadPictureButton.addActionListener(this);

        JLabel username = new JLabel("Username:");
        username.setBounds(250, 100, 200, 25);
        JLabel password = new JLabel("Password:");
        password.setBounds(250, 135, 200, 25);
        JLabel confirmPassword = new JLabel("Confirm Password:");
        confirmPassword.setBounds(250, 170, 200, 25);
        JLabel firstName = new JLabel("First Name:");
        firstName.setBounds(250, 205, 200, 25);
        JLabel middleName = new JLabel("Middle Name:");
        middleName.setBounds(250, 240, 200, 25);
        JLabel lastName = new JLabel("Last Name:");
        lastName.setBounds(250, 275, 200, 25);
        JLabel accountType = new JLabel("Account Type:");
        accountType.setBounds(250, 310, 200, 25);

        enterUsername = new JTextField();
        enterUsername.setBounds(375, 100, 200, 25);
        enterPassword = new JPasswordField();
        enterPassword.setBounds(375, 135, 200, 25);
        enterConfirmPassword = new JPasswordField();
        enterConfirmPassword.setBounds(375, 170, 200, 25);
        enterFirstName = new JTextField();
        enterFirstName.setBounds(375, 205, 200, 25);
        enterMiddleName = new JTextField();
        enterMiddleName.setBounds(375, 240, 200, 25);
        enterLastName = new JTextField();
        enterLastName.setBounds(375, 275, 200, 25);
        adminTypeButton = new JRadioButton("Admin");
        adminTypeButton.setBounds(375, 310, 100, 25);
        adminTypeButton.setFocusPainted(false);
        tenantTypeButton = new JRadioButton("Tenant");
        tenantTypeButton.setFocusPainted(false);
        tenantTypeButton.setBounds(475, 310, 100, 25);
        accountTypeButton = new ButtonGroup();
        accountTypeButton.add(adminTypeButton);
        accountTypeButton.add(tenantTypeButton);

        createUserResetButton = new JButton("Reset");
        createUserResetButton.setFont(new Font("Arial", Font.BOLD, 10));
        createUserResetButton.setFocusPainted(false);
        createUserResetButton.setBackground(Color.WHITE);
        createUserResetButton.setBounds(475, 525, 75, 25);
        createUserResetButton.addActionListener(this);

        createUserButton = new JButton("Create");
        createUserButton.setFont(new Font("Arial", Font.BOLD, 10));
        createUserButton.setFocusPainted(false);
        createUserButton.setBackground(Color.WHITE);
        createUserButton.setBounds(560, 525, 75, 25);
        createUserButton.addActionListener(this);

        createUser.add(createUserHeader);
        createUser.add(newPicture);
        createUser.add(uploadPictureButton);
        createUser.add(username);
        createUser.add(password);
        createUser.add(confirmPassword);
        createUser.add(firstName);
        createUser.add(middleName);
        createUser.add(lastName);
        createUser.add(accountType);
        createUser.add(enterUsername);
        createUser.add(enterPassword);
        createUser.add(enterConfirmPassword);
        createUser.add(enterFirstName);
        createUser.add(enterMiddleName);
        createUser.add(enterLastName);
        createUser.add(adminTypeButton);
        createUser.add(tenantTypeButton);
        createUser.add(createUserResetButton);
        createUser.add(createUserButton);
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

    @Override
    public void actionPerformed(ActionEvent e)
    {
        // Manage Apartment Button
        if(e.getSource() == navButtons[0])
        {
            for(JButton button : navButtons)
            {
                button.setBackground(Color.WHITE);
            }
            navButtons[0].setBackground(Color.LIGHT_GRAY);
            contentCard.show(contentPanel, "manageApartmentPanel");
        }

        // Manage Tenant Button
        if(e.getSource() == navButtons[1])
        {
            for(JButton button : navButtons)
            {
                button.setBackground(Color.WHITE);
            }
            navButtons[1].setBackground(Color.LIGHT_GRAY);
            contentCard.show(contentPanel, "manageTenantsPanel");
        }

        // Create Bill Button
        if(e.getSource() == navButtons[2])
        {
            for(JButton button : navButtons)
            {
                button.setBackground(Color.WHITE);
            }
            navButtons[2].setBackground(Color.LIGHT_GRAY);
            contentCard.show(contentPanel, "createBillPanel");
        }

        // Create User Button
        if(e.getSource() == navButtons[3])
        {
            for(JButton button : navButtons)
            {
                button.setBackground(Color.WHITE);
            }
            navButtons[3].setBackground(Color.LIGHT_GRAY);
            contentCard.show(contentPanel, "createUser");
        }

        // Show Profile Details Button
        if(e.getSource() == profileButton)
        {
            profileButton.setEnabled(false);
            new Profile(this);
        }

        // Search Recipient Button
        if(e.getSource() == recipientSearchButton)
        {
            try
            {
                int id = Integer.parseInt(recipientSearchField.getText());
                if(id / 100000 == 2)
                {
                    ResultSet resultSet = connection.getResult("SELECT * FROM users WHERE key='" + id + "'");
                    if(resultSet.next())
                    {
                        int roomID = 0;
                        int roomNumber;
                        double rentAmount;
                        String username = resultSet.getString("username");
                        String firstName = resultSet.getString("first_name");
                        String middleName = resultSet.getString("middle_name");
                        String lastName = resultSet.getString("last_name");
                        resultSet.close();

                        resultSet = connection.getResult("SELECT * FROM tenants WHERE key='" + id + "'");
                        if(resultSet.next())
                        {
                            roomID = resultSet.getInt("room");
                        }
                        resultSet.close();

                        resultSet = connection.getResult("SELECT * FROM rooms WHERE key='" + roomID + "'");
                        if(resultSet.next())
                        {
                            roomNumber = resultSet.getInt("room_number");
                            rentAmount = resultSet.getDouble("rent_amount");
                            LocalDate localDate = LocalDate.now();
                            int month = localDate.getMonthValue();
                            int day = localDate.getDayOfMonth();
                            int year = localDate.getYear();
                            dateString = String.format("%2d/%2d/%4d", month, day, year);
                            dbDate.setText(dateString);
                            dbUsername.setText(username);
                            dbFirstName.setText(firstName);
                            dbMiddleName.setText(middleName);
                            dbLastName.setText(lastName);
                            dbRoomNumber.setText(String.valueOf(roomNumber));
                            dbRoomFee.setText(String.valueOf(rentAmount));
                            recipientPicture.setIcon(new ImageIcon(new ImageIcon("image/" + id).getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
                            rentFee = rentAmount;
                            recipientSearchField.setEnabled(false);
                            recipientSearchButton.setEnabled(false);
                            recipientMiscFee.setEnabled(true);
                            createBillResetButton.setEnabled(true);
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "No room is assigned to this tenant. Please assign the tenant to a room first.", "Tenant does not have a room.", JOptionPane.ERROR_MESSAGE);
                        }
                        resultSet.close();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Please enter a valid recipient ID.", "Recipient not found.", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Please enter a valid recipient ID.", "Recipient is not a tenant.", JOptionPane.ERROR_MESSAGE);
                }
            }
            catch(NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(null, "Please enter a valid recipient ID.", "Invalid Input.", JOptionPane.ERROR_MESSAGE);
            }
            catch(SQLException ex)
            {
                ex.printStackTrace();
                System.out.println(ex.getMessage());
            }
        }

        // Create Bill Reset Button
        if(e.getSource() == createBillResetButton)
        {
            recipientPicture.setIcon(new ImageIcon(new ImageIcon("image/default_pic.png").getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
            dbDate.setText("");
            dbUsername.setText("");
            dbFirstName.setText("");
            dbMiddleName.setText("");
            dbLastName.setText("");
            dbRoomNumber.setText("");
            dbRoomFee.setText("");
            recipientMiscFee.setText("");
            dbTotalFee.setText("");
            recipientSearchField.setText("");
            rentFee = 0;
            recipientSearchField.setEnabled(true);
            recipientSearchButton.setEnabled(true);
            recipientMiscFee.setEnabled(false);
            createBillResetButton.setEnabled(false);
            createBillButton.setEnabled(false);
        }

        // Confirm Create Bill Button
        if(e.getSource() == createBillButton)
        {
            connection.execute("INSERT INTO bills(recipient_id, date_issued, total_amount, amount_paid) " +
                               "VALUES(" +
                               "'" + recipientSearchField.getText() + "'," +
                               "'" + dateString + "'," +
                               "'" + totalFee + "'," +
                               "'0'" +
                               ")");
            recipientPicture.setIcon(new ImageIcon(new ImageIcon("image/default_pic.png").getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
            dbDate.setText("");
            dbUsername.setText("");
            dbFirstName.setText("");
            dbMiddleName.setText("");
            dbLastName.setText("");
            dbRoomNumber.setText("");
            dbRoomFee.setText("");
            recipientMiscFee.setText("");
            dbTotalFee.setText("");
            recipientSearchField.setText("");
            rentFee = 0;
            recipientSearchField.setEnabled(true);
            recipientSearchButton.setEnabled(true);
            recipientMiscFee.setEnabled(false);
            createBillResetButton.setEnabled(false);
            createBillButton.setEnabled(false);
            JOptionPane.showMessageDialog(null, "The bill is successfully created!", "Bill Created", JOptionPane.INFORMATION_MESSAGE);
        }

        // Upload Picture Button
        if(e.getSource() == uploadPictureButton)
        {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes()));
            int i = fileChooser.showOpenDialog(null);
            if(i == JFileChooser.APPROVE_OPTION)
            {
                image = fileChooser.getSelectedFile();
                newPicture.setIcon(new ImageIcon(new ImageIcon(image.getPath()).getImage().getScaledInstance(newPicture.getWidth(), newPicture.getHeight(), Image.SCALE_SMOOTH)));
            }
        }

        // Create User Reset Button
        if(e.getSource() == createUserResetButton)
        {
            enterUsername.setText("");
            enterPassword.setText("");
            enterConfirmPassword.setText("");
            enterFirstName.setText("");
            enterMiddleName.setText("");
            enterLastName.setText("");
            accountTypeButton.clearSelection();
            newPicture.setIcon(new ImageIcon(new ImageIcon("default_pic.png").getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
            image = null;
        }

        // Confirm Create User Button
        if(e.getSource() == createUserButton)
        {
            if(image == null)
            {
                image = new File("image/default_pic.png");
            }
            if(!enterUsername.getText().isEmpty() &&
               !String.valueOf(enterPassword.getPassword()).isEmpty() &&
               !String.valueOf(enterPassword.getPassword()).isEmpty() &&
               !enterFirstName.getText().isEmpty() &&
               !enterMiddleName.getText().isEmpty() &&
               !enterLastName.getText().isEmpty())
            {
                if(String.valueOf(enterPassword.getPassword()).equals(String.valueOf(enterPassword.getPassword())))
                {
                    int id = 0;
                    if(adminTypeButton.isSelected())
                    {
                        id = 100000;
                    }
                    if(tenantTypeButton.isSelected())
                    {
                        id = 200000;
                    }
                    id += new Random().nextInt(100000);

                    File save = new File("image/" + id);
                    try
                    {
                        Files.copy(image.toPath(), save.toPath());
                        connection.execute("INSERT INTO users VALUES(" +
                                           "'" + id + "'," +
                                           "'" + enterUsername.getText() + "'," +
                                           "'" + String.valueOf(enterPassword.getPassword()) + "'," +
                                           "'" + enterFirstName.getText() + "'," +
                                           "'" + enterMiddleName.getText() + "'," +
                                           "'" + enterLastName.getText() + "'" +
                                           ")");
                        if(tenantTypeButton.isSelected())
                        {
                            connection.execute("INSERT INTO tenants(key) VALUES('" + id + "')");
                        }
                    }
                    catch(IOException ioException)
                    {
                        ioException.printStackTrace();
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "The password you entered did not match the the confirmation.", "Password Mismatch", JOptionPane.ERROR_MESSAGE);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Please fill all the fields.", "Insufficient Data", JOptionPane.ERROR_MESSAGE);
            }
        }

        // Tenant Search Button
        if(e.getSource() == tenantSearchButton)
        {
            ResultSet resultSet = connection.getResult("SELECT * FROM tenants WHERE key='" + tenantSearchField.getText() + "'");
            try
            {
                if(resultSet.next())
                {
                    new TenantDetails(Integer.parseInt(tenantSearchField.getText()));
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Please enter a valid tenant ID.", "Tenant Not Found", JOptionPane.ERROR_MESSAGE);
                }
            }
            catch(SQLException throwables)
            {
                throwables.printStackTrace();
            }
        }

        // Logout Button
        if(e.getSource() == logoutButton)
        {
            frame.dispose();
            new Login();
        }

        // Exit Button
        if(e.getSource() == exitButton)
        {
            connection.close();
            frame.dispose();
        }
    }

    public void changeTotalFee(DocumentEvent e)
    {
        totalFee = rentFee;
        try
        {
            totalFee += Double.parseDouble(recipientMiscFee.getText());
        }
        catch(NumberFormatException ex)
        {
            System.out.println("Miscellaneous Fee textfield's value is not a number.");
        }
        recipientTotalFee.setText("Total Fee: " + totalFee);
        createBillButton.setEnabled(true);
    }

    public void refresh()
    {
        connection = DatabaseConnection.getInstance();
        ResultSet resultSet = connection.getResult("SELECT * FROM users'" + "'");
        try
        {
            int key;
            int tenantRoom = 0;
            String lastName;
            String firstName;
            String middleName;

            defaultTableModeltt.setRowCount(0);

            while(resultSet.next())
            {

                key = resultSet.getInt("key");

                if(key / 100000 == 2)
                {
                    lastName = resultSet.getString("last_name");
                    firstName = resultSet.getString("first_name");
                    middleName = resultSet.getString("middle_name");

                    resultSet.close();

                    ResultSet rs = connection.getResult("SELECT * FROM tenants WHERE room ='" + key +"'");

                    if(rs.next())
                    {
                        int roomID = rs.getInt("room");
                        rs.close();

                        ResultSet roomSet = connection.getResult("SELECT * FROM rooms WHERE key='" + roomID + "'");
                        tenantRoom = roomSet.getInt("room_number");
                        roomSet.close();

                    }
                    defaultTableModeltt.addRow(new Object[]{key,lastName,firstName,middleName,tenantRoom});
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void insertUpdate(DocumentEvent e)
    {
        changeTotalFee(e);
    }

    @Override
    public void removeUpdate(DocumentEvent e)
    {
        changeTotalFee(e);
    }

    @Override
    public void changedUpdate(DocumentEvent e)
    {
    }
}
