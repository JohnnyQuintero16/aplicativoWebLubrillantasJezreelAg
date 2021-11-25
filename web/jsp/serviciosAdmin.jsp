<%-- 
    Document   : serviciosAdmin
    Created on : 20/11/2021, 04:41:55 PM
    Author     : Jarlin
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="DTO.Servicio"%>
<%@page import="java.util.List"%>
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


        <link href="<%=basePath%>css/serviciosAdmin.css" rel="stylesheet" type="text/css"/>
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
                                <span class="links_name"><%=request.getSession().getAttribute("nameUser") %></span>
                            </p>
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
                        <span class="links_name" >Productos</span>
                    </a>
                    <span class="tooltip">Productos</span>
                </li>
                <!--   <li>
                    <a href="#">
                      <i class="fas fa-chart-pie"></i>
                      <span class="links_name">Reportes</span>
                    </a>
                    <span class="tooltip">Reportes</span>
                  </li> -->


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
                    <h1>Lista de servicios</h1>
                </div>

                <div class="boton">
                    <button type="button" class="btn btn-primary btn-lg" data-bs-toggle="modal" data-bs-target="#modal1">Añadir servicio</button>
                </div>

            </div>

            <%

                List<Servicio> servicios = new ArrayList<Servicio>();
                       if((request.getSession().getAttribute("servicios"))!=null){
                       
                       servicios = (List<Servicio>) (request.getSession().getAttribute("servicios"));}
                       
          int i=1;

            %>

            <div class="table-responsive table-style">
                <table id="example" class="table table-bordered table-striped table-hover">

                    <thead class="table-secondary">
                        <tr>
                            
                            <th class="enc" style="width: 6%" scope="col">No</th>
                            <th style="display: none"class="enc" style="width: 6%" scope="col">Id</th>
                            <th class="enc" style="width: 19%" scope="col">Nombre</th>
                            <th class="enc" style="width: 34%" scope="col">Descripción</th>
                            <th class="enc" style="width: 32%" scope="col">ImagenURL</th>
                            <th class="enc" style="width:  9%" scope="col">Duración-min</th>
                            <th class="enc" style="width: 10%" scope="col">Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                       
                        <% for (Servicio s : servicios) {%>
                        <tr>
                            <th class="enc" scope="row"><%=i%></th>
                            <td style="display: none" ><%=s.getId()%></td>S
                            <td ><%=s.getNombre()%></td>
                            <td><%= s.getDescripcion()%></td>
                            <td><%=s.getImgUrl()%></td>
                            <td><%=s.getDuracion()%></td>
                            <!-- Acciones: editar y cancelar. -->
                            <td>
                                <div class="icons-acciones">
                                    <div>

                                        <i class="fas fa-edit" id="e1" data-bs-toggle="modal" data-bs-target="#modal2" data-bs-whatever=""></i>
                                    </div>
                                    <div>
                                        <i class="fas fa-trash-alt" data-bs-toggle="modal" data-bs-target="#modal3"></i>

                                    </div>      
                                </div>
                            </td>
                        </tr>
                        <%i++;
                            }%>
                    </tbody>


                </table>

                <!-- Cierre div tabla -->
            </div>

        </section>


        <!-- ventana modal -->
        <div class="modal fade" tabindex="-1" role="dialog" id="modal1" aria-labelledby="modal1" aria-hidden="true">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header justify-content-center align-items-center">

                        <h2 class="modal-title">Añadir Servicio</h2>
                        <!--       <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close">
                              </button> -->
                    </div>
                    <div class="modal-body ">


                        <form action="<%=basePath%>AdminAddServicio.do" method="POST">
                            <div class="row text-center m-3">

                                <div class="col-md-6">

                                    <div class="mb-3 ">
                                        <label for="exampleInputNombre" class="form-label">Nombre</label>
                                        <input type="text" class="form-control " id="exampleInputNombre" name="nombre" required>

                                    </div>


                                </div>

                                <div class="col-md-6">

                                    <div class="mb-3">
                                        <label for="exampleInputDuracion" class="form-label">Duración</label>
                                        <input type="number" class="form-control" id="exampleInputDuracion"  name="duracion" required>
                                    </div>

                                </div>



                                <div class="mb-3">
                                    <label for="exampleInputImagenURL" class="form-label">ImagenURL</label>
                                    <input type="text" class="form-control" id="exampleInputImagenURL" name ="img" required>
                                </div>





                                <div class="mb-3">
                                    <label for="exampleDescripcion" class="form-label">Descripción</label>
                                    <textarea class="form-control" id="exampleInputDescripcion" rows="3" name="descripcion" required></textarea>
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

                        <h2 class="modal-title">Editar Servicio</h2>
                        <!--       <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close">
                              </button> -->
                    </div>
                    <div class="modal-body ">


                        <form action="<%=basePath%>/ActualizarServicio.do" method="GET">
                            <div class="row text-center m-3">

                                <div class="col-md-6">

                                    <div class="mb-3" style="display: none">
                                        <label for="exampleInputNombre" class="form-label">ID</label>
                                        <input type="text" class="form-control " id="exampleInputNombre" name="id" required>

                                    </div>
                                    <div class="mb-3 ">
                                        <label for="exampleInputNombre" class="form-label">Nombre</label>
                                        <input type="text" class="form-control " id="exampleInputNombre" name="nombre" required>

                                    </div>


                                </div>



                                <div class="col-md-6">

                                    <div class="mb-3">
                                        <label for="exampleInputDuracion" class="form-label">Duración</label>
                                        <input type="number" class="form-control" id="exampleInputDuracion"  name="duracion" value="30" required>
                                    </div>

                                </div>



                                <div class="mb-3">
                                    <label for="exampleInputImagenURL" class="form-label">ImagenURL</label>
                                    <input type="text" class="form-control" id="exampleInputImagenURL" name="img" required>
                                </div>





                                <div class="mb-3">
                                    <label for="exampleDescripcion" class="form-label">Descripción</label>
                                    <textarea class="form-control" id="exampleInputDescripcion" rows="3" name="descripcion" required></textarea>
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


        <!-- Modal para el botón eliminar-->
        <div class="modal fade" id="modal3" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog ">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Eliminar Servicio</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        ¿Estás seguro de eliminar el Servicio?
                    </div>
                    <div class="modal-footer">
                        <form action="<%=basePath%>/EliminarServicio.do" method="GET">

                            <div class="mb-3" >
                              
                                <input type="text" class="form-control " id="exampleInputNombre" name="id"  style="display: none ">

                            </div>
                            
                            <button type="button" class="boton2" data-bs-dismiss="modal">Cancelar</button>
                            <button type="submit" class="boton3">Eliminar</button>

                        </form>
                    </div>
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

            var modalEditarCliente = document.getElementById('modal2');
            modalEditarCliente.addEventListener('show.bs.modal', (e) => {
                var btn = e.relatedTarget.valueOf().parentNode;
                li = btn.parentNode;
                li = li.parentNode;
                li = li.parentNode;
                datos = li.querySelectorAll("td");
                modalBodyInput = modalEditarCliente.querySelector('.modal-body').querySelectorAll('input');
                textA = modalEditarCliente.querySelector('.modal-body').querySelectorAll('textarea');
                modalBodyInput[0].value = datos[0].innerHTML;
                modalBodyInput[1].value = datos[1].innerHTML;
                modalBodyInput[2].value = datos[4].innerHTML;
                modalBodyInput[3].value = datos[3].innerHTML;
                textA[0].value = datos[2].innerHTML;

            });

            var modalEliminarCliente = document.getElementById('modal3');
            modalEliminarCliente.addEventListener('show.bs.modal', (e) => {
                var btn = e.relatedTarget.valueOf().parentNode;
                li = btn.parentNode;
                li = li.parentNode;
                li = li.parentNode;
                datos = li.querySelectorAll("td");
                modalBodyInput = modalEliminarCliente.querySelector('.modal-footer').querySelectorAll('input');
               console.log(modalBodyInput);
                modalBodyInput[0].value = datos[0].innerHTML;


            });
        </script>

    </body>
</html>