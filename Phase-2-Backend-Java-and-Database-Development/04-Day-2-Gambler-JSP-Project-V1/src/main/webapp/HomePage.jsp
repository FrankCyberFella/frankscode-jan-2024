<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Gambler App Home Page</title>
<link rel="stylesheet" href="./resources/style.css"></link>
</head>
<body>
<h1> Welcome to the Gambler Support App!</h1>

<div>
	<a class="options" href="./gambler">Display all Gamblers</a> 
</div>

<div class="options">
	<form action="./gambler" method="get">
		Display Gambler with id: <input type="text" size=5 name="id">
	</form>
</div>
<div class="options">
	<form action="./gambler" method="get">
		Display Gambler with name: <input type="text" size=25 name="gamblerName">
	</form>
</div>
<div class="options">
	<form action="./gambler?action=delete" method="get">
		Delete Gambler with id: <input type="text" size=5 name="id2Delete">
	</form>
</div>
<div class="options">
	<form action="./gambler?action=update" method="post">
		Update Gambler with id: <input type="text" size=5 name="id2Update">
	</form>
</div>
</body>
</html>