package com.aln.mutant.controllers;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.aln.mutant.models.Adn;
import com.aln.mutant.services.AdnService;
@RestController
@RequestMapping("/mutant")
public class AdnController {
	@Autowired
	AdnService adnService;
	@GetMapping("obtenerAdns")
	public ArrayList<Adn>obtenerAdns(){
		return adnService.obtenerAdns();
	}
	@PostMapping() 	
	public ResponseEntity<Adn> guardarAdn(@RequestBody Adn adn) {
	
		
		if (adnService.obtenerPorAdn(adn.getAdn()).isEmpty()) {
		    adn.setHuman(!isMutant(adn.getAdn()));
		    if( adn.isHuman()){
		    	return new ResponseEntity <Adn>(adnService.guardarAdn(adn),HttpStatus.FORBIDDEN);
		    }else {
		    	return new ResponseEntity <Adn>(adnService.guardarAdn(adn),HttpStatus.OK);
		    }
		}else {
			return new ResponseEntity <Adn>(new Adn(),HttpStatus.ALREADY_REPORTED);
			
		}
	}
	@RequestMapping("/stats")
	@GetMapping
	public ArrayList<String> obtenerEstadisticas(){
		long cantidadHumanos=0;
		long cantidadMutantes=0;
		long total=0;
		double ratio=0.0;
		cantidadHumanos= adnService.countByIsHuman(false);
		cantidadMutantes= adnService.countByIsHuman(true);
		total =cantidadHumanos+cantidadMutantes;
		if (total!=0 ) {
			ratio = (0.1)*cantidadMutantes /total;
			
		}
	ArrayList<String>	resp =new ArrayList<String>();
	resp.add("Cantidad Humanos:"+cantidadHumanos);
	resp.add("Cantidad Mutantes:"+cantidadMutantes);
	resp.add("Ratio:"+ratio);
	
		return resp;
	}
	@DeleteMapping(path="/{id}")
	public String eliminarPorId(@PathVariable("id")Long id) {
		boolean resp  = adnService.elminarAdn(id);
			if (resp) {
				return "se elimino  el registro con id"+id;
			}else {
				return "No se pudo eliminar el registro con id"+id;
			}
		
		}
	 public static String generateRamdomSequence(int numberOfItems) {
		 
	       String[] constants = {"A", "T", "C", "G"};
	       int arrayLength = numberOfItems;
	       String[] randomArray = new String[arrayLength];
	       Random random = new Random();

	       for (int i = 0; i < arrayLength; i++) {
	           int randomIndex = random.nextInt(constants.length);
	           randomArray[i] = constants[randomIndex];
	       }

	    	   
		    return Arrays.toString(randomArray);
	   }
	       
	        public static boolean isMutant(String[] adn) {
	        	int cant=0;
	        	
	        	for ( String str: adn)  
	        	{  
	        	
			           if (isRepeated(str)) {cant++;
			           break;}
			           else {
			        	   			for(String vertically :transpose(adn)) {
				        	   			 if (isRepeated(vertically)) {
				        	   				 cant++;
				        	   				 break;}
				        	   			 else {
				        	   				for(String diag :getDiagonally(adn)) {
						        	   			 if (isRepeated(diag)) {
						        	   				 cant++;
						        	   				 break;}
						        	   			 else {
						        	   				for(String reverseDiag :getDiagonallyReverse(adn)) {
								        	   			 if (isRepeated(reverseDiag)) {
								        	   				 cant++;
								        	   				 break;}
								        	   			 					        	   				
						        	   				}
						        	   			 }
				        	   				 
				        	   				 
				        	   				 
				        	   				 
				        	   			}
			        	   			} 
			        	    }  
			           }
	        	}
	        	
	        	if(cant>0) {
	        		return true;
	        	}else{
	        		return false;
	        	}
	        	
	        }
	        

	       public static boolean isRepeated(String str){
	        
	        if (str.length() < 4) {
	            return false;  // String is too short to have 4 consecutively repeated characters
	        }

	        for (int i = 0; i < str.length() - 3; i++) {
	            char current = str.charAt(i);
	            if (str.charAt(i + 1) == current && 
	                str.charAt(i + 2) == current &&
	                str.charAt(i + 3) == current) {
	                return true;  // Found 4 consecutively repeated characters
	            }
	        }
	        return false;  // No 4 consecutively repeated characters found
	        }
	   
	       static String [] transpose(String[] s) {
	    	   String [] result= new String[s.length];
	    	   for (int j = 0; j < s.length ; j++) {  
	    		   StringBuilder sb = new StringBuilder();
	    		   for (int i = 0; i < s.length ; i++) {
	    			   sb.append(s[i].charAt(j));
	    		   }
	    	    	result[j]=sb.toString();
	    	   }
	    	    return result;
	    	}
	       static String [] getDiagonally(String[] s) {
	    	
	    	   List<String> testList = new ArrayList<String>();
	       int size = s.length;
	       int wordLength = size - 1;
	    
	       // Traverse diagonals from top-left to bottom-right
	       for (int i = 0; i <= wordLength; i++) {
	           String word = "";
	           for (int j = 0; j <= i; j++) {
	               
	               word += s[j].charAt(i-j);
	               
	           }
	           if ((word.length())>3 && !(word.isBlank()) && (!word.equals(null) )){
	        	   testList.add(word);
	        
	           }
	       }
	       for (int i = 1; i <= wordLength; i++) {
	           String word = "";
	           for (int j = 0; j <=wordLength- i; j++) {
	               
	               word += s[i+j].charAt(size-1-j);
	               
	           }
	         
	           if ((word.length())>3 && !(word.isBlank()) && (!word.equals(null) )){
	        	   testList.add(word);
	       
	           }
	       }
	       
	       String [] result = new String[ testList.size() ];
	       testList.toArray(result);
	       return result;
	      
	       }
	       static String [] getDiagonallyReverse(String[] s) {
	    	 
	       int size = s.length;
	     
	       List<String> testList = new ArrayList<String>();
	                  // Show the words along the main diagonal
	       String word = "";
	             for (int i = 0; i <size; i++) {
	              
	               word+=s[i].charAt(i);
	           }
	          
	           if ((word.length())>3 && !(word.isBlank()) && (!word.equals(null) )){
	        	   testList.add(word);
	           }
	           // Show the words along the diagonals above the main diagonal
	           for (int i = 1; i < size; i++) {
	              word="";
	               for (int j = 0; j < size - i; j++) {
	                   word+=s[j].charAt(i+j);
	                   
	               }
	              
	               if ((word.length())>3 && !(word.isBlank()) && (!word.equals(null) )){
	            	   testList.add(word);
	               }
	           }
	          
	           // Show the words along the diagonals below the main diagonal
	           for (int i = 1; i < size; i++) {
	               word="";
	               for (int j = 0; j < size - i; j++) {
	            	   word+=s[i+j].charAt(j);
	               }
	              
	               if ((word.length())>3 && !(word.isBlank()) && (!word.equals(null) )){
	            	   testList.add(word);
	               }
	           }
	    
	         
	       
	       String [] result = new String[ testList.size() ];
	       testList.toArray(result);
	        return result;
	      
	       }
}
