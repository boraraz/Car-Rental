import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SigninScreen extends JFrame implements ActionListener {

    private HomeScreen hm = new HomeScreen();
    private JLabel signin, mailL, passL;
    private JButton signinB;
    private JTextField mailT, passT;

    public SigninScreen(){
        signin = new JLabel("Sign In");
        mailL = new JLabel("Mail:");
        passL = new JLabel("Password:");

        mailT = new JTextField();
        passT = new JTextField();

        signinB = new JButton("Sign In");

        setLayout(new FlowLayout());
        add(signin);
        add(mailL);
        add(mailT);
        add(passL);
        add(passT);
        add(signinB);

        signinB.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signinB){
            setVisible(false);
            hm.setVisible(true);
            hm.setSize(300,200);
            hm.setVisible(true);

            hm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }
}
