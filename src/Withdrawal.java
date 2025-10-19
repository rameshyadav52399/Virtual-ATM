import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.time.LocalDate;

public class Withdrawal implements ActionListener {
    String pin;
    JFrame frame4;
    TextField txtEnterAmt;
    JLabel lblSelectTransaction;
    JButton btnWithdrawal;
    JButton btnBack;
    Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);

    Withdrawal(String pin) {
        this.pin = pin;

        // -------- Frame Properties ------------
        frame4 = new JFrame("ATM - Withdrawal");
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

        // -------- Label ------------
        lblSelectTransaction = new JLabel("Enter the amount you want to Withdraw");
        lblSelectTransaction.setBounds(screenSize.width / 2 - 350, 250, 400, 40);
        lblSelectTransaction.setFont(new Font("Arial", Font.BOLD, 20));
        lblSelectTransaction.setForeground(Color.WHITE);
        lblImage.add(lblSelectTransaction);

        // -------- Amount Text Field ------------
        txtEnterAmt = new TextField();
        txtEnterAmt.setBounds(screenSize.width / 2 - 330, 330, 330, 30);
        txtEnterAmt.setFont(new Font("Arial", Font.BOLD, 18));
        txtEnterAmt.setForeground(Color.BLUE);
        lblImage.add(txtEnterAmt);

        // -------- Cancel Button ------------
        btnBack = new JButton("Cancel");
        btnBack.setBounds(screenSize.width / 2 - 350, 410, 150, 30);
        btnBack.setFont(new Font("Arial", Font.BOLD, 15));
        btnBack.setFocusable(false);
        btnBack.setCursor(handCursor);
        btnBack.addActionListener(this);
        lblImage.add(btnBack);

        // -------- Withdrawal Button ------------
        btnWithdrawal = new JButton("Withdraw");
        btnWithdrawal.setBounds(screenSize.width / 2 - 150, 410, 150, 30);
        btnWithdrawal.setFont(new Font("Arial", Font.BOLD, 15));
        btnWithdrawal.setFocusable(false);
        btnWithdrawal.setCursor(handCursor);
        btnWithdrawal.addActionListener(this);
        lblImage.add(btnWithdrawal);

        // -------- Add Background ------------
        frame4.add(lblImage);
        frame4.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String amount = txtEnterAmt.getText().trim();
        // String atm_pin = String.valueOf(pin);
        String date = LocalDate.now().toString();

        // Withdrawal Logic
        if (e.getSource() == btnWithdrawal) {
            if (amount.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter an amount.");
                return;
            }

            try {
                int withdrawAmt = Integer.parseInt(amount);

                // Check balance before withdrawal
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery("SELECT * FROM bank WHERE pin = '" + pin + "'");

                int balance = 0;
                while (rs.next()) {
                    if (rs.getString("type").equalsIgnoreCase("Deposit")) {
                        balance += Integer.parseInt(rs.getString("amount"));
                    } else {
                        balance -= Integer.parseInt(rs.getString("amount"));
                    }
                }

                if (withdrawAmt > balance) {
                    JOptionPane.showMessageDialog(null, "Insufficient Balance!");
                    return;
                }

                String query = "INSERT INTO bank VALUES('" + pin + "', '" + date + "', 'Withdrawal', '" + withdrawAmt
                        + "')";
                c.s.executeUpdate(query);

                JOptionPane.showMessageDialog(null, "Rs. " + withdrawAmt + " Withdrawn Successfully!");
                frame4.dispose();
                new Transaction(pin); // Go back to transaction screen
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid numeric amount.");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error while withdrawing: " + ex.getMessage());
            }
        }

        if (e.getSource() == btnBack) {
            frame4.dispose();
            new Transaction(pin);
        }
    }

    public static void main(String[] args) {
        // new Withdrawal(1234);
    }
}
