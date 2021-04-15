package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import dao.CarDAO;
import model.Car;

@WebServlet("/api/filter")
public class Filter extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Filter() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		filterNCars(request, response);
		filterMarca(request, response);
	}

	private void filterMarca(HttpServletRequest request, HttpServletResponse response) throws IOException {

		if (request.getParameter("marca") != null) {
			
			List<Car> cars = CarDAO.filterMarca(request.getParameter("marca"));
			JSONArray jArray = new JSONArray();
			
			for (Car car : cars) {
				jArray.put(CarService.jsonCar(car));
			}
			
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(jArray.toString());
			response.getWriter().flush();

			return;
		}
	}

	private void filterNCars(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (request.getParameter("qtd") != null) {
			int qtd;
			try {
				qtd = Integer.parseInt(request.getParameter("qtd"));
			} catch (NumberFormatException e) {
				qtd = 0;
			}
			
			List<Car> cars = CarDAO.getNCars(qtd);
			JSONArray jArray = new JSONArray();
			
			for (Car car : cars) {
				jArray.put(CarService.jsonCar(car));
			}
			
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(jArray.toString());
			response.getWriter().flush();

			return;
		}
	}

}
