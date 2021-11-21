<%-- 
    Document   : productosAdmin
    Created on : 20/11/2021, 12:50:55 p. m.
    Author     : Acer
--%>

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

        <link href="<%=basePath%>css/productosAdmin.css" rel="stylesheet" type="text/css"/>
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
                                <span class="links_name">Administrador</span>
                            </p>
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
                    <a href="jsp/adminClientes.jsp">
                        <i class="icon fas fa-user"></i>
                        <span class="links_name">Clientes</span>
                    </a>
                    <span class="tooltip">Clientes</span>
                </li>
                <li>
                    <a href="jsp/serviciosAdmin.jsp">
                        <i class="fas fa-user-cog"></i>
                        <span class="links_name">Servicios</span>
                    </a>
                    <span class="tooltip">Servicios</span>
                </li>
                <li>
                    <a href="jsp/productosAdmin.jsp">
                        <i class="fas fa-shopping-cart"></i>
                        <span class="links_name">Productos</span>
                    </a>
                    <span class="tooltip">Productos</span>
                </li>

                <!--<li>
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
                    <h1>Lista de productos</h1>
                </div>

                <div class="boton">
                    <button type="button" class="btn btn-primary btn-lg" data-bs-toggle="modal" data-bs-target="#modal1">Añadir producto</button>
                </div>

            </div>

            <div class="table-responsive table-style tablaPro">
                <table id="example" class="table table-bordered table-striped table-hover ">
                    <thead class="table-secondary">
                        <tr>
                            <th class="enc" style="width: 9%" scope="col">Código</th>
                            <th class="enc" style="width: 8%" scope="col">Nombre</th>
                            <th class="enc" style="width: 10%" scope="col">Marca</th>
                            <th class="enc" style="width: 5%" scope="col">Tipo</th>
                            <th class="enc" style="width: 10%"scope="col">Referencia</th>
                            <th class="enc" style="width: 30%" scope="col">Descripción</th>
                            <th class="enc" style="width: 6%" scope="col">Precio Unitario</th>
                            <th class="enc" style="width: 6%" scope="col">Precio Venta</th>
                            <th class="enc" style="width: 7%" scope="col">Cantidad</th>

                            <th class="enc" style="width: 9%" scope="col">Acciones</th>

                        </tr>
                    </thead>
                    <tbody >
                        <tr>
                            <th class="enc" scope="row">100</th>
                            <td>Aceite Fusion</td>
                            <td>Shell</td>
                            <td>ACEITES</td>
                            <td>Refe 20w-50</td>
                            <td>lorem sdfsf sf  f f fs f f s f  sd sd fsd</td>
                            <td>5000</td>
                            <td>10.000</td>
                            <td>5</td>

                            <!-- Acciones: editar y cancelar. -->
                            <td>
                                <div class="icons-acciones">
                                    <div>
                                        <i class="fas fa-edit" data-bs-toggle="modal" data-bs-target="#modal2"></i>
                                    </div>
                                    <div>
                                        <i class="fas fa-trash-alt" data-bs-toggle="modal" data-bs-target="#modal3"></i>
                                    </div>      
                                </div>
                            </td>
                        </tr>

                        <tr>
                            <th class="enc" scope="row">101</th>
                            <td>Aceite Fusion</td>
                            <td>Shell</td>
                            <td>ACEITES</td>
                            <td>Refe 20w-50</td>
                            <td>lorem sdfsf sf  f f fs f f s f  sd sd fsd</td>
                            <td>5000</td>
                            <td>10.000</td>
                            <td>5</td>

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

                        <h2 class="modal-title">Añadir producto</h2>

                    </div>
                    <div class="modal-body ">


                        <form >
                            <div class="row text-center m-3">

                                <div class="col-md-6">

                                    <div class="mb-3 ">
                                        <label for="exampleInputCodigo" class="form-label">Código</label>
                                        <input type="number" class="form-control " id="exampleInputCodigo" required>

                                    </div>

                                </div>

                                <div class="col-md-6">

                                    <div class="mb-3 ">
                                        <label for="exampleInputRef" class="form-label">Referencia</label>
                                        <input type="text" class="form-control " id="exampleInputRef" required>

                                    </div>


                                </div>

                                <div class="col-md-6">

                                    <div class="mb-3 ">
                                        <label for="exampleInputNombre" class="form-label">Nombre</label>
                                        <input type="text" class="form-control " id="exampleInputNombre" required>

                                    </div>


                                </div>

                                <div class="col-md-6">

                                    <div class="mb-3 ">
                                        <label for="exampleInputMarca" class="form-label">Marca</label>
                                        <input type="text" class="form-control " id="exampleInputMarca" required>

                                    </div>


                                </div>

                                <div  class="col-md-6">

                                    <div class="mb-3">
                                        <label for="exampleInputPrecioU" class="form-label">Precio Unitario</label>
                                        <input type="number"  class="form-control" id="exampleInputPrecioU" required>
                                    </div>

                                </div>

                                <div  class="col-md-6">

                                    <div class="mb-3">
                                        <label for="exampleInputPrecioV" class="form-label">Precio Venta</label>
                                        <input type="number"  class="form-control" id="exampleInputPrecioV" required>
                                    </div>

                                </div>


                                <div class="col-md-6">

                                    <div class="mb-3">
                                        <label for="exampleInputCant" class="form-label">Cantidad</label>
                                        <input type="number" class="form-control" id="exampleInputCant" required>
                                    </div>

                                </div>

                                <div class="col-md-6">

                                    <div class="mb-3">
                                        <label for="exampleInputCant" class="form-label">Tipo</label>
                                        <select class="form-select" aria-label="Default select example" required>
                                            <option value="">Selecciona un tipo:</option>
                                            <option value="1">ACEITES</option>
                                            <option value="2">FILTROS</option>
                                            <option value="3">VALVULINAS</option>
                                            <option value="4">ADITIVOS</option>
                                             <option value="5">OTROS</option>
                                        </select>
                                    </div>

                                </div>




                                <div class="mb-3">
                                    <label for="exampleInputImagenURL" class="form-label">ImagenURL</label>
                                    <input type="text" class="form-control" id="exampleInputImagenURL" required>
                                </div>





                                <div class="mb-3">
                                    <label for="exampleDescripcion" class="form-label">Descripción</label>
                                    <textarea class="form-control" id="exampleInputDescripcion" rows="3" required></textarea>
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

                        <h2 class="modal-title">Editar Producto</h2>
                        <!--       <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close">
                              </button> -->
                    </div>
                    <div class="modal-body ">


                       
                        <form >
                            <div class="row text-center m-3">

                                <div class="col-md-6">

                                    <div class="mb-3 ">
                                        <label for="exampleInputCodigo" class="form-label">Código</label>
                                        <input type="number" class="form-control " id="exampleInputCodigo" value="1010" required>

                                    </div>

                                </div>

                                <div class="col-md-6">

                                    <div class="mb-3 ">
                                        <label for="exampleInputRef" class="form-label">Referencia</label>
                                        <input type="text" class="form-control " id="exampleInputRef" value="25w-20" required>

                                    </div>


                                </div>

                                <div class="col-md-6">

                                    <div class="mb-3 ">
                                        <label for="exampleInputNombre" class="form-label">Nombre</label>
                                        <input type="text" class="form-control " id="exampleInputNombre" value="Fusion Fox" required>

                                    </div>


                                </div>

                                <div class="col-md-6">

                                    <div class="mb-3 ">
                                        <label for="exampleInputMarca" class="form-label">Marca</label>
                                        <input type="text" class="form-control " id="exampleInputMarca" value="Shell" required>

                                    </div>


                                </div>

                                <div  class="col-md-6">

                                    <div class="mb-3">
                                        <label for="exampleInputPrecioU" class="form-label">Precio Unitario</label>
                                        <input type="number"  class="form-control" id="exampleInputPrecioU" value="5000" required>
                                    </div>

                                </div>

                                <div  class="col-md-6">

                                    <div class="mb-3">
                                        <label for="exampleInputPrecioV" class="form-label">Precio Venta</label>
                                        <input type="number"  class="form-control" id="exampleInputPrecioV" value="8000" required>
                                    </div>

                                </div>


                                <div class="col-md-6">

                                    <div class="mb-3">
                                        <label for="exampleInputCant" class="form-label">Cantidad</label>
                                        <input type="number" class="form-control" id="exampleInputCant" value="5"required>
                                    </div>

                                </div>

                                <div class="col-md-6">

                                    <div class="mb-3">
                                        <label for="exampleInputCant" class="form-label">Tipo</label>
                                        <select class="form-select" aria-label="Default select example" required>
                                            <option value="">Selecciona un tipo:</option>
                                            <option selected value="1">ACEITES</option>
                                            <option value="2">FILTROS</option>
                                            <option value="3">VALVULINAS</option>
                                            <option value="4">ADITIVOS</option>
                                             <option value="5">OTROS</option>
                                        </select>
                                    </div>

                                </div>




                                <div class="mb-3">
                                    <label for="exampleInputImagenURL" class="form-label">ImagenURL</label>
                                    <input type="text" class="form-control" id="exampleInputImagenURL" value="http//:zz5as5ssd4s" required>
                                </div>





                                <div class="mb-3">
                                    <label for="exampleDescripcion" class="form-label">Descripción</label>
                                    <textarea class="form-control" id="exampleInputDescripcion" rows="3" required>Habia una vez un......</textarea>
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
                        <h5 class="modal-title" id="exampleModalLabel">Eliminar Producto</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        ¿Estás seguro de eliminar el Producto?
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="boton2" >Confirmar</button>
                        <button type="button" class="boton3 " data-bs-dismiss="modal">Cancelar</button>
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
                        "sProcessing": "Procesando..."
                    }

                }
                );
            });
        </script>

    </body>
</html>
