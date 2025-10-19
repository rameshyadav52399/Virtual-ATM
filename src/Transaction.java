import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Transaction implements ActionListener {
    JFrame frame4;
    JLabel lblSelectTransaction;
    JButton btnDeposit;
    JButton btnFastCash;
    JButton btnPinChange;
    JButton btnCashWithdraw;
    JButton btnMiniStatement;
    JButton btnBalanceEquiry;
    JButton btnExit;
    Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);
    String pin;

    Transaction(String pin) {
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
        lblSelectTransaction = new JLabel("Please select a Transaction");
        lblSelectTransaction.setBounds(screenSize.width / 2 - 330, 250, 400, 50);
        lblSelectTransaction.setFont(new Font("Arial", Font.BOLD, 20));
        lblSelectTransaction.setForeground(Color.WHITE);
        lblImage.add(lblSelectTransaction);

        // -------- Transaction Button (on top of image) ------------
        btnDeposit = new JButton("Deposit");
        btnDeposit.setBounds(screenSize.width / 2 - 430, 350, 150, 30);
        btnDeposit.setFont(new Font("Arial", Font.BOLD, 17));
        btnDeposit.setFocusable(false);
        btnDeposit.setCursor(handCursor);
        lblImage.add(btnDeposit);
        btnDeposit.addActionListener(this);

        // -------- Transaction Button (on top of image) ------------
        btnFastCash = new JButton("Fast Cash");
        btnFastCash.setBounds(screenSize.width / 2 - 430, 390, 150, 30);
        btnFastCash.setFont(new Font("Arial", Font.BOLD, 17));
        btnFastCash.setFocusable(false);
        btnFastCash.setCursor(handCursor);
        lblImage.add(btnFastCash);
        btnFastCash.addActionListener(this);

        // -------- Transaction Button (on top of image) ------------
        btnPinChange = new JButton("Pin Chnage");
        btnPinChange.setBounds(screenSize.width / 2 - 430, 430, 150, 30);
        btnPinChange.setFont(new Font("Arial", Font.BOLD, 17));
        btnPinChange.setFocusable(false);
        btnPinChange.setCursor(handCursor);
        lblImage.add(btnPinChange);
        btnPinChange.addActionListener(this);

        // -------- Transaction Button (on top of image) ------------
        btnCashWithdraw = new JButton("Cash Withdraw");
        btnCashWithdraw.setBounds(screenSize.width / 2 - 70, 350, 150, 30);
        btnCashWithdraw.setFont(new Font("Arial", Font.BOLD, 15));
        btnCashWithdraw.setFocusable(false);
        btnCashWithdraw.setCursor(handCursor);
        lblImage.add(btnCashWithdraw);
        btnCashWithdraw.addActionListener(this);

        // -------- Transaction Button (on top of image) ------------
        btnMiniStatement = new JButton("Mini Statement");
        btnMiniStatement.setBounds(screenSize.width / 2 - 70, 390, 150, 30);
        btnMiniStatement.setFont(new Font("Arial", Font.BOLD, 17));
        btnMiniStatement.setFocusable(false);
        btnMiniStatement.setCursor(handCursor);
        lblImage.add(btnMiniStatement);
        btnMiniStatement.addActionListener(this);

        // -------- Transaction Button (on top of image) ------------
        btnBalanceEquiry = new JButton("Balance Equiry");
        btnBalanceEquiry.setBounds(screenSize.width / 2 - 70, 430, 150, 30);
        btnBalanceEquiry.setFont(new Font("Arial", Font.BOLD, 15));
        btnBalanceEquiry.setFocusable(false);
        btnBalanceEquiry.setCursor(handCursor);
        lblImage.add(btnBalanceEquiry);
        btnBalanceEquiry.addActionListener(this);

        // -------- Transaction Button (on top of image) ------------
        btnExit = new JButton("EXIT");
        btnExit.setBounds(screenSize.width / 2 - 250, 430, 150, 30);
        btnExit.setFont(new Font("Arial", Font.BOLD, 17));
        btnExit.setFocusable(false);
        btnExit.setCursor(handCursor);
        btnExit.addActionListener(this);
        lblImage.add(btnExit);

        // -------- Add image label to frame ------------
        frame4.add(lblImage);
        frame4.setVisible(true);
    }

    public static void main(String[] args) {
        // SwingUtilities.invokeLater(Transaction::new);
        // new Transaction("1817");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==btnDeposit){
            new Deposit(pin);
        }
        if(e.getSource()==btnFastCash){
            new FastCash(pin);
        }
        if(e.getSource()==btnCashWithdraw){
            new Withdrawal(pin);
        }
        if(e.getSource()==btnBalanceEquiry){            
           new BalanceEquiry(pin);
        }
        if(e.getSource()==btnMiniStatement){            
           new MiniStatement(pin);
        }
        if(e.getSource()==btnPinChange){
            new PinChange(pin);
        }

        if (e.getSource() == btnExit) {
            int confirm = JOptionPane.showConfirmDialog(frame4,
                    "Are you sure you want to exit?",
                    "Exit Confirmation",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }

    }
}
