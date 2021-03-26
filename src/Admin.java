import javafx.scene.chart.PieChart;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Admin extends User implements ActionListener, DocumentListener
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
    JLabel recipientRoomNumber;
    JLabel recipientRoomFee;
    JTextField recipientMiscFee;
    JLabel recipientTotalFee;
    JButton createBillResetButton;
    JButton createBillButton;
    JLabel date;
    //Create Bill Variables
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

        profileButton = new JButton(firstName + " " + lastName);
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
            button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 5));
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
        searchButton.addActionListener(this);

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


        date = new JLabel("Date Issued: ");
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
        recipientMiscFee.getDocument().addDocumentListener(this);
        recipientTotalFee = new JLabel("Total Fee:");
        recipientTotalFee.setBounds(60, 425, 500, 25);

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

        JLabel createUserHeader = new JLabel("CREATE USER");
        createUserHeader.setBounds(50, 25, 400, 50);
        createUserHeader.setFont(new Font("Arial", Font.BOLD, 32));

        newPicture = new JLabel();
        newPicture.setIcon(new ImageIcon(new ImageIcon("default_pic.png").getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
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
        createUserButton.setEnabled(false);
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
        if(e.getSource() == searchButton)
        {
            try
            {
                int id = Integer.parseInt(searchField.getText());
                if(id / 100000 == 2)
                {
                    ResultSet resultSet = connection.getResult("SELECT * FROM users WHERE key='" + id + "'");
                    if(!resultSet.next())
                    {
                        JOptionPane.showMessageDialog(null, "Please enter a valid recipient ID.", "Recipient not found.", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    String username = resultSet.getString("username");
                    String firstName = resultSet.getString("first_name");
                    String middleName = resultSet.getString("middle_name");
                    String lastName = resultSet.getString("last_name");
                    String imagePath = resultSet.getString("image");
                    resultSet.close();

                    resultSet = connection.getResult("SELECT * FROM tenants WHERE key='" + id + "'");
                    int roomID = resultSet.getInt("room");
                    resultSet.close();

                    resultSet = connection.getResult("SELECT * FROM rooms WHERE key='" + roomID + "'");
                    int roomNumber = resultSet.getInt("room_number");
                    double rentAmount = resultSet.getDouble("rent_amount");
                    if(!resultSet.next())
                    {
                        JOptionPane.showMessageDialog(null, "No room is assigned to this tenant. Please assign the tenant to a room first.", "Tenant does not have a room.", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    resultSet.close();

                    LocalDate localDate = LocalDate.now();
                    int month = localDate.getMonthValue();
                    int day = localDate.getDayOfMonth();
                    int year = localDate.getYear();
                    dateString = String.format("%2d/%2d/%4d", month, day, year);
                    date.setText("Date Issued: " + dateString);
                    recipientUsername.setText("Username: " + username);
                    recipientFirstName.setText("First Name: " + firstName);
                    recipientMiddleName.setText("Middle Name: " + middleName);
                    recipientLastName.setText("Last Name: " + lastName);
                    recipientRoomNumber.setText("Room Number: " + roomNumber);
                    recipientRoomFee.setText("Room Rent Fee: " + rentAmount);
                    recipientPicture.setIcon(new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
                    rentFee = rentAmount;
                    searchField.setEnabled(false);
                    searchButton.setEnabled(false);
                    recipientMiscFee.setEnabled(true);
                    createBillResetButton.setEnabled(true);
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
            recipientPicture.setIcon(new ImageIcon(new ImageIcon("default_pic.png").getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
            recipientUsername.setText("Username: ");
            recipientFirstName.setText("First Name: ");
            recipientMiddleName.setText("Middle Name: ");
            recipientLastName.setText("Last Name: ");
            recipientRoomNumber.setText("Room Number: ");
            recipientRoomFee.setText("Room Rent Fee: ");
            recipientMiscFee.setText("");
            recipientTotalFee.setText("");
            searchField.setText("");
            rentFee = 0;
            searchField.setEnabled(true);
            searchButton.setEnabled(true);
            recipientMiscFee.setEnabled(false);
            createBillResetButton.setEnabled(false);
            createBillButton.setEnabled(false);
        }

        // Confirm Create Bill Button
        if(e.getSource() == createBillButton)
        {
            connection.execute("INSERT INTO bills(recipient_id, date_issued, total_amount, amount_paid) " +
                               "VALUES(" +
                               "'" + searchField.getText() + "'," +
                               "'" + dateString + "'," +
                               "'" + totalFee + "'," +
                               "'0'" +
                               ")");
            recipientPicture.setIcon(new ImageIcon(new ImageIcon("default_pic.png").getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
            recipientUsername.setText("Username: ");
            recipientFirstName.setText("First Name: ");
            recipientMiddleName.setText("Middle Name: ");
            recipientLastName.setText("Last Name: ");
            recipientRoomNumber.setText("Room Number: ");
            recipientRoomFee.setText("Room Rent Fee: ");
            recipientMiscFee.setText("");
            recipientTotalFee.setText("");
            searchField.setText("");
            rentFee = 0;
            searchField.setEnabled(true);
            searchButton.setEnabled(true);
            recipientMiscFee.setEnabled(false);
            createBillResetButton.setEnabled(false);
            createBillButton.setEnabled(false);
            JOptionPane.showMessageDialog(null, "The bill is successfully created!", "Bill Created", JOptionPane.INFORMATION_MESSAGE);
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
            System.err.println("Miscellaneous Fee textfield's value is not a number.");
        }
        recipientTotalFee.setText("Total Fee: " + totalFee);
        createBillButton.setEnabled(true);
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
