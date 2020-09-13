package Visualisation.Sliders;

import Factory.Factory;
import Factory.FactoryStaff.Dealer;

import javax.swing.*;
import java.awt.*;

import static Visualisation.Constants.Constants.*;

public class DealersSlider extends JSlider {
    public DealersSlider(Factory factory, JLabel label){
        super(JSlider.HORIZONTAL, SUPPLIER_MIN, SUPPLIER_MAX, SUPPLIER_MAX);
        setMajorTickSpacing(COUNT_VALUES);
        setPaintTicks(true);
        setPaintLabels(true);

        addChangeListener(e -> {
            JSlider slider = (JSlider)e.getSource();
            int value = slider.getValue();
            factory.changeDelayDealers(value * 1000);
            label.setText("Dealers Speed: " + factory.getDelayDealers() / 1000);
        });

        setBounds(410, 340, SLIDER_WIDTH, SLIDER_HEIGHT);
        setPreferredSize(new Dimension(SLIDER_WIDTH, SLIDER_HEIGHT));
        setOpaque(false);
        setBackground(Color.BLACK);
        setForeground(Color.BLACK);
        setFont(new Font("Segoe Print", Font.BOLD, 8));
    }
}
