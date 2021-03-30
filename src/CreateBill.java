import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class CreateBill extends JPanel implements ActionListener, DocumentListener
{
    DatabaseConnection connection;
    public final Admin admin;

    private final JTextField recipientSearchField;
    private final JButton recipientSearchButton;
    private final JLabel recipientPicture;
    private final JTextField recipientMiscFee;
    private final JLabel recipientTotalFee;
    private final JButton createBillResetButton;
    private final JButton createBillButton;
    private final JLabel dbFirstName;
    private final JLabel dbMiddleName;
    private final JLabel dbLastName;
    private final JLabel dbUsername;
    private final JLabel dbRoomNumber;
    private final JLabel dbRoomFee;
    private final JLabel dbTotalFee;
    private final JLabel dbDate;
    private double rentFee = 0;
    private double totalFee = 0;
    private String dateString;

    public CreateBill(Admin admin)
    {
        this.admin = admin;

        connection = DatabaseConnection.getInstance();

        setLayout(null);

        JLabel createBillHeader = new JLabel("CREATE BILL");
        createBillHeader.setBounds(50, 25, 400, 50);
        createBillHeader.setFont(new Font("Arial", Font.BOLD, 32));

        JLabel recipientLabel = new JLabel("Recipient ID:");
        recipientLabel.setBounds(50, 75, 75, 25);

        recipientSearchField = new JTextField();
        recipientSearchField.setBounds(125, 75, 350, 25);
        recipientSearchField.setToolTipText("Enter tenant ID here.");

        recipientSearchButton = new JButton("SUBMIT");
        recipientSearchButton.setFont(new Font("Arial", Font.BOLD, 10));
        recipientSearchButton.setFocusPainted(false);
        recipientSearchButton.setBackground(Color.WHITE);
        recipientSearchButton.setBounds(480, 75, 75, 24);
        recipientSearchButton.addActionListener(this);

        //picture
        recipientPicture = new JLabel();
        recipientPicture.setIcon(new ImageIcon(new ImageIcon("default_pic.png").getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
        recipientPicture.setBounds(50, 140, recipientPicture.getIcon().getIconWidth(), recipientPicture.getIcon().getIconHeight());
        recipientPicture.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel recipientUsername = new JLabel("Username:");
        recipientUsername.setBounds(300, 140, 300, 25);
        JLabel recipientFirstName = new JLabel("First Name:");
        recipientFirstName.setBounds(300, 165, 300, 25);
        JLabel recipientMiddleName = new JLabel("Middle Name:");
        recipientMiddleName.setBounds(300, 190, 300, 25);
        JLabel recipientLastName = new JLabel("Last Name:");
        recipientLastName.setBounds(300, 215, 300, 25);

        dbUsername = new JLabel();
        dbUsername.setBounds(380,140,300,25);
        dbFirstName = new JLabel();
        dbFirstName.setBounds(380,165,300,25);
        dbMiddleName = new JLabel();
        dbMiddleName.setBounds(380, 190,300,25);
        dbLastName = new JLabel();
        dbLastName.setBounds(380,215,300,25);


        JLabel date = new JLabel("Date Issued: ");
        date.setBounds(60, 350, 500, 25);
        JLabel recipientRoomNumber = new JLabel("Room Number:");
        recipientRoomNumber.setBounds(60, 375, 500, 25);
        JLabel recipientRoomFee = new JLabel("Room Rent Fee:");
        recipientRoomFee.setBounds(60, 400, 500, 25);
        JLabel miscFeeLabel = new JLabel("Miscellaneous Fee:");
        miscFeeLabel.setBounds(60, 425, 150, 25);
        recipientMiscFee = new JTextField();
        recipientMiscFee.setToolTipText("Enter miscellaneous fees here.");
        recipientMiscFee.setEnabled(false);
        recipientMiscFee.setBounds(175, 425, 385, 25);
        recipientMiscFee.getDocument().addDocumentListener(this);
        recipientTotalFee = new JLabel("Total Fee:");
        recipientTotalFee.setBounds(60, 450, 500, 25);

        dbDate = new JLabel();
        dbDate.setBounds(172,350,500,25);
        dbRoomNumber = new JLabel();
        dbRoomNumber.setBounds(175,375,500,25);
        dbRoomFee = new JLabel();
        dbRoomFee.setBounds(175,400,500,25);
        dbTotalFee = new JLabel();
        dbTotalFee.setBounds(175,450,500,25);

        createBillResetButton = new JButton("Reset");
        createBillResetButton.setFont(new Font("Arial", Font.BOLD, 10));
        createBillResetButton.setFocusPainted(false);
        createBillResetButton.setBackground(Color.WHITE);
        createBillResetButton.setBounds(475, 525, 75, 25);
        createBillResetButton.setEnabled(false);
        createBillResetButton.addActionListener(this);

        createBillButton = new JButton("Create");
        createBillButton.setFont(new Font("Arial", Font.BOLD, 10));
        createBillButton.setFocusPainted(false);
        createBillButton.setBackground(Color.WHITE);
        createBillButton.setBounds(560, 525, 75, 25);
        createBillButton.setEnabled(false);
        createBillButton.addActionListener(this);

        add(dbDate);
        add(dbRoomNumber);
        add(dbRoomFee);
        add(dbTotalFee);
        add(dbFirstName);
        add(dbMiddleName);
        add(dbLastName);
        add(dbUsername);
        add(createBillHeader);
        add(recipientLabel);
        add(recipientSearchField);
        add(recipientSearchButton);
        add(recipientPicture);
        add(recipientUsername);
        add(recipientFirstName);
        add(recipientMiddleName);
        add(recipientLastName);
        add(date);
        add(recipientRoomNumber);
        add(recipientRoomFee);
        add(miscFeeLabel);
        add(recipientMiscFee);
        add(recipientTotalFee);
        add(createBillResetButton);
        add(createBillButton);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        // Search Recipient Button
        if(e.getSource() == recipientSearchButton)
        {
            try
            {
                int id = Integer.parseInt(recipientSearchField.getText());
                if(id / 100000 == 2)
                {
                    ResultSet resultSet = connection.getResult("SELECT * FROM users WHERE key='" + id + "'");
                    if(resultSet.next())
                    {
                        int roomID = 0;
                        int roomNumber;
                        double rentAmount;
                        String username = resultSet.getString("username");
                        String firstName = resultSet.getString("first_name");
                        String middleName = resultSet.getString("middle_name");
                        String lastName = resultSet.getString("last_name");
                        resultSet.close();

                        resultSet = connection.getResult("SELECT * FROM tenants WHERE key='" + id + "'");
                        if(resultSet.next())
                        {
                            roomID = resultSet.getInt("room");
                        }
                        resultSet.close();

                        resultSet = connection.getResult("SELECT * FROM rooms WHERE key='" + roomID + "'");
                        if(resultSet.next())
                        {
                            roomNumber = resultSet.getInt("room_number");
                            rentAmount = resultSet.getDouble("rent_amount");
                            LocalDate localDate = LocalDate.now();
                            int month = localDate.getMonthValue();
                            int day = localDate.getDayOfMonth();
                            int year = localDate.getYear();
                            dateString = String.format("%2d/%2d/%4d", month, day, year);
                            dbDate.setText(dateString);
                            dbUsername.setText(username);
                            dbFirstName.setText(firstName);
                            dbMiddleName.setText(middleName);
                            dbLastName.setText(lastName);
                            dbRoomNumber.setText(String.valueOf(roomNumber));
                            dbRoomFee.setText(String.valueOf(rentAmount));
                            recipientPicture.setIcon(new ImageIcon(new ImageIcon("image/" + id).getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
                            rentFee = rentAmount;
                            recipientSearchField.setEnabled(false);
                            recipientSearchButton.setEnabled(false);
                            recipientMiscFee.setEnabled(true);
                            createBillResetButton.setEnabled(true);
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "No room is assigned to this tenant. Please assign the tenant to a room first.", "Tenant does not have a room.", JOptionPane.ERROR_MESSAGE);
                        }
                        resultSet.close();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Please enter a valid recipient ID.", "Recipient not found.", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Please enter a valid recipient ID.", "Recipient is not a tenant.", JOptionPane.ERROR_MESSAGE);
                }
            }
            catch(NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(null, "Please enter a valid recipient ID.", "Invalid Input.", JOptionPane.ERROR_MESSAGE);
            }
            catch(SQLException ex)
            {
                ex.printStackTrace();
                System.out.println(ex.getMessage());
            }
        }

        // Create Bill Reset Button
        if(e.getSource() == createBillResetButton)
        {
            recipientPicture.setIcon(new ImageIcon(new ImageIcon("default_pic.png").getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
            dbDate.setText("");
            dbUsername.setText("");
            dbFirstName.setText("");
            dbMiddleName.setText("");
            dbLastName.setText("");
            dbRoomNumber.setText("");
            dbRoomFee.setText("");
            recipientMiscFee.setText("");
            dbTotalFee.setText("");
            recipientSearchField.setText("");
            rentFee = 0;
            recipientSearchField.setEnabled(true);
            recipientSearchButton.setEnabled(true);
            recipientMiscFee.setEnabled(false);
            createBillResetButton.setEnabled(false);
            createBillButton.setEnabled(false);
        }

        // Confirm Create Bill Button
        if(e.getSource() == createBillButton)
        {
            connection.execute("INSERT INTO bills(recipient_id, date_issued, total_amount, amount_paid) " +
                               "VALUES(" +
                               "'" + recipientSearchField.getText() + "'," +
                               "'" + dateString + "'," +
                               "'" + totalFee + "'," +
                               "'0'" +
                               ")");
            recipientPicture.setIcon(new ImageIcon(new ImageIcon("default_pic.png").getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
            dbDate.setText("");
            dbUsername.setText("");
            dbFirstName.setText("");
            dbMiddleName.setText("");
            dbLastName.setText("");
            dbRoomNumber.setText("");
            dbRoomFee.setText("");
            recipientMiscFee.setText("");
            dbTotalFee.setText("");
            recipientSearchField.setText("");
            rentFee = 0;
            recipientSearchField.setEnabled(true);
            recipientSearchButton.setEnabled(true);
            recipientMiscFee.setEnabled(false);
            createBillResetButton.setEnabled(false);
            createBillButton.setEnabled(false);
            JOptionPane.showMessageDialog(null, "The bill is successfully created!", "Bill Created", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void changeTotalFee(DocumentEvent e)
    {
        totalFee = rentFee;
        try
        {
            totalFee += Double.parseDouble(recipientMiscFee.getText());
        }
        catch(NumberFormatException ex)
        {
            System.out.println("Miscellaneous Fee textfield's value is not a number.");
        }
        recipientTotalFee.setText("Total Fee: " + totalFee);
        createBillButton.setEnabled(true);
    }

    @Override
    public void insertUpdate(DocumentEvent e)
    {
        changeTotalFee(e);
    }

    @Override
    public void removeUpdate(DocumentEvent e)
    {
        changeTotalFee(e);
    }

    @Override
    public void changedUpdate(DocumentEvent e)
    {
    }
}
