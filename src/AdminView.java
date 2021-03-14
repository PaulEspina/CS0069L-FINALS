import javax.swing.*;
import java.awt.*;

public class AdminView
{
    private Admin admin;
    private JFrame window;
    private JPanel sidePanel;
    private JPanel contentPanel;
    private JButton[] navButtons;
    CardLayout contentCard;
    JButton profileButton;

    public AdminView(Admin admin)
    {
        this.admin = admin;
        window = null;
        sidePanel = new JPanel();
        contentPanel = new JPanel();
        contentCard = new CardLayout();
    }

    public void start()
    {
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(800, 600);
        window.setMinimumSize(new Dimension(800, 600));
        window.setLocationRelativeTo(null);
        window.setTitle("Apartment Management System");
        header();
        content();
        window.add(sidePanel, BorderLayout.WEST);
        window.add(contentPanel, BorderLayout.CENTER);
        window.setVisible(true);
    }

    protected void header()
    {
        sidePanel.setPreferredSize(new Dimension(window.getWidth() / 6, window.getHeight()));
        sidePanel.setLayout(new GridLayout(3, 1));
        sidePanel.setBackground(Color.WHITE);
        sidePanel.setOpaque(true);

        // PROFILE PANEL STARTS
        JPanel profilePanel = new JPanel();
        profilePanel.setLayout(new FlowLayout());

        JLabel profileLabel = new JLabel();
        profileLabel.setFont(new Font("Arial", Font.BOLD, 10));
        profileLabel.setIcon(new ImageIcon(new ImageIcon(admin.getImagePath()).getImage().getScaledInstance(window.getWidth() / 8, window.getWidth() / 8, Image.SCALE_SMOOTH)));
        profileLabel.setVerticalAlignment(JLabel.TOP);
        profileLabel.setHorizontalAlignment(JLabel.CENTER);
        profileLabel.setHorizontalTextPosition(JLabel.CENTER);
        profileLabel.setVerticalTextPosition(JLabel.BOTTOM);

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
        createBillPanel.setBackground(Color.BLUE);
        createBillPanel.setOpaque(true);
        //TODO

        JPanel createUser = new JPanel();
        createUser.setBackground(Color.YELLOW);
        createUser.setOpaque(true);
        //TODO

        contentPanel.add("manageApartmentPanel", manageApartmentPanel);
        contentPanel.add("manageTenantsPanel", manageTenantsPanel);
        contentPanel.add("createBillPanel", createBillPanel);
        contentPanel.add("createUser", createUser);
    }

    public Admin getModel()
    {
        return admin;
    }

    public JFrame getWindow()
    {
        return window;
    }

    public JPanel getSidePanel()
    {
        return sidePanel;
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
