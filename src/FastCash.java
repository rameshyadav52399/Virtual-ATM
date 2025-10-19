// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import java.sql.ResultSet;
// import java.time.LocalDate;

// public class FastCash implements ActionListener {
//     JFrame frame4;
//     JLabel lblSelectTransaction;
//     JButton btnFashCash100;
//     JButton btnFashCash1000;
//     JButton btnFashCash5000;
//     JButton btnFashCash500;
//     JButton btnFashCash2000;
//     JButton btnFashCash10000;
//     JButton btnBack;
//     Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);
//     long pin;
//     String myPin;

//     FastCash(long pin) {
//         this.pin = pin;
//         // -------- Frame Properties ------------
//         frame4 = new JFrame("Welcome to ATM");
//         frame4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         frame4.setExtendedState(JFrame.MAXIMIZED_BOTH); // Full screen
//         frame4.getContentPane().setBackground(Color.WHITE);

//         // -------- Load and Scale Image ------------
//         ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("myATM.jpg"));
//         Image img = i1.getImage();

//         // Get screen size dynamically
//         Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//         Image scaledImg = img.getScaledInstance(screenSize.width, screenSize.height, Image.SCALE_SMOOTH);
//         ImageIcon i2 = new ImageIcon(scaledImg);

//         // -------- Image Label as Background ------------
//         JLabel lblImage = new JLabel(i2);
//         lblImage.setLayout(null); // Allow absolute positioning

//         // -------- Transaction Label (on top of image) ------------
//         lblSelectTransaction = new JLabel("Please select your Transaction");
//         lblSelectTransaction.setBounds(screenSize.width / 2 - 330, 250, 400, 50);
//         lblSelectTransaction.setFont(new Font("Arial", Font.BOLD, 20));
//         lblSelectTransaction.setForeground(Color.WHITE);
//         lblImage.add(lblSelectTransaction);

//         // -------- Transaction Button (on top of image) ------------
//         btnFashCash100 = new JButton("Rs 100");
//         btnFashCash100.setBounds(screenSize.width / 2 - 430, 350, 150, 30);
//         btnFashCash100.setFont(new Font("Arial", Font.BOLD, 17));
//         btnFashCash100.setFocusable(false);
//         btnFashCash100.setCursor(handCursor);
//         lblImage.add(btnFashCash100);
//         btnFashCash100.addActionListener(this);

//         // -------- Transaction Button (on top of image) ------------
//         btnFashCash1000 = new JButton("Rs 1000");
//         btnFashCash1000.setBounds(screenSize.width / 2 - 430, 390, 150, 30);
//         btnFashCash1000.setFont(new Font("Arial", Font.BOLD, 17));
//         btnFashCash1000.setFocusable(false);
//         btnFashCash1000.setCursor(handCursor);
//         lblImage.add(btnFashCash1000);

//         // -------- Transaction Button (on top of image) ------------
//         btnFashCash5000 = new JButton("Rs 5,000");
//         btnFashCash5000.setBounds(screenSize.width / 2 - 430, 430, 150, 30);
//         btnFashCash5000.setFont(new Font("Arial", Font.BOLD, 17));
//         btnFashCash5000.setFocusable(false);
//         btnFashCash5000.setCursor(handCursor);
//         lblImage.add(btnFashCash5000);

//         // -------- Transaction Button (on top of image) ------------
//         btnFashCash500 = new JButton("Rs 500");
//         btnFashCash500.setBounds(screenSize.width / 2 - 70, 350, 150, 30);
//         btnFashCash500.setFont(new Font("Arial", Font.BOLD, 15));
//         btnFashCash500.setFocusable(false);
//         btnFashCash5000.setCursor(handCursor);
//         lblImage.add(btnFashCash500);

//         // -------- Transaction Button (on top of image) ------------
//         btnFashCash2000 = new JButton("Rs 2,000");
//         btnFashCash2000.setBounds(screenSize.width / 2 - 70, 390, 150, 30);
//         btnFashCash2000.setFont(new Font("Arial", Font.BOLD, 17));
//         btnFashCash2000.setFocusable(false);
//         btnFashCash2000.setCursor(handCursor);
//         lblImage.add(btnFashCash2000);

//         // -------- Transaction Button (on top of image) ------------
//         btnFashCash10000 = new JButton("Rs 10,000");
//         btnFashCash10000.setBounds(screenSize.width / 2 - 70, 430, 150, 30);
//         btnFashCash10000.setFont(new Font("Arial", Font.BOLD, 15));
//         btnFashCash10000.setFocusable(false);
//         btnFashCash10000.setCursor(handCursor);
//         lblImage.add(btnFashCash10000);

//         // -------- Transaction Button (on top of image) ------------
//         btnBack = new JButton("Back");
//         btnBack.setBounds(screenSize.width / 2 - 250, 430, 150, 30);
//         btnBack.setFont(new Font("Arial", Font.BOLD, 17));
//         btnBack.setFocusable(false);
//         btnBack.setCursor(handCursor);
//         btnBack.addActionListener(this);
//         lblImage.add(btnBack);

