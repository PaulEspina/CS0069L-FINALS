import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class AdminController implements ActionListener, WindowListener
{
    AdminView adminView;
    DatabaseConnection connection;

    public AdminController(Admin admin)
    {
        this.adminView = new AdminView(admin);
        connection = DatabaseConnection.getInstance();

        adminView.start();

        adminView.getProfileButton().addActionListener(this);
        for(JButton button : adminView.getNavButtons())
        {
            button.addActionListener(this);
        }
    }

    private void logout()
    {
        DatabaseConnection dbCon = DatabaseConnection.getInstance();
        dbCon.close();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == adminView.getNavButtons()[0])
        {
            for(JButton button : adminView.getNavButtons())
            {
                button.setBackground(Color.WHITE);
            }
            adminView.getNavButtons()[0].setBackground(Color.LIGHT_GRAY);
            adminView.getContentCard().show(adminView.getContentPanel(), "manageApartmentPanel");
        }
        if(e.getSource() == adminView.getNavButtons()[1])
        {
            for(JButton button : adminView.getNavButtons())
            {
                button.setBackground(Color.WHITE);
            }
            adminView.getNavButtons()[1].setBackground(Color.LIGHT_GRAY);
            adminView.getContentCard().show(adminView.getContentPanel(), "manageTenantsPanel");
        }
        if(e.getSource() == adminView.getNavButtons()[2])
        {
            for(JButton button : adminView.getNavButtons())
            {
                button.setBackground(Color.WHITE);
            }
            adminView.getNavButtons()[2].setBackground(Color.LIGHT_GRAY);
            adminView.getContentCard().show(adminView.getContentPanel(), "createBillPanel");
        }
        if(e.getSource() == adminView.getNavButtons()[3])
        {
            for(JButton button : adminView.getNavButtons())
            {
                button.setBackground(Color.WHITE);
            }
            adminView.getNavButtons()[3].setBackground(Color.LIGHT_GRAY);
            adminView.getContentCard().show(adminView.getContentPanel(), "createUser");
        }
        if(e.getSource() == adminView.getProfileButton())
        {
            adminView.getProfileButton().setEnabled(false);
            new Profile(adminView);
        }
    }

    @Override
    public void windowOpened(WindowEvent e)
    {

    }

    @Override
    public void windowClosing(WindowEvent e)
    {
        logout();
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
