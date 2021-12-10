<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <%
            String path = request.getContextPath();
            String basePath = request.getScheme() + "://" + request.getServerName() + ":"
                    + request.getServerPort() + path + "/";

            /*if (request.getSession().getAttribute("listaServiciosIndex") == null) {
                request.getRequestDispatcher(("MostrarServiciosIndex.do")).forward(request, response);
            }*/
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
        <link rel="stylesheet" href="<%=basePath%>css/menu.css"/>
        <link rel="stylesheet" href="<%=basePath%>css/cotizacion.css"/>
        <link rel="stylesheet" href="<%=basePath%>css/footer.css">


    </head>
    <body onload="sesion('<%=request.getSession().getAttribute("usuario")%>')">
        <!-- menu -->
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
        <!--Fin menu -->

        <div class="titulo-vista text-primary">
            SIMULADOR COTIZACIONES
            <hr>
            <h2>SERVICIOS DISPONIBLES</h2>
        </div>
        <main>
            <div class="contenedor">

                <div class="card">

                    <div class="imagen">
                        <img src="https://blog.reparacion-vehiculos.es/hubfs/Im%C3%A1genes_Post/Julio%202018/errores%20cambio-aceite.jpg" alt="">
                        <div class="titulo">
                            <h3 class="text-primary">Cambio lubricantes para engranajes</h3>
                        </div>
                    </div>

                    <div class="descripcion">
                        <div class="texto">
                            <p>El cambio de neumáticos es una de las operaciones de 
                                mantenimiento más frecuentes que se realizan sobre el vehículo. 
                                Asimismo, el neumático puede presentar otros tipo de anomalías </p>
                        </div>
                        <a href="#">
                            <button type="button" class="btn btn-primary">COTIZAR</button>
                        </a>
                    </div>

                </div>

                <div class="card">

                    <div class="imagen">
                        <img src="https://blog.reparacion-vehiculos.es/hubfs/Im%C3%A1genes_Post/Julio%202018/errores%20cambio-aceite.jpg" alt="">
                        <div class="titulo">
                            <h3 class="text-primary">Cambio lubricantes para engranajes</h3>
                        </div>
                    </div>

                    <div class="descripcion">
                        <div class="texto">
                            <p>El cambio de neumáticos es una de las operaciones de 
                                mantenimiento más frecuentes que se realizan sobre el vehículo. 
                                Asimismo, el neumático puede presentar otros tipo de anomalías </p>
                        </div>
                        <a href="#">
                            <button type="button" class="btn btn-primary">COTIZAR</button>
                        </a>
                    </div>

                </div>

                <div class="card">

                    <div class="imagen">
                        <img src="https://blog.reparacion-vehiculos.es/hubfs/Im%C3%A1genes_Post/Julio%202018/errores%20cambio-aceite.jpg" alt="">
                        <div class="titulo">
                            <h3 class="text-primary">Cambio lubricantes para engranajes</h3>
                        </div>
                    </div>

                    <div class="descripcion">
                        <div class="texto">
                            <p>El cambio de neumáticos es una de las operaciones de 
                                mantenimiento más frecuentes que se realizan sobre el vehículo. 
                                Asimismo, el neumático puede presentar otros tipo de anomalías </p>
                        </div>
                        <a href="#">
                            <button type="button" class="btn btn-primary">COTIZAR</button>
                        </a>
                    </div>

                </div>

                <div class="card">

                    <div class="imagen">
                        <img src="https://blog.reparacion-vehiculos.es/hubfs/Im%C3%A1genes_Post/Julio%202018/errores%20cambio-aceite.jpg" alt="">
                        <div class="titulo">
                            <h3 class="text-primary">Cambio lubricantes para engranajes</h3>
                        </div>
                    </div>

                    <div class="descripcion">
                        <div class="texto">
                            <p>El cambio de neumáticos es una de las operaciones de 
                                mantenimiento más frecuentes que se realizan sobre el vehículo. 
                                Asimismo, el neumático puede presentar otros tipo de anomalías </p>
                        </div>
                        <a href="#">
                            <button type="button" class="btn btn-primary">COTIZAR</button>
                        </a>
                    </div>

                </div>

                <div class="card">

                    <div class="imagen">
                        <img src="https://blog.reparacion-vehiculos.es/hubfs/Im%C3%A1genes_Post/Julio%202018/errores%20cambio-aceite.jpg" alt="">
                        <div class="titulo">
                            <h3 class="text-primary">Cambio lubricantes para engranajes</h3>
                        </div>
                    </div>

                    <div class="descripcion">
                        <div class="texto">
                            <p>El cambio de neumáticos es una de las operaciones de 
                                mantenimiento más frecuentes que se realizan sobre el vehículo. 
                                Asimismo, el neumático puede presentar otros tipo de anomalías </p>
                        </div>
                        <a href="#">
                            <button type="button" class="btn btn-primary">COTIZAR</button>
                        </a>
                    </div>

                </div>

                <div class="card">

                    <div class="imagen">
                        <img src="https://blog.reparacion-vehiculos.es/hubfs/Im%C3%A1genes_Post/Julio%202018/errores%20cambio-aceite.jpg" alt="">
                        <div class="titulo">
                            <h3 class="text-primary">Cambio lubricantes para engranajes</h3>
                        </div>
                    </div>

                    <div class="descripcion">
                        <div class="texto">
                            <p>El cambio de neumáticos es una de las operaciones de 
                                mantenimiento más frecuentes que se realizan sobre el vehículo. 
                                Asimismo, el neumático puede presentar otros tipo de anomalías </p>
                        </div>
                        <a href="#">
                            <button type="button" class="btn btn-primary">COTIZAR</button>
                        </a>
                    </div>

                </div>

            </div>
        </main>


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
        <script src="./js/sesion.js"></script>
    </body>
</html>
