package com.demo.example;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableInt extends CrudRepository<Table1,Integer> {

}
