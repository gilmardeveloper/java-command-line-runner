package com.gilmardeveloper.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.gilmardeveloper.demo.model.Expressoes;

public interface ExpressoesRepository extends CrudRepository<Expressoes, Long> {
	
	@Query("select p from Expressoes p")
	List<Expressoes> listAll();
	
	@Transactional
	@Modifying
	@Query("delete from Expressoes p where p.nome = :pnome")
	void deleteByNome(@Param("pnome") String nome);
	
	@Query("select p from Expressoes p where p.nome like %:pvalor%")
	List<Expressoes> findByNome(@Param("pvalor") String valor);

}
