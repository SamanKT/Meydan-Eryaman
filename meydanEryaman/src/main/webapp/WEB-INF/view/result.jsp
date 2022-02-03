<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sonuc Sayfasi</title>
<style type="text/css">
.error {
	color: red;
	position: relative;
	text-align: left;
	font-style: italic;
	font-size: small;
	margin-left: 15px;
}
.simple-input {
    display: inline-block;
    padding: 5px;
    border: 4px solid #F1B720;
    border-radius: 5px;
    color: #333;
    transition: all 0.3s ease-out;
    position: inherit;
}

.simple-input:hover {
    border-radius: 8px
}

.simple-input:focus {
    outline: none;
    border-radius: 8px;
    border-color: #EBD292;
}

.bordered-link {
    display: inline-block;
    padding: 6px;
    border: 3px solid #FCB326;
    border-radius: 6px;
    box-shadow: 
        0 2px 1px rgba(0, 0, 0, 0.2), 
        inset 0 2px 1px rgba(0, 0, 0, 0.2);
    /* Font styles */
    text-decoration: none;
    font-size: 12px;
    text-transform: uppercase;
    color: #222;
    width: 10%;
}

.bordered-link:hover {
    border-color: #FDD68B
}

.bordered-link:active {
    border-color: #FEE8BD
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
</head>
<body>
<form:form action="resultShow" modelAttribute="resultInfo">

<span style="font-family: sans-serif;">Lutfen telefon numaranizi basinda 0 olmadan giriniz: </span> <form:input path="telResult" placeholder="5??" cssClass="simple-input"/>
<form:errors path="telResult" cssClass="error"/> 

<br><br><input type="submit" value="Sonuc" class="bordered-link">
</form:form>
</body>
</html>