package br.ufc.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.ufc.server.model.Car;
import br.ufc.server.service.CarService;

@RestController
@CrossOrigin
@RequestMapping(path = "/api/carros")
public class CarController {
	
	@Autowired
	CarService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Car>> getCars() {
        return new ResponseEntity<List<Car>>(service.getCars(), HttpStatus.OK);
    }
	
	@RequestMapping(method = RequestMethod.GET, value = "{id}")
	public ResponseEntity<Car> getCar(@PathVariable("id") Integer id) {
		Car car = service.getCar(id);
		if(car == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Car>(service.addCar(car), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/search", params = "marca")
    public ResponseEntity<List<Car>> getCarByLogin(@RequestParam("marca") String marca) {
        return new ResponseEntity<>(service.getCarByMarca(marca), HttpStatus.OK);
    }
	
	@RequestMapping(method = RequestMethod.GET, value = "/search", params = "qtd")
	public ResponseEntity<List<Car>> getQtdCar(@RequestParam("qtd") Integer qtd) {
		return new ResponseEntity<>(service.getQtdCar(qtd), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Car> addCar(@RequestBody Car car) {
		return new ResponseEntity<Car>(service.addCar(car), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "{id}")
	public ResponseEntity<Car> updateCar(@PathVariable("id") Integer id, @RequestBody Car car) {
		return new ResponseEntity<Car>(service.updateCar(id, car), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "{id}")
	public ResponseEntity<Void> deleteCar(@PathVariable("id") Integer id) {
		if (service.removeCar(id)) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
}
