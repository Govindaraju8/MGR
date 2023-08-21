package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Passenger;
import model.PassengerList;
import model.Ticket;



public class BookingServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get form data and create Ticket object
		String fromStation = request.getParameter("from");
		String toStation = request.getParameter("to");
		String train = request.getParameter("train");
		String date = request.getParameter("date");
		String trainClass = request.getParameter("class");
		System.out.println(train + date + trainClass);
		Ticket ticket = new Ticket();
		ticket.setFromStation(fromStation);
		ticket.setToStation(toStation);
		ticket.setTrain(train);
		ticket.setDate(date);
		ticket.setTrainClass(trainClass);

		// Add passenger details to the Ticket object
		PassengerList passengers = new PassengerList();
		String[] passengerNames = request.getParameterValues("passengerName");
		String[] passengerGenders = request.getParameterValues("passengerGender");
		String[] passengerAges = request.getParameterValues("passengerAge");
		for (int i = 0; i < passengerNames.length; i++) {
			Passenger passenger = new Passenger();
			passenger.setName(passengerNames[i]);
			passenger.setGender(passengerGenders[i]);
			passenger.setAge(Integer.parseInt(passengerAges[i]));
			passengers.addPassenger(passenger);
		}

		ticket.setPassengers(passengers);
		request.setAttribute("passengers",passengers);
		request.setAttribute("fromStation", fromStation );
		request.setAttribute("toStation", toStation );
		request.setAttribute("train", train );
		request.setAttribute("date", date );
		request.setAttribute("class", trainClass );
		RequestDispatcher dispatcher = request.getRequestDispatcher("confirmation.jsp");
		dispatcher.forward(request, response);
	}
}