//         // -------- Add image label to frame ------------
//         frame4.add(lblImage);
//         frame4.setVisible(true);
//     }

//     public static void main(String[] args) {

//     }

//     public void actionPerformed(ActionEvent ae) {
//         myPin = String.valueOf(pin);
//         LocalDate localDate = LocalDate.now();
//         String date = localDate.toString();
//         try {
//             String amount = ((JButton) ae.getSource()).getText().substring(3); // k
//             Conn c = new Conn();
//             ResultSet rs = c.s.executeQuery("select * from bank where pin = '" + myPin + "'");
//             int balance = 0;
//             while (rs.next()) {
//                 if (rs.getString("type").equals("Deposit")) {
//                     balance += Integer.parseInt(rs.getString("amount"));
//                 } else {
//                     balance -= Integer.parseInt(rs.getString("amount"));
//                 }
//             }

//             if (ae.getSource() != btnBack && balance < Integer.parseInt(amount)) {
//                 JOptionPane.showMessageDialog(null, "Insuffient Balance");
//                 return;
//             }

//             if (ae.getSource() == btnBack) {
//                 // this.setVisible(false);
//                 // new Transactions(pin).setVisible(true);
//             } else {
//                 c.s.executeUpdate(
//                         "insert into bank values('" + myPin + "', '" + date + "', 'Withdrawl', '" + amount + "')");
//                 JOptionPane.showMessageDialog(null, "Rs. " + amount + " Debited Successfully");

//                 // setVisible(false);
//                 // new Transactions(pin).setVisible(true);
//             }
//         } catch (Exception e) {
//             e.printStackTrace();
//         }

//     }
// }
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.time.LocalDate;

public class FastCash implements ActionListener {
    JFrame frame4;
    JLabel lblSelectTransaction;
    JButton btnFashCash100, btnFashCash1000, btnFashCash5000, btnFashCash500, btnFashCash2000, btnFashCash10000, btnBack;
    Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);
    String pin;
    

    FastCash(String pin) {
        this.pin = pin;

        // -------- Frame Properties ------------
        frame4 = new JFrame("Welcome to ATM");
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
        lblSelectTransaction = new JLabel("Select Withdrawal Amount");
        lblSelectTransaction.setBounds(screenSize.width / 2 - 330, 250, 400, 50);
        lblSelectTransaction.setFont(new Font("Arial", Font.BOLD, 20));
        lblSelectTransaction.setForeground(Color.WHITE);
        lblImage.add(lblSelectTransaction);

        // -------- Buttons ------------
        btnFashCash100 = createButton("Rs 100", screenSize.width / 2 - 430, 350, lblImage);
        btnFashCash1000 = createButton("Rs 1000", screenSize.width / 2 - 430, 390, lblImage);
        btnFashCash5000 = createButton("Rs 5000", screenSize.width / 2 - 430, 430, lblImage);
        btnFashCash500 = createButton("Rs 500", screenSize.width / 2 - 70, 350, lblImage);
        btnFashCash2000 = createButton("Rs 2000", screenSize.width / 2 - 70, 390, lblImage);
        btnFashCash10000 = createButton("Rs 10000", screenSize.width / 2 - 70, 430, lblImage);

        // -------- Back Button ------------
        btnBack = new JButton("Back");
        btnBack.setBounds(screenSize.width / 2 - 250, 430, 150, 30);
        btnBack.setFont(new Font("Arial", Font.BOLD, 17));
        btnBack.setFocusable(false);
        btnBack.setCursor(handCursor);
        btnBack.addActionListener(this);
        lblImage.add(btnBack);

        // -------- Add image to frame ------------
        frame4.add(lblImage);
        frame4.setVisible(true);
    }

    // Helper method to reduce duplicate button code
    private JButton createButton(String text, int x, int y, JLabel parent) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 150, 30);
        button.setFont(new Font("Arial", Font.BOLD, 17));
        button.setFocusable(false);
        button.setCursor(handCursor);
        button.addActionListener(this);
        parent.add(button);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        // myPin = String.valueOf(pin);
        String date = LocalDate.now().toString();

        try {
            if (ae.getSource() == btnBack) {
                frame4.dispose();
                new Transaction(pin); // Go back to Transaction page
                return;
            }

            String amount = ((JButton) ae.getSource()).getText().replace("Rs ", "").replace(",", "").trim();

            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM bank WHERE pin = '" + pin + "'");

            int balance = 0;
            while (rs.next()) {
                if (rs.getString("type").equals("Deposit")) {
                    balance += Integer.parseInt(rs.getString("amount"));
                } else {
                    balance -= Integer.parseInt(rs.getString("amount"));
                }
            }

            int withdrawAmt = Integer.parseInt(amount);
            if (balance < withdrawAmt) {
                JOptionPane.showMessageDialog(null, "Insufficient Balance");
                return;
            }

            c.s.executeUpdate("INSERT INTO bank VALUES('" + pin + "', '" + date + "', 'Withdraw', '" + amount + "')");
            JOptionPane.showMessageDialog(null, "Rs. " + amount + " Debited Successfully");

            frame4.dispose();
            new Transaction(pin);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // new FastCash(1234); 
    }
}
