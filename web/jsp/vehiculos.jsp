
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
        <link href="<%=basePath%>css/serviciosUsu.css" rel="stylesheet" type="text/css"/>
        <link href="<%=basePath%>css/footer.css" rel="stylesheet" type="text/css"/>
        <link href="<%=basePath%>css/vehiculos.css" rel="stylesheet" type="text/css"/>
    </head>

    <body>
        <!--Menú -->
        <nav class="navbar navbar-expand-lg sticky-top navbar-dark">
            <div class="container-fluid">

                <a class="navbar-brand" href="#">
                    <img src="<%=basePath%>img/LogoLJAG.png" alt="" width="140px" height="120px" />
                </a>

                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                        aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
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
                        <li class="nav-item">
                            <a class="nav-link" href="<%=basePath%>MostrarServiciosCotizacion.do">COTIZA YA!</a>
                        </li>
                    </ul>

                    <ul class="navbar-nav ml-auto m-4">
                        <li class="nav-item dropdown" style="list-style-type: none;">
                            <a  class="nav-link dropdown-toggle link-dark  " href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false" >
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
                    </ul>
                </div>



                <div class="user">
                    <img src="<%= request.getSession().getAttribute("urlFoto").toString()%>" width="70" height="70" class="rounded-circle me-2">
                </div>
            </div>
        </nav>
        <!--Fin Menú -->

        <div class="container-fluid">
            <div class="row">
                <!--perfil  -->
                <div class="col-md-3 col-lg-3 verticalLine vertil">    

                    <div class="d-flex flex-column flex-shrink-0 p-3 bg-light colum-datos"  >
                        <div >
                            <img class="bd-placeholder-img rounded-circle" id="Perfil" src="<%=request.getSession().getAttribute("urlFoto") %>">
                        </div>
                        <br>
                        <h4><%=request.getSession().getAttribute("nameUser")%></h4>
                        <hr>
                        <ul class="nav nav-pills flex-column mb-auto">
                            <li class="nav-item">
                                <a href="<%=basePath%>./jsp/datosCliente.jsp" class="nav-link link-dark" aria-current="page">
                                    <svg class="bi me-2" width="16" height="16">
                                    <use xlink:href="#home" />
                                    </svg>
                                    <strong color="gray"> Mis datos personales </strong></a>
                            </li>
                            <li>
                                <a href="<%=basePath%>MisVehiculos.do" class="nav-link link-dark">
                                    <svg class="bi me-2 servicios" width="16" height="16">
                                    <use xlink:href="#speedometer2" />
                                    </svg>
                                    <strong id="servi"> Mis Vehículos</strong> </a>
                            </li>
                            <li>
                                <a href="<%=basePath%>MisServiciosUsu.do" class="nav-link link-dark">
                                    <svg class="bi me-2 " width="16" height="16" >
                                    <use xlink:href="#table" />
                                    </svg> 
                                    <strong > Mis Servicios</strong> </a>
                            </li>
                            <li>
                                <a href="<%=basePath%>MostrarCitasUsu.do" class="nav-link link-dark">
                                    <svg class="bi me-2" width="16" height="16">
                                    <use xlink:href="#grid" />
                                    </svg> 
                                    <strong> Mis Citas</strong></a>
                            </li>
                        </ul>
                    </div>        
                </div>
                <!-- end perfil -->

                <!-- VEHICULOS -->
                <div class="col-12 col-sm-12 col-md-9 col-lg-9">
                    <h2 class="titulo"> MIS VEHICULOS</h2>
                    <hr>
                    <div class="vehiculo-datos">
                        <%= request.getSession().getAttribute("vehiculos")%>
                    </div>
                    <br>
                </div>

                <!-- end servicios -->
            </div>
        </div>


        <!--FOOTER-->
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
        <!--FIN FOOTER-->
    </body>

</html>