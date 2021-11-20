<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <%
            String path = request.getContextPath();
            String basePath = request.getScheme() + "://" + request.getServerName() + ":"
                    + request.getServerPort() + path + "/";
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
                    <img src="<%=basePath%>img/LogoLJAG.png" alt="" width="140px" height="120px"/>
                </a>

                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarSupportedContent">

                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link" href="<%=basePath%>/index.jsp">INICIO</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="<%=basePath%>/jsp/nosotros.jsp">NOSOTROS</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="<%=basePath%>/MostrarServicios.do">SERVICIOS</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page"href="#">PRODUCTOS</a>
                        </li>
                    </ul>

                    <ul class="navbar-nav ml-auto m-4">
                        <li class="nav-item">
                            <a class="nav-link" href="<%=basePath%>jsp/iniciarsesion.jsp">INICIAR SESIÓN</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="<%=basePath%>jsp/registrarse.jsp">REGISTRARSE</a>
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
                        <img id="background" src="<%=basePath%>img/header.jpeg" style="filter:brightness(45%)">
                        <div class="carousel-caption d-flex flex-column justify-content-center h-100 txt-carousel">
                            <h1  class="tittle-carrusel ">NUESTROS <p style="color: blue; display: inline;">PRODUCTOS</p></h1>
                        </div>

                    </div>

                    <div class="item-slide">
                        <img id="background" src="<%=basePath%>img/header_2.jpeg" style="filter:brightness(45%)">
                        <div class="carousel-caption d-flex flex-column justify-content-center h-100 txt-carousel">
                            <h1  class="tittle-carrusel ">NUESTROS <p style="color: blue; display: inline;">PRODUCTOS</p></h1>
                        </div>
                    </div>

                    <div class="item-slide">
                        <img id="background" src="<%=basePath%>img/home.jpeg" style="filter:brightness(45%)">
                        <div class="carousel-caption d-flex flex-column justify-content-center h-100 txt-carousel">
                            <h1  class="tittle-carrusel ">NUESTROS <p style="color: blue; display: inline;">PRODUCTOS</p></h1>
                        </div>
                    </div>

                </div>

                <div class="pagination">

                    <label class="pagination-item" for="1">
                        <img id="background-p" src="<%=basePath%>img/header.jpeg">

                    </label>

                    <label class="pagination-item" for="2">
                        <img id="background-p" src="<%=basePath%>img/header_2.jpeg">
                    </label>

                    <label class="pagination-item" for="3">
                        <img id="background-p" src="<%=basePath%>img/home.jpeg">
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


            <%
                String[] productos = (String[]) (request.getSession().getAttribute("productos"));
            %>
            <div class="productos">

                <div class="product" data-category="ACEITES">
                    <h2>Categoría Aceites para Vehiculos(Carros y motos):</h2>
                    <div class="container ctn">

                        <%= productos[0]%>					

                    </div>
                </div>



                <div class="product" data-category="FILTROS">
                    <h2>Categoría Filtros(De Aceite, Gasolina, ACPM y Aire acondicionado): </h2>

                    <div class="container ctn" >
                        <%= productos[1]%>
                    </div>
                </div>



                <div class="product" data-category="VALVULINAS">
                    <h2>Categoría Valvulinas: </h2>
                    <div class="container ctn">
                        <%= productos[2]%>
                    </div>
                </div>


                <div class="product" data-category="ADITIVOS">
                    <h2>Categoría Aditivos: </h2>
                    <div class="container ctn">
                        <%= productos[3]%>

                    </div>

                </div>


                <div class="product" data-category="OTROS">
                    <h2>Categoría Otros: </h2>
                    <div class="container ctn">
                        <%= productos[4]%>

                    </div>

                </div>



            </div>





        </div>


        <!--FOOTER-->
        <footer>
            <div class="container-fluid">
                <div class="row ">
                    <div class="col-12 redes" style="background-color: #00114e;">
                        <img src="<%=basePath%>img/whatsapp.png" >
                        <img src="<%=basePath%>img/facebook.png" >
                        <img src="<%=basePath%>img/instagram.png" >
                    </div>
                </div>
                <div class="row" style="background-color: #001971;">

                    <div class="col-12 col-sm-4 col-md-4 col-lg-4">
                        <img src="<%=basePath%>img/LogoLJAG.png" alt="Logo Jezreel" id="imgFooter">
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
