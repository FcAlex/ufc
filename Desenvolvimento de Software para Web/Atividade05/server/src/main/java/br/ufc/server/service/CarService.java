package br.ufc.server.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufc.server.model.Car;
import br.ufc.server.repository.CarRepository;

@Service
public class CarService {
	
	@Autowired
	CarRepository repo;
	
	public Car addCar(Car car) {
		return repo.save(car);
	}
	
	public Car getCar(Integer id) {
		Optional<Car> car = repo.findById(id);
		if(car.isEmpty()) return null;
		return car.get();
	}
	
	public List<Car> getCars() {
		return repo.findAll();
	}
	
	public boolean removeCar(Integer id) {
		if(repo.existsById(id)) {
			repo.deleteById(id);
			return true;
		}
		return false;
	}
	
	public Car updateCar(Integer id, Car car) {
		Optional<Car> carAux = repo.findById(id);
		if(carAux.isPresent()) {
			Car newCar = carAux.get();
			newCar.copy(car);
			repo.save(newCar);
		}
		
		return null;
	}

	public List<Car> getQtdCar(Integer qtd) {
		return repo.findCarsList(qtd);
	}

	public List<Car> getCarByMarca(String marca) {
		return repo.findByMarca(marca);
	}
}
