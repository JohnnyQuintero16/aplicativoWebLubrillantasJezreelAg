<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" +     
        request.getServerPort() + path + "/";
%>
<base href="<%=basePath%>">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lubrillantas Jezreel AG</title>

    <!-- Fuente de google: Open Sans - Regular 400 -->
    <link href="https://fonts.googleapis.com/css?family=Poppins:400,500,600,700&display=swap" rel="stylesheet">

	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    
	<!--Normallize css: proyecto que corrige estilos predeterminados de los diferentes navegadores, para evitar usar el selector universal
    en la hoja de estilos CSS. -->
    <link rel="stylesheet" href="https://necolas.github.io/normalize.css/8.0.1/normalize.css">
<<<<<<< HEAD:web/html/iniciarsesion.jsp
=======
    
    <!--Importar CSS -->
    <link rel="stylesheet" href="<%=basePath%>css/menu.css"/>
    <link rel="stylesheet" href="<%=basePath%>css/iniciarsesion.css">
    <link rel="stylesheet" href="<%=basePath%>css/footer.css">


    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"/>
>>>>>>> origin/Test:web/html/iniciarsesion.html

	<!--Importar CSS -->
         <link href="<%=basePath%>css/menu.css" rel="stylesheet" type="text/css"/>
        <link href="<%=basePath%>css/iniciarsesion.css" rel="stylesheet" type="text/css"/>
        <link href="<%=basePath%>css/footer.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"/>
</head>
<body>

 	<!-- Inicio menÃº-->
     <nav class="navbar navbar-expand-lg sticky-top navbar-dark bg-primary">
		<div class="container-fluid">

<<<<<<< HEAD:web/html/iniciarsesion.jsp
			<a class="navbar-brand" href="<%=basePath%>/index.jsp">
				<img src="<%=basePath%>/img/LogoLJAG.png" alt="" width="140px" height="120px"/>
=======
			<a class="navbar-brand" href="<%=basePath%>index.html">
				<img src="<%=basePath%>img/LogoLJAG.png" alt="" width="140px" height="120px"/>
>>>>>>> origin/Test:web/html/iniciarsesion.html
			</a>

		  <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		  </button>
		  
		  <div class="collapse navbar-collapse" id="navbarSupportedContent">

			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
			  <li class="nav-item">
<<<<<<< HEAD:web/html/iniciarsesion.jsp
				<a class="nav-link" href="<%=basePath%>/index.jsp">INICIO</a>
=======
				<a class="nav-link" href="<%=basePath%>index.html">INICIO</a>
>>>>>>> origin/Test:web/html/iniciarsesion.html
			  </li>
			  <li class="nav-item">
				<a class="nav-link" href="<%=basePath%>/nosotros.jsp">NOSOTROS</a>
			  </li>
			  <li class="nav-item">
<<<<<<< HEAD:web/html/iniciarsesion.jsp
				<a class="nav-link" href="<%=basePath%>/MostrarServiciosAdmin.do">SERVICIOS</a>
			  </li>
			  <li class="nav-item">
				<a class="nav-link" href="<%=basePath%>/MostrarProductos.do">PRODUCTOS</a>
=======
				<a class="nav-link" href="<%=basePath%>html/servicios.html">SERVICIOS</a>
			  </li>
			  <li class="nav-item">
				<a class="nav-link" href="<%=basePath%>html/productos.html">PRODUCTOS</a>
>>>>>>> origin/Test:web/html/iniciarsesion.html
			  </li>
			</ul>

			<ul class="navbar-nav ml-auto m-4">
				<li class="nav-item">
<<<<<<< HEAD:web/html/iniciarsesion.jsp
					<a class="nav-link active" aria-current="page" href="<%=basePath%>/iniciarsesion.jsp">INICIAR SESIÃ“N</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="<%=basePath%>/html/registrarse.jsp">REGISTRARSE</a>
=======
					<a class="nav-link active" aria-current="page" href="#">INICIAR SESION</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="<%=basePath%>html/registrarse.html">REGISTRARSE</a>
>>>>>>> origin/Test:web/html/iniciarsesion.html
				</li>
			</ul>

			<!-- <li class="nav-item dropdown" style="list-style-type: none;">
				<a style="color: white;" class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
				  Dropdown
				</a>
				<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
				  <li><a class="dropdown-item" href="#">Action</a></li>
				  <li><a class="dropdown-item" href="#">Another action</a></li>
				  <li><hr class="dropdown-divider"></li>
				  <li><a class="dropdown-item" href="#">Something else here</a></li>
				</ul>
			</li> -->

		  </div>
		</div>
	</nav>
	<!-- Fin menÃº -->   

    <main>
        <div class="wrapper">
            <div class="encabezado">
