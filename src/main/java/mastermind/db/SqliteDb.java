package mastermind.db;

import javafx.util.Pair;
import mastermind.model.User;

import javax.inject.Singleton;
import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    public void addScore(String name, Integer score) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("INSERT INTO Score(gamer,score) VALUES(?,?)");
        ps.setString(1,name);
        ps.setInt(2,score);
        ps.executeUpdate();
    }

    public List<Pair<String, Integer>> getRanking() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("SELECT gamer, score FROM Score");
        List<Pair<String, Integer>> res = new ArrayList<>();
        while(result.next()){
            res.add(new Pair<String, Integer>(result.getString("gamer"), result.getInt("score")));
        }
        result.close();
        return res.stream().sorted((p1, p2) -> Integer.compare(p2.getValue(), p1.getValue())).collect(Collectors.toList());
    }

    public Pair<User, Integer> currentBest() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("SELECT gamer, e_mail, score FROM Score s JOIN Gamer g ON s.gamer = g.gamer_name");
        List<Pair<User, Integer>> res = new ArrayList<>();
        while(result.next()) {
            res.add(new Pair<User, Integer>(new User(result.getString("gamer"), result.getString("e_mail")), result.getInt("score")));
        }
        result.close();
        return res.stream().max(Comparator.comparing(Pair::getValue)).orElse(null);
    }

}
