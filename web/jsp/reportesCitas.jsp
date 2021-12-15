<%-- 
Document   : reportesCitasnjsp
Created on : Dec 9, 2021, 5:16:51 PM
Author     : Jefersonrr
--%>

<%@page import="DAO.CitaDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DTO.Cita"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":"
                + request.getServerPort() + path + "/";

        List<Cita> citas = new ArrayList<Cita>();
        if (request.getSession().getAttribute("citas") != null) {
            citas = (List<Cita>) request.getSession().getAttribute("citas");
        }
        String nombreExcel = request.getSession().getAttribute("reporte").toString();

    %>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Lubrillantas Jezreel AG - Administración</title>

        <!-- Fuente de google: Open Sans - Regular 400 -->
        <link href="https://fonts.googleapis.com/css?family=Poppins:400,500,600,700&display=swap" rel="stylesheet">

        <!-- Boxicons CDN Link -->
        <link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />

        <!-- DataTable -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.0.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdn.datatables.net/1.11.3/css/dataTables.bootstrap5.min.css">

        <!-- boo -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">


        <!--Importar CSS y script del menú -->
        <link rel="stylesheet" href="<%=basePath%>/css/menuAdministrador.css" />
        <link rel="stylesheet" href="<%=basePath%>/css/admClientes.css" />
        <!-- links para exportar a excel -->

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
                    <h1>Reportes de citas</h1>
                </div>

                <div class="boton">
                    <a type="button" href="<%=basePath%>ValoresEstadisticas.do" class="btn btn-primary btn-lg">Regresar</a>
                </div>
            </div>

            <form action="<%=basePath%>/FiltrarReporteCitas.do" method="POST">
            <div class="container-fluid">
                <div class="row"> 
                    <!--select -->
                    
                    
                    <div class="col-3 ">
                        <select class="form-select" name="tipo" id="selectT">
                            <option selected value="0">Tipo de reporte</option>
                            <option value="ATENDIDO">Citas atendidas</option>
                            <option value="CANCELADA">Citas canceladas</option>
                            <option value="NO ATENDIDO">Citas No Atendidas</option>
                            <option value="EN PROCESO">Citas En Proceso</option>
                        </select>
                    </div>

                    <!--fecha ini -->
                    <div class="col-3">
                        <div class="row mb-3">
                            <label class="col-sm-2 col-form-label">Desde:</label>
                            <div class="col-sm-8">
                                <input id="inicial"  name="inicial"onclick="desabilitar()"type="date"  class="form-control">
                            </div>
                        </div>
                    </div>

                    <!--fecha fin -->
                    <div class="col-3">
                        <div class="row mb-3">
                            <label class="col-sm-2 col-form-label">Hasta:</label>
                            <div class="col-sm-8">
                                <input onclick="limitarFecha()" name="final"  id="final"type="date" class="form-control">
                            </div>
                        </div>
                    </div>

                    <!--fecha boton -->
                    <div class="col-3">
                        <button type="submit" class="btn btn-primary btn-lg">Consultar</button>
                    </div>
                    
                </div>
            </div>
            </form>

            <br>

            <div class="table-responsive table-style">
                <table  id="example" class="table table-bordered table-striped table-hover">
                    <thead class="table-secondary">
                        <tr>
                            <th class="enc" scope="col">Id</th>
                            <th class="enc" scope="col">Cédula</th>
                            <th class="enc" scope="col">Nombre</th>
                            <th class="enc" scope="col">Celular</th>
                            <th class="enc" scope="col">Correo Electronico</th>
                            <th class="enc" scope="col">Descripción</th>
                            <th class="enc" scope="col">Fecha</th>
                            <th class="enc" scope="col">Hora</th>
                            <th class="enc" scope="col">Estado</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%for (Cita c : citas) {%>
                        <tr>
                            <td ><%=c.getId()%></td>
                            <td><%=c.getIdPersona().getCedula()%></td>
                            <td><%=c.getIdPersona().getNombres()%></td>
                            <td><%=c.getIdPersona().getCelular()%></td>
                            <td><%=c.getIdPersona().getEmail()%></td>
                            <td><%=c.getDescripcion()%></td>
                            <td><%= c.formatoFecha(c.getFecha()).toString()%></td>
                            <td><%= c.formatoHora(c.getHora())%></td>
                            <td><%=c.getEstado()%> </td>
                            <!-- Acciones: editar y cancelar. -->

                        </tr>
                        <%}%>                      
                    </tbody>
                </table>
            </div>

            <br>
            <!-- reporte -->
            <style>
                .botonF{
                    margin-right: 35px;
                    margin-top: -20px;
                    margin-bottom: 20px;
                }
            </style> 
            
            <div class="botonF " align="right">
                  <button type="button" id="btn" class=" arreglo btn btn-primary btn-lg" data-bs-toggle="modal" data-bs-target="#modal1">Generar excel</button>   
            </div>
        </section>
        <div class="modal fade" tabindex="-1" role="dialog" id="modal1" aria-labelledby="modal1" aria-hidden="true">
            <div class="modal-dialog modal-lg" role="document">
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
                        <button type="button" onclick="generarExcel();" class="btn btn-success">
                            <i class="fas fa-file-excel"></i> Exportar datos a Excel
                        </button>

                    </div>
                    </form>
                </div>
            </div>
        </div>

        <script src="<%=basePath%>/js/menuAdministrador.js"></script>
        <script src="<%=basePath%>/js/generarExcelCitas.js"></script>
        <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
        <script src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.11.3/js/dataTables.bootstrap5.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>


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
        <script>


            modalBodyOptionTipo = document.querySelector('#selectT').querySelectorAll('option');
            
            
            <%
            String t ="";
            if(request.getSession().getAttribute("seleTipo")!=null){
                t =request.getSession().getAttribute("seleTipo").toString();
            }
            %>
            if(<%=!t.equals("")%>){
                
                for(var i=0;i< modalBodyOptionTipo.length;i++){
                   
                    if(modalBodyOptionTipo[i].value === "<%=t%>"){
                       
                        modalBodyOptionTipo[i].setAttribute("selected","");
                     <%request.getSession().setAttribute("seleTipo", null); %>
                            break;
                    }
                    
                }
                
            }
            <%
            String f1 ="";
            if(request.getSession().getAttribute("seleFecha1")!=null){
                f1 =request.getSession().getAttribute("seleFecha1").toString();
            }
            %>
                var inicial = document.querySelector('#inicial');
           
                console.log("<%=f1%>");
            if(<%=!f1.equals("")%>){
                
               
                   
                    inicial.value = "<%=f1%>";
                       <%request.getSession().setAttribute("seleFecha1", null); %> 
                       
                
            }
            
            <%
            String f2 ="";
            if(request.getSession().getAttribute("seleFecha2")!=null){
                f2 =request.getSession().getAttribute("seleFecha2").toString();
            }
            %>
               
            var final = document.querySelector('#final');
                console.log("<%=f2%>");
            if(<%=!f2.equals("")%>){
                
               
                   
                    final.value = "<%=f2%>";
                       <%request.getSession().setAttribute("seleFecha2", null); %> 
                       
                
            }

            
        </script>
        <script>


            function generarExcel() {
                var nombre = "<%=request.getSession().getAttribute("reporte").toString()%>";
                save(readCitas(), nombre);
            }
            function readCitas() {

                var citas = [["Id Cita", "Cedula", "Nombres", "Apellidos", "Celular", "Correo", "Descripcion", "Fecha", "Hora", "Estado"]];
                console.log(citas);
                var k = 1;
            <%for (int i = 0; i < citas.size(); i++) {%>

                citas[k] = [<%=citas.get(i).getId()%>,
            <%= Long.parseLong(citas.get(i).getIdPersona().getCedula())%>,
                    "<%=citas.get(i).getIdPersona().getNombres()%>",
                    "<%=citas.get(i).getIdPersona().getApellidos()%>",
            <%=citas.get(i).getIdPersona().getCelular()%>,
                    "<%=citas.get(i).getIdPersona().getEmail()%>",
                    "<%=citas.get(i).getDescripcion()%>",
                    "<%=citas.get(i).formatoFecha(citas.get(i).getFecha())%>",
                    "<%=citas.get(i).formatoHora(citas.get(i).getHora())%>",
                    "<%=citas.get(i).getEstado()%> "
                ];
                k++;
                console.log(<%=i%>);
            <%};%>
                return citas;
            }


        </script>

        <script type="text/javascript" src="<%=basePath%>./js/xlsx.core.min.js"></script>
        <script type="text/javascript" src="<%=basePath%>./js/Blob.js"></script>
        <script type="text/javascript" src="<%=basePath%>./js/FileSaver.js"></script>

    </body>

</html>
