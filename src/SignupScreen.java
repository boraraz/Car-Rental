import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignupScreen extends JFrame implements ActionListener {
    private JLabel signUp, mailL, passL, usernameL;
    private JButton signUpB;
    private JTextField mailT, passT, usernameT;

    public SignupScreen(){
        signUp = new JLabel("Signup");
        usernameL = new JLabel("Username:");
        mailL = new JLabel("Mail:");
        passL = new JLabel("Password:");

        usernameT = new JTextField();
        mailT = new JTextField();
        passT = new JTextField();

        signUpB = new JButton("Sign Up");

        setLayout(new FlowLayout());
        add(signUp);
        add(usernameL);
        add(usernameT);
        add(mailL);
        add(mailT);
        add(passL);
        add(passT);
        add(signUpB);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

