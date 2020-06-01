package com.demo.example;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface ImagesInt extends CrudRepository<Images, Integer>  {
	

}
