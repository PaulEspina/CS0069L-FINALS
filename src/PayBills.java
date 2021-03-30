import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;

public class PayBills extends JFrame implements WindowListener, ActionListener
{
    private final DatabaseConnection connection;
    private final Tenant tenant;
    private final int key;

    private final JTextField payBill;
    private final JButton payButton;
    private final JButton closeButton;

    private double amountPaid;

    PayBills(Tenant tenant, int id)
    {
        this.tenant = tenant;
        this.key = id;
        connection = DatabaseConnection.getInstance();

        //Frame settings
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Pay Bills");
        setLayout(null);
        setSize(400,300);
        setLocationRelativeTo(null);
        addWindowListener(this);

        //Label Settings
        JLabel recipientIDLabel = new JLabel();
        recipientIDLabel.setBounds(25, 20, 375, 25);

        JLabel dateIssueLabel = new JLabel();
        dateIssueLabel.setBounds(25, 50, 375, 25);

        JLabel totalAmountLabel = new JLabel();
        totalAmountLabel.setBounds(25, 80, 375, 25);

        JLabel amountPaidLabel = new JLabel();
        amountPaidLabel.setBounds(25, 110, 375, 25);

        JLabel balanceRemainLabel = new JLabel();
        balanceRemainLabel.setBounds(25, 140, 375, 25);

        JLabel enterAmount = new JLabel();
        enterAmount.setText("Enter Amount:");
        enterAmount.setBounds(70, 220, 100, 25);

        //Enter money settings
        payBill = new JTextField();
        payBill.setBounds(170,220,100,25);

        //Pay button
        payButton = new JButton("Pay");
        payButton.setBackground(Color.WHITE);
        payButton.setFont(new Font("Arial", Font.BOLD, 10));
        payButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        payButton.setFocusPainted(false);
        payButton.setBounds(272, 220, 50, 24);
        payButton.addActionListener(this);

        //Close button
        closeButton = new JButton("Close");
        closeButton.setBackground(Color.WHITE);
        closeButton.setFont(new Font("Arial", Font.BOLD, 10));
        closeButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        closeButton.setFocusPainted(false);
        closeButton.setBounds(325, 220, 50, 24);
        closeButton.addActionListener(this);

        ResultSet rs = connection.getResult("SELECT * FROM bills WHERE key='" + id + "'");
        try
        {
                int recipientID = rs.getInt("recipient_id");
                String dateIssue = rs.getString("date_issued");
            double totalAmount = rs.getDouble("total_amount");
                amountPaid = rs.getDouble("amount_paid");

                recipientIDLabel.setText("Recipient ID: " + recipientID);
                dateIssueLabel.setText("Date Issued: " + dateIssue);
                totalAmountLabel.setText("Total Amount: " + totalAmount);
                amountPaidLabel.setText("Amount Paid: " + amountPaid);
                balanceRemainLabel.setText("Balance Remaining: " + (totalAmount - amountPaid));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        add(enterAmount);
        add(closeButton);
        add(payButton);
        add(payBill);
        add(recipientIDLabel);
        add(dateIssueLabel);
        add(totalAmountLabel);
        add(amountPaidLabel);
        add(balanceRemainLabel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == payButton)
        {
            connection.execute("UPDATE bills SET amount_paid='" + (Double.parseDouble(payBill.getText()) + amountPaid) + "'WHERE key= '" + key + "'");
            ResultSet resultSet = connection.getResult("SELECT * FROM bills WHERE key='" + key + "'");
            try
            {
                if(resultSet.next())
                {
                    if(resultSet.getDouble("amount_paid") < resultSet.getDouble("total_amount"))
                    {
                        JOptionPane.showMessageDialog(null,"Payment Successful","Bill",JOptionPane.WARNING_MESSAGE);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"Your Bill Is Fully Paid! Thank you!","Bill", JOptionPane.WARNING_MESSAGE);
                    }
                }
                resultSet.close();
            }
            catch(Exception f)
            {
                f.printStackTrace();
            }
            dispose();
        }

        if(e.getSource() == closeButton)
        {
            dispose();
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e)
    {
        tenant.enablePayButton();
        tenant.refresh();
    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
