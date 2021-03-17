import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

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
    private JButton[] navButtons;
    private CardLayout contentCard;
    private JButton profileButton;
    private JComboBox tenants;


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

        ResultSet rs1 = connection.execute("SELECT * FROM users");
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
        catch (SQLException e2){}




        icon = new JLabel();
        Date date = new Date();

        ResultSet resultSet = connection.getResult("SELECT image FROM users WHERE username = 'admin' ");
        String image_path = "";

        try {
            image_path = resultSet.getString("image");
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        icon.setIcon(new ImageIcon(image_path));


        JLabel billDate = new JLabel();
        billDate.setForeground(Color.DARK_GRAY);
        billDate.setFont(new Font("Times New Roman", Font.BOLD, 12));
        billDate.setText("Date: " + date);
        billDate.setBounds(100,250, 200, 50);
        billDate.setVisible(true);

        JLabel billNum = new JLabel();
        billNum.setForeground(Color.DARK_GRAY);
        billNum.setFont(new Font("Times New Roman", Font.BOLD, 12));
        billNum.setText("Room Number: ");
        billNum.setBounds(100,270, 200, 50);
        billNum.setVisible(true);

        JLabel billFee = new JLabel();
        billFee.setForeground(Color.DARK_GRAY);
        billFee.setFont(new Font("Times New Roman", Font.BOLD, 12));
        billFee.setText("Room Rent Fee: ");
        billFee.setBounds(100,290, 200, 50);
        billFee.setVisible(true);

        JLabel billMiscLabel = new JLabel();

        billMiscLabel.setForeground(Color.DARK_GRAY);
        billMiscLabel.setText("Miscellaneous Fee: ");
        billMiscLabel.setBounds(100, 310, 150, 50);
        billMiscLabel.setVisible(true);

        JTextField billMisc = new JTextField();
        billMisc.setForeground(Color.DARK_GRAY);
        billMisc.setBounds(220,325,100, 20);
        billMisc.setFont(new Font("Times New Roman", Font.BOLD, 12));
        billMisc.setEditable(true);

        JLabel billTotal = new JLabel();
        billTotal.setForeground(Color.DARK_GRAY);
        billTotal.setFont(new Font("Times New Roman", Font.BOLD, 12));
        billTotal.setText("Total Fee: ");
        billTotal.setBounds(100,330, 200, 50);
        billTotal.setVisible(true);





        JPanel createUser = new JPanel();
        createUser.setBackground(Color.YELLOW);
        createUser.setOpaque(true);
        //TODO
        createBillPanel.add(billTotal);
        createBillPanel.add(billMiscLabel);
        createBillPanel.add(billMisc);
        createBillPanel.add(billFee);
        createBillPanel.add(billNum);
        createBillPanel.add(billDate);
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

    public JLabel getIcon() { return icon ;}

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
