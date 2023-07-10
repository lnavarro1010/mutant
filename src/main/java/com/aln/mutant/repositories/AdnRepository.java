package com.aln.mutant.repositories;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aln.mutant.models.Adn;

@Repository
public interface AdnRepository extends CrudRepository<Adn,Long> {
	public abstract ArrayList<Adn> findByAdn(String[] adn);
	public long countByIsHuman(boolean h);

}
