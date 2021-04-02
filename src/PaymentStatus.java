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

public class PaymentStatus extends JFrame implements ActionListener, WindowListener {

    private final JButton close;
    private final TenantDetails tenantDetails;

    PaymentStatus(TenantDetails tenantDetails, int tenantID)
    {
        this.tenantDetails = tenantDetails;

        DatabaseConnection connection = DatabaseConnection.getInstance();

        //Frame Settings
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(500, 300);
        setLayout(null);
        addWindowListener(this);
        setLocationRelativeTo(null);
        setTitle("Payment Status");

        //Label Bill
        JLabel bills = new JLabel();
        bills.setText("BILLS");
        bills.setFont(new Font("Arial", Font.BOLD, 20));
        bills.setBounds(30, 5, 75, 75);

        //close Button
        close = new JButton("Close");
        close.setBackground(Color.WHITE);
        close.setFont(new Font("Arial", Font.BOLD, 10));
        close.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        close.setFocusPainted(false);
        close.setBounds(400, 240, 50, 25);
        close.addActionListener(this);

        //Table Settings
        DefaultTableModel defaultTableModeltt = new DefaultTableModel()
        {
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
        defaultTableModeltt.setRowCount(0);
        defaultTableModeltt.addColumn("ID");
        defaultTableModeltt.addColumn("Date");
        defaultTableModeltt.addColumn("Total Amount");
        defaultTableModeltt.addColumn("Amount Paid");
        defaultTableModeltt.addColumn("Status");

        DefaultTableCellRenderer defaultTableCellRenderer = new DefaultTableCellRenderer();
        defaultTableCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        JTable detailTable = new JTable(defaultTableModeltt);
        detailTable.getTableHeader().setResizingAllowed(false);
        detailTable.getTableHeader().setReorderingAllowed(false);
        detailTable.setDragEnabled(false);
        detailTable.setCellSelectionEnabled(false);
        detailTable.setFocusable(false);
        detailTable.setOpaque(true);
        detailTable.setModel(defaultTableModeltt);
        detailTable.setBounds(0, 0, 200, Integer.MAX_VALUE);

        for(int i = 0; i < 5; i++)
        {
            detailTable.getColumnModel().getColumn(i).setCellRenderer(defaultTableCellRenderer);
        }

        JScrollPane panelScroll = new JScrollPane(detailTable);
        panelScroll.setBounds(0, 0, 435, 180);

        //Settings for detail panel.
        JPanel detailPanel = new JPanel();
        detailPanel.setLayout(null);
        detailPanel.setBounds(30, 55, 580, 475);
        detailPanel.add(panelScroll);

        //bills statement of account
        ResultSet resultSet = connection.getResult("SELECT * FROM bills WHERE recipient_id='" + tenantID + "'");
        try
        {
            while(resultSet.next())
            {
                int billID = resultSet.getInt("key");
                String dateIssue = resultSet.getString("date_issued");
                String totalAmount = String.valueOf(resultSet.getDouble("total_amount"));
                String amountPaid = String.valueOf(resultSet.getDouble("amount_paid"));
                String status = Double.parseDouble(amountPaid) >= Double.parseDouble(totalAmount) ? "Paid" : "Unpaid";

                defaultTableModeltt.addRow((new Object[]{billID, dateIssue, totalAmount, amountPaid, status}));
            }
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }

        add(bills);
        add(detailPanel);
        add(close);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == close)
        {
            dispose();
        }
    }

    @Override
    public void windowOpened(WindowEvent e)
    {

    }

    @Override
    public void windowClosing(WindowEvent e)
    {

    }

    @Override
    public void windowClosed(WindowEvent e)
    {
        tenantDetails.enablePaymentStatus();
    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
