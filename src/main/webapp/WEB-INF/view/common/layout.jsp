<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>


<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="/css/favicon.ico">

<title>Spring</title>

<tiles:insertAttribute name="basicLib" />

</head>

<body>

	<tiles:insertAttribute name="header" />

	<div class="container-fluid">
		<div class="row">

			<tiles:insertAttribute name="left" />

			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

				<tiles:insertAttribute name="content"/>
				
			</div>
		</div>
	</div>
</body>
</html>
