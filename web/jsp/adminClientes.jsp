
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
    </head>
    <body onload="sesion('<%=request.getSession().getAttribute("usuario")%>')">

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
                    <a href="#">
                        <i class="far fa-calendar-alt"></i>
                        <span class="links_name">Agendamientos</span>
                    </a>
                    <span class="tooltip">Agendamientos</span>
                </li>
                <li>
                    <a href="<%=basePath%>admClientes.jsp">
                        <i class="icon fas fa-user"></i>
                        <span class="links_name">Clientes</span>
                    </a>
                    <span class="tooltip">Clientes</span>
                </li>
                <li>
                    <a href="#">
                        <i class="fas fa-user-cog"></i>
                        <span class="links_name">Servicios</span>
                    </a>
                    <span class="tooltip">Servicios</span>
                </li>
                <li>
                    <a href="#">
                        <i class="fas fa-shopping-cart"></i>
                        <span class="links_name">Productos</span>
                    </a>
                    <span class="tooltip">Productos</span>
                </li>
                <!-- <li>
                  <a href="#">
                    <i class="fas fa-chart-pie"></i>
                    <span class="links_name">Reportes</span>
                  </a>
                  <span class="tooltip">Reportes</span>
                </li> -->
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
                    <h1>Lista de Clientes</h1>
                </div>

                <div class="boton">
                    <button type="button" class="btn btn-primary btn-lg">Añadir cliente</button>
                </div>
            </div>

            <div class="table-responsive table-style">
                <table id="example" class="table table-bordered table-striped table-hover">
                    <thead class="table-secondary">
                        <tr>
                            <th class="enc" scope="col">No</th>
                            <th class="enc" scope="col">Nombre</th>
                            <th class="enc" scope="col">Cédula</th>
                            <th class="enc" scope="col">Celular</th>
                            <th class="enc" scope="col">Correo Electronico</th>
                            <th class="enc" scope="col">Ficha Técnica</th>
                            <th class="enc" scope="col">Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <th class="enc" scope="row">1</th>
                            <td>Sebastian Casadiegos</td>
                            <td>1000718165</td>
                            <td>3174535149</td>
                            <td>casadiegosgomezjs@ufps.edu.co</td>
                            <td> <a href="<%=basePath%>jsp/serviciosUsuAdmin.jsp"> <img src="<%=basePath%>img/lupa.png" style="display: block; width: 30px; height: 30px;            margin:auto;"/>
                                </a></td>
                            <!-- Acciones: editar y cancelar. -->
                            <td>
                                <div class="icons-acciones">
                                    <div>
                                        <i class="fas fa-edit"></i>
                                    </div>
                                    <div>
                                        <i class="fas fa-trash-alt"></i>
                                    </div>      
                                </div>
                            </td>
                        </tr>

                        <tr>
                            <th class="enc" scope="row">2</th>
                            <td>Samantha Zamora</td>
                            <td>1000718165</td>
                            <td>3174535149</td>
                            <td>samanthaelianaza@ufps.edu.co</td>
                            <td> <a href="<%=basePath%>jsp/serviciosUsuAdmin.jsp"> <img src="<%=basePath%>img/lupa.png" style="display: block; width: 30px; height: 30px;            margin:auto;"/>
                                </a></td>
                            <!-- Acciones: editar y cancelar. -->
                            <td>
                                <div class="icons-acciones">
                                    <div>
                                        <i class="fas fa-edit"></i>
                                    </div>
                                    <div>
                                        <i class="fas fa-trash-alt"></i>
                                    </div>      
                                </div>
                            </td>
                        </tr>

                        <tr>
                            <th class="enc" scope="row">3</th>
                            <td>Yoel Castilla</td>
                            <td>1000718165</td>
                            <td>3174535149</td>
                            <td>junioryoelco@ufps.edu.co</td>
                            <td> <a href="<%=basePath%>jsp/serviciosUsuAdmin.jsp"> <img src="<%=basePath%>img/lupa.png" style="display: block; width: 30px; height: 30px;            margin:auto;"/>
                                </a></td>
                            <!-- Acciones: editar y cancelar. -->
                            <td>
                                <div class="icons-acciones">
                                    <div>
                                        <i class="fas fa-edit"></i>
                                    </div>
                                    <div>
                                        <i class="fas fa-trash-alt"></i>
                                    </div>      
                                </div>
                            </td>
                        </tr>

                        <tr>
                            <th class="enc" scope="row">4</th>
                            <td>Susana Rojas</td>
                            <td>1000718165</td>
                            <td>3174535149</td>
                            <td>susanart@ufps.edu.co</td>
                            <td> <a href="<%=basePath%>jsp/serviciosUsuAdmin.jsp"><img src="<%=basePath%>img/lupa.png" style="display: block; width: 30px; height: 30px;            margin:auto;"/>
                                </a></td>
                            <!-- Acciones: editar y cancelar. -->
                            <td>
                                <div class="icons-acciones">
                                    <div>
                                        <i class="fas fa-edit"></i>
                                    </div>
                                    <div>
                                        <i class="fas fa-trash-alt"></i>
                                    </div>      
                                </div>
                            </td>
                        </tr>

                        <tr>
                            <th class="enc" scope="row">5</th>
                            <td>Jeferson  Rodriguez Ramirez</td>
                            <td>1000718165</td>
                            <td>3174535149</td>
                            <td>jefersonr@ufps.edu.co</td>
                            <td> <a href="<%=basePath%>jsp/serviciosUsuAdmin.jsp"> <img src="<%=basePath%>img/lupa.png" style="display: block; width: 30px; height: 30px;            margin:auto;"/>
                                </a></td>
                            <!-- Acciones: editar y cancelar. -->
                            <td>
                                <div class="icons-acciones">
                                    <div>
                                        <i class="fas fa-edit"></i>
                                    </div>
                                    <div>
                                        <i class="fas fa-trash-alt"></i>
                                    </div>      
                                </div>
                            </td>
                        </tr>

                        <tr>
                            <th class="enc" scope="row">6</th>
                            <td>Jarlin Fonseca</td>
                            <td>1000718165</td>
                            <td>3174535149</td>
                            <td>jarlinf@ufps.edu.co</td>
                            <td> <a href="<%=basePath%>jsp/serviciosUsuAdmin.jsp"> <img src="<%=basePath%>img/lupa.png" style="display: block; width: 30px; height: 30px;            margin:auto;"/>
                                </a></td>
                            <!-- Acciones: editar y cancelar. -->
                            <td>
                                <div class="icons-acciones">
                                    <div>
                                        <i class="fas fa-edit"></i>
                                    </div>
                                    <div>
                                        <i class="fas fa-trash-alt"></i>
                                    </div>      
                                </div>
                            </td>
                        </tr>

                        <tr>
                            <th class="enc" scope="row">7</th>
                            <td>Cristian Medina</td>
                            <td>1000718165</td>
                            <td>3174535149</td>
                            <td>cristianmanuelmp@ufps.edu.co</td>
                            <td> <a href="<%=basePath%>jsp/serviciosUsuAdmin.jsp"> <img src="<%=basePath%>img/lupa.png" style="display: block; width: 30px; height: 30px;            margin:auto;"/>
                                </a></td>
                            <!-- Acciones: editar y cancelar. -->
                            <td>
                                <div class="icons-acciones">
                                    <div>
                                        <i class="fas fa-edit"></i>
                                    </div>
                                    <div>
                                        <i class="fas fa-trash-alt"></i>
                                    </div>      
                                </div>
                            </td>
                        </tr>

                        <tr>
                            <th class="enc" scope="row">8</th>
                            <td>Jeferson  Rodriguez Ramirez</td>
                            <td>1000718165</td>
                            <td>3174535149</td>
                            <td>jefersonr@ufps.edu.co</td>
                            <td> <a href="<%=basePath%>jsp/serviciosUsuAdmin.jsp"> <img src="<%=basePath%>img/lupa.png" style="display: block; width: 30px; height: 30px;            margin:auto;"/>
                                </a></td>
                            <!-- Acciones: editar y cancelar. -->
                            <td>
                                <div class="icons-acciones">
                                    <div>
                                        <i class="fas fa-edit"></i>
                                    </div>
                                    <div>
                                        <i class="fas fa-trash-alt"></i>
                                    </div>      
                                </div>
                            </td>
                        </tr>

                        <tr>
                            <th class="enc" scope="row">9</th>
                            <td>Jarlin Fonseca</td>
                            <td>1000718165</td>
                            <td>3174535149</td>
                            <td>jarlinf@ufps.edu.co</td>
                            <td> <a href="<%=basePath%>jsp/serviciosUsuAdmin.jsp"><img src="<%=basePath%>img/lupa.png" style="display: block; width: 30px; height: 30px;            margin:auto;"/>
                                </a></td>
                            <!-- Acciones: editar y cancelar. -->
                            <td>
                                <div class="icons-acciones">
                                    <div>
                                        <i class="fas fa-edit"></i>
                                    </div>
                                    <div>
                                        <i class="fas fa-trash-alt"></i>
                                    </div>      
                                </div>
                            </td>
                        </tr>

                        <tr>
                            <th class="enc" scope="row">1</th>
                            <td>Cristian Medina</td>
                            <td>1000718165</td>
                            <td>3174535149</td>
                            <td>cristianmanuelmp@ufps.edu.co</td>
                            <td> <a href="<%=basePath%>jsp/serviciosUsuAdmin.jsp"> <img src="<%=basePath%>img/lupa.png" style="display: block; width: 30px; height: 30px;            margin:auto;"/>
                                </a></td>
                            <!-- Acciones: editar y cancelar. -->
                            <td>
                                <div class="icons-acciones">
                                    <div>
                                        <i class="fas fa-edit"></i>
                                    </div>
                                    <div>
                                        <i class="fas fa-trash-alt"></i>
                                    </div>      
                                </div>
                            </td>
                        </tr>

                        <tr>
                            <th class="enc" scope="row">1</th>
                            <td>Cristian Medina</td>
                            <td>1000718165</td>
                            <td>3174535149</td>
                            <td>cristianmanuelmp@ufps.edu.co</td>
                            <td> <a href="<%=basePath%>jsp/serviciosUsuAdmin.jsp"> <img src="<%=basePath%>img/lupa.png" style="display: block; width: 30px; height: 30px;            margin:auto;"/>
                                </a></td>
                            <!-- Acciones: editar y cancelar. -->
                            <td>
                                <div class="icons-acciones">
                                    <div>
                                        <i class="fas fa-edit"></i>
                                    </div>
                                    <div>
                                        <i class="fas fa-trash-alt"></i>
                                    </div>      
                                </div>
                            </td>
                        </tr>

                    </tbody>
                    <!-- <tfoot>
                        <tr>
                          <th class="enc" scope="col">No</th>
                          <th class="enc" scope="col">Nombre</th>
                          <th class="enc" scope="col">Cédula</th>
                          <th class="enc" scope="col">Celular</th>
                          <th class="enc" scope="col">Correo Electronico</th>
                          <th class="enc" scope="col">Ficha Técnica</th>
                          <th class="enc" scope="col">Acciones</th>
                        </tr>
                    </tfoot> -->
                </table>

                <!-- <div class="boton">
                    <button type="button" class="btn btn-primary btn-lg">Añadir producto</button>
                </div> -->
                <!-- Cierre div tabla -->
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

    </body>
</html>