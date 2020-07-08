package com.example.zadatak2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.zadatak2.Dokument;

@Repository
public interface DokumentRepository extends JpaRepository<Dokument, Long>{

}

