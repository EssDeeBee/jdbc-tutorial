package dao;

import java.util.List;

import entity.Project;

public interface ProjectDAO {

	// create
	void add(Project project);

	// read
	List<Project> getAll();

	Project getById(Long Id);

	// update
	void update(Project project);

	// delete
	void remove(Project project);
}
