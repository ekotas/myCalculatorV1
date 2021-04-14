import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

public class CalculatorGUI extends JFrame {
    private JTextField display;
    private JPanel panel;
    private BigDecimal element1;
    private BigDecimal element2;
    private String lastCommand;
    private boolean emptiness;
    private boolean lastEqual;

    //Creating UI of calculator
    public CalculatorGUI() {
        super("Calculator");
        setLayout(new BorderLayout());

        emptiness=true;
        display = new JTextField("0");
        display.setEnabled(false);
        display.setFont(display.getFont().deriveFont(50f));
        display.setDisabledTextColor(Color.BLACK);
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        add(display, BorderLayout.NORTH);

        panel = new JPanel();
        panel.setLayout(new GridLayout(4,4));
        AddButtons();
        add(panel,BorderLayout.CENTER);
    }
    //Function for adding buttons
    private void AddButtons() {
        List<String> buttons= Arrays.asList(new String[]{"7","8","9","/","4","5","6","*","1","2","3","-","0",".","=","+"});
        for(String str:buttons) {
            Button tmp=new Button(str);
            if(Character.isDigit(str.charAt(0))) {
                tmp.addActionListener(new NumListener());
            }
            else {
                tmp.addActionListener(new NotNumListener());
            }
            panel.add(tmp);
        }
    }
    //ActionListener for number buttons
    private class NumListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String input = event.getActionCommand();
            if(emptiness) {
                display.setText("");
                emptiness=false;
            }
            if(lastEqual==true) {
                element2=null;
                display.setText("");
                lastEqual=false;
            }
            else if(element1!=null && element2!=null) {
                element1=null;
                element2=null;
                display.setText("");
                emptiness=true;
            }
            display.setText(display.getText() + input);
        }
    }
    //ActionListener for non-number buttons
    private class NotNumListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String command = event.getActionCommand();
            if(command.equals("=")) {
                if(element2==null) {
                    element2 = new BigDecimal(display.getText());
                }
                BigDecimal result = doCommand();
                display.setText(result.toString());
                element1 = result;
                //System.out.println("element1 = "+element1);
                //System.out.println("element2 = "+element2);
                lastEqual=true;
            }
            else if(command.equals(".")) {
                lastCommand=command;
                display.setText(display.getText() + command);
            }
            else if(command.equals("/")) {
                lastCommand=command;
                element1 = new BigDecimal(display.getText());
                display.setText("");
            }
            else if(command.equals("-")) {
                lastCommand=command;
                element1 = new BigDecimal(display.getText());
                display.setText("");
            }
            else if(command.equals("+")) {
                lastCommand=command;
                element1 = new BigDecimal(display.getText());
                display.setText("");
            }
            else if(command.equals("*")) {
                lastCommand=command;
                element1 = new BigDecimal(display.getText());
                display.setText("");
            }
        }
    }
    //Function for calculating result
    private BigDecimal doCommand() {
        BigDecimal result=BigDecimal.ZERO;
        if(lastCommand.equals("/")) {
            if(element2.equals(BigDecimal.ZERO)) {
                JOptionPane.showMessageDialog(null,
                        "wrong input, type what you want again","error", JOptionPane.PLAIN_MESSAGE); //if division by zero appeared JOptionPane with message
            }
            else {
                result = element1.divide(element2,3, RoundingMode.HALF_EVEN);
            }
        }
        else if(lastCommand.equals("-")) {
            result = element1.subtract(element2);
        }
        else if(lastCommand.equals("+")) {
            result = element1.add(element2);
        }
        else if(lastCommand.equals("*")) {
            result = element1.multiply(element2);
        }
        return result;
    }
}
