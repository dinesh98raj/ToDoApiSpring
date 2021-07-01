package com.example.springsecurityjwt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springsecurityjwt.models.ListItem;

@Repository
public interface ListRepository extends JpaRepository<ListItem, Integer>{
	ListItem findByTaskNoAndUserid(Integer taskid, Integer id);
	List<ListItem> findAllByUserid(Integer id);
}
