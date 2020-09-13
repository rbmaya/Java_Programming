import JumperCanvases.*;

import javax.swing.*;

public class JumperFrame extends JFrame {

    public static void main(String[] args) {
        new JumperFrame();
    }

    private JumperFrame(){
        setTitle("Jumper");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        getContentPane().add(new JumperMainMenuCanvas(getContentPane()));

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
