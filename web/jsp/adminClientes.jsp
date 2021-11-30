
<%@page import="java.util.List"%>
<%@page import="DTO.Persona"%>
<%@page import="DAO.PersonaDAO"%>
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
    <body onload="validarSesion('<%=request.getSession().getAttribute("msg")%>')">

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

            <%
                PersonaDAO p = new PersonaDAO();
                List<Persona> lista = p.read();
                if (lista.isEmpty()) {%>
            <div class = "container-fluid" style="display: flex; align-content: center; align-items: center;justify-content: center">
                <h1 style="color:#ff0000" align="center">En estos momentos no se encuentran registrados clientes!</h1>
            </div>
            <%} else {%>
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
                        <%
                            int i = 1;
                            for (Persona persona : lista) {
                            if(persona.getIdRol().getId() == 2){
                                String nombre = persona.getNombres().split(" ")[0] + " " + persona.getApellidos().split(" ")[0];   
                        %>
                        <tr>
                            <th class="enc" scope="row"><%=i%></th>
                            <td><%=nombre%></td>
                            <td><%=persona.getCedula()%></td>
                            <td><%=persona.getCelular()%></td>
                            <td><%=persona.getEmail()%></td>
                            <td><%=persona.getDirecccion()%></td>
                            <td style="text-align: center"> 
                                <form action = "<%=basePath%>/MostrarFichaTecnica.do" method="POST" >
                                    
                                    <input  style="display: none"type="text" class="form-control " value="<%=persona.getCedula()%>" id="exampleInputNombre" name="cedula" required>
                                    <button style=" border: none ;background-color: transparent" type="submit" > <img src="<%=basePath%>img/lupa.png" style="display: block; width: 30px; height: 30px;            margin:auto;"/>
                                    </button>
                                </form>
                            </td>
                            <!-- Acciones: editar y cancelar. -->
                            <td>
                                <div class="icons-acciones">
                                    <div>
                                        <button type="button" class="fas fa-edit" data-bs-toggle="modal" data-bs-target="#modal2" data-bs-whatever = "<%=persona.getContraseña()%>"></button>
                                    </div>

                                </div>
                            </td>
                        </tr>
                        <%i++;
                            }}%>
                    </tbody>
                </table>
            </div>
            <%}%>

        </section>

        <!-- ventana modal Añadir cliente-->
        <div class="modal fade" tabindex="-1" role="dialog" id="modal1" aria-labelledby="modal1" aria-hidden="true">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header justify-content-center align-items-center">
                        <h2 class="modal-title">Añadir Cliente</h2>
                    </div>
                    <div class="modal-body ">
                        <form action = "<%=basePath%>/adminAddCliente.do" method="POST" >
                            <div class="row text-center m-3">
                                <div class="col-md-6">
                                    <div class="mb-3 ">
                                        <label for="exampleInputNombre" class="form-label">Nombre Completo</label>
                                        <input type="text" class="form-control " id="exampleInputNombre" required name="nombre">
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="mb-3">
                                        <label for="exampleInputCed" class="form-label">Cédula</label>
                                        <input type="number" class="form-control" id="exampleInputCed" required name="cedula">
                                    </div>

                                </div>


                                <div class="col-md-6">
                                    <div class="mb-3">
                                        <label for="exampleInputEmail" class="form-label">Correo electrónico</label>
                                        <input type="text" class="form-control" id="exampleInputEmail" required name="email">
                                    </div>
                                </div>

                                <div class="col-md-6">
                                    <div class="mb-3">
                                        <label for="exampleInputCel" class="form-label">Celular</label>
                                        <input type="number" class="form-control" id="exampleInputCel" required name="celular">
                                    </div>
                                </div>
                                <div class="col-md-6">

                                    <div class="mb-3">
                                        <label for="exampleInputDirec" class="form-label">Dirección</label>
                                        <input type="text" class="form-control" id="exampleInputDirec" required name="direccion">
                                    </div>

                                </div>

                                <div class="col-md-6">

                                    <div class="mb-3">
                                        <label for="exampleInputPass" class="form-label">Contraseña</label>
                                        <input type="password" class="form-control" id="exampleInputPass" required name="clave">
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
        <div class="modal fade" tabindex="-1" role="dialog" id="modal2" aria-labelledby="modal2example" aria-hidden="true">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header justify-content-center align-items-center">

                        <h2 class="modal-title">Editar Cliente</h2>
                        <!--       <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close">
                              </button> -->
                    </div>
                    <div class="modal-body ">
                        <form action="<%=basePath%>adminUpdateCliente.do" method="GET">
                            <div class="row text-center m-3">
                                <div class="col-md-6">

                                    <div class="mb-3 ">
                                        <label for="exampleInputNombre" class="form-label">Nombre</label>
                                        <input type="text" class="form-control " id="recipient-name" name="nombre" required>

                                    </div>


                                </div>



                                <div class="col-md-6">

                                    <div class="mb-3">
                                        <label for="exampleInputCed" class="form-label">Cédula</label>
                                        <input type="number" class="form-control" id="exampleInputCed" name="cedula" readonly>
                                    </div>

                                </div>


                                <div class="col-md-6">
                                    <div class="mb-3">
                                        <label for="exampleInputEmail" class="form-label">Correo electrónico</label>
                                        <input type="text" class="form-control" id="exampleInputEmail" name="email" required>
                                    </div>
                                </div>

                                <div class="col-md-6">
                                    <div class="mb-3">
                                        <label for="exampleInputCel" class="form-label">Celular</label>
                                        <input type="number" class="form-control" id="exampleInputCel" name="celular" required>
                                    </div>
                                </div>




                                <div class="col-md-6">

                                    <div class="mb-3">
                                        <label for="exampleInputDirec" class="form-label">Dirección</label>
                                        <input type="text" class="form-control" id="exampleInputDirec" name="direccion" required>
                                    </div>

                                </div>

                                <div class="col-md-6">

                                    <div class="mb-3">
                                        <label for="exampleInputPass" class="form-label">Contraseña</label>
                                        <input type="password" class="form-control" id="exampleInputPass" name="clave" required>
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
           
            var modalEditarCliente = document.getElementById('modal2');
            modalEditarCliente.addEventListener('show.bs.modal', (e) => {
                var btn = e.relatedTarget.valueOf().parentNode;
                li = btn.parentNode;
                li = li.parentNode;
                li = li.parentNode;
                datos = li.querySelectorAll("td");
                console.log(datos);
                modalBodyInput = modalEditarCliente.querySelector('.modal-body').querySelectorAll('input');
                modalBodyInput[0].value = datos[0].innerHTML;//nombre
                modalBodyInput[1].value = datos[1].innerHTML;//cc
                modalBodyInput[2].value = datos[3].innerHTML;//email
                modalBodyInput[3].value = datos[2].innerHTML;//celular
                modalBodyInput[4].value = datos[4].innerHTML;//direccion
                modalBodyInput[5].value = e.relatedTarget.getAttribute('data-bs-whatever');//clave

            });
        
        </script>

    </body>
</html>