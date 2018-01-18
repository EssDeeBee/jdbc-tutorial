package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bl.Util;
import dao.EmployeeDAO;
import entity.Employee;

public class EmployeeService extends Util implements EmployeeDAO {

	private Connection connection = getConnection();

	@Override
	public void add(Employee employee) {
		PreparedStatement preparedStatement = null;

		String sql = "INSERT INTO EMPLOYEE (ID, FIRST_NAME, LAST_NAME, BIRTHDAY, ADDRESS_ID) VALUES(?, ?, ?, ?, ?)";

		try {
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setLong(1, employee.getId());
			preparedStatement.setString(2, employee.getFirstName());
			preparedStatement.setString(3, employee.getLastName());
			preparedStatement.setDate(4, employee.getBirthday());
			preparedStatement.setLong(5, employee.getAddressId());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Employee> getAll() {

		List<Employee> employeeList = new ArrayList<>();

		String sql = "SELECT ID, FIRST_NAME, LAST_NAME, BIRTHDAY, ADDRESS_ID FROM EMPLOYEE";

		Statement statement = null;

		try {
			statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				Employee employee = new Employee();
				employee.setId(resultSet.getLong("ID"));
				employee.setFirst_name(resultSet.getString("FIRST_NAME"));
				employee.setLastName(resultSet.getString("LAST_NAME"));
				employee.setBirthday(resultSet.getDate("BIRTHDAY"));
				employee.setAddressId(resultSet.getLong("ADDRESS_ID"));

				employeeList.add(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employeeList;

	}

	@Override
	public Employee getById(Long id) {

		PreparedStatement preparedStatement = null;

		String sql = "SELECT ID, FIRST_NAME, LAST_NAME, BIRTHDAY, ADDRESS_ID FROM EMPLOYEE WHERE ID=?";

		Employee employee = new Employee();
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);

			ResultSet resultSet = preparedStatement.executeQuery();

			employee.setId(resultSet.getLong("ID"));
			employee.setFirst_name(resultSet.getString("FIRST_NAME"));
			employee.setLastName(resultSet.getString("LAST_NAME"));
			employee.setBirthday(resultSet.getDate("BIRTHDAY"));
			employee.setAddressId(resultSet.getLong("ADDRESS_ID"));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employee;
	}

	@Override
	public void remove(Employee employee) {
		PreparedStatement preparedStatement = null;

		String sql = "DELETE FROM EMPLOYEE WHERE ID=?";

		try {

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setLong(1, employee.getId());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
