package Visualisation.Sliders;

import Factory.Factory;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

import static Visualisation.Constants.Constants.*;

public class BodySlider extends JSlider {
    public BodySlider(Factory factory, JLabel label) {
        super(JSlider.HORIZONTAL, SUPPLIER_MIN, SUPPLIER_MAX, SUPPLIER_MAX);
        setMajorTickSpacing(COUNT_VALUES);
        setPaintTicks(true);
        setPaintLabels(true);

        addChangeListener(changeEvent -> {
            JSlider slider = (JSlider) changeEvent.getSource();
            int value = slider.getValue();
            factory.changeDelayBodySupplier(value * 1000);
            label.setText("Body Speed: " + factory.getDelayBodySupplier() / 1000);
        });

        setBounds(150, 340, SLIDER_WIDTH, SLIDER_HEIGHT);
        setPreferredSize(new Dimension(SLIDER_WIDTH, SLIDER_HEIGHT));
        setOpaque(false);
        setBackground(Color.BLACK);
        setForeground(Color.BLACK);
        setFont(new Font("Segoe Print", Font.BOLD, 8));
    }
}
