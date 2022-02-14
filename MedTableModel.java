import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;

public class MedTableModel extends AbstractTableModel {
    private int columnCount = 5;
    private ArrayList<String []> dataArrayList;

    public MedTableModel(){
        dataArrayList = new ArrayList<String []>();
        for(int i = 0; i < dataArrayList.size(); i++){
            dataArrayList.add(new String[getColumnCount()]);
        }
    }

    @Override
    public int getRowCount() { return dataArrayList.size(); }

    @Override
    public int getColumnCount() {
        return columnCount;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex){
            case 0: return "#id";
            case 1: return "name";
            case 2: return "price";
            case 3: return "ex_date";
            case 4: return "manufacturer";
        }
        return "";
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String []rows = dataArrayList.get(rowIndex);

        return rows[columnIndex];
    }

    public void addData(String []row){
        String []rowTable = new String[getColumnCount()];
        rowTable = row;
        dataArrayList.add(rowTable);
    }

    public void addData(ConnectionDb connect) {
        dataArrayList.clear();

        ResultSet resultSet = connect.resultSetQuery("SELECT * FROM \"Medicine\" ORDER BY id ASC");
        try {
            while (resultSet.next()) {
                String []row = {
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getString("price"),
                        resultSet.getString("expiration_date"),
                        resultSet.getString("manufacturer")
                };

                addData(row);
            }
            resultSet.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    public void searchData(ConnectionDb connect, String searchText) {
        dataArrayList.clear();

        ResultSet resultSet = connect.resultSetQuery("SELECT * FROM \"Medicine\" WHERE name LIKE '%"+ searchText +"%' or manufacturer LIKE '%"+ searchText +"%' ");
        try {
            while (resultSet.next()) {
                String []row = {
                        resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getString("price"),
                        resultSet.getString("expiration_date"),
                        resultSet.getString("manufacturer")
                };

                addData(row);
            }
            resultSet.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void deleteRow(ConnectionDb connect, int row) {
        if (row == -1) {
            JOptionPane.showConfirmDialog(null,
                    "You have not selected an element!", "Error", JOptionPane.DEFAULT_OPTION);

        } else {
            String []dataInRow = getDataInRow(row);;
            String id = dataInRow[0];
            int input = JOptionPane.showConfirmDialog(new JFrame(),
                    "Are you sure you want to delete it? It will delete from database forever", "Delete Dialog",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

            if (input == 0) {
                try {
                    dataArrayList.remove(row);
                    connect.sqlQuery("DELETE FROM \"Medicine\" WHERE id=" + id + ";\n");
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            System.out.println(input);
        }

    }

    public String []getDataInRow (int row) {
        String []data = (String []) dataArrayList.get(row);
        return data;
    }
}
