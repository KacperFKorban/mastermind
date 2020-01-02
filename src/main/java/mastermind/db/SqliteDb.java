package mastermind.db;

import javax.inject.Singleton;
import java.sql.*;

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
            statement.execute("create table Test ( id rowid, test varchar(200) );");
            statement.execute("pragma user_version = 1;");
        }
    }

}
