<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="icon" href="/css/favicon.ico">

<title>login.jsp</title>

<!-- Bootstrap core CSS -->
<link href="/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="/css/signin.css" rel="stylesheet">

<script src="/js/ie-emulation-modes-warning.js"></script>

<script src="/js/jquery-3.3.1.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/js-cookie@2/src/js.cookie.min.js"></script>

<script type="text/javascript">
	function getCookie(cookieName) {
		var cookieStr = document.cookie
		var cookieList = cookieStr.split("; ");
		var resultStr = null;

		for (var i = 0; i < cookieList.length; i++) {
			if (cookieList[i].startsWith(cookieName + "=")) {
				resultStr = cookieList[i].substring((cookieName + "=").length);
				break;
			}
		}

		return resultStr;
	}

	$('document').ready(function() {
		console.log("test");
		var userId = getCookie("userId");
		if (getCookie("remember_id") == "Y") {
			$("#userId").val(userId);
			$("#remember_id").attr("checked", true); //						  checked/unchecked
			$("#remember_id").prop("checked", true); //attr이랑 조금 다름 구글링 해봐 , true/false
		} else {
			$("#remember_id").attr("checked", false);
		}
	});
</script>

</head>

<body>

	<div class="container">

		<form class="form-signin" method="post" action="/user/loginCheck">
			<h2 class="form-signin-heading">박   진</h2>
			<label for="inputEmail" class="sr-only">아이디</label> <input
				type="text" id="userId" class="form-control" placeholder="아이디"
				required autofocus name="userId"> <label
				for="inputPassword" class="sr-only">비밀번호</label> <input
				type="password" id="inputPassword" class="form-control"
				placeholder="비밀번호" required name="pass">
			<div class="checkbox">
				<label> <input type="checkbox" id="remember_id"
					name="remember_id" value="Y"> 아이디 기억하기
				</label>
			</div>
			<button class="btn btn-lg btn-primary btn-block" type="submit">Sign
				in</button>
		</form>

	</div>
	<!-- /container -->
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>