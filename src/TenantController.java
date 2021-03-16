import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class TenantController implements ActionListener, WindowListener
{
    TenantView tenantView;
    DatabaseConnection connection;

    public TenantController(Tenant tenant)
    {
        this.tenantView = new TenantView(tenant);
        connection = DatabaseConnection.getInstance();

        tenantView.start();

        tenantView.getProfileButton().addActionListener(this);
    }

    private void logout()
    {

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == tenantView.getProfileButton()){
            new Profile(tenantView);
        }

    }

    @Override
    public void windowOpened(WindowEvent e)
    {

    }

    @Override
    public void windowClosing(WindowEvent e)
    {
        connection.close();
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
