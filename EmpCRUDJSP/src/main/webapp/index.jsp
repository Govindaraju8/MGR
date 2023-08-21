<%@ page language="java" import="java.util.*,java.sql.*,java.io.*" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <title>CRUD Assignment</title>
</head>
<body>
    <center>
        <h1 style="padding-top:170px;">Employee Login</h1>
        <form action="index.jsp" method="post">
            <table>
                <!-- Form fields for employee data -->
                <tr><td><I>Employee No</I></td><td><b>:</b></td><td><input type="number" name="empno" id="eno"></td></tr>
                <tr><td><I>Name</I></td><td><b>:</b></td><td><input type="text" name="name" id="ename" onkeydown="return /[a-z]/i.test(event.key)"></td></tr>
                <tr><td><I>Job</I></td><td><b>:</b></td><td><input type="text" name="job" id="ejob" onkeydown="return /[a-z]/i.test(event.key)"></td></tr>
                <tr><td><I>Salary</I></td><td><b>:</b></td><td><input type="number" name="salary" id="esal"></td></tr>
                <tr><td><I>Department</I></td><td><b>:</b></td><td><input type="text" name="dept" id="edept" onkeydown="return /[a-z]/i.test(event.key)"></td></tr>
            </table>
            <br><br>
            <div id="message"></div>
            <br><br>
            <table>
                <tr>
                    <td><input type="submit" value="First" name="action" style="color:white; background-color:blue;font-size: 15px;width: 80px; height: 22px; border-radius: 50px 20px;" ></td>
                    <td><input type="submit" value="Last" name="action" style="color:white; background-color:blue;font-size: 15px;width: 80px; height: 22px; border-radius: 50px 20px;" ></td>
                    <td><input type="submit" value="Prev" name="action"style="color:white; background-color:blue;font-size: 15px;width: 80px; height: 22px; border-radius: 50px 20px;" ></td>
                    <td><input type="submit" value="Next" name="action"style="color:white; background-color:blue;font-size: 15px;width: 80px; height: 22px; border-radius: 50px 20px;" ></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Add" name="action"style="color:white; background-color:blue;font-size: 15px;width: 80px; height: 22px; border-radius: 50px 20px;" ></td>
                    <td><input type="submit" value="Edit" name="action" style="color:white; background-color:blue;font-size: 15px;width: 80px; height: 22px; border-radius: 50px 20px;"></td>
                    <td><input type="submit" value="Delete" name="action" style="color:white; background-color:blue;font-size: 15px;width: 80px; height: 22px; border-radius: 50px 20px;"></td>
                    <td><input type="submit" value="Search" name="action" style="color:white; background-color:blue;font-size: 15px;width: 80px; height: 22px; border-radius: 50px 20px;"></td>
                </tr>
            </table>
            <table>
                <tr>
                    <td><input type="submit" value="Save" name="action"style="color:white; background-color:blue;font-size: 15px;width: 80px; height: 22px; border-radius: 50px 20px;" ></td>
                    <td><input type="button" value="Clear" name="clear" onclick="clearfun()"style="color:white; background-color:blue;font-size: 15px;width: 80px; height: 22px; border-radius: 50px 20px;" ></td>
                    <td><input type="button" value="Exit" name="exit" onclick="exitfun()" style="color:white; background-color:red;font-size: 15px;width: 80px; height: 22px; border-radius: 50px 20px;"></td>
                </tr>
            </table>
        </form>
    </center>

    <%
    try {
        // Database connection
        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection("jdbc:postgresql://192.168.110.48:5432/plf_training", "plf_training_admin", "pff123");
        PreparedStatement p=conn.prepareStatement("select * from king_emp1",ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        String query="";
        String action = request.getParameter("action");
        int length=0;
    	PreparedStatement leng=conn.prepareStatement("select count(*) from king_emp1");
    	ResultSet rsl=leng.executeQuery();
    	if(rsl.next())
    	{
    		length=rsl.getInt(1);
    	}
        if (action != null) {
            if (action.equals("Add") && session.getAttribute("editRow")!=null) {
            	%><center><h3>Edit mode on...</h3></center><%
        		System.out.println("Edit mode on");
        		session.setAttribute("addRow", true);
        		String s=request.getParameter("empno")+":"+request.getParameter("name")+":"+request.getParameter("job")+":"+request.getParameter("salary")+":"+request.getParameter("dept");
        		session.setAttribute("DataToAdd",s);
            } else if (action.equals("Edit")) {
            	%><center><h3>Edit mode on...</h3></center><%
        		System.out.println("Edit mode on");
        		session.setAttribute("editRow", true);
            } else if (action.equals("Delete") && session.getAttribute("editRow")!=null) {
            	%><center><h3>Edit mode on...</h3></center><%
        		System.out.println("Edit mode on");
        		session.setAttribute("delRow", true);
        		String s=request.getParameter("empno");
        		session.setAttribute("DataToDel",s);
            } else if (action.equals("Search")) {
            	int x=Integer.parseInt(request.getParameter("empno"));
        		PreparedStatement f=conn.prepareStatement("select * from king_emp1");
        		ResultSet rs=f.executeQuery();
        		while(rs.next())
        		{
        			if(x==Integer.parseInt(rs.getString("empno")))
        			%>
        			<script>
        			document.getElementById("eno").value ='<%=rs.getString("empno")%>';
        			document.getElementById("ename").value ='<%=rs.getString("name")%>';
        			document.getElementById("ejob").value ='<%= rs.getString("job")%>';
        			document.getElementById("esal").value = '<%=rs.getString("salary")%>';
        			document.getElementById("edept").value ='<%= rs.getString("department")%>';
        			</script>
        			<%
        			break;
        		}
            } 
            else if (action.equals("Save") && session.getAttribute("delRow") != null) {
            	session.removeAttribute("editRow");
        		session.removeAttribute("delRow");
        		String s=(String)session.getAttribute("DataToDel");
        		int x=Integer.parseInt(s);
        		query="delete from king_emp1 where empno="+x;
        		PreparedStatement ps=conn.prepareStatement(query);
        		ps.executeUpdate();
        		System.out.println("record deleted successfully");
        		%><center><h3>Record deleted successfully...</h3></center><%
            } else if (action.equals("First")) {
            	session.setAttribute("counter",1);
        		PreparedStatement f=conn.prepareStatement("select * from king_emp1 limit 1");
        		ResultSet rs=f.executeQuery();
        		while(rs.next())
        		{
        		System.out.println("first	"+rs.getString("empno"));
        		%>
        		<script>
        		document.getElementById("eno").value ='<%=rs.getString("empno")%>';
        		document.getElementById("ename").value ='<%=rs.getString("name")%>';
        		document.getElementById("ejob").value ='<%= rs.getString("job")%>';
        		document.getElementById("esal").value = '<%=rs.getString("salary")%>';
        		document.getElementById("edept").value ='<%= rs.getString("department")%>';
        		</script>
        		<%
        		}
            } else if (action.equals("Last")) {
            	session.setAttribute("counter",length);
        		PreparedStatement l=conn.prepareStatement("select * from king_emp1",ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        		ResultSet rs=l.executeQuery();
        		while(rs.next())
        		{
        			if(rs.last())
        			{
        				System.out.println("Last	"+rs.getString("empno"));
        				%>
        				<script>
        				document.getElementById("eno").value ='<%=rs.getString("empno")%>';
        				document.getElementById("ename").value ='<%=rs.getString("name")%>';
        				document.getElementById("ejob").value ='<%= rs.getString("job")%>';
        				document.getElementById("esal").value = '<%=rs.getString("salary")%>';
        				document.getElementById("edept").value ='<%= rs.getString("department")%>';
        				</script>
        				<%
        			}
        		}
            } else if (action.equals("Prev")) {
            	int c=(int)session.getAttribute("counter");
        		c--;
        		if(c>=1)
        		{
        			session.setAttribute("counter",c);
        			PreparedStatement f=conn.prepareStatement("select * from king_emp1",ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        			ResultSet rs=f.executeQuery();
        			if(rs.absolute(c))
        			{
        				System.out.println("prev	"+rs.getString("empno"));
        				%>
        				<script>
        				document.getElementById("eno").value ='<%=rs.getString("empno")%>';
        				document.getElementById("ename").value ='<%=rs.getString("name")%>';
        				document.getElementById("ejob").value ='<%= rs.getString("job")%>';
        				document.getElementById("esal").value = '<%=rs.getString("salary")%>';
        				document.getElementById("edept").value ='<%= rs.getString("department")%>';
        				</script>
        				<%
        			}
        		}
            } else if (action.equals("Next")) {
            	int c=(int)session.getAttribute("counter");
        		c++;
        		if(c<=length)
        		{
        			session.setAttribute("counter",c);
        			PreparedStatement f=conn.prepareStatement("select * from king_emp1",ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        			ResultSet rs=f.executeQuery();
        			if(rs.absolute(c))
        			{
        				System.out.println("prev	"+rs.getString("empno"));
        				%>
        				<script>
        				document.getElementById("eno").value ='<%=rs.getString("empno")%>';
        				document.getElementById("ename").value ='<%=rs.getString("name")%>';
        				document.getElementById("ejob").value ='<%= rs.getString("job")%>';
        				document.getElementById("esal").value = '<%=rs.getString("salary")%>';
        				document.getElementById("edept").value ='<%= rs.getString("department")%>';
        				</script>
        				<%
        			}
        		}            }
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
    %>

    <script>
        function clearfun() {
            document.getElementById("eno").value = "";
            document.getElementById("ename").value = "";
            document.getElementById("ejob").value = "";
            document.getElementById("esal").value = "";
            document.getElementById("edept").value = "";
        }
        function exitfun() {
            window.close();
        }
    </script>
</body>
</html>
