import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Admin extends User
{
    private JButton[] buttons;

    public Admin(int userID, String username, String firstName, String middleName, String lastName)
    {
        super(userID, username, firstName, middleName, lastName);
    }

    @Override
    protected void header()
    {
        sidePanel = new JPanel();
        sidePanel.setPreferredSize(new Dimension(window.getWidth() / 6, window.getHeight()));
        sidePanel.setLayout(new GridLayout(3, 1));
        sidePanel.setBackground(Color.WHITE);
        sidePanel.setOpaque(true);

        // PROFILE PANEL STARTS
        JPanel profilePanel = new JPanel();
        //TODO

        // PROFILE PANEL ENDS

        // NAVIGATION PANEL STARTS
        JPanel navigationPanel = new JPanel();
        navigationPanel.setLayout(new GridLayout(4, 1));

        buttons = new JButton[4];
        buttons[0] = new JButton("Manage Apartment");
        buttons[1] = new JButton("Manage Tenants");
        buttons[2] = new JButton("Create Bill");
        buttons[3] = new JButton("Create User");
        for(JButton button : buttons)
        {
            button.setBackground(Color.WHITE);
            button.setFont(new Font("Arial", Font.BOLD, 10));
            button.setHorizontalAlignment(JButton.LEFT);
            button.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            button.setFocusPainted(false);
            button.addActionListener(this);
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

    @Override
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

        JPanel manageTenantsPanel = new JPanel();
        manageTenantsPanel.setBackground(Color.GREEN);
        manageTenantsPanel.setOpaque(true);

        JPanel createBillPanel = new JPanel();
        createBillPanel.setBackground(Color.BLUE);
        createBillPanel.setOpaque(true);

        JPanel createUser = new JPanel();
        createUser.setBackground(Color.YELLOW);
        createUser.setOpaque(true);

        contentPanel.add("manageApartmentPanel", manageApartmentPanel);
        contentPanel.add("manageTenantsPanel", manageTenantsPanel);
        contentPanel.add("createBillPanel", createBillPanel);
        contentPanel.add("createUser", createUser);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == buttons[0])
        {
            for(JButton button : buttons)
            {
                button.setBackground(Color.WHITE);
            }
            buttons[0].setBackground(Color.LIGHT_GRAY);
            contentCard.show(contentPanel, "manageApartmentPanel");
        }
        if(e.getSource() == buttons[1])
        {
            for(JButton button : buttons)
            {
                button.setBackground(Color.WHITE);
            }
            buttons[1].setBackground(Color.LIGHT_GRAY);
            contentCard.show(contentPanel, "manageTenantsPanel");
        }
        if(e.getSource() == buttons[2])
        {
            for(JButton button : buttons)
            {
                button.setBackground(Color.WHITE);
            }
            buttons[2].setBackground(Color.LIGHT_GRAY);
            contentCard.show(contentPanel, "createBillPanel");
        }
        if(e.getSource() == buttons[3])
        {
            for(JButton button : buttons)
            {
                button.setBackground(Color.WHITE);
            }
            buttons[3].setBackground(Color.LIGHT_GRAY);
            contentCard.show(contentPanel, "createUser");
        }
    }
}
