import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class RoomDetails extends JFrame implements WindowListener, ActionListener
{
    private final JLabel roomNumber = new JLabel();
    private final JLabel rentFee = new JLabel();
    private final JLabel tenantID = new JLabel();
    private final JLabel tenantName = new JLabel();
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
    private final JButton editTenantName = new JButton();
    private final JButton confirmTenantName = new JButton();
    private final JTextField number = new JTextField();
    private final JTextField fee = new JTextField();
    private final JTextField id = new JTextField();
    private final JTextField name = new JTextField();
    private final JButton close = new JButton("Close");

//    ManageApartment apartment;
    User user;
    public RoomDetails(User user)
    {
        this.user = user;
//        this.apartment = apartment;
        DatabaseConnection connection = DatabaseConnection.getInstance();

        //frame
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(500,300);
        setLayout(null);
        setLocationRelativeTo(null);
        addWindowListener(this);
        setTitle("Room");

        //text
        roomNumber.setText("Room Number:");
        roomNumber.setBounds(30,30,200,25);
        dbRoomNumber.setBounds(120,30,200,25);
        rentFee.setText("Rent Fee:");
        rentFee.setBounds(30,60,200,25);
        dbRentFee.setBounds(120,60,200,25);
        tenantID.setText("TenantID:");
        tenantID.setBounds(30,120,200,25);
        dbTenantID.setBounds(120,120,200,25);
        tenantName.setText("Tenant Name:");
        tenantName.setBounds(30,150,200,25);
        dbTenantName.setBounds(120,150,200,25);

        //JTextField
        number.setBounds(120,30,200,25);
        number.setVisible(false);
        fee.setBounds(120,60,200,25);
        fee.setVisible(false);
        id.setBounds(120,120,200,25);
        id.setVisible(false);
        name.setBounds(120,150,200,25);
        name.setVisible(false);

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

        editTenantName.setText("Edit");
        editTenantName.setFocusable(false);
        editTenantName.setLayout(null);
        editTenantName.setFont(new Font("Courier",Font.PLAIN, 10));
        editTenantName.setBounds(340,155,55,20);
        editTenantName.setBackground(Color.GRAY);
        editTenantName.setOpaque(true);
        editTenantName.addActionListener(this);

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

        confirmTenantName.setVisible(false);
        confirmTenantName.setText("Confirm");
        confirmTenantName.setFocusable(false);
        confirmTenantName.setLayout(null);
        confirmTenantName.setFont(new Font("Courier",Font.PLAIN,10));
        confirmTenantName.setBounds(340,153,80,20);
        confirmTenantName.setBackground(Color.GRAY);
        confirmTenantName.setOpaque(true);
        confirmTenantName.addActionListener(this);

        //close button
        close.setText("Close");
        close.setFocusable(false);
        close.setFont(new Font("Courier", Font.PLAIN, 10));
        close.setBounds(410,230,60,20);
        close.setBackground(Color.GRAY);
        close.setOpaque(true);
        close.addActionListener(this);

        setVisible(true);

        add(roomNumber);
        add(rentFee);
        add(tenantID);
        add(tenantName);
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
        add(editTenantName);
        add(confirmTenantName);
        add(number);
        add(fee);
        add(id);
        add(name);
        add(close);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == editRoomNumber)
        {
            editRoomNumber.setVisible(false);
            confirmRoomNumber.setVisible(true);
            number.setVisible(true);
            roomNumber.setText("Room Number: ");
        }
        if(e.getSource() == confirmRoomNumber)
        {
            if(number.getText().length() < 1)
            {
                JOptionPane.showMessageDialog(null, "ERROR: No character input.", "ERROR", JOptionPane.WARNING_MESSAGE);
            }
            else
            {
                editRoomNumber.setEnabled(true);
                editRoomNumber.setVisible(true);
                confirmRoomNumber.setVisible(false);
                number.setVisible(false);
                dbRoomNumber.setText(number.getText());
                //roomNumber.setText("Room Number: " + number.getText());
            }
        }
        if(e.getSource() == editRentFee)
        {
            editRentFee.setVisible(false);
            confirmRentFee.setVisible(true);
            fee.setVisible(true);
            rentFee.setText("Rent Fee: ");
        }
        if(e.getSource() == confirmRentFee)
        {
            if (fee.getText().length() < 1)
            {
                JOptionPane.showMessageDialog(null, "ERROR: No character input.", "ERROR", JOptionPane.WARNING_MESSAGE);
            }
            else
            {
                editRentFee.setEnabled(true);
                editRentFee.setVisible(true);
                confirmRentFee.setVisible(false);
                fee.setVisible(false);
                dbRentFee.setText(fee.getText());
                //rentFee.setText("Rent Fee: " + fee.getText());
            }
        }
        if(e.getSource() == editTenantID)
        {
            editTenantID.setVisible(false);
            confirmTenantID.setVisible(true);
            id.setVisible(true);
            tenantID.setText("Tenant ID: ");
        }
        if(e.getSource() == confirmTenantID)
        {
            if(id.getText().length() < 1)
            {
                JOptionPane.showMessageDialog(null, "ERROR: No character input.", "ERROR", JOptionPane.WARNING_MESSAGE);
            }
            else
            {
                editTenantID.setEnabled(true);
                editTenantID.setVisible(true);
                confirmTenantID.setVisible(false);
                id.setVisible(false);
                dbTenantID.setText(id.getText());
                //tenantID.setText("Tenant ID: " + id.getText());
            }
        }
        if(e.getSource() == editTenantName)
        {
            editTenantName.setVisible(false);
            confirmTenantName.setVisible(true);
            name.setVisible(true);
            tenantName.setText("Tenant Name: ");
        }
        if(e.getSource() == confirmTenantName)
        {
            if(name.getText().length() < 1)
            {
                JOptionPane.showMessageDialog(null, "ERROR: No character input.", "ERROR", JOptionPane.WARNING_MESSAGE);
            }
            else
            {
                editTenantName.setEnabled(true);
                editTenantName.setVisible(true);
                confirmTenantName.setVisible(false);
                name.setVisible(false);
                dbTenantName.setText(name.getText());
                //tenantName.setText("Tenant Name: " + name.getText());
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
