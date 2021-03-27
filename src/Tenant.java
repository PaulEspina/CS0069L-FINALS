import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Tenant extends User implements ActionListener
{
    private JButton exit;
    private JButton logout;
    private JButton pay;
    private JTextField idText;
    private JScrollPane panelScroll;
    private JTable detailTable;

    private DefaultTableCellRenderer defaultTableCellRenderer;
    private DefaultTableModel defaultTableModeltt;

    DatabaseConnection connection;

    public Tenant(int userID, String username, String firstName, String middleName, String lastName)
    {
        super(userID, username, firstName, middleName, lastName);
        start();
    }

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
        defaultTableCellRenderer = new DefaultTableCellRenderer();
        defaultTableCellRenderer.setHorizontalAlignment(JLabel.CENTER);
        detailTable = new JTable(defaultTableModeltt);
        detailTable.getTableHeader().setResizingAllowed(false);
        detailTable.getTableHeader().setReorderingAllowed(false);
        detailTable.setOpaque(true);
        detailTable.setModel(defaultTableModeltt);
        detailTable.setBackground(Color.LIGHT_GRAY);
        detailTable.setBounds(0,0,585,Integer.MAX_VALUE);

        //BILL MESSAGE
        JLabel welcomeMessage = new JLabel();
        welcomeMessage.setBounds(190,25,200,22);
        welcomeMessage.setText("BILLS");
        welcomeMessage.setFont(new Font("Courier", Font.BOLD, 25));

        //Setting for profile panel.
        JPanel picturePanel = new JPanel();
        picturePanel.setLayout(null);
        picturePanel.setBounds(0,0,160,600);
        picturePanel.setBackground(Color.lightGray);

        //Settings for detail panel.
        JPanel detailPanel = new JPanel();
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
                int billID = rs.getInt("key");
                String dateIssue = rs.getString("date_issued");
                String totalAmount = String.valueOf(rs.getInt("total_amount"));
                String amountPaid = String.valueOf(rs.getInt("amount_paid"));

                String status;
                if(Double.parseDouble(amountPaid) >= Double.parseDouble(totalAmount))
                {
                    status = "Paid";
                }
                else
                {
                    status = "Unpaid";
                }
                defaultTableModeltt.addRow((new Object[]{billID,dateIssue,totalAmount,amountPaid,status}));
            }
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }

        for(int i = 0; i < 5; i++)
        {
            detailTable.getColumnModel().getColumn(i).setCellRenderer(defaultTableCellRenderer);
        }

        panelScroll = new JScrollPane(detailTable);
        panelScroll.setBounds(0,0,585,490);
        panelScroll.setBackground(Color.LIGHT_GRAY);

        //This is where ID input for pay bills.
        idText = new JTextField();
        idText.setBounds(590,36,100,20);

        //Enter ID for textfield
        JLabel enterID = new JLabel();
        enterID.setText("Please enter ID:");
        enterID.setFont(new Font("Courier",Font.PLAIN,14));
        enterID.setBounds(480,35, 200,20);

        //Pay Button
        pay = new JButton();
        pay.setText("Pay");
        pay.setBackground(Color.WHITE);
        pay.setFont(new Font("Courier", Font.PLAIN,14));
        pay.setBounds(702,35,60,20);
        pay.setFocusable(false);
        pay.setOpaque(true);
        pay.addActionListener(this);

        //Profile button
        profileButton = new JButton();
        profileButton.setText(username);
        profileButton.setBounds(25,180,110,20);
        profileButton.setBackground(Color.WHITE);
        profileButton.setBorderPainted(true);
        profileButton.setContentAreaFilled(false);
        profileButton.setOpaque(true);
        profileButton.setFocusable(false);
        profileButton.addActionListener(this);

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
        picturePanel.add(profileButton);

        frame.add(enterID);
        frame.add(idText);
        frame.add(pay);
        frame.add(detailPanel);
        frame.add(picturePanel);
        frame.add(welcomeMessage);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == profileButton)
        {
            new Profile(this);
        }
        if(e.getSource() == exit)
        {
            frame.dispose();
        }
        if(e.getSource() == logout)
        {
            frame.dispose();
            new Login();
        }
        if(e.getSource() == pay)
        {
            DatabaseConnection con = DatabaseConnection.getInstance();
            ResultSet rs = con.getResult("SELECT * FROM bills WHERE key='" + idText.getText() + "'");

            try
            {
                if(rs.next())
                {
                    if(rs.getInt("recipient_id") == userID)
                    {
                        if (rs.getDouble("amount_paid") >= rs.getDouble("total_amount"))
                        {
                            JOptionPane.showMessageDialog(null, "Your Bill is Already Paid!", "Warning", JOptionPane.WARNING_MESSAGE);
                        }
                        else
                        {
                            pay.setEnabled(false);
                            new PayBills(this, Integer.parseInt(idText.getText()));
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"No Existing Bill for this Tenant!","Warning",JOptionPane.WARNING_MESSAGE);
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"This Bill Doesn't Exist!","Error",JOptionPane.ERROR_MESSAGE);
                }
            }
            catch (SQLException f)
            {
                f.printStackTrace();
            }
        }
    }

    public void refresh()
    {
        connection = DatabaseConnection.getInstance();
        ResultSet rs = connection.getResult("SELECT * FROM bills WHERE recipient_id='" + userID + "'");

        try
        {
            defaultTableModeltt.setRowCount(0);

            while (rs.next())
            {
                int billID = rs.getInt("key");
                String dateIssue = rs.getString("date_issued");
                String totalAmount = String.valueOf(rs.getInt("total_amount"));
                String amountPaid = String.valueOf(rs.getInt("amount_paid"));

                String status;
                if(Double.parseDouble(amountPaid) >= Double.parseDouble(totalAmount))
                {
                    status = "Paid";
                }
                else
                {
                    status = "Unpaid";
                }
                defaultTableModeltt.addRow((new Object[]{billID,dateIssue,totalAmount,amountPaid,status}));
            }
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
    }

    public JButton getPay()
    {
        return pay;
    }
}
