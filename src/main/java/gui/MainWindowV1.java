package gui;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import model.CelsiusToFahrenheitConverter;
import model.CelsiusToKelvinConverter;
import model.Converter;
import model.FahrenheitToCelsiusConverter;
import model.FahrenheitToKelvinConverter;
import model.IdentityConverter;
import model.KelvinToCelsiusConverter;
import model.KelvinToFahrenheitConverter;

public class MainWindowV1 extends KeyAdapter {
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

    public void keyReleased(KeyEvent ke) {
        try {
            double temp = Double.parseDouble(inputTemp.getText());
            int row = fromTemp.getSelectedIndex();
            int col = toTemp.getSelectedIndex();
            Converter converter = matrix[row][col];
            outputTemp.setText(String.format("%.2f", converter.convert(temp)));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public MainWindowV1() {
        this.jframe = new JFrame("Temperature Converter");
        this.jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = this.jframe.getContentPane();
        GridLayout layout = new GridLayout(2, 2, 10, 10);
        c.setLayout(layout);
        String[] temps = new String[] { "Celsius", "Fahrenheit", "Kelvin" };
        fromTemp = new JComboBox<String>(temps);
        fromTemp.addKeyListener(this);
        c.add(fromTemp);
        inputTemp = new JTextField();
        inputTemp.addKeyListener(this);
        c.add(inputTemp);
        toTemp = new JComboBox<String>(temps);
        toTemp.setSelectedIndex(1);
        toTemp.addKeyListener(this);
        c.add(toTemp);
        outputTemp = new JLabel();
        c.add(outputTemp);
        this.jframe.setSize(300, 100);
        // this.jframe.pack();
        this.jframe.setVisible(true);
        inputTemp.requestFocus();
    }

    public static void main(String[] args) {
        new MainWindowV1();
    }
}
