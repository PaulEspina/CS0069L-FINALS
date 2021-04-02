import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Tenant extends User implements ActionListener
{
    private final DatabaseConnection connection;

    private JButton payButton;
    private JTextField searchField;
    private DefaultTableModel defaultTableModeltt;

    public Tenant(int userID, String username, String firstName, String middleName, String lastName)
    {
        super(userID, username, firstName, middleName, lastName);
        connection = DatabaseConnection.getInstance();
        side();
        content();
        frame.add(sidePanel, BorderLayout.WEST);
        frame.add(contentPanel, BorderLayout.CENTER);
        frame.setVisible(true);
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

        //Profile button
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

        // NAVIGATION PANEL START
        JPanel navigationPanel = new JPanel(); // this is empty just so it is consistent with Admin's interface
        // NAVIGATION PANEL ENDS

        // LOGOUT PANEL STARTS
        //Exit button
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

        sidePanel.add(profilePanel);
        sidePanel.add(navigationPanel);
        sidePanel.add(logoutPanel);
    }

    @Override
    protected void content()
    {
        //Table Settings
        defaultTableModeltt = new DefaultTableModel()
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
        detailTable.setBounds(0, 0, 550, Integer.MAX_VALUE);

        for(int i = 0; i < 5; i++)
        {
            detailTable.getColumnModel().getColumn(i).setCellRenderer(defaultTableCellRenderer);
        }

        JScrollPane panelScroll = new JScrollPane(detailTable);
        panelScroll.setBounds(0, 0, 550, 475);

        //Settings for detail panel.
        JPanel detailPanel = new JPanel();
        detailPanel.setLayout(null);
        detailPanel.setBounds(50,55,580,475);
        detailPanel.add(panelScroll);

        //bills statement of account
        ResultSet resultSet = connection.getResult("SELECT * FROM bills WHERE recipient_id='" + userID + "'");
        try
        {
            while (resultSet.next())
            {
                int billID = resultSet.getInt("key");
                String dateIssue = resultSet.getString("date_issued");
                String totalAmount = String.valueOf(resultSet.getDouble("total_amount"));
                String amountPaid = String.valueOf(resultSet.getDouble("amount_paid"));
                String status = Double.parseDouble(amountPaid) >= Double.parseDouble(totalAmount) ? "Paid" : "Unpaid";
                defaultTableModeltt.addRow((new Object[]{billID,dateIssue,totalAmount,amountPaid,status}));
            }
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }

        //Enter ID for textfield
        JLabel enterID = new JLabel();
        enterID.setText("Bill ID:");
        enterID.setBounds(300,25, 75,25);

        //This is where ID input for pay bills.
        searchField = new JTextField();
        searchField.setBounds(375, 25, 150, 25);

        //Pay Button
        payButton = new JButton("Pay");
        payButton.setBackground(Color.WHITE);
        payButton.setFont(new Font("Arial", Font.BOLD, 10));
        payButton.setFocusPainted(false);
        payButton.setBounds(535, 25, 60, 24);
        payButton.addActionListener(this);

        contentPanel.add(enterID);
        contentPanel.add(searchField);
        contentPanel.add(payButton);
        contentPanel.add(detailPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        // Profile Button
        if(e.getSource() == profileButton)
        {
            new Profile(this);
        }

        // Exit Button
        if(e.getSource() == exitButton)
        {
            frame.dispose();
        }

        // Logout Button
        if(e.getSource() == logoutButton)
        {
            frame.dispose();
            new Login();
        }

        // Pay Button
        if(e.getSource() == payButton)
        {
            ResultSet resultSet = connection.getResult("SELECT * FROM bills WHERE key='" + searchField.getText() + "'");
            try
            {
                if(resultSet.next())
                {
                    if(resultSet.getInt("recipient_id") == userID)
                    {
                        if (resultSet.getDouble("amount_paid") >= resultSet.getDouble("total_amount"))
                        {
                            JOptionPane.showMessageDialog(null, "Your Bill is Already Paid!", "Warning", JOptionPane.WARNING_MESSAGE);
                            //TODO: disable pay button on PayBills instead.
                        }
                        else
                        {
                            payButton.setEnabled(false);
                            new PayBills(this, Integer.parseInt(searchField.getText()));
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
            searchField.setText("");
        }
    }

    @Override
    public void refresh()
    {
        defaultTableModeltt.setRowCount(0);
        ResultSet rs = connection.getResult("SELECT * FROM bills WHERE recipient_id='" + userID + "'");
        try
        {
            while (rs.next())
            {
                int billID = rs.getInt("key");
                String dateIssue = rs.getString("date_issued");
                String totalAmount = String.valueOf(rs.getDouble("total_amount"));
                String amountPaid = String.valueOf(rs.getDouble("amount_paid"));
                String status = Double.parseDouble(amountPaid) >= Double.parseDouble(totalAmount) ? "Paid" : "Unpaid";
                defaultTableModeltt.addRow((new Object[]{billID,dateIssue,totalAmount,amountPaid,status}));
            }
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
    }

    public void enablePayButton()
    {
        payButton.setEnabled(true);
    }
}
