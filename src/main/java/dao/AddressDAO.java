package dao;

import java.util.List;

import entity.Address;

public interface AddressDAO {

	// create
	void add(Address address);

	// read
	List<Address> getAll();

	Address getById(Long id);

	// update
	void update(Address address);

	// delete
	void remove(Address address);

}
