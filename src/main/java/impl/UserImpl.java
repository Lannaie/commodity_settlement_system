package impl;

import dao.DBUtil;
import entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static dao.DBUtil.executeUpdate;
import static dao.DBUtil.getConnection;

/**
 * created by Bonnie on 2022/1/10
 */
public class UserImpl {
    public static int add( User user ) throws SQLException, ClassNotFoundException {
        String sql = "INSERT IGNORE INTO USER(username, password, payword) VALUES(?, ?, ?)";
        try(Connection conn = getConnection(); ) {
            return executeUpdate(conn, sql, new Object[]{user.getUsername(), user.getPassword(), user.getPayword()});
        }
    }

    public static boolean findUser(String username, String password, int f) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * from USER where username = ?";
        boolean res = false;
        try(ResultSet rs = DBUtil.executeQuery(getConnection().prepareStatement(sql), new Object[]{username})) {
            if( rs.next() )
            {
                if( f == 0 && password.equals(rs.getString("password")) )
                {
                    res = true;
                }else if( f == 1 && password.equals(rs.getString("payword")) )
                {
                    res = true;
                }
            }
        }
        return res;
    }
}
