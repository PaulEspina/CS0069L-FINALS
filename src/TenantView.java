import javax.swing.*;
import java.awt.*;


public class TenantView extends UserView {

    private Tenant tenant;
    private JFrame tenantPage;
    private JButton exit;
    private JButton logout;
    private JButton profile;
    private JLabel welcomemessage;
    private JLabel id;
    private JLabel date;
    private JLabel totalammount;
    private JLabel ammountpaid;
    private JLabel status;
    private JPanel picturepanel;
    private JPanel detailpanel;

    TenantView(Tenant tenant) {
        this.user = tenant;
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

        //BILL MESSAGE
        welcomemessage = new JLabel();
        welcomemessage.setBounds(190,25,200,22);
        welcomemessage.setText("BILLS");
        welcomemessage.setFont(new Font("Courier", Font.BOLD,25));

        //Details
        //detailpanel.setLayout(new GridLayout());
        id = new JLabel();
        id.setText("ID");
        id.setLayout(null);
        id.setBounds(40, 5, 20,20);
        id.setFont(new Font("Courier", Font.BOLD, 16));

        date = new JLabel();
        date.setText("Date");
        date.setLayout(null);
        date.setBounds(90, 5, 40,20);
        date.setFont(new Font("Courier", Font.BOLD, 16));

        totalammount = new JLabel();
        totalammount.setText("Total Amount");
        totalammount.setLayout(null);
        totalammount.setBounds(170, 5, 120,20);
        totalammount.setFont(new Font("Courier", Font.BOLD, 16));

        ammountpaid = new JLabel();
        ammountpaid.setText("Amount Paid");
        ammountpaid.setLayout(null);
        ammountpaid.setBounds(340, 5, 120,20);
        ammountpaid.setFont(new Font("Courier", Font.BOLD, 16));

        status = new JLabel();
        status.setText("Status");
        status.setLayout(null);
        status.setBounds(500, 5, 120,20);
        status.setFont(new Font("Courier", Font.BOLD, 16));

        //Setting for profile panel.
        picturepanel = new JPanel();
        picturepanel.setLayout(null);
        picturepanel.setBounds(0,0,160,600);
        picturepanel.setBackground(Color.lightGray);

        //Settings for detail panel.
        detailpanel = new JPanel();
        detailpanel.setLayout(null);
        detailpanel.setBounds(180,60,585,490);
        detailpanel.setBackground(Color.lightGray);

        //Profile button
        profile = new JButton();
        profile.setText("Username");
        profile.setBounds(25,180,110,20);
        profile.setBackground(Color.WHITE);
        profile.setBorderPainted(true);
        profile.setContentAreaFilled(false);
        profile.setOpaque(true);
        profile.setFocusable(false);

        //Exit button
        exit = new JButton();
        exit.setText("Exit");
        exit.setBounds(5,530,55,20);
        exit.setBorderPainted(false);
        exit.setContentAreaFilled(false);
        exit.setOpaque(false);
        exit.setFocusable(false);

        //Logout button
        logout = new JButton();
        logout.setText("Logout");
        logout.setBounds(0,510,80,20);
        logout.setBorderPainted(false);
        logout.setContentAreaFilled(false);
        logout.setOpaque(false);
        logout.setFocusable(false);

        //Add things.
        tenantPage.add(detailpanel);
        tenantPage.add(picturepanel);
        tenantPage.add(welcomemessage);
        picturepanel.add(logout);
        picturepanel.add(exit);
        picturepanel.add(profile);
        detailpanel.add(id);
        detailpanel.add(date);
        detailpanel.add(totalammount);
        detailpanel.add(ammountpaid);
        detailpanel.add(status);

    }

    public Tenant getModel()
    {
        return tenant;
    }

    public JFrame getFrame()
    {
        return tenantPage;
    }

    public JPanel getSidePanel()
    {
        return picturepanel;
    }

    public JPanel getContentPanel()
    {
        return detailpanel;
    }

    public JButton getProfileButton()
    {
        return profile;
    }
}


