package com.example.company_employee_servlet.servlet;

import com.example.company_employee_servlet.constants.SharedConstants;
import com.example.company_employee_servlet.manager.CompanyManager;
import com.example.company_employee_servlet.manager.EmployeeManager;
import com.example.company_employee_servlet.model.Company;
import com.example.company_employee_servlet.model.Employee;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;


@WebServlet("/createEmployee")
@MultipartConfig(
        maxFileSize = 1024 * 1024 * 5, //5mb
        maxRequestSize = 1024 * 1024 * 10, //10mb
        fileSizeThreshold = 1024 * 1024 //1mb
)
public class CreateEmployeeServlet extends HttpServlet {
    private EmployeeManager employeeManager = new EmployeeManager();
    private CompanyManager companyManager = new CompanyManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("companies", companyManager.getAll());
        req.getRequestDispatcher("WEB-INF/createEmployee.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Employee employee = new Employee();
        employee.setName(req.getParameter("name"));
        employee.setSurname(req.getParameter("surname"));
        employee.setEmail(req.getParameter("email"));
        int companyId = Integer.parseInt(req.getParameter("companyID"));
        Part profilePic = req.getPart("profilePic");
        String picName = null;
        if  (profilePic != null && profilePic.getSize() > 0) {
            picName = System.nanoTime() + "_" + profilePic.getSubmittedFileName();
            profilePic.write(SharedConstants.UPLOAD_FOLDER + picName);
            System.out.println("-----------------Photo-Added----------------");
        }
        employee.setPicName(picName);
        Company company = companyManager.getByID(companyId);
        employee.setCompany(company);
        employeeManager.save(employee);
        resp.sendRedirect("/employees");
    }
}
