import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class CreatePanel extends JPanel {
    private ConnectionDb connect;

    private JButton submitButton = new JButton("Submit");
    private JTextField nameTextField = new JTextField();
    private JTextField priceTextField = new JTextField();
    private JTextField dateTextField = new JTextField();
    private JTextField manufacturerTextField = new JTextField();
    private JLabel nameLabel = new JLabel("input name:");
    private JLabel priceLabel = new JLabel("input price:");
    private JLabel dateLabel = new JLabel("input date:");
    private JLabel manufacturerLabel = new JLabel("input manufacturer:");


    public CreatePanel(ConnectionDb connect) {
        this.connect = connect;
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(300,200));

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
        add(submitButton, new GridBagConstraints(0, 4, 2, 1, 1, 1,
                GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
                new Insets(1, 1, 1, 1), 0, 0));

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String []row = {
                        nameTextField.getText(),
                        priceTextField.getText(),
                        dateTextField.getText(),
                        manufacturerTextField.getText()
                };
                try {
                    addProduct(row);
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
                // если чищу текст то нужно также почистить айдишник, иначе либо инсерт пустых полей либо потеря
                //если почищу айди то нужно ставить защиту на наличие айди
                //либо не чистить текст
                //либо сразу программно закрывать диалоговое окно
                nameTextField.setText("");
                priceTextField.setText("");
                dateTextField.setText("");
                manufacturerTextField.setText("");

                JOptionPane.showConfirmDialog(new JFrame(),
                        "Data successfully added to the database", "Notification",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
            }
        });

    }

    public void addProduct(String []row) {
        System.out.println(Arrays.toString(row));
        try {
            connect.sqlQuery("INSERT INTO \"Medicine\" (name, price, expiration_date, manufacturer) VALUES('" + row[0] + "', "+ row[1] +", '" + row[2] + "', '" + row[3] + "')");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
