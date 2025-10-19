
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;

public class SignUpTwo implements ActionListener {
    JFrame frame3;
    JLabel lblLogo;
    JLabel lblFormNo;
    JLabel lblReligion;
    JLabel lblHeader;
    JComboBox<String> cmbReligions;
    JLabel lblCategory;
    JComboBox<String> cmbCategory;
    JLabel lblIncome;
    JComboBox<String> cmbIncome;
    JLabel lblQualification;
    JLabel lblQualification2;
    JComboBox<String> cmbQualification;
    JLabel lblPanNo;
    JTextField txtPanNo;
    JLabel lblAadhaarNo;
    JTextField txtAadhaar;
    JLabel lblSeniorCitizen;
    JRadioButton rdoSeniorCitizenYes;
    JRadioButton rdoSeniorCitizenNo;
    ButtonGroup SeniorCitizenGroup;
    JLabel lblExistingAc;
    JRadioButton rdoExistingAcYes;
    JRadioButton rdoExistingAcNo;
    ButtonGroup existingAcAGroup;
    JButton btnNext;
    long randomFormNo;

    Font headerFontStyle = new Font("Georgia", Font.BOLD, 36);
    Font subHeaderFontStyle = new Font("Arial", Font.BOLD, 25);
    Font labelsFontStyle = new Font("Arial", Font.BOLD, 20);
    Font btnFontStyle = new Font("Arial", Font.BOLD, 15);
    Font rdoFontStyle = new Font("Arial", Font.BOLD, 15);

