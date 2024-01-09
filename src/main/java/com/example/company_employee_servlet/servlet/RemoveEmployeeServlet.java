package com.example.company_employee_servlet.servlet;

import com.example.company_employee_servlet.constants.SharedConstants;
import com.example.company_employee_servlet.manager.EmployeeManager;
import com.example.company_employee_servlet.model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet("/removeEmployee")
public class RemoveEmployeeServlet extends HttpServlet {

    private EmployeeManager employeeManager = new EmployeeManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Employee byId = employeeManager.getByID(id);
        if (byId != null) {
            String picName = byId.getPicName();
            if (picName != null) {
                File file = new File(SharedConstants.UPLOAD_FOLDER + picName);
                if (file.delete()) {
                    System.out.println(SharedConstants.UPLOAD_FOLDER + picName + ": is deleted");
                }
            }
            employeeManager.removeById(id);
        }
        resp.sendRedirect("/employees");
    }
}
