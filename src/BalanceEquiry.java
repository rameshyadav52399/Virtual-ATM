import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class BalanceEquiry implements ActionListener {
    JFrame frame4;
    JLabel lblSelectTransaction;
    Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);
    JButton btnBack;
    String pin;

    BalanceEquiry(String pin) {
        this.pin = pin;

        // -------- Frame Properties ------------
        frame4 = new JFrame("Balance Enquiry");
        frame4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame4.setExtendedState(JFrame.MAXIMIZED_BOTH); // Full screen
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

        // -------- Title Label ------------
        lblSelectTransaction = new JLabel("Your Current Balance:");
        lblSelectTransaction.setBounds(screenSize.width / 2 - 350, 250, 400, 50);
        lblSelectTransaction.setFont(new Font("Arial", Font.BOLD, 22));
        lblSelectTransaction.setForeground(Color.WHITE);
        lblImage.add(lblSelectTransaction);

        // -------- Back Button ------------
        btnBack = new JButton("Back");
        btnBack.setBounds(screenSize.width / 2 - 100, 430, 150, 30);
        btnBack.setFont(new Font("Arial", Font.BOLD, 17));
        btnBack.setFocusable(false);
        btnBack.setCursor(handCursor);
        btnBack.addActionListener(this);
        lblImage.add(btnBack);

        // -------- Add image to frame ------------
        frame4.add(lblImage);
        frame4.setVisible(true);

        // Fetch balance and show on screen
        showBalance();
    }

    // Function to fetch and show balance
    void showBalance() {
        // myPin = String.valueOf(pin);
        int balance = 0;
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM bank WHERE pin = '" + pin + "'");
            while (rs.next()) {
                if (rs.getString("type").equalsIgnoreCase("Deposit")) {
                    balance += Integer.parseInt(rs.getString("amount"));
                } else {
                    balance -= Integer.parseInt(rs.getString("amount"));
                }
            }

            lblSelectTransaction.setText("Your Current Balance: ₹ " + balance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnBack) {
            frame4.dispose();
            new Transaction(pin); // Go back to Transaction page
        }
    }

    public static void main(String[] args) {
        // new BalanceEquiry(100);
    }
}
