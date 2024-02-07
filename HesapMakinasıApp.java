import javax.swing.*;
import javax.swing.plaf.basic.BasicOptionPaneUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HesapMakinasiApp  extends  JFrame{
    private JTextField textField;
    private double firstNumber;
    private String operator;

    public HesapMakinasiApp(){
        setTitle("Hesap Makinası");
        setSize(300,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        textField = new JTextField();
        add(textField,BorderLayout.NORTH);
        textField.setPreferredSize(new Dimension(300,50));
        textField.setForeground(Color.black);
        textField.setBackground(Color.green);
        Font font = new Font("Arial",Font.PLAIN,20);textField.setFont(font);
        textField.setBorder(null);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4,4,5,5));

        String[] buttonLabels =  {
                "7","8","9","/",
                "4","5","6","*",
                "1","2","3","-",
                "0","00","=","+"
        };
        for (String label : buttonLabels){
            JButton button = new JButton(label);
            button.addActionListener(new ButtonClickListener());
            button.setBackground(Color.YELLOW);
            button.setOpaque(true);
            buttonPanel.add(button);
        }
        add(buttonPanel,BorderLayout.CENTER);
    }
    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (Character.isDigit(command.charAt(0)) || command.equals(".")) {
                textField.setText(textField.getText() + command);
            }
            else if(command.equals("=")){
                double secondNumber = Double.parseDouble(textField.getText());
                double result = calculate(firstNumber,secondNumber,operator);
                textField.setText(Double.toString(result));
            }else {
                operator=command;
                firstNumber= Double.parseDouble(textField.getText());
                textField.setText("");
            }
        }
    }
    private double calculate(double num1,double num2,String op){
        switch (op){
            case "+":
                return num1 + num2;
            case "-":
                return num1-num2;
            case "*":
                return num1*num2;
            case "/":
                if (num2 !=0){
                    return num1 / num2;
                }else {
                    JOptionPane.showMessageDialog(this,"Böleme yanlış");
                    return 0;
                }
            default:
                return 0;
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
        {
            HesapMakinasiApp calculator = new HesapMakinasiApp();
            calculator.setVisible(true);
        });
    }

}