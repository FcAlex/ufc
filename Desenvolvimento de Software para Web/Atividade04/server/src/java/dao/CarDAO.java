package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Car;

public class CarDAO {
	private static final Map<Integer, Car> cars = new HashMap<Integer, Car>();
	private static int i = 5;
	
	static {
		cars.put(0, new Car(0, "Gol", "Volkswagen", 2010, 2011, "2020-05-08"));
		cars.put(1, new Car(1, "Uno", "Fiat", 2018, 2018, "2020-05-20"));
		cars.put(2, new Car(2, "Palio", "Fiat", 2015, 2017, "2018-01-05"));
		cars.put(3, new Car(3, "Celta", "General Motors", 2000, 2001, "2005-12-21"));
		cars.put(4, new Car(4, "HB20", "Hyundai", 2019, 2019, "2019-10-12"));
	}

	public static Car getCar(int id) {
		return cars.get(id);
	}
	
	public static List<Car> getAllCars() {
		return new ArrayList<Car>(cars.values());
	}

	public static Car addCar(String nome, String marca, int anoFab, int anoModelo, String dataVenda) {
		Car car = new Car(i, nome, marca, anoFab, anoModelo, dataVenda);
		cars.put(car.getId(), car);
		i++;
		return car;
	}
	
	public static Car updateUser(int id, String nome, String marca, int anoFab, int anoModelo, String dataVenda) {
		Car car = new Car(id, nome, marca, anoFab, anoModelo, dataVenda);
		cars.put(car.getId(), car);
		return car;
	}
	
	public static void deleteUser(int id) {
		if(cars.containsKey(id)) cars.remove(id);
	}

	public static List<Car> getNCars(int n) {
		ArrayList<Car> nCars = new ArrayList<Car>();
		for(int i = 0; (i < cars.size() && i < n); i++) {
			nCars.add(cars.get(i));
		}
		return nCars;
	}

	public static List<Car> filterMarca(String marca) {
		ArrayList<Car> aux = new ArrayList<Car>();
		for (Car car : cars.values())
			if(car.getMarca().equals(marca)) {
				aux.add(car);
			}
		
		return aux;
	}

	
}
