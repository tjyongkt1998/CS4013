import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Microwave extends JFrame {

    public Microwave() {
        JPanel panel1 = new JPanel(new GridLayout(4, 3));

        for (int i = 1; i <= 9; i++) {
            panel1.add(new JButton(" " + i));
        }

        panel1.add(new JButton(" " + 0));
        panel1.add(new JButton("Begin"));
        panel1.add(new JButton("End"));

        JPanel panel2 = new JPanel(new BorderLayout());
        panel2.add(new JTextField("Time to be displayed here"), BorderLayout.NORTH);
        panel2.add(panel1, BorderLayout.CENTER);

        add(new JButton("Place Food Here"), BorderLayout.CENTER);
        add(panel2, BorderLayout.EAST);
    }

    public static void main(String[] args) {
        Microwave microwave = new Microwave();
        microwave.pack();
        microwave.setTitle("Microwave Oven");
        microwave.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        microwave.setLocationRelativeTo(null);
        microwave.setVisible(true);
       
        microwave.setResizable(false);
       
    }

}