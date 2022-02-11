package impl;

import entity.Settlement;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

import static dao.DBUtil.executeUpdate;
import static dao.DBUtil.getConnection;

/**
 * created by Bonnie on 2022/1/16
 */
public class SettlementImpl {
    public static int add(Object[] obj) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO SETTLEMENT(username, imgid) VALUES(?, ?)";
        try(Connection conn = getConnection(); ) {
            return executeUpdate(conn, sql, obj);
        }
    }
}
