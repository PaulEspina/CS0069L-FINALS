import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;

public class PayBills extends JFrame implements WindowListener, ActionListener
{
    DatabaseConnection con = DatabaseConnection.getInstance();

    JLabel recipientIDLabel;
    JLabel dateIssueLabel;
    JLabel totalAmountLabel;
    JLabel amountPaidLabel;
    JLabel balanceRemainLabel;
    JLabel enterAmount;
    JPanel allDetailLabel;
    JTextField payBill;
    JButton pay;
    JButton close;

    double totalAmount;
    double amountPaid;

    int key;

    Tenant tenant;

    PayBills(Tenant tenant, int id)
    {
        this.tenant = tenant;
        this.key = id;

        //Frame settings
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Pay your bills below!");
        setLayout(null);
        setSize(500,300);
        setLocationRelativeTo(null);
        addWindowListener(this);
        setVisible(true);

        //Panel Settings
        allDetailLabel = new JPanel();
        allDetailLabel.setBounds(0,0,500,300);
        allDetailLabel.setLayout(null);

        //Label Settings
        recipientIDLabel = new JLabel();
        recipientIDLabel.setLayout(null);
        recipientIDLabel.setBounds(40,20,400,25);
        recipientIDLabel.setFont(new Font("Courier",Font.BOLD,14));

        dateIssueLabel = new JLabel();
        dateIssueLabel.setLayout(null);
        dateIssueLabel.setBounds(40,50,400,25);
        dateIssueLabel.setFont(new Font("Courier",Font.BOLD,14));

        totalAmountLabel = new JLabel();
        totalAmountLabel.setLayout(null);
        totalAmountLabel.setBounds(40,80,400,25);
        totalAmountLabel.setFont(new Font("Courier",Font.BOLD,14));

        amountPaidLabel = new JLabel();
        amountPaidLabel.setLayout(null);
        amountPaidLabel.setBounds(40,110,400,25);
        amountPaidLabel.setFont(new Font("Courier",Font.BOLD,14));

        balanceRemainLabel = new JLabel();
        balanceRemainLabel.setLayout(null);
        balanceRemainLabel.setBounds(40,140,400,25);
        balanceRemainLabel.setFont(new Font("Courier",Font.BOLD,14));

        enterAmount = new JLabel();
        enterAmount.setText("Enter Amount:");
        enterAmount.setLayout(null);
        enterAmount.setBounds(165,220,200,25);

        //Enter money settings
        payBill = new JTextField();
        payBill.setBounds(250,220,70,24);

        //Pay button
        pay = new JButton();
        pay.setText("Pay");
        pay.setBackground(Color.LIGHT_GRAY);
        pay.setFocusable(false);
        pay.setBorderPainted(false);
        pay.setOpaque(true);
        pay.setBounds(330,220,65,25);
        pay.addActionListener(this);

        //Close button
        close = new JButton();
        close.setText("Close");
        close.setBackground(Color.LIGHT_GRAY);
        close.setFocusable(false);
        close.setBorderPainted(false);
        close.setOpaque(true);
        close.setBounds(400,220,70,25);
        close.addActionListener(this);

        ResultSet rs = con.getResult("SELECT * FROM bills WHERE key='" + id + "'");
        try
        {
                int recipientID = rs.getInt("recipient_id");
                String dateIssue = rs.getString("date_issued");
                totalAmount = rs.getDouble("total_amount");
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

        allDetailLabel.add(enterAmount);
        allDetailLabel.add(close);
        allDetailLabel.add(pay);
        allDetailLabel.add(payBill);
        allDetailLabel.add(recipientIDLabel);
        allDetailLabel.add(dateIssueLabel);
        allDetailLabel.add(totalAmountLabel);
        allDetailLabel.add(amountPaidLabel);
        allDetailLabel.add(balanceRemainLabel);
        add(allDetailLabel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == close)
        {
            dispose();
        }
        if(e.getSource() == pay)
        {
            DatabaseConnection con = DatabaseConnection.getInstance();
            con.execute("UPDATE bills SET amount_paid='" + (Double.parseDouble(payBill.getText()) + amountPaid) + "'WHERE key= '" + key + "'");

            try
            {
                ResultSet rs = con.getResult("SELECT * FROM bills WHERE key='" + key + "'");

                if(rs.getDouble("amount_paid") < rs.getDouble("total_amount"))
                {
                    JOptionPane.showMessageDialog(null,"Payment Successful","Bill",JOptionPane.WARNING_MESSAGE);
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Your bill is fully paid! Thank you!","Bill", JOptionPane.WARNING_MESSAGE);
                }
            }
            catch(Exception f)
            {
                f.printStackTrace();
            }
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
        tenant.getPay().setEnabled(true);
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
