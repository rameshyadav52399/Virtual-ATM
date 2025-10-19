
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.util.Random;

public class SignUpThree implements ActionListener {
    private static final Random random = new Random();
    final StringBuilder selectedServices = new StringBuilder();
    StringBuilder cardNumber = new StringBuilder();
    String pin;

    JFrame frame3;
    JLabel lblLogo;
    JLabel lblFormNo;
    JLabel lblAccountType;
    JLabel lblHeader;
    JRadioButton rdoSavingAccount;
    JRadioButton rdoCurrentAccount;
    JRadioButton rdoFDAccount;
    JRadioButton rdoRDAccount;
    ButtonGroup accountGroup;
    JLabel lblCardNo;
    JLabel lblCardNoInfo;
    JLabel lblCardNoData;
    JLabel lblCardNoDataInfo;
    JLabel lblPinNo;
    JLabel lblPinNoInfo;
    JLabel lblPinNoData;
    JLabel lblServiceRequired;
    JCheckBox chBoxAtmCard;
    JCheckBox chBoxInternetBanking;
    JCheckBox chBoxMobileBanking;
    JCheckBox chBoxEmailAlert;
    JCheckBox chBoxCheckBook;
    JCheckBox chBoxEStatement;
    JCheckBox chBoxDeclaration;
    JButton btnSubmit;
    JButton btnCancel;
    long form_number;

    Font headerFontStyle = new Font("Georgia", Font.BOLD, 36);
    Font subHeaderFontStyle = new Font("Arial", Font.BOLD, 25);
    Font labelsFontStyle = new Font("Arial", Font.BOLD, 20);
    Font btnFontStyle = new Font("Arial", Font.BOLD, 15);
    Font rdoFontStyle = new Font("Arial", Font.BOLD, 15);

