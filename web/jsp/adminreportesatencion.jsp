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
        <title>Lubrillantas Jezreel AG - AdministraciÃ³n</title>

        <!-- Fuente de google: Open Sans - Regular 400 -->
        <link href="https://fonts.googleapis.com/css?family=Poppins:400,500,600,700&display=swap" rel="stylesheet">

        <!-- Boxicons CDN Link -->
        <link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"/>

        <!-- DataTable -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.0.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdn.datatables.net/1.11.3/css/dataTables.bootstrap5.min.css">

        
        <link rel="stylesheet" href="<%=basePath%>css/menuAdministrador.css" />
        <link rel="stylesheet" href="<%=basePath%>css/admClientes.css" />

        <style>
            .arreglo{
                background-color: #10f035f6 !important;
                margin-right: 27px;
                float: right;
                border: none;
            }
        </style>
    </head>
    <body>

        <div class="sidebar">
            <div class="logo-details">
                <i class="fas fa-tire icon"></i> <!-- Espacio entre mensaje Bienvenido-->
                <div class="logo_name">Bienvenido</div>
                <i class='bx bx-menu' id="btn" ></i>
            </div>

            <ul class="nav-list">
                <li>
                    <div class="image-admin">
                        <div class="container-img">
                            <img src="<%=basePath%>img/user-admin.png" alt="Administrador">
                        </div>
                        <div class="container-name">
                            <p>
                                <span class="links_name"><%=request.getSession().getAttribute("nameUser")%></span>
                            </p>
                        </div>
                    </div>
                </li>

                <li>
                    <a href="<%=basePath%>CitasAdmin.do">
                        <i class="far fa-calendar-alt"></i>
                        <span class="links_name">Agendamientos</span>
                    </a>
                    <span class="tooltip">Agendamientos</span>
                </li>
                <li>
                    <a href="<%=basePath%>./jsp/adminClientes.jsp">
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
                    <a href="<%=basePath%>MostrarProductosAdmin.do">
                        <i class="fas fa-shopping-cart"></i>
                        <span class="links_name">Productos</span>
                    </a>
                    <span class="tooltip">Productos</span>
                </li>

                <li>
                    <a href="<%=basePath%>ValoresEstadisticas.do">
                        <i class="fas fa-chart-pie"></i>
                        <span class="links_name">Reportes y Estada­sticas</span>
                    </a>
                    <span class="tooltip">Reportes y Estada­sticas</span>
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
            <div class="title">
                <div class="titulo">
                    <h1>Reportes de atencion de servicios de clientes</h1>
                </div>

                <div class="boton">
                    <button type="button" class="btn btn-primary btn-lg">Regresar</button>
                </div>
            </div>

            <div class="container-fluid">
                <div class="row"> 
                    <!--select -->


                    <!--fecha ini -->
                    <div class="col-4">
                        <div class="row mb-3">
                            <label class="col-sm-2 col-form-label">Desde:</label>
                            <div class="col-sm-8">
                                <input type="date" class="form-control">
                            </div>
                        </div>
                    </div>

                    <!--fecha fin -->
                    <div class="col-4">
                        <div class="row mb-3">
                            <label class="col-sm-2 col-form-label">Hasta:</label>
                            <div class="col-sm-8">
                                <input type="date" class="form-control">
                            </div>
                        </div>
                    </div>

                    <!--fecha boton -->
                    <div class="col-4">
                        <button type="button" class="btn btn-primary btn-lg">Consultar</button>
                    </div>
                </div>
            </div>

            <br>
            <div class="table-responsive table-style">
                <table id="example" class="table table-bordered table-striped table-hover">
                    <thead class="table-secondary">
                        <tr>
                            <th class="enc" scope="col">Id</th>
                            <th class="enc" scope="col">Placa</th>
                            <th class="enc" scope="col">Marca</th>
                            <th class="enc" scope="col">Kilometraje</th>
                            <th class="enc" scope="col">Cedula</th>
                            <th class="enc" scope="col">Nombres</th>
                            <th class="enc" scope="col">Descripcion</th>
                            <th class="enc" scope="col">Fecha / Hora</th>
                            <th class="enc" scope="col">Total Factura</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <th class="enc" scope="row">1</th>
                            <td>CUX-347</td>
                            <td>Spark </td>
                            <td>61200</td>
                            <td>1149453973</td>
                            <td>Susana Rojas Triana</td>
                            <td>No se que poner</td>
                            <td>aqui la fecha</td>
                            <td>150000</td>
                            <!-- Acciones: editar y cancelar. -->

                        </tr>


                    </tbody>
                </table>
            </div>

            <br>
            <!-- reporte -->

            <button type="button" class=" arreglo btn btn-primary btn-lg" data-bs-toggle="modal" data-bs-target="#modal1">Generar excel</button>


            <!-- modal-->
            <div class="modal fade" tabindex="-1" id="modal1" aria-labelledby="modal1" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Generar Excel (.XLXS)</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <p style="text-align:center">¿Quieres generar un archivo excel del reporte?</p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" style="background-color: #f70b0b !important;">Cancelar</button>
                            <button type="button" class="btn btn-primary">Confirmar</button>
                        </div>
                    </div>
                </div>
            </div>
        </section>




        <script src="../js/menuAdministrador.js"></script>
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
            "sLast": "Ãltimo",
            "sNext": "Siguiente",
            "sPrevious": "Anterior"
            },
            "sProcessing": "Procesando...",
            }

            }
            );
            } );
        </script>

        <!-- ValidaciÃ³n de formulario -->
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