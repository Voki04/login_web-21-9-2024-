package vn.iotstar.controllers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iotstar.services.IUserSevice;
import vn.iotstar.services.impl.UserServiceImpl;

@WebServlet("/forgot_password")
public class ForgotPasswordController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IUserSevice service = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/forgot_password.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        // Get parameters from the form
        String username = req.getParameter("username");
        String email = req.getParameter("email");

        String alertMsg = "";
        String currentPassword = service.getCurrentPassword(username, email);

        if (currentPassword != null) {
            // Nếu tìm thấy mật khẩu, hiển thị mật khẩu trên trang
            req.setAttribute("password", currentPassword);
        } else {
            // Nếu không khớp username hoặc email
            alertMsg = "Tên đăng nhập và email không khớp!";
            req.setAttribute("alert", alertMsg);
        }

        // Chuyển tiếp đến trang forgot_password.jsp với kết quả
        req.getRequestDispatcher("/views/forgot_password.jsp").forward(req, resp);
    }
}