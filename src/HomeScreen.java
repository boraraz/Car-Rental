import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeScreen extends JFrame implements ActionListener {
    public int getI() {
        return i;
    }
    public String[][] getCars() {
        return cars;
    }

    private int i;
    private String[][] cars;
    private JLabel welcome;
    private JButton[] btn = new JButton[15];
    private JButton logout, carReport, clientReport;

    public HomeScreen(){
        logout = new JButton("Logout");
        carReport = new JButton("Car Report");
        clientReport = new JButton("Client Report");

        welcome = new JLabel("Welcome");


        setLayout(new FlowLayout());
        getContentPane().setBackground(new Color(160, 192, 255, 255));

        add(logout);
        add(welcome);
        add(createCarSelection());
        add(carReport);
        add(clientReport);

        logout.addActionListener(this);
        carReport.addActionListener(this);
    }

    private JPanel createCarSelection(){

        Image[] img = new Image[15];


        cars = new String[][]{
                {"Mercedes-Benz","CLA 180d","Diesel","Sedan","Automatic","15","06 ABC 06"},
                {"Mercedes-Benz","C 180","Gasoline","Coupé","Automatic","20", "06 DOC 24"},
                {"Mercedes-Benz","S 400","Gasoline","Sedan","Automatic","30","06 KAS 56"},
                {"BMW","320i","Gasoline","Sedan","Automatic","10", "06 DUZ 31"},
                {"BMW","420d","Diesel","Cabrio","Automatic","22", "06 PCA 5961"},
                {"BMW","iX3","Electric","SUV","Automatic","40", "06 L 234"},
                {"Audi","A3","Diesel","Hatchback","Automatic","7", "06 CAS 88"},
                {"Audi","A7 Long","Diesel","Sedan","Automatic","18", "06 CIZ 765"},
                {"Volvo","XC-90","Diesel","SUV","Automatic","25", "06 DIF 009"},
                {"Volvo","V-40 Cross Country","Hatchback","Gasoline","Automatic","10", "06 CUM 909"},
                {"Hyundai","i10","Gasoline & LPG","Hatchback","Manuel","5", "06 FN 232"},
                {"Hyundai","i20","Gasoline & LPG","Hatchback","Automatic","7", "06 BYR 7890"},
                {"Alfa-Romeo","Tonale","Diesel","SUV","Automatic","13","06 AG 3245"},
                {"Alfa-Romeo","Giulietta","Diesel","Hatchback","Manuel","15", "06 PBA 566"},
                {"Alfa-Romeo","Giulia","Gasoline","Hatchback","Automatic","9","06 BNN 65"},
        };

        for (i = 0; i<15;i++){
            btn[i] = new JButton(cars[i][1]);
        }



        JPanel carsPanel = new JPanel();
        carsPanel.setLayout(new FlowLayout());

        try {
            for(i = 0; i<15;i++) {
                img[i] = ImageIO.read(getClass().getResource("images/"+ (i+1) +".jpg")).getScaledInstance(175, 120,  java.awt.Image.SCALE_SMOOTH);
                btn[i].setIcon(new ImageIcon(img[i]));
                carsPanel.add(btn[i]);
                btn[i].addActionListener(this);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }


        carsPanel.setPreferredSize(new Dimension(1000,600));
        carsPanel.setBackground(new Color(41,41,41));



        return carsPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int id = 0;

        for (i = 0;i<15;i++){
            if (e.getSource() == btn[i]){
                id = i;
            }
        }
        if (e.getSource() == btn[id]){
            CarReservation cR = new CarReservation(id,cars);
            setVisible(false);
            cR.setVisible(true);
            cR.setSize(250,900);

            cR.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        }
        if(e.getSource() == logout){
            OnboardingScreen onBoardScrn = new OnboardingScreen();

            setVisible(false);
            onBoardScrn.setTitle("Car Rental");
            onBoardScrn.setSize(300,200);
            onBoardScrn.setVisible(true);
            onBoardScrn.setResizable(false);

            onBoardScrn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        }
        if (e.getSource() == carReport){
            CarReportCar cRc = new CarReportCar();
            setVisible(false);
            cRc.setTitle("Car Rental");
            cRc.setSize(300,200);
            cRc.setVisible(true);
            cRc.setResizable(false);

            cRc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }
}
