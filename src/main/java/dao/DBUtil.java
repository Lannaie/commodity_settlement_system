package dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * created by Bonnie on 2022/1/10
 */
public class DBUtil {

    private static String driver;
    private static String url;
    private static String username;
    private static String password;

    static {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void init() throws IOException {
        Properties pro = PropertiesUtil.getProperties();
        // 获取指定参数
        driver = pro.getProperty("driver");
        url = pro.getProperty("url");
        username = pro.getProperty("username");
        password = pro.getProperty("password");
    }

    /**
     * 获取数据库连接对象
     * */
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        // 注册JDBC驱动
        Class.forName(driver);

        System.out.println("连接数据库 -----------------");
        Connection conn = DriverManager.getConnection(url, username, password);
        return conn;
    }

    /**
     * 执行sql的 update、insert、delete 语句
     * conn: 数据库连接对象
     * sql: 执行语句
     * params: 执行语句中的参数，形如 values(?,?,?,...)
     * */
    public static int executeUpdate( Connection conn, String sql, Object[] params )
    {
        int num = -1;
        try( Connection connection = conn;
             PreparedStatement ps = connection.prepareStatement(sql); ) {
            if( params != null )
            {
                for( int i = 0; i < params.length; ++i )
                {
                    ps.setObject(i + 1, params[i]);
                }
            }
            num =  ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }

    /**
     * 执行sql的 select 语句
     * conn: 数据库连接对象
     * sql: 执行语句
     * params: 执行语句中的参数，形如 values(?,?,?,...)
     * */
    public static ResultSet executeQuery( PreparedStatement ps, Object[] params ) throws SQLException {
        for( int i = 0; i < params.length; ++i )
        {
            ps.setObject(i + 1, params[i]);
        }
        return ps.executeQuery();
    }

    /**
     * 执行sql的 call procedure 语句
     * username: 用户名
     * img_id: 商品id
     * */
    public static int execute_procedure(String username, int img_id) throws SQLException, ClassNotFoundException {
        int res = -3;
        try( Connection conn = getConnection(); CallableStatement clbStmt = conn.prepareCall("{CALL settlement_procedure(?,?,?,?)}") )
        {
            // 设置输入参数
            clbStmt.setString(1, username);
            clbStmt.setInt(2, img_id);
            // 注册输出参数
            clbStmt.registerOutParameter(3, Types.INTEGER);
            clbStmt.registerOutParameter(4, Types.INTEGER);
            clbStmt.executeQuery();
//            int insert_result = clbStmt.getInt(3);
            res = clbStmt.getInt(4);
        }
        return res;
    }
}
