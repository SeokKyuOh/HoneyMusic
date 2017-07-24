<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>차트파인더</title>
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function() {
	Highcharts.chart('container', {
	    chart: {
	        type: 'column'
	    },
	    title: {
	        text: '음악 사이트별 차트 순위'
	    },
	    subtitle: {
	        text: ''
	    },
	    xAxis: {
	        categories: [
	            'melon',
	            'genie',
	            'mnet',
	            'honey'
	        ],
	        crosshair: true
	    },
	    yAxis: {
	        min: 0,
	        title: {
	            text: 'Rating '
	        }
	    },
	    tooltip: {
	        headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
	        pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
	            '<td style="padding:0"><b>{point.y:.1f} mm</b></td></tr>',
	        footerFormat: '</table>',
	        shared: true,
	        useHTML: true
	    },
	    plotOptions: {
	        column: {
	            pointPadding: 0.2,
	            borderWidth: 0
	        }
	    },
	    series: <%=request.getAttribute("json")%>
	});
});

</script>
</head>
<body>
	<section id="contentSection">
		<div class="center_content" style="width:80%">
			<table class="table table-hover">
				<thead>
					<tr>
						<th colspan="5">차트 파인더</th>
					</tr>
				</thead>
			</table>
			<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
		</div>
	</section>	
</body>
</html>