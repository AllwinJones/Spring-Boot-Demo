package com.allwin.first.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.allwin.first.model.Address;

@Repository
public interface AddressRepo extends MongoRepository<Address, Integer>{
	
	@Query("{ empId:?0 }")
	List<Address> findAllByEmpId(String empId);
	
	@Query(value = "{ empId:?0 }", delete = true)
	int deleteAllByEmpId(String empId);
	
	@Query("{ $or:[ { street:{ $regex:?0, $options: 'i' } }, { city:{ $regex:?0, $options: 'i' } }, { district:{ $regex:?0, $options: 'i' } }, { state:{ $regex:?0, $options: 'i' } }, { pincode:{ $regex:?0, $options: 'i' } } ] }")
	List<Address> searchAddress(String searchText);
	
}
