import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;

public class Invoice extends JFrame implements ActionListener {
    private JLabel clientIdL, gearL, fuelL, brandL, modelL, vehicleGroupL, pickLocL, retLocL, pickDateL, retDateL;
    private SigninScreen sign = new SigninScreen();
    private String clientId, gear, fuel, brand, model, vehicleGroup, pickLoc, retLoc, pickDate, retDate, carsIdentity;
    private String accInfo, carsInfo;
    private JButton print;
    public Invoice(){

        accInfo = sign.getPersonInfo();
        sign.dispose();
        String arr[] = accInfo.split(" ", 2);
        clientId = arr[0];

        try {
            FileReader fileReader = new FileReader("reservation.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            carsInfo = bufferedReader.readLine();
            String resInfoArr[] = carsInfo.split(" ",8);
            carsIdentity = resInfoArr[0];

            if (carsInfo!= null){
                if(carsIdentity == clientId){
                    gear = resInfoArr[1];
                    fuel = resInfoArr[2];
                    brand = resInfoArr[3];
                    model = resInfoArr[4];
                    vehicleGroup = resInfoArr[3];
                    pickLoc = resInfoArr[4];
                    retLoc = resInfoArr[5];
                    pickDate = resInfoArr[6];
                    retDate = resInfoArr[7];
                }
            }
        }catch (Exception ie) {ie.printStackTrace();
        }

        print = new JButton("Back");

        clientIdL = new JLabel(clientId);
        gearL = new JLabel(gear);
        fuelL = new JLabel(fuel);
        vehicleGroupL = new JLabel(vehicleGroup);
        pickLocL = new JLabel(pickLoc);
        retLocL = new JLabel(retLoc);
        pickDateL = new JLabel(pickDate);
        retDateL = new JLabel(retDate);

        setLayout(new FlowLayout());
        add(print);
        add(clientIdL);
        add(gearL);
        add(fuelL);
        add(vehicleGroupL);
        add(pickLocL);
        add(retLocL);
        add(pickDateL);
        add(retDateL);

        print.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == print){

        }
    }
}
