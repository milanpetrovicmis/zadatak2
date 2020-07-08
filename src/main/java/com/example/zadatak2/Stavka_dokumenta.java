package com.example.zadatak2;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
public class Stavka_dokumenta {
	
    @Id
    private long ID;
    private float kolicina;
    private float cena;
    
    @JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "dokument_id", referencedColumnName="ID")
	public Dokument dokument;	
	
	@OneToOne(targetEntity = Roba.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "roba_id", referencedColumnName="ID")
    public Roba roba;

}