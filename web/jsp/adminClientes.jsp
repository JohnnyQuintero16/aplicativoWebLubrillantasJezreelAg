
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
        <link rel="stylesheet" href="<%=basePath%>css/admClientes.css" />
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
                            <img src="<%=basePath%>img/user-admin.png" alt="Administrador">
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
                    <a href="<%=basePath%>/jsp/adminClientes.jsp">
                        <i class="icon fas fa-user"></i>
                        <span class="links_name">Clientes</span>
                    </a>
                    <span class="tooltip">Clientes</span>
                </li>
                <li>
                    <a href="<%=basePath%>/jsp/serviciosAdmin.jsp">
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
                    <button type="button" class="btn btn-primary btn-lg" data-bs-toggle="modal" data-bs-target="#modal1">Añadir cliente</button>
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
                            <th class="enc" scope="col">Direccion</th>
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
                            <td>Calle 7 #05-50 La Pastora</td>
                            <td> <a href="<%=basePath%>jsp/serviciosUsuAdmin.jsp"> <img src="<%=basePath%>img/lupa.png" style="display: block; width: 30px; height: 30px;            margin:auto;"/>
                                </a></td>
                            <!-- Acciones: editar y cancelar. -->
                            <td>
                                <div class="icons-acciones">
                                    <div>
                                        <i class="fas fa-edit" data-bs-toggle="modal" data-bs-target="#modal2"></i>
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
                            <td>Calle 9 #05-50 La Merced</td>
                            <td> <a href="<%=basePath%>jsp/serviciosUsuAdmin.jsp"> <img src="<%=basePath%>img/lupa.png" style="display: block; width: 30px; height: 30px;            margin:auto;"/>
                                </a></td>
                            <!-- Acciones: editar y cancelar. -->
                            <td>
                                <div class="icons-acciones">
                                    <div>
                                        <i class="fas fa-edit"></i>
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
                            <td>Calle 700 #05-50 El Poblado</td>
                            <td> <a href="<%=basePath%>jsp/serviciosUsuAdmin.jsp"> <img src="<%=basePath%>img/lupa.png" style="display: block; width: 30px; height: 30px;            margin:auto;"/>
                                </a></td>
                            <!-- Acciones: editar y cancelar. -->
                            <td>
                                <div class="icons-acciones">
                                    <div>
                                        <i class="fas fa-edit"></i>
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
                            <td>Calle 0 #05-50  Los Alpes</td>
                            <td> <a href="<%=basePath%>jsp/serviciosUsuAdmin.jsp"><img src="<%=basePath%>img/lupa.png" style="display: block; width: 30px; height: 30px;            margin:auto;"/>
                                </a></td>
                            <!-- Acciones: editar y cancelar. -->
                            <td>
                                <div class="icons-acciones">
                                    <div>
                                        <i class="fas fa-edit"></i>
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
                            <td>Calle 9 #05-50 La Divina</td>
                            <td> <a href="<%=basePath%>jsp/serviciosUsuAdmin.jsp"> <img src="<%=basePath%>img/lupa.png" style="display: block; width: 30px; height: 30px;            margin:auto;"/>
                                </a></td>
                            <!-- Acciones: editar y cancelar. -->
                            <td>
                                <div class="icons-acciones">
                                    <div>
                                        <i class="fas fa-edit"></i>
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
                            <td>Calle 5 #05-50 La Merced</td>
                            <td> <a href="<%=basePath%>jsp/serviciosUsuAdmin.jsp"> <img src="<%=basePath%>img/lupa.png" style="display: block; width: 30px; height: 30px;            margin:auto;"/>
                                </a></td>
                            <!-- Acciones: editar y cancelar. -->
                            <td>
                                <div class="icons-acciones">
                                    <div>
                                        <i class="fas fa-edit"></i>
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
                            <td>Calle 7 #05-50 La Junta</td>
                            <td> <a href="<%=basePath%>jsp/serviciosUsuAdmin.jsp"> <img src="<%=basePath%>img/lupa.png" style="display: block; width: 30px; height: 30px;            margin:auto;"/>
                                </a></td>
                            <!-- Acciones: editar y cancelar. -->
                            <td>
                                <div class="icons-acciones">
                                    <div>
                                        <i class="fas fa-edit"></i>
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

        <!-- ventana modal -->
        <div class="modal fade" tabindex="-1" role="dialog" id="modal1" aria-labelledby="modal1" aria-hidden="true">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header justify-content-center align-items-center">

                        <h2 class="modal-title">Añadir Cliente</h2>
                    </div>
                    <div class="modal-body ">


                        <form >
                            <div class="row text-center m-3">

                                <div class="col-md-6">

                                    <div class="mb-3 ">
                                        <label for="exampleInputNombre" class="form-label">Nombre</label>
                                        <input type="text" class="form-control " id="exampleInputNombre" required>

                                    </div>


                                </div>



                                <div class="col-md-6">

                                    <div class="mb-3">
                                        <label for="exampleInputCed" class="form-label">Cédula</label>
                                        <input type="number" class="form-control" id="exampleInputCed" required>
                                    </div>

                                </div>


                                <div class="col-md-6">
                                    <div class="mb-3">
                                        <label for="exampleInputEmail" class="form-label">Correo electrónico</label>
                                        <input type="text" class="form-control" id="exampleInputEmail" required>
                                    </div>
                                </div>

                                <div class="col-md-6">
                                    <div class="mb-3">
                                        <label for="exampleInputCel" class="form-label">Celular</label>
                                        <input type="number" class="form-control" id="exampleInputCel" required>
                                    </div>
                                </div>




                                <div class="col-md-6">

                                    <div class="mb-3">
                                        <label for="exampleInputDirec" class="form-label">Dirección</label>
                                         <input type="text" class="form-control" id="exampleInputDirec" required>
                                    </div>

                                </div>
                                
                                 <div class="col-md-6">

                                    <div class="mb-3">
                                        <label for="exampleInputPass" class="form-label">Contraseña</label>
                                         <input type="password" class="form-control" id="exampleInputPass" required>
                                    </div>

                                </div>






                            </div>




                            <div class="modal-footer mt-2 " id="foterM">
                                <button type="button" class="boton2" data-bs-dismiss="modal">Cancelar</button>
                                <button type="submit" class="boton3">Guardar</button>
                            </div>


                        </form>


                    </div>
                    <!--  <div class="modal-footer" id="foterM">
                         <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                       <a href="#" class="btn" id="boton" type="button">Calificar servicio</a>
                     </div> -->
                </div>
            </div>
        </div>




        <!-- ventana modal de editar -->
        <div class="modal fade" tabindex="-1" role="dialog" id="modal2" aria-labelledby="modal2" aria-hidden="true">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header justify-content-center align-items-center">

                        <h2 class="modal-title">Editar Cliente</h2>
                        <!--       <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close">
                              </button> -->
                    </div>
                    <div class="modal-body ">


                        <form >
                            <div class="row text-center m-3">

                                <div class="col-md-6">

                                    <div class="mb-3 ">
                                        <label for="exampleInputNombre" class="form-label">Nombre</label>
                                        <input type="text" class="form-control " id="exampleInputNombre" value="Ricardo Fernandez" required>

                                    </div>


                                </div>



                                <div class="col-md-6">

                                    <div class="mb-3">
                                        <label for="exampleInputCed" class="form-label">Cédula</label>
                                        <input type="number" class="form-control" id="exampleInputCed" value="1005879654" required>
                                    </div>

                                </div>


                                <div class="col-md-6">
                                    <div class="mb-3">
                                        <label for="exampleInputEmail" class="form-label">Correo electrónico</label>
                                        <input type="text" class="form-control" id="exampleInputEmail" value="ricar@gmail.com" required>
                                    </div>
                                </div>

                                <div class="col-md-6">
                                    <div class="mb-3">
                                        <label for="exampleInputCel" class="form-label">Celular</label>
                                        <input type="number" class="form-control" id="exampleInputCel" value="3152546875" required>
                                    </div>
                                </div>




                                <div class="col-md-6">

                                    <div class="mb-3">
                                        <label for="exampleInputDirec" class="form-label">Dirección</label>
                                        <input type="text" class="form-control" id="exampleInputDirec" value="Calle 8 9-9194545656" required>
                                    </div>

                                </div>
                                
                                 <div class="col-md-6">

                                    <div class="mb-3">
                                        <label for="exampleInputPass" class="form-label">Contraseña</label>
                                         <input type="password" class="form-control" id="exampleInputPass" value="juanito757" required>
                                    </div>

                                </div>






                            </div>





                            <div class="modal-footer mt-2 " id="foterM">
                                <button type="button" class="boton2" data-bs-dismiss="modal">Cancelar</button>
                                <button type="submit" class="boton3">Guardar</button>
                            </div>


                        </form>


                    </div>
                    <!--  <div class="modal-footer" id="foterM">
                         <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                       <a href="#" class="btn" id="boton" type="button">Calificar servicio</a>
                     </div> -->
                </div>
            </div>
        </div>


        <script src="<%=basePath%>js/menuAdministrador.js"></script>
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