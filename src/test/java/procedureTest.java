import dao.DBUtil;

import java.sql.*;

/**
 * created by Bonnie on 2022/2/11
 */
public class procedureTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection conn = DBUtil.getConnection();
        try( CallableStatement clbStmt = conn.prepareCall("{CALL settlement_procedure(?,?,?,?)}") )
        {
            // 设置输入参数
            clbStmt.setString(1, "bonnie");
            clbStmt.setInt(2, 4);
            // 注册输出参数
            clbStmt.registerOutParameter(3, Types.INTEGER);
            clbStmt.registerOutParameter(4, Types.INTEGER);
            clbStmt.executeQuery();
            int insert_result = clbStmt.getInt(3);
            int update_result = clbStmt.getInt(4);
            System.out.println("insert_result-" + insert_result + " update_result-" + update_result);
        }
    }
}
