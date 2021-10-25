<%@ page language="java"%>
<%@ page import="lf05.DBModel"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src="./script/functions/animationFunctions.jq"></script>
<link href="./animation/animista.css" rel="stylesheet"></link>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">

<style>
/* Set height of the grid so .sidenav can be 100% (adjust if needed) */
.row.content {
	height: 1000px
}

/* Set gray background color and 100% height */
.sidenav {
	background-color: #f1f1f1;
	height: 100%;
}

/* Set black background color, white text and some padding */
footer {
	background-color: #555;
	color: white;
	padding: 15px;
}

/* On small screens, set height to 'auto' for sidenav and grid */
@media screen and (max-width: 767px) {
	.sidenav {
		height: auto;
		padding: 15px;
	}
	.row.content {
		height: auto;
	}
}
</style>



</head>
<body>
	<!--Navigationsleiste-->
	<nav class="navbar navbar sticky-top navbar-expand-lg navbar-light"
		style="background-color: lightgreen;">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#" style="font-style: italic;"> <%
 out.println("reeeeeee");
 %>
				</a>
			</div>
			<div class="collapse navbar-collapse" id="NavbarHome">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link active" href="#">Startseite</a>
					</li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" id="Dropdown1"
						data-bs-toggle="dropdown" href="#" aria-expanded="false">
							Funktionen </a>
						<ul class="dropdown-menu" aria-labelledby="Dropdown1">
							<li><a class="dropdown-item" href="#">Datenbankanfragen</a></li>
							<li><a class="dropdown-item" href="#">Kundenverwaltung</a></li>
						</ul></li>
				</ul>
			</div>
		</div>
	</nav>
	<!--Start Background Image-->
	<div class="bg-image"
		style="background-image: url(./img/d36d462db827833805497d9ea78a1343.jpg); height: 900px; width: 100%; background-size: cover;">
		<div class="container-fluid items-center text-center mask"
			style="height: 100%; width: max-content; display: flex; align-items: center;">
			<div class="row">
				<div id="backgroundStart" class="col">
					<h1>Order your food online!</h1>
					<h2>Try it now!</h2>
					<button id="button1" type="button" class="btn btn-light">Login
						here!</button>
				</div>
			</div>
		</div>
	</div>
	<div class="container-fluid">
		<div class="row content">
			<div id="menu" class="col-sm-3 sidenav">
				<h4>Datenbankabfragen</h4>
				<ul class="nav nav-pills nav-stacked">
					<li class="active"><a href="#">Start</a></li>
					<li class="row"><a href="#section2">Beispiel A</a></li>
					<li class="row"><a href="#section3">Beispiel B</a></li>
					<li class="row"><a href="#section3">Beispiel C</a></li>
					<li class="row"><a href="#section3">Beispiel D</a></li>
					<li class="row"><a href="#section3">Beispiel E</a></li>
				</ul>
			</div>
			<div class="col-sm-9">
				<div id=main>
					<%
					ArrayList<String> tables = new ArrayList<>();
					ArrayList<String> columns = new ArrayList<>();
					ArrayList<String> filters = new ArrayList<>();
					Map<Integer, Map<String, String>> output = new HashMap<>();
					tables.add("kunde");
					//columns.add(request.getParameter("column"));
					//filters.add(request.getParameter("filter"));

					try {
						Connection con = DBModel.initDB();
						output = DBModel.getTable(con, tables, columns, filters);
					} catch (Exception e) {
						out.println(e.getMessage());
					}
					for (int i = 0; i < output.size(); i++) {
						for (int j = 0; j < output.size(); j++) {
							out.println("i cry");
						}
					}
					/*output.forEach((k, v) -> {
						v.forEach((x, y) -> out.println(x + ": " + y + "\n"));
					});*/
					%>
				</div>
			</div>
		</div>
	</div>











	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
		integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.min.js"
		integrity="sha384-PsUw7Xwds7x08Ew3exXhqzbhuEYmA2xnwc8BuD6SEr+UmEHlX8/MCltYEodzWA4u"
		crossorigin="anonymous"></script>
</body>




</html>