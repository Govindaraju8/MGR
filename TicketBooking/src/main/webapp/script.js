$.ajax({
	url:"http://localhost:8080/TicketBooking/StationsServlet",
	success:function(response){
		var from = $("#from");
		response.forEach((item)=>{
			const newOption = $("<option>");
			newOption.text(item);
			from.append(newOption);
		})
	}
})
$.ajax({
	url:"http://localhost:8080/TicketBooking/StationsServlet",
	success:function(response){
		var from = $("#to");
		response.forEach((item)=>{
			const newOption = $("<option>");
			newOption.text(item);
			from.append(newOption);
		})
	}
})

$.ajax({
	url:"http://localhost:8080/TicketBooking/TrainsServlet",
	success:function(response){
		var from = $("#train");
		response.forEach((item)=>{
			const newOption = $("<option>");
			newOption.text(item);
			from.append(newOption);
		})
	}
})


$.ajax({
	url:"http://localhost:8080/TicketBooking/ClassesServlet",
	success:function(response){
		var from = $("#class");
		response.forEach((item)=>{
			const newOption = $("<option>");
			newOption.text(item);
			from.append(newOption);
		})
	}
})

















function addPassenger() {
  var table = document.querySelector("#tab");
  var rowCount = table.rows.length;
  if (rowCount <= 6) {
    var row = table.insertRow(rowCount);
    var cell1 = row.insertCell(0);
    cell1.innerHTML = rowCount;
  
    var cell2 = row.insertCell(1);
    cell2.innerHTML = '<input type="text" name="passengerName" onkeydown="return /[a-z]/i.test(event.key)">';
  
    var cell3 = row.insertCell(2);
    cell3.innerHTML = '<select name="passengerGender"><option value="select">--Select--</option><option value="Male">Male</option><option value="Female">Female</option><option value="Transgender">Transgender</option></select>';
  
    var cell4 = row.insertCell(3);
    cell4.innerHTML = '<input type="number" min="5" max="100" name="passengerAge">';
  
    var cell5 = row.insertCell(4);
    cell5.innerHTML = '<button type="button" onclick="removePassenger()">Cancel</button>';
    row.classList.add("passenger-row");
  } else {
    alert("You can add a maximum of 6 passengers.");
  }
}

function removePassenger() {
  var table = document.querySelector("#tab");
  var rowCount = table.rows.length;
  
  if (rowCount > 2) {
    table.deleteRow(rowCount - 1);
  }
}

document.getElementById("bookingForm").addEventListener("submit", function(event) {
  if (validateForm()) {
    alert("Ticket Booked!");
    displayDataAsJSON();
    resetPage();
  }
});



function validateForm() {
  var inputs = document.getElementsByTagName("input");
  var selects = document.getElementsByTagName("select");
  
  for (var i = 0; i < inputs.length; i++) {
    if (inputs[i].type === "text" && inputs[i].value.trim() === "") {
      alert("Please fill in all fields.");
      return false;
    }
  }
  
  for (var j = 0; j < selects.length; j++) {
    if (selects[j].value === "select") {
      alert("Please select all fields.");
      return false;
    }
  }
  var fromStation = document.getElementById("from").value;
  var toStation = document.getElementById("to").value;
  if (fromStation === toStation) {
    alert("The 'From' and 'To' stations cannot be the same.");
    return false;
  }
  var dateInput = document.getElementById("date").value;
  var selectedDate = new Date(dateInput);
  var today = new Date();
  today.setHours(0, 0, 0, 0); 

  if (selectedDate <= today) {
    alert("Please select a date not before today's date.");
    return false;
  }
  
  return true;
}

function displayDataAsJSON() {
  var formData = {
    passengers: []
  };
  var passengerRows = document.querySelectorAll(".passenger-row");
  passengerRows.forEach(function (row) {
    var passenger = {};
    passenger.name = row.querySelector("input[type='text']").value;
    passenger.gender = row.querySelector("select").value;
    passenger.age = row.querySelector("input[type='number']").value;
    formData.passengers.push(passenger);
  });
  var fromStation = document.getElementByName("from").value;
  var toStation = document.getElementByName("to").value;
  var dateInput = document.getElementById("date").value;
  var Class = document.getElementById("class").value;
  formData.fromStation = fromStation;
  formData.toStation = toStation;
  formData.date = dateInput;
  formData.Class = Class;
 alert("Booking Details:\n" + JSON.stringify(formData, null, 2));
}

function resetPage() {
  var table = document.querySelector("#tab");
  while (table.rows.length > 2) {
    table.deleteRow(table.rows.length - 1);
  }
  document.getElementById("bookingForm").reset();
}


