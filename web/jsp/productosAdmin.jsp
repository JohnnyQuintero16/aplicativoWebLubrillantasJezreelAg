<%-- 
    Document   : productosAdmin
    Created on : 20/11/2021, 12:50:55 p. m.
    Author     : Acer
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="DTO.Producto"%>
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

                <!--<li>
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
                    <h1>Lista de productos</h1>
                </div>

                <div class="boton">
                    <button type="button" class="btn btn-primary btn-lg" data-bs-toggle="modal" data-bs-target="#modal1">Añadir producto</button>
                </div>
            </div>
            <%
                List<Producto> productos = new ArrayList<>();
                if ((request.getSession().getAttribute("productos")) == null) {%>
            <div class = "container-fluid" style="display: flex; align-content: center; align-items: center;justify-content: center">
                <h1 style="color:#ff0000" align="center">En estos momentos no se encuentran Productos Registrados!</h1>
            </div>

            <% } else {
                productos = (List<Producto>) (request.getSession().getAttribute("productos"));
            %>
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

                    <tbody>
                        <%
                            int i = 1;
                            for (Producto p : productos) {
                        %>
                        <tr>
                            <th class="enc" scope="row"><%=i%></th>
                            <td><%=p.getNombre()%></td>
                            <td><%=p.getMarca()%></td>
                            <td><%=p.getTipo()%></td>
                            <td><%=p.getReferencia()%></td>
                            <td><%=p.getDescripcion()%></td>
                            <td><%=p.getPrecioUnitario()%></td>
                            <td><%=p.getPrecioVenta()%></td>
                            <td><%=p.getCantidad()%></td>

                            <!-- Acciones: editar y cancelar. -->
                            <td>
                                <div class="icons-acciones">
                                    <div>
                                        <i class="fas fa-edit" data-bs-toggle="modal" data-bs-target="#modal2" data-bs-whatever = 
                                           '
                                           {
                                           "codigo":"<%=p.getCodigo()%>",
                                           "url":"<%=p.getImgUrl()%>"
                                           }'></i>
                                    </div>
                                    <div>
                                        <i class="fas fa-trash-alt" data-bs-toggle="modal" data-bs-target="#modal3" data-bs-whatever = "<%=p.getCodigo()%>"></i>
                                    </div>      
                                </div>
                            </td>
                        </tr>
                        <%
                                    i++;
                                }
                            }%>
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


                        <form action="./AdminAddProducto.do" method="GET">
                            <div class="row text-center m-3">

                                <div class="col-md-6">

                                    <div class="mb-3 ">
                                        <label for="exampleInputCodigo" class="form-label">Código</label>
                                        <input type="number" class="form-control " name = "codigo" id="exampleInputCodigo" required>
                                    </div>

                                </div>

                                <div class="col-md-6">

                                    <div class="mb-3 ">
                                        <label for="exampleInputRef" class="form-label">Referencia</label>
                                        <input type="text" class="form-control " name="referencia" id="exampleInputRef" required>

                                    </div>


                                </div>

                                <div class="col-md-6">

                                    <div class="mb-3 ">
                                        <label for="exampleInputNombre" class="form-label">Nombre</label>
                                        <input type="text" class="form-control " name="nombre" id="exampleInputNombre" required>
                                    </div>
                                </div>

                                <div class="col-md-6">

                                    <div class="mb-3 ">
                                        <label for="exampleInputMarca" class="form-label">Marca</label>
                                        <input type="text" class="form-control " name = "marca" id="exampleInputMarca" required>

                                    </div>


                                </div>

                                <div  class="col-md-6">

                                    <div class="mb-3">
                                        <label for="exampleInputPrecioU" class="form-label">Precio Unitario</label>
                                        <input type="number"  class="form-control" name = "precioUnitario" id="exampleInputPrecioU" required>
                                    </div>

                                </div>

                                <div  class="col-md-6">

                                    <div class="mb-3">
                                        <label for="exampleInputPrecioV" class="form-label">Precio Venta</label>
                                        <input type="number"  class="form-control" name = "precioVenta" id="exampleInputPrecioV" required>
                                    </div>

                                </div>


                                <div class="col-md-6">

                                    <div class="mb-3">
                                        <label for="exampleInputCant" class="form-label">Cantidad</label>
                                        <input type="number" class="form-control" name = "cantidad" id="exampleInputCant" required>
                                    </div>

                                </div>

                                <div class="col-md-6">

                                    <div class="mb-3">
                                        <label for="exampleInputCant" class="form-label">Tipo</label>
                                        <select class="form-select"  name = "tipo"aria-label="Default select example" required>
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
                                    <input type="text" class="form-control" name="url" id="exampleInputImagenURL" required>
                                </div>
                                <div class="mb-3">
                                    <label for="exampleDescripcion" class="form-label">Descripción</label>
                                    <textarea class="form-control" name="descripcion" id="exampleInputDescripcion" rows="3" required></textarea>
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
                        <form action="ActualizarProducto.do">
                            <div class="row text-center m-3">

                                <div class="col-md-6">

                                    <div class="mb-3 ">
                                        <label for="exampleInputCodigo" class="form-label">Código</label>
                                        <input type="number" class="form-control " id="exampleInputCodigo" name="codigo" readonly>

                                    </div>

                                </div>

                                <div class="col-md-6">

                                    <div class="mb-3 ">
                                        <label for="exampleInputRef" class="form-label">Referencia</label>
                                        <input type="text" class="form-control " id="exampleInputRef" name = "referencia" required>

                                    </div>


                                </div>

                                <div class="col-md-6">

                                    <div class="mb-3 ">
                                        <label for="exampleInputNombre" class="form-label">Nombre</label>
                                        <input type="text" class="form-control " id="exampleInputNombre" name="nombre" required>

                                    </div>


                                </div>

                                <div class="col-md-6">

                                    <div class="mb-3 ">
                                        <label for="exampleInputMarca" class="form-label">Marca</label>
                                        <input type="text" class="form-control " id="exampleInputMarca" name = "marca" required>

                                    </div>


                                </div>

                                <div  class="col-md-6">

                                    <div class="mb-3">
                                        <label for="exampleInputPrecioU" class="form-label">Precio Unitario</label>
                                        <input type="number"  class="form-control" id="exampleInputPrecioU" name="precioUnitario" required>
                                    </div>

                                </div>

                                <div  class="col-md-6">

                                    <div class="mb-3">
                                        <label for="exampleInputPrecioV" class="form-label">Precio Venta</label>
                                        <input type="number"  class="form-control" id="exampleInputPrecioV" name="precioVenta" required>
                                    </div>

                                </div>


                                <div class="col-md-6">

                                    <div class="mb-3">
                                        <label for="exampleInputCant" class="form-label">Cantidad</label>
                                        <input type="number" class="form-control" id="exampleInputCant" name="cantidad" required>
                                    </div>

                                </div>

                                <div class="col-md-6">

                                    <div class="mb-3">
                                        <label for="exampleInputCant" class="form-label">Tipo</label>
                                        <select class="form-select" aria-label="Default select example" name = "select" required>
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
                                    <input type="text" class="form-control" id="exampleInputImagenURL" name="url" equired>
                                </div>
                                <div class="mb-3">
                                    <label for="exampleDescripcion" class="form-label">Descripción</label>
                                    <textarea class="form-control" id="exampleInputDescripcion" rows="3" name = "descripcion" required></textarea>
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

                        <form action = "<%=basePath%>EliminarProducto.do" method = "GET">

                            <div class="mb-3" >

                                <input type="text" class="form-control " id="exampleInputNombre" name="id"  style="display: none ">

                            </div>

                            <!--<input type="radio" class="btn-check" name="options" id="option1" autocomplete="off" data-bs-dismiss="modal" >
                            <label class="botonSI" for="option1">Confirmar</label>

                            <input type="radio" class="btn-check" name="options" id="option2" autocomplete="off" data-bs-dismiss="modal">
                            <label class="botonNO" for="option2">Cancelar</label>
                            -->
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
            "sProcessing": "Procesando..."
            }

            }
            );
            });

            var modalEditarProducto = document.getElementById('modal2');

            modalEditarProducto.addEventListener('show.bs.modal', (e) => {
            var btn = e.relatedTarget.valueOf().parentNode;
            li = btn.parentNode;
            li = li.parentNode;
            li = li.parentNode;
            datos = li.querySelectorAll("td");
            var datosRecipien = e.relatedTarget.getAttribute('data-bs-whatever');
            var txtDatos = JSON.parse(datosRecipien);
            console.log(datos);
            modalBodyInput = modalEditarProducto.querySelector('.modal-body').querySelectorAll('input');
            modalBodyOption = modalEditarProducto.querySelector('.modal-body').querySelectorAll('option');
            console.log(modalBodyInput);
            textA = modalEditarProducto.querySelector('.modal-body').querySelectorAll('textarea');
            modalBodyInput[0].value = txtDatos.codigo;
            modalBodyInput[2].value = datos[0].innerHTML;
            modalBodyInput[1].value = datos[3].innerHTML;
            modalBodyInput[3].value = datos[1].innerHTML;
            modalBodyInput[4].value = datos[5].innerHTML;
            modalBodyInput[5].value = datos[6].innerHTML;
            modalBodyInput[6].value = datos[7].innerHTML;
            modalBodyInput[7].value = txtDatos.url;
            textA[0].value = datos[4].innerHTML;                
            for(let i = 0; i < modalBodyOption.length; i++){
            if(modalBodyOption[i].innerHTML === datos[2].innerHTML){
            modalBodyOption[i].setAttribute("selected","");
            modalBodyOption[i].innerHTML = datos[2].innerHTML;
            }else{
            modalBodyOption[i].removeAttribute("selected");
            }
            }
            });
            var modalEliminarProducto = document.getElementById('modal3');
            modalEliminarProducto.addEventListener('show.bs.modal', (e) => {
            var datosRecipien = e.relatedTarget.getAttribute('data-bs-whatever');
            console.log(datosRecipien);
            modalBodyInput = modalEliminarProducto.querySelector('.modal-footer').querySelectorAll('input');
            modalBodyInput[0].value = datosRecipien;
            
            });

        </script>

    </body>
</html>
