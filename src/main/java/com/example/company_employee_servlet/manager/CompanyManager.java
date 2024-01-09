package com.example.company_employee_servlet.manager;

import com.example.company_employee_servlet.model.Company;
import com.example.company_employee_servlet.db.DBConnectionProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyManager {

    private final Connection connection = DBConnectionProvider.getInstance().getConnection();

    public Company getByID(int id) {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM company_employee.company WHERE id = " + id);
            return resultSet.next() ? getCompanyFromResultSet(resultSet) : null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Company> getByCountry(String country) {
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM company_employee.company WHERE country = ?")) {
            ps.setString(1, country);
            ResultSet resultSet = ps.executeQuery();
            List<Company> companyList = new ArrayList<>();
            while (resultSet.next()) {
                Company company = new Company();
                company.setId(resultSet.getInt("id"));
                company.setName(resultSet.getString("name"));
                company.setCountry(resultSet.getString("country"));
                companyList.add(company);
            }
            return companyList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Company> getAll() {
        List<Company> companyList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM company_employee.company");
            while (resultSet.next()) {
                Company company = getCompanyFromResultSet(resultSet);
                companyList.add(company);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return companyList;
    }

    public void save(Company company) {
        String sql = "INSERT INTO company_employee.company(name, country) VALUES (?, ?);";
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, company.getName());
            ps.setString(2, company.getCountry());
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                company.setId(generatedKeys.getInt(1));
            }
            System.out.println("The Generate ID is - " + company.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeById(int companyId) {
        String sql = "DELETE FROM company_employee.company WHERE id = " + companyId;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Company getCompanyFromResultSet(ResultSet resultSet) throws SQLException {
        Company company = new Company();
//        resultSet.next();
        company.setId(resultSet.getInt("id"));
        company.setName(resultSet.getString("name"));
        company.setCountry(resultSet.getString("country"));
        return company;
    }

    public void update(Company company) {
        String sql = "UPDATE company_employee.company SET name = '" + company.getName()
                + "', country = '" + company.getCountry() + "' WHERE id = " + company.getId();
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
