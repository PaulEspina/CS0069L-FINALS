import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TenantDetails extends JFrame implements ActionListener, WindowListener
{
    DatabaseConnection connection;

    Admin admin;

    int tenantID;
    int roomNumber;
    String username;
    String firstName;
    String middleName;
    String lastName;

    JLabel profilePicture;
    JLabel tenantIDValue;
    JLabel usernameValue;
    JLabel roomNumberValue;
    JLabel firstNameValue;
    JLabel middleNameValue;
    JLabel lastNameValue;
    JTextField newRoomField;
    JButton editRoomButton;
    JButton confirmEditButton;
    JButton paymentStatusButton;
    JButton closeButton;
    JButton removeButton;

    public TenantDetails(Admin admin, int tenantID)
    {
        this.admin = admin;
        this.tenantID = tenantID;

        connection = DatabaseConnection.getInstance();
        ResultSet resultSet = connection.getResult("SELECT * FROM users WHERE key='" + tenantID + "'");
        try
        {
            roomNumber = 0;
            int roomID = 0;
            if(resultSet.next())
            {
                username = resultSet.getString("username");
                firstName = resultSet.getString("first_name");
                middleName = resultSet.getString("middle_name");
                lastName = resultSet.getString("last_name");
            }
            resultSet.close();

            resultSet = connection.getResult("SELECT * FROM tenants WHERE key='" + tenantID + "'");
            if(resultSet.next())
            {
                roomID = resultSet.getInt("room");
            }
            resultSet.close();

            resultSet = connection.getResult("SELECT * FROM rooms WHERE key='" + roomID + "'");
            if(resultSet.next())
            {
                roomNumber = resultSet.getInt("room_number");
            }
            resultSet.close();
        }
        catch(SQLException throwables)
        {
            throwables.printStackTrace();
        }

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(500, 300);
        setLayout(null);
        setLocationRelativeTo(null);
        addWindowListener(this);
        setTitle("Tenant: " + username);

        profilePicture = new JLabel();
        profilePicture.setIcon(new ImageIcon(new ImageIcon("image/" + tenantID).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
        profilePicture.setVerticalAlignment(JLabel.TOP);
        profilePicture.setHorizontalAlignment(JLabel.CENTER);
        profilePicture.setHorizontalTextPosition(JLabel.CENTER);
        profilePicture.setVerticalTextPosition(JLabel.BOTTOM);
        profilePicture.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        profilePicture.setBounds(50, 25, 100, 100);

        JLabel tenantIDText = new JLabel("Tenant ID:");
        tenantIDText.setBounds(175, 35, 100, 25);
        JLabel usernameText = new JLabel("Username:");
        usernameText.setBounds(175, 60, 100, 25);
        JLabel roomNumberText = new JLabel("Room Number:");
        roomNumberText.setBounds(175, 85, 100, 25);
        JLabel firstNameText = new JLabel("First Name:");
        firstNameText.setBounds(50, 135, 100, 25);
        JLabel middleNameText = new JLabel("Middle Name:");
        middleNameText.setBounds(50, 160, 100, 25);
        JLabel lastNameText = new JLabel("Last Name:");
        lastNameText.setBounds(50, 185, 100, 25);

        tenantIDValue = new JLabel(String.valueOf(tenantID));
        tenantIDValue.setBounds(275, 35, 85, 25);
        usernameValue = new JLabel(username);
        usernameValue.setBounds(275, 60, 85, 25);
        roomNumberValue = new JLabel(roomNumber == 0 ? "Vacant" : String.valueOf(roomNumber));
        roomNumberValue.setBounds(275, 85, 80, 25);
        firstNameValue = new JLabel(firstName);
        firstNameValue.setBounds(150, 135, 150, 25);
        middleNameValue = new JLabel(middleName);
        middleNameValue.setBounds(150, 160, 150, 25);
        lastNameValue = new JLabel(lastName);
        lastNameValue.setBounds(150, 185, 150, 25);

        editRoomButton = new JButton("Edit");
        editRoomButton.setBackground(Color.WHITE);
        editRoomButton.setFont(new Font("Arial", Font.BOLD, 10));
        editRoomButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        editRoomButton.setFocusPainted(false);
        editRoomButton.setBounds(360, 85, 50, 20);
        editRoomButton.addActionListener(this);

        paymentStatusButton = new JButton("Payment Status");
        paymentStatusButton.setBackground(Color.WHITE);
        paymentStatusButton.setFont(new Font("Arial", Font.BOLD, 10));
        paymentStatusButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        paymentStatusButton.setFocusPainted(false);
        paymentStatusButton.setBounds(290, 225, 100, 25);
        paymentStatusButton.addActionListener(this);

        closeButton = new JButton("Close");
        closeButton.setBackground(Color.WHITE);
        closeButton.setFont(new Font("Arial", Font.BOLD, 10));
        closeButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        closeButton.setFocusPainted(false);
        closeButton.setBounds(400, 225, 50, 25);
        closeButton.addActionListener(this);

        newRoomField = new JTextField();
        newRoomField.setToolTipText("Enter new room for this tenant.");
        newRoomField.setBounds(275, 85, 80, 25);
        newRoomField.setVisible(false);

        confirmEditButton = new JButton("Confirm");
        confirmEditButton.setBackground(Color.WHITE);
        confirmEditButton.setFont(new Font("Arial", Font.BOLD, 10));
        confirmEditButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        confirmEditButton.setFocusPainted(false);
        confirmEditButton.setBounds(360, 85, 50, 20);
        confirmEditButton.addActionListener(this);
        confirmEditButton.setVisible(false);

        removeButton = new JButton("Remove");
        removeButton.setBackground(Color.WHITE);
        removeButton.setFont(new Font("Arial", Font.BOLD, 10));
        removeButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        removeButton.setFocusPainted(false);
        removeButton.setBounds(50, 225, 75, 25);
        removeButton.addActionListener(this);

        add(profilePicture);
        add(tenantIDText);
        add(usernameText);
        add(roomNumberText);
        add(firstNameText);
        add(middleNameText);
        add(lastNameText);
        add(tenantIDValue);
        add(usernameValue);
        add(roomNumberValue);
        add(firstNameValue);
        add(middleNameValue);
        add(lastNameValue);
        add(editRoomButton);
        add(paymentStatusButton);
        add(closeButton);
        add(newRoomField);
        add(editRoomButton);
        add(confirmEditButton);
        add(removeButton);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        // Edit Room Button
        if(e.getSource() == editRoomButton)
        {
            newRoomField.setVisible(true);
            confirmEditButton.setVisible(true);
            editRoomButton.setVisible(false);
            roomNumberValue.setVisible(false);
        }

        // Confirm Edit Button
        if(e.getSource() == confirmEditButton)
        {
            try
            {
                ResultSet resultSet = connection.getResult("SELECT * FROM tenants WHERE room='" + newRoomField.getText() + "'");
                boolean exists = resultSet.next();
                resultSet.close();

                if(!exists || Integer.parseInt(newRoomField.getText()) == 0)
                {
                    resultSet = connection.getResult("SELECT * FROM rooms WHERE room_number='" + newRoomField.getText() + "'");
                    exists = resultSet.next();
                    resultSet.close();
                    if(exists || Integer.parseInt(newRoomField.getText()) == 0)
                    {
                        connection.execute("UPDATE tenants SET room='" + newRoomField.getText() + "' WHERE key='" + tenantID + "'");
                        resultSet = connection.getResult("SELECT * FROM users WHERE key='" + tenantID + "'");
                        int roomID = 0;
                        roomNumber = 0;
                        if(resultSet.next())
                        {
                            username = resultSet.getString("username");
                            firstName = resultSet.getString("first_name");
                            middleName = resultSet.getString("middle_name");
                            lastName = resultSet.getString("last_name");
                        }
                        resultSet.close();

                        resultSet = connection.getResult("SELECT * FROM tenants WHERE key='" + tenantID + "'");
                        if(resultSet.next())
                        {
                            roomID = resultSet.getInt("room");
                        }
                        resultSet.close();

                        resultSet = connection.getResult("SELECT * FROM rooms WHERE key='" + roomID + "'");
                        if(resultSet.next())
                        {
                            roomNumber = resultSet.getInt("room_number");
                        }
                        resultSet.close();

                        usernameValue.setText(username);
                        roomNumberValue.setText(roomNumber == 0 ? "Vacant" : String.valueOf(roomNumber));
                        firstNameValue.setText(firstName);
                        middleNameValue.setText(middleName);
                        lastNameValue.setText(lastName);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "That room does not exist.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "That room is already occupied.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            catch(SQLException throwables)
            {
                throwables.printStackTrace();
            }
            newRoomField.setVisible(false);
            confirmEditButton.setVisible(false);
            editRoomButton.setVisible(true);
            roomNumberValue.setVisible(true);
        }

        // Payment Status Button
        if(e.getSource() == paymentStatusButton)
        {
            paymentStatusButton.setEnabled(false);
            closeButton.setEnabled(false);
            // create PaymentStatus Frame

        }

        // Remove Tenant Button
        if(e.getSource() == removeButton)
        {
            int x = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove this tenant?", "Confirm Remove" , JOptionPane.YES_NO_OPTION);
            if(x == JOptionPane.YES_OPTION)
            {
                connection.execute("DELETE FROM users WHERE key='" + tenantID + "'");
                connection.execute("DELETE FROM tenants WHERE key='" + tenantID + "'");
            }
        }

        // Close Button
        if(e.getSource() == closeButton)
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
        admin.refresh();
        admin.enableTenantSearchButton();
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
