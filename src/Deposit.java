import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class Deposit implements ActionListener {
String pin;
    JFrame frame4;
    TextField txtEnterAmt;
    JLabel lblSelectTransaction;
    JButton btnDeposit;
    JButton btnBack;
    Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);

    Deposit(String pin) {
        this.pin=pin;
        // -------- Frame Properties ------------
        frame4 = new JFrame("Welcome to ATM");
        frame4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame4.setExtendedState(JFrame.MAXIMIZED_BOTH); // Full screen
        frame4.getContentPane().setBackground(Color.WHITE);

        // -------- Load and Scale Image ------------
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("myATM.jpg"));
        Image img = i1.getImage();

        // Get screen size dynamically
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Image scaledImg = img.getScaledInstance(screenSize.width, screenSize.height, Image.SCALE_SMOOTH);
        ImageIcon i2 = new ImageIcon(scaledImg);

        // -------- Image Label as Background ------------
        JLabel lblImage = new JLabel(i2);
        lblImage.setLayout(null); // Allow absolute positioning

        // -------- Transaction Label (on top of image) ------------
        lblSelectTransaction = new JLabel("Enter the amount you want to deposit");
        lblSelectTransaction.setBounds(screenSize.width / 2 - 330, 250, 400, 50);
        lblSelectTransaction.setFont(new Font("Arial", Font.BOLD, 18));
        lblSelectTransaction.setForeground(Color.WHITE);
        lblImage.add(lblSelectTransaction);

        // -------- Transaction textfield (on top of image) ------------
        txtEnterAmt = new TextField();
        txtEnterAmt.setBounds(screenSize.width / 2 - 330, 330, 330, 27);
        txtEnterAmt.setFont(new Font("Arial", Font.BOLD, 18));
        txtEnterAmt.setForeground(Color.BLUE);
        lblImage.add(txtEnterAmt);

        // -------- Transaction Button (on top of image) ------------
        btnBack = new JButton("Cancel");
        btnBack.setBounds(screenSize.width / 2 - 350, 410, 150, 30);
        btnBack.setFont(new Font("Arial", Font.BOLD, 15));
        btnBack.setFocusable(false);
        btnBack.setCursor(handCursor);
        lblImage.add(btnBack);
        btnBack.addActionListener(this);

        // -------- Transaction Button (on top of image) ------------
        btnDeposit = new JButton("Deposit");
        btnDeposit.setBounds(screenSize.width / 2 - 150, 410, 150, 30);
        btnDeposit.setFont(new Font("Arial", Font.BOLD, 15));
        btnDeposit.setFocusable(false);
        btnDeposit.setCursor(handCursor);
        lblImage.add(btnDeposit);
        btnDeposit.addActionListener(this);

        // -------- Add image label to frame ------------
        frame4.add(lblImage);
        frame4.setVisible(true);
    }

    public static void main(String[] args) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String amount = txtEnterAmt.getText().trim();
        String atm_pin=String.valueOf(pin);
        LocalDate localDate = LocalDate.now();
        String date = localDate.toString();

        // Deposit Button Logic
        if (e.getSource() == btnDeposit) {
            if (!amount.isEmpty()) {
                try {
                    Conn c1 = new Conn();
                    String query = "INSERT INTO bank VALUES('" + atm_pin + "', '" + date + "', 'Deposit', '" + amount
                            + "')";
                    c1.s.executeUpdate(query);

                    JOptionPane.showMessageDialog(null, "Rs. " + amount + " Deposited Successfully");
                    frame4.setVisible(false);
                    new Transaction(pin);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error while depositing amount: " + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please Enter Amount");
            }
        }

        // Back Button Logic
        if (e.getSource() == btnBack) {
            frame4.dispose();
            new Transaction(pin);
        }
    }
}
