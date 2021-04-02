import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.Vector;

public class ManageApartment extends JPanel implements ActionListener
{
    private final DatabaseConnection connection;

    private JPanel roomPanel;
    private JScrollPane scrollPane;

    Vector<Room> rooms;
    Vector<JButton> roomButtons;
    JButton addButton;

    public ManageApartment()
    {
        connection = DatabaseConnection.getInstance();
        setLayout(null);
        init();
        roomPanel = new JPanel();
        scrollPane = new JScrollPane();
    }

    public void init()
    {
        rooms = new Vector<>();
        ResultSet resultSet = connection.getResult("SELECT * FROM rooms");
        try
        {
            while(resultSet.next())
            {
                Room room = new Room();
                room.key = resultSet.getInt("key");
                room.roomNumber = resultSet.getInt("room_number");
                room.roomFee = resultSet.getDouble("rent_amount");
                rooms.add(room);
            }
            resultSet.close();
        }
        catch(SQLException throwables)
        {
            throwables.printStackTrace();
        }
        rooms.sort(new RoomComparator());

        JLabel manageApartmentHeader = new JLabel("MANAGE APARTMENT");
        manageApartmentHeader.setBounds(50, 25, 400, 50);
        manageApartmentHeader.setFont(new Font("Arial", Font.BOLD, 32));
        //TODO: Manage Apartment

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5 ,5);

        roomPanel = new JPanel();
        roomPanel.setLayout(new GridBagLayout());
        roomPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        roomButtons = new Vector<>();
        for(int i = 0; i < rooms.size(); i++)
        {
            Room room = rooms.get(i);
            int tenantID = -1;
            String tenantFirstName = "", tenantLastName = "";

            try
            {
                resultSet = connection.getResult("SELECT key FROM tenants WHERE room='" + room.key + "'");
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
            catch(SQLException throwables)
            {
                throwables.printStackTrace();
            }

            gbc.gridy = i / 4;
            JButton roomButton = new JButton(String.valueOf(room.roomNumber));
            roomButton.setHorizontalTextPosition(JButton.CENTER);
            roomButton.setFont(new Font("Arial", Font.PLAIN, 24));
            roomButton.setPreferredSize(new Dimension(120,120));
            roomButton.setBackground(Color.LIGHT_GRAY);
            roomButton.setOpaque(true);
            roomButton.setFocusable(false);
            roomButton.setLayout(new BorderLayout());
            roomButton.addActionListener(this);

            JLabel roomTenant = new JLabel("Tenant: " + tenantFirstName + " " + tenantLastName);
            roomTenant.setHorizontalAlignment(JLabel.LEFT);
            roomTenant.setFont(new Font("Arial", Font.PLAIN, 9));

            roomButton.add(roomTenant, BorderLayout.NORTH);
            roomButtons.add(roomButton);
            roomPanel.add(roomButton, gbc);
        }

        gbc.gridy = rooms.size() / 4;
        addButton = new JButton("+");
        addButton.setHorizontalTextPosition(JButton.CENTER);
        addButton.setFont(new Font("Arial", Font.PLAIN, 32));
        addButton.setPreferredSize(new Dimension(120,120));
        addButton.setBackground(Color.LIGHT_GRAY);
        addButton.setOpaque(true);
        addButton.setFocusable(false);
        addButton.setLayout(new BorderLayout());
        addButton.addActionListener(this);
        roomPanel.add(addButton, gbc);

        scrollPane = new JScrollPane(roomPanel);
        scrollPane.setBounds(50, 100, 550, 450);
        scrollPane.setBackground(Color.LIGHT_GRAY);
        scrollPane.setVisible(true);

        add(manageApartmentHeader);
        add(scrollPane);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        for(int i = 0; i < roomButtons.size(); i++)
        {
            if(e.getSource() == roomButtons.get(i))
            {
                for(JButton button : roomButtons)
                {
                    button.setEnabled(false);
                }
                new RoomDetails(this, rooms.get(i));
            }
        }

        if(e.getSource() == addButton)
        {
            for(JButton button : roomButtons)
            {
                button.setEnabled(false);
            }
            new AddRoom(this);
        }
    }
}

class Room
{
    int key;
    int roomNumber;
    double roomFee;
}

class RoomComparator implements Comparator<Room>
{
    @Override
    public int compare(Room o1, Room o2)
    {
        return o1.roomNumber - o2.roomNumber;
    }
}
