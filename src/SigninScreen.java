import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SigninScreen extends JFrame implements ActionListener {
    private JLabel signin;

    public SigninScreen(){
        signin = new JLabel("Sign In olcak bu abisi");

        setLayout(new FlowLayout());
        add(signin);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}