    SignUpTwo(long randomFormNo) {
        this.randomFormNo = randomFormNo;
        // --------Frame Properties------------
        frame3 = new JFrame("NEW ACCOUNT APPLICATION FORM - PAGE:2");
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

        lblHeader = new JLabel("Page 2: Additional Details");
        lblHeader.setFont(headerFontStyle);
        lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
        lblHeader.setBounds(200, 40, 500, 70);

        JLabel lblFormNo = new JLabel("Form No: " + randomFormNo);
        lblFormNo.setFont(subHeaderFontStyle);
        lblFormNo.setHorizontalAlignment(SwingConstants.CENTER);
        lblFormNo.setBounds(500, 10, 300, 40);

        lblReligion = new JLabel("Religion: ");
        lblReligion.setFont(labelsFontStyle);
        lblReligion.setBounds(100, 150, 200, 40);
        String[] religions = { "Hindu", "Muslim", "Other" };
        cmbReligions = new JComboBox<>(religions);
        cmbReligions.setBounds(330, 150, 400, 30);
        cmbReligions.setBackground(Color.white);

        lblCategory = new JLabel("Category: ");
        lblCategory.setFont(labelsFontStyle);
        lblCategory.setBounds(100, 210, 200, 40);
        String[] categories = { "SC", "ST", "OBC", "Others" };
        cmbCategory = new JComboBox<>(categories);
        cmbCategory.setBounds(330, 210, 400, 30);
        cmbCategory.setBackground(Color.white);

        lblIncome = new JLabel("Income: ");
        lblIncome.setFont(labelsFontStyle);
        lblIncome.setBounds(100, 260, 200, 40);
        String[] income = {"10000>20000", "20000-30000", "30000-50000","50000 above"};
        cmbIncome = new JComboBox<>(income);
        cmbIncome.setBounds(330, 260, 400, 30);
        cmbIncome.setBackground(Color.white);

        lblQualification = new JLabel("Educational");
        lblQualification.setFont(labelsFontStyle);
        lblQualification.setBounds(100, 320, 200, 40);
        lblQualification2 = new JLabel("Qualification: ");
        lblQualification2.setFont(labelsFontStyle);
        lblQualification2.setBounds(100, 350, 200, 40);
        String[] qualification = { "Graduate", "Post-Graduate", "Doctorate", "Others" };
        cmbQualification = new JComboBox<>(qualification);
        cmbQualification.setBounds(330, 320, 400, 30);
        cmbQualification.setBackground(Color.white);

        lblPanNo = new JLabel("PAN Number: ");
        lblPanNo.setFont(labelsFontStyle);
        lblPanNo.setBounds(100, 400, 200, 40);
        txtPanNo = new JTextField();
        txtPanNo.setBounds(330, 400, 400, 30);
        txtPanNo.setFont(btnFontStyle);

        lblAadhaarNo = new JLabel("Aadhaar Number: ");
        lblAadhaarNo.setFont(labelsFontStyle);
        lblAadhaarNo.setBounds(100, 460, 200, 40);
        txtAadhaar = new JTextField();
        txtAadhaar.setBounds(330, 460, 400, 30);
        txtAadhaar.setFont(btnFontStyle);

        lblSeniorCitizen = new JLabel("Senior Citizen:");
        lblSeniorCitizen.setFont(labelsFontStyle);
        lblSeniorCitizen.setBounds(100, 520, 200, 40);
        rdoSeniorCitizenYes = new JRadioButton("Yes");
        rdoSeniorCitizenYes.setBounds(330, 520, 100, 30);
        rdoSeniorCitizenYes.setFocusable(false);
        rdoSeniorCitizenYes.setBackground(Color.white);
        rdoSeniorCitizenYes.setFont(rdoFontStyle);
        rdoSeniorCitizenNo = new JRadioButton("No");
        rdoSeniorCitizenNo.setBounds(450, 520, 100, 30);
        rdoSeniorCitizenNo.setFocusable(false);
        rdoSeniorCitizenNo.setBackground(Color.white);
        rdoSeniorCitizenNo.setFont(rdoFontStyle);
        // ------------- Group the radio buttons so only one can be selected--------
        SeniorCitizenGroup = new ButtonGroup();
        SeniorCitizenGroup.add(rdoSeniorCitizenYes);
        SeniorCitizenGroup.add(rdoSeniorCitizenNo);

        lblExistingAc = new JLabel("Existing Account:");
        lblExistingAc.setFont(labelsFontStyle);
        lblExistingAc.setBounds(100, 580, 200, 40);

        rdoExistingAcYes = new JRadioButton("Yes");
        rdoExistingAcYes.setBounds(330, 580, 100, 30);
        rdoExistingAcYes.setFocusable(false);
        rdoExistingAcYes.setBackground(Color.white);
        rdoExistingAcYes.setFont(rdoFontStyle);

        rdoExistingAcNo = new JRadioButton("No");
        rdoExistingAcNo.setBounds(450, 580, 100, 30);
        rdoExistingAcNo.setFocusable(false);
        rdoExistingAcNo.setBackground(Color.white);
        rdoExistingAcNo.setFont(rdoFontStyle);
        // ------------- Group the radio buttons so only one can be selected--------
        existingAcAGroup = new ButtonGroup();
        existingAcAGroup.add(rdoExistingAcNo);
        existingAcAGroup.add(rdoExistingAcYes);

        // ------------- button properties-------
        btnNext = new JButton("Next");
        btnNext.setFont(btnFontStyle);
        btnNext.setBackground(Color.black);
        btnNext.setForeground(Color.WHITE);
        btnNext.setFocusable(false);
        btnNext.setBounds(630, 650, 150, 30);
        btnNext.addActionListener(this);

        // Adding the components
        frame3.setVisible(true);

        frame3.add(lblLogo);
        frame3.add(lblHeader);
        frame3.add(lblFormNo);
        frame3.add(lblReligion);
        frame3.add(cmbReligions);
        frame3.add(lblCategory);
        frame3.add(cmbCategory);
        frame3.add(lblIncome);
        frame3.add(cmbIncome);
        frame3.add(lblQualification);
        frame3.add(lblQualification2);
        frame3.add(cmbQualification);
        frame3.add(lblPanNo);
        frame3.add(txtPanNo);
        frame3.add(lblAadhaarNo);
        frame3.add(txtAadhaar);
        frame3.add(lblSeniorCitizen);
        frame3.add(rdoSeniorCitizenYes);
        frame3.add(rdoSeniorCitizenNo);
        frame3.add(lblExistingAc);
        frame3.add(rdoExistingAcYes);
        frame3.add(rdoExistingAcNo);
        frame3.add(btnNext);
    }

    // --------Implementing the actionPerformed method of ActionListener interface
    // which occurred when next button is clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        String formNo = "" + randomFormNo;
        String religion = (String) cmbReligions.getSelectedItem();
        // String category = (String) cmbCategory.getSelectedItem();
        String income = (String) cmbIncome.getSelectedItem();
        String education = (String) cmbQualification.getSelectedItem();
        String panNo = txtPanNo.getText();
        String aadharNo = txtAadhaar.getText();

        String serCitizen = null;
        if (rdoSeniorCitizenYes.isSelected()) {
            serCitizen = "Yes";
        } else {
            serCitizen = "No";
        }

        String existAc = null;
        if (rdoExistingAcYes.isSelected()) {
            existAc = "Yes";
        } else {
            existAc = "No";
        }

        try {
            Conn c = new Conn(); // Make sure Conn.java exists with proper DB connection
            String query = "INSERT INTO signupTwo VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement pst = c.c.prepareStatement(query);
            pst.setString(1, formNo);
            pst.setString(2, religion);
            pst.setString(3, income);
            pst.setString(4, education);
            pst.setString(5, panNo);
            pst.setString(6, aadharNo);
            pst.setString(7, serCitizen);
            pst.setString(8, existAc);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Record inserted successfully!");
            frame3.dispose();
            new SignUpThree(randomFormNo);
        }

        catch (

        Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Record not inserted!");
        }
    }

    public static void main(String[] args) {
        new SignUpTwo(0L);
    }
}