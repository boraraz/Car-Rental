import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SigninScreen extends JFrame implements ActionListener {

    private HomeScreen hm = new HomeScreen();
    private JLabel signin, usernameL, passL;
    private JButton signinB, back;
    private JTextField usernameT, passT;

    private static String personInfo;
    public void setPersonInfo(String personInfo)
    {
        SigninScreen.personInfo =personInfo;
    }
    public String getPersonInfo()
    {
        return personInfo;
    }
    public SigninScreen(){
        signin = new JLabel("Sign In");
        signin.setFont(new Font("Sans", Font.PLAIN, 40));
        usernameL = new JLabel("Username:");
        passL = new JLabel("Password:");

        usernameT = new JTextField(20);
        passT = new JTextField(20);

        signinB = new JButton("Sign In");
        back = new JButton("Back");

        setLayout(new FlowLayout());
        add(back);
        add(signin);
        add(usernameL);
        add(usernameT);
        add(passL);
        add(passT);
        add(signinB);

        signinB.addActionListener(this);
        back.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back){
            OnboardingScreen oS = new OnboardingScreen();
            setVisible(false);
            oS.setVisible(true);
            oS.setSize(300,200);
            oS.setVisible(true);

            oS.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        }
        if (e.getSource() == signinB){
            String username = usernameT.getText();
            String password = passT.getText();
            if (username.isEmpty()||password.isEmpty()) {
                JOptionPane.showMessageDialog(null,"Fill in the blanks.");
                return;
            }
            try {
                FileReader fileReader=new FileReader("account.txt");
                BufferedReader bufferedReader=new BufferedReader(fileReader);
                String info;
                boolean found=false;
                while ((info = bufferedReader.readLine())!=null && !found)
                {
                    //Founded username
                    if(info.contains(username)) {
                        //found password
                        if(info.contains(password))
                        {
                            setPersonInfo(info);
                            found=true;
                        }
                    }
                }
                fileReader.close();
                if(found==false) {
                    JOptionPane.showMessageDialog(null,"You have entered invalid username or password. Please try again");
                }
                if (found==true)
                {
                    CarReservation.rentQuantity = 0;
                    setVisible(false);
                    hm.setVisible(true);
                    hm.setSize(1030,1030);
                    hm.setVisible(true);

                    hm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    dispose();
                }
            } catch (Exception ie) {ie.printStackTrace();
            }


        }
    }
}
