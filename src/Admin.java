import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Admin extends User implements ActionListener
{
    private JButton[] navButtons;
    private final CardLayout contentCard;

    private ManageApartment manageApartment;
    private ManageTenants manageTenants;
    private CreateBill createBill;
    private CreateUser createUser;

    public Admin(int userID, String username, String firstName, String middleName, String lastName)
    {
        super(userID, username, firstName, middleName, lastName);
        contentCard = new CardLayout();

        side();
        content();
        frame.add(sidePanel, BorderLayout.WEST);
        frame.add(contentPanel, BorderLayout.CENTER);
        frame.setVisible(true);
        frame.setIconImage(new ImageIcon("icon48.png").getImage());
    }

    @Override
    protected void side()
    {
        // PROFILE PANEL STARTS
        JPanel profilePanel = new JPanel();
        profilePanel.setLayout(null);

        JLabel profileLabel = new JLabel();
        profileLabel.setBounds(5, 5, 130, 130);
        profileLabel.setIcon(new ImageIcon(new ImageIcon("image/" + getUserID()).getImage().getScaledInstance(profileLabel.getWidth(), profileLabel.getHeight(), Image.SCALE_SMOOTH)));
        profileLabel.setVerticalAlignment(JLabel.TOP);
        profileLabel.setHorizontalAlignment(JLabel.CENTER);
        profileLabel.setHorizontalTextPosition(JLabel.CENTER);
        profileLabel.setVerticalTextPosition(JLabel.BOTTOM);

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

    @Override
    protected void content()
    {
        contentPanel.setLayout(contentCard);
        manageApartment = new ManageApartment();
        manageTenants = new ManageTenants();
        createBill = new CreateBill(this);
        createUser = new CreateUser(this);
        contentPanel.add("manageApartmentPanel", manageApartment);
        contentPanel.add("manageTenantsPanel", manageTenants);
        contentPanel.add("createBillPanel", createBill);
        contentPanel.add("createUserPanel", createUser);
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
            manageTenants.refresh();
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
            contentCard.show(contentPanel, "createUserPanel");
        }

        // Show Profile Details Button
        if(e.getSource() == profileButton)
        {
            profileButton.setEnabled(false);
            new Profile(this);
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
            frame.dispose();
        }
    }

    public void refresh()
    {
        manageTenants.refresh();
    }
}
