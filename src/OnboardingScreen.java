import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OnboardingScreen extends JFrame implements ActionListener {

    private SigninScreen sgn_in = new SigninScreen();
    private SignupScreen sgnup = new SignupScreen();
    private JLabel carRental, carRentalName;
    private JButton sign_in_btn, sign_up_btn;

    public OnboardingScreen(){

        carRentalName = new JLabel("Car Rental");
        carRentalName.setFont(new Font("Sans", Font.PLAIN, 30));
        carRental = new JLabel("Welcome to car rental program");
        carRental.setFont(new Font("Sans", Font.PLAIN, 20));
        //sign in button
        sign_in_btn = new JButton("Login");
        sign_in_btn.setForeground(Color.BLACK);
        sign_in_btn.setPreferredSize(new Dimension(150,40));
        //signup button
        sign_up_btn = new JButton("Sign Up");
        sign_up_btn.setForeground(Color.BLACK);
        sign_up_btn.setPreferredSize(new Dimension(150,40));
        //layout
        setLayout(new FlowLayout());
        getContentPane().setBackground(new Color(160, 192, 255, 255));
        //add part
        add(carRentalName);
        add(carRental);
        add(sign_up_btn);
        add(sign_in_btn);

        //action listeners
        sign_in_btn.addActionListener(this);
        sign_up_btn.addActionListener(this);

    }



    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == sign_in_btn){
            setVisible(false);
            sgn_in.setVisible(true);
            sgn_in.setSize(280,250);
            sgn_in.setVisible(true);
            sgn_in.setResizable(false);

            sgn_in.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        }
        else if(e.getSource() == sign_up_btn){
            setVisible(false);
            sgnup.setVisible(true);
            sgnup.setSize(280,300);
            sgnup.setVisible(true);
            sgnup.setResizable(false);

            sgnup.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        }
    }
}
