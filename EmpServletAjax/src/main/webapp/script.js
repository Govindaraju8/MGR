$(document).ready(function () {
	let currentEmployeeIndex = -1;
    let employeeData = [];
    
    function clearInputFields() {
        $("#empNo").val("");
        $("#name").val("");
        $("#job").val("");
        $("#salary").val("");
        $("#department").val("");
    }

    function getCurrentEmployee() {
        return employeeData[currentEmployeeIndex];
    }

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


    $("#addEmployeeButton").click(function () {
        clearInputFields();
        currentEmployeeIndex = -1; 
        alert("Employee data cleared for adding.");

    });

    $("#editEmployeeButton").click(function () {
        const currentEmployee = getCurrentEmployee();
        if (currentEmployee) {
            $("#empNo").val(currentEmployee.empNo);
            $("#name").val(currentEmployee.name);
            $("#job").val(currentEmployee.job);
            $("#salary").val(currentEmployee.salary);
            $("#department").val(currentEmployee.department);
        }
        alert("Editing employee data.");
    });

  $("#deleteEmployeeButton").click(function () {
    const currentEmployee = getCurrentEmployee();
    if (currentEmployee) {
        const empNo = currentEmployee.empNo;
         if (confirm("Are you sure you want to delete this employee?")) {
        $.ajax({
            url: "EmployeeServlet",
            data: {
                action: "deleteEmployee",
                empNo: empNo
            },
            success: function (response) {
                if (response === "Success") {
                    employeeData.splice(currentEmployeeIndex, 1);
                    currentEmployeeIndex = -1;
                    displayEmployeeData();
                     alert("Employee deleted successfully.");
                } else {
                    alert("Error deleting employee.");
                }
            },
            error: function () {
                alert("Error deleting employee.");
            }
        });
    }
}
});

    

    $("#saveEmployeeButton").click(function () {
        const inputEmpNo = $("#empNo").val();
        const inputName = $("#name").val();
        const inputJob = $("#job").val();
        const inputSalary = $("#salary").val();
        const inputDepartment = $("#department").val();

        // Validate name field
        if (!/^[a-zA-Z ]+$/.test(inputName)) {
            alert("Name should contain only letters and spaces.");
            return;
        }
         // Validate job field
        if (!/^[a-zA-Z ]+$/.test(inputJob)) {
            alert("Name should contain only letters and spaces.");
            return;
        }
        // Validate empNo field
        if (!/^\d+$/.test(inputEmpNo)) {
            alert("Employee Number should contain only numbers.");
            return;
        }

        // Validate salary field
        if (!/^\d+$/.test(inputSalary)) {
            alert("Salary should contain only numbers.");
            return;
        }

        // Validate unique empNo
        if (currentEmployeeIndex === -1) {
            const isEmpNoUnique = employeeData.every(employee => employee.empNo !== inputEmpNo);
            if (!isEmpNoUnique) {
                alert("Employee Number must be unique.");
                return;
            }
        }


        const employee = {
            empNo: empNo,
            name: name,
            job: job,
            salary: salary,
            department: department
        };
    

        if (currentEmployeeIndex === -1) {
            // Adding a new employee
            $.ajax({
                url: "EmployeeServlet",
                data: {
                    action: "addEmployee",
                    empNo: empNo,
                    name: name,
                    job: job,
                    salary: salary,
                    department: department
                },
                success: function (response) {
                    if (response === "Success") {
                        employeeData.push(employee);
                        currentEmployeeIndex = employeeData.length - 1;
                        displayEmployeeData();
                        alert("Employee added successfully.");
                    } else {
                        alert("Error adding employee.");
                    }
                },
                error: function () {
                    alert("Error adding employee.");
                }
            });
        } else {
            // Editing an existing employee
            $.ajax({
                url: "EmployeeServlet",
                data: {
                    action: "editEmployee",
                    empNo: empNo,
                    name: name,
                    job: job,
                    salary: salary,
                    department: department
                },
                success: function (response) {
                    if (response === "Success") {
                        employeeData[currentEmployeeIndex] = employee;
                        displayEmployeeData();
                        alert("Employee edited successfully.");
                    } else {
                        alert("Error editing employee.");
                    }
                },
                error: function () {
                    alert("Error editing employee.");
                }
            });
        }
    });
    
    $("#getEmployeeDataButton").click(function () {
        $.ajax({
            url: "http://localhost:8080/EmpServletAjax/EmployeeServlet",
         
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
    