<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org">
      
<head>

<meta charset="ISO-8859-1">
<link rel="icon" type="image/x-icon" href="/img/icon.png">
<title>Add Employee</title>

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
			<p><i class="fa fa-plus-square icon"></i>Add Employee</p>
		</div>
		<div class="tableContainer">		
		
			<form action="#" th:action="@{/addEmployee}" method="post" th:object="${employee}" autocomplete="off">
				<label> <i class="fa fa-bookmark"></i> Employee ID : </label><br> <input type="text" th:field="*{empId}"><br><br th:if="${#fields.hasErrors('empId')}">
				<span class="spanCustom" th:if="${#fields.hasErrors('empId')}" th:errors="*{empId}">empId Error</span><br th:if="${#fields.hasErrors('empId')}"><br>
				<label> <i class="fa fa-user-circle-o"></i> Employee Name : </label><br> <input type="text" th:field="*{empName}"><br><br th:if="${#fields.hasErrors('empName')}">
				<span class="spanCustom" th:if="${#fields.hasErrors('empName')}" th:errors="*{empName}">empName Error</span><br th:if="${#fields.hasErrors('empName')}"><br>
				<label> <i class="fa fa-venus-mars"></i> Employee Gender : </label><br>	<select th:field="*{empGender}">
																							<option value="Male">Male</option>
																							<option value="Female">Female</option>
																						</select><br>
				<span class="spanCustom" th:if="${#fields.hasErrors('empGender')}" th:errors="*{empGender}">empGender Error</span><br th:if="${#fields.hasErrors('empGender')}"><br>
				<label> <i class="fa fa-envelope"></i> Employee Email : </label><br> <input type="text" th:field="*{empEmail}"><br><br th:if="${#fields.hasErrors('empEmail')}">
				<span class="spanCustom" th:if="${#fields.hasErrors('empEmail')}" th:errors="*{empEmail}">empEmail Error</span><br th:if="${#fields.hasErrors('empEmail')}"><br>
				<label> <i class="fa fa-phone"></i> Employee Phone : </label><br> <input type="text" th:field="*{empPhone}"><br><br th:if="${#fields.hasErrors('empPhone')}">
				<span class="spanCustom" th:if="${#fields.hasErrors('empPhone')}" th:errors="*{empPhone}">empPhone Error</span><br th:if="${#fields.hasErrors('empPhone')}"><br>
				<label> <i class="fa fa-address-book"></i> Employee Position : </label><br> <input type="text" th:field="*{empPosition}"><br><br th:if="${#fields.hasErrors('empPosition')}">
				<span class="spanCustom" th:if="${#fields.hasErrors('empPosition')}" th:errors="*{empPosition}">empPosition Error</span><br th:if="${#fields.hasErrors('empPosition')}"><br>
				<!-- Enter Time : --> <input type="text" th:field="*{lastUpdated}" style="display:none;">
				<span style="display:none;" th:if="${#fields.hasErrors('lastUpdated')}" th:errors="*{lastUpdated}">lastUpdated Error</span>
				
				<center><input type="submit" value="Submit" class="buttonLike" style="padding:7px; width:100px;"></center>
			</form>
			
			<!-- 
			<div> 
				<hr style="border:0px; border-bottom: 1px solid #9900e6; width:45%; height:20px; float:left;"> 
				<p style="width:10%; height:20px; float:left;">&nbsp; OR</p>
				<hr style="border:0px; border-bottom: 1px solid #9900e6; width:45%; height:20px; padding-top:7px;">
			</div><br>
			
			<form action="#" th:action="@{/addBulkEmployee}" method="post" autocomplete="off">
				
				<label> <i class="fa fa-file-excel-o"></i> Bulk Upload File : </label><br><br> <input type="file" name="bulkFile" class="custom-file-input"><br><br>
				
				<center><input type="submit" value="Submit" class="buttonLike" style="padding:7px; width:100px;"></center>
			</form>
			-->
			
		 </div>
	</div>
	
	<div id='hideMe' class="noti notiSuccess" th:if="${notification.type == 'Success'}" th:text="${notification.message}"></div>	 
	<div id='hideMe' class="noti notiError" th:if="${notification.type == 'Error'}" th:text="${notification.message}"></div>
	
	<div>&nbsp;</div>

</body>
</html>