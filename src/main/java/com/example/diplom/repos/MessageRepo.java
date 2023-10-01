package com.example.diplom.repos;

import com.example.diplom.domain.Cargo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepo extends CrudRepository<Cargo, Integer> {

    List<Cargo> findByConsigneeContains(String consignee);

}