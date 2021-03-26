import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Tenant extends User implements ActionListener, WindowListener
{
    private Room room;

    public Tenant(int userID, String username, String firstName, String middleName, String lastName, String imagePath)
    {
        super(userID, username, firstName, middleName, lastName, imagePath);
        start();
    }

    private final Tenant tenant = null;
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

    private DefaultTableCellRenderer defaultTableCellRenderer;
    private DefaultTableModel defaultTableModeltt;

    DatabaseConnection connection;

    public void start() {

        //Frame settings.
        frame = new JFrame();
        frame.setTitle("Apartment Management System");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(null);
        frame.setVisible(true);

        frame.setLocationRelativeTo(null);

        //Table Settings
        defaultTableModeltt = new DefaultTableModel()
        {
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
        detailTable = new JTable(defaultTableModeltt);
        defaultTableCellRenderer = new DefaultTableCellRenderer();
        defaultTableCellRenderer.setHorizontalAlignment(JLabel.CENTER);
        detailTable.getTableHeader().setResizingAllowed(false);
        detailTable.getTableHeader().setReorderingAllowed(false);
        detailTable.setOpaque(true);
        detailTable.setModel(defaultTableModeltt);
        detailTable.setBackground(Color.LIGHT_GRAY);
        detailTable.setBounds(0,0,585,Integer.MAX_VALUE);

        //BILL MESSAGE
        welcomeMessage = new JLabel();
        welcomeMessage.setBounds(190,25,200,22);
        welcomeMessage.setText("BILLS");
        welcomeMessage.setFont(new Font("Courier", Font.BOLD, 25));

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
        ResultSet rs = connection.getResult("SELECT * FROM bills WHERE recipient_id='" + userID + "'");
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
        profile.setText(username);
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
        exit.addActionListener(this);

        //Logout button
        logout = new JButton();
        logout.setText("Logout");
        logout.setBounds(0,510,80,20);
        logout.setBorderPainted(false);
        logout.setContentAreaFilled(false);
        logout.setOpaque(false);
        logout.setFocusable(false);
        logout.addActionListener(this);

        //Add things.

        detailPanel.add(panelScroll);

        picturePanel.add(logout);
        picturePanel.add(exit);
        picturePanel.add(profile);

        frame.add(detailPanel);
        frame.add(picturePanel);
        frame.add(welcomeMessage);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == profileButton){
            new Profile(this);
        }
        if(e.getSource() == exit){
            frame.dispose();
        }
        if(e.getSource() == logout){
            frame.dispose();
            new Login();
        }
    }


    @Override
    public void windowOpened(WindowEvent e)
    {

    }

    @Override
    public void windowClosing(WindowEvent e)
    {
        connection.close();
    }

    @Override
    public void windowClosed(WindowEvent e)
    {
    }

    @Override
    public void windowIconified(WindowEvent e)
    {

    }

    @Override
    public void windowDeiconified(WindowEvent e)
    {

    }

    @Override
    public void windowActivated(WindowEvent e)
    {

    }

    @Override
    public void windowDeactivated(WindowEvent e)
    {

    }


    // Setters
    public void setRoom(Room room)
    {
        this.room = room;
    }

    // Getters
    public Room getRoom()
    {
        return room;
    }


    public JButton getExit(){
        return exit;
    }

    public JButton getLogout() {
        return logout;
    }
}
