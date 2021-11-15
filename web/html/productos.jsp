
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

	<!--Importar CSS -->
         <link href="<%=basePath%>css/menu.css" rel="stylesheet" type="text/css"/>
        <link href="<%=basePath%>css/productos.css" rel="stylesheet" type="text/css"/>
        <link href="<%=basePath%>css/footer.css" rel="stylesheet" type="text/css"/>
</head>
<body>


	<!-- Inicio menú-->
	<nav class="navbar navbar-expand-lg sticky-top navbar-dark bg-primary">
		<div class="container-fluid">

			<a class="navbar-brand" href="#">
				<img src="../img/LogoLJAG.png" alt="" width="140px" height="120px"/>
			</a>

		  <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		  </button>
		  
		  <div class="collapse navbar-collapse" id="navbarSupportedContent">

			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
			  <li class="nav-item">
				<a class="nav-link" href="../index.jsp">INICIO</a>
			  </li>
			  <li class="nav-item">
				<a class="nav-link" href="./nosotros.jsp">NOSOTROS</a>
			  </li>
			  <li class="nav-item">
				<a class="nav-link" href="./servicios.jsp">SERVICIOS</a>
			  </li>
			  <li class="nav-item">
				<a class="nav-link active" aria-current="page"href="#">PRODUCTOS</a>
			  </li>
			</ul>

			<ul class="navbar-nav ml-auto m-4">
				<li class="nav-item">
					<a class="nav-link" href="./iniciarsesion.jsp">INICIAR SESIÓN</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="./registrarse.jsp">REGISTRARSE</a>
				</li>
			</ul>

		  </div>
		</div>
	</nav>
	<!-- Fin menú -->
	

<section class="contenido">
    <div class="container-all">
		<input type="radio" id="1" name="image-slide" hidden/>
		<input type="radio" id="2" name="image-slide" hidden/>
		<input type="radio" id="3" name="image-slide" hidden/>

		<div class="slide">
			<div class="item-slide">
				<img id="background" src="../img/header.jpeg" style="filter:brightness(45%)">
				<div class="carousel-caption d-flex flex-column justify-content-center h-100 txt-carousel">
					<h1  class="tittle-carrusel ">NUESTROS <p style="color: blue; display: inline;">PRODUCTOS</p></h1>
				  </div>
			
			</div>

			<div class="item-slide">
				<img id="background" src="../img/header_2.jpeg" style="filter:brightness(45%)">
				<div class="carousel-caption d-flex flex-column justify-content-center h-100 txt-carousel">
					<h1  class="tittle-carrusel ">NUESTROS <p style="color: blue; display: inline;">PRODUCTOS</p></h1>
				  </div>
			</div>

			<div class="item-slide">
				<img id="background" src="../img/home.jpeg" style="filter:brightness(45%)">
				<div class="carousel-caption d-flex flex-column justify-content-center h-100 txt-carousel">
					<h1  class="tittle-carrusel ">NUESTROS <p style="color: blue; display: inline;">PRODUCTOS</p></h1>
				  </div>
			</div>

		</div>

		<div class="pagination">

			<label class="pagination-item" for="1">
				<img id="background-p" src="../img/header.jpeg">
				
			</label>

			<label class="pagination-item" for="2">
				<img id="background-p" src="../img/header_2.jpeg">
			</label>

			<label class="pagination-item" for="3">
				<img id="background-p" src="../img/home.jpeg">
			</label>

		</div>

	</div>

</section>


