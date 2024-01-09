package com.example.company_employee_servlet.servlet;

import com.example.company_employee_servlet.manager.CompanyManager;
import com.example.company_employee_servlet.manager.EmployeeManager;
import com.example.company_employee_servlet.model.Company;
import com.example.company_employee_servlet.model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/updateEmployee")
public class UpdateEmployeeServlet extends HttpServlet {

    private EmployeeManager employeeManager = new EmployeeManager();
    private CompanyManager companyManager = new CompanyManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Employee employee = employeeManager.getByID(id);
        List<Company> companies = companyManager.getAll();
        req.setAttribute("employee", employee);
        req.setAttribute("companies", companies);
        req.getRequestDispatcher("WEB-INF/updateEmployee.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Employee employee = employeeManager.getByID(id);
        employee.setName(req.getParameter("name"));
        employee.setSurname(req.getParameter("surname"));
        employee.setEmail(req.getParameter("email"));
        int companyID = Integer.parseInt(req.getParameter("companyID"));
        Company company = companyManager.getByID(companyID);
        employee.setCompany(company);
        employeeManager.update(employee);
        resp.sendRedirect("/employees");

    }
}
