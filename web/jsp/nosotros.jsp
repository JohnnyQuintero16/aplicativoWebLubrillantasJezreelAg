
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
        <link href="<%=basePath%>css/nosotros.css" rel="stylesheet" type="text/css"/>
        <link href="<%=basePath%>css/footer.css" rel="stylesheet" type="text/css"/>
    </head>
    <body onload="sesion('<%=request.getSession().getAttribute("usuario")%>')">



        <!-- Inicio menÃº-->
        <nav class="navbar navbar-expand-lg sticky-top navbar-dark bg-primary">
            <div class="container-fluid">

                <a class="navbar-brand" href="#">
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
                            <a class="nav-link active" href="<%=basePath%>/jsp/nosotros.jsp">NOSOTROS</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="<%=basePath%>/MostrarServicios.do">SERVICIOS</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link " aria-current="page"href="<%=basePath%>MostrarProductos.do">PRODUCTOS</a>
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
                                    <li><a class="dropdown-item" href="#" >Mi Cuenta</a></li>
                                    <li><a class="dropdown-item" href="<%=basePath%>MisServiciosUsu.do" >Mis Servicios</a></li>
                                    <li><hr class="dropdown-divider"></li>
                                    <li><a class="dropdown-item" href="./cerrarSesion.do">Salir</a></li>
                                </ul>
                            </li>

                            <svg xmlns="http://www.w3.org/2000/svg" style="color:#fff" width="50" height="50" fill="currentColor" class="bi bi-person-circle" viewBox="0 0 16 16">
                            <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
                            <path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
                            </svg>

                        </ul>
                    </template>

                </div>
            </div>
        </nav>
        <!-- Fin menÃº -->


        <section class="contenido">
            <div class="container-all">
                <input type="radio" id="1" name="image-slide" hidden/>
                <input type="radio" id="2" name="image-slide" hidden/>
                <input type="radio" id="3" name="image-slide" hidden/>

                <div class="slide">
                    <div class="item-slide">
                        <img id="background" src="<%=basePath%>/img/header.jpeg" style="filter:brightness(45%)">
                        <div class="carousel-caption d-flex flex-column justify-content-center h-100 txt-carousel">
                            <h1  class="tittle-carrusel ">NOSOTROS</h1>
                        </div>

                    </div>

                    <div class="item-slide">
                        <img id="background" src="<%=basePath%>/img/header_2.jpeg" style="filter:brightness(45%)">
                        <div class="carousel-caption d-flex flex-column justify-content-center h-100 txt-carousel">
                            <h1  class="tittle-carrusel ">NOSOTROS</h1>
                        </div>
                    </div>

                    <div class="item-slide">
                        <img id="background" src="<%=basePath%>/img/home.jpeg" style="filter:brightness(45%)">
                        <div class="carousel-caption d-flex flex-column justify-content-center h-100 txt-carousel">
                            <h1  class="tittle-carrusel ">NOSOTROS</h1>
                        </div>
                    </div>

                </div>

                <div class="pagination">

                    <label class="pagination-item" for="1">
                        <img id="background-p" src="<%=basePath%>/img/header.jpeg">

                    </label>

                    <label class="pagination-item" for="2">
                        <img id="background-p" src="<%=basePath%>/img/header_2.jpeg">
                    </label>

                    <label class="pagination-item" for="3">
                        <img id="background-p" src="<%=basePath%>/img/home.jpeg">
                    </label>

                </div>

            </div>

        </section>


        <div class="container-fluid">
            <div class="row row-principal">
                <div class="col-12 col-sm-12 col-md-12 col-lg-6 nosotros-img">
                    <img src="<%=basePath%>/img/home.jpeg" alt="">
                </div>

                <div class="col-12 col-sm-12 col-md-12 col-lg-6 nosotros-contenido">
                    <h3>SOBRE NOSOTROS</h3>
                    <h1 >Quiénes <p style="color: blue; display: inline;">somos</p></h1>
                    <hr size= "6" style="color: blue;">
                    <p>Lorem, ipsum dolor sit amet consectetur adipisicing elit. Aut rerum dolores numquam possimus, porro praesentium repellendus sequi velit eius facilis animi hic deserunt itaque quis. Veritatis quo voluptatem distinctio exercitationem.</p>
                    <p>Lorem, ipsum dolor sit amet consectetur adipisicing elit. Aut rerum dolores numquam possimus, porro praesentium repellendus sequi velit eius facilis animi hic deserunt itaque quis. Veritatis quo voluptatem distinctio exercitationem.</p>
                    <p>Lorem, ipsum dolor sit amet consectetur adipisicing elit. Aut rerum dolores numquam possimus, porro praesentium repellendus sequi velit eius facilis animi hic deserunt itaque quis. Veritatis quo voluptatem distinctio exercitationem.</p>
                </div>
            </div>

        </div>

        <div style="background-color: #F4F4F4;"> 
            <div class="container-fluid contenido-mv" ">

                <h1 >Nuestra <p style="color: blue; display: inline;">Misión</p></h1> <br>
                <p class="texto">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Doloremque aut quasi, explicabo eveniet provident illo quisquam accusamus aperiam dolore maxime? Vel nostrum quisquam omnis quis, perspiciatis ex vitae officiis eius?
                    Lorem ipsum dolor sit amet consectetur adipisicing elit. Recusandae illum, quia eaque ab et laudantium qui saepe tempora quos autem nam fugiat sapiente corporis modi atque cum earum quam vitae.
                </p>

            </div>
        </div>

        <div class="container-fluid contenido-mv">

            <h1 >Nuestra <p style="color: blue; display: inline;">Visión</p></h1> <br>
            <p class="texto">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Doloremque aut quasi, explicabo eveniet provident illo quisquam accusamus aperiam dolore maxime? Vel nostrum quisquam omnis quis, perspiciatis ex vitae officiis eius?
                Lorem ipsum dolor sit amet consectetur adipisicing elit. Recusandae illum, quia eaque ab et laudantium qui saepe tempora quos autem nam fugiat sapiente corporis modi atque cum earum quam vitae.
            </p>

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
        <script src="<%=basePath%>js/sesion.js"></script>
    </body>
</html>