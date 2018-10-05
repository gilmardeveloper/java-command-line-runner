package com.gilmardeveloper.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.gilmardeveloper.demo.model.Palavras;

public interface PalavrasRepository extends CrudRepository<Palavras, Long> {
	
	@Query("select p from Palavras p")
	List<Palavras> listAll();
	
	@Transactional
	@Modifying
	@Query("delete from Palavras p where p.nome = :pnome")
	void deleteByNome(@Param("pnome") String nome);
	
	@Query("select p from Palavras p where p.nome like %:pvalor%")
	List<Palavras> findByNome(@Param("pvalor") String valor);

}
