package web.servlet.client;

import domain.User;
import org.apache.log4j.Logger;
import service.UserService;
import utils.MySessionContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/login.do")
public class LoginServlet extends HttpServlet {
    static Logger logger = Logger.getLogger(LoginServlet.class);

    /**
     * The doGet method of the servlet. <br>
     * <p>
     * This method is called when a form has its tag value method equals to get.
     *
     * @param request  the request send by the client to the server
     * @param response the response send by the server to the client
     * @throws ServletException if an error occurred
     * @throws IOException      if an error occurred
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");*/

        // 得到用户名和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        // 实例化用户操作相关的逻辑层
        UserService service = new UserService();
        request.getServletContext().removeAttribute("msg");
        try {
            if(username != null && !"".equals(username)){
                // 进行登录验证，判断数据库是否存在这个用户
                User user = service.login(username, password);
                logger.warn("用户信息：" + user.toString());
                // 登录成功，将用户存储到 session中
                request.getSession().setAttribute("user", user);
                request.getSession().setAttribute("userId", String.valueOf(user.getId()));
                logger.warn("sessionId:------------>" + request.getSession().getId());
                // 将Session 添加到 map中
                MySessionContext.addSession(request.getSession());

                // 发送自动登录的 cookie
                String autoLogin = "3600";
                // 注意 cookie中的密码要加密
                Cookie cookie = new Cookie("autoLogin", username + "-" + password);
                cookie.setMaxAge(Integer.parseInt(autoLogin));
                cookie.setPath(request.getContextPath());
                response.addCookie(cookie);
                // 如果是管理员调到后台账户
                if ("admin".equals(user.getRole())) {
                    // TODO: 跳转到后台管理界面
                    response.sendRedirect("management/index.jsp");
                } else {
                    // TODO: 如果不是管理员，调到主页面
                    response.sendRedirect("main.do");
                }
            }
        } catch (
                Exception e) {
            e.printStackTrace();
            // 如果出现问题，登录失败，则将错误信息存储到request域中，并跳转回登录页面显示错误信息
            request.setAttribute("register_meesage", e.getMessage());
            request.getRequestDispatcher("loginAndRegister.jsp").forward(request, response);
            return;
        }

    }

    /**
     * The doPost method of the servlet. <br>
     * <p>
     * This method is called when a form has its tag value method equals to post.
     *
     * @param request  the request send by the client to the server
     * @param response the response send by the server to the client
     * @throws ServletException if an error occurred
     * @throws IOException      if an error occurred
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }

}
