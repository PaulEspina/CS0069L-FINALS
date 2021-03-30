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
    private final JLabel dbRoomNumber = new JLabel();
    private final JLabel dbRentFee = new JLabel();
    private final JLabel dbTenantID = new JLabel();
    private final JLabel dbTenantName = new JLabel();
    private final JButton editRoomNumber = new JButton();
    private final JButton confirmRoomNumber = new JButton();
    private final JButton editRentFee = new JButton();
    private final JButton confirmRentFee = new JButton();
    private final JButton editTenantID = new JButton();
    private final JButton confirmTenantID = new JButton();
    private final JTextField roomNumberField = new JTextField();
    private final JTextField roomFeeField = new JTextField();
    private final JTextField tenantField = new JTextField();
    private final JButton close = new JButton("Close");

    ImageIcon logo = new ImageIcon(".\\images\\icon48.png");
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
        setTitle("Room");
        setIconImage(logo.getImage());

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

        dbRoomNumber.setText(String.valueOf(room.roomNumber));
        dbRoomNumber.setBounds(120,30,200,25);

        dbRentFee.setText(String.valueOf(room.roomFee));
        dbRentFee.setBounds(120,60,200,25);

        dbTenantID.setText(String.valueOf(tenantID));
        dbTenantID.setBounds(120,120,200,25);

        dbTenantName.setText(tenantFirstName + " " + tenantLastName);
        dbTenantName.setBounds(120,150,200,25);

        //JTextField
        roomNumberField.setBounds(120, 30, 200, 25);
        roomNumberField.setVisible(false);
        roomFeeField.setBounds(120, 60, 200, 25);
        roomFeeField.setVisible(false);
        tenantField.setBounds(120, 120, 200, 25);
        tenantField.setVisible(false);

        //edit button
        editRoomNumber.setText("Edit");
        editRoomNumber.setFocusable(false);
        editRoomNumber.setLayout(null);
        editRoomNumber.setFont(new Font("Courier",Font.PLAIN,10));
        editRoomNumber.setBounds(340,35,55,20);
        editRoomNumber.setBackground(Color.GRAY);
        editRoomNumber.setOpaque(true);
        editRoomNumber.addActionListener(this);

        editRentFee.setText("Edit");
        editRentFee.setFocusable(false);
        editRentFee.setLayout(null);
        editRentFee.setFont(new Font("Courier",Font.PLAIN, 10));
        editRentFee.setBounds(340,65,55,20);
        editRentFee.setBackground(Color.GRAY);
        editRentFee.setOpaque(true);
        editRentFee.addActionListener(this);

        editTenantID.setText("Edit");
        editTenantID.setFocusable(false);
        editTenantID.setLayout(null);
        editTenantID.setFont(new Font("Courier",Font.PLAIN, 10));
        editTenantID.setBounds(340,125,55,20);
        editTenantID.setBackground(Color.GRAY);
        editTenantID.setOpaque(true);
        editTenantID.addActionListener(this);

        //confirm button
        confirmRoomNumber.setVisible(false);
        confirmRoomNumber.setText("Confirm");
        confirmRoomNumber.setFocusable(false);
        confirmRoomNumber.setLayout(null);
        confirmRoomNumber.setFont(new Font("Courier",Font.PLAIN,10));
        confirmRoomNumber.setBounds(340,33,80,20);
        confirmRoomNumber.setBackground(Color.GRAY);
        confirmRoomNumber.setOpaque(true);
        confirmRoomNumber.addActionListener(this);

        confirmRentFee.setVisible(false);
        confirmRentFee.setText("Confirm");
        confirmRentFee.setFocusable(false);
        confirmRentFee.setLayout(null);
        confirmRentFee.setFont(new Font("Courier",Font.PLAIN,10));
        confirmRentFee.setBounds(340,63,80,20);
        confirmRentFee.setBackground(Color.GRAY);
        confirmRentFee.setOpaque(true);
        confirmRentFee.addActionListener(this);

        confirmTenantID.setVisible(false);
        confirmTenantID.setText("Confirm");
        confirmTenantID.setFocusable(false);
        confirmTenantID.setLayout(null);
        confirmTenantID.setFont(new Font("Courier",Font.PLAIN,10));
        confirmTenantID.setBounds(340,123,80,20);
        confirmTenantID.setBackground(Color.GRAY);
        confirmTenantID.setOpaque(true);
        confirmTenantID.addActionListener(this);

        //close button
        close.setText("Close");
        close.setFocusable(false);
        close.setFont(new Font("Courier", Font.PLAIN, 10));
        close.setBounds(410,230,60,20);
        close.setBackground(Color.GRAY);
        close.setOpaque(true);
        close.addActionListener(this);

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
        add(close);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == editRoomNumber)
        {
            editRoomNumber.setVisible(false);
            confirmRoomNumber.setVisible(true);
            roomNumberField.setVisible(true);
        }
        if(e.getSource() == confirmRoomNumber)
        {
            if(roomNumberField.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "ERROR: No character input.", "ERROR", JOptionPane.WARNING_MESSAGE);
            }
            else
            {
                connection.execute("UPDATE rooms SET room_number='" + roomNumberField.getText() + "' WHERE key='" + room.key + "'");
                editRoomNumber.setEnabled(true);
                editRoomNumber.setVisible(true);
                confirmRoomNumber.setVisible(false);
                roomNumberField.setVisible(false);
                dbRoomNumber.setText(roomNumberField.getText());
            }
        }
        if(e.getSource() == editRentFee)
        {
            editRentFee.setVisible(false);
            confirmRentFee.setVisible(true);
            roomFeeField.setVisible(true);
        }
        if(e.getSource() == confirmRentFee)
        {
            if (roomFeeField.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "ERROR: No character input.", "ERROR", JOptionPane.WARNING_MESSAGE);
            }
            else
            {
                connection.execute("UPDATE rooms SET rent_amount='" + roomFeeField.getText() + "' WHERE key='" + room.key + "'");
                editRentFee.setEnabled(true);
                editRentFee.setVisible(true);
                confirmRentFee.setVisible(false);
                roomFeeField.setVisible(false);
                dbRentFee.setText(roomFeeField.getText());
            }
        }
        if(e.getSource() == editTenantID)
        {
            editTenantID.setVisible(false);
            confirmTenantID.setVisible(true);
            tenantField.setVisible(true);
        }
        if(e.getSource() == confirmTenantID)
        {
            if(tenantField.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "ERROR: No character input.", "ERROR", JOptionPane.WARNING_MESSAGE);
            }
            else
            {
                connection.execute("UPDATE tenants SET room='0' WHERE room='" + room.key + "'");
                connection.execute("UPDATE tenants SET room='" + room.key + "' WHERE key='" + tenantField.getText() + "'");
                editTenantID.setEnabled(true);
                editTenantID.setVisible(true);
                confirmTenantID.setVisible(false);
                tenantField.setVisible(false);
                dbTenantID.setText(tenantField.getText());

                try
                {
                    ResultSet resultSet = connection.getResult("SELECT first_name, last_name FROM users WHERE key='" + tenantField.getText() + "'");
                    dbTenantName.setText(resultSet.getString("first_name") + " " + resultSet.getString("last_name"));
                    resultSet.close();
                }
                catch(SQLException ex)
                {
                    ex.printStackTrace();
                }
            }
        }
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
