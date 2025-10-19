import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class MiniStatement extends JFrame implements ActionListener {

    JButton b1;
    JLabel l1;
    String pin;

    MiniStatement(String pin) {
        this.pin = pin;
        super.setTitle("Mini Statement");

        getContentPane().setBackground(Color.WHITE);
        setSize(400, 600);
        setLocation(900, 100);
        setLayout(null);

        l1 = new JLabel();
        l1.setBounds(20, 140, 400, 200);
        add(l1);

        JLabel l2 = new JLabel("BANK OF BARODA");
        l2.setFont(new Font("System", Font.BOLD, 16));
        l2.setBounds(120, 20, 200, 20);
        add(l2);

        JLabel l3 = new JLabel();
        l3.setBounds(20, 80, 350, 20);
        add(l3);

        JLabel l4 = new JLabel();
        l4.setBounds(20, 400, 350, 20);
        add(l4);

        // ----- Fetch Card Number -----
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM signupThree WHERE pin = '" + pin + "'");
            while (rs.next()) {
                String cardNo = rs.getString("cardno");
                l3.setText("Card Number: " + cardNo.substring(0, 4) + "XXXXXXXX" + cardNo.substring(12));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // ----- Fetch Transaction History & Balance -----
        try {
            int balance = 0;
            Conn c1 = new Conn();
            ResultSet rs = c1.s.executeQuery("SELECT * FROM bank WHERE pin = '" + pin + "'");
            StringBuilder sb = new StringBuilder("<html>");
            while (rs.next()) {
                sb.append(rs.getString("date"))
                        .append("&nbsp;&nbsp;&nbsp;&nbsp;")
                        .append(rs.getString("type"))
                        .append("&nbsp;&nbsp;&nbsp;&nbsp;")
                        .append(rs.getString("amount"))
                        .append("<br><br>");
                if (rs.getString("type").equals("Deposit")) {
                    balance += Integer.parseInt(rs.getString("amount"));
                } else {
                    balance -= Integer.parseInt(rs.getString("amount"));
                }
            }
            sb.append("</html>");
            l1.setText(sb.toString());
            l4.setText("Your total Balance is Rs " + balance);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // ----- Exit Button -----
        b1 = new JButton("Exit");
        b1.setBounds(20, 500, 100, 25);
        b1.addActionListener(this);
        add(b1);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==b1){
            this.setVisible(false);

        }
        // this.setVisible(false);
    }

    public static void main(String[] args) {
        // new MiniStatement("9399"); 
    }
}
