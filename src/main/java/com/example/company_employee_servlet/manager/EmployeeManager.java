package com.example.company_employee_servlet.manager;

import com.example.company_employee_servlet.model.Company;
import com.example.company_employee_servlet.db.DBConnectionProvider;
import com.example.company_employee_servlet.model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeManager {

    private final Connection connection = DBConnectionProvider.getInstance().getConnection();
    private CompanyManager companyManager = new CompanyManager();

//    public Employee getByID(int id) {
//        try (Statement statement = connection.createStatement()) {
//            String query = "SELECT * FROM company_employee.employee WHERE id = " + id;
//            System.out.println(query);
//            ResultSet resultSet = statement.executeQuery(query);
//            return getEmployeeFromResultSet(resultSet);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public Employee getByID(int id) {
        String query = "SELECT * FROM company_employee.employee " +
                "WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int employeeId = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String surname = resultSet.getString("surname");
                    String email = resultSet.getString("email");
                    int companyId = resultSet.getInt("company_id");
                    Company company = companyManager.getByID(companyId);
                    String picName = resultSet.getString("pic_name");

                    return new Employee(employeeId, name, surname, email, company, picName);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }




    public List<Employee> getAll() {
        List<Employee> employeeList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM company_employee.employee");
            while (resultSet.next()) {
                Employee employee = getEmployeeFromResultSet(resultSet);
                employeeList.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeList;
    }

    public void save(Employee employee) {
        try (Statement statement = connection.createStatement()) {
            String sql = "INSERT INTO company_employee.employee(name, surname, email, company_id, pic_name) " +
                    "VALUES('" + employee.getName() + "', '" + employee.getSurname() + "', '" +
                    employee.getEmail() + "', '" + employee.getCompany().getId() + "', '" + employee.getPicName() + "')";
            statement.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Employee> getAllByCompanyID(int companyId) {
        List<Employee> employeeList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM company_employee.employee WHERE company_id = " + companyId);
            while (resultSet.next()) {
                employeeList.add(getEmployeeFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeList;
    }

    public void removeById(int employeeId) {
        String sql = "DELETE FROM company_employee.employee WHERE id = " + employeeId;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Employee employee) {
        String sql = "UPDATE company_employee.employee SET name = '" + employee.getName() +
                "', surname = '" + employee.getSurname() +
                "', email = '" + employee.getEmail() +
                "', company_id = '" + employee.getCompany().getId() +
                "', pic_name = '" + employee.getPicName() +
                "' WHERE id = " + employee.getId();
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Employee getEmployeeFromResultSet(ResultSet resultSet) throws SQLException {
        Employee employee = new Employee();
        employee.setId(resultSet.getInt("id"));
        employee.setName(resultSet.getString("name"));
        employee.setSurname(resultSet.getString("surname"));
        employee.setEmail(resultSet.getString("email"));
        employee.setPicName(resultSet.getString("pic_name"));
        int companyId = resultSet.getInt("company_id");
        employee.setCompany(companyManager.getByID(companyId));
        return employee;
    }

    public List<Employee> search(String keyword) {
        List<Employee> employeeList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM company_employee.employee WHERE name LIKE ? OR surname LIKE ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            keyword = "%" + keyword + "%";
            preparedStatement.setString(1, keyword);
            preparedStatement.setString(2, keyword);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Employee employee = getEmployeeFromResultSet(resultSet);
                employeeList.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeList;
    }
}
