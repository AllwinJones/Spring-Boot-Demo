<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org">
      
<head>

<meta charset="ISO-8859-1">
<link rel="icon" type="image/x-icon" href="/img/icon.png">
<title>Add Address</title>

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
			<p><i class="fa fa-plus-square icon"></i>Add Address</p>
		</div>
		<div class="tableContainer">		
		
			<form action="#" th:action="@{/addAddress}" method="post" th:object="${address}" autocomplete="off">
				<!-- Enter Address ID : --> <input type="text" th:field="*{addressId}" style="display:none;">
				<span style="display:none;" th:if="${#fields.hasErrors('addressId')}" th:errors="*{addressId}">addressId Error</span>
				<!-- Enter Employee ID : --> <input type="text" th:field="*{empId}" style="display:none;">
				<span style="display:none;" th:if="${#fields.hasErrors('empId')}" th:errors="*{empId}">empId Error</span>
				<label> <i class="fa fa-street-view"></i> Street : </label><br> <input type="text" th:field="*{street}"><br><br th:if="${#fields.hasErrors('street')}">
				<span class="spanCustom" th:if="${#fields.hasErrors('street')}" th:errors="*{street}">street Error</span><br th:if="${#fields.hasErrors('street')}"><br>
				<label> <i class="fa fa-map-marker"></i> City : </label><br> <input type="text" th:field="*{city}"><br><br th:if="${#fields.hasErrors('city')}">
				<span class="spanCustom" th:if="${#fields.hasErrors('city')}" th:errors="*{city}">city Error</span><br th:if="${#fields.hasErrors('city')}"><br>
				<label> <i class="fa fa-area-chart"></i> District : </label><br> <input type="text" th:field="*{state}"><br><br th:if="${#fields.hasErrors('state')}">
				<span class="spanCustom" th:if="${#fields.hasErrors('state')}" th:errors="*{state}">state Error</span><br th:if="${#fields.hasErrors('state')}"><br>
				<label> <i class="fa fa-safari"></i> State : </label><br> <input type="text" th:field="*{district}"><br><br th:if="${#fields.hasErrors('district')}">
				<span class="spanCustom" th:if="${#fields.hasErrors('district')}" th:errors="*{district}">district Error</span><br th:if="${#fields.hasErrors('district')}"><br>
				<label> <i class="fa fa-hashtag"></i> Pincode : </label><br> <input type="text" th:field="*{pincode}"><br><br th:if="${#fields.hasErrors('pincode')}">
				<span class="spanCustom" th:if="${#fields.hasErrors('pincode')}" th:errors="*{pincode}">pincode Error</span><br th:if="${#fields.hasErrors('pincode')}"><br>
				<!-- Enter Time : --> <input type="text" th:field="*{lastUpdated}" style="display:none;">
				<span style="display:none;" th:if="${#fields.hasErrors('lastUpdated')}" th:errors="*{lastUpdated}">lastUpdated Error</span>
				
				<center><input type="submit" value="Submit" class="buttonLike" style="padding:7px; width:100px;"></center>
			</form>
			
		 </div>
	</div>
	
	<div id='hideMe' class="noti notiSuccess" th:if="${notification.type == 'Success'}" th:text="${notification.message}"></div>	 
	<div id='hideMe' class="noti notiError" th:if="${notification.type == 'Error'}" th:text="${notification.message}"></div>
	
	<div>&nbsp;</div>

</body>
</html>