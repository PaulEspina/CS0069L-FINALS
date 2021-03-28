import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageApartment extends JPanel implements ActionListener
{
    private final DatabaseConnection connection;
    private final Admin admin;

    private final JPanel panelButton;
    private final JScrollPane panelScroll2;

    public ManageApartment(Admin admin)
    {
        this.admin = admin;

        connection = DatabaseConnection.getInstance();

        setLayout(null);

        JLabel manageApartmentHeader = new JLabel("MANAGE APARTMENT");
        manageApartmentHeader.setBounds(50, 25, 400, 50);
        manageApartmentHeader.setFont(new Font("Arial", Font.BOLD, 32));
        //TODO: Manage Apartment

        JButton continuousButton;
        JButton continuousButton1;
        JButton continuousButton2;
        JButton continuousButton3;
        JButton continuousButton4;
        JButton continuousButton5;
        JButton continuousButton6;
        JButton continuousButton7;
        JButton continuousButton8;
        JButton continuousButton9;
        JButton continuousButton10;
        JButton continuousButton11;
        JButton continuousButton12;
        JButton continuousButton13;
        JButton continuousButton14;
        JButton continuousButton15;
        JButton continuousButton16;

        JPanel continuousPanel;

        JPanel manageApartmentPanel = new JPanel();
        manageApartmentPanel.setLayout(null);
        manageApartmentPanel.setOpaque(true);

        continuousButton = new JButton();
        continuousButton.setPreferredSize(new Dimension(100,100));
        continuousButton.setBackground(Color.LIGHT_GRAY);
        continuousButton.setOpaque(true);
        continuousButton.setFocusable(false);

        continuousButton1 = new JButton();
        continuousButton1.setPreferredSize(new Dimension(100,100));
        continuousButton1.setBackground(Color.LIGHT_GRAY);
        continuousButton1.setOpaque(true);
        continuousButton1.setFocusable(false);

        continuousButton2 = new JButton();
        continuousButton2.setPreferredSize(new Dimension(100,100));
        continuousButton2.setBackground(Color.LIGHT_GRAY);
        continuousButton2.setOpaque(true);
        continuousButton2.setFocusable(false);

        continuousButton3 = new JButton();
        continuousButton3.setPreferredSize(new Dimension(100,100));
        continuousButton3.setBackground(Color.LIGHT_GRAY);
        continuousButton3.setOpaque(true);
        continuousButton3.setFocusable(false);

        continuousButton4 = new JButton();
        continuousButton4.setPreferredSize(new Dimension(100,100));
        continuousButton4.setBackground(Color.LIGHT_GRAY);
        continuousButton4.setOpaque(true);
        continuousButton4.setFocusable(false);

        continuousButton5 = new JButton();
        continuousButton5.setPreferredSize(new Dimension(100,100));
        continuousButton5.setBackground(Color.LIGHT_GRAY);
        continuousButton5.setOpaque(true);
        continuousButton5.setFocusable(false);

        continuousButton6 = new JButton();
        continuousButton6.setPreferredSize(new Dimension(100,100));
        continuousButton6.setBackground(Color.LIGHT_GRAY);
        continuousButton6.setOpaque(true);
        continuousButton6.setFocusable(false);

        continuousButton7 = new JButton();
        continuousButton7.setPreferredSize(new Dimension(100,100));
        continuousButton7.setBackground(Color.LIGHT_GRAY);
        continuousButton7.setOpaque(true);
        continuousButton7.setFocusable(false);

        continuousButton8 = new JButton();
        continuousButton8.setPreferredSize(new Dimension(100,100));
        continuousButton8.setBackground(Color.LIGHT_GRAY);
        continuousButton8.setOpaque(true);
        continuousButton8.setFocusable(false);

        continuousButton9 = new JButton();
        continuousButton9.setPreferredSize(new Dimension(100,100));
        continuousButton9.setBackground(Color.LIGHT_GRAY);
        continuousButton9.setOpaque(true);
        continuousButton9.setFocusable(false);

        continuousButton10 = new JButton();
        continuousButton10.setPreferredSize(new Dimension(100,100));
        continuousButton10.setBackground(Color.LIGHT_GRAY);
        continuousButton10.setOpaque(true);
        continuousButton10.setFocusable(false);

        continuousButton11 = new JButton();
        continuousButton11.setPreferredSize(new Dimension(100,100));
        continuousButton11.setBackground(Color.LIGHT_GRAY);
        continuousButton11.setOpaque(true);
        continuousButton11.setFocusable(false);

        continuousButton12 = new JButton();
        continuousButton12.setPreferredSize(new Dimension(100,100));
        continuousButton12.setBackground(Color.LIGHT_GRAY);
        continuousButton12.setOpaque(true);
        continuousButton12.setFocusable(false);

        continuousButton13 = new JButton();
        continuousButton13.setPreferredSize(new Dimension(100,100));
        continuousButton13.setBackground(Color.LIGHT_GRAY);
        continuousButton13.setOpaque(true);
        continuousButton13.setFocusable(false);

        continuousButton14 = new JButton();
        continuousButton14.setPreferredSize(new Dimension(100,100));
        continuousButton14.setBackground(Color.LIGHT_GRAY);
        continuousButton14.setOpaque(true);
        continuousButton14.setFocusable(false);

        continuousButton15 = new JButton();
        continuousButton15.setPreferredSize(new Dimension(100,100));
        continuousButton15.setBackground(Color.LIGHT_GRAY);
        continuousButton15.setOpaque(true);
        continuousButton15.setFocusable(false);

        continuousButton16 = new JButton();
        continuousButton16.setPreferredSize(new Dimension(100,100));
        continuousButton16.setBackground(Color.LIGHT_GRAY);
        continuousButton16.setOpaque(true);
        continuousButton16.setFocusable(false);

        panelButton = new JPanel();
        panelButton.setLayout(new FlowLayout(FlowLayout.LEADING,30,25));
        panelButton.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        panelButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panelButton.setBounds(30,100,550,Integer.MAX_VALUE);

        panelScroll2 = new JScrollPane(panelButton);
        panelScroll2.setBounds(26,115,585,400);
        panelScroll2.setBackground(Color.LIGHT_GRAY);
        panelScroll2.setVisible(true);

        add(continuousButton);
        add(continuousButton1);
        add(continuousButton2);
        add(continuousButton3);
        add(continuousButton4);
        add(continuousButton5);
        add(continuousButton6);
        add(continuousButton7);
        add(continuousButton8);
        add(continuousButton9);
        add(continuousButton10);
        add(continuousButton11);
        add(continuousButton12);
        add(continuousButton13);
        add(continuousButton14);
        add(continuousButton15);
        add(continuousButton16);

        manageApartmentPanel.add(panelScroll2);

        manageApartmentPanel.add(manageApartmentHeader);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

    }
}
