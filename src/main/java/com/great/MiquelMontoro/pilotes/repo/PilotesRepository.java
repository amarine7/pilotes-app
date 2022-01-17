package com.great.MiquelMontoro.pilotes.repo;

import com.great.MiquelMontoro.pilotes.model.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PilotesRepository extends CrudRepository<Order, Long>, JpaSpecificationExecutor<Order> {

    List<Order> findAll();

    //Optional<Order> findById(Long id);

    Order getOne(Long id);

    List<Order> findAll(Specification<Order> spec);
}
