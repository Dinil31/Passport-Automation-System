import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class PassportAutomationSystem extends JFrame implements ActionListener {
    private JButton adminLoginButton, userLoginButton, registerButton;





        public PassportAutomationSystem() {
            setTitle("Passport Automation System");
            setSize(1200, 600);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(4, 1));
            panel.setBackground(new Color(255, 255, 255)); // White background

            // Load image
            ImageIcon passportIcon = new ImageIcon("resources/passport_image.png");
            JLabel imageLabel = new JLabel(passportIcon);
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

            JLabel welcomeLabel = new JLabel("Welcome to the Passport Automation System");
            welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
            welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18)); // Larger and bold font

            adminLoginButton = new JButton("Login as Admin");
            adminLoginButton.addActionListener(this);
            adminLoginButton.setBackground(new Color(65, 105, 225)); // Royal blue color
            adminLoginButton.setForeground(Color.WHITE); // White text color
            adminLoginButton.setFont(new Font("Arial", Font.PLAIN, 18)); // Larger font size
            adminLoginButton.setFocusPainted(false); // Remove focus border
            adminLoginButton.setPreferredSize(new Dimension(200, 50)); // Set button size

            userLoginButton = new JButton("Login as User");
            userLoginButton.addActionListener(this);
            userLoginButton.setBackground (Color.red); // Steel blue color
            userLoginButton.setForeground(Color.WHITE); // White text color
            userLoginButton.setFont(new Font("Arial", Font.PLAIN, 18)); // Larger font size
            userLoginButton.setFocusPainted(false); // Remove focus border
            userLoginButton.setPreferredSize(new Dimension(200, 50)); // Set button size

            registerButton = new JButton("<html><center>Are you a new user?<br>Register to the system from here</center></html>");
            registerButton.addActionListener(this);
            registerButton.setBackground(new Color(50, 205, 50)); // Lime green color
            registerButton.setForeground(Color.WHITE); // White text color
            registerButton.setFont(new Font("Arial", Font.PLAIN, 14)); // Larger font size
            registerButton.setFocusPainted(false); // Remove focus border
            registerButton.setPreferredSize(new Dimension(1200, 100)); // Set button size


            panel.add(imageLabel); // Add image label
            panel.add(welcomeLabel);
            panel.add(adminLoginButton);
            panel.add(userLoginButton);
            panel.add(registerButton);

            add(panel);
            setVisible(true);
        }


        public void actionPerformed(ActionEvent e) {
        if (e.getSource() == adminLoginButton) {
            new AdminLoginForm();
            dispose();
        } else if (e.getSource() == userLoginButton) {
            new UserLoginForm();
            dispose();
        } else if (e.getSource() == registerButton) {
            new RegisterForm();
            dispose();
        }
    }

    public static void main(String[] args) {
        new PassportAutomationSystem();
    }

    public static class AdminDashboardForm {
        public void setVisible(boolean b) {
        }
    }
}

class RegisterForm extends JFrame implements ActionListener {
    private JTextField fullNameField, addressField, emailField, dobField, nicField, nationalityField, genderField;
    private JPasswordField passwordField;
    private JButton registerButton, backButton;


