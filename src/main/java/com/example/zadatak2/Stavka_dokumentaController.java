package com.example.zadatak2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class Stavka_dokumentaController {
	
 	  @Autowired
	  private Stavka_dokumentaRepository stavka_dokumentaRepository;
	  
	  /* Get all*/
	  @GetMapping("/stavka_dokumenta")
	  public List<Stavka_dokumenta> getAllStavka_Dokumenta() {
	    return stavka_dokumentaRepository.findAll();
	  }
	  
	  /* Get by ID */
	  @GetMapping("/stavka_dokumenta/{id}")
	  public ResponseEntity<Stavka_dokumenta> getStavka_DokumentaById(@PathVariable(value = "id") Long stavka_dokumentaId) {
		  Stavka_dokumenta stavka_dokumenta = stavka_dokumentaRepository.findById(stavka_dokumentaId).orElseGet(null);	  
	      return ResponseEntity.ok().body(stavka_dokumenta);
	  }  

	  /* Post Dokument  */	  
	  @PostMapping("/stavka_dokumenta")
	  public Stavka_dokumenta createStavka_dokumenta(@RequestBody Stavka_dokumenta stavka_dokumenta) {
     	  return  stavka_dokumentaRepository.save(stavka_dokumenta);
	  }
	  
	   /* Put Dokument  */
	   @PutMapping("/stavka_dokumenta/{id}")
	   public ResponseEntity<Stavka_dokumenta> updateStavka_dokumenta(
	       @PathVariable(value = "id") Long stavka_dokumentaId, 
	       @Valid @RequestBody Stavka_dokumenta stavka_dokumentaDetails) {

		 Stavka_dokumenta stavka_dokumenta = stavka_dokumentaRepository.findById(stavka_dokumentaId).orElseGet(null);
		 stavka_dokumenta.setCena(stavka_dokumentaDetails.getCena());
		 stavka_dokumenta.setKolicina(stavka_dokumentaDetails.getKolicina());
	     final Stavka_dokumenta updatedStavka_dokumenta = stavka_dokumentaRepository.save(stavka_dokumenta);
	     return ResponseEntity.ok(updatedStavka_dokumenta);
	   } 
	   
	   /* Delete Dokument   */
	   @DeleteMapping("/stavka_dokumenta/{id}")
	   public Map<String, Boolean> deleteDokument(@PathVariable(value = "id") Long stavka_dokumentaId) {

		 Stavka_dokumenta stavka_dokumenta = stavka_dokumentaRepository.findById(stavka_dokumentaId).orElseGet(null);
		 stavka_dokumentaRepository.delete(stavka_dokumenta);
	     Map<String, Boolean> response = new HashMap<>();
	     response.put("deleted", Boolean.TRUE);
	     return response;
	   }
	   
}
