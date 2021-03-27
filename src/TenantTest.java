public class TenantTest {
    public static void main (String[] args){

        DatabaseConnection dbCon = DatabaseConnection.getInstance();
        dbCon.init();

        //new TenantController(new Tenant(100000, "default_tenant", "", "", "", "default_pic.png"));
        //dbCon.execute("INSERT INTO users VALUES('200000', 'tenant', 'tenant', 'Paul Kristopher', 'D', 'Espina')");

        //dbCon.execute("UPDATE bills SET amount_paid='3000' WHERE recipient_id=");

//        dbCon.execute("INSERT INTO bills('recipient_id', 'date_issued', 'total_amount', 'amount_paid') VALUES('205000', '01/05/2021', '3000', '3000') ");
//        dbCon.execute("INSERT INTO bills('recipient_id', 'date_issued', 'total_amount', 'amount_paid') VALUES('205000', '02/05/2021', '3000', '0') ");
//        dbCon.execute("INSERT INTO bills('recipient_id', 'date_issued', 'total_amount', 'amount_paid') VALUES('205000', '03/05/2021', '3000', '0') ");

        dbCon.execute("INSERT INTO users(key, username, password, first_name, middle_name, last_name) VALUES('205000', 'qwe', 'qwe', 'Paul', 'Rendon', 'Espina')");
        dbCon.execute("INSERT INTO tenants(key, room) VALUES('205000', '2')");
        dbCon.execute("INSERT INTO rooms(room_number, rent_amount) VALUES('2', '3000')");

        dbCon.execute("INSERT INTO users(key, username, password, first_name, middle_name, last_name) VALUES('200000', 'ha', 'ha', 'Paul', 'Rendon', 'Espina')");
        dbCon.execute("INSERT INTO tenants(key, room) VALUES('200000', '1')");
        dbCon.execute("INSERT INTO rooms(room_number, rent_amount) VALUES('1', '3000')");

//        dbCon.execute("INSERT INTO bills('recipient_id', 'date_issued', 'total_amount', 'amount_paid') VALUES('205000', '04/05/2021', '3000', '0') ");
//        dbCon.execute("INSERT INTO bills('recipient_id', 'date_issued', 'total_amount', 'amount_paid') VALUES('205000', '06/05/2021', '3000', '0') ");
//        dbCon.execute("INSERT INTO bills('recipient_id', 'date_issued', 'total_amount', 'amount_paid') VALUES('205000', '07/05/2021', '3000', '0') ");
//        dbCon.execute("INSERT INTO bills('recipient_id', 'date_issued', 'total_amount', 'amount_paid') VALUES('205000', '08/05/2021', '3000', '0') ");
//        dbCon.execute("INSERT INTO bills('recipient_id', 'date_issued', 'total_amount', 'amount_paid') VALUES('205000', '09/05/2021', '3000', '0') ");
//        dbCon.execute("INSERT INTO bills('recipient_id', 'date_issued', 'total_amount', 'amount_paid') VALUES('205000', '10/05/2021', '3000', '0') ");
//        dbCon.execute("INSERT INTO bills('recipient_id', 'date_issued', 'total_amount', 'amount_paid') VALUES('205000', '11/05/2021', '3000', '0') ");
//        dbCon.execute("INSERT INTO bills('recipient_id', 'date_issued', 'total_amount', 'amount_paid') VALUES('205000', '12/05/2021', '3000', '0') ");
//        dbCon.execute("INSERT INTO bills('recipient_id', 'date_issued', 'total_amount', 'amount_paid') VALUES('205000', '01/05/2022', '3000', '0') ");
//        dbCon.execute("INSERT INTO bills('recipient_id', 'date_issued', 'total_amount', 'amount_paid') VALUES('205000', '02/05/2022', '3000', '0') ");
//        dbCon.execute("INSERT INTO bills('recipient_id', 'date_issued', 'total_amount', 'amount_paid') VALUES('205000', '03/05/2022', '3000', '0') ");
//        dbCon.execute("INSERT INTO bills('recipient_id', 'date_issued', 'total_amount', 'amount_paid') VALUES('205000', '04/05/2022', '3000', '0') ");
//        dbCon.execute("INSERT INTO bills('recipient_id', 'date_issued', 'total_amount', 'amount_paid') VALUES('205000', '05/05/2022', '3000', '0') ");
//        dbCon.execute("INSERT INTO bills('recipient_id', 'date_issued', 'total_amount', 'amount_paid') VALUES('205000', '06/05/2022', '3000', '0') ");
//        dbCon.execute("INSERT INTO bills('recipient_id', 'date_issued', 'total_amount', 'amount_paid') VALUES('205000', '07/05/2022', '3000', '0') ");
//        dbCon.execute("INSERT INTO bills('recipient_id', 'date_issued', 'total_amount', 'amount_paid') VALUES('205000', '08/05/2022', '3000', '0') ");
//        dbCon.execute("INSERT INTO bills('recipient_id', 'date_issued', 'total_amount', 'amount_paid') VALUES('205000', '09/05/2022', '3000', '0') ");
//        dbCon.execute("INSERT INTO bills('recipient_id', 'date_issued', 'total_amount', 'amount_paid') VALUES('205000', '10/05/2022', '3000', '0') ");
//        dbCon.execute("INSERT INTO bills('recipient_id', 'date_issued', 'total_amount', 'amount_paid') VALUES('205000', '11/05/2022', '3000', '0') ");
//        dbCon.execute("INSERT INTO bills('recipient_id', 'date_issued', 'total_amount', 'amount_paid') VALUES('205000', '12/05/2022', '3000', '0') ");
//        dbCon.execute("INSERT INTO bills('recipient_id', 'date_issued', 'total_amount', 'amount_paid') VALUES('205000', '01/05/2023', '3000', '0') ");
//        dbCon.execute("INSERT INTO bills('recipient_id', 'date_issued', 'total_amount', 'amount_paid') VALUES('205000', '02/05/2023', '3000', '0') ");
//        dbCon.execute("INSERT INTO bills('recipient_id', 'date_issued', 'total_amount', 'amount_paid') VALUES('205000', '03/05/2023', '3000', '0') ");
//        dbCon.execute("INSERT INTO bills('recipient_id', 'date_issued', 'total_amount', 'amount_paid') VALUES('205000', '04/05/2023', '3000', '0') ");
//        dbCon.execute("INSERT INTO bills('recipient_id', 'date_issued', 'total_amount', 'amount_paid') VALUES('205000', '05/05/2023', '3000', '0') ");
//        dbCon.execute("INSERT INTO bills('recipient_id', 'date_issued', 'total_amount', 'amount_paid') VALUES('205000', '06/05/2023', '3000', '0') ");
//        dbCon.execute("INSERT INTO bills('recipient_id', 'date_issued', 'total_amount', 'amount_paid') VALUES('205000', '07/05/2023', '3000', '0') ");
//        dbCon.execute("INSERT INTO bills('recipient_id', 'date_issued', 'total_amount', 'amount_paid') VALUES('205000', '08/05/2023', '3000', '0') ");
//        dbCon.execute("INSERT INTO bills('recipient_id', 'date_issued', 'total_amount', 'amount_paid') VALUES('205000', '09/05/2023', '3000', '0') ");
//        dbCon.execute("INSERT INTO bills('recipient_id', 'date_issued', 'total_amount', 'amount_paid') VALUES('205000', '10/05/2023', '3000', '0') ");
//        dbCon.execute("INSERT INTO bills('recipient_id', 'date_issued', 'total_amount', 'amount_paid') VALUES('205000', '11/05/2023', '3000', '0') ");
//        dbCon.execute("INSERT INTO bills('recipient_id', 'date_issued', 'total_amount', 'amount_paid') VALUES('205000', '12/05/2023', '3000', '0') ");
//        dbCon.execute("INSERT INTO bills('recipient_id', 'date_issued', 'total_amount', 'amount_paid') VALUES('205000', '01/05/2024', '3000', '0') ");
//        dbCon.execute("INSERT INTO bills('recipient_id', 'date_issued', 'total_amount', 'amount_paid') VALUES('205000', '02/05/2024', '3000', '0') ");
//        dbCon.execute("INSERT INTO bills('recipient_id', 'date_issued', 'total_amount', 'amount_paid') VALUES('205000', '03/05/2024', '3000', '0') ");
//        dbCon.execute("INSERT INTO bills('recipient_id', 'date_issued', 'total_amount', 'amount_paid') VALUES('205000', '04/05/2024', '3000', '0') ");
//        dbCon.execute("INSERT INTO bills('recipient_id', 'date_issued', 'total_amount', 'amount_paid') VALUES('205000', '05/05/2024', '3000', '0') ");
//        dbCon.execute("INSERT INTO bills('recipient_id', 'date_issued', 'total_amount', 'amount_paid') VALUES('205000', '06/05/2024', '3000', '0') ");
//        dbCon.execute("INSERT INTO bills('recipient_id', 'date_issued', 'total_amount', 'amount_paid') VALUES('205000', '07/05/2024', '3000', '0') ");
//        dbCon.execute("INSERT INTO bills('recipient_id', 'date_issued', 'total_amount', 'amount_paid') VALUES('205000', '08/05/2024', '3000', '0') ");
//        dbCon.execute("INSERT INTO bills('recipient_id', 'date_issued', 'total_amount', 'amount_paid') VALUES('205000', '09/05/2024', '3000', '0') ");
//        dbCon.execute("INSERT INTO bills('recipient_id', 'date_issued', 'total_amount', 'amount_paid') VALUES('205000', '10/05/2024', '3000', '0') ");
//        dbCon.execute("INSERT INTO bills('recipient_id', 'date_issued', 'total_amount', 'amount_paid') VALUES('205000', '11/05/2024', '3000', '0') ");
//        dbCon.execute("INSERT INTO bills('recipient_id', 'date_issued', 'total_amount', 'amount_paid') VALUES('205000', '12/05/2024', '3000', '0') ");
    }
}
