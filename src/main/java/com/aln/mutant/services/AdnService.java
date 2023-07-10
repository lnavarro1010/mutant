package com.aln.mutant.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.aln.mutant.models.Adn;
import com.aln.mutant.repositories.AdnRepository;

@Service
public class AdnService {
@Autowired
AdnRepository adnRepository;
	public ArrayList<Adn>obtenerAdns(){
		return (ArrayList<Adn>)adnRepository.findAll();
	}
	public Adn guardarAdn(Adn adn) {
		return adnRepository.save(adn);
	}
	public ArrayList<Adn> obtenerPorAdn(String[]adn){
		return adnRepository.findByAdn(adn);
	}
	public boolean elminarAdn(Long id) {
		try {
		 adnRepository.deleteById(id);
		 return true;
		} catch(Exception E){
			return false;
		}
	}
	public long countByIsHuman(boolean h){
		return adnRepository.countByIsHuman(h);
	}
}
