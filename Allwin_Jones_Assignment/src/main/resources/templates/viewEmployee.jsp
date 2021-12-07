<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org">
      
<head>

<meta charset="ISO-8859-1">
<link rel="icon" type="image/x-icon" href="/img/icon.png">
<title>View Employee</title>

<!-- CSS -->
<link rel="stylesheet" href="/css/style.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<!-- JS -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
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
			<p><i class="fa fa-address-card"></i> Employee Details</p>
		</div>
		
		<div class="tableContainer">
			<div style="float:right;">
				<a th:href="@{'/addAddress/' + ${employeeData.empId}}" class="gnClr" style="margin-left:20px; width:155px;"><i class="fa fa-plus-square icon"></i><span class="buttonText">Add Address</span></a><br>
				<a th:href="@{'/editEmployee/' + ${employeeData.empId}}" class="ylClr" style="margin-left:20px; width:155px;"><i class="fa fa-pencil-square icon"></i><span class="buttonText">Edit Employee</span></a><br>
				<a th:href="@{'/deleteEmployee/' + ${employeeData.empId}}" class="rdClr" style="margin-left:20px; width:155px;"><i class="fa fa-trash icon"></i><span class="buttonText">Delete Employee</span></a>
			</div>
					
			<table>
			  <tr>
			  	<td class="thLike"><i class="fa fa-bookmark"></i> Employee ID</td>
				<td th:text="${employeeData.empId}" class="tdLike">Employee ID</td>
			  </tr>
			  <tr>
			  	<td class="thLike"><i class="fa fa-user-circle-o"></i> Name</td>
				<td th:text="${employeeData.empName}" class="tdLike">Name</td>
			  </tr>
			  <tr>
			  	<td class="thLike"><i class="fa fa-venus-mars"></i> Gender</td>
				<td th:text="${employeeData.empGender}" class="tdLike">Gender</td>
			  </tr>
			  <tr>
			  	<td class="thLike"><i class="fa fa-envelope"></i> Email</td>
				<td th:text="${employeeData.empEmail}" class="tdLike">Email</td>
			  </tr>
			  <tr>
			  	<td class="thLike"><i class="fa fa-phone"></i> Phone</td>
				<td th:text="${employeeData.empPhone}" class="tdLike">Phone</td>
			  </tr>
			  <tr>
			  	<td class="thLike"><i class="fa fa-address-book"></i> Position</td>
				<td th:text="${employeeData.empPosition}" class="tdLike">Position</td>
			  </tr>
			  <tr th:if="${addressData[0].addressId != 0}">
			  	<td th:rowspan="${#lists.size(addressData)}" class="thLike"><i class="fa fa-home"></i> Address(es)</td>
			  	<td th:text="${addressData[0].street+', '+addressData[0].city+', '+addressData[0].district+', '+addressData[0].state+' - '+addressData[0].pincode}" class="tdLike">Address</td>
			  </tr>
			  <tr th:each="addressData,row : ${addressData}" th:if="${row.count} &gt; 1">
				<td th:text="${addressData.street+', '+addressData.city+', '+addressData.district+', '+addressData.state+' - '+addressData.pincode}" class="tdLike">Address</td>
			  </tr>
			</table> 
		 </div>
	</div>
	
	<div id='hideMe' class="noti notiSuccess" th:if="${notification.type == 'Success'}" th:text="${notification.message}"></div>	 
	<div id='hideMe' class="noti notiError" th:if="${notification.type == 'Error'}" th:text="${notification.message}"></div>
	
	<div>&nbsp;</div>

</body>
</html>