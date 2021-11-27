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
        <title>Lubrillantas Jezreel AG - Administración</title>

        <!-- Fuente de google: Open Sans - Regular 400 -->
        <link href="https://fonts.googleapis.com/css?family=Poppins:400,500,600,700&display=swap" rel="stylesheet">

        <!-- Boxicons CDN Link -->
        <link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"/>

        <!-- DataTable -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.0.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdn.datatables.net/1.11.3/css/dataTables.bootstrap5.min.css">

        <!--Importar CSS y script del menú -->
        <link rel="stylesheet" href="<%=basePath%>css/menuAdministrador.css" />
        <link rel="stylesheet" href="<%=basePath%>css/admClientes.css" />

    </head>
    <body>

        <div class="sidebar"> 
            <div class="logo-details">
                <i class="fas fa-tire icon"></i> 
                <!-- Espacio entre mensaje Bienvenido-->
                <div class="logo_name">Bienvenido</div>
                <i class='bx bx-menu' id="btn" ></i>
            </div>

            <ul class="nav-list">
                <li>
                    <div class="image-admin">
                        <div class="container-img">
                            <img src="<%=basePath%>img/user.png" alt="Administrador">
                        </div>
                        <div class="container-name">
                            <p><span class="links_name"><%=request.getSession().getAttribute("nameUser")%></span></p>
                        </div>
                    </div>
                </li>

                <li>
                    <a href="<%=basePath%>jsp/citasAdmin.jsp"">
                        <i class="far fa-calendar-alt"></i>
                        <span class="links_name">Agendamientos</span>
                    </a>
                    <span class="tooltip">Agendamientos</span>
                </li>
                <li>
                    <a href="<%=basePath%>jsp/adminClientes.jsp">
                        <i class="icon fas fa-user"></i>
                        <span class="links_name">Clientes</span>
                    </a>
                    <span class="tooltip">Clientes</span>
                </li>
                <li>
                    <a href="<%=basePath%>MostrarServiciosAdmin.do">
                        <i class="fas fa-user-cog"></i>
                        <span class="links_name">Servicios</span>
                    </a>
                    <span class="tooltip">Servicios</span>
                </li>
                <li>
                    <a href="<%=basePath%>jsp/productosAdmin.jsp">
                        <i class="fas fa-shopping-cart"></i>
                        <span class="links_name">Productos</span>
                    </a>
                    <span class="tooltip">Productos</span>
                </li>

                <li class="profile">
                    <a href="<%=basePath%>cerrarSesion.do">
                        <i class='bx bx-log-out'></i>
                        <span class="links_name">Salir</span>
                    </a>
                </li>
            </ul>
        </div>

        <section class="home-section">

            <div class=" title">            
                <div class="titulo">
                    <h1>Cliente: </h1>
                </div>
            </div>

            <div class="row" style="margin-bottom: 4rem;" >
                <div class="col-12 col-sm-6 col-md-6 col-lg-3" style="padding-left: 5rem">
                    <h2 style="color: #001971;">Escoja el Servicio:</h2>
                    <img src="<%=basePath%>img/LogoLJAG.png" alt="LogoA"  width="140px" height="120px" >
                </div>

                <div class="col-12 col-sm-6 col-md-6 col-lg-3" style="padding-top: 0.5rem; ">
                    <select style="background-color: rgb(214, 205, 205);" >
                        <option value="0">-</option> 
                        <option value="1" style="background-color: rgb(241, 233, 233);">Cambio de Aceite</option> 
                        <option value="2">Cambio de Filtro de aire</option> 
                        <option value="3" style="background-color: rgb(241, 233, 233);">Servicio de Montallantas</option> 
                        <option value="4">Servicio de frenos</option> 

                    </select>
                </div>  

                <div class="col-12 col-sm-6 col-md-6 col-lg-3" style="padding-left: 5rem">
                    <h2 style="color: #001971;">Escoja el Mecánico:</h2>
                    <img src="<%=basePath%>img/vehicle.png" alt="LogoA"  width="140px" height="120px" >
                </div>

                <div class="col-12 col-sm-6 col-md-6 col-lg-3" style="padding-top: 0.5rem; ">
                    <select style=" background-color: rgb(214, 205, 205);" >
                        <option value="0">-</option> 
                        <option value="1" style="background-color: rgb(241, 233, 233);">Sebastian Casadiegos</option> 
                        <option value="2">Cristian Mendoza</option> 
                        <option value="3" style="background-color: rgb(241, 233, 233);">Jeferson Muriel</option> 
                        <option value="4">Jhoel Castilla</option> 

                    </select>
                </div>    

            </div>



            <div class="row" style=" background-color: white; padding-top: 1rem; ">
                <div class="col-12 col-sm-6 col-md-6 col-lg-6" style="padding-left: 5rem">
                    <h2 style="color: #001971;">Escoja el Producto:</h2>
                    <p>Lista de productos con los que se llevo a cabo el servicio.</p>
                    <table class="table" >
                        <tr>
                            <th>id</th>
                            <th>Nombre Producto</th>
                            <th>Accion</th>
                        </tr>

                        <tr>
                            <td>1</td>
                            <td>Aceite Max</td>
                            <td> <button type="button" class="btn btn-primary btn-lg" data-bs-toggle="modal" data-bs-target="#modal1" style="background-color: red !important;">X</button></td>                        
                        </tr>
                        <tr>
                            <td>2</td>
                            <td>Aceite Min</td>
                            <td> <button type="button" class="btn btn-primary btn-lg" data-bs-toggle="modal" data-bs-target="#modal1" style="background-color: red !important;">X</button></td>                        
                        </tr>

                    </table>

                </div>


                <div class="search-container col-12 col-sm-6 col-md-6 col-lg-6" style="padding: 1rem;"> <br>

                    <form action="">
                        <input type="text" placeholder="Buscar Producto.." name="search">
                        <button  type="submit"><i class="fa fa-search " style="width: 2rem;"></i></button>
                    </form> <br><br>

                    <p>Por favor digite el kilometraje del vehiculo:</p>
                    <input type="number" id="kilometraje" name="kilometraje" placeholder="kilometraje"  required> <br> <br>
                    <div>
                        <button type="button" class="btn btn-primary btn-lg" data-bs-toggle="modal" data-bs-target="#modal1" style="background-color: rgb(235, 71, 71) !important;">Cancelar</button>
                        <button type="button" class="btn btn-primary btn-lg" data-bs-toggle="modal" data-bs-target="#modal1" style="background-color: #001971  !important;">Agregar</button>
                    </div>

                </div>
            </div>
        </section>
        <script src="<%=basePath%>js/menuAdministrador.js"></script>
        <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
        <script src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.11.3/js/dataTables.bootstrap5.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>


        <script>
            $(document).ready(function() {
            $('#example').DataTable({

            "language":{
            "lengthMenu": "Mostrar_MENU_registros",
            "zeroRecords": "No se encontraron resultados",
            "info": "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
            "infoEmpty": "Mostrando registros del 0 al 0 de un total de 0 registros",
            "infoFiltered": "(Filtrado de un total de _MAX_ registros)",
            "sSearch": "Buscar:",
            "oPaginate": {
            "sFirst": "Primero",
            "sLast": "Último",
            "sNext": "Siguiente",
            "sPrevious": "Anterior"
            },
            "sProcessing": "Procesando...",
            }

            }
            );
            } );
        </script>

        <!-- Validación de formulario -->
        <script>
            (function () {
            'use strict'

            var forms = document.querySelectorAll('.needs-validation')

            // Loop over them and prevent submission
            Array.prototype.slice.call(forms)
            .forEach(function (form) {
            form.addEventListener('submit', function (event) {
            if (!form.checkValidity()) {
            event.preventDefault()
            event.stopPropagation()
            }
            form.classList.add('was-validated')
            }, false)
            })
            })()
        </script>
    </body>
</html>