import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeScreen extends JFrame implements ActionListener {
    private int i;
    private Car_Reservation cR;
    private JLabel welcome;
    private JButton[] btn = new JButton[15];

    public HomeScreen(){
        welcome = new JLabel("Welcome");

        setLayout(new FlowLayout());
        getContentPane().setBackground(new Color(160, 192, 255, 255));

        add(welcome);
        add(createCarSelection());
    }

    private JPanel createCarSelection(){

        Image[] img = new Image[15];
        String[][] cars;

        cars = new String[][]{
                {"Mercedes-Benz","CLA 180d","Diesel","Automatic","2"},
                {"Mercedes-Benz","C 180 Coupé","Gasoline","Automatic","1"},
                {"Mercedes-Benz","S 400","Gasoline","Automatic","1"},
                {"BMW","320i","Gasoline","Automatic","3"},
                {"BMW","420d","Diesel","Automatic","2"},
                {"BMW","iX3","Electric","Automatic","4"},
                {"Audi","A3","Diesel","Automatic","5"},
                {"Audi","A7 Long","Diesel","Automatic","1"},
                {"Volvo","XC-90","Diesel","Automatic","3"},
                {"Volvo","V-40 Cross Country","Gasoline","Automatic","1"},
                {"Hyundai","i10","Gasoline & LPG","Manuel","5"},
                {"Hyundai","i20","Gasoline & LPG","Automatic","2"},
                {"Alfa-Romeo","Tonale","Diesel","Automatic","3"},
                {"Alfa-Romeo","Giulietta","Diesel","Manuel","2"},
                {"Alfa-Romeo","Giulia","Gasoline","Automatic","1"},
        };

        for (i = 0; i<15;i++){
            btn[i] = new JButton(cars[i][1]);
        }



        JPanel carsPanel = new JPanel();
        carsPanel.setLayout(new FlowLayout());

        try {
            for(i = 0; i<15;i++) {
                img[i] = ImageIO.read(getClass().getResource("images/"+ (i+1) +".jpg"));
                //btn[i].setIcon(new ImageIcon(img[i]));
                carsPanel.add(btn[i]);
                btn[i].addActionListener(this);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }


        carsPanel.setPreferredSize(new Dimension(1000,1000));
        carsPanel.setBackground(new Color(41,41,41));



        return carsPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (i = 0;i<15;i++){
            System.out.println(i);
            cR = new Car_Reservation(i);
            if (e.getSource() == btn[i]){
                setVisible(false);
                cR.setVisible(true);
                cR.setSize(600,600);
                cR.setVisible(true);

                cR.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            }
        }
    }
}
