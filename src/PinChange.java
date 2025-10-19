import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PinChange implements ActionListener {
    String pin;
    JFrame frame4;
    TextField txtEnterPin;
    TextField txtReEnterPin;
    JLabel lblSelectTransaction;
    JButton btnChange, btnBack;
    Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);

    PinChange(String pin) {
        this.pin = pin;

        // -------- Frame Properties ------------
        frame4 = new JFrame("ATM - Change PIN");
        frame4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame4.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame4.getContentPane().setBackground(Color.WHITE);

        // -------- Load and Scale Image ------------
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("myATM.jpg"));
        Image img = i1.getImage();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Image scaledImg = img.getScaledInstance(screenSize.width, screenSize.height, Image.SCALE_SMOOTH);
        ImageIcon i2 = new ImageIcon(scaledImg);

        // -------- Image Label as Background ------------
        JLabel lblImage = new JLabel(i2);
        lblImage.setLayout(null);

        // -------- Title ------------
        lblSelectTransaction = new JLabel("CHANGE YOUR ATM PIN");
        lblSelectTransaction.setBounds(screenSize.width / 2 - 330, 240, 400, 50);
        lblSelectTransaction.setFont(new Font("Arial", Font.BOLD, 22));
        lblSelectTransaction.setForeground(Color.WHITE);
        lblImage.add(lblSelectTransaction);

        // -------- New PIN ------------
        JLabel lblEnterPin = new JLabel("Enter New PIN:");
        lblEnterPin.setBounds(screenSize.width / 2 - 400, 300, 200, 30);
        lblEnterPin.setFont(new Font("Arial", Font.BOLD, 18));
        lblEnterPin.setForeground(Color.WHITE);
        lblImage.add(lblEnterPin);

        txtEnterPin = new TextField();
        txtEnterPin.setBounds(screenSize.width / 2 - 200, 300, 250, 27);
        txtEnterPin.setFont(new Font("Arial", Font.BOLD, 18));
        txtEnterPin.setForeground(Color.BLUE);
        lblImage.add(txtEnterPin);

        // -------- Re-enter PIN ------------
        JLabel lblReEnterPin = new JLabel("Re-enter New PIN:");
        lblReEnterPin.setBounds(screenSize.width / 2 - 400, 350, 200, 30);
        lblReEnterPin.setFont(new Font("Arial", Font.BOLD, 18));
        lblReEnterPin.setForeground(Color.WHITE);
        lblImage.add(lblReEnterPin);

        txtReEnterPin = new TextField();
        txtReEnterPin.setBounds(screenSize.width / 2 - 200, 350, 250, 27);
        txtReEnterPin.setFont(new Font("Arial", Font.BOLD, 18));
        txtReEnterPin.setForeground(Color.BLUE);
        lblImage.add(txtReEnterPin);

        // -------- Buttons ------------
        btnChange = new JButton("Change PIN");
        btnChange.setBounds(screenSize.width / 2 - 150, 430, 150, 30);
        btnChange.setFont(new Font("Arial", Font.BOLD, 15));
        btnChange.setFocusable(false);
        btnChange.setCursor(handCursor);
        btnChange.addActionListener(this);
        lblImage.add(btnChange);

        btnBack = new JButton("Back");
        btnBack.setBounds(screenSize.width / 2 - 350, 430, 150, 30);
        btnBack.setFont(new Font("Arial", Font.BOLD, 15));
        btnBack.setFocusable(false);
        btnBack.setCursor(handCursor);
        btnBack.addActionListener(this);
        lblImage.add(btnBack);

        // -------- Add image to frame ------------
        frame4.add(lblImage);
        frame4.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String newPin = txtEnterPin.getText().trim();
        String rePin = txtReEnterPin.getText().trim();
        // String oldPin = String.valueOf(pin);

        if (e.getSource() == btnChange) {
            if (newPin.isEmpty() || rePin.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter both PIN fields.");
                return;
            }

            if (!newPin.equals(rePin)) {
                JOptionPane.showMessageDialog(null, "PINs do not match. Try again.");
                return;
            }

            if (newPin.length() != 4) {
                JOptionPane.showMessageDialog(null, "PIN must be exactly 4 digits.");
                return;
            }

            try {
                Conn c1 = new Conn();

                // Update PIN in all relevant tables
                String[] tables = { "bank", "signupthree" };
                for (String table : tables) {
                    String query = "UPDATE " + table + " SET pin = '" + newPin + "' WHERE pin = '" + pin + "'";
                    c1.s.executeUpdate(query);
                }

                JOptionPane.showMessageDialog(null, "PIN Changed Successfully!");
                frame4.dispose();
                new Transaction(pin);

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error while changing PIN: " + ex.getMessage());
            }
        }

        if (e.getSource() == btnBack) {
            frame4.dispose();
            new Transaction(pin);
        }
    }

    public static void main(String[] args) {
        // new PinChange(pin);
    }
}
