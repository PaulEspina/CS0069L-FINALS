import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TenantView extends UserView{

    private final Tenant tenant = null;
    private JFrame tenantPage;
    private JButton exit;
    private JButton logout;
    private JButton profile;
    private JLabel welcomeMessage;
    private JLabel id;
    private JLabel date;
    private JLabel totalAmmount;
    private JLabel ammountPaid;
    private JLabel status;
    private JPanel picturePanel;
    private JPanel detailPanel;
    private JTable newpanel;
    private JScrollPane panelScroll;
    private JTable detailTable;

    DefaultTableCellRenderer defaultTableCellRenderer;
    DefaultTableModel defaultTableModeltt;

    DatabaseConnection connection;

    TenantView(Tenant tenant) {
        this.user = tenant;
    }

    public void start() {

        //Frame settings.
        tenantPage = new JFrame();
        tenantPage.setTitle("Apartment Management System");
        tenantPage.setResizable(false);
        tenantPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tenantPage.setSize(800, 600);
        tenantPage.setLayout(null);
        tenantPage.setVisible(true);

        tenantPage.setLocationRelativeTo(null);

        //Table Settings
        defaultTableModeltt = new DefaultTableModel();
        detailTable = new JTable(defaultTableModeltt);
        defaultTableCellRenderer = new DefaultTableCellRenderer();
        defaultTableCellRenderer.setHorizontalAlignment(JLabel.CENTER);
        detailTable.setOpaque(true);
        detailTable.setModel(defaultTableModeltt);
        detailTable.setBackground(Color.LIGHT_GRAY);
        detailTable.setBounds(0,0,585,Integer.MAX_VALUE);

        //BILL MESSAGE
        welcomeMessage = new JLabel();
        welcomeMessage.setBounds(190,25,200,22);
        welcomeMessage.setText("BILLS");
        welcomeMessage.setFont(new Font("Courier", Font.BOLD,25));

        //Setting for profile panel.
        picturePanel = new JPanel();
        picturePanel.setLayout(null);
        picturePanel.setBounds(0,0,160,600);
        picturePanel.setBackground(Color.lightGray);

        //Settings for detail panel.
        detailPanel = new JPanel();
        detailPanel.setLayout(null);
        detailPanel.setBounds(180,60,585,490);
        detailPanel.setBackground(Color.lightGray);

        //bills statement of account
        connection = DatabaseConnection.getInstance();
        ResultSet rs = connection.getResult("SELECT * FROM bills WHERE recipient_id='" + user.getUserID() + "'");
            try
            {
                defaultTableModeltt.setRowCount(0);
                defaultTableModeltt.addColumn("ID");
                defaultTableModeltt.addColumn("Date");
                defaultTableModeltt.addColumn("Total Amount");
                defaultTableModeltt.addColumn("Amount Paid");
                defaultTableModeltt.addColumn("Status");

                while (rs.next())
                {
                    int recipient_id = rs.getInt("recipient_id");
                    String dateIssue = rs.getString("date_issued");
                    String totalAmount = String.valueOf(rs.getInt("total_amount"));
                    String amountPaid = String.valueOf(rs.getInt("amount_paid"));

                    defaultTableModeltt.addRow((new Object[]{recipient_id,dateIssue,totalAmount,amountPaid}));
                }
            }
            catch (SQLException throwables)
            {
                throwables.printStackTrace();
            }

        for(int i = 0; i < 4; i++)
        {
            detailTable.getColumnModel().getColumn(i).setCellRenderer(defaultTableCellRenderer);
        }
        panelScroll = new JScrollPane(detailTable);
        panelScroll.setBounds(0,0,585,490);
        panelScroll.setBackground(Color.LIGHT_GRAY);

        //Profile button
        profile = new JButton();
        profile.setText(user.getUsername());
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

        detailPanel.add(panelScroll);

//        detailPanel.add(id);
//        detailPanel.add(date);
//        detailPanel.add(totalAmmount);
//        detailPanel.add(ammountPaid);
//        detailPanel.add(status);

        picturePanel.add(logout);
        picturePanel.add(exit);
        picturePanel.add(profile);

        tenantPage.add(detailPanel);
        tenantPage.add(picturePanel);
        tenantPage.add(welcomeMessage);
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
        return picturePanel;
    }

    public JPanel getContentPanel()
    {
        return detailPanel;
    }

    public JButton getProfileButton()
    {
        return profile;
    }

    public JButton getExit(){
        return exit;
    }

    public JButton getLogout() {
        return logout;
    }
}


