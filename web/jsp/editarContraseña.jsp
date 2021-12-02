

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
        <title>Mis Datos Personales</title>

        <!--Importar CSS de la fuente desde Google -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Francois+One&display=swap" rel="stylesheet">

        <!--Link del boostrap-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

        <!--Importar CSS y script del menú, footer y citas de usuario-->
        <link rel="stylesheet" href="<%=basePath%>css/menu.css"/>
        <link rel="stylesheet" href="<%=basePath%>css/datosPersonales.css"/>
        <link rel="stylesheet" href="<%=basePath%>css/citasUsu.css">  
        <link rel="stylesheet" href="<%=basePath%>css/footer.css">

        <%

            String correcta = "si";
            String iguales = "si";
            if (request.getSession().getAttribute("passwordcorrecta") != null) {
                correcta = request.getSession().getAttribute("passwordcorrecta").toString();

            }
            if (request.getSession().getAttribute("iguales") != null) {
                iguales = request.getSession().getAttribute("iguales").toString();

            }


        %>

    </head>
    <body onload="sesion('<%=request.getSession().getAttribute("usuario")%>')">  
        <!--Menú -->
        <nav class="navbar navbar-expand-lg sticky-top navbar-dark">
            <div class="container-fluid">

                <!-- LOGO DE LA EMPRESA -->
                <a class="navbar-brand" href="#">
                    <img src="<%=basePath%>img/LogoLJAG.png" alt="" width="140px" height="120px" style="margin: -20px;" />
                </a>

                <!-- BOTON DE NAV RESPONSIVO -->
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                        aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <!-- NAV -->
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link " aria-current="page" href="<%=basePath%>index.jsp">INICIO</a>
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

                    <!-- MENU DESPLEGABLE NOMBRE USUARIO -->
                    <ul class="navbar-nav ml-auto m-4">
                        <li class="nav-item dropdown" style="list-style-type: none;">
                            <a class="nav-link dropdown-toggle link-dark  " href="#" id="navbarDropdown" role="button"
                               data-bs-toggle="dropdown" aria-expanded="false">
                                <%= request.getSession().getAttribute("nameUser")%>
                            </a>
                             <ul class="dropdown-menu text-small "aria-labelledby="dropdownUser2"  >
                                        <li><a class="dropdown-item" href="<%=basePath%>./jsp/datosCliete.jsp" >Mi Cuenta</a></li>
                                        <li><a class="dropdown-item" href="<%=basePath%>MisVehiculos.do" >Mis Vehiculos</a></li>
                                        <li><a class="dropdown-item" href="<%=basePath%>MisServiciosUsu.do" >Mis Servicios</a></li>
                                        <li><a class="dropdown-item" href="<%=basePath%>MostrarCitasUsu.do" >Mis Citas</a></li>
                                        <li><hr class="dropdown-divider"></li>
                                        <li><a class="dropdown-item" href="<%=basePath%>/cerrarSesion.do">Salir</a></li>
                                    </ul>
                        </li>
                    </ul>
                </div>

               <div class="perfil-nav">
                    <img src="<%= request.getSession().getAttribute("urlFoto").toString() %>" width="70" height="70" class="rounded-circle me-2">
                </div>
            </div>
        </nav>
        <!--Fin Menú -->


        <div class="container-fluid">
            <div class="row ">
                <!--SideBar de Opciones -->
                <div class=" sidebar col-3 col-sm-3 col-md-3 col-lg-3 verticalLine">
                    <div class="d-flex flex-column flex-shrink-0 p-3 bg-light colum-datos">					
                        <aside>
                            <div class="side-inner">
                               <div class="profile">
                                    <img  src="<%= request.getSession().getAttribute("urlFoto").toString() %>" alt="Image" class="img-fluid rounded-circle ">
                                    <h3 class="name"><%= request.getSession().getAttribute("nameUser")%></h3>
                                </div>
                                <div class="nav-menu">
                                     <ul > 
                                        <li><a href="<%=basePath%>./jsp/datosCliente.jsp"<%=basePath%>MostrarServiciosAdmin.do"><span class=""></span>Mis Datos Personales</a></li>
                                        <li><a href="<%=basePath%>MisVehiculos.do"><span class=""></span>Mis Vehículos</a></li>
                                        <li><a href="<%=basePath%>MisServiciosUsu.do"><span class=""></span>Mis Servicios</a></li>
                                        <li id="misCitas"><a href="<%=basePath%>MostrarCitasUsu.do"><span class=""></span>Mis Citas</a></li>
                                    </ul>
                                </div>
                            </div>  
                        </aside>
                    </div>
                </div>
                <!-- Fin del SideBar de Opciones -->

                <!-- Contenido Principal para editar la contraseña-->
                <div class="d-flex flex-column col-9 col-sm-9 col-md-9 col-lg-9">
                    <div> 
                        <h1 id="citas"> EDITAR CONTRASEÑA</h1>
                    </div>	
                    <div>
                        <form class="row" id="form-datos" action="<%=basePath%>UpdatePassword.do" method="POST">
                            <div class="col-md-6">
                                <label for="actual" class="form-label">Contraseña Actual</label>
                                <input type="password" class="form-control" id="actual" name="actual" required>
                            </div>
                            <div class="col-md-6">
                                <label for="newContraseña" class="form-label">Nueva Contraseña</label>
                                <input type="password" class="form-control" id="newContraseña" name="newpassword" required>
                            </div>
                            <div class="col-md-6">
                                <br>
                                <label for="newContraseña2" class="form-label">Repetir Nueva Contraseña</label>
                                <input type="password" class="form-control" id="newContraseña2" name="newpassword2" required>
                            </div>


                            <div  id="boton" class="col-6 align-content-center ">
                                <br>
                                <br>
                                <button id="save" type="submit" class="btn btn-primary"> Guardar Contraseña</button>
                            </div>
                        </form>
                    </div>
                </div>
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
        <script src="<%=basePath%>js/sesion.js"></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <script>

            document.body.onload = function exist() {
                let co = '<%=correcta%>';

                if (<%=correcta.equals("no")%>) {
                      swal("Oops!", "Contraseña Invalida!", "error");
            <% request.getSession().setAttribute("passwordcorrecta", "si");%>
                }
                if (<%=iguales.equals("no")%>) {
                     swal("Oops!", "Las contraseñas no coinciden!", "error");
                    //alert('Las contraseñas no coinciden');
            <% request.getSession().setAttribute("iguales", "si");%>
                }

                console.log("iguales = " + <%=iguales%>);
            }
        </script>
    </body>

</html>