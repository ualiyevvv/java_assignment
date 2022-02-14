import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class UpdatePanel extends JPanel {
    private ConnectionDb connect;

    private String []oldData;

    private JButton updateButton = new JButton("Update");
    private JTextField nameTextField = new JTextField();
    private JTextField priceTextField = new JTextField();
    private JTextField dateTextField = new JTextField();
    private JTextField manufacturerTextField = new JTextField();
    private JLabel nameLabel = new JLabel("input name:");
    private JLabel priceLabel = new JLabel("input price:");
    private JLabel dateLabel = new JLabel("input date:");
    private JLabel manufacturerLabel = new JLabel("input manufacturer:");


    public UpdatePanel(ConnectionDb connect) {
        this.connect = connect;
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(300,200));

    }

    public void displayOldData(String []oldData) {
        this.oldData = oldData;
        nameTextField.setText(oldData[1]);
        priceTextField.setText(oldData[2]);
        dateTextField.setText(oldData[3]);
        manufacturerTextField.setText(oldData[4]);
    }

    public void init() {

        add(nameLabel, new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
                new Insets(1, 1, 1, 1), 0, 0));

        add(nameTextField, new GridBagConstraints(1, 0, 1, 1, 1, 1,
                GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
                new Insets(1, 1, 1, 1), 0, 0));

        add(priceLabel, new GridBagConstraints(0, 1, 1, 1, 1, 1,
                GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
                new Insets(1, 1, 1, 1), 0, 0));

        add(priceTextField, new GridBagConstraints(1, 1, 1, 1, 1, 1,
                GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
                new Insets(1, 1, 1, 1), 0, 0));

        add(dateLabel, new GridBagConstraints(0, 2, 1, 1, 1, 1,
                GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
                new Insets(1, 1, 1, 1), 0, 0));

        add(dateTextField, new GridBagConstraints(1, 2, 1, 1, 1, 1,
                GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
                new Insets(1, 1, 1, 1), 0, 0));

        add(manufacturerLabel, new GridBagConstraints(0, 3, 1, 1, 1, 1,
                GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
                new Insets(1, 1, 1, 1), 0, 0));

        add(manufacturerTextField, new GridBagConstraints(1, 3, 1, 1, 1, 1,
                GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
                new Insets(1, 1, 1, 1), 0, 0));

//        add(Box.createHorizontalStrut(15));
        add(updateButton, new GridBagConstraints(0, 4, 2, 1, 1, 1,
                GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
                new Insets(1, 1, 1, 1), 0, 0));

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String []row = {
                        nameTextField.getText(),
                        priceTextField.getText(),
                        dateTextField.getText(),
                        manufacturerTextField.getText()
                };
                try {
                    updateProduct(row);
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
                nameTextField.setText(row[0]);
                priceTextField.setText(row[1]);
                dateTextField.setText(row[2]);
                manufacturerTextField.setText(row[3]);

                JOptionPane.showConfirmDialog(new JFrame(),
                        "Data successfully added to the database", "Notification",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
            }
        });

    }

    public void updateProduct(String []row) {
        System.out.println(Arrays.toString(row));
        try {
            connect.sqlQuery("UPDATE \"Medicine\" SET name = '" + row[0] + "', price = " + row[1] + ", expiration_date = '" + row[2] + "', manufacturer = '" + row[3] + "' WHERE id = " + oldData[0]);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
