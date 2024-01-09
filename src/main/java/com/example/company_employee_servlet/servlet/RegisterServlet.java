package com.example.company_employee_servlet.servlet;

import com.example.company_employee_servlet.manager.UserManager;
import com.example.company_employee_servlet.model.User;
import com.example.company_employee_servlet.model.UserType;
import com.example.company_employee_servlet.util.EmailUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private UserManager userManager = new UserManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //filters are called
        String email = req.getParameter("email");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String password = req.getParameter("password");
        UserType type = UserType.valueOf(req.getParameter("type"));

        String msg = "";
        if ((msg = fieldsValidation(name, surname, email, password)).isEmpty()) {
            if (userManager.getByEmail(email) == null) {
                userManager.save(User.builder()
                        .name(name)
                        .surname(surname)
                        .email(email)
                        .password(password)
                        .type(type)
                        .build());
            }
            resp.sendRedirect("/");
        } else {
            req.setAttribute("msg", msg);
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
        }
    }

    private static String fieldsValidation(String name, String surname, String email, String password) {
        String msg = "";

        if (name == null || name.trim().startsWith(" ") || name.trim().isEmpty()) {
            msg += "Name is required<br>";
        }
        if (surname == null || surname.trim().startsWith(" ") || surname.trim().isEmpty()) {
            msg += "Surname is required<br>";
        }
        if (!EmailUtil.patternMatches(email)) {
            msg += "Email format is incorrect<br>";
        }
        if (password.length() < 6) {
            msg += "Password length must be 6 or more symbols<br>";
        }
        return msg;
    }

}
