import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class ManageApartment extends JPanel implements ActionListener
{
    private final DatabaseConnection connection;
    private final Admin admin;

    private final JPanel panelButton;
    private final JScrollPane panelScroll2;

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

    JLabel tenantName;

    JLabel room;

    public ManageApartment(Admin admin)
    {
        this.admin = admin;

        connection = DatabaseConnection.getInstance();

        setLayout(null);

        JLabel manageApartmentHeader = new JLabel("MANAGE APARTMENT");
        manageApartmentHeader.setBounds(50, 25, 400, 50);
        manageApartmentHeader.setFont(new Font("Arial", Font.BOLD, 32));
        //TODO: Manage Apartment

        continuousPanel = new JPanel();
        continuousPanel.setLayout(null);
        continuousPanel.setBounds(0,0,125,125);
        continuousPanel.setBackground(Color.LIGHT_GRAY);

        tenantName = new JLabel();
        tenantName.setText(" ");
        tenantName.setFont(new Font("Arial", Font.PLAIN,10));
        tenantName.setBounds(1,1,75,10);

        room = new JLabel();
        room.setText("ROOM");
        room.setFont(new Font("Arial", Font.BOLD,10));
        room.setBounds(28,25,75,10);

        panelButton = new JPanel();
        panelButton.setLayout(new FlowLayout(FlowLayout.LEADING, 15, 15));
        panelButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panelButton.setPreferredSize(new Dimension(550, 500));

        continuousButton = new JButton();
        continuousButton.setPreferredSize(new Dimension(125,125));
        continuousButton.setBackground(Color.LIGHT_GRAY);
        continuousButton.setOpaque(true);
        continuousButton.setFocusable(false);
        continuousButton.addActionListener(this);

        continuousButton1 = new JButton();
        continuousButton1.setPreferredSize(new Dimension(125,125));
        continuousButton1.setBackground(Color.LIGHT_GRAY);
        continuousButton1.setOpaque(true);
        continuousButton1.setFocusable(false);

        continuousButton2 = new JButton();
        continuousButton2.setPreferredSize(new Dimension(125,125));
        continuousButton2.setBackground(Color.LIGHT_GRAY);
        continuousButton2.setOpaque(true);
        continuousButton2.setFocusable(false);

        continuousButton3 = new JButton();
        continuousButton3.setPreferredSize(new Dimension(125,125));
        continuousButton3.setBackground(Color.LIGHT_GRAY);
        continuousButton3.setOpaque(true);
        continuousButton3.setFocusable(false);

        continuousButton4 = new JButton();
        continuousButton4.setPreferredSize(new Dimension(125,125));
        continuousButton4.setBackground(Color.LIGHT_GRAY);
        continuousButton4.setOpaque(true);
        continuousButton4.setFocusable(false);

        continuousButton5 = new JButton();
        continuousButton5.setPreferredSize(new Dimension(125,125));
        continuousButton5.setBackground(Color.LIGHT_GRAY);
        continuousButton5.setOpaque(true);
        continuousButton5.setFocusable(false);

        continuousButton6 = new JButton();
        continuousButton6.setPreferredSize(new Dimension(125,125));
        continuousButton6.setBackground(Color.LIGHT_GRAY);
        continuousButton6.setOpaque(true);
        continuousButton6.setFocusable(false);

        continuousButton7 = new JButton();
        continuousButton7.setPreferredSize(new Dimension(125,125));
        continuousButton7.setBackground(Color.LIGHT_GRAY);
        continuousButton7.setOpaque(true);
        continuousButton7.setFocusable(false);

        continuousButton8 = new JButton();
        continuousButton8.setPreferredSize(new Dimension(125,125));
        continuousButton8.setBackground(Color.LIGHT_GRAY);
        continuousButton8.setOpaque(true);
        continuousButton8.setFocusable(false);

        continuousButton9 = new JButton();
        continuousButton9.setPreferredSize(new Dimension(125,125));
        continuousButton9.setBackground(Color.LIGHT_GRAY);
        continuousButton9.setOpaque(true);
        continuousButton9.setFocusable(false);

        continuousButton10 = new JButton();
        continuousButton10.setPreferredSize(new Dimension(125,125));
        continuousButton10.setBackground(Color.LIGHT_GRAY);
        continuousButton10.setOpaque(true);
        continuousButton10.setFocusable(false);

        continuousButton11 = new JButton();
        continuousButton11.setPreferredSize(new Dimension(125,125));
        continuousButton11.setBackground(Color.LIGHT_GRAY);
        continuousButton11.setOpaque(true);
        continuousButton11.setFocusable(false);

        continuousButton12 = new JButton();
        continuousButton12.setPreferredSize(new Dimension(125,125));
        continuousButton12.setBackground(Color.LIGHT_GRAY);
        continuousButton12.setOpaque(true);
        continuousButton12.setFocusable(false);

        continuousButton13 = new JButton();
        continuousButton13.setPreferredSize(new Dimension(125,125));
        continuousButton13.setBackground(Color.LIGHT_GRAY);
        continuousButton13.setOpaque(true);
        continuousButton13.setFocusable(false);

        continuousButton14 = new JButton();
        continuousButton14.setPreferredSize(new Dimension(125,125));
        continuousButton14.setBackground(Color.LIGHT_GRAY);
        continuousButton14.setOpaque(true);
        continuousButton14.setFocusable(false);

        continuousButton15 = new JButton();
        continuousButton15.setPreferredSize(new Dimension(125,125));
        continuousButton15.setBackground(Color.LIGHT_GRAY);
        continuousButton15.setOpaque(true);
        continuousButton15.setFocusable(false);

        continuousButton16 = new JButton();
        continuousButton16.setPreferredSize(new Dimension(125,125));
        continuousButton16.setBackground(Color.LIGHT_GRAY);
        continuousButton16.setOpaque(true);
        continuousButton16.setFocusable(false);

        continuousButton.add(continuousPanel);
        continuousPanel.add(tenantName);
        continuousPanel.add(room);

        panelButton.add(continuousButton);
        panelButton.add(continuousButton1);
        panelButton.add(continuousButton2);
        panelButton.add(continuousButton3);
        panelButton.add(continuousButton4);
        panelButton.add(continuousButton5);
        panelButton.add(continuousButton6);
        panelButton.add(continuousButton7);
        panelButton.add(continuousButton8);
        panelButton.add(continuousButton9);
        panelButton.add(continuousButton10);
        panelButton.add(continuousButton11);
        panelButton.add(continuousButton12);
        panelButton.add(continuousButton13);
        panelButton.add(continuousButton14);
        panelButton.add(continuousButton15);
        panelButton.add(continuousButton16);

        panelScroll2 = new JScrollPane(panelButton, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panelScroll2.setBounds(50,100,550,450);
        panelScroll2.setBackground(Color.LIGHT_GRAY);
        panelScroll2.setVisible(true);

        add(manageApartmentHeader);
        add(panelScroll2);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == continuousButton)
        {
            new RoomDetails(admin);
        }
    }
}
