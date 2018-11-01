package com.gilmardeveloper.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.gilmardeveloper.demo.model.PalavrasEn;

public interface PalavrasEnRepository extends CrudRepository<PalavrasEn, Long> {
	
	@Query("select p from PalavrasEn p")
	List<PalavrasEn> listAll();
	
	@Transactional
	@Modifying
	@Query("delete from PalavrasEn p where p.nome = :pnome")
	void deleteByNome(@Param("pnome") String nome);
	
	@Query("select p from PalavrasEn p where p.nome like %:pvalor%")
	List<PalavrasEn> findByNome(@Param("pvalor") String valor);

}
