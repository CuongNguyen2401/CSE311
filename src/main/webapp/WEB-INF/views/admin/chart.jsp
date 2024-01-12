<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>
<body>

	<main>
	<div class="table-data">
		<div class="order">
			<div class="head">
				<h3>Chart</h3>

			</div>


			<div class="content">

				<div id="chartContainer" style="height: 370px; width: 100%;"></div>
			</div>



		</div>
	</div>
	</main>

	
<script type="text/javascript">
	
	window.onload = function() {
	
		var dps = [ [] ];
		var chart = new CanvasJS.Chart("chartContainer", {
			exportEnabled : true,
			animationEnabled : true,
			theme : "light2", // "light1", "dark1", "dark2"
			title : {
				text : "Visitor Demographics of a Website"
			},
			subtitles : [ {
				text : "Age Groups of Visitors"
			} ],
			data : [ {
				type : "pie",
				yValueFormatString : "#,##0\"%\"",
				indexLabel : "{label} - {y}",
				dataPoints : dps[0]
			} ]
		});

		var yValue;
		var label;

		<c:forEach items="${dataPointsList}" var="dataPoints" varStatus="loop">
		<c:forEach items="${dataPoints}" var="dataPoint">
		yValue = parseFloat("${dataPoint.y}");
		label = "${dataPoint.label}";
		dps[parseInt("${loop.index}")].push({
			label : label,
			y : yValue,
		});
		</c:forEach>
		</c:forEach>

		chart.render();

	}
</script>

</body>
</html>