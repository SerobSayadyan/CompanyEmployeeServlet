package com.example.company_employee_servlet.servlet;

import com.example.company_employee_servlet.manager.EmployeeManager;
import com.example.company_employee_servlet.model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/employees")
public class EmployeeServlet extends HttpServlet {
    private EmployeeManager employeeManager = new EmployeeManager();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keyword = req.getParameter("keyword");
        List<Employee> employees = null;
        if (keyword == null || keyword.isEmpty()) {
            employees = employeeManager.getAll();
        } else {
            employees = employeeManager.search(keyword);
        }
        req.setAttribute("employees", employees);
        req.getRequestDispatcher("WEB-INF/employees.jsp").forward(req, resp);
    }
}
