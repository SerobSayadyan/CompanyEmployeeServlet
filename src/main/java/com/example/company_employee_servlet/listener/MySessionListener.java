package com.example.company_employee_servlet.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Date;

@WebListener
public class MySessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        String id = se.getSession().getId();
        System.out.println("Session created at " + new Date() +
                " session ID " + id);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        String id = se.getSession().getId();
        System.out.println("Session destroyed at " + new Date() +
                " session ID " + id);
    }
}
