<%@ page contentType="text/html; charset=UTF-8" %>
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
        <link href="<%=basePath%>css/iniciarsesion.css" rel="stylesheet" type="text/css"/>
        <link href="<%=basePath%>css/footer.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"/>
    </head>
    <body onload="sesion('<%=request.getSession().getAttribute("usuario")%>')">
        <!-- Inicio menú-->
        <nav class="navbar navbar-expand-lg sticky-top navbar-dark bg-primary">
            <div class="container-fluid">

                <a class="navbar-brand" href="<%=basePath%>/index.jsp">
                    <img src="<%=basePath%>/img/LogoLJAG.png" alt="" width="140px" height="120px"/>
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
                            <a class="nav-link" href="<%=basePath%>/MostrarProductos.do">PRODUCTOS</a>
                        </li>
                    </ul>
                        <ul class="navbar-nav ml-auto m-4">
                            <li class="nav-item">
                                <a class="nav-link" href="<%=basePath%>jsp/iniciarsesion.jsp">INICIAR SESIÓN</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="./jsp/registrarse.jsp">REGISTRARSE</a>
                            </li>
                        </ul>

                </div>
            </div>
        </nav>
        <!-- Fin menú -->   

        <main>
            <div class="wrapper">
                <div class="encabezado">
                    <h1>Iniciar Sesión</h1>
                </div>
                <form action='<%=basePath%>/IniciarSesion.do' method="POST" class="formulario" id="formulario">
                    <div class="field cedula">
                        <div class="input-area">
                            <input type="text" name="cedula" placeholder="Número de cédula">
                            <i class="icon fas fa-user"></i>
                            <i class="error error-icon fas fa-exclamation-circle"></i>
                        </div>
                        <div class="error error-txt">La cedula no puede estar en blanco</div>
                    </div>
                    <div class="field password">
                        <div class="input-area">
                            <input type="password" name="clave" placeholder="Contraseña">
                            <i class="icon fas fa-lock"></i>
                            <template id="errorClave" ><i class="error error-icon fas fa-exclamation-circle">Digito mal su clave</i></template>
                        </div>

                        <div class="error error-txt">La contraseña no puede estar en blanco</div>
                    </div>
                    
                   
                    <input type="submit" value="Ingresar" >

                </form>
                <div class="sign-txt">¿No eres miembro? <a href="<%=basePath%>/jsp/registrarse.jsp">Registrate</a></div>
            </div>

        </main>
        <div class="modal fade" id="staticBackdrop" data-bs-backdrop="statc" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
            <div class="modal-dialog modal-danger" id="Mymodal">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="staticBackdropLabel" style="color:red">¡¡Error!!</h5>

                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class = "row">
                            Sus datos son incorrectos, por favor vuelva a digitarlos!!
                        </div>
                        <div class = "row" class = "display: flex; align-content: center;">
                           <svg xmlns="http://www.w3.org/2000/svg" width="80" height="80" style="color:#dc3545; " fill="currentColor" class="bi bi-x-circle" viewBox="0 0 16 16">
                            <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
                            <path d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"/>
                            </svg>
                        </div>
                    </div>
                    
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" data-bs-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>



        <!--FOOTER-->
        <footer>
            <div class="container-fluid">
                <div class="row ">
                    <div class="col-12 redes" style="background-color: #00114e;">
                        <img src="<%=basePath%>/img/whatsapp.png" >
                        <img src="<%=basePath%>/img/facebook.png" >
                        <img src="<%=basePath%>/img/instagram.png" >
                    </div>
                </div>
                <div class="row" style="background-color: #001971;">

                    <div class="col-12 col-sm-4 col-md-4 col-lg-4">
                        <img src="<%=basePath%>/img/LogoLJAG.png" alt="Logo Jezreel" id="imgFooter">
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

        <script>
            document.body.onload = function validarCampos(){
            var campo = '<%=request.getSession().getAttribute("mensaje")%>';
            if(campo === "err"){
            var myModal = new bootstrap.Modal(document.getElementById('staticBackdrop'), {
            keyboard: false,
            focus: true
            })
            myModal.show();
            }
            }                      
        </script>
        <script src="<%=basePath%>js/sesion.js"></script>

    </body>
</html>