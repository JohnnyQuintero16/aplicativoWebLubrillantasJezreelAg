<%-- 
    Document   : citasAdmin
    Created on : 21/11/2021, 03:05:26 PM
    Author     : Jarlin
--%>

<%@page import="DTO.AtencionServicio"%>
<%@page import="java.util.List"%>
<%@page import="DAO.CitaDAO"%>
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
        <!--smtp correo se debe cargar al inicio-->
        <script src="https://smtpjs.com/v3/smtp.js"></script>
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
                    <h1>Lista de Agendamientos</h1>
                </div>
            </div>
           <div class="table-responsive table-style">
                <table id="example" class="table table-bordered table-striped table-hover">
                    
                        <thead class="table-secondary">
                            <tr>
                                <th class="enc" scope="col">No</th>
                                <th class="enc" scope="col">Cédula</th>
                                <th class="enc" scope="col">Nombre</th>
                                <th class="enc" scope="col">Celular</th>
                                <th class="enc" scope="col">Correo Electrónico</th>
                                <th class="enc" scope="col">Fecha/Hora</th>
                                <th class="enc" scope="col">Servicio</th>
                                <th class="enc" scope="col">Asistencia</th>
                                <th class="enc" scope="col">Confirmación</th>
                            </tr>
                        </thead>
                        <tbody>
                        <%=request.getSession().getAttribute("citas").toString()%>
                        </tbody>
                </table>
           </div>
              <!-- Cierre div tabla -->
         </section>
        
          
        <!-- ventana modal -->
        <!-- Modal para el botón ver servicio-->
         <script>
         let m = document.getElementsByClassName('mod');
         let edit = document.getElementsByClassName('editt');
         let atendidas = '<%=request.getSession().getAttribute("atendida").toString()%>'.split(';');
         let noatendidas = '<%=request.getSession().getAttribute("noatendida").toString()%>'.split(";");
         
         for (var i = 0; i < m.length; i++) {
              m[i].addEventListener('click', function(e){
                  let modal1 = document.getElementById('textServ');
                  let id = e.target.id;
                  
                  for (var i = 0; i < atendidas.length-1; i++) {
                        let fila = atendidas[i].split(","); 
                        console.log(fila);
                        if(fila[0]===id){
                            modal1.innerHTML = 'ESTADO DEL SERVICIO: '+fila[3]+' \nDESCRIPCION: '+fila[1]+'PARA EL VEHICULO DE PLACA'+fila[2];
                            
                            }
                    }
                    for (var i = 0; i < noatendidas.length-1; i++) {
                        let fila = noatendidas[i].split(","); 
                        console.log(fila);
                        if(fila[0]===id){
                            modal1.innerHTML = 'ESTADO DEL SERVICIO: NO ATENDIDO \nDESCRIPCION: '+fila[1];
                            }
                    }
              });
            }  
            for (var i = 0; i < edit.length; i++) {
    
                    edit[i].addEventListener('click', function(e){
                        let modalSi = document.getElementById('optiona');
                        modalSi.value = e.currentTarget.id;
                        let modalNo = document.getElementById('optionb');
                        modalNo.value = e.currentTarget.id;
                        let correo = document.getElementById('option1');
                        correo.setAttribute("value", e.currentTarget.id);
                    });
                };
        </script>
        
        <div class="modal fade" id="modal1" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                             <div class="modal-dialog">
                                 <div class="modal-content">
                                     <div class="modal-header">
                                           <h5 class="modal-title" id="exampleModalLabel">Detalles Servicio</h5>
                                          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                       </div>
                                     <div class="modal-body" id="textServ">
                                       </div>
                                       <div class="modal-footer">
                                 <button type="button" class="boton3" data-bs-dismiss="modal">Aceptar</button>
                              </div>
                   </div>
               </div>
        </div>
        
        <div class="modal fade" id="modal2" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog ">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Asistencia del cliente</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">

                        ¿El cliente asistió a la realización del servicio?
                    </div>
                    <div class="modal-footer">
                     
                        <form name="confirmar1" action="./ConfirmaAsistenciaAdmin.do">
                            <input hidden name="respuesta" value="si"/>
                            <input name="idCi" hidden id="optiona" value="">
                            
                            <input class="btn-check" id="option1" autocomplete="off" data-bs-dismiss="modal" type ="button" onclick="javascript:enviarMail('modal');" />
                            <label class="botonSI" for="option1">Si</label>
                        </form>
                        <form name="confirmar2" action="./ConfirmaAsistenciaAdmin.do">
                            <input hidden name="respuesta" value="no"/>
                            <input hidden name="idCi" value="" id="optionb"/>
                            <input type="submit" class="btn-check" id="option2" autocomplete="off" data-bs-dismiss="modal">
                            <label class="botonNO" for="option2">NO</label>
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
        function enviarMail(llamado){
            let correo="";
            let asunto="";
            let cuerpo="";
            if(llamado==='modal'){
                let fila = document.getElementsByClassName(document.getElementById('option1').value)[0];
                correo = fila.children[4].innerText;
                asunto = "SERVICIO EN PROCESO";
                cuerpo = "📣 Hola desde Lubrillantas Jezreel queremos avisarte que tu servicio esta en proceso 🔩 🔧\n\n\
                       pronto recibiras un correo cuando tu servicio este listo!";
            }
            else{
                arr = llamado.split(",");
                correo = arr[0];
                asunto = "SERVICIO FINALIZADO";
                cuerpo = "📣 Hola desde Lubrillantas Jezreel queremos avisarte que tu servicio ha finalizado\n\n\
                       ya puedes acercarte por tu vehiculo 🔩 🔧🚗!";
            }
            Email.send({
                Host: "smtp.gmail.com",
                Username: 'lubrillantasjezreel@gmail.com',
                Password: "rvuxyiyppggwcrvx",
                To: correo,
                From: 'lubrillantasjezreel@gmail.com',
                Subject: asunto,
                Body: cuerpo,
                       
            });
            if(llamado==='modal'){
                document.confirmar1.submit();
            }
            else{
                document.getElementById('confirma'+arr[1]).submit();
            }
        }
            
        </script>
        <script>
        $(document).ready(function () {
            
            $('#example').DataTable({
                "order": [[5, "asc"]],
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
            })
        });
        </script>
        
    </body>