    public RegisterForm() {
        setTitle("Registration Form");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null); // Center the window on the screen

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(10, 2, 10, 10)); // Add gaps between components
        panel.setBackground(new Color(240, 240, 240)); // Light gray background
        panel.setBorder(new EmptyBorder(20, 20, 20, 20)); // Add padding to the panel

        JLabel fullNameLabel = new JLabel("Full Name:");
        fullNameField = new JTextField();
        JLabel addressLabel = new JLabel("Address:");
        addressField = new JTextField();
        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();
        JLabel dobLabel = new JLabel("Date of Birth:");
        dobField = new JTextField();
        JLabel nicLabel = new JLabel("NIC Number:");
        nicField = new JTextField();
        JLabel nationalityLabel = new JLabel("Nationality:");
        nationalityField = new JTextField();
        JLabel genderLabel = new JLabel("Gender:");
        genderField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        registerButton = new JButton("Register");
        registerButton.addActionListener(this);
        registerButton.setBackground(new Color(30, 144, 255)); // Dodger blue color
        registerButton.setForeground(Color.WHITE); // White text color
        registerButton.setFocusPainted(false); // Remove focus border
        backButton = new JButton("Back");
        backButton.addActionListener(this);
        backButton.setBackground(new Color(255, 69, 0)); // Orange-Red color
        backButton.setForeground(Color.WHITE); // White text color
        backButton.setFocusPainted(false); // Remove focus border

        panel.add(fullNameLabel);
        panel.add(fullNameField);
        panel.add(addressLabel);
        panel.add(addressField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(dobLabel);
        panel.add(dobField);
        panel.add(nicLabel);
        panel.add(nicField);
        panel.add(nationalityLabel);
        panel.add(nationalityField);
        panel.add(genderLabel);
        panel.add(genderField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(registerButton);
        panel.add(backButton);

        add(panel);
        getContentPane().setBackground(Color.WHITE); // Set background color of the content pane
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerButton) {
            String fullName = fullNameField.getText();
            String address = addressField.getText();
            String email = emailField.getText();
            String dob = dobField.getText();
            String nic = nicField.getText();
            String nationality = nationalityField.getText();
            String gender = genderField.getText();
            String password = new String(passwordField.getPassword());

            // Validate inputs
            if (fullName.isEmpty() || address.isEmpty() || email.isEmpty() || dob.isEmpty() || nic.isEmpty() || nationality.isEmpty() || gender.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.");
                return;
            }

            // Register user
            registerUser(fullName, address, email, dob, nic, nationality, gender, password);
        } else if (e.getSource() == backButton) {
            new PassportAutomationSystem();
            dispose();
        }
    }

    private void registerUser(String fullName, String address, String email, String dob, String nic, String nationality, String gender, String password) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/passport_automation_system", "root", "");
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO users (full_name, address, email, dob, nic, nationality, gender, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            pstmt.setString(1, fullName);
            pstmt.setString(2, address);
            pstmt.setString(3, email);
            pstmt.setString(4, dob);
            pstmt.setString(5, nic);
            pstmt.setString(6, nationality);
            pstmt.setString(7, gender);
            pstmt.setString(8, password);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Registration successful. You can now login.");
            } else {
                JOptionPane.showMessageDialog(this, "Registration failed. Please try again.");
            }
            pstmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error registering user: " + ex.getMessage());
        }
    }
}




class AdminLoginForm extends JFrame implements ActionListener {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton, backButton;




        public AdminLoginForm() {
            setTitle("Admin Login");
            setSize(800, 400); // Increased size
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Creating a panel with a custom background color
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(3, 2));
            panel.setBackground(new Color(240, 240, 240)); // Light gray color

            JLabel emailLabel = new JLabel("Email:");
            emailField = new JTextField();
            JLabel passwordLabel = new JLabel("Password:");
            passwordField = new JPasswordField();

            loginButton = new JButton("Login");
            loginButton.setBackground(new Color(0, 150, 136)); // Teal color
            loginButton.setForeground(Color.WHITE); // White text color
            loginButton.addActionListener(this);

            backButton = new JButton("Back");
            backButton.setBackground(new Color(244, 67, 54)); // Red color
            backButton.setForeground(Color.WHITE); // White text color
            backButton.addActionListener(this);

            panel.add(emailLabel);
            panel.add(emailField);
            panel.add(passwordLabel);
            panel.add(passwordField);
            panel.add(loginButton);
            panel.add(backButton);

            add(panel);

