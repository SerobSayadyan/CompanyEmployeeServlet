package com.example.company_employee_servlet.filter;

import com.example.company_employee_servlet.model.User;
import com.example.company_employee_servlet.model.UserType;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/removeCompany", "/removeEmployee", "/updateCompany", "/updateEmployee"})
public class AdminFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null || user.getType() != UserType.ADMIN) {
            response.sendRedirect("/");
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
