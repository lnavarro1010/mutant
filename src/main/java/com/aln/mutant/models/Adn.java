package com.aln.mutant.models;


import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name="adn")
public class Adn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private String[] adn;
    private boolean isHuman;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String[] getAdn() {
        return adn;
    }
    public void setAdn(String[] adn) {
        this.adn = adn;
    }
    public boolean isHuman() {
        return isHuman;
    }
    public void setHuman(boolean isHuman) {
        this.isHuman = isHuman;
    }
    public Adn(Long id, String[] adn, boolean isHuman) {
		super();
		this.id = id;
		this.adn = adn;
		this.isHuman = isHuman;
	}
    public Adn() {
		super();
		
	}
}
