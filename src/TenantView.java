import javax.swing.*;
import javax.swing.table.AbstractTableModel;
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
    private JScrollPane panelScroll;
    private JTable detailTable;
    DefaultTableModel dtt = new DefaultTableModel();

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
        detailTable = new JTable(dtt);
        detailTable.setOpaque(true);
        dtt = (DefaultTableModel) detailTable.getModel();
        detailTable.setBackground(Color.LIGHT_GRAY);
        detailTable.setBounds(0,30,585,490);

        //BILL MESSAGE
        welcomeMessage = new JLabel();
        welcomeMessage.setBounds(190,25,200,22);
        welcomeMessage.setText("BILLS");
        welcomeMessage.setFont(new Font("Courier", Font.BOLD,25));

        id = new JLabel();
        id.setText("ID");
        id.setLayout(null);
        id.setBounds(60, 5, 20,20);
        id.setFont(new Font("Courier", Font.BOLD, 16));

        date = new JLabel();
        date.setText("Date");
        date.setLayout(null);
        date.setBounds(160, 5, 40,20);
        date.setFont(new Font("Courier", Font.BOLD, 16));

        totalAmmount = new JLabel();
        totalAmmount.setText("Total Amount");
        totalAmmount.setLayout(null);
        totalAmmount.setBounds(240, 5, 120,20);
        totalAmmount.setFont(new Font("Courier", Font.BOLD, 16));

        ammountPaid = new JLabel();
        ammountPaid.setText("Amount Paid");
        ammountPaid.setLayout(null);
        ammountPaid.setBounds(360, 5, 120,20);
        ammountPaid.setFont(new Font("Courier", Font.BOLD, 16));

        status = new JLabel();
        status.setText("Status");
        status.setLayout(null);
        status.setBounds(500, 5, 120,20);
        status.setFont(new Font("Courier", Font.BOLD, 16));

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
                while (rs.next())
                {
                    int key = rs.getInt("key");
                    int recipient_id = rs.getInt("recipient_id");
                    String dateIssue = rs.getString("date_issued");
                    String totalAmount = String.valueOf(rs.getInt("total_amount"));
                    String amountPaid = String.valueOf(rs.getInt("amount_paid"));

//                    detailTable.getModel().setValueAt(id,detailTable.getSelectedRow(), 0);
//                    detailTable.getModel().setValueAt(date,detailTable.getSelectedRow(), 0);
//                    detailTable.getModel().setValueAt(totalAmount,detailTable.getSelectedRow(), 0);
//                    detailTable.getModel().setValueAt(amountPaid,detailTable.getSelectedRow(), 0);
                    dtt.setRowCount(6);
                    dtt.addRow((new Object[]{recipient_id,dateIssue,totalAmount,amountPaid}));
                    dtt.addColumn(recipient_id);
                    dtt.addColumn(dateIssue);
                    dtt.addColumn(totalAmount);
                    dtt.addColumn(amountPaid);
                    dtt.addColumn(status);

                    break;
                }
            }
            catch (SQLException throwables)
            {
                throwables.printStackTrace();
            }

        //Scroll Settings
        panelScroll = new JScrollPane(detailTable,JScrollPane.VERTICAL_SCROLLBAR_NEVER,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

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
        tenantPage.add(detailPanel);
        tenantPage.add(picturePanel);
        tenantPage.add(welcomeMessage);
        picturePanel.add(logout);
        picturePanel.add(exit);
        picturePanel.add(profile);
        detailPanel.add(panelScroll);
        detailPanel.add(detailTable);

        detailPanel.add(id);
        detailPanel.add(date);
        detailPanel.add(totalAmmount);
        detailPanel.add(ammountPaid);
        detailPanel.add(status);
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