<br>
      <section class="contenido">

		<div class="container">

			<div class="row">
				<div class="col-md-1"></div>
				<div class="col-md-10">
					<h1 >OFRECEMOS TODOS LOS <p style="color: blue; display: inline;">PRODUCTOS</p> QUE NECESITAS PARA QUE TU VEHÍCULO ESTE AL DÍA EN REPUESTOS</h1>
				</div>
				<div class="col-md-1"></div>

			</div>


		</div>
     </section>



	 <div class="container-zonaproduct">

        <input type="radio" id="TODOS" name="categories" value="TODOS" checked>
        <input type="radio" id="ACEITES" name="categories" value="ACEITES">
        <input type="radio" id="FILTROS" name="categories" value="FILTROS">
        <input type="radio" id="VALVULINAS" name="categories" value="VALVULINAS">
        <input type="radio" id="ADITIVOS" name="categories" value="ADITIVOS">
        <input type="radio" id="OTROS" name="categories" value="OTROS">
     

        <div class="container-category">
            <label for="TODOS">TODOS</label>
            <label for="ACEITES">ACEITES</label>
            <label for="FILTROS">FILTROS</label>
            <label for="VALVULINAS">VALVULINAS</label>
            <label for="ADITIVOS">ADITIVOS</label>
            <label for="OTROS">OTROS</label>
        </div>


		  <div class="productos">

			<div class="product" data-category="ACEITES">
				<h2>Categoría Aceites para Vehiculos(Carros y motos):</h2>
				<div class="container ctn">
					<div class="card">
						<img src="https://http2.mlstatic.com/D_772419-MLV46335874323_062021-O.jpg" alt="">
						<h4 class="titulo-card">Shell Helix HX5</h4>
						<p  id="desc">Aceite Premium para motor carros</p>
						<p><strong id="ref-prec">Referencia:</strong> 20W-50</p>				
						<p><strong id="ref-prec">Precio:</strong>  $5.000</p>

						
					</div>

					<div class="card">
						<img src="https://coexito.com.co/wp-content/uploads/2019/02/Coexito-silver-ATF-DEXRON-III.jpg" alt="">
						<h4 class="titulo-card">Coéxito Silver</h4>
						<p  id="desc">Aceite Mineral para Transmisiones Automáticas y Direcciones Hidráulicas carros</p>
						<p><strong id="ref-prec">Referencia:</strong> ATF DEXTRON III 1/4 galon</p>
						<p><strong id="ref-prec">Precio:</strong>  $8.000</p>
						
					</div>
					<div class="card">
						<img src="data:image/jpeg;base64,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" alt="">
						<h4 class="titulo-card">Akron Spitzen</h4>
						<p><strong id="ref-prec">Referencia:</strong> FULL SYNTHETIC SN 5W-40</p>
						<p><strong id="ref-prec">Precio:</strong>  $7.000</p>
						
					</div>

					<div class="card">
						<img src="https://www.corbeta.com.co/media/catalog/product/0/7/079191239353_1.jpg" alt="">
						<h4 class="titulo-card">Nombre producto</h4>
						<p><strong id="ref-prec">Referencia:</strong> xxxxxxxx</p>
						<p><strong id="ref-prec">Precio:</strong>  $$$$$$</p>
						
					</div>
		
					
		
		
				</div>
			</div>
         
		

			<div class="product" data-category="FILTROS">
				<h2>Categoría Filtros(De Aceite, Gasolina, ACPM y Aire acondicionado): </h2>

	 	<div class="container ctn" >
			<div class="card">
				<img src="http://partmo.com/catalog/monitor/images/A14616.jpg" alt="">
						<h4 class="titulo-card">Nombre producto</h4>
						<p><strong id="ref-prec">Referencia:</strong> xxxxxxxx</p>
						<p><strong id="ref-prec">Precio:</strong>  $$$$$$</p>
			</div>
            <div class="card">
				<img src="https://http2.mlstatic.com/D_NQ_NP_899886-MCO31562503410_072019-V.jpg" alt="">
				<h4 class="titulo-card">Nombre producto</h4>
						<p><strong id="ref-prec">Referencia:</strong> xxxxxxxx</p>
						<p><strong id="ref-prec">Precio:</strong>  $$$$$$</p>
			</div>

			



		</div>
	</div>



	     <div class="product" data-category="VALVULINAS">
		<h2>Categoría Valvulinas: </h2>
		<div class="container ctn">
			<div class="card">
				<img src="https://www.beglubricantes.com/wp-content/uploads/2018/04/07-LT-1.jpg" alt="">
						<h4 class="titulo-card">Nombre producto</h4>
						<p><strong id="ref-prec">Referencia:</strong> xxxxxxxx</p>
						<p><strong id="ref-prec">Precio:</strong>  $$$$$$</p>
			</div>
            <div class="card">
				<img src="https://www.lubrialf.com/wp-content/uploads/2021/06/valvunina-chupo-806x1024.png" alt="">
				<h4 class="titulo-card">Nombre producto</h4>
						<p><strong id="ref-prec">Referencia:</strong> xxxxxxxx</p>
						<p><strong id="ref-prec">Precio:</strong>  $$$$$$</p>
			</div>

			<div class="card">
				<img src="https://iprpartesyrepuestos.com/wp-content/uploads/2018/09/2129.jpg" alt="">
				<h4 class="titulo-card">Nombre producto</h4>
						<p><strong id="ref-prec">Referencia:</strong> xxxxxxxx</p>
						<p><strong id="ref-prec">Precio:</strong>  $$$$$$</p>
			</div>


		</div>
	    </div>


		<div class="product" data-category="ADITIVOS">
			<h2>Categoría Aditivos: </h2>
			<div class="container ctn">
				<div class="card">
					<img src="https://www.ecosbox.com/wp-content/uploads/2018/01/P18726632.jpg" alt="">
							<h4 class="titulo-card">Nombre producto</h4>
							<p><strong id="ref-prec">Referencia:</strong> xxxxxxxx</p>
							<p><strong id="ref-prec">Precio:</strong>  $$$$$$</p>
				</div>

			</div>

		</div>

	
		<div class="product" data-category="OTROS">
			<h2>Categoría Otros: </h2>
			<div class="container ctn">
				<div class="card">
					<img src="https://www.motor.com.co/files/article_content/uploads/2019/05/31/5cf16ea509868.jpeg" alt="">
							<h4 class="titulo-card">Nombre producto</h4>
							<p><strong id="ref-prec">Referencia:</strong> xxxxxxxx</p>
							<p><strong id="ref-prec">Precio:</strong>  $$$$$$</p>
				</div>

			</div>

		</div>



	</div>





	</div>


  <!--FOOTER-->
 <footer>
	<div class="container-fluid">
	  <div class="row ">
		<div class="col-12 redes" style="background-color: #00114e;">
		  <img src="../img/whatsapp.png" >
		  <img src="../img/facebook.png" >
		  <img src="../img/instagram.png" >
		</div>
	  </div>
	  <div class="row" style="background-color: #001971;">
  
		<div class="col-12 col-sm-4 col-md-4 col-lg-4">
		  <img src="../img/LogoLJAG.png" alt="Logo Jezreel" id="imgFooter">
		</div>
  
		<div class="col-12  col-sm-4 col-md-4 col-lg-4 horario" >
		  <h4 >HORARIOS DE ATENCIÓN</h4>
		  <p>Lunes a Viernes</p>
		  <p>7:30 AM a 6:00 PM</p>
		  <p>Sábado</p>
		  <p>7:30 AM a 5:00 PM</p>
		</div>
  
		<div class="col-12  col-sm-4 col-md-4 col-lg-4 footer-contacto" >
		  <h4 > CONTACTO </h4>
		  <P>Av 5 # 0N-54 Barrio La Merced</P>
		  <p>San José de Cúcuta - Colombia</p>
		  <p>albeirofonseca74@gmail.com</p>
		  <p>+57 3112810082</p>
		</div>
  
	  </div>
	</div>
  </footer>
  <!--FIN FOOTER-->
 
	    
    
</body>
</html>