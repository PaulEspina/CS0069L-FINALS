import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageApartment extends JPanel implements ActionListener
{
    private final DatabaseConnection connection;
    private final Admin admin;
    public ManageApartment(Admin admin)
    {
        this.admin = admin;

        connection = DatabaseConnection.getInstance();

        setLayout(null);

        JLabel manageApartmentHeader = new JLabel("MANAGE APARTMENT");
        manageApartmentHeader.setBounds(50, 25, 400, 50);
        manageApartmentHeader.setFont(new Font("Arial", Font.BOLD, 32));
        //TODO: Manage Apartment

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

    }
}
