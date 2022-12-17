import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Car_Reservation extends JFrame {
    private ImageIcon carImage;
    private JLabel carImageL;
    private JButton rentB;

    public Car_Reservation(int Id){
        rentB = new JButton("Rent");
        try {
            carImage = new ImageIcon(ImageIO.read(getClass().getResource("images/" + (Id + 1) + ".jpg")));
            carImageL = new JLabel(carImage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        setLayout(new FlowLayout());
        add(carImageL);
        add(rentB);
    }
}
