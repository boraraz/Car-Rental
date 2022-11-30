import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        OnboardingScreen onBoardScrn = new OnboardingScreen();

        onBoardScrn.setTitle("Car Rental");
        onBoardScrn.setSize(300,200);
        onBoardScrn.setVisible(true);
        onBoardScrn.setResizable(false);

        onBoardScrn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
