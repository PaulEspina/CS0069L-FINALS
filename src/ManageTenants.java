import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ManageTenants extends JPanel implements ActionListener
{
    private final DatabaseConnection connection;

    private final DefaultTableModel defaultTableModeltt;
    private final JTextField tenantSearchField;
    private final JButton tenantSearchButton;

    public ManageTenants()
    {
        connection = DatabaseConnection.getInstance();

        setLayout(null);

        JLabel manageTenantsHeader= new JLabel("MANAGE TENANT");
        manageTenantsHeader.setBounds(50, 25, 400, 50);
        manageTenantsHeader.setFont(new Font("Arial", Font.BOLD, 32));

        JLabel searchLabel = new JLabel("Search:");
        searchLabel.setBounds(300, 75, 50, 25);

        tenantSearchField = new JTextField();
        tenantSearchField.setBounds(350, 75, 160, 25);
        tenantSearchField.setToolTipText("Enter tenant ID here.");

        tenantSearchButton = new JButton("SUBMIT");
        tenantSearchButton.setFont(new Font("Arial", Font.BOLD, 10));
        tenantSearchButton.setFocusPainted(false);
        tenantSearchButton.setBackground(Color.WHITE);
        tenantSearchButton.setBounds(520, 75, 75, 24);
        tenantSearchButton.addActionListener(this);

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
        defaultTableModeltt.addColumn("LAST NAME");
        defaultTableModeltt.addColumn("FIRST NAME");
        defaultTableModeltt.addColumn("MIDDLE NAME");
        defaultTableModeltt.addColumn("ROOM");

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
        detailTable.setBounds(0,0,550,Integer.MAX_VALUE);
        for(int i = 0; i < 5; i++)
        {
            detailTable.getColumnModel().getColumn(i).setCellRenderer(defaultTableCellRenderer);
        }

        JScrollPane panelScroll = new JScrollPane(detailTable);
        panelScroll.setBounds(50,110,550,425);
        panelScroll.setBackground(Color.LIGHT_GRAY);

        ResultSet resultSet = connection.getResult("SELECT * FROM users'" + "'");
        try
        {
            ArrayList<ArrayList<String>> users = new ArrayList<>();
            while(resultSet.next())
            {
                ArrayList<String> user = new ArrayList<>();
                user.add(String.valueOf(resultSet.getInt("key")));
                user.add(resultSet.getString("last_name"));
                user.add(resultSet.getString("first_name"));
                user.add(resultSet.getString("middle_name"));
                users.add(user);
            }
            resultSet.close();

            for(ArrayList<String> user : users)
            {
                int key = Integer.parseInt(user.get(0));
                if(key / 100000 == 2)
                {
                    int roomID = 0;
                    int tenantRoom = 0;
                    String lastName = user.get(1);
                    String firstName = user.get(2);
                    String middleName = user.get(3);

                    resultSet = connection.getResult("SELECT * FROM tenants WHERE key='" + key + "'");
                    if(resultSet.next())
                    {
                        roomID = resultSet.getInt("room");
                    }
                    resultSet.close();

                    resultSet = connection.getResult("SELECT * FROM rooms WHERE key='" + roomID + "'");
                    if(resultSet.next())
                    {
                        tenantRoom = resultSet.getInt("room_number");
                    }
                    resultSet.close();
                    defaultTableModeltt.addRow(new Object[]{key, lastName, firstName, middleName, tenantRoom == 0 ? "N/A" : tenantRoom});
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        add(panelScroll);
        add(searchLabel);
        add(tenantSearchField);
        add(tenantSearchButton);
        add(manageTenantsHeader);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        // Tenant Search Button
        if(e.getSource() == tenantSearchButton)
        {
            ResultSet resultSet = connection.getResult("SELECT * FROM tenants WHERE key='" + tenantSearchField.getText() + "'");
            try
            {
                if(resultSet.next())
                {
                    tenantSearchButton.setEnabled(false);
                    new TenantDetails(this, Integer.parseInt(tenantSearchField.getText()));
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Please enter a valid tenant ID.", "Tenant Not Found", JOptionPane.ERROR_MESSAGE);
                }
            }
            catch(SQLException throwables)
            {
                throwables.printStackTrace();
            }
        }
    }

    public void refresh()
    {
        ResultSet resultSet = connection.getResult("SELECT * FROM users'" + "'");
        try
        {
            defaultTableModeltt.setRowCount(0);
            ArrayList<ArrayList<String>> users = new ArrayList<>();
            while(resultSet.next())
            {
                ArrayList<String> user = new ArrayList<>();
                user.add(String.valueOf(resultSet.getInt("key")));
                user.add(resultSet.getString("last_name"));
                user.add(resultSet.getString("first_name"));
                user.add(resultSet.getString("middle_name"));
                users.add(user);
            }
            resultSet.close();

            for(ArrayList<String> user : users)
            {
                int key = Integer.parseInt(user.get(0));
                if(key / 100000 == 2)
                {
                    int roomID = 0;
                    int tenantRoom = 0;
                    String lastName = user.get(1);
                    String firstName = user.get(2);
                    String middleName = user.get(3);

                    resultSet = connection.getResult("SELECT * FROM tenants WHERE key='" + key + "'");
                    if(resultSet.next())
                    {
                        roomID = resultSet.getInt("room");
                    }
                    resultSet.close();

                    resultSet = connection.getResult("SELECT * FROM rooms WHERE key='" + roomID + "'");
                    if(resultSet.next())
                    {
                        tenantRoom = resultSet.getInt("room_number");
                    }
                    resultSet.close();
                    defaultTableModeltt.addRow(new Object[]{key, lastName, firstName, middleName, tenantRoom == 0 ? "N/A" : tenantRoom});
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void enableTenantSearchButton()
    {
        tenantSearchButton.setEnabled(true);
    }
}
