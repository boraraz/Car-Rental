import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class CarReportCar extends JFrame {
    private JLabel a;
    private String infoOfClient, infoOfCar,infoOfRes, clientId;
    private int rentQuantity = 0;

    public CarReportCar(){
        try {
            FileReader fileReaderCar = new FileReader("car_plates.txt");
            BufferedReader bufferedReaderCar = new BufferedReader(fileReaderCar);
            infoOfCar = bufferedReaderCar.readLine();
        } catch (Exception ie) {ie.printStackTrace();
        }



    }
}
