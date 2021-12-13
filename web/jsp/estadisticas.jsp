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
        <title>Lubrillantas Jezreel AG - Reportes y Estadísticas</title>


        <link href="<%=basePath%>css/serviciosAdmin.css" rel="stylesheet" type="text/css"/>
        <link href="<%=basePath%>css/menuAdministrador.css" rel="stylesheet" type="text/css"/>
        <link href="<%=basePath%>css/estadisticas.css" rel="stylesheet" type="text/css"/>

        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <script type="text/javascript">
            
            google.charts.load("current", {packages: ["corechart"]});
            google.charts.setOnLoadCallback(drawChartCitas);
            google.charts.setOnLoadCallback(drawChartCalificaciones);
            
            //Torta para las citas 
            function drawChartCitas() {
                let atendidas = <%= Integer.parseInt(request.getSession().getAttribute("cAtendidas").toString()) %>
                let canceladas = <%= Integer.parseInt(request.getSession().getAttribute("canceladas").toString()) %>
                let = ma = [['Estado de Cita', 'Número'],['Atendidas', atendidas],['Canceladas', canceladas]]
                var data = google.visualization.arrayToDataTable(ma);

                var options = {
                    title: 'Relación de Citas ',
                    is3D: true
                };

                var chart = new google.visualization.PieChart(document.getElementById('piechartCitas'));
                chart.draw(data, options);
            }
//Torta para la Calificación de los Servicios

            function drawChartCalificaciones() {
                let cali = '<%=request.getSession().getAttribute("calificaArray")%>'.split(",");
                var data = google.visualization.arrayToDataTable([
                    ['Calificación', 'Número'],
                    ['1', parseInt(cali[0])],
                    ['2', parseInt(cali[1])],
                    ['3', parseInt(cali[2])],
                    ['4', parseInt(cali[3])],
                    ['5', parseInt(cali[4])]
                ]);

                var options = {
                    title: 'Calificación del Servicio',
                    is3D: true,
                };

                var chart = new google.visualization.PieChart(document.getElementById('piechartCalificaciones'));
                chart.draw(data, options);
            }


//Gráfico de barras verticales para los servicios por meses

            function drawChartServicios() {
                // Define the chart to be drawn.
                let meses = '<%=request.getSession().getAttribute("mesesAtencion")%>'.split(",");
                var data = google.visualization.arrayToDataTable([
                    ['Mes', 'N° Atenciones'],
                    ['Enero', parseInt(meses[0])],
                    ['Febrero', parseInt(meses[1])],
                    ['Marzo', parseInt(meses[2])],
                    ['Abril', parseInt(meses[3])],
                    ['Mayo', parseInt(meses[4])],
                    ['Junio', parseInt(meses[5])],
                    ['Julio', parseInt(meses[6])],
                    ['Agosto', parseInt(meses[7])],
                    ['Septiembre', parseInt(meses[8])],
                    ['Octubre', parseInt(meses[9])],
                    ['Noviembre', parseInt(meses[10])],
                    ['Diciembre', parseInt(meses[11])]
                ]);

                var options = {title: 'Número de Servicios Realizados'};

                // Instantiate and draw the chart.
                var chart = new google.visualization.ColumnChart(document.getElementById('numServicios'));
                chart.draw(data, options);
            }
            google.charts.setOnLoadCallback(drawChartServicios);
        </script>

        <!-- Fuente de google: Open Sans - Regular 400 -->
        <link href="https://fonts.googleapis.com/css?family=Poppins:400,500,600,700&display=swap" rel="stylesheet">

        <!-- Boxicons CDN Link -->
        <link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"/>

        <!-- DataTable -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.0.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdn.datatables.net/1.11.3/css/dataTables.bootstrap5.min.css">
    </head>
    <body >

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
                    <a href="<%=basePath%>jsp/citasAdmin.jsp">
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
                <li>
                    <a href="<%=basePath%>ValoresEstadisticas.do">
                        <i class="fas fa-chart-pie"></i>
                        <span class="links_name" >Reportes y Estadísticas</span>
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

        <section class="home-section" style="background-color:white">
            <div class="title">

                <div class="titulo">
                    <h1>Estadísticas</h1>
                </div>

                <div class="boton">
                    <button type="button" class="btn btn-primary btn-lg" onclick="location.href='<%=basePath%>jsp/reportesAdmin.jsp'" > Regresar a Reportes</button>
                </div>

            </div>
            <div class="row g-3 graficas" >
                <div class="col-12 title-torta ">
                    <div>
                        <h5 > Gráfico de torta</h5>
                    </div>
                </div>
                <div class="col-md-6"  align-content="center">
                    <div id="piechartCitas" style="width: 100%; height: 400px;"></div>
                </div>
                <div class="col-md-6"  align-content="center">

                    <div id="piechartCalificaciones" style="width: 100%; height: 400px;"></div>
                </div>
                <div class="col-12 title-torta">
                    <div>
                        <h5 > Gráfico de Barras: Número de Atenciones Realizadas Mensualmente</h5>
                    </div>
                </div>
                <div class="col-12 ">
                    <div id = "numServicios" style = "width: 700px; height: 400px; margin: 0 auto">
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
                        "sProcessing": "Procesando..."
                    }

                }
                );
            });
        </script>
    </body>
</html>