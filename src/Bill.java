public class Bill
{
    private int billID;
    private int recipientID;
    private String dateIssued;
    private double totalAmount;
    private double amountPaid;

    public Bill()
    {
        billID = 0;
        recipientID = 0;
        dateIssued = "";
        totalAmount = 0.0;
        amountPaid = 0.0;
    }

    public Bill(int billID, int recipientID, String dateIssued, double totalAmount, double amountPaid)
    {
        this.billID = billID;
        this.recipientID = recipientID;
        this.dateIssued = dateIssued;
        this.totalAmount = totalAmount;
        this.amountPaid = amountPaid;
    }

    // Setters
    public void setBillID(int value)
    {
        billID = value;
    }

    public void setRecipientID(int value)
    {
        recipientID = value;
    }

    public void setDateIssued(String date)
    {
        dateIssued = date;
    }

    public void setTotalAmount(double value)
    {
        assert value > 0: "Cannot input negative values";
        if(value > 0)
        {
            totalAmount = value;
        }
    }

    public void setAmountPaid(double value)
    {
        assert value > 0: "Cannot input negative values";
        amountPaid = value;
        if(value > 0)
        {
            totalAmount = value;
        }
    }

    // Getters
    public int getBillID()
    {
        return billID;
    }

    public int getRecipientID()
    {
        return recipientID;
    }

    public String getDateIssued()
    {
        return dateIssued;
    }

    public double getTotalAmount()
    {
        return totalAmount;
    }

    public double getAmountPaid()
    {
        return amountPaid;
    }

    public boolean getStatus()
    {
        return amountPaid >= totalAmount;
    }
}
