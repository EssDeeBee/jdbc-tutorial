package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bl.Util;
import dao.AddressDAO;
import entity.Address;

public class AddressService extends Util implements AddressDAO {

	Connection connection = getConnection();

	@Override
	public void add(Address address) {
		PreparedStatement preparedStatement = null;

		String sql = "INSERT INTO ADDRESS (ID, COUNTRY, CITY, STREET, POST_CODE) VALUES(?, ?, ?, ?, ?)";

		try {
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setLong(1, address.getId());
			preparedStatement.setString(2, address.getCountry());
			preparedStatement.setString(3, address.getCity());
			preparedStatement.setString(4, address.getStreet());
			preparedStatement.setString(5, address.getPostCode());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Address> getAll() {
		List<Address> addressList = new ArrayList<>();

		String sql = "SELECT ID, COUNTRY, CITY, STREET, POST_CODE FROM ADDRESS";

		Statement statement = null;

		try {
			statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				Address address = new Address();
				address.setId(resultSet.getLong("ID"));
				address.setCountry(resultSet.getString("COUNTRY"));
				address.setCity(resultSet.getString("CITY"));
				address.setStreet(resultSet.getString("STREET"));
				address.setPostCode(resultSet.getString("POST_CODE"));

				addressList.add(address);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return addressList;
	}

	@Override
	public Address getById(Long id) {

		PreparedStatement preparedStatement = null;

		String sql = "SELECT ID, COUNTRY, CITY, STREET, POST_CODE FROM ADDRESS WHERE ID=?";

		Address address = new Address();
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);

			ResultSet resultSet = preparedStatement.executeQuery();

			address.setId(resultSet.getLong("ID"));
			address.setCountry(resultSet.getString("COUNTRY"));
			address.setCity(resultSet.getString("CITY"));
			address.setStreet(resultSet.getString("STREET"));
			address.setPostCode(resultSet.getString("POST_CODE"));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return address;
	}

	@Override
	public void update(Address address) {

		PreparedStatement preparedStatement = null;

		String sql = "UPDATE ADDRESS SET COUNTRY=?, CITY=?, STREET=?, POST_CODE=? WHERE ID=?";

		try {
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, address.getCountry());
			preparedStatement.setString(2, address.getCity());
			preparedStatement.setString(3, address.getStreet());
			preparedStatement.setString(4, address.getPostCode());
			preparedStatement.setLong(5, address.getId());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void remove(Address address) {

		PreparedStatement preparedStatement = null;

		String sql = "DELETE FROM ADDRESS WHERE ID=?";

		try {

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setLong(1, address.getId());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
