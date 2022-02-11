package impl;

import entity.Commodity;
import entity.Good;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static dao.DBUtil.getConnection;

/**
 * created by Bonnie on 2022/1/11
 */
public class CommodityImpl {
    // 插入商品信息
    public static void add( Commodity commodity ) {
        List<Good> goods = commodity.getGoods();
        if( null == goods || goods.size() == 0 )
        {
            return;
        }
        String sql = "INSERT INTO COMMODITY(name, picture, cost, inventory) values(?, ?, ?, ?)";

        try(Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sql))
        {
            int size = goods.size();
            for( int i = 0; i < size; ++i )
            {

                try(InputStream input = goods.get(i).getPicture())
                {
                    ps.setString(1, goods.get(i).getName());
                    ps.setBinaryStream(2, input, input.available());
                    ps.setBigDecimal(3, goods.get(i).getCost());
                    ps.setInt(4, goods.get(i).getInventory());
                    ps.executeUpdate();
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 获取商品信息
    public static void getGoods() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM COMMODITY";
        List<Good> goods = new ArrayList<>();

        try(Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery())
        {
//            System.out.println(rs.getRow());
            while( rs.next() )
            {
                Good good = new Good();
                good.setPicture(rs.getBinaryStream("picture"));
                good.setImgid(rs.getInt("imgid"));
                good.setName(rs.getString("name"));
                good.setCost(rs.getBigDecimal("cost"));
                goods.add(good);
            }
            Commodity.setGoods(goods);
        }
    }

    public int getInventory(int img_id) throws SQLException, ClassNotFoundException {
        int inventory = 0;
        String sql = "SELECT inventory FROM COMMODITY WHERE imgid = ?";

        try(Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql))
        {
            ps.setInt(1, img_id);
            try( ResultSet rs = ps.executeQuery() )
            {
                if( rs.next() )
                {
                    inventory = rs.getInt("inventory");
                }
            }
        }
        return inventory;
    }
}
