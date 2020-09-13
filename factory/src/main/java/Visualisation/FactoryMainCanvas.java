package Visualisation;

import Factory.Factory;
import Observers.Observer;
import Visualisation.Sliders.AccessoriesSlider;
import Visualisation.Sliders.BodySlider;
import Visualisation.Sliders.DealersSlider;
import Visualisation.Sliders.MotorSlider;

import javax.swing.*;
import java.awt.*;

import static Visualisation.Constants.Constants.*;

public class FactoryMainCanvas extends JPanel implements Observer {
    private final Image background = new ImageIcon("src/main/resources/Pictures/background1.png").getImage();
    private final JLabel motorStorageState;
    private final JLabel bodyStorageState;
    private final JLabel accessoriesStorageState;
    private final JLabel autoStorageState;

    private final JLabel countMotorSuppliers;
    private final JLabel countBodySuppliers;
    private final JLabel countAccessoriesSuppliers;

    private final Factory factory;

    public FactoryMainCanvas(Factory factory) {
        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        setLayout(null);
        setFocusable(true);

        this.factory = factory;
        factory.addObserver(this);

        countMotorSuppliers = createLabel(factory.getCountWorkingMotorSuppliers() + "->", 30, 90);
        countBodySuppliers = createLabel(factory.getCountWorkingBodySuppliers() + "->", 30, 180);
        countAccessoriesSuppliers = createLabel(factory.getCountWorkingAccessoriesSuppliers() + "->", 30, 270);

        motorStorageState = createLabel(factory.getStateMotorStorage(), 72, 40);
        bodyStorageState = createLabel(factory.getStateBodyStorage(), 72, 140);
        accessoriesStorageState = createLabel(factory.getStateAccessoriesStorage(), 72, 230);
        autoStorageState = createLabel(factory.getStateAutoStorage(), 410, 110);

        JLabel motorSpeed = createLabel("Motor speed: " + factory.getDelayMotorSupplier() / 1000, 15, 330);
        JLabel bodySpeed = createLabel("Body speed: " + factory.getDelayBodySupplier() / 1000, 150, 330);
        JLabel accessoriesSpeed = createLabel("Acc-s speed:" + factory.getDelayAccessoriesSuppliers() / 1000, 280, 330);
        JLabel dealersSpeed = createLabel("Dealers speed: " + factory.getDelayDealers() / 1000, 400, 330);

        add(motorSpeed);
        add(bodySpeed);
        add(accessoriesSpeed);
        add(dealersSpeed);

        add(new MotorSlider(factory, motorSpeed));
        add(new BodySlider(factory, bodySpeed));
        add(new AccessoriesSlider(factory, accessoriesSpeed));
        add(new DealersSlider(factory, dealersSpeed));

        add(countMotorSuppliers);
        add(countBodySuppliers);
        add(countAccessoriesSuppliers);

        add(motorStorageState);
        add(bodyStorageState);
        add(accessoriesStorageState);
        add(autoStorageState);
    }

    private JLabel createLabel(String description, int x, int y){
        JLabel label = new JLabel(description);
        label.setForeground(Color.BLACK);
        label.setFont(new Font("Segoe Print", Font.BOLD, 14));
        label.setBounds(x, y, LABEL_WIDTH, LABEL_HEIGHT);
        return label;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, FRAME_WIDTH, FRAME_HEIGHT, null);
    }

    @Override
    public void update() {
        motorStorageState.setText(factory.getStateMotorStorage());
        countMotorSuppliers.setText(factory.getCountWorkingMotorSuppliers() + "->");
        bodyStorageState.setText(factory.getStateBodyStorage());
        countBodySuppliers.setText(factory.getCountWorkingBodySuppliers() + "->");
        accessoriesStorageState.setText(factory.getStateAccessoriesStorage());
        countAccessoriesSuppliers.setText(factory.getCountWorkingAccessoriesSuppliers() + "->");
        autoStorageState.setText(factory.getStateAutoStorage());
    }
}
