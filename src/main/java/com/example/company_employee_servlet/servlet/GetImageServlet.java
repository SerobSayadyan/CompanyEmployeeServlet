package com.example.company_employee_servlet.servlet;

import com.example.company_employee_servlet.constants.SharedConstants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet("/getImage")
public class GetImageServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String picName = req.getParameter("picName");
        File imageFile = new File(SharedConstants.UPLOAD_FOLDER + picName);
        if (imageFile.exists()) {
            try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(imageFile));
                 BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(resp.getOutputStream())) {

                resp.setContentType("image/jpeg");
                resp.setContentLength((int) imageFile.length());

                bufferedOutputStream.write(inputStream.readAllBytes());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
