package com.demo.example;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropInt extends CrudRepository<Prop,Integer> {

	public List<Prop> findAllByDocuments_Id(int id);
	
}