            setVisible(true);
        }

        // ActionListener implementation and other methods...



    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            if (authenticateAdmin(email, password)) {
                new PassportAutomationSystem.AdminDashboardForm();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid email or password");
            }
        } else if (e.getSource() == backButton) {
            new PassportAutomationSystem();
            dispose();
        }
    }

    private boolean authenticateAdmin(String email, String password) {
        // Implement admin authentication logic here
        // For example, query the database to verify admin credentials
        // Return true if authentication succeeds, false otherwise
        return true; // Placeholder for demonstration
    }
}

class UserLoginForm extends JFrame implements ActionListener {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton, backButton;

    public UserLoginForm() {
        setTitle("User Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        loginButton = new JButton("Login");
        loginButton.addActionListener(this);

        backButton = new JButton("Back");
        backButton.addActionListener(this);

        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(backButton);

        add(panel);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            if (authenticateUser(email, password)) {
                new UserDashboardForm();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid email or password");
            }
        } else if (e.getSource() == backButton) {
            new PassportAutomationSystem();
            dispose();
        }
    }

    private boolean authenticateUser(String email, String password) {
        // Implement user authentication logic here
        // For example, query the database to verify user credentials
        // Return true if authentication succeeds, false otherwise
        return true; // Placeholder for demonstration
    }
}

class UserDashboardForm extends JFrame implements ActionListener {
    private JButton uploadDocumentsButton, viewStatusButton, backButton;

    public UserDashboardForm() {
        setTitle("User Dashboard");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        uploadDocumentsButton = new JButton("Upload Documents");
        uploadDocumentsButton.addActionListener(this);
        viewStatusButton = new JButton("View Status");
        viewStatusButton.addActionListener(this);
        backButton = new JButton("Back");
        backButton.addActionListener(this);

        panel.add(uploadDocumentsButton);
        panel.add(viewStatusButton);
        panel.add(backButton);

        add(panel);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == uploadDocumentsButton) {
            new UploadDocumentsForm();
            dispose();
        } else if (e.getSource() == viewStatusButton) {
            // Show status dialog
            showStatusDialog();
        } else if (e.getSource() == backButton) {
            new UserLoginForm();
            dispose();
        }
    }

    private void showStatusDialog() {
        // Logic to show status dialog
        // Placeholder implementation
        JOptionPane.showMessageDialog(this, "Your application status: Pending");
    }
}

class UploadDocumentsForm extends JFrame implements ActionListener {
    private JButton chooseFileButton, uploadButton, backButton;
    private JLabel filePathLabel;
    private String filePath;

    public UploadDocumentsForm() {
        setTitle("Upload Documents");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        chooseFileButton = new JButton("Choose File");
        chooseFileButton.addActionListener(this);
        uploadButton = new JButton("Upload");
        uploadButton.addActionListener(this);
        backButton = new JButton("Back");
        backButton.addActionListener(this);
        filePathLabel = new JLabel("No file selected");

        panel.add(chooseFileButton);
        panel.add(filePathLabel);
        panel.add(uploadButton);
        panel.add(backButton);

        add(panel);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == chooseFileButton) {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(this);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                filePath = fileChooser.getSelectedFile().getPath();
                filePathLabel.setText(filePath);
            }
        } else if (e.getSource() == uploadButton) {
            if (filePath != null && !filePath.isEmpty()) {
                // Upload file logic
                JOptionPane.showMessageDialog(this, "File uploaded successfully");
            } else {
                JOptionPane.showMessageDialog(this, "Please select a file to upload.");
            }
        } else if (e.getSource() == backButton) {
            new UserDashboardForm();
            dispose();
        }
    }
}

class ViewUsersForm extends JFrame implements ActionListener {
    private JTable table;
    private JScrollPane scrollPane;
    private JButton backButton;

