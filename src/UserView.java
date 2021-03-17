import javax.swing.*;
import java.awt.*;

public abstract class UserView {

    protected JFrame frame;
    protected JPanel sidePanel;
    protected JPanel contentPanel;
    protected JButton profileButton;
    protected User user;

    public User getModel()
    {
        return user;
    }

    public JFrame getFrame()
    {
        return frame;
    }

    public JPanel getSidePanel()
    {
        return sidePanel;
    }

    public JPanel getContentPanel()
    {
        return contentPanel;
    }

    public JButton getProfileButton()
    {
        return profileButton;
    }

}
