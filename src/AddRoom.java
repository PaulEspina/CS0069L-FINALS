import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddRoom extends JFrame implements ActionListener, WindowListener
{
    private final DatabaseConnection connection;
    private final JTextField roomNumberField;
    private final JTextField roomFeeField;
    private final JButton cancelButton;
    private final JButton confirmButton;

    ManageApartment manageApartment;
    public AddRoom(ManageApartment manageApartment)
    {
        this.manageApartment = manageApartment;
        connection = DatabaseConnection.getInstance();

        //frame
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(400,200);
        setLayout(null);
        setLocationRelativeTo(null);
        addWindowListener(this);
        setTitle("Room");
        setIconImage(new ImageIcon("icon48.png").getImage());

        //text
        JLabel roomNumberText = new JLabel("Room Number:");
        roomNumberText.setBounds(30,30,200,25);

        JLabel rentFeeText = new JLabel("Rent Fee:");
        rentFeeText.setBounds(30,60,200,25);

        //JTextField
        roomNumberField = new JTextField();
        roomNumberField.setBounds(120, 30, 200, 25);

        roomFeeField = new JTextField();
        roomFeeField.setBounds(120, 60, 200, 25);

        //confirm button
        confirmButton = new JButton("Confirm");
        confirmButton.setFocusable(false);
        confirmButton.setFont(new Font("Arial", Font.PLAIN, 10));
        confirmButton.setBounds(290, 135, 80, 20);
        confirmButton.setBackground(Color.WHITE);
        confirmButton.setOpaque(true);
        confirmButton.addActionListener(this);

        //close button
        cancelButton = new JButton("Cancel");
        cancelButton.setFocusable(false);
        cancelButton.setFont(new Font("Arial", Font.PLAIN, 10));
        cancelButton.setBounds(210, 135, 70, 20);
        cancelButton.setBackground(Color.WHITE);
        cancelButton.setOpaque(true);
        cancelButton.addActionListener(this);

        add(roomNumberText);
        add(rentFeeText);
        add(roomNumberField);
        add(roomFeeField);
        add(confirmButton);
        add(cancelButton);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == confirmButton)
        {
            try
            {
                int number = Integer.parseInt(roomNumberField.getText());
                double fee = Double.parseDouble(roomFeeField.getText());
                ResultSet resultSet = connection.getResult("SELECT * FROM rooms WHERE room_number='" + number + "'");
                if(!resultSet.next() && number != 0)
                {
                    connection.execute("INSERT INTO rooms(room_number, rent_amount) VALUES('" + number + "', '" + fee + "')");
                    dispose();
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "The room number you entered is invalid or already taken.", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
                resultSet.close();
            }
            catch(SQLException ex)
            {
                ex.printStackTrace();
            }
            catch(NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(null, "Invalid input.", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }

        if(e.getSource() == cancelButton)
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
        manageApartment.init();
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
