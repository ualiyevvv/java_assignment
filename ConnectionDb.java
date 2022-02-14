import java.sql.*;

public class ConnectionDb {
    private Connection connect;

    private String url = "jdbc:postgresql://localhost:5432/week7";
    private String user = "postgres";
    private String pass = "root";
    //tables: id, name, price, ex_date, manufacturer


    public ConnectionDb(){

    }

    public void init() {
        try {
            Class.forName("org.postgresql.Driver");
            connect = DriverManager.getConnection(url, user, pass);
        }
        catch(ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

    }

    public void close() {
        try {
            connect.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }


    public ResultSet resultSetQuery(String query) {
        try {
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery(query);
            return rs;
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }



    public void sqlQuery(String query) {
        try {
            Statement st = connect.createStatement();
            st.executeUpdate(query);
            st.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

}
