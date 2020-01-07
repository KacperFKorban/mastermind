package mastermind.db;

import javax.inject.Singleton;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class SqliteDb {

    private Connection connection;

    public SqliteDb() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:db.sqlite");
        initSchema();
    }

    private void initSchema() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet userVersionRes = statement.executeQuery("pragma user_version;");
        int userVersion = userVersionRes.getInt(1);
        userVersionRes.close();
        if (userVersion == 0) {
            // TODO change the statement below to create the appropriate db schema
            statement.execute("CREATE TABLE IF NOT EXISTS Gamer ( gamer_name VARCHAR(200) NOT NULL UNIQUE PRIMARY KEY, e_mail VARCHAR(200));");
            statement.execute("CREATE TABLE IF NOT EXISTS Score (id INTEGER PRIMARY KEY, gamer VARCHAR(200), score INTEGER," +
                                    "FOREIGN KEY(gamer) REFERENCES Gamer(gamer_name) )");
            statement.execute("pragma user_version = 1;");
        }
    }

    public List<String> getGamers() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("SELECT gamer_name FROM Gamer");
        List<String> gamers = new ArrayList<>();
        while(result.next()){
            gamers.add(result.getString("gamer_name"));
        }
        result.close();
        return gamers;
    }

    public void addGamer(String name, String e_mail) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("INSERT INTO Gamer(gamer_name,e_mail) VALUES(?,?)");
        ps.setString(1,name);
        ps.setString(2,e_mail);
        ps.executeUpdate();
    }



}
