import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class CreateUser extends JPanel implements ActionListener
{
    DatabaseConnection connection;
    private final Admin admin;

    private final JLabel newPicture;
    private final JButton uploadPictureButton;
    private final JTextField enterFirstName;
    private final JTextField enterMiddleName;
    private final JTextField enterLastName;
    private final JTextField enterUsername;
    private final JPasswordField enterPassword;
    private final JPasswordField enterConfirmPassword;
    private final JRadioButton tenantTypeButton;
    private final JRadioButton adminTypeButton;
    private final ButtonGroup accountTypeButton;
    private final JButton createUserResetButton;
    private final JButton createUserButton;
    private File image;

    public CreateUser(Admin admin)
    {
        this.admin = admin;

        connection = DatabaseConnection.getInstance();

        setLayout(null);

        JLabel createUserHeader = new JLabel("CREATE USER");
        createUserHeader.setBounds(50, 25, 400, 50);
        createUserHeader.setFont(new Font("Arial", Font.BOLD, 32));

        newPicture = new JLabel();
        newPicture.setIcon(new ImageIcon(new ImageIcon("default_pic.png").getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
        newPicture.setBounds(50, 100, newPicture.getIcon().getIconWidth(), newPicture.getIcon().getIconHeight());
        newPicture.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        uploadPictureButton = new JButton("Upload Image");
        uploadPictureButton.setFont(new Font("Arial", Font.BOLD, 9));
        uploadPictureButton.setFocusPainted(false);
        uploadPictureButton.setBackground(Color.WHITE);
        uploadPictureButton.setBounds(75, 260, 100, 24);
        uploadPictureButton.addActionListener(this);

        JLabel username = new JLabel("Username:");
        username.setBounds(250, 100, 200, 25);
        JLabel password = new JLabel("Password:");
        password.setBounds(250, 135, 200, 25);
        JLabel confirmPassword = new JLabel("Confirm Password:");
        confirmPassword.setBounds(250, 170, 200, 25);
        JLabel firstName = new JLabel("First Name:");
        firstName.setBounds(250, 205, 200, 25);
        JLabel middleName = new JLabel("Middle Name:");
        middleName.setBounds(250, 240, 200, 25);
        JLabel lastName = new JLabel("Last Name:");
        lastName.setBounds(250, 275, 200, 25);
        JLabel accountType = new JLabel("Account Type:");
        accountType.setBounds(250, 310, 200, 25);

        enterUsername = new JTextField();
        enterUsername.setBounds(375, 100, 200, 25);
        enterPassword = new JPasswordField();
        enterPassword.setBounds(375, 135, 200, 25);
        enterConfirmPassword = new JPasswordField();
        enterConfirmPassword.setBounds(375, 170, 200, 25);
        enterFirstName = new JTextField();
        enterFirstName.setBounds(375, 205, 200, 25);
        enterMiddleName = new JTextField();
        enterMiddleName.setBounds(375, 240, 200, 25);
        enterLastName = new JTextField();
        enterLastName.setBounds(375, 275, 200, 25);
        adminTypeButton = new JRadioButton("Admin");
        adminTypeButton.setBounds(375, 310, 100, 25);
        adminTypeButton.setFocusPainted(false);
        tenantTypeButton = new JRadioButton("Tenant");
        tenantTypeButton.setFocusPainted(false);
        tenantTypeButton.setBounds(475, 310, 100, 25);
        accountTypeButton = new ButtonGroup();
        accountTypeButton.add(adminTypeButton);
        accountTypeButton.add(tenantTypeButton);

        createUserResetButton = new JButton("Reset");
        createUserResetButton.setFont(new Font("Arial", Font.BOLD, 10));
        createUserResetButton.setFocusPainted(false);
        createUserResetButton.setBackground(Color.WHITE);
        createUserResetButton.setBounds(475, 525, 75, 25);
        createUserResetButton.addActionListener(this);

        createUserButton = new JButton("Create");
        createUserButton.setFont(new Font("Arial", Font.BOLD, 10));
        createUserButton.setFocusPainted(false);
        createUserButton.setBackground(Color.WHITE);
        createUserButton.setBounds(560, 525, 75, 25);
        createUserButton.addActionListener(this);

        add(createUserHeader);
        add(newPicture);
        add(uploadPictureButton);
        add(username);
        add(password);
        add(confirmPassword);
        add(firstName);
        add(middleName);
        add(lastName);
        add(accountType);
        add(enterUsername);
        add(enterPassword);
        add(enterConfirmPassword);
        add(enterFirstName);
        add(enterMiddleName);
        add(enterLastName);
        add(adminTypeButton);
        add(tenantTypeButton);
        add(createUserResetButton);
        add(createUserButton);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        // Upload Picture Button
        if(e.getSource() == uploadPictureButton)
        {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes()));
            int i = fileChooser.showOpenDialog(null);
            if(i == JFileChooser.APPROVE_OPTION)
            {
                image = fileChooser.getSelectedFile();
                newPicture.setIcon(new ImageIcon(new ImageIcon(image.getPath()).getImage().getScaledInstance(newPicture.getWidth(), newPicture.getHeight(), Image.SCALE_SMOOTH)));
            }
        }

        // Create User Reset Button
        if(e.getSource() == createUserResetButton)
        {
            enterUsername.setText("");
            enterPassword.setText("");
            enterConfirmPassword.setText("");
            enterFirstName.setText("");
            enterMiddleName.setText("");
            enterLastName.setText("");
            accountTypeButton.clearSelection();
            newPicture.setIcon(new ImageIcon(new ImageIcon("default_pic.png").getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
            image = null;
        }

        // Confirm Create User Button
        if(e.getSource() == createUserButton)
        {
            boolean exists = false;
            ResultSet resultSet = connection.getResult("SELECT * FROM users WHERE username='" + enterUsername.getText() + "'");
            try
            {
                exists = resultSet.next();
                resultSet.close();
            }
            catch(SQLException throwables)
            {
                throwables.printStackTrace();
            }

            if(image == null)
            {
                image = new File("default_pic.png");
            }
            if(!enterUsername.getText().isEmpty() &&
               !String.valueOf(enterPassword.getPassword()).isEmpty() &&
               !String.valueOf(enterPassword.getPassword()).isEmpty() &&
               !enterFirstName.getText().isEmpty() &&
               !enterMiddleName.getText().isEmpty() &&
               !enterLastName.getText().isEmpty() &&
               (tenantTypeButton.isSelected() || adminTypeButton.isSelected()))
            {
                if(!exists)
                {
                    if(String.valueOf(enterPassword.getPassword()).equals(String.valueOf(enterConfirmPassword.getPassword())))
                    {
                        int id = 0;
                        if(adminTypeButton.isSelected())
                        {
                            id = 100000;
                        }
                        else if(tenantTypeButton.isSelected())
                        {
                            id = 200000;
                        }
                        id += new Random().nextInt(100000);

                        File save = new File("image/" + id);
                        try
                        {
                            Files.copy(image.toPath(), save.toPath());
                            connection.execute("INSERT INTO users VALUES(" +
                                               "'" + id + "'," +
                                               "'" + enterUsername.getText() + "'," +
                                               "'" + String.valueOf(enterPassword.getPassword()) + "'," +
                                               "'" + enterFirstName.getText() + "'," +
                                               "'" + enterMiddleName.getText() + "'," +
                                               "'" + enterLastName.getText() + "'" +
                                               ")");
                            if(tenantTypeButton.isSelected())
                            {
                                connection.execute("INSERT INTO tenants(key) VALUES('" + id + "')");
                            }
                        }
                        catch(IOException ioException)
                        {
                            ioException.printStackTrace();
                        }
                        enterUsername.setText("");
                        enterPassword.setText("");
                        enterConfirmPassword.setText("");
                        enterFirstName.setText("");
                        enterMiddleName.setText("");
                        enterLastName.setText("");
                        accountTypeButton.clearSelection();
                        newPicture.setIcon(new ImageIcon(new ImageIcon("default_pic.png").getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
                        image = null;
                        JOptionPane.showMessageDialog(null, "The user is successfully created!", "User Created", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "The password did not match.", "Password Mismatch", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "The username you entered already exists.", "Username Exists", JOptionPane.ERROR_MESSAGE);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Please fill all the fields.", "Insufficient Data", JOptionPane.ERROR_MESSAGE);
            }
            admin.refresh();
        }
    }
}
