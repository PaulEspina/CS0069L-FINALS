import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Profile extends JFrame implements WindowListener
{
    AdminView adminView;
    Profile(AdminView adminView)
    {
        this.adminView = adminView;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(500, 400);
        setLocationRelativeTo(null);
        addWindowListener(this);
        setTitle("Profile");

        setVisible(true);
    }

    @Override
    public void windowOpened(WindowEvent e)
    {

    }

    @Override
    public void windowClosing(WindowEvent e)
    {

        adminView.getProfileButton().setEnabled(true);
    }

    @Override
    public void windowClosed(WindowEvent e)
    {
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
