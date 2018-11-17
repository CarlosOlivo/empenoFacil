/*
 * Copyright (C) 2018 lunix
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package empenofacil.reportes.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author lunix
 */
public class Connect {

    public static final int MYSQL = 0;
    public static final int MSSQLSERVER = 1;
    public static final int ORACLE = 2;

    private Connection conn;
    private String host;
    private String db;
    private String username;
    private String password;

    private static Connect connect;

    public Connect(int type, String host, int port, String db, String username, String password) {
        String driver = "";
        String url = "";
        switch (type) {
            case MYSQL:
                driver = "com.mysql.jdbc.Driver";
                url = "jdbc:mysql://" + host + ":" + port + "/" + db;
                break;
            case MSSQLSERVER:
                driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
                //url = "jdbc:sqlserver://"+host+":"+port+";db="+db;
                url = "jdbc:sqlserver://" + host + ":" + port + ";db=" + db + ";SelectMethod=direct";
                //Log.log(url);
                break;
            case ORACLE:
                driver = "oracle.jdbc.driver.OracleDriver";
                url = "jdbc:oracle:thin:@//" + host + ":" + port + "/" + db;
                break;
        }
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.host = host;
        this.db = db;
        this.username = username;
        this.password = password;
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("SQLException" + e.getMessage());
        }
        connect = this;
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static Connect getConnect() {
        return connect;
    }

    public static void setConnect(Connect connect) {
        Connect.connect = connect;
    }

    public Connection connection() {
        try {
            return conn;
        } finally {

        }
    }

    public Statement query(String sQuery) throws SQLException {
        Statement s = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        s.executeQuery(sQuery);
        return s;
    }

    public Statement udpdate(String sQuery) throws SQLException {
        Statement s = conn.createStatement();
        s.executeUpdate(sQuery);
        return s;
    }

    public void close(Statement s) {
        try {
            s.close();
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage() + "\n"
                    + e.getErrorCode());
        }
    }

    public void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage() + "\n" + e.getErrorCode());
        }
    }
}
