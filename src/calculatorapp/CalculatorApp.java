/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package calculatorapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorApp extends JFrame {
    private JTextField display;
    private String operator;
    private double firstOperand;

    public CalculatorApp() {
        setTitle("Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        display = new JTextField();
        display.setEditable(false);
        display.setHorizontalAlignment(SwingConstants.RIGHT);

        JPanel buttonPanel = createButtonPanel();

        setLayout(new BorderLayout());
        add(display, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4));

        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(new ButtonClickListener());
            panel.add(button);
        }

        return panel;
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            String buttonText = source.getText();

            if (buttonText.matches("[0-9]")) {
                display.setText(display.getText() + buttonText);
            } else if ("+-*/".contains(buttonText)) {
                operator = buttonText;
                firstOperand = Double.parseDouble(display.getText());
                display.setText("");
            } else if (buttonText.equals("=")) {
                if (operator != null) {
                    double secondOperand = Double.parseDouble(display.getText());
                    double result = performOperation(firstOperand, secondOperand, operator);
                    display.setText(String.valueOf(result));
                    operator = null;
                }
            } else if (buttonText.equals(".")) {
                if (!display.getText().contains(".")) {
                    display.setText(display.getText() + buttonText);
                }
            }
        }

        private double performOperation(double firstOperand, double secondOperand, String operator) {
            switch (operator) {
                case "+":
                    return firstOperand + secondOperand;
                case "-":
                    return firstOperand - secondOperand;
                case "*":
                    return firstOperand * secondOperand;
                case "/":
                    if (secondOperand != 0) {
                        return firstOperand / secondOperand;
                    } else {
                        JOptionPane.showMessageDialog(CalculatorApp.this, "Cannot divide by zero", "Error", JOptionPane.ERROR_MESSAGE);
                        return 0;
                    }
                default:
                    return 0;
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalculatorApp calculator = new CalculatorApp();
            calculator.setVisible(true);
        });
    }
}
