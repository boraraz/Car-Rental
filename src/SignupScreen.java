import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

public class SignupScreen extends JFrame implements ActionListener {
    private JLabel signUp, mailL, passL, usernameL;
    private JButton signUpB;
    private JTextField mailT, passT, usernameT;

    public SignupScreen(){
        signUp = new JLabel("Signup");
        usernameL = new JLabel("Username:");
        mailL = new JLabel("Mail:");
        passL = new JLabel("Password:");

        usernameT = new JTextField(20);
        mailT = new JTextField(20);
        passT = new JTextField(20);

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

        signUpB.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==signUpB) {
            System.out.println(usernameT.getText());
            try {
                String clientId = String.valueOf(Math.abs(Math.random()*10000));
                FileWriter fw = new FileWriter("account.txt",true);
                fw.write(clientId + " " +usernameT.getText() + " " + mailT.getText() + " " + passT.getText());
                fw.close();
            } catch (Exception ie){ie.printStackTrace();
            }

            SigninScreen signIn = new SigninScreen();
            setVisible(false);
            signIn.setVisible(true);
            signIn.setSize(300,200);
            signIn.setResizable(false);

            signIn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            dispose();
        }
    }
}

