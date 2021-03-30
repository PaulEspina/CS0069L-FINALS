import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main
{
    public boolean loggedIn;

    public static void main(String[] args)
    {
        try
        {
            Path path = Paths.get("image");
            Files.createDirectories(path);
            File image = new File("default_pic.png");
            File save = new File("image/100000");
            if(!save.isFile())
            {
                Files.copy(image.toPath(), save.toPath());
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        DatabaseConnection dbCon = DatabaseConnection.getInstance();
        dbCon.init();
        new Login();
    }
}