    SignUpThree(long randomFormNo) {
        this.form_number = randomFormNo;
        // --------Frame Properties------------
        frame3 = new JFrame("NEW ACCOUNT APPLICATION FORM - PAGE:3");
        frame3.setLayout(null);
        frame3.setSize(850, 800);
        frame3.setLocationRelativeTo(null);
        frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame3.getContentPane().setBackground(Color.WHITE);

        // ---------Labels and other components properties--------
        ImageIcon logoIcon = new ImageIcon(ClassLoader.getSystemResource("logo.jpg"));
        Image scaledImage = logoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon resizedImage = new ImageIcon(scaledImage);
        lblLogo = new JLabel(resizedImage);
        lblLogo.setBounds(70, 25, 100, 100);

        lblHeader = new JLabel("Page 3: Account Details");
        lblHeader.setFont(headerFontStyle);
        lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
        lblHeader.setBounds(200, 40, 500, 70);

        lblFormNo = new JLabel("Form No: " + randomFormNo);
        lblFormNo.setFont(subHeaderFontStyle);
        lblFormNo.setHorizontalAlignment(SwingConstants.CENTER);
        lblFormNo.setBounds(500, 10, 300, 40);

        lblAccountType = new JLabel("Account Type: ");
        lblAccountType.setFont(labelsFontStyle);
        lblAccountType.setBounds(100, 150, 200, 40);

        rdoSavingAccount = new JRadioButton("Saving");
        rdoSavingAccount.setBounds(100, 200, 150, 30);
        rdoSavingAccount.setFocusable(false);
        rdoSavingAccount.setBackground(Color.white);
        rdoSavingAccount.setFont(rdoFontStyle);

        rdoCurrentAccount = new JRadioButton("Current");
        rdoCurrentAccount.setBounds(100, 250, 150, 30);
        rdoCurrentAccount.setFocusable(false);
        rdoCurrentAccount.setBackground(Color.white);
        rdoCurrentAccount.setFont(rdoFontStyle);

        rdoFDAccount = new JRadioButton("Fixed Deposit Account");
        rdoFDAccount.setBounds(250, 200, 250, 30);
        rdoFDAccount.setFocusable(false);
        rdoFDAccount.setBackground(Color.white);
        rdoFDAccount.setFont(rdoFontStyle);

        rdoRDAccount = new JRadioButton("Recurring Deposit Account");
        rdoRDAccount.setBounds(250, 250, 250, 30);
        rdoRDAccount.setFocusable(false);
        rdoRDAccount.setBackground(Color.white);
        rdoRDAccount.setFont(rdoFontStyle);

        int first = 1 + random.nextInt(9); // first digit 1–9
        cardNumber.append(first);

        for (int i = 0; i < 15; i++) {
            cardNumber.append(random.nextInt(10)); // next 15 digits
        }

        // Convert to string
        String card = cardNumber.toString();

        // Get last 4 digits
        String last4 = card.substring(card.length() - 4);

        lblCardNo = new JLabel("Card No: ");
        lblCardNo.setBounds(100, 300, 100, 30);
        lblCardNo.setFont(labelsFontStyle);

        lblCardNoInfo = new JLabel("(Your 16-digit Card number)");
        lblCardNoInfo.setBounds(100, 330, 200, 30);
        lblCardNoInfo.setFont(rdoFontStyle);

        lblCardNoData = new JLabel("XXXX-XXXX-XXXX-" + last4);
        lblCardNoData.setBounds(370, 300, 450, 30);
        lblCardNoData.setFont(labelsFontStyle);

        lblCardNoDataInfo = new JLabel("(It World appear on ATM Card/Cheque Book  and Statements)");
        lblCardNoDataInfo.setBounds(370, 330, 450, 30);
        lblCardNoDataInfo.setFont(rdoFontStyle);
        // generating pin
        StringBuilder sb = new StringBuilder(4);
        for (int i = 0; i < 4; i++) {
            sb.append(random.nextInt(10)); // 0-9
        }
        pin = sb.toString();

        lblPinNo = new JLabel("PIN:");
        lblPinNo.setBounds(100, 390, 450, 30);
        lblPinNo.setFont(labelsFontStyle);

        lblPinNoInfo = new JLabel("(4-digit password)");
        lblPinNoInfo.setBounds(100, 430, 450, 30);
        lblPinNoInfo.setFont(rdoFontStyle);

        lblPinNoData = new JLabel("XXXX");
        lblPinNoData.setBounds(370, 390, 450, 30);
        lblPinNoData.setFont(labelsFontStyle);

        lblServiceRequired = new JLabel("Service Required:");
        lblServiceRequired.setBounds(100, 470, 450, 30);
        lblServiceRequired.setFont(labelsFontStyle);

        chBoxAtmCard = new JCheckBox("ATM Card");
        chBoxAtmCard.setBounds(100, 500, 100, 30);
        chBoxAtmCard.setBackground(Color.WHITE);
        chBoxAtmCard.setFocusable(false);

        chBoxInternetBanking = new JCheckBox("Internet Banking");
        chBoxInternetBanking.setBounds(300, 500, 200, 30);
        chBoxInternetBanking.setBackground(Color.WHITE);
        chBoxInternetBanking.setFocusable(false);

        chBoxMobileBanking = new JCheckBox("Mobile Banking");
        chBoxMobileBanking.setBounds(100, 540, 200, 30);
        chBoxMobileBanking.setBackground(Color.WHITE);
        chBoxMobileBanking.setFocusable(false);

        chBoxEmailAlert = new JCheckBox("Email Alert");
        chBoxEmailAlert.setBounds(300, 540, 100, 30);
        chBoxEmailAlert.setBackground(Color.WHITE);
        chBoxEmailAlert.setFocusable(false);

        chBoxCheckBook = new JCheckBox("Cheque Book");
        chBoxCheckBook.setBounds(100, 580, 100, 30);
        chBoxCheckBook.setBackground(Color.WHITE);
        chBoxCheckBook.setFocusable(false);

        chBoxEStatement = new JCheckBox("E- Statement");
        chBoxEStatement.setBounds(300, 580, 100, 30);
        chBoxEStatement.setBackground(Color.WHITE);
        chBoxEStatement.setFocusable(false);

        chBoxDeclaration = new JCheckBox("I hereby declares that the entered details correct to best of my knowledge");
        chBoxDeclaration.setBounds(100, 620, 700, 30);
        chBoxDeclaration.setBackground(Color.WHITE);
        chBoxDeclaration.setFocusable(false);

        btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(200, 660, 100, 30);
        btnSubmit.setBackground(Color.black);
        btnSubmit.setForeground(Color.white);
        btnSubmit.setFocusable(false);
        btnSubmit.setFont(btnFontStyle);
        btnSubmit.addActionListener(this);

        btnCancel = new JButton("Cancel");
        btnCancel.setBounds(380, 660, 100, 30);
        btnCancel.setBackground(Color.black);
        btnCancel.setForeground(Color.white);
        btnCancel.setFocusable(false);
        btnCancel.setFont(btnFontStyle);
        btnCancel.addActionListener(this);

        // ------------- Group the radio buttons so only one can be selected--------
        accountGroup = new ButtonGroup();
        accountGroup.add(rdoCurrentAccount);
        accountGroup.add(rdoSavingAccount);
        accountGroup.add(rdoFDAccount);
        accountGroup.add(rdoRDAccount);

        // Adding the components
        frame3.setVisible(true);

        frame3.add(lblLogo);
        frame3.add(lblHeader);
        frame3.add(lblFormNo);
        frame3.add(lblAccountType);
        frame3.add(rdoSavingAccount);
        frame3.add(rdoCurrentAccount);
        frame3.add(rdoFDAccount);
        frame3.add(rdoRDAccount);
        frame3.add(lblCardNo);
        frame3.add(lblCardNoInfo);
        frame3.add(lblCardNoData);
        frame3.add(lblCardNoDataInfo);
        frame3.add(lblPinNo);
        frame3.add(lblPinNoInfo);
        frame3.add(lblPinNoData);
        frame3.add(lblServiceRequired);
        frame3.add(chBoxAtmCard);
        frame3.add(chBoxInternetBanking);
        frame3.add(chBoxMobileBanking);
        frame3.add(chBoxEmailAlert);
        frame3.add(chBoxCheckBook);
        frame3.add(chBoxEStatement);
        frame3.add(chBoxDeclaration);
        frame3.add(btnSubmit);
        frame3.add(btnCancel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnSubmit){
        String formNo = "" + form_number;
        String accountType = null;
        String card_number = "" + cardNumber;

        if (rdoSavingAccount.isSelected()) {
            accountType = "Saving";
        }

        else if (rdoFDAccount.isSelected()) {
            accountType = "Fixed Deposit";
        } else if (rdoCurrentAccount.isSelected()) {
            accountType = "Current";
        } else {
            accountType = "Rrecurring Deposit";
        }

        // selecting services

        selectedServices.setLength(0); // clear old selections

        if (chBoxAtmCard.isSelected())
            selectedServices.append("ATM Card, ");
        if (chBoxInternetBanking.isSelected())
            selectedServices.append("Internet Banking, ");
        if (chBoxMobileBanking.isSelected())
            selectedServices.append("Mobile Banking, ");
        if (chBoxEmailAlert.isSelected())
            selectedServices.append("Email Alert, ");
        if (chBoxCheckBook.isSelected())
            selectedServices.append("Check Book, ");
        if (chBoxEStatement.isSelected())
            selectedServices.append("E- Statement, ");

        if (selectedServices.length() > 0) {
            // Remove last comma and space
            selectedServices.setLength(selectedServices.length() - 2);

        }
        String services = selectedServices.toString();

        boolean declarationCheck = false;
        if (chBoxDeclaration.isSelected()) {
            declarationCheck = true;
        }
        if (declarationCheck) {

            try {
                Conn c = new Conn(); // Make sure Conn.java exists with proper DB connection
                String query = "INSERT INTO signupThree VALUES(?,?,?,?,?)";
                PreparedStatement pst = c.c.prepareStatement(query);
                pst.setString(1, formNo);
                pst.setString(2, accountType);
                pst.setString(3, card_number);
                pst.setString(4, pin);
                pst.setString(5, services);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Record inserted successfully!");
                JOptionPane.showMessageDialog(frame3, "YOUR CARD DETAILS\n\nCARD NO:🔒 " + card_number + "\n\nPIN:🔑 " + pin+"\n\n\n\n");
                frame3.dispose();
                new Transaction(pin);

            }
            catch (

            Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Record not inserted!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Plase check the declaration");

        }
    }
    if(e.getSource()==btnCancel){
        frame3.dispose();
        new Login();
    }
    }

    public static void main(String[] args) {
        new SignUpThree(0L);
    }
}