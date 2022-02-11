package servlet;

import entity.User;
import impl.CommodityImpl;
import impl.UserImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * created by Bonnie on 2022/1/8
 */
@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置格式
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");

        // 获取账号和密码
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        //确认账号和密码存在
        try {
            if( !UserImpl.findUser(username, password, 0) )
            {
                resp.getWriter().print("<script>" +
                        "alert('账号或密码错误');" +
                        "window.history.back(-1);" +
                        "</script>");
            }else
            {
                // 获取商品列表
                CommodityImpl.getGoods();
                // 跳转页面
                req.getRequestDispatcher("choose_goods.jsp?username=" + username).forward(req, resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
