package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import dao.CarDAO;
import model.Car;

@WebServlet("/api/carros/*")
public class CarService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CarService() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// GET BY ID
		String pathInfo = request.getPathInfo();
		if (pathInfo != null) {
			String[] params = pathInfo.split("/");

			if (params.length > 0) {
				Car car = CarDAO.getCar(Integer.parseInt(params[1]));

				if (car != null) {
					JSONObject json = jsonCar(car);

					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");
					response.getWriter().print(json.toString());
					response.getWriter().flush();
				}

				return;
			}
		}

		// GET ALL
		List<Car> list = CarDAO.getAllCars();
		try {
			JSONArray jArray = new JSONArray();

			for (Car car : list) {
				jArray.put(jsonCar(car));
			}

			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(jArray.toString());
			response.getWriter().flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		StringBuilder jb = buildStringBuilder(request);

		Car car = null;
		JSONObject json = null;

		try {
			json = new JSONObject(jb.toString());
			car = CarDAO.addCar(json.getString("nome"), json.getString("marca"), json.getInt("anoFabricacao"),
					json.getInt("anoModelo"), json.getString("dataVenda"));

			json = jsonCar(car);

		} catch (Exception e) {
			e.printStackTrace();
		}

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(json.toString());
		response.getWriter().flush();
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String pathInfo = request.getPathInfo();

		if (pathInfo != null) {
			String[] params = pathInfo.split("/");
			StringBuilder jb = buildStringBuilder(request);

			Car car = null;
			JSONObject json = null;

			try {
				
				json = new JSONObject(jb.toString());
				car = CarDAO.updateUser(Integer.parseInt(params[1]), json.getString("nome"), json.getString("marca"),
						json.getInt("anoFabricacao"), json.getInt("anoModelo"), json.getString("dataVenda"));

				json = jsonCar(car);

			} catch (Exception e) {
				e.printStackTrace();
			}

			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(json.toString());
			response.getWriter().flush();
		}
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String pathInfo = request.getPathInfo();

		if (pathInfo != null) {
			String[] params = pathInfo.split("/");

			if (params.length > 0) {
				CarDAO.deleteUser(Integer.parseInt(params[1]));
				
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().flush();
			}
		}
	}

	public static JSONObject jsonCar(Car car) {
		JSONObject json = new JSONObject();
		json.put("id", car.getId());
		json.put("marca", car.getMarca());
		json.put("nome", car.getNome());
		json.put("anoFabricacao", car.getAnoFabricacao());
		json.put("anoModelo", car.getAnoModelo());
		json.put("dataVenda", car.getDataVenda());

		return json;
	}

	private StringBuilder buildStringBuilder(HttpServletRequest request) {
		StringBuilder sb = new StringBuilder();
		String line = null;

		BufferedReader reader;
		try {
			reader = request.getReader();
			while ((line = reader.readLine()) != null)
				sb.append(line);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return sb;
	}

}
