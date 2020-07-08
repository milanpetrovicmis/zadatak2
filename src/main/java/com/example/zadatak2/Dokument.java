package com.example.zadatak2;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Dokument {
	
    @Id
    private long ID;  
    private float iznos;
    private String naziv;
    
    @JsonManagedReference
    @OneToMany(mappedBy = "dokument",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Set<Stavka_dokumenta> stavka_dokumenta;
    
    
}
