

<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <%
            String path = request.getContextPath();
            String basePath = request.getScheme() + "://" + request.getServerName() + ":"
                    + request.getServerPort() + path + "/";

            if (request.getSession().getAttribute("listaServiciosIndex") == null) {
                request.getRequestDispatcher(("MostrarServiciosIndex.do")).forward(request, response);

            }
        %>
        <base href="<%=basePath%>">
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Lubrillantas Jezreel AG</title>


        <!-- Fuente de google: Open Sans - Regular 400 -->
        <link href="https://fonts.googleapis.com/css2?family=Open+Sans&display=swap" rel="stylesheet">

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">


        <!--Importar CSS y script del menÃº -->
        <link rel="stylesheet" href="./css/menu.css"/>
        <link rel="stylesheet" href="./css/index.css"/>
        <link rel="stylesheet" href="./css/footer.css">



        <!--Link del boostrap-->
        <link rel="stylesheet" href="./css/lightbox.css">


    </head>
    <body onload="sesion('<%=request.getSession().getAttribute("usuario")%>')">
        <!--MenÃº -->
        <nav class="navbar navbar-expand-lg sticky-top navbar-dark">
            <div class="container-fluid">

                <a class="navbar-brand" href="index.jsp">
                    <img src="./img/LogoLJAG.png" alt="" width="140px" height="120px" />
                </a>

                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarSupportedContent">

                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="index.jsp">INICIO</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="<%=basePath%>jsp/nosotros.jsp">NOSOTROS</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="<%=basePath%>MostrarServicios.do">SERVICIOS</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="<%=basePath%>MostrarProductos.do">PRODUCTOS</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="<%=basePath%>MostrarServiciosCotizacion.do">COTIZA YA!</a>
                        </li>
                    </ul>

                    <template id="NoSesion">
                        <ul class="navbar-nav ml-auto m-4">
                            <li class="nav-item">
                                <a class="nav-link" href="<%=basePath%>jsp/iniciarsesion.jsp">INICIAR SESIÓN</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="<%=basePath%>jsp/registrarse.jsp">REGISTRARSE</a>
                            </li>
                        </ul>
                    </template>

                    <template id="SiSesion">
                        <ul class="navbar-nav ml-auto m-4">
                            <li class="nav-item dropdown" style="list-style-type: none;">
                                <a  class="nav-link dropdown-toggle link-dark" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false" >
                                    <%=request.getSession().getAttribute("nameUser")%>
                                </a>
                                <ul class="dropdown-menu text-small "aria-labelledby="dropdownUser2"  >
                                    <li><a class="dropdown-item" href="<%=basePath%>./jsp/datosCliente.jsp" >Mi Cuenta</a></li>
                                    <li><a class="dropdown-item" href="<%=basePath%>MisVehiculos.do" >Mis Vehiculos</a></li>
                                    <li><a class="dropdown-item" href="<%=basePath%>MisServiciosUsu.do" >Mis Servicios</a></li>
                                    <li><a class="dropdown-item" href="<%=basePath%>MostrarCitasUsu.do" >Mis Citas</a></li>
                                    <li><hr class="dropdown-divider"></li>
                                    <li><a class="dropdown-item" href="<%=basePath%>/cerrarSesion.do">Salir</a></li>
                                </ul>
                            </li>

                            <div class="user">
                                <img src="<%=request.getSession().getAttribute("urlFoto")%>" width="70" height="70" class="rounded-circle me-2">
                            </div>

                        </ul>
                    </template>



                </div>
            </div>
        </nav>
        <!--Fin MenÃº -->


        <!--Carrusel-->
        <section class="contenido">
            <div class="container-all">
                <input type="radio" id="1" name="image-slide" hidden/>
                <input type="radio" id="2" name="image-slide" hidden/>
                <input type="radio" id="3" name="image-slide" hidden/>

                <div class="slide">
                    <div class="item-slide">
                        <img id="background" src="./img/header.jpeg" style="filter:brightness(45%)">
                        <div class="carousel-caption d-flex flex-column justify-content-center h-100 txt-carousel">
                            <div class="carousel-caption d-flex flex-column justify-content-center h-100 txt-carousel ">
                                <h1 class="tittle-carrusel" >Lubrillantas <p style="color: blue; display: inline;">Jezreel</p> AG</h1>
                                <input class="boton" id="botonAgendar" type="button" value="Agendar Servicio" onclick="location.href='ObtenerHorarios.do'">         
                            </div>
                        </div>

                    </div>

                    <div class="item-slide">
                        <img id="background" src="./img/header_2.jpeg" style="filter:brightness(45%)">
                        <div class="carousel-caption d-flex flex-column justify-content-center h-100 txt-carousel">
                            <div class="carousel-caption d-flex flex-column justify-content-center h-100 txt-carousel ">
                                <h1 class="tittle-carrusel" >Lubrillantas <p style="color: blue; display: inline;">Jezreel</p> AG</h1>
                                <input class="boton" id="botonAgendar" type="button" value="Agendar Servicio" onclick="location.href='ObtenerHorarios.do'"> 
                            </div> 
                        </div>
                    </div>

                    <div class="item-slide">
                        <img id="background" src="./img/home.jpeg" style="filter:brightness(45%)">
                        <div class="carousel-caption d-flex flex-column justify-content-center h-100 txt-carousel">
                            <div class="carousel-caption d-flex flex-column justify-content-center h-100 txt-carousel ">
                                <h1 class="tittle-carrusel" >Lubrillantas <p style="color: blue; display: inline;">Jezreel</p> AG</h1>
                                <input class="boton" id="botonAgendar" type="button" value="Agendar Servicio" onclick="location.href='ObtenerHorarios.do'"> 
                            </div> 
                        </div>
                    </div>

                </div>

                <div class="pagination">

                    <label class="pagination-item" for="1">
                        <img id="background-p" src="./img/header.jpeg">

                    </label>

                    <label class="pagination-item" for="2">
                        <img id="background-p" src="./img/header_2.jpeg">
                    </label>

                    <label class="pagination-item" for="3">
                        <img id="background-p" src="./img/home.jpeg">
                    </label>

                </div>

            </div>

        </section>
        <!--Fin de carrusel-->

        <!--Sobre nosotros-->
        <section class="mt-4 mb-4">
            <div id="content">
                <div id="left">
                    <img src="./img/home.jpeg" height="400px" alt=""> 
                </div>

                <div id="right">
                    <h1 style="color: #858585; font-weight: boild;">SOBRE NOSOTROS</h1>
                    <h1>Quienes <p style="color: blue; display: inline;">somos</p></h1>
                    <hr size="20"  style="background-color:#2F80ED;">
                    <p class="texto">LUBRILLANTAS JEZREEL AG es una micro-empresa que se desempeña en el sector comercial relacionado con los vehiculos, en el que ofrece todo tipo de servicios de mantenimiento de cambio de aceite, servicio de frenos de suspension, montallantas y venta de productos. Llevamos
                        más de 20 años en el mercado, ofreciendo servicios y productos de alta calidad, en pro y beneficio de nuestros clientes.
                    </p>
                    <div class="row">



                        <div class="col-md-4"></div>
                        <div class="col-md-6"> 	
                            <input class="boton3" id="botonLeerMas" type="button" value="Leer mas" onclick="location.href = '<%=basePath%>jsp/nosotros.jsp'">
                        </div>
                        <div class="col-md-2"></div>
                    </div>
                </div>
            </div>
        </section>
        <!--Fin sobre nosotros-->


        <section class="contenido mt-5 mb-5">

            <div class="container">

                <div class="row">
                    <div class="col-md-1"></div>
                    <div class="col-md-10">
                        <h1 >SÁCALE EL MEJOR RENDIMIENTO A TU VEHÍCULO CON NUESTROS <p style="color: blue; display: inline;">SERVICIOS</p></h1>
                    </div>
                    <div class="col-md-1"></div>
                </div>
            </div>
        </section>

        <%
            String listServicios = request.getSession().getAttribute("listaServiciosIndex").toString();
        %>


        <div class="container">
            <div class="row galeria">
                <%=listServicios%>

            </div>


        </div>


        <section class="contenido mt-3 mb-5">
            <div class="col-md-2"></div>
            <div class="col-md-8"> 		
                <input class="boton2" id="botonServicios" type="button" value="Ver todos" onclick="location.href = '<%=basePath%>MostrarServicios.do'">
            </div>
            <div class="col-md-2"></div>
        </section>



        <!--FOOTER-->
        <footer>
            <div class="container-fluid">
                <div class="row ">
                    <div class="col-12 redes" style="background-color: #00114e;">
                        <a href="https://api.whatsapp.com/send/?phone=573112810082&text&app_absent=0"><img src="<%=basePath%>/img/whatsapp.png" ></a>
                        <a href="https://web.facebook.com/profile.php?id=100075532121136"><img src="<%=basePath%>/img/facebook.png" ></a>
                        <a href="https://www.instagram.com/lubrillantasjag/"><img src="<%=basePath%>/img/instagram.png" ></a>
                    </div>
                </div>
                <div class="row" style="background-color: #001971;">

                    <div class="col-12 col-sm-4 col-md-4 col-lg-4">
                        <img src="<%=basePath%>/img/LogoLJAG.png" alt="Logo Jezreel" id="imgFooter">
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


        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>


        <script src="./js/ligthboxjs/lightbox-plus-jquery.min.js"></script>
        <script src="./js/sesion.js"></script>
        <!--  <script>
           lightbox.option({
             'maxWidth' : 800,
             'width' : 800,
             'maxHeight' : 300,
           })
       </script> -->


    </body>
</html>