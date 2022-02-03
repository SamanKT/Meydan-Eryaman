<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sonuc sayfasi</title>
</head>
<body>

<h3 style="color: olive;">Sayin ${resultInfo.nameResult}</h3> <hr>
Toplantiya numarasi altda yazilmis komsularimizla katilmaniz planlanmistir. Katiliminiz icin simdiden tesekkurler. <br><br>
<h4 style="color: red;">${driver.name} &nbsp ${driver.lastName} &nbsp &nbsp ${driver.tel}</h4>

<c:forEach var="i"  items="${list}">
<h4 style="color: red;">Sayin: ${i.name} ${i.lastName} &nbsp &nbsp ${i.tel} </h4>
</c:forEach>
</body>
</html>