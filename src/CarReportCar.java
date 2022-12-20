import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class CarReportCar extends JFrame implements ActionListener {
    private JLabel carPlateL;
    private JTextField carPlateT;
    private JButton reportB;
    private String infoOfClient, infoOfCar,infoOfRes, clientId;

    private int rentQuantity = 0;

    public CarReportCar(){
        carPlateL = new JLabel("Enter the car plate you want to report: ");

        carPlateT = new JTextField(20);

        reportB = new JButton("Report");

        setLayout(new FlowLayout());

        add(carPlateL);
        add(carPlateT);
        add(reportB);

        reportB.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == reportB){
            try {
                File car_plate = new File("car_plates.txt");
                File temp = new File("temp.txt");
                File res = new File("reservation.txt");
                Scanner resReader = new Scanner(res);
                Scanner myReader = new Scanner(car_plate);
                FileWriter fw = new FileWriter("temp.txt");



                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    String arr[] = data.split(" ", 8);
                    String plate = arr[4] +" "+ arr[5] +" "+ arr[6];
                    String quantity = arr[7];

                    int quantityInt = Integer.parseInt(quantity);

                    if(plate.equals(carPlateT.getText())){
                        while (resReader.hasNextLine()){
                            String dataRes = resReader.nextLine();
                            String arrRes[] = dataRes.split(" ", 11);
                            String plateRes = arrRes[8] +" "+ arrRes[9] +" "+ arrRes[10];
                            if (plate.equals(plateRes)){
                                quantityInt += 1;
                            }
                        }

                        resReader.close();
                        fw.write(arr[0]+" "+arr[1]+" "+arr[2]+" "+arr[3]+" "+plate+" "+quantityInt+"\n");
                    }
                    else {
                        fw.write(data +"\n");
                    }
                }
                fw.close();
                myReader.close();
                temp.renameTo(car_plate);
            } catch (Exception ie) {ie.printStackTrace();
            }
        }
    }
}
