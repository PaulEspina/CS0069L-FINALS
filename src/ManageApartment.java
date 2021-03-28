import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageApartment extends JPanel implements ActionListener
{
    private final DatabaseConnection connection;
    private final Admin admin;

    private final JPanel roomPanel;
    private final JScrollPane scrollPane;

    public ManageApartment(Admin admin)
    {
        this.admin = admin;

        connection = DatabaseConnection.getInstance();

        setLayout(null);

        JLabel manageApartmentHeader = new JLabel("MANAGE APARTMENT");
        manageApartmentHeader.setBounds(50, 25, 400, 50);
        manageApartmentHeader.setFont(new Font("Arial", Font.BOLD, 32));
        //TODO: Manage Apartment

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5 ,5);

        roomPanel = new JPanel();
        roomPanel.setLayout(new GridBagLayout());
        roomPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        for(int i = 0; i < 25; i++)
        {
            gbc.gridx = i % 4;
            gbc.gridy = i / 4;
            JButton continuousButton = new JButton();
            continuousButton.setPreferredSize(new Dimension(120,120));
            continuousButton.setBackground(Color.LIGHT_GRAY);
            continuousButton.setOpaque(true);
            continuousButton.setFocusable(false);
            roomPanel.add(continuousButton, gbc);
        }

        scrollPane = new JScrollPane(roomPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(50, 100, 550, 450);
        scrollPane.setBackground(Color.LIGHT_GRAY);
        scrollPane.setVisible(true);

        add(manageApartmentHeader);
        add(scrollPane);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

    }
}
