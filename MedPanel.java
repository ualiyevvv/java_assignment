import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MedPanel extends JPanel {
    private ConnectionDb connect;
    private MedTableModel mtm = new MedTableModel();
    private JTable medTable = new JTable(mtm);


    private JButton addButton = new JButton("Add");
    private JButton editButton = new JButton("Edit");
    private JButton deleteButton = new JButton("Delete");
    private JButton reloadButton = new JButton("Reload");
    private JButton searchButton = new JButton("Search");

    public MedPanel(ConnectionDb connect) {
        this.connect = connect;
        setLayout(new GridBagLayout());

    }
    public void init() {
        JScrollPane medTableScrollPage = new JScrollPane(medTable);
        medTableScrollPage.setSize(400,500);
        medTableScrollPage.setPreferredSize(new Dimension(600,400));

        add(medTableScrollPage, new GridBagConstraints(0, 0, 5, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        add(addButton, new GridBagConstraints(0, 1, 1, 1, 1, 1,
                GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
                new Insets(1, 1, 1, 1), 0, 0));

        add(editButton, new GridBagConstraints(1, 1, 1, 1, 1, 1,
                GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
                new Insets(1, 1, 1, 1), 0, 0));

        add(deleteButton, new GridBagConstraints(2, 1, 1, 1, 1, 1,
                GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
                new Insets(1, 1, 1, 1), 0, 0));

        add(reloadButton, new GridBagConstraints(3, 1, 1, 1, 1, 1,
                GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
                new Insets(1, 1, 1, 1), 0, 0))
        ;
        add(searchButton, new GridBagConstraints(4, 1, 1, 1, 1, 1,
                GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
                new Insets(1, 1, 1, 1), 0, 0));

        mtm.addData(connect);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = JOptionPane.showInputDialog(
                        null,
                        "Input search text");
                mtm.searchData(connect, searchText);
                medTable.repaint();
            }
        });
        reloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                db.init();
                mtm.addData(connect);
                medTable.repaint();
//                db.close();
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreatePanel createPanel = new CreatePanel(connect);
                createPanel.init();
                int isToAdd = JOptionPane.showConfirmDialog(null, createPanel,
                        "Adding a product", JOptionPane.OK_CANCEL_OPTION);
                System.out.println("@@---====" + isToAdd);

                mtm.addData(connect);
                medTable.repaint();
            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = medTable.getSelectedRow();
                if (row == -1) {
                    JOptionPane.showConfirmDialog(null,
                            "You have not selected an element!", "Error", JOptionPane.DEFAULT_OPTION);

                } else {
                    String []data = mtm.getDataInRow(row);
                    UpdatePanel updatePanel = new UpdatePanel(connect);
                    updatePanel.init();
                    updatePanel.displayOldData(data);

                    int isToAdd = JOptionPane.showConfirmDialog(null, updatePanel,
                            "Editing a product", JOptionPane.OK_CANCEL_OPTION);
                    System.out.println("@@---====" + isToAdd);

                    mtm.addData(connect);
                    medTable.repaint();
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    int row = medTable.getSelectedRow();
                    mtm.deleteRow(connect, row);
                    repaint();
                }
                catch(Exception ex){
                    System.out.println(ex);
                    JTextArea textArea = new JTextArea (e.toString());
                    JScrollPane scrollPane = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                    scrollPane.setPreferredSize(new Dimension(400, 200));
                    JOptionPane.showMessageDialog(null, scrollPane,
                            "Error", JOptionPane.ERROR_MESSAGE);

                }
            }
        });

    }


}
