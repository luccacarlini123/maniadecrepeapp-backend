package com.mouzetech.maniadecrepeapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mouzetech.maniadecrepeapp.domain.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

	@Transactional(readOnly=true)
	@Query("SELECT obj FROM Cidade obj where obj.estado.id = :estadoId ORDER BY obj.nome")
	public List<Cidade> findCidadeByEstadoId(@Param("estadoId") Integer estado_id);
	
}