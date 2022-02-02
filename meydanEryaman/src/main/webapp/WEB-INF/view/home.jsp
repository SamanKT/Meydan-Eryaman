<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Meydan Eryaman Site Sakinler Sitesi</title>
<style>
input[type=submit] {
	width: 100%;
	background-color: #4CAF50;
	color: white;
	padding: 14px 20px;
	margin: 8px 0;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

input[type=submit]:hover {
	background-color: #45a049;
}
.error {
	color: red;
	position: relative;
	text-align: left;
	font-style: italic;
	font-size: small;
	margin-left: 15px;
}
</style>
<script type="text/javascript">
	function isActice() {

		if (document.getElementById("radio").checked == true) {
			document.getElementById("car").disabled = false;
		} else {
			document.getElementById("car").disabled = true;
		}

	}
</script>
</head>
<body>
	<div style="color: olive;">
		<h2>Meydan Eryaman Site Sakinler Sitesine Hos Geldiniz</h2> <br> <a href="/meydanEryaman/result">Sonuclar</a>
		<hr>
		<br>
	</div>
	<form:form action="confirm" modelAttribute="meydanInfo">
Adiniz: <form:input path="name" /><form:errors path="name" cssClass="error" /> &nbsp &nbsp Soyadiniz: <form:input
			path="lastName" /><form:errors path="lastName" cssClass="error" /> &nbsp &nbsp 
Telefon numaraniz: <form:input path="tel" placeholder="5??" />
		<form:errors path="tel" cssClass="error" />
		<br>
		<br>
		<hr>
		<br>
Blok: <form:select path="block" cssStyle="width: 10%;">
			<form:option value="A">A</form:option>
			<form:option value="B">B</form:option>
			<form:option value="C">C</form:option>
			<form:option value="D">D</form:option>
			<form:option value="E">E</form:option>
			<form:option value="F">F</form:option>
		</form:select>
		<span>---</span>
Daire: <form:select path="no" cssStyle="width: 10%;">
			<form:options items="${items}" />
		</form:select>
		<br>
		<br>
		<hr>
		<br>
Arabaniz var mi? <br>
Evet <form:radiobutton path="carOwn" value="true" id="radio"
			onclick="return isActice()" />
		<span> &nbsp &nbsp</span>
Hayir <form:radiobutton path="carOwn" value="false"
			onclick="return isActice()" />
		<br>
		<br>
		<hr>
		<br>

Arabaniza Kac kisi alabilirsiniz? <form:select path="capacity" id="car"
			disabled="true" cssStyle="width: 10%;">
			<form:option value="1">1</form:option>
			<form:option value="2">2</form:option>
			<form:option value="3">3</form:option>
			<form:option value="4">4</form:option>
		</form:select>
		<br>
		<br>
		<input type="submit" value="Kaydet">
	</form:form>
</body>
</html>