package Visualisation;

import Factory.Factory;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class FactoryFrame extends JFrame {

    public static void main(String[] args) throws IOException {
        Factory factory = new Factory();
        FactoryFrame factoryFrame = new FactoryFrame(factory);
        factory.start();
        factoryFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt){
                factory.stop();
            }
        });
    }

    public FactoryFrame(Factory factory){
        setTitle("Factory");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().add(new FactoryMainCanvas(factory));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
