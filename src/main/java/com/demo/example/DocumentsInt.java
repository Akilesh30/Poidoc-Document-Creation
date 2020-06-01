package com.demo.example;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentsInt extends CrudRepository<Documents, Integer> {
public Documents findBydocName(String name);
//public List<Prop> findAllByDocCode_Id(int docCodeId);
}
