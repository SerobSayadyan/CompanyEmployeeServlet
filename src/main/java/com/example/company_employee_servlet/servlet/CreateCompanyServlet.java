package com.example.company_employee_servlet.servlet;

import com.example.company_employee_servlet.manager.CompanyManager;
import com.example.company_employee_servlet.model.Company;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/createCompany")
public class CreateCompanyServlet extends HttpServlet {

    private CompanyManager companyManager = new CompanyManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/createCompany.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Company company = new Company();
        company.setName(req.getParameter("name"));
        company.setCountry(req.getParameter("country"));
        companyManager.save(company);
        resp.sendRedirect("/companies");

    }
}
