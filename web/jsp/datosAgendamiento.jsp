

<%@page import="DTO.Vehiculo"%>
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
        <script src="https://smtpjs.com/v3/smtp.js"></script>

        <link href="<%=basePath%>css/menuAdministrador.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="<%=basePath%>css/datosAgendamiento" />


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
                    <span class="links_name">Reportes y Estadísticas</span>
                  </a>
                  <span class="tooltip">Reportes y Estadísticas</span>
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
                    <h1 style="color:blue">Cliente: <%=request.getSession().getAttribute("usuarioCliente").toString()%></h1> 
                </div>
                <form action="<%=basePath%>CargarAddVehiculo.do" method="GET">
                    <div class="boton">
                        <input style = "display: none" name = "cedula" value="<%=request.getSession().getAttribute("IdCliente")%>"> 
                        <button class="btn btn-primary btn-lg" type="submit" value="Regresar" style="color: white;"> Añadir Vehiculo </button>
                    </div>
                </form>
            </div>
            <div class="main" >
                <section>
                    <div class="row">
                        <div class="container shadow p-4 mb-10 bg-body rounded-10 m-5 col-11">
                            <h3 style="display: flex; align-items: center">Datos de la cita agendada por el cliente</h3>
                            <hr style="color:#000" size="1" width="100%">
                         <div class = "row">
                            <div class="col-md-4">
                                <div class="div">
                                    <h5>No. Placa: <i style='color:blue'><%=request.getSession().getAttribute("idPlacaCita")%></i></h5>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="div">
                                    <h5>Modelo: <i style='color:blue'><%=request.getSession().getAttribute("idModeloCita")%></i></h5>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="div">
                                    <h5>Marca: <i style='color:blue'><%=request.getSession().getAttribute("idMarcaCita")%></i></h5>
                                </div>
                            </div>
                            <hr style="color:#000" size="1" width="100%">
                            <div class="col-md-4">
                                <div class="div">
                                    <h5>Año: <i style='color:blue'><%=request.getSession().getAttribute("idAnioCita")%></i></h5>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="div">
                                    <h5>Kilometraje: <i style='color:blue'><%=request.getSession().getAttribute("idKilometraCita")%></i></h5>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="div">
                                    <h5>Descripción <i style='color:blue'><%=request.getSession().getAttribute("idDescripcionCita")%></i></h5>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    </div>
                </section>

                <form class="row " name="formdatos" action="<%=basePath%>MostrarServiProduAdmin.do" method="POST" > 
                    <div class="row">
                        <div class="col-4">
                            <!-- Select Vehiculo -->
                             <section>
                                <div class="container shadow p-4 mb-10 bg-body rounded-10 ms-5">
                                    <div id="vehiculo"  class="col-12">
                                        <h2>Escoge el vehiculo</h2>
                                    </div>        
                                <br><br>
                                    <div class="col-12">
                                        <select id="inputState" class="form-select" required>
                                            <option selected value="">Seleccione un vehiculo</option>
                                            <%=request.getSession().getAttribute("getVehiculo").toString()%>
                                        </select>
                                    </div>
                                </div>
                            </section>
                        </div>
                        <div class="col-7">
                            <!-- Vehiculo Datos -->
                            <section>
                                <div class="container shadow p-4 mb-10 ms-5 bg-body rounded-10 m-1 h-80 w-55">
                                    <div id="datos"  class="col-md-6">
                                        <h2>Datos del agendamiento</h2>
                                    </div>        
                                    <br>
                                    <div class="row">
                                        <div class="col-4">
                                            <div class="divs">
                                                <input hidden name="placa" value="" id="inputPlaca"/>
                                                <h5>No. Placa:<template id = "placaV" class = "tmp"><b><h5 class='msg' style='color:blue'></h5></b></template></h5>
                                            </div>
                                        </div>
                                        <div class="col-4">
                                            <div class="divs">
                                                <h5>Modelo: <template id = "modeloV" class = "tmp"><b><h5 class='msg' style='color:blue'></h5></b></template></h5>
                                            </div>
                                        </div>
                                        <div class="col-4">
                                            <div class="divs">
                                                <input hidden name="km" value="" id="inputkm"/>
                                                <h5>Kilometraje: <template id = "kmV" class = "tmp"><b><h5 class='msg' style='color:blue'></h5></b></template></h5>
                                            </div>
                                        </div>
                                    </div>

                                    <hr style="color:#000" size="1" width="100%">
                                    <div class="row">
                                        <div class="col-5">
                                            <div class="divs">
                                                <h5>Marca: <template id = "marcaV" class = "tmp"><b><h5 class='msg' style='color:blue'></h5></b></template></h5>
                                            </div>
                                        </div>
                                        <div class="col-5">
                                            <div class="divs" > 
                                                <h5> Tipo de Vehiculo: <template id = "anioV" class = "tmp"><b><h5 class='msg' style='color:blue'></h5></b></template></h5>
                                            </div>
                                        </div>
                                    </div>
                                    <hr style="color:#000" size="1" width="100%">
                                    <div class="row">
                                        <div class="divsb">
                                            <input class="btn btn-primary btn-lg"  type="submit" value="Continuar">
                                        </div>
                                    </div>                            
                                </div>
                        </section>
                        </div>
                    </div>
                </form>
            </div>

        </section>

        <script src="<%=basePath%>/js/menuAdministrador.js"></script>
        <script src="<%=basePath%>/js/datosAgendamiento.js"></script>
        <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
        <script src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.11.3/js/dataTables.bootstrap5.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>


        <script>
            var vehiculos = '<%=request.getSession().getAttribute("vehiculos").toString()%>'.split(";");
            datosJSP(vehiculos);
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