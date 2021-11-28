<!DOCTYPE html>
<html lang="en">

    <head>
        <%
            String path = request.getContextPath();
            String basePath = request.getScheme() + "://" + request.getServerName() + ":"
                    + request.getServerPort() + path + "/";
        %>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Lubrillantas Jezreel AG</title>

        <!-- Fuente de google: Open Sans - Regular 400 -->
        <link href="https://fonts.googleapis.com/css2?family=Open+Sans&display=swap" rel="stylesheet">


        <!--Link del boostrap-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>

        <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>

        <!--Importar CSS y script del men� -->
        <link rel="stylesheet" href="../css/menu.css" />
        <link rel="stylesheet" href="../css/index.css" />
        <link rel="stylesheet" href="../css/serviciosUsu.css" />
        <link href="../css/footer.css" rel="stylesheet" type="text/css"/>

    </head>

    <body>
        <!--Men� -->
        <nav class="navbar navbar-expand-lg sticky-top navbar-dark">
            <div class="container-fluid">

                <!-- LOGO DE LA EMPRESA -->
                <a class="navbar-brand" href="#">
                    <img src="../img/LogoLJAG.png" alt="" width="140px" height="120px" style="margin: -20px;" />
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
                            <a class="nav-link " aria-current="page" href="#">INICIO</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">NOSOTROS</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">SERVICIOS</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">PRODUCTOS</a>
                        </li>
                    </ul>

                    <!-- MENU DESPLEGABLE NOMBRE USUARIO -->
                    <ul class="navbar-nav ml-auto m-4">
                        <li class="nav-item dropdown" style="list-style-type: none;">
                            <a class="nav-link dropdown-toggle link-dark  " href="#" id="navbarDropdown" role="button"
                               data-bs-toggle="dropdown" aria-expanded="false">
                                NOMBRE USUARIO
                            </a>
                            <ul class="dropdown-menu text-small " aria-labelledby="dropdownUser2">
                                <li><a class="dropdown-item" href="#">Mi Cuenta</a></li>
                                <li>
                                    <hr class="dropdown-divider">
                                </li>
                                <li><a class="dropdown-item" href="#">Salir</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>

                <!-- IMAGEN DE USARIO EN EL NAV -->
                <div class="user">
                    <img src="../img/user.png" width="70" height="70" class="rounded-circle me-2">
                </div>
            </div>
        </nav>
        <!--Fin Men� -->

        <div class="container-fluid">
            <div class="row">
                <!--perfil -->
                <div class="col-md-3 col-lg-3 verticalLine">
                    <div class="d-flex flex-column flex-shrink-0 p-3 bg-light colum-datos">
                        <!-- img grande de usuario -->
                        <img class="bd-placeholder-img rounded-circle" id="Perfil" src="../img/usuario.png">
                        <br>
                        <h4>Nombre de Usuario</h4>
                        <hr>
                        <ul class="nav nav-pills flex-column mb-auto">
                            <li class="nav-item">
                                <a href="#" class="nav-link link-dark" aria-current="page">
                                    <svg class="bi me-2" width="16" height="16">
                                    <use xlink:href="#home" />
                                    </svg>
                                    <strong color="gray"> Mis datos personales </strong></a>
                            </li>
                            <li>
                                <a href="#" class="nav-link link-dark">
                                    <svg class="bi me-2" width="16" height="16">
                                    <use xlink:href="#speedometer2" />
                                    </svg>
                                    <strong> Mis Veh�culos</strong> </a>
                            </li>
                            <li>
                                <a href="#" class="nav-link link-dark">
                                    <svg class="bi me-2 servicios" width="16" height="16">
                                    <use xlink:href="#table" />
                                    </svg>
                                    <strong id="servi"> Mis Servicios</strong> </a>
                            </li>
                            <li>
                                <a href="#" class="nav-link link-dark">
                                    <svg class="bi me-2" width="16" height="16">
                                    <use xlink:href="#grid" />
                                    </svg>
                                    <strong> Mis Citas</strong></a>
                            </li>
                        </ul>
                    </div>
                </div>
                <!-- end perfil -->

                <!-- SERVICIOS -->
                <div class="col-12 col-sm-12 col-md-9 col-lg-9">
                    <h2 class="titulo"> MIS SERVICIOS</h2>
                    <hr>
                    <!-- INICIO CARD 1 -->
                    <div class="card">
                        <div class="card-header row " id="cardt">
                            <div class="col-4  verticalLine centrado">
                                <h4> Servicio No�</h4>
                            </div>
                            <div class="col-4  verticalLine centrado">
                                <h4> Fecha</h4>
                            </div>
                            <div class="col-4">
                                <h4>Total $</h4>
                            </div>
                        </div>
                        <div class="card-body row">
                            <!-- img servicio -->
                            <div class="col-12 col-sm-6 col-md-4 col-lg-4">
                                <img src="../img/serV1.jpg" id="imgServicio">
                            </div>

                            <!-- texto del servicio -->
                            <div class="col-12 col-sm-6 col-md-8 col-lg-8">
                                <p class="card-text">
                                    Lorem, ipsum dolor sit amet consectetur   adipisicing elit. Reprehenderit amet sit distinctio similique inventore vero necessitatibus temporibus consequuntur hic quo sapiente veritatis aliquam, quis officia incidunt consectetur at nam nobis!
                                </p>

                                <!-- boton del servicio -->
                                <a href="#" class="btn" id="boton" type="button"  data-bs-toggle="modal" data-bs-target="#modal1">
                                    Ver Servicio
                                </a>

                                <!-- ventana modal -->
                                <div class="modal fade" tabindex="-1" role="dialog" id="modal1" aria-labelledby="modal1" aria-hidden="true">
                                    <div class="modal-dialog modal-lg" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h2 class="modal-title">Detalles del Servicio</h2>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close">
                                                </button>
                                            </div>
                                            <div class="modal-body">
                                                <table class="table table-bordered table-responsive table-striped">
                                                    <thead style="background-color: #616060a1;">
                                                        <tr>
                                                            <th scope="col">Concepto</th>
                                                            <th scope="col">Und</th>
                                                            <th scope="col">Precio</th>
                                                            <th scope="col">IVA</th>
                                                            <th scope="col">Total</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <tr>
                                                            <td>XXXX</td>
                                                            <td>1</td>
                                                            <td>$3000</td>
                                                            <td>$380</td>
                                                            <td>$3380</td>
                                                        </tr>
                                                        <tr>
                                                            <td>XXXX</td>
                                                            <td>1</td>
                                                            <td>$ 3000</td>
                                                            <td>$380</td>
                                                            <td>$3380</td>
                                                        </tr>
                                                        <tr>
                                                            <td>XXXX</td>
                                                            <td>1</td>
                                                            <td>$ 3000</td>
                                                            <td>$380</td>
                                                            <td>$3380</td>
                                                        </tr>
                                                    </tbody>
                                                </table>

                                                <br>
                                                <h6>SUBTOTAL</h6>
                                                <hr width="30%">
                                                <h6>IVA</h6>
                                                <hr width="30%">
                                                <h6>TOTAL</h6>
                                                <hr width="30%">
                                            </div>
                                            <div class="modal-footer" id="foterM">
                                                <a href="#" class="btn" id="boton" type="button" data-bs-toggle="modal" data-bs-target="#modal2">Calificar servicio</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>


                            </div>
                        </div>
                        <br>
                    </div>
                    <br>


                    <!-- end servicios -->
                </div>
            </div>
        </div>
        <!-- ventana modal de estrellas-->
        <div class="modal fade" tabindex="-1" role="dialog" id="modal2" aria-labelledby="modal1" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h2 class="modal-title">Calificar  Servicio</h2>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close">
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="col-12" style="text-align: center;">
                            <br>
                            <h5>�Cu�l fue tu nivel de satisfacci�n?</h5>

                            <span class="fa fa-star" onclick="calificar(this)" style="cursor: pointer;" id="1star"></span>

                            <span class="fa fa-star" onclick="calificar(this)" style="cursor: pointer;" id="2star"></span>

                            <span class="fa fa-star" onclick="calificar(this)" style="cursor: pointer;" id="3star"></span>

                            <span class="fa fa-star" onclick="calificar(this)" style="cursor: pointer;" id="4star"></span>

                            <span class="fa fa-star" onclick="calificar(this)" style="cursor: pointer;" id="5star"></span>
                        </div>

                        <br>
                        <textarea class="form-control" id="exampleFormControlTextarea1" rows="3" placeholder="Danos tu opini�n sobre el servicio ..."></textarea>
                    </div>
                    <div class="modal-footer" id="foterM">
                        <a href="#" class="btn" id="boton" type="button">
                            Guardar</a>
                        <a href="#" class="btn btn-danger" type="button">  Cancelar</a>
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
                        <h4 >HORARIOS DE ATENCI�N</h4>
                        <p>Lunes a Viernes</p>
                        <p>7:30 AM a 6:00 PM</p>
                        <p>S�bado</p>
                        <p>7:30 AM a 5:00 PM</p>
                    </div>

                    <div class="col-12  col-sm-4 col-md-4 col-lg-4 footer-contacto" >
                        <h4 > CONTACTO </h4>
                        <P>Av 5 # 0N-54 Barrio La Merced</P>
                        <p>San Jos� de C�cuta - Colombia</p>
                        <p>albeirofonseca74@gmail.com</p>
                        <p>+57 3112810082</p>
                    </div>

                </div>
            </div>
        </footer>
        <!--FIN FOOTER-->

    </body>
    <!-- calificar estrellas -->
    <script>
        var contador;

        function calificar(item) {
            contador = item.id[0];
            let nombre = item.id.substring(1);

            for (let i = 0; i < 5; i++) {
                if (i < contador) {
                    document.getElementById((i + 1) + nombre).style.color = "blue";
                } else {
                    document.getElementById((i + 1) + nombre).style.color = "black";
                }
            }
        }
    </script>
</html>
