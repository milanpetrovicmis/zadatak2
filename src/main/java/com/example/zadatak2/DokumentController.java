package com.example.zadatak2;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
public class DokumentController {
	
	  @Autowired
	  private DokumentRepository dokumentRepository;
	  
	  @Autowired
	  private RobaRepository robaRepository;  
	  
	  /* Get all*/
	  @GetMapping("/dokument")
	  public List<Dokument> getAllDokument() {
	    return dokumentRepository.findAll();
	  }
	  
	  /* Get by ID */
	  @GetMapping("/dokument/{id}")
	  public ResponseEntity<Dokument> getDokumentById(@PathVariable(value = "id") Long dokumentId) {
		Dokument dokument = dokumentRepository.findById(dokumentId).orElseGet(null);	  
	    return ResponseEntity.ok().body(dokument);
	  }  

	  /* Post Dokument  */	  
	  @PostMapping("/dokument")
	  public Dokument createDokument(@RequestBody Dokument dokument) {
		  
		  /*Prvo proverim da li postoji roba i ako je nema ubaci prvo nju*/
		  Set<Stavka_dokumenta> sdok = dokument.getStavka_dokumenta();
		  Iterator iterator = sdok.iterator();
		  while(iterator.hasNext()){
			    Stavka_dokumenta i = (Stavka_dokumenta) iterator.next();
			    Roba roba = i.getRoba();
			    if (roba != null) {
			        long rid = roba.getID();			        		        
		            if (robaRepository.existsById(rid)==false) {
		            	String rnaziv = roba.getNaziv();	
		            	Roba novaRoba = new Roba();
		            	novaRoba.setID(rid);
		            	novaRoba.setNaziv(rnaziv);
		            	robaRepository.save(novaRoba);
		            };			        
			    } 
		  }		  	
		  /*na kraju ubaci i sve ostalo*/
		  return dokumentRepository.save(dokument);
	  }
	  
	   /* Put Dokument  */
	   @PutMapping("/dokument/{id}")
	   public ResponseEntity<Dokument> updateDokument(
	       @PathVariable(value = "id") Long dokumentId, 
	       @Valid @RequestBody Dokument dokumentDetails) {

	     Dokument dokument = dokumentRepository.findById(dokumentId).orElseGet(null);
	     dokument.setNaziv(dokumentDetails.getNaziv());
	     dokument.setIznos(dokumentDetails.getIznos());
	     final Dokument updatedDokument = dokumentRepository.save(dokument);
	     return ResponseEntity.ok(updatedDokument);
	   } 
	   
	   /* Delete Dokument   */
	   @DeleteMapping("/dokument/{id}")
	   public Map<String, Boolean> deleteDokument(@PathVariable(value = "id") Long dokumentId) {

	     Dokument dokument = dokumentRepository.findById(dokumentId).orElseGet(null);
	     dokumentRepository.delete(dokument);
	     Map<String, Boolean> response = new HashMap<>();
	     response.put("deleted", Boolean.TRUE);
	     return response;
	   }
	   
}
