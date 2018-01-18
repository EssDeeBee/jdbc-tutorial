package dao;

import java.util.List;

import entity.EmplProj;

public interface EmplProjDAO {
	// create
	void add(EmplProj emplProj);

	// read
	List<EmplProj> getAll();

	EmplProj getByEmployeeIdAndProjectId(Long employeeId, Long projectId);

	// update
	void update(EmplProj emplProj);

	// delete
	void remove(EmplProj emplProj);

}
