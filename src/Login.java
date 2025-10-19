import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

class Login implements ActionListener {
    // creating frame components reference as global
    JFrame frame;
    JLabel lblLogo;
    JLabel lblWelcome;
    JLabel lblCardNo;
    JLabel lblPin;
    JTextField txtCardNo;
    JPasswordField txtPin;
    JButton btnSignIn;
    JButton btnSignUp;
    JButton btnClear;
    String pin;
    Font myHeadFont = new Font("Georgia", Font.BOLD, 38);
    Font myContentFont = new Font("Arial", Font.BOLD, 25);
    Font txtFont = new Font("Arial", Font.BOLD, 15);
    Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);

    // creating constructor for automated adding the components(creating object)
    Login() {
        // --------JFrame properties-------

        frame = new JFrame("AUTOMATED TILLER MACHINE");
        frame.setLayout(null);
        frame.setSize(800, 480);
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // --------label and textField properties-------
        ImageIcon logoIcon = new ImageIcon(ClassLoader.getSystemResource("logo.jpg"));
        Image scaledImage = logoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledImage);
        lblLogo = new JLabel(resizedIcon);

        lblLogo.setBounds(35, 10, 100, 100);

        lblWelcome = new JLabel("WELCOME TO ATM");
        lblWelcome.setBounds(200, 40, 400, 40);
        lblWelcome.setFont(myHeadFont);

        lblCardNo = new JLabel("Card No: ");
        lblCardNo.setBounds(200, 150, 400, 40);
        lblCardNo.setFont(myContentFont);
        txtCardNo = new JTextField();
        txtCardNo.setBounds(350, 150, 250, 30);
        txtCardNo.setFont(txtFont);

        lblPin = new JLabel("PIN: ");
        lblPin.setBounds(200, 220, 400, 40);
        lblPin.setFont(myContentFont);
        txtPin = new JPasswordField();
        txtPin.setBounds(350, 220, 250, 30);
        txtPin.setFont(txtFont);

        btnSignIn = new JButton("SIGN IN");
        btnSignIn.setBounds(300, 300, 115, 30);
        btnSignIn.setFocusable(false);
        btnSignIn.setForeground(Color.white);
        btnSignIn.setBackground(Color.black);
        btnSignIn.setCursor(handCursor);
        btnSignIn.addActionListener(this);

        btnClear = new JButton("CLEAR");
        btnClear.setBounds(450, 300, 115, 30);
        btnClear.setFocusable(false);
        btnClear.setForeground(Color.white);
        btnClear.setBackground(Color.black);
        btnClear.setCursor(handCursor);
        btnClear.addActionListener(this);

        btnSignUp = new JButton("SIGN UP");
        btnSignUp.setBounds(310, 360, 250, 30);
        btnSignUp.setFocusable(false);
        btnSignUp.setForeground(Color.white);
        btnSignUp.setBackground(Color.black);
        btnSignUp.setCursor(handCursor);
        btnSignUp.addActionListener(this);

        // adding the components on the frame(displaying)
        frame.setVisible(true);
        frame.add(lblLogo);
        frame.add(lblWelcome);
        frame.add(lblCardNo);
        frame.add(lblPin);
        frame.add(txtCardNo);
        frame.add(txtPin);
        frame.add(btnSignIn);
        frame.add(btnClear);
        frame.add(btnSignUp);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cardNoInput = txtCardNo.getText().trim();
        String pinInput = new String(txtPin.getPassword()).trim();
        pin = pinInput;

        if (e.getSource() == btnSignIn && !cardNoInput.isEmpty() && !pinInput.isEmpty()) {
            try {
                Conn c = new Conn(); // Your DB connection class

                String query = "SELECT * FROM signupThree WHERE card_number = ? AND pin = ?";
                try (PreparedStatement pst = c.c.prepareStatement(query)) {
                    pst.setString(1, cardNoInput);
                    pst.setString(2, pinInput);

                    try (ResultSet rs = pst.executeQuery()) {
                        if (rs.next()) {
                            // Login successful
                            JOptionPane.showMessageDialog(null, "Login Successful! ✔");
                            frame.dispose();
                            new Transaction(pinInput);
                        } else {
                            // Login failed
                            JOptionPane.showMessageDialog(null, "Invalid Card Number or PIN ❌");
                            txtPin.setText("");
                            txtCardNo.setText("");
                            txtCardNo.requestFocusInWindow();

                        }
                    }
                }

                c.c.close(); // Optional: connection closing if not using try-with-resources

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error connecting to database!");
                txtPin.setText("");
                txtCardNo.setText("");
                txtCardNo.requestFocusInWindow();

            }
        }

        if (e.getSource() == btnClear) {
            txtPin.setText("");
            txtCardNo.setText("");
            txtCardNo.requestFocusInWindow();

        }

        if (e.getSource() == btnSignUp) {
            frame.dispose();
            new SignUpOne();
        }
    }

    // -------------main function--------start executing....
    public static void main(String[] args) {
        new Login();
    }
}
