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
public class RobaController {
	
	  @Autowired
	  private RobaRepository robaRepository;
	  

	  /* Get all*/
	  @GetMapping("/roba")
	  public List<Roba> getAllRoba() {
	    return robaRepository.findAll();
	  }
	  
	  /* Get by ID */
	  @GetMapping("/roba/{id}")
	  public ResponseEntity<Roba> getRobaById(@PathVariable(value = "id") Long robaId) {
		Roba roba = robaRepository.findById(robaId).orElseGet(null);	  
	    return ResponseEntity.ok().body(roba);
	  }  

	  /* Post Dokument  */	  
	  @PostMapping("/roba")
	  public Roba createDokument(@RequestBody Roba roba) {
		  return robaRepository.save(roba);
	  }
	  
	   /* Put Dokument  */
	   @PutMapping("/roba/{id}")
	   public ResponseEntity<Roba> updateRoba(
	       @PathVariable(value = "id") Long robaId, 
	       @Valid @RequestBody Roba robaDetails) {

	     Roba roba = robaRepository.findById(robaId).orElseGet(null);
	     roba.setNaziv(robaDetails.getNaziv());
	     final Roba updatedRoba = robaRepository.save(roba);
	     return ResponseEntity.ok(updatedRoba);
	   } 
	   
	   /* Delete Dokument   */
	   @DeleteMapping("/roba/{id}")
	   public Map<String, Boolean> deleteDokument(@PathVariable(value = "id") Long robaId) {

	     Roba roba = robaRepository.findById(robaId).orElseGet(null);
	     robaRepository.delete(roba);
	     Map<String, Boolean> response = new HashMap<>();
	     response.put("deleted", Boolean.TRUE);
	     return response;
	   }
	   
}
