package br.ufc.server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.ufc.server.model.Car;

public interface CarRepository extends JpaRepository<Car, Integer>{
	@Query(value = "SELECT * FROM cars LIMIT ?1", nativeQuery = true)
	List<Car> findCarsList(Integer qtd);
	
	@Query(value = "SELECT * FROM cars where cars.marca = ?1", nativeQuery = true)
	List<Car> findByMarca(String marca);
}