<<<<<<< HEAD:web/html/iniciarsesion.jsp
                <h1>Iniciar SesiÃ³n</h1>
            </div>3
            <form action='../IniciarSesion.do' method="GET">
            <div class="field cedula">
              <div class="input-area">
                <input type="text" name="cedula" placeholder="NÃºmero de cÃ©dula">
=======
                <h1>Iniciar Sesion</h1>
            </div>
        <form action="#">
            <div class="field cedula">
              <div class="input-area">
                <input type="text" placeholder="Numero de cedula">
>>>>>>> origin/Test:web/html/iniciarsesion.html
                <i class="icon fas fa-user"></i>
                <i class="error error-icon fas fa-exclamation-circle"></i>
              </div>
              <div class="error error-txt">La cedula no puede estar en blanco</div>
            </div>
            <div class="field password">
              <div class="input-area">
<<<<<<< HEAD:web/html/iniciarsesion.jsp
                <input type="password" name="clave" placeholder="ContraseÃ±a">
=======
                <input type="password" placeholder="Contraseña">
>>>>>>> origin/Test:web/html/iniciarsesion.html
                <i class="icon fas fa-lock"></i>
                <i class="error error-icon fas fa-exclamation-circle"></i>
              </div>
              <div class="error error-txt">La contraseña no puede estar en blanco</div>
            </div>
            <div class="pass-txt"><a href="#">¿Olvidaste tu contraseña?</a></div>
            <input type="submit" value="Ingresar">
          </form>
<<<<<<< HEAD:web/html/iniciarsesion.jsp
          <div class="sign-txt">Â¿No eres miembro? <a href="<%=basePath%>/html/registrarse.jsp">Registrate</a></div>
=======
          <div class="sign-txt">¿No eres miembro? <a href="<%=basePath%>html/registrarse.html">Registrate</a></div>
>>>>>>> origin/Test:web/html/iniciarsesion.html
        </div>

    </main>

  
 <!--FOOTER-->
 <footer>
  <div class="container-fluid">
    <div class="row ">
      <div class="col-12 redes" style="background-color: #00114e;">
<<<<<<< HEAD:web/html/iniciarsesion.jsp
        <img src="<%=basePath%>/img/whatsapp.png" >
        <img src="<%=basePath%>/img/facebook.png" >
        <img src="<%=basePath%>/img/instagram.png" >
=======
        <img src="<%=basePath%>img/whatsapp.png" >
        <img src="<%=basePath%>img/facebook.png" >
        <img src="<%=basePath%>img/instagram.png" >
>>>>>>> origin/Test:web/html/iniciarsesion.html
      </div>
    </div>
    <div class="row" style="background-color: #001971;">

      <div class="col-12 col-sm-4 col-md-4 col-lg-4">
<<<<<<< HEAD:web/html/iniciarsesion.jsp
        <img src="<%=basePath%>/img/LogoLJAG.png" alt="Logo Jezreel" id="imgFooter">
=======
        <img src="<%=basePath%>img/LogoLJAG.png" alt="Logo Jezreel" id="imgFooter">
>>>>>>> origin/Test:web/html/iniciarsesion.html
      </div>

      <div class="col-12  col-sm-4 col-md-4 col-lg-4 horario" >
        <h4 >HORARIOS DE ATENCION</h4>
        <p>Lunes a Viernes</p>
        <p>7:30 AM a 6:00 PM</p>
        <p>Sabado</p>
        <p>7:30 AM a 5:00 PM</p>
      </div>

      <div class="col-12  col-sm-4 col-md-4 col-lg-4 footer-contacto" >
        <h4 > CONTACTO </h4>
        <P>Av 5 # 0N-54 Barrio La Merced</P>
        <p>San Jose de Cucuta - Colombia</p>
        <p>albeirofonseca74@gmail.com</p>
        <p>+57 3112810082</p>
      </div>

    </div>
  </div>
</footer>
<!--FIN FOOTER-->

<<<<<<< HEAD:web/html/iniciarsesion.jsp
    <script src="<%=basePath%>/js/iniciarsesion.js"></script>
=======
    <script src="<%=basePath%>js/iniciarsesion.js"></script>
>>>>>>> origin/Test:web/html/iniciarsesion.html
    
</body>
</html>