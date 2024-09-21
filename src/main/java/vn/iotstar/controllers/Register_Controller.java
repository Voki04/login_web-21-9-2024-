package vn.iotstar.controllers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iotstar.services.IUserSevice;
import vn.iotstar.services.impl.UserServiceImpl;

@WebServlet("/register")
public class Register_Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IUserSevice service = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        // Get parameters from the form
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");

        String alertMsg = "";

        // Check if username already exists
        if (service.checkExistUsername(username)) {
            alertMsg = "Tài khoản đã tồn tại!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
            return;
        }

        // Optionally check if email already exists (commented out code)
        // if (service.checkExistEmail(email)) {
        //     alertMsg = "Email đã tồn tại!";
        //     req.setAttribute("alert", alertMsg);
        //     req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
        //     return;
        // }

        // Register the user
        boolean isSuccess = service.register(username, password,email);
        if (isSuccess) {
            resp.sendRedirect(req.getContextPath() + "/login");
        } else {
            alertMsg = "System error!";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
        }
    }
}
