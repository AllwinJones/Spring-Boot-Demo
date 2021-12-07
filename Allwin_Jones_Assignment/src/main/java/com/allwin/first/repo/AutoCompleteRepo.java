package com.allwin.first.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.allwin.first.model.AutoComplete;

@Repository
public interface AutoCompleteRepo extends MongoRepository<AutoComplete, String>{

}
