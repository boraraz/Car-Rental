import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Car_Reservation extends JFrame implements ActionListener {
    private Invoice invoice = new Invoice();
    private ImageIcon carImage;
    private JPanel confirm = new JPanel();
    private JLabel carImageL, dailyPrice, brand, model, gear, vehicleGrp, fuelType, pickUpLoc, returnLoc, pickUpDate, returnDate;
    private JLabel totalPriceL, dateL;
    private JButton rentB;
    private JTextField pickUpDateT, returnDateT, pickUpLocT, returnLocT;
    private String priceForCalc;

    public String getClientId() {
        return clientId;
    }

    private String clientId;

    public Car_Reservation(){
        //empty constructur for use of getClientId()
    }
    public Car_Reservation(int Id, String[][] cars) {
        priceForCalc = cars[Id][5];

        SigninScreen sign = new SigninScreen();
        String userInfo = sign.getPersonInfo();
        sign.dispose();
        //extracting client id
        String arr[] = userInfo.split(" ", 2);

        clientId = arr[0];

        rentB = new JButton("Rent");
        dailyPrice = new JLabel("Daily Price: $" + cars[Id][5]);
        brand = new JLabel("Brand: " + cars[Id][0]);
        model = new JLabel("Model: " + cars[Id][1]);
        gear = new JLabel("Gear Type: " + cars[Id][4]);
        vehicleGrp = new JLabel("Vehicle Group: " + cars[Id][3]);
        fuelType = new JLabel("Fuel Type: " + cars[Id][2]);
        pickUpLoc = new JLabel("Pickup Location: ");
        returnLoc = new JLabel("Return Location: ");
        pickUpDate = new JLabel("Pickup Date: ");
        returnDate = new JLabel("Return Date: ");

        pickUpDateT = new JTextField(20);
        returnDateT = new JTextField(20);
        pickUpLocT = new JTextField(20);
        returnLocT = new JTextField(20);


        pickUpDateT.setText("DD/MM/YYYY");
        returnDateT.setText("DD/MM/YYYY");
        returnDateT.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (returnDateT.getText().equals("DD/MM/YYYY")) {
                    returnDateT.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (returnDateT.getText().isEmpty()) {
                    returnDateT.setText("DD/MM/YYYY");
                }
            }
        });
        pickUpDateT.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (pickUpDateT.getText().equals("DD/MM/YYYY")) {
                    pickUpDateT.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (pickUpDateT.getText().isEmpty()) {
                    pickUpDateT.setText("DD/MM/YYYY");
                }
            }
        });

        try {
            carImage = new ImageIcon(ImageIO.read(getClass().getResource("images/" + (Id + 1) + ".jpg")).getScaledInstance(175, 120,  java.awt.Image.SCALE_SMOOTH));
            carImageL = new JLabel(carImage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        setLayout(new FlowLayout());
        add(carImageL);
        add(brand);
        add(model);
        add(gear);
        add(vehicleGrp);
        add(fuelType);
        add(pickUpLoc);
        add(pickUpLocT);
        add(returnLoc);
        add(returnLocT);
        add(pickUpDate);
        add(pickUpDateT);
        add(returnDate);
        add(returnDateT);
        add(dailyPrice);
        add(rentB);
        add(Confirmation());
        rentB.addActionListener(this);
    }

    private JPanel Confirmation(){


        confirm.setLayout(new FlowLayout());

        confirm.setPreferredSize(new Dimension(200,300));
        confirm.setBackground(new Color(41,41,41));



        return confirm;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.UK);

        if(e.getSource() == rentB){
            Date pickupDateParse = null;
            try {
                pickupDateParse = sdf.parse(pickUpDateT.getText());
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
            Date returnDateParse = null;
            try {
                returnDateParse = sdf.parse(returnDateT.getText());
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }

            long diffInMillies = Math.abs(returnDateParse.getTime() - pickupDateParse.getTime());
            long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

            int totalPrice = (int) (diff * Integer.parseInt(priceForCalc));
            totalPriceL = new JLabel("Total price: $" + totalPrice);
            totalPriceL.setForeground(Color.white);

            dateL = new JLabel("For between these dates\n" + pickUpDateT.getText()+"-"+returnDateT.getText()+".");
            dateL.setForeground(Color.white);
            confirm.add(totalPriceL);
            confirm.add(dateL);
            try {
                FileWriter fw = new FileWriter("reservation.txt",true);
                fw.write(clientId + " " +gear.getText() + " " + fuelType.getText() + " " + vehicleGrp.getText() + " " + pickUpLocT.getText() + " " + returnLocT.getText() + " " + pickUpDateT.getText() + " " +returnDateT.getText());
                fw.close();
            }catch (IOException ie){ie.printStackTrace();
            }catch (Exception ex){ex.printStackTrace();}

            invoice.setVisible(true);

            invoice.setVisible(true);
            invoice.setSize(300,200);


            invoice.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        }
    }
}
