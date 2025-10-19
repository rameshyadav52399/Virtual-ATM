import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import com.toedter.calendar.JDateChooser;
import java.sql.*;

public class SignUpOne implements ActionListener {

    JFrame frame2;
    JLabel lblLogo, lblHeader, lblFormNo, lblSubHeader, lblName, lblFathersName,
            lblDOB, lblGender, lblEmail, lblMaritalStatus, lblAddress, lblCity,
            lblPinCode, lblState;
    JButton btnNext;
    JTextField txtName, txtFathersName, txtEmail, txtAddress, txtCity, txtPinCode;
    JComboBox<String> cmbState;
    JRadioButton rdoMale, rdoFemale, rdoOther;
    ButtonGroup genderGroup;
    JRadioButton rdoMarried, rdoUnmarried, rdoMaritalOther;
    ButtonGroup marriedStatusGroup;
    long randomFormNo;
    JDateChooser dateChooser;

    Font headerFontStyle = new Font("Georgia", Font.BOLD, 30);
    Font subHeaderFontStyle = new Font("Arial", Font.BOLD, 25);
    Font labelsFontStyle = new Font("Arial", Font.BOLD, 20);
    Font btnFontStyle = new Font("Arial", Font.BOLD, 15);
    Font rdoFontStyle = new Font("Arial", Font.BOLD, 15);
    Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);

    SignUpOne() {
        // -------- Frame Properties ------------
        frame2 = new JFrame("NEW ACCOUNT APPLICATION FORM");
        frame2.setLayout(null);
        frame2.setSize(850, 800);
        frame2.setLocationRelativeTo(null);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.getContentPane().setBackground(Color.WHITE);

        // Random number generation
        Random random = new Random();
        randomFormNo = 1000 + random.nextInt(9000);

        // -------- Labels and Components -----------
        ImageIcon logoIcon = new ImageIcon(ClassLoader.getSystemResource("logo.jpg"));
        Image scaledImage = logoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        lblLogo = new JLabel(new ImageIcon(scaledImage));
        lblLogo.setBounds(70, 5, 100, 100);

        lblHeader = new JLabel("APPLICATION FORM NO. " + randomFormNo);
        lblHeader.setFont(headerFontStyle);
        lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
        lblHeader.setBounds(200, 15, 500, 70);

        // // lblFormNo = new JLabel(String.valueOf(randomFormNo));
        // lblFormNo.setFont(subHeaderFontStyle);
        // lblFormNo.setHorizontalAlignment(SwingConstants.CENTER);
        // lblFormNo.setBounds(530, 15, 200, 70);

        lblSubHeader = new JLabel("Page:1 Personal Details");
        lblSubHeader.setFont(subHeaderFontStyle);
        lblSubHeader.setHorizontalAlignment(SwingConstants.CENTER);
        lblSubHeader.setBounds(200, 85, 400, 50);

        lblName = new JLabel("Name : ");
        lblName.setFont(labelsFontStyle);
        lblName.setBounds(100, 155, 150, 40);
        txtName = new JTextField();
        txtName.setBounds(330, 155, 400, 30);
        txtName.setFont(rdoFontStyle);

        lblFathersName = new JLabel("Father's Name : ");
        lblFathersName.setFont(labelsFontStyle);
        lblFathersName.setBounds(100, 200, 200, 40);
        txtFathersName = new JTextField();
        txtFathersName.setBounds(330, 200, 400, 30);
        txtFathersName.setFont(rdoFontStyle);

        lblDOB = new JLabel("Date of Birth : ");
        lblDOB.setFont(labelsFontStyle);
        lblDOB.setBounds(100, 245, 200, 40);
        dateChooser = new JDateChooser();
        dateChooser.setBounds(330, 245, 400, 30);
        dateChooser.setFont(rdoFontStyle);

        lblGender = new JLabel("Gender : ");
        lblGender.setFont(labelsFontStyle);
        lblGender.setBounds(100, 300, 200, 40);

        rdoMale = new JRadioButton("Male");
        rdoMale.setBounds(330, 300, 80, 30);
        rdoMale.setFocusable(false);
        rdoMale.setBackground(Color.white);
        rdoMale.setFont(rdoFontStyle);

        rdoFemale = new JRadioButton("Female");
        rdoFemale.setBounds(430, 300, 100, 30);
        rdoFemale.setFocusable(false);
        rdoFemale.setBackground(Color.white);
        rdoFemale.setFont(rdoFontStyle);

        rdoOther = new JRadioButton("Other");
        rdoOther.setBounds(540, 300, 80, 30);
        rdoOther.setFocusable(false);
        rdoOther.setBackground(Color.white);
        rdoOther.setFont(rdoFontStyle);

        genderGroup = new ButtonGroup();
        genderGroup.add(rdoMale);
        genderGroup.add(rdoFemale);
        genderGroup.add(rdoOther);

        lblEmail = new JLabel("Email Address : ");
        lblEmail.setFont(labelsFontStyle);
        lblEmail.setBounds(100, 345, 200, 40);
        txtEmail = new JTextField();
        txtEmail.setBounds(330, 345, 400, 30);
        txtEmail.setFont(rdoFontStyle);

        lblMaritalStatus = new JLabel("Marital Status : ");
        lblMaritalStatus.setFont(labelsFontStyle);
        lblMaritalStatus.setBounds(100, 400, 200, 40);

        rdoMarried = new JRadioButton("Married");
        rdoMarried.setBounds(330, 400, 80, 30);
        rdoMarried.setFocusable(false);
        rdoMarried.setBackground(Color.white);
        rdoMarried.setFont(rdoFontStyle);

        rdoUnmarried = new JRadioButton("Unmarried");
        rdoUnmarried.setBounds(430, 400, 100, 30);
        rdoUnmarried.setFocusable(false);
        rdoUnmarried.setBackground(Color.white);
        rdoUnmarried.setFont(rdoFontStyle);

        rdoMaritalOther = new JRadioButton("Other");
        rdoMaritalOther.setBounds(540, 400, 100, 30);
        rdoMaritalOther.setFocusable(false);
        rdoMaritalOther.setBackground(Color.white);
        rdoMaritalOther.setFont(rdoFontStyle);

        marriedStatusGroup = new ButtonGroup();
        marriedStatusGroup.add(rdoMarried);
        marriedStatusGroup.add(rdoUnmarried);
        marriedStatusGroup.add(rdoMaritalOther);

        lblAddress = new JLabel("Address : ");
        lblAddress.setFont(labelsFontStyle);
        lblAddress.setBounds(100, 445, 200, 40);
        txtAddress = new JTextField();
        txtAddress.setBounds(330, 445, 400, 30);
        txtAddress.setFont(rdoFontStyle);

        lblCity = new JLabel("City : ");
        lblCity.setFont(labelsFontStyle);
        lblCity.setBounds(100, 500, 200, 40);
        txtCity = new JTextField();
        txtCity.setBounds(330, 500, 400, 30);
        txtCity.setFont(rdoFontStyle);

        lblPinCode = new JLabel("Pin Code : ");
        lblPinCode.setFont(labelsFontStyle);
        lblPinCode.setBounds(100, 545, 200, 40);
        txtPinCode = new JTextField();
        txtPinCode.setBounds(330, 545, 400, 30);
        txtPinCode.setFont(rdoFontStyle);

        lblState = new JLabel("State : ");
        lblState.setFont(labelsFontStyle);
        lblState.setBounds(100, 600, 200, 40);
        String[] states = { "Andhra Pradesh", "Arunachal Pradesh", "Assam", "Bihar", "Chhattisgarh", "Goa",
                "Gujarat", "Haryana", "Himachal Pradesh", "Jharkhand", "Karnataka", "Kerala", "Madhya Pradesh",
                "Maharashtra", "Manipur", "Meghalaya", "Mizoram", "Nagaland", "Odisha", "Punjab", "Rajasthan",
                "Sikkim", "Tamil Nadu", "Telangana", "Tripura", "Uttar Pradesh", "Uttarakhand", "West Bengal" };
        cmbState = new JComboBox<>(states);
        cmbState.setBounds(330, 600, 400, 30);
        cmbState.setBackground(Color.white);

        btnNext = new JButton("Next");
        btnNext.setFont(btnFontStyle);
        btnNext.setBackground(Color.black);
        btnNext.setForeground(Color.WHITE);
        btnNext.setFocusable(false);
        btnNext.setBounds(630, 650, 150, 30);
        btnNext.addActionListener(this);

        // ------------- Adding all components before making frame visible -----------
        frame2.add(lblLogo);
        frame2.add(lblHeader);
        // frame2.add(lblFormNo);
        frame2.add(lblSubHeader);
        frame2.add(lblName);
        frame2.add(txtName);
        frame2.add(lblFathersName);
        frame2.add(txtFathersName);
        frame2.add(lblDOB);
        frame2.add(dateChooser);
        frame2.add(lblGender);
        frame2.add(rdoMale);
        frame2.add(rdoFemale);
        frame2.add(rdoOther);
        frame2.add(lblEmail);
        frame2.add(txtEmail);
        frame2.add(lblMaritalStatus);
        frame2.add(rdoMarried);
        frame2.add(rdoUnmarried);
        frame2.add(rdoMaritalOther);
        frame2.add(lblAddress);
        frame2.add(txtAddress);
        frame2.add(lblCity);
        frame2.add(txtCity);
        frame2.add(lblPinCode);
        frame2.add(txtPinCode);
        frame2.add(lblState);
        frame2.add(cmbState);
        frame2.add(btnNext);

        frame2.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String formNo = "" + randomFormNo;
        String name = txtName.getText();
        String fname = txtFathersName.getText();
        String dob = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();

        String gender = null;
        if (rdoMale.isSelected())
            gender = "Male";
        else if (rdoFemale.isSelected())
            gender = "Female";
        else
            gender = "Other";

        String email = txtEmail.getText();

        String marital = null;
        if (rdoMarried.isSelected())
            marital = "Married";
        else if (rdoUnmarried.isSelected())
            marital = "Unmarried";
        else
            marital = "Other";

        String address = txtAddress.getText();
        String city = txtCity.getText();
        String pincode = txtPinCode.getText();
        String state = (String) cmbState.getSelectedItem();

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Name is required");
            return;
        }

        try {
            Conn c = new Conn(); // Make sure Conn.java exists with proper DB connection
            String query = "INSERT INTO signup VALUES(?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = c.c.prepareStatement(query);
            pst.setString(1, formNo);
            pst.setString(2, name);
            pst.setString(3, fname);
            pst.setString(4, dob);
            pst.setString(5, gender);
            pst.setString(6, email);
            pst.setString(7, marital);
            pst.setString(8, address);
            pst.setString(9, city);
            pst.setString(10, pincode);
            pst.setString(11, state);

            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Record inserted successfully!");
            frame2.dispose();
            new SignUpTwo(randomFormNo);
        }

        catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Record not inserted!");
        }
    }

    public static void main(String[] args) {
        new SignUpOne();
    }
}