    public ViewUsersForm() {
        setTitle("View Users");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create table model
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Full Name");
        model.addColumn("Address");
        model.addColumn("Email");
        model.addColumn("Date of Birth");
        model.addColumn("NIC Number");
        model.addColumn("Nationality");
        model.addColumn("Gender");

        // Fetch users from database and add them to the table model
        fetchUsers(model);

        // Create table with the model
        table = new JTable(model);
        table.setPreferredScrollableViewportSize(new Dimension(600, 400));
        table.setFillsViewportHeight(true);
        scrollPane = new JScrollPane(table);

        backButton = new JButton("Back");
        backButton.addActionListener(this);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(backButton, BorderLayout.SOUTH);

        add(panel);

        pack();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            new PassportAutomationSystem.AdminDashboardForm();
            dispose();
        }
    }

    private void fetchUsers(DefaultTableModel model) {
        // Implement database query to fetch users
        // Use the retrieved data to populate the table model
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/passport_automation_system", "root", "");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");
            while (rs.next()) {
                String fullName = rs.getString("full_name");
                String address = rs.getString("address");
                String email = rs.getString("email");
                String dob = rs.getString("dob");
                String nic = rs.getString("nic");
                String nationality = rs.getString("nationality");
                String gender = rs.getString("gender");
                model.addRow(new Object[]{fullName, address, email, dob, nic, nationality, gender});
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

class UpdateStatusForm extends JFrame implements ActionListener {
    private JTextField searchField, statusField;
    private JButton searchButton, updateButton, backButton;
    private JTable table;
    private JScrollPane scrollPane;

    public UpdateStatusForm() {
        setTitle("Update Status");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout());
        JLabel searchLabel = new JLabel("Search by NIC:");
        searchField = new JTextField(20);
        searchButton = new JButton("Search");
        searchButton.addActionListener(this);
        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        panel.add(searchPanel, BorderLayout.NORTH);

        // Initialize table
        table = new JTable();
        scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new FlowLayout());
        JLabel statusLabel = new JLabel("New Status:");
        statusField = new JTextField(20);
        updateButton = new JButton("Update");
        updateButton.addActionListener(this);
        statusPanel.add(statusLabel);
        statusPanel.add(statusField);
        statusPanel.add(updateButton);

        panel.add(statusPanel, BorderLayout.SOUTH);

        backButton = new JButton("Back");
        backButton.addActionListener(this);
        panel.add(backButton, BorderLayout.SOUTH);

        add(panel);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            // Perform search
            String nic = searchField.getText();
            fetchUserByNIC(nic);
        } else if (e.getSource() == updateButton) {
            // Update status
            String newStatus = statusField.getText();
            String nic = searchField.getText();
            updateStatus(nic, newStatus);
        } else if (e.getSource() == backButton) {
            new PassportAutomationSystem.AdminDashboardForm();
            dispose();
        }
    }

    private void fetchUserByNIC(String nic) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Full Name");
        model.addColumn("Address");
        model.addColumn("Email");
        model.addColumn("Date of Birth");
        model.addColumn("NIC Number");
        model.addColumn("Nationality");
        model.addColumn("Gender");

        // Fetch user from database based on the NIC
        // Populate table model with the retrieved user
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/passport_automation_system", "root", "");
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM users WHERE nic = ?");
            pstmt.setString(1, nic);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String fullName = rs.getString("full_name");
                String address = rs.getString("address");
                String email = rs.getString("email");
                String dob = rs.getString("dob");
                String nationality = rs.getString("nationality");
                String gender = rs.getString("gender");
                model.addRow(new Object[]{fullName, address, email, dob, nic, nationality, gender});
            } else {
                JOptionPane.showMessageDialog(this, "User with NIC " + nic + " not found.");
            }
            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        table.setModel(model);
    }

    private void updateStatus(String nic, String newStatus) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/passport_automation_system", "root", "");
            PreparedStatement pstmt = conn.prepareStatement("UPDATE users SET status = ? WHERE nic = ?");
            pstmt.setString(1, newStatus);
            pstmt.setString(2, nic);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Status updated successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update status. User with NIC " + nic + " not found.");
            }
            pstmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating status: " + ex.getMessage());
        }
    }
}

