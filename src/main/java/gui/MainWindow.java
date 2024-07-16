package gui;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import model.CelsiusToFahrenheitConverter;
import model.CelsiusToKelvinConverter;
import model.Converter;
import model.FahrenheitToCelsiusConverter;
import model.FahrenheitToKelvinConverter;
import model.IdentityConverter;
import model.KelvinToCelsiusConverter;
import model.KelvinToFahrenheitConverter;

public class MainWindow {
    private JFrame jframe;
    private JComboBox<String> fromTemp;
    private JComboBox<String> toTemp;
    private JTextField inputTemp;
    private JLabel outputTemp;

    private Converter[][] matrix = {
            { new IdentityConverter(), new CelsiusToFahrenheitConverter(), new CelsiusToKelvinConverter() },
            { new FahrenheitToCelsiusConverter(), new IdentityConverter(), new FahrenheitToKelvinConverter() },
            { new KelvinToCelsiusConverter(), new KelvinToFahrenheitConverter(), new IdentityConverter() }
    };

    public MainWindow() {
        this.jframe = new JFrame("Temperature Converter");
        this.jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = this.jframe.getContentPane();
        GridLayout layout = new GridLayout(2, 2, 10, 10);
        c.setLayout(layout);
        String[] temps = new String[] { "Celsius", "Fahrenheit", "Kelvin" };
        fromTemp = new JComboBox<String>(temps);
        c.add(fromTemp);
        inputTemp = new JTextField();
        inputTemp.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                warn();
            }

            public void removeUpdate(DocumentEvent e) {
                warn();
            }

            public void insertUpdate(DocumentEvent e) {
                warn();
            }

            public void warn() {
                try {
                    double temp = Double.parseDouble(inputTemp.getText());
                    System.out.println(temp);
                    int row = fromTemp.getSelectedIndex();
                    int col = toTemp.getSelectedIndex();
                    System.out.println(row);
                    System.out.println(col);
                    Converter converter = matrix[row][col];
                    outputTemp.setText(String.valueOf(converter.convert(temp)));
                } catch (Exception ex) {

                }
            }
        });
        c.add(inputTemp);
        toTemp = new JComboBox<String>(temps);
        c.add(toTemp);
        outputTemp = new JLabel();
        c.add(outputTemp);
        this.jframe.pack();
        this.jframe.setVisible(true);
        inputTemp.requestFocus();
    }

    public static void main(String[] args) {
        new MainWindow();
    }
}
