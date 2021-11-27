<%-- 
    Document   : fichaTecnica
    Created on : 21/11/2021, 10:52:32 a. m.
    Author     : Rubie
--%>

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
        <title>Lubrillantas Jezreel AG - Administración</title>

          <link rel="stylesheet" href="<%=basePath%>css/admClientes.css" />
        <link href="<%=basePath%>css/menuAdministrador.css" rel="stylesheet" type="text/css"/>

        <!-- Fuente de google: Open Sans - Regular 400 -->
        <link href="https://fonts.googleapis.com/css?family=Poppins:400,500,600,700&display=swap" rel="stylesheet">

        <!-- Boxicons CDN Link -->
        <link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"/>

        <!-- DataTable -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.0.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdn.datatables.net/1.11.3/css/dataTables.bootstrap5.min.css">
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
                                <span class="links_name">Administrador</span>
                            </p>
                        </div>
                    </div>
                </li>

                <li>
                    <a href="#">
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
                    <a href="<%=basePath%>jsp/serviciosAdmin.jsp">
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

                <li class="profile">
                    <a href="#">
                        <i class='bx bx-log-out'></i>
                        <span class="links_name">Salir</span>
                    </a>
                </li>

            </ul>
        </div>


        <section class="home-section">
            <div class="title">

                <div class="titulo">
                    <h1>Ficha Técnica- Nombre de Alguien</h1>
                </div>

                <div class="boton">
                    <input class="btn boton" id="botonRegresar" type="button" value="Regresar" style="color: white;" onclick="location.href='<%=basePath%>jsp/adminClientes.jsp'">
                </div>

            </div>

            <div class="table-responsive table-style" >
                <h4>Datos del Cliente</h4> <br>
                <table  class="table table-bordered table-striped table-hover" >
                    <thead class="table-secondary">
                        <tr>
                            <th class="enc" scope="col">N°</th>
                            <th class="enc" scope="col">Cédula</th>
                            <th class="enc" scope="col">Nombre</th>
                            <th class="enc" scope="col">Celular</th>
                            <th class="enc" scope="col">Correo Electrónico</th>

                        </tr>
                    </thead>
                    <tbody style="text-align: center">
                        <tr>
                            <th class="enc" scope="row">xx</th>
                            <td>1234567890</td>
                            <td>Jarlin Fonseca Bermón</td>
                            <td>0123456789</td>
                            <td>jarlinmanager@ufps.edu.co</td>
                        </tr>
                    </tbody>
                </table>
                <!-- Cierre div tabla -->
            </div>

            <!--Datos del Vehículo -->
            <div class="table-responsive table-style" >
                <h4>Datos del Vehículo</h4> <br>
                <table  class="table table-bordered table-striped table-hover" >
                    <thead class="table-secondary">
                        <tr>
                            <th class="enc" scope="col">N°</th>
                            <th class="enc" scope="col">Marca</th>
                            <th class="enc" scope="col">Clase</th>
                            <th class="enc" scope="col">Color</th>
                            <th class="enc" scope="col">Modelo</th>
                            <th class="enc" scope="col">Carrosería</th>
                            <th class="enc" scope="col">Cilindraje</th>
                            <th class="enc" scope="col">Kilometraje</th>
                            <th class="enc" scope="col">Acciones</th>
                        </tr>
                    </thead>
                    <tbody style="text-align: center">
                        <tr>
                            <th class="enc" scope="row">xx</th>
                            <td>Marca xxxxxx</td>
                            <td>Clase xxxxxx</td>
                            <td>Color xxxxx</td>
                            <td>Modelo xxxxxx</td>
                            <td>Carrosería xxxxx</td>
                            <td>Cilindraje xxxxxx</td>
                            <td>Kilometraje xxxx</td>
                            <td>
                                <div class="icons-acciones">
                                    <div>
                                        <i class="fas fa-edit " data-bs-toggle="modal" data-bs-target="#modal2"></i>Editar

                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <th class="enc" scope="row">xx</th>
                            <td>Marca xxxxxx</td>
                            <td>Clase xxxxxx</td>
                            <td>Color xxxxx</td>
                            <td>Modelo xxxxxx</td>
                            <td>Carrosería xxxxx</td>
                            <td>Cilindraje xxxxxx</td>
                            <td>Kilometraje xxxx</td>
                            <td>
                                <div class="icons-acciones">
                                    <div>
                                        <i class="fas fa-edit" data-bs-toggle="modal" data-bs-target="#modal2"></i>Editar
                                    </div>
                                </div>
                            </td>
                        </tr>

                    </tbody>
                </table>
                <!-- Cierre div tabla -->
            </div>

            <!--Historial de Servicios -->
            <div class="table-responsive table-style" >
                <h4>Historial de Servicios</h4><br>
                <table id="example" class="table table-bordered table-striped table-hover" >
                    <thead class="table-secondary">
                        <tr>
                            <th class="enc" scope="col">No</th>
                            <th class="enc" scope="col">Servicio</th>
                            <th class="enc" scope="col">Productos</th>
                            <th class="enc" scope="col">Total</th>

                        </tr>
                    </thead>
                    <tbody style="text-align: center">
                        <tr>
                            <th class="enc" scope="row">1</th>
                            <td>Cambio de Aceite</td>
                            <td>Aceite1 Filtro2 Aditivo</td>
                            <td>1234567890</td>

                        </tr>
                        <tr>
                            <th class="enc" scope="row">2</th>
                            <td>Cambio de Llantas</td>
                            <td>Llantas Filtros</td>
                            <td>1234567890</td>

                        </tr>
                        <tr>
                            <th class="enc" scope="row">3</th>
                            <td>Cambio de Filtro</td>
                            <td>Filtro</td>
                            <td>1234567890</td>

                        </tr>
                        <tr>
                            <th class="enc" scope="row">4</th>
                            <td>Cambio de Carro XD</td>
                            <td>Carro nuevo</td>
                            <td>1234567890</td>

                        </tr>
                        <tr>
                            <th class="enc" scope="row">5</th>
                            <td>Aceite</td>
                            <td>Yoel Castilla</td>
                            <td>Another Oil</td>
                        </tr>
                    </tbody>
                </table>
                <!-- Cierre div tabla -->
            </div>

        </section>


        <!--Modal de Vehículos-->

        <div class="modal fade" id="modal2" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg ">
                <div class="modal-content">
                    <div class="modal-header" align-content="center">
                        <h5  class="modal-title" id="exampleModalLabel">Editar los datos del Vehículo</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        
                        <form>         

                        <div class="row g-3">
                            <div class="col">
                                <label for="marca" class="form-label">Marca del Vehículo</label>
                                <input type="text" class="form-control"  aria-label="Marca" required>
                            </div>
                            <div class="col">
                                <label for="clase" class="form-label">Clase de Vehículo</label>
                                <input type="text" class="form-control"  aria-label="Clase" required>
                            </div>
                            <div class="col">
                                <label for="color" class="form-label">Color del Vehículo</label>
                                <input type="text" class="form-control"  aria-label="Color" required>
                            </div>
                        </div>
                        <br>
                        <div class="row g-3">

                            <div class="col">
                                <label for="modelo" class="form-label">Modelo del Vehículo</label>
                                <input type="text" class="form-control"  aria-label="Modelo" required>
                            </div>
                        </div>
                        <br>
                        <div class="row g-3">
                            <div class="col">
                                <label for="carroseria" class="form-label">Carrosería del Vehículo</label>
                                <input type="text" class="form-control"  aria-label="Carrosería" required>
                            </div>
                            <div class="col">
                                <label for="cilindraje" class="form-label">Cilindraje del Vehículo</label>
                                <input type="text" class="form-control"  aria-label="Cilindraje" required>
                            </div>
                            <div class="col">
                                <label for="kilometraje" class="form-label">Kilometraje del Vehículo</label>
                                <input type="text" class="form-control"  aria-label="Kilometraje" required>
                            </div>
                        </div>
                        <br>
                        
                         <div class="modal-footer mt-2 " id="foterM">
                              <button type="submit" class="boton3">Guardar Cambios</button>
                                <button type="button" class="boton2" data-bs-dismiss="modal">Cancelar</button>
                               
                            </div>
                         </form>

                    </div>
                   
                </div>
            </div>
        </div>

        <!--Fin de Modal de Servicios -->

        <script src="<%=basePath%>/js/menuAdministrador.js"></script>
        <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
        <script src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.11.3/js/dataTables.bootstrap5.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>


        <script>
            $(document).ready(function () {
                $('#example').DataTable({

                    "language": {
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
            });
        </script>

    </body>
</html>