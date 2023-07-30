function addPassenger() {
  var table = document.querySelector("table.center1");
  var rowCount = table.rows.length;
  if (rowCount <= 6) {
    var row = table.insertRow(rowCount);
    var cell1 = row.insertCell(0);
    cell1.innerHTML = rowCount;
  
    var cell2 = row.insertCell(1);
    cell2.innerHTML = '<input onkeydown="return /[a-z]/i.test(event.key)" >';
  
    var cell3 = row.insertCell(2);
    cell3.innerHTML = '<select><option value="select">--Select--</option><option value="Male">Male</option><option value="Female">Female</option><option value="Transgender">Transgender</option></select>';
  
    var cell4 = row.insertCell(3);
    cell4.innerHTML = '<input type="number" min="5" max="100">';
  
    var cell5 = row.insertCell(4);
    cell5.innerHTML = '<button type="button" onclick="removePassenger()">Cancel</button>';
  } else {
    alert("You can add a maximum of 6 passengers.");
  }
}

function removePassenger() {
  var table = document.querySelector("table.center1");
  var rowCount = table.rows.length;
  
  if (rowCount > 2) {
    table.deleteRow(rowCount - 1);
  }
}

document.getElementById("bookingForm").addEventListener("submit", function(event) {
  event.preventDefault();
  if (validateForm()) {
	alert("Ticket Booked!");
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

function resetPage() {
  var table = document.querySelector("table.center1");
  while (table.rows.length > 2) {
    table.deleteRow(table.rows.length - 1);
  }
  document.getElementById("bookingForm").reset();
}
