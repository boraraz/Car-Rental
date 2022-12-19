import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class CarReservation extends JFrame implements ActionListener {
    private Invoice invoicePage = new Invoice();
    private ImageIcon carImage;
    private JPanel confirm = new JPanel();
    private JLabel carImageL, dailyPriceL, brandL, modelL, gearL, vehicleGrpL, fuelTypeL, pickUpLocL, returnLocL, pickUpDateL, returnDateL;
    private JLabel totalPriceL, dateL, trailerL, snowChainL,navigationL,childSeatL;
    private JCheckBox trailer,snowChain,navigation,childSeat;
    private JButton rentB, back;
    private JTextField pickUpDateT, returnDateT, pickUpLocT, returnLocT;
    private String priceForCalc, dailyPrice, brand, model, gear, vehicleGrp, fuelType, userInfo, infoOfRes,clientIdRes, infoOfClient;
    private int rentQuantity = 0, currentQuantityInt = 0;
    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    private int totalPrice = 0, generalTotal = 0;


    public String getClientId() {
        return clientId;
    }

    private String clientId;


    public CarReservation(int Id, String[][] cars) {
        priceForCalc = cars[Id][5];

        SigninScreen sign = new SigninScreen();
        userInfo = sign.getPersonInfo();


        //extracting client id
        String arr[] = userInfo.split(" ", 2);
        clientId = arr[0];

        //defining car props to local
        dailyPrice = cars[Id][5];
        brand =  cars[Id][0];
        model = cars[Id][1];
        gear = cars[Id][4];
        vehicleGrp = cars[Id][3];
        fuelType = cars[Id][2];

        //buttons
        rentB = new JButton("Rent");
        back = new JButton("Back");

        //label
        dailyPriceL = new JLabel("Daily Price: $" + dailyPrice);
        brandL = new JLabel("Brand: " + brand);
        modelL = new JLabel("Model: " + model);
        gearL = new JLabel("Gear Type: " + gear);
        vehicleGrpL = new JLabel("Vehicle Group: " + vehicleGrp);
        fuelTypeL = new JLabel("Fuel Type: " + fuelType);
        pickUpLocL = new JLabel("Pickup Location: ");
        returnLocL = new JLabel("Return Location: ");
        pickUpDateL = new JLabel("Pickup Date: ");
        returnDateL = new JLabel("Return Date: ");
        navigationL = new JLabel("Navigation: ");
        childSeatL = new JLabel("Child Seat: ");
        snowChainL = new JLabel("Snow Chain: ");
        trailerL = new JLabel("Trailer: ");

        navigation = new JCheckBox();
        childSeat = new JCheckBox();
        snowChain = new JCheckBox();
        trailer = new JCheckBox();

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
        add(back);
        add(carImageL);
        add(brandL);
        add(modelL);
        add(gearL);
        add(vehicleGrpL);
        add(fuelTypeL);
        add(pickUpLocL);
        add(pickUpLocT);
        add(returnLocL);
        add(returnLocT);
        add(pickUpDateL);
        add(pickUpDateT);
        add(returnDateL);
        add(returnDateT);
        add(trailerL);
        add(trailer);
        add(snowChainL);
        add(snowChain);
        add(childSeatL);
        add(childSeat);
        add(navigationL);
        add(navigation);
        add(dailyPriceL);
        add(rentB);

        rentB.addActionListener(this);
        back.addActionListener(this);

        navigation.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == 1){
                    totalPrice += 5;
                }
            }
        });
        snowChain.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == 1){
                    totalPrice += 3;
                }
            }
        });
        trailer.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == 1){
                    totalPrice += 2;
                }
            }
        });
        childSeat.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == 1){
                    totalPrice += 1;
                }
            }
        });
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
                totalPrice += (int) (diff * Integer.parseInt(priceForCalc));

                try {
                    FileWriter fw = new FileWriter("reservation.txt", true);
                    fw.write(clientId + " " + gear + " " + fuelType + " " + vehicleGrp + " " + pickUpLocT.getText().replaceAll("\\s", "") + " " + returnLocT.getText().replaceAll("\\s", "") + " " + pickUpDateT.getText() + " " + returnDateT.getText() + "\n");
                    fw.close();
                } catch (Exception ie) {
                    ie.printStackTrace();
                }
                try {
                    FileWriter fwClient = new FileWriter("client_id.txt", true);

                    FileReader fileReaderRes = new FileReader("reservation.txt");
                    BufferedReader bufferedReaderRes = new BufferedReader(fileReaderRes);

                    FileReader fileReaderClient = new FileReader("client_id.txt");
                    BufferedReader bufferedReaderClient = new BufferedReader(fileReaderClient);

                    while ((infoOfRes = bufferedReaderRes.readLine()) != null) {
                        String arrRes[] = infoOfRes.split(" ", 2);
                        if (clientId.equals(arrRes[0])) {
                            rentQuantity += 1;
                            clientIdRes = arrRes[0];
                        }
                    }
                    while ((infoOfClient = bufferedReaderClient.readLine()) != null) {
                        if (clientId.equals(clientIdRes)) {
                            String arr[] = infoOfClient.split(" ", 8);

                            String currentPrice = arr[6];
                            String currentQuantity = arr[7];
                            currentQuantityInt = Integer.parseInt(currentQuantity);
                            currentQuantityInt += 1;
                            generalTotal = Integer.parseInt(currentPrice) + totalPrice;

                            fwClient.write(userInfo + " " + pickUpLocT.getText().replaceAll("\\s", "") + " " + returnLocT.getText().replaceAll("\\s", "") + " " + generalTotal + " " + currentQuantityInt + "\n");
                            fwClient.close();
                        } else {
                            fwClient.write(userInfo + " " + pickUpLocT.getText().replaceAll("\\s", "") + " " + returnLocT.getText().replaceAll("\\s", "") + " " + totalPrice + " " + currentQuantityInt + "\n");
                            fwClient.close();
                        }
                    }
                    if (bufferedReaderClient.readLine() == null) {
                        fwClient.write(userInfo + " " + pickUpLocT.getText().replaceAll("\\s", "") + " " + returnLocT.getText().replaceAll("\\s", "") + " " + totalPrice + " " + rentQuantity + "\n");
                        fwClient.close();
                    }

                } catch (Exception ie) {
                    ie.printStackTrace();
                }


                invoicePage.setVisible(true);
                invoicePage.setSize(300, 200);

                invoicePage.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        }
        if (e.getSource()==back){
            HomeScreen home = new HomeScreen();
            setVisible(false);
            home.setVisible(true);
            home.setSize(1030,1030);
            home.setVisible(true);

            home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        }
    }
}
