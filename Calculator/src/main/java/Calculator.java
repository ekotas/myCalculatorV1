import javax.swing.*;

public class Calculator {
    public static void main(String[] args) {
        CalculatorGUI buttonFrame = new CalculatorGUI();
        buttonFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buttonFrame.setSize(500, 500);
        buttonFrame.setVisible(true);
    }
}