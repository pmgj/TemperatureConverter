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
import model.IdentityConverter;
import model.KelvinToCelsiusConverter;

public class MainWindowV2 extends KeyAdapter {
    private JFrame jframe;
    private JComboBox<Converter> jcbFromTemperature;
    private JComboBox<Converter> jcbToTemperature;
    private JTextField inputTemp;
    private JLabel outputTemp;

    private Converter[] fromTemp = {new IdentityConverter(), new FahrenheitToCelsiusConverter(), new KelvinToCelsiusConverter() };
    private Converter[] toTemp = {new IdentityConverter(), new CelsiusToFahrenheitConverter(), new CelsiusToKelvinConverter() };

    public void keyReleased(KeyEvent ke) {
        try {
            double temp = Double.parseDouble(inputTemp.getText());
            int i1 = jcbFromTemperature.getSelectedIndex();
            int i2 = jcbToTemperature.getSelectedIndex();
            Converter converter1 = fromTemp[i1];
            Converter converter2 = toTemp[i2];
            outputTemp.setText(String.format("%.2f", converter2.convert(converter1.convert(temp))));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public MainWindowV2() {
        this.jframe = new JFrame("Temperature Converter");
        this.jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = this.jframe.getContentPane();
        GridLayout layout = new GridLayout(2, 2, 10, 10);
        c.setLayout(layout);
        jcbFromTemperature = new JComboBox<>(fromTemp);
        jcbFromTemperature.addKeyListener(this);
        c.add(jcbFromTemperature);
        inputTemp = new JTextField();
        inputTemp.addKeyListener(this);
        c.add(inputTemp);
        jcbToTemperature = new JComboBox<>(toTemp);
        jcbToTemperature.setSelectedIndex(1);
        jcbToTemperature.addKeyListener(this);
        c.add(jcbToTemperature);
        outputTemp = new JLabel();
        c.add(outputTemp);
        this.jframe.setSize(300, 100);
        this.jframe.setVisible(true);
        inputTemp.requestFocus();
    }

    public static void main(String[] args) {
        new MainWindowV2();
    }
}
