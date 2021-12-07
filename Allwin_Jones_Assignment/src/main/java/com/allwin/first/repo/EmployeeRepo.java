package com.allwin.first.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.allwin.first.model.Employee;

@Repository
public interface EmployeeRepo extends MongoRepository<Employee, String>{
	
	@Query("{ $or:[ { empId: { $regex:?0, $options: 'i' } }, { empName: { $regex:?0, $options: 'i' } }, { empGender: { $regex:?0, $options: 'i' } }, { empEmail: { $regex:?0, $options: 'i' } }, { empPhone: { $regex:?0, $options: 'i' } }, { empPosition: { $regex:?0, $options: 'i' } } ] }")
	List<Employee> searchEmployee(String searchText);
	
	@Query("{ empId:?0 }")
	Employee findOneById(String empId);
	
	@Query(value = "{ empId:?0 }", delete = true)
	int deleteOneById(String empId);
	
}
