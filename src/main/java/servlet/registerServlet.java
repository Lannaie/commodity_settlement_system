package servlet;

import entity.User;
import impl.UserImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * created by Bonnie on 2022/1/12
 */
@WebServlet("/registerServlet")
public class registerServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("调用post");
        //设置格式
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");

        User user = new User();
        user.setUsername(req.getParameter("username"));
        user.setPassword(req.getParameter("password"));
        user.setPayword(req.getParameter("pay_word"));
        try {
            int is_added = UserImpl.add(user);
            if(is_added > 0)
            {
                System.out.println("插入成功");
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            }else if(is_added == 0)
            {
                System.out.println("插入失败");
                resp.getWriter().print("<script>" +
                        "if(confirm('账号已经存在！请重新注册'))" +
                        "{location.href='register.jsp'}" +
                        "</script>");
            }else
            {
                System.out.println("插入失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("调用 get");
        doPost(req, resp);
    }
}
