<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Passenger" %>
<%@ page import="model.PassengerList" %>
<%@ page import="dal.StationsDAL" %>
<%@ page import="dal.TrainsDAL" %>
<%@ page import="dal.ClassesDAL" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Booking Confirmation</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h1>Booking Confirmation</h1>
        <div class="card mt-3">
            <div class="card-body">
                <p>Your ticket has been booked successfully!</p>
                <p>Booking Details:</p>
                <ul>
                    <li><strong>PNR:</strong>2345676124</li>
                    <li><strong>From:</strong> <%= request.getAttribute("fromStation") %></li>
                    <li><strong>To:</strong> <%= request.getAttribute("toStation") %></li>
                    <li><strong>Train:</strong> <%= request.getAttribute("train") %></li>
                    <li><strong>Date:</strong> <%= request.getAttribute("date") %></li>
                    <li><strong>Class:</strong> <%= request.getAttribute("class") %></li>
                    <li><strong>Price:</strong>500rs</li>
                    
                </ul>
                <h2>Passenger Details:</h2>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Gender</th>
                            <th>Age</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% PassengerList passengers = (PassengerList) request.getAttribute("passengers");
    for (Passenger passenger : passengers.getPassengers()) { %>
    <tr>
        <td><%= passenger.getName() %></td>
        <td><%= passenger.getGender() %></td>
        <td><%= passenger.getAge() %></td>
    </tr>
<% } %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>
