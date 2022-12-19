import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class Invoice extends JFrame{
    private JLabel clientIdL, gearL, fuelL, brandL, modelL, vehicleGroupL, pickLocL, retLocL, pickDateL, retDateL;
    private SigninScreen sign = new SigninScreen();
    private String clientId, gear, fuel, brand, model, vehicleGroup, pickLoc, retLoc, pickDate, retDate, carsIdentity;
    private String accInfo, carsInfo;
    private JButton print;
    public Invoice(){

        accInfo = sign.getPersonInfo();

        String arr[] = accInfo.split(" ", 2);
        clientId = arr[0];
        try {
            FileReader fileReader = new FileReader("reservation.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((carsInfo = bufferedReader.readLine())!= null){
                String resInfoArr[] = carsInfo.split(" ",8);
                carsIdentity = resInfoArr[0];
                if(carsIdentity.trim().equals(clientId.trim())){
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

        print = new JButton("Print");

        clientIdL = new JLabel("Invoice for " +clientId);
        gearL = new JLabel("Gear Type: "+gear);
        fuelL = new JLabel("Fuel Type: "+fuel);
        vehicleGroupL = new JLabel("Vehicle Group: "+vehicleGroup);
        pickLocL = new JLabel("Pickup Location: "+pickLoc);
        retLocL = new JLabel("Return Location: "+retLoc);
        pickDateL = new JLabel("Pickup Date: "+pickDate);
        retDateL = new JLabel("Return Date: "+retDate);

        setLayout(new FlowLayout());

        add(clientIdL);
        add(gearL);
        add(fuelL);
        add(vehicleGroupL);
        add(pickLocL);
        add(retLocL);
        add(pickDateL);
        add(retDateL);
        add(print);
    }

}
