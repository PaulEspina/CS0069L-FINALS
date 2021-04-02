import javax.swing.*;
import java.awt.*;

public class SidePanel extends JPanel
{
    public SidePanel()
    {
        setPreferredSize(new Dimension(140, 600));
        setLayout(new GridLayout(3, 1));
        setBackground(Color.WHITE);
        setOpaque(true);
    }
}
