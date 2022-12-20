<!DOCTYPE html>
<%@page contentType="text/html; charset=utf-8"%>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="author" content="Kodinger">
	<meta name="viewport" content="width=device-width,initial-scale=1">
	<title>My Login Page</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<link rel="stylesheet" type="text/css" href="login/css/my-login.css">
	
</head>

<body class="my-login-page">
	<section class="h-100">
		<div class="container h-100">
			<div class="row justify-content-md-center h-100">
				<div class="card-wrapper">
					<div class="brand">
						<img src="login/img/logo.jpg" alt="logo">
					</div>
					<div class="card fat">
						<div class="card-body">
						<h1 style="text-align: center;" class="card-title">上机考试系统</h1>
							<h4 class="card-title" style="text-align: center;">登录</h4>
							<form action="login" method="post" class="my-login-validation"  style="text-align: center;">
								<div class="form-group">
									<label >帐号</label>
									<input id="email" type="text" class="form-control" name="uname" value="001" required autofocus>
								</div>

								<div class="form-group">
									<label for="password">密码</label>
									<input id="password" type="password" class="form-control" name="pwd" value="123456" required data-eye>
								</div>

								<div class="form-group" style="margin-top:20px; height:30px">
									<div style="margin-left:30px">
										<div class="radio-custom radio-primary" style="float: left; margin-left:25px">
											<input name="type" value="1" type="radio"> 
											<label style="width: 70px;">老师</label>
										</div>
										<div class="radio-custom radio-primary" style="float: left;">
											<input name="type" value="0" type="radio"> <label>学生</label>
										</div>
										<div class="radio-custom radio-primary" style="float: left; margin-left:7px">
											<input name="type" value="2" type="radio" checked=true> <label>管理员</label>
										</div>

									</div>
								</div>

								<div class="form-group m-0" style="margin-top:20px; margin-bottom:20px">
									<button type="submit" class="btn btn-primary btn-block">
										登录
									</button>
								</div>
								<div id="prompt" class="prompt" style="color:red;text-align: center; ">${error}</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	


	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	<script src="login/js/my-login.js"></script>
</body>
</html>
