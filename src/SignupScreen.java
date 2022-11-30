import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignupScreen extends JFrame implements ActionListener {
    private JLabel signin;

    public SignupScreen(){
        signin = new JLabel("Signup olcak bu abisi");

        setLayout(new FlowLayout());
        add(signin);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

