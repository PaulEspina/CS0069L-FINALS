import javax.swing.*;
import java.awt.*;


public class TenantView {

    private Tenant tenant;
    private JFrame tenantPage;
    private JButton exit;
    private JButton logout;
    private JButton profile;
    private JLabel welcomemessage;
    private JPanel picturepanel;

    TenantView(Tenant tenant) {
        this.tenant = tenant;
    }

    public void start() {

        //Frame settings.
        tenantPage = new JFrame();
        tenantPage.setTitle("Hakdog draft quack quack");
        tenantPage.setResizable(false);
        tenantPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tenantPage.setSize(800, 600);
        tenantPage.setLayout(null);
        tenantPage.setVisible(true);

        tenantPage.setLocationRelativeTo(null);

        welcomemessage = new JLabel();
        welcomemessage.setBounds(190,35,200,22);
        welcomemessage.setText("BILLS");
        welcomemessage.setFont(new Font("Courier", Font.BOLD,25));

        //Setting for profile panel.
        picturepanel = new JPanel();
        picturepanel.setLayout(null);
        picturepanel.setBounds(0,0,160,600);
        picturepanel.setBackground(Color.lightGray);
        picturepanel.add(logout);
        picturepanel.add(exit);
        picturepanel.add(profile);

        //Profile button
        profile = new JButton();
        profile.setBounds(25,180,110,20);
        profile.setBackground(Color.WHITE);
        profile.setBorderPainted(true);
        profile.setContentAreaFilled(false);
        profile.setOpaque(true);
        profile.setFocusable(false);

        //Exit button
        exit = new JButton();
        exit.setBounds(5,530,55,20);
        exit.setBorderPainted(false);
        exit.setContentAreaFilled(false);
        exit.setOpaque(false);
        exit.setFocusable(false);

        //Logout button
        logout = new JButton();
        logout.setBounds(0,510,80,20);
        logout.setBorderPainted(false);
        logout.setContentAreaFilled(false);
        logout.setOpaque(false);
        logout.setFocusable(false);

        //Add things.
        tenantPage.add(picturepanel);
        tenantPage.add(welcomemessage);


    }
}


