import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
    public static void main(String[] args) {
        ConnectionDb db = new ConnectionDb();
        db.init();

        JFrame frame = new JFrame("Medicine App");
        frame.setSize(new Dimension(600, 400));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                System.exit(1);
            }
        });


        MedPanel medPanel = new MedPanel(db);
        frame.add(medPanel, BorderLayout.CENTER);
        medPanel.init();


        frame.setVisible(true);
        frame.pack();
//        db.close();
    }
}
