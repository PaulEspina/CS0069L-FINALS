public class TenantTest {
    public static void main (String[] args){

        DatabaseConnection dbCon = DatabaseConnection.getInstance();
        dbCon.init();

        //new TenantController(new Tenant(100000, "default_tenant", "", "", "", "default_pic.png"));
//        dbCon.execute("INSERT INTO users VALUES('200000', 'tenant', 'tenant', 'Paul Kristopher', 'D', 'Espina', 'default_pic.png')");
//
//        dbCon.execute("INSERT INTO bills('recipient_id', 'date_issued', 'total_amount', 'amount_paid') VALUES('200000', '01/05/2021', '3000', '0') ");
//        dbCon.execute("INSERT INTO bills('recipient_id', 'date_issued', 'total_amount', 'amount_paid') VALUES('200000', '02/05/2021', '3000', '0') ");
//        dbCon.execute("INSERT INTO bills('recipient_id', 'date_issued', 'total_amount', 'amount_paid') VALUES('200000', '03/05/2021', '3000', '0') ");
//        dbCon.execute("INSERT INTO bills('recipient_id', 'date_issued', 'total_amount', 'amount_paid') VALUES('200000', '04/05/2021', '3000', '0') ");
//        dbCon.execute("INSERT INTO bills('recipient_id', 'date_issued', 'total_amount', 'amount_paid') VALUES('200000', '05/05/2021', '3000', '0') ");
    }
}
