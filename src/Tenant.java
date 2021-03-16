public class Tenant extends User
{
    private Room room;

    public Tenant(int userID, String username, String firstName, String middleName, String lastName, String imagePath)
    {
        super(userID, username, firstName, middleName, lastName, imagePath);
    }

    // Setters
    public void setRoom(Room room)
    {
        this.room = room;
    }

    // Getters
    public Room getRoom()
    {
        return room;
    }
}
