package servlet;

import dao.DBUtil;
import entity.Settlement;
import impl.CommodityImpl;
import impl.SettlementImpl;
import impl.UserImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * created by Bonnie on 2022/1/15
 */
@WebServlet("/settlementServlet")
public class settlementServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置格式
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        // 获取参数
        String username = req.getParameter("username");
        String payword = req.getParameter("password");
        int imgid = Integer.parseInt(req.getParameter("img_id"));

        // 对比密码
        try {
            if(UserImpl.findUser(username, payword, 1)){
                String msg = "支付成功！请点击确定按钮回到商品页面。";
                // 1. 判断库存是否为0
                if( new CommodityImpl().getInventory(imgid) <= 0 )
                {
                    msg = "支付失败！库存不足。";
                }else
                {   // 2. 添加明细
//                    if(SettlementImpl.add(new Object[]{username, imgid}) < 0)
//                    {
//                        msg = "支付失败！代码出现错误";
//                    }else
//                    {   // 3. 更新数据库中的库存
//                        String update_sql = "UPDATE COMMODITY SET inventory = inventory - 1 WHERE imgid = ? AND inventory > 0";
//                        if( DBUtil.executeUpdate(DBUtil.getConnection(), update_sql, new Object[]{imgid}) < 0 )
//                        {
//                            msg = "支付失败！代码出现错误";
//                        }
//                    }
                    int update_r = DBUtil.execute_procedure(username, imgid);
                    if( update_r >= 0 )
                    {
                        if( update_r == 0)
                        {
                            msg = "支付失败！库存不足";
                        }
                    }else
                    {
                        msg = "支付失败！代码出现错误";
                    }
                }

                // 返回成功通知
                String url = "location.href='choose_goods.jsp?username=" + username + "'";
                resp.getWriter().print("<script>" +
                        "if(confirm('" + msg + "'))" +
                        "{" +
                        url+
                        "}" +
                        "</script>");
            }else{
                resp.getWriter().print("<script>" +
                        "alert('支付失败，密码错误');" +
                        "window.history.back(-1);" +
                        "</script>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
//        req.getRequestDispatcher("choose_goods.jsp?username=" + username).forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
