package Visualisation.Sliders;

import Factory.Factory;

import javax.swing.*;
import java.awt.*;

import static Visualisation.Constants.Constants.*;

public class MotorSlider extends JSlider{
    public MotorSlider(Factory factory, JLabel label){
        super(JSlider.HORIZONTAL, SUPPLIER_MIN, SUPPLIER_MAX, SUPPLIER_MAX);
        setMajorTickSpacing(COUNT_VALUES);
        setPaintTicks(true);
        setPaintLabels(true);

        addChangeListener(e -> {
            JSlider slider = (JSlider)e.getSource();
            int value = slider.getValue();
            factory.changeDelayMotorSupplier(value * 1000);
            label.setText("Motor Speed: " + factory.getDelayMotorSupplier() / 1000);
        });

        setBounds(20, 340, SLIDER_WIDTH, SLIDER_HEIGHT);
        setPreferredSize(new Dimension(SLIDER_WIDTH, SLIDER_HEIGHT));
        setOpaque(false);
        setBackground(Color.BLACK);
        setForeground(Color.BLACK);
        setFont(new Font("Segoe Print", Font.BOLD, 8));
    }
}
