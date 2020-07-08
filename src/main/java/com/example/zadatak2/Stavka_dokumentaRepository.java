package com.example.zadatak2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Stavka_dokumentaRepository extends JpaRepository<Stavka_dokumenta, Long> {

}
