<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org">
      
<head>

<meta charset="ISO-8859-1">
<link rel="icon" type="image/x-icon" href="/img/icon.png">
<title>Employee Dashboard</title>

<!-- CSS -->
<link rel="stylesheet" href="/css/style.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">  

<!-- JS -->

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.js"></script>
<script src="/js/index.js"></script>

</head>

<body>

	<div class="header">
		<img class="logo" src="/img/icon.png" alt="Logo" style="cursor: pointer;" onclick="location.href = '/';">
		<p class="projectTitle">My First<br> Assignment</p>
		<p class="doneBy">Done By :<br> Allwin Jones</p>
	</div>
	
	<div class="container">
		<div class="containerTitle">
			<p><i class="fa fa-users"></i> All Employees</p>
		</div>
		<div class="tableContainer">		
		
			<div>
			<button onclick="location.href = '/addEmployee';" class="vlClr"><i class="fa fa-plus-square icon"></i><span class="buttonText">Add Employee</span></button>
			
			<form action="/searchEmployee" method="post" class="searchForm"> 
				<input type="text" name="searchText" th:value="${searchText}" class="searchBox" placeholder="Search Text" style="width:200px;"> 
				<button type="submit" style="float:right;" class="vlClr"><i class="fa fa-search icon"></i><span class="buttonText">Search</span></button> 
			</form>
			</div>
		
			<table id="example" style="min-width:1000px;">
			<thead>
			  <tr>
			    <th>Employee ID</th>
			    <th>Name</th>
			    <th>Gender</th>
			    <th>Email</th>
			    <th>Phone</th>
			    <th>Position</th>
			    <th>Actions</th>
			  </tr>
			 </thead>
			 <tbody id="employeeRows" th:if="${notification.message != 'No Match Found !'}">
			  <tr  th:each="employee : ${employeeList}">
			  	<td th:text="${employee.empId}" class="borderRight">Employee ID</td>
				<td th:text="${employee.empName}" class="borderRight">Name</td>
				<td th:text="${employee.empGender}" class="borderRight">Gender</td>
				<td th:text="${employee.empEmail}" class="borderRight">Email</td>
				<td th:text="${employee.empPhone}" class="borderRight">Phone</td>
				<td th:text="${employee.empPosition}" class="borderRight">Position</td>
				<td class="borderLeft">
				<a th:href="@{'/viewEmployee/' + ${employee.empId}}" class="blClr"><i class="fa fa-search icon"></i><span class="buttonText">View</span></a>
				<a th:href="@{'/addAddress/' + ${employee.empId}}" class="gnClr"><i class="fa fa-plus-square icon"></i><span class="buttonText">Add Address</span></a>
				<a th:href="@{'/editEmployee/' + ${employee.empId}}" class="ylClr"><i class="fa fa-pencil-square icon"></i><span class="buttonText">Edit</span></a>
				<a th:href="@{'/deleteEmployee/' + ${employee.empId}}" class="rdClr"><i class="fa fa-trash icon"></i><span class="buttonText">Delete</span></a>
				</td>     
			  </tr>
			 </tbody>
			 </table>
		 </div>
	</div>
	
	<div id='hideMe' class="noti notiSuccess" th:if="${notification.type == 'Success'}" th:text="${notification.message}"></div>	 
	<div id='hideMe' class="noti notiError" th:if="${notification.type == 'Error'}" th:text="${notification.message}"></div>
	
	<div>&nbsp;</div>

</body>
</html>