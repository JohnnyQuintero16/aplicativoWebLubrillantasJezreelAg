<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html lang="en">

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
  <link rel="stylesheet" href="../css/menuAdministrador.css" />
  <link rel="stylesheet" href="../css/admClientes.css" />
</head>

<body>

  <div class="sidebar">
    <div class="logo-details">
      <i class="fas fa-tire icon"></i>
      <!-- Espacio entre mensaje Bienvenido-->
      <div class="logo_name">Bienvenido</div>
      <i class='bx bx-menu' id="btn"></i>
    </div>

    <ul class="nav-list">
      <li>
        <div class="image-admin">
          <div class="container-img">
            <img src="../img/user.png" alt="Administrador">
          </div>
          <div class="container-name">
            <p><span class="links_name">Samantha Zamora</span></p>
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
        <a href="admClientes.html">
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
      <li>
        <a href="reportes.html">
          <i class="fas fa-chart-pie"></i>
          <span class="links_name">Reportes</span>
        </a>
        <span class="tooltip">Reportes</span>
      </li>
      <li>
        <a href="#">
          <i class="fas fa-signal"></i>
          <span class="links_name">Estadísticas</span>
        </a>
        <span class="tooltip">Estadísticas</span>
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
        <h1>Reportes de citas</h1>
      </div>

      <div class="boton">
        <button type="button" class="btn btn-primary btn-lg">Regresar</button>
      </div>
    </div>

    <div class="container-fluid">
      <div class="row"> 
        <!--select -->
        <div class="col-3 ">
          <select class="form-select" >
            <option selected>Tipo de reporte</option>
            <option value="1">Citas atendidas</option>
            <option value="2">Citas canceladas</option>
          </select>
        </div>

         <!--fecha ini -->
         <div class="col-3">
          <div class="row mb-3">
            <label class="col-sm-2 col-form-label">Desde:</label>
            <div class="col-sm-8">
              <input type="date" class="form-control">
            </div>
          </div>
         </div>

         <!--fecha fin -->
         <div class="col-3">
          <div class="row mb-3">
            <label class="col-sm-2 col-form-label">Hasta:</label>
            <div class="col-sm-8">
              <input type="date" class="form-control">
            </div>
          </div>
        </div>

         <!--fecha boton -->
         <div class="col-3">
          <button type="button" class="btn btn-primary btn-lg">Consultar</button>
        </div>
      </div>
    </div>

    <br>
    <div class="table-responsive table-style">
      <table id="example" class="table table-bordered table-striped table-hover">
        <thead class="table-secondary">
          <tr>
            <th class="enc" scope="col">Id</th>
            <th class="enc" scope="col">Cédula</th>
            <th class="enc" scope="col">Nombre</th>
            <th class="enc" scope="col">Celular</th>
            <th class="enc" scope="col">Correo Electronico</th>
            <th class="enc" scope="col">Descripción</th>
            <th class="enc" scope="col">Fecha / Hora</th>
            <th class="enc" scope="col">Acciones</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <th class="enc" scope="row">1</th>
            <td>1000718165</td>
            <td>Sebastian Casadiegos</td>
            <td>3174535149</td>
            <td>casadiegosgomezjs@ufps.edu.co</td>
            <td>XXX54, Runner, Toyota, 2018, 150, Cambio de aceite</td>
            <td>29/09/2021 4:50 p.m</td>
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
            <td>1000718165</td>
            <td>Sebastian Casadiegos</td>
            <td>3174535149</td>
            <td>casadiegosgomezjs@ufps.edu.co</td>
            <td>XXX54, Runner, Toyota, 2018, 150, Cambio de aceite</td>
            <td>29/09/2021 4:50 p.m</td>
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
      </table>
    </div>

    <br>
    <!-- reporte -->
    
    <button type="button" class=" arreglo btn btn-primary btn-lg" data-bs-toggle="modal" data-bs-target="#modal1">Generar excel</button>
   

    <!-- modal-->
    <div class="modal fade" tabindex="-1" id="modal1" aria-labelledby="modal1" aria-hidden="true">
      <div class="modal-dialog">
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
            <button type="button" class="btn btn-primary">Confirmar</button>
          </div>
        </div>
      </div>
    </div>
  </section>


  <script src="../js/menuAdministrador.js"></script>
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

</body>

</html>