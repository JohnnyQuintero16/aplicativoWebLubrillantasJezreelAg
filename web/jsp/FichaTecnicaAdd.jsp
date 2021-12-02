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
     <link rel="stylesheet" href="<%=basePath%>css/admClientes.css" />
  </head>
<body>

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
                            <p>
                                <span class="links_name"><%=request.getSession().getAttribute("nameUser") %></span>
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
                    <a href="<%=basePath%>MostrarProductosAdmin.do">
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
                    <a href="<%=basePath%>cerrarSesion.do">
                        <i class='bx bx-log-out'></i>
                        <span class="links_name">Salir</span>
                    </a>
                </li>

            </ul>
    </div>

      <section class="home-section">
        <div class="title">
            <h1>Ficha Técnica</h1>
        </div>

        <div class="container">
            <form class="row g-3 needs-validation" action = "<%=basePath%>AddVehiculo.do" novalidate>
                <h3>Datos del vehículo</h3>
        
                <div class="col-md-4" style="text-align: center;">
                    <strong for="validationCustom01" class="form-label">No. Placa</strong>
                    <input type="text" name = "placa" class="form-control" id="validationCustom01" required>
                </div>
        
                <div class="col-md-4" style="text-align: center;">
                    <strong for="validationCustom01" class="form-label">Modelo</strong>
                    <input type="text" name = "modelo" class="form-control" id="validationCustom01" required>
                </div>
        
                <div class="col-md-4" style="text-align: center;">
                    <strong for="validationCustom01" class="form-label">Color</strong>
                    <input type="text" name = "color" class="form-control" id="validationCustom01" required>
                </div>
        
                <div class="col-md-4" style="text-align: center;">
                    <strong for="validationCustom01" class="form-label">Ruedas</strong>
                    <input type="number" name="ruedas" class="form-control" id="validationCustom01" required>
                </div>
        
                <div class="col-md-4" style="text-align: center;">
                    <strong for="validationCustom01" class="form-label">Cilindraje</strong>
                    <input type="number" name="cilindra" class="form-control" id="validationCustom01" required>
                </div>
        
                <div class="col-md-4" style="text-align: center;">
                    <strong for="validationCustom01" class="form-label">Kilometraje</strong>
                    <input type="number" name="km" class="form-control" id="validationCustom01" required>
                </div>
        
                <div class="col-md-4" style="text-align: center;">
                    <strong for="validationCustom01" class="form-label">Carrocería</strong>
                    <input type="text" name="carroceria" class="form-control" id="validationCustom01" required>
                </div>
        
                <div class="col-md-4" style="text-align: center;">
                    <strong for="validationCustom01" class="form-label"> Peso</strong>
                    <input type="number" name="peso" class="form-control" id="validationCustom01" required>
                </div>
        
                <div class="col-md-4" style="text-align: center;">
                    <strong for="validationCustom01" class="form-label">No. Motor</strong>
                    <input type="text" name = "motor" class="form-control" id="validationCustom01" required>
                </div>
        
                <div class="col-md-4" style="text-align: center;">
                    <strong for="validationCustom01" class="form-label">Dimensión</strong>
                    <input type="text" name="dimension" class="form-control" id="validationCustom01" required>
                </div>

                <br>
                <div class="col-md-3 mx-2" style="text-align: center;">
                    <strong for="validationCustom01" class="form-label">Marca</strong>
                    <select name = "Marca" class = "form-control">
                        <option selected value = " ">Seleccione Marca</option>
                        <%=request.getSession().getAttribute("marca").toString()%>
                    </select>                    
                </div>
                <div class="col-md-3 mx-2" style="text-align: center;">
                    <strong for="validationCustom01" class="form-label">Tipo</strong>
                    <select name = "Tipo" class = "form-control">
                        <option selected value = " ">Seleccione Tipo</option>
                        <%=request.getSession().getAttribute("tipo").toString()%>
                    </select>                    
                </div>
                <div class="col-12" style="text-align: center;">
                
                    <button class="btn btn-primary margin-right" type="submit">Guardar</button>
                
                    <button  class="btn btn-danger" id="cancelar" type="reset" >Cancelar</button>
                </div>
            </form>
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

<!-- Validación de formulario -->
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
