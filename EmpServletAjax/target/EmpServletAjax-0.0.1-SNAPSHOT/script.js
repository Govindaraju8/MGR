$(document).ready(function () {
	let currentEmployeeIndex = -1;
    let employeeData = [];

    function displayEmployeeData() {
        if (employeeData.length > 0 && currentEmployeeIndex >= 0) {
            const employee = employeeData[currentEmployeeIndex];
            $("#empNo").val(employee.empNo);
            $("#name").val(employee.name);
            $("#job").val(employee.job);
            $("#salary").val(employee.salary);
            $("#department").val(employee.department);
        }
    }

    function loadEmployeeData() {
        $.ajax({
            url: "EmployeeServlet?action=getEmployeeData",
            method: "GET",
            dataType: "json",
            success: function(data) {
                employeeData = data;
                currentEmployeeIndex = 0;
                displayEmployeeData();
            }
        });
    }

    loadEmployeeData();

    $("#nextButton").click(function() {
        if (currentEmployeeIndex < employeeData.length - 1) {
            currentEmployeeIndex++;
            displayEmployeeData();
        }
    });

    $("#prevButton").click(function() {
        if (currentEmployeeIndex > 0) {
            currentEmployeeIndex--;
            displayEmployeeData();
        }
    });

    $("#firstButton").click(function() {
        currentEmployeeIndex = 0;
        displayEmployeeData();
    });

    $("#lastButton").click(function() {
        currentEmployeeIndex = employeeData.length - 1;
        displayEmployeeData();
    });

    $("#clearFormButton").click(function() {
        $("#empNo").val("");
        $("#name").val("");
        $("#job").val("");
        $("#salary").val("");
        $("#department").val("");
    });

    $("#exitButton").click(function() {
        window.close();
    });

    
    $("#getEmployeeDataButton").click(function () {
        $.ajax({
            url: "http://localhost:9011/EmpServletAjax/EmployeeServlet",
            type: "GET",
            data: { action: "getEmployeeData" },
            dataType: "json",
            success: function (data) {
                // Clear the table body
                $("#employeeTable tbody").empty();

                // Populate the table with data received from the server
                $.each(data, function (index, employee) {
                    var row = $("<tr></tr>");
                    row.append($("<td>" + employee.empNo + "</td>"));
                    row.append($("<td>" + employee.name + "</td>"));
                    row.append($("<td>" + employee.job + "</td>"));
                    row.append($("<td>" + employee.salary + "</td>"));
                    row.append($("<td>" + employee.department + "</td>"));
                    $("#employeeTable tbody").append(row);
                });
            },
            error: function () {
                // Handle error appropriately
                alert("Error retrieving employee data.");
            }
        });
        
    });
    });
    