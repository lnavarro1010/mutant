package com.aln.mutant;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.aln.mutant.models.Adn;
import com.aln.mutant.repositories.AdnRepository;
import com.aln.mutant.services.AdnService;
@RunWith(SpringRunner.class)
@SpringBootTest
public class MutantApplicationTests {

	@Autowired
	private AdnService adnService;
	@MockBean
	private AdnRepository adnRepository;
	@Test
	void obtenerAdnsTest() {
		 String[] aux = {"TAGCCG", "GGTCCT", "TAGCGC", "GAAGTG", "CGCACC", "CGCCTC"};
		 String[] aux2 = {"TAGCCG", "GGTCCT", "TAGCGC", "GAAGTG", "CGCACC", "CGCCTC"};
		 Adn adn1= new Adn((long) 100, aux, false);
		Adn adn2= new Adn((long) 200, aux2, false);
		
		when(adnRepository.findAll()).thenReturn(Stream.of(adn1,adn2).collect(Collectors.toList()));
		assertEquals(2,adnService.obtenerAdns().size());
		
	}
	
	  @Test
	  public void obtenerPorAdnTest() {
		  String [] aux = {"TAGCCG", "GGTCCT", "TAGCGC", "GAAGTG", "CGCACC", "CGCCTC"};
	  when(adnRepository.findByAdn(aux)).thenReturn((ArrayList<Adn>) Stream.of(new Adn((long)12,aux, false)).collect(Collectors.toList()));
	   assertEquals(1, adnService.obtenerPorAdn(aux).size()); }
	  
	 @Test 
	 public void saveUserTest() {
		 String [] aux = {"TAGCCG", "GGTCCT", "TAGCGC", "GAAGTG", "CGCACC", "CGCCTC"};
		 Adn adn = new Adn((long)12, aux, true);
		 when(adnRepository.save(adn)).thenReturn(adn);
		 assertEquals(adn, adnService.guardarAdn(adn)); }
	  
	  @Test public void deleteUserTest() {
		  String [] aux = {"TAGCCG", "GGTCCT", "TAGCGC", "GAAGTG", "CGCACC", "CGCCTC"};
			 Adn adn = new Adn((long)12, aux, true);
		  adnService.elminarAdn(adn.getId()) ;
		  verify(adnRepository,  times(1)).delete(adn);
		  }
	 
}
