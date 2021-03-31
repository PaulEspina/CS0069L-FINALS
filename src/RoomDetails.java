import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomDetails extends JFrame implements WindowListener, ActionListener
{
    private final DatabaseConnection connection;
    private final JLabel dbRoomNumber;
    private final JLabel dbRentFee;
    private final JLabel dbTenantID;
    private final JLabel dbTenantName;
    private final JButton editRoomNumber;
    private final JButton confirmRoomNumber;
    private final JButton editRentFee;
    private final JButton confirmRentFee;
    private final JButton editTenantID;
    private final JButton confirmTenantID;
    private final JTextField roomNumberField;
    private final JTextField roomFeeField;
    private final JTextField tenantField;
    private final JButton closeButton;
    private final JButton removeButton;

    ManageApartment manageApartment;
    Room room;
    public RoomDetails(ManageApartment manageApartment, Room room)
    {
        this.manageApartment = manageApartment;
        this.room = room;
        connection = DatabaseConnection.getInstance();

        //frame
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(500,300);
        setLayout(null);
        setLocationRelativeTo(null);
        addWindowListener(this);
        setTitle("Room " + room.roomNumber);
        setIconImage(new ImageIcon("icon48.png").getImage());

        int tenantID = 0;
        String tenantFirstName = "";
        String tenantLastName = "";
        try
        {
            ResultSet resultSet = connection.getResult("SELECT key FROM tenants WHERE room='" + room.key + "'");
            if(resultSet.next())
            {
                tenantID = resultSet.getInt("key");
            }
            resultSet.close();

            resultSet = connection.getResult("SELECT first_name, last_name FROM users WHERE key='" + tenantID +"'");
            if(resultSet.next())
            {
                tenantFirstName = resultSet.getString("first_name");
                tenantLastName = resultSet.getString("last_name");
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

        //text
        JLabel roomNumberText = new JLabel("Room Number:");
        roomNumberText.setBounds(30,30,200,25);

        JLabel rentFeeText = new JLabel("Rent Fee:");
        rentFeeText.setBounds(30,60,200,25);

        JLabel tenantIDText = new JLabel("TenantID:");
        tenantIDText.setBounds(30,120,200,25);

        JLabel tenantNameText = new JLabel("Tenant Name:");
        tenantNameText.setBounds(30,150,200,25);

        dbRoomNumber = new JLabel();
        dbRoomNumber.setText(String.valueOf(room.roomNumber));
        dbRoomNumber.setBounds(120,30,200,25);

        dbRentFee = new JLabel();
        dbRentFee.setText(String.valueOf(room.roomFee));
        dbRentFee.setBounds(120,60,200,25);

        dbTenantID = new JLabel();
        dbTenantID.setText(String.valueOf(tenantID));
        dbTenantID.setBounds(120,120,200,25);

        dbTenantName = new JLabel();
        dbTenantName.setText(tenantFirstName + " " + tenantLastName);
        dbTenantName.setBounds(120,150,200,25);

        //JTextField
        roomNumberField = new JTextField();
        roomNumberField.setBounds(120, 30, 200, 25);
        roomNumberField.setVisible(false);

        roomFeeField = new JTextField();
        roomFeeField.setBounds(120, 60, 200, 25);
        roomFeeField.setVisible(false);

        tenantField = new JTextField();
        tenantField.setBounds(120, 120, 200, 25);
        tenantField.setVisible(false);

        //edit button
        editRoomNumber = new JButton("Edit");
        editRoomNumber.setFocusable(false);
        editRoomNumber.setLayout(null);
        editRoomNumber.setFont(new Font("Arial",Font.PLAIN,10));
        editRoomNumber.setBounds(340,35,55,20);
        editRoomNumber.setBackground(Color.WHITE);
        editRoomNumber.setOpaque(true);
        editRoomNumber.addActionListener(this);

        editRentFee = new JButton("Edit");
        editRentFee.setFocusable(false);
        editRentFee.setLayout(null);
        editRentFee.setFont(new Font("Arial",Font.PLAIN, 10));
        editRentFee.setBounds(340,65,55,20);
        editRentFee.setBackground(Color.WHITE);
        editRentFee.setOpaque(true);
        editRentFee.addActionListener(this);

        editTenantID = new JButton("Edit");
        editTenantID.setFocusable(false);
        editTenantID.setLayout(null);
        editTenantID.setFont(new Font("Arial",Font.PLAIN, 10));
        editTenantID.setBounds(340,125,55,20);
        editTenantID.setBackground(Color.WHITE);
        editTenantID.setOpaque(true);
        editTenantID.addActionListener(this);

        //confirm button
        confirmRoomNumber = new JButton("Confirm");
        confirmRoomNumber.setVisible(false);
        confirmRoomNumber.setFocusable(false);
        confirmRoomNumber.setLayout(null);
        confirmRoomNumber.setFont(new Font("Arial",Font.PLAIN,10));
        confirmRoomNumber.setBounds(340,33,80,20);
        confirmRoomNumber.setBackground(Color.WHITE);
        confirmRoomNumber.setOpaque(true);
        confirmRoomNumber.addActionListener(this);

        confirmRentFee = new JButton("Confirm");
        confirmRentFee.setVisible(false);
        confirmRentFee.setFocusable(false);
        confirmRentFee.setLayout(null);
        confirmRentFee.setFont(new Font("Arial",Font.PLAIN,10));
        confirmRentFee.setBounds(340,63,80,20);
        confirmRentFee.setBackground(Color.WHITE);
        confirmRentFee.setOpaque(true);
        confirmRentFee.addActionListener(this);

        confirmTenantID = new JButton("Confirm");
        confirmTenantID.setVisible(false);
        confirmTenantID.setFocusable(false);
        confirmTenantID.setLayout(null);
        confirmTenantID.setFont(new Font("Arial",Font.PLAIN,10));
        confirmTenantID.setBounds(340,123,80,20);
        confirmTenantID.setBackground(Color.WHITE);
        confirmTenantID.setOpaque(true);
        confirmTenantID.addActionListener(this);

        //close button
        closeButton = new JButton("Close");
        closeButton.setFocusable(false);
        closeButton.setFont(new Font("Arial", Font.PLAIN, 10));
        closeButton.setBounds(410, 230, 60, 20);
        closeButton.setBackground(Color.WHITE);
        closeButton.setOpaque(true);
        closeButton.addActionListener(this);

        //remove button
        removeButton = new JButton("Remove");
        removeButton.setFocusable(false);
        removeButton.setFont(new Font("Arial", Font.PLAIN, 10));
        removeButton.setBounds(320, 230, 80, 20);
        removeButton.setBackground(Color.WHITE);
        removeButton.setOpaque(true);
        removeButton.addActionListener(this);

        setVisible(true);

        add(roomNumberText);
        add(rentFeeText);
        add(tenantIDText);
        add(tenantNameText);
        add(dbRoomNumber);
        add(dbRentFee);
        add(dbTenantID);
        add(dbTenantName);
        add(editRoomNumber);
        add(confirmRoomNumber);
        add(editRentFee);
        add(confirmRentFee);
        add(editTenantID);
        add(confirmTenantID);
        add(roomNumberField);
        add(roomFeeField);
        add(tenantField);
        add(closeButton);
        add(removeButton);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == editRoomNumber)
        {
            editRoomNumber.setVisible(false);
            confirmRoomNumber.setVisible(true);
            roomNumberField.setVisible(true);
            dbRoomNumber.setVisible(false);
        }

        if(e.getSource() == confirmRoomNumber)
        {
            if(!roomNumberField.getText().isEmpty())
            {
                try
                {
                    ResultSet resultSet = connection.getResult("SELECT * FROM rooms WHERE room_number='" + roomNumberField.getText() + "'");
                    if(!resultSet.next())
                    {
                        connection.execute("UPDATE rooms SET room_number='" + roomNumberField.getText() + "' WHERE key='" + room.key + "'");
                        dbRoomNumber.setText(roomNumberField.getText());
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "The room number you entered is already taken.", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                    resultSet.close();
                }
                catch(SQLException ex)
                {
                    ex.printStackTrace();
                }
            }
            dbRoomNumber.setVisible(true);
            editRoomNumber.setVisible(true);
            confirmRoomNumber.setVisible(false);
            roomNumberField.setVisible(false);
            roomNumberField.setText("");
        }

        if(e.getSource() == editRentFee)
        {
            editRentFee.setVisible(false);
            confirmRentFee.setVisible(true);
            roomFeeField.setVisible(true);
            dbRentFee.setVisible(false);
        }

        if(e.getSource() == confirmRentFee)
        {
            if(!roomFeeField.getText().isEmpty())
            {
                double amount;
                try
                {
                    amount = Double.parseDouble(roomFeeField.getText());
                    connection.execute("UPDATE rooms SET rent_amount='" + amount + "' WHERE key='" + room.key + "'");
                    dbRentFee.setText(roomFeeField.getText());
                }
                catch(NumberFormatException ex)
                {
                    JOptionPane.showMessageDialog(null, "Invalid input for rent. Please enter a number.", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
            dbRentFee.setVisible(true);
            editRentFee.setVisible(true);
            confirmRentFee.setVisible(false);
            roomFeeField.setVisible(false);
            roomFeeField.setText("");
        }

        if(e.getSource() == editTenantID)
        {
            editTenantID.setVisible(false);
            confirmTenantID.setVisible(true);
            tenantField.setVisible(true);
            dbTenantID.setVisible(false);
        }

        if(e.getSource() == confirmTenantID)
        {
            if(!tenantField.getText().isEmpty())
            {
                int tenantID = Integer.parseInt(tenantField.getText());
                try
                {
                    ResultSet resultSet = connection.getResult("SELECT * FROM users WHERE key='" + tenantID + "'");
                    if(resultSet.next() || tenantID == 0)
                    {
                        resultSet.close();
                        connection.execute("UPDATE tenants SET room='0' WHERE room='" + room.key + "'");
                        connection.execute("UPDATE tenants SET room='" + (tenantID == 0 ? "0" : room.key) + "' WHERE key='" + tenantID + "'");
                        dbTenantID.setText(String.valueOf(tenantID));
                        resultSet = connection.getResult("SELECT first_name, last_name FROM users WHERE key='" + tenantID + "'");
                        if(resultSet.next())
                        {
                            dbTenantName.setText(resultSet.getString("first_name") + " " + resultSet.getString("last_name"));
                        }
                        else if(tenantID == 0)
                        {
                            dbTenantName.setText("");
                        }
                        resultSet.close();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Tenant not found.", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }
                catch(NumberFormatException ex)
                {
                    JOptionPane.showMessageDialog(null, "Invalid input for tenant. Please enter a number.", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
                catch(SQLException ex)
                {
                    ex.printStackTrace();
                }
            }
            dbTenantID.setVisible(true);
            editTenantID.setVisible(true);
            confirmTenantID.setVisible(false);
            tenantField.setVisible(false);
        }

        if(e.getSource() == closeButton)
        {
            dispose();
        }

        if(e.getSource() == removeButton)
        {
            connection.execute("UPDATE tenants SET room='0' WHERE room='" + room.key + "'");
            connection.execute("DELETE FROM rooms WHERE key='" + room.key + "'");
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
        manageApartment.removeAll();
        manageApartment.revalidate();
        manageApartment.repaint();
        manageApartment.create();
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
}
