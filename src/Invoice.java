import javax.swing.*;
import java.awt.*;

public class Invoice extends JFrame {
    private JLabel clientId, gear, fuel, brand, model, vehicleGroup, pickLoc, retLoc, pickDate, retDate;


    public Invoice(){
        Car_Reservation cR = new Car_Reservation();
        String info = cR.getClientId();
        cR.dispose();
        clientId = new JLabel(info);
        setLayout(new FlowLayout());
        add(clientId);
    }
}
