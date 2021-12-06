<%@page import="DTO.Servicio"%>
<%@page import="DTO.Producto"%>
<%@page import="java.util.List"%>
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

        <!-- Selector -->
        <link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet" />
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
                            <img src="<%=basePath%>img/user.png" alt="Administrador">
                        </div>
                        <div class="container-name">
                            <!--<p><span class="links_name"></span></p>-->
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
                    <a href="<%=basePath%>jsp/productosAdmin.jsp">
                        <i class="fas fa-shopping-cart"></i>
                        <span class="links_name">Productos</span>
                    </a>
                    <span class="tooltip">Productos</span>
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

            <div class=" title">            
                <div class="titulo">
                    <h1 style="color:blue">Cliente: <%=request.getSession().getAttribute("usuarioCliente").toString()%></h1> 
                </div>

            </div>

            <form action = "<%=basePath%>ProcesarAtencionServicio.do" method = "POST">
                <div class="row shadow m-auto rounded-3 bd-body" style=" background-color: white; padding: 2rem; width: 95%; overflow-x: hidden;">
                    <div class="col-6" style="padding-left: 1rem; border-right: 1px solid #323;">
                        <h2 style="color: #001971;">Escoja los servicio:</h2>
                        <p>Lista de servicios: </p>
                        <table class="table" >
                            <tr>
                                <th>id</th>
                                <th>Nombre del servicio</th>
                                <th>Accion</th>
                            </tr>

                            <%
                                List<Servicio> servicios = (List<Servicio>) request.getSession().getAttribute("servicios");
                                String servi = "";
                                int i = 0;
                                for (Servicio ser : servicios) {
                                    servi = ser.getId() + ","
                                            + ser.getNombre() + ","
                                            + i + ";";
                            %>
                            <tr>
                                <td value = '<%=ser.getId()%>'><%=ser.getId()%></td>
                                <td value = '<%=ser.getNombre()%>'><%=ser.getNombre()%></td>
                                <td> <button type="button" value = "<%=i%>"  onclick="javascript:addServicio('<%=servi%>')" class="btn btn-primary btn-lg btnAddServicio" style="background-color: green !important;">+</button></td>                        
                            </tr>
                            <%i++;
                                }%>
                        </table>

                    </div>


                    <div class="search-container col-6" style="padding: 1rem;"> <br>
                        <p>Servicios seleccionados: </p>
                        <table class="table" >
                            <tr>
                                <th>id</th>
                                <th>Nombre del servicio</th>
                                <th>Accion</th>
                            </tr>
                            <tbody id = "TablaServicioCliente">
                                <template id = "template-ServicioSelect">
                                    <tr>
                                        <td scope="row">1</td>
                                        <td>Montallantas</td>
                                        <td> <button type="button" class="btn btn-primary btn-lg eliminarServicio" style="background-color: red !important;">-</button></td>                        
                                        </tr>
                                </template>
                            </tbody>
                        </table>
                    </div>
                </div>

                <div class="row shadow rounded-3 bg-body m-auto my-3" style=" background-color: white; width: 95%;  overflow-x: hidden; padding: 2rem;">
                    <div class="col-12" style="padding-left: 0.5rem">
                        <h2 style="color: #001971;">Escoja el Producto:</h2>
                        <br>
                        <select class = "selectPro" id="selectProductos">
                            <option select readonly>Elegir Producto</option>
                            <%
                                List<Producto> productos = (List<Producto>) request.getSession().getAttribute("productos");
                                for (Producto pro : productos) {
                                    String value = pro.getCodigo() + "," + pro.getNombre();
                            %>
                            <option value="<%=value%>"><%=pro.getNombre()%></option>
                            <%}%>
                        </select>
                        <p>Lista de productos con los que se llevo a cabo el servicio.</p>
                        <table class="table" >
                            <tr>
                                <th>id</th>
                                <th>Nombre</th>
                                <th>cantidad</th>
                                <th>Acción</th>
                            </tr>
                            <tbody id="tablaBody">
                            <template id="TablaProductosCliente">
                                <tr>
                                    <td scope = "row">1</td>
                                    <td>Aceite Max</td>
                                    <td> <button type="button" class="btn btn-primary btn-lg eliminarProducto" style="background-color: red !important;">X</button></td>
                                </tr>
                            </template>
                            </tbody>
                            <tfoot>
                                <tr id = "footer">
                                    <th scope="row" colspan="4">No se ha seleccionado productos, seleccione!</th>
                                </tr>
                            </tfoot>
                            <template id="template-footer">
                                <th scope="row" colspan="3">Costo De Los Productos</th>
                                <td class="font-weight-bold" colspan="2">$ <span>5000</span></td>
                                <br>
                                <p>Por favor digite descuento (Se aplica al total de los productos)</p>
                                <input type="number" id="descuento" name="descuento" placeholder="Descuento" value = "0" min="0" max="100"> 
                                <br>
                                <th scope="row" colspan="3">Costo De Los Productos Con Descuento</th>
                                <td class="font-weight-bold" colspan="2">$ <span>5000</span></td>
                            </template>
                        </table>
                    </div>
                </div>
                </div>
                
                    <div class="row shadow rounded-3 bg-body m-auto" style=" background-color: white; width: 95%;  overflow-x: hidden; padding: 2rem;">
                        <div class="search-container col-4" style="padding: 1rem;"> <br>                        
                            <h4 style="color: #001971;">Ultimo kilometraje registrado del vehiculo</h4>
                            <input type="text" id="kilometraje" value='<%=request.getSession().getAttribute("km")%>' readonly> <br>
                            <br>
                            <h4 style="color: #001971;">Por favor digite el kilometraje del vehiculo</h4>
                            <p>Por favor digite el kilometraje del vehiculo:</p>
                            <input type="number" id="kilometraje" name="kilometraje" placeholder="kilometraje"  required min="0"> <br> <br>
                        </div>
                        <div class="search-container col-4 " style="padding: 1rem;"> <br> 
                            <h4 style="color: #001971;">Escoja el Mecánico:</h4>
                            <select style=" background-color: rgb(214, 205, 205);"  name="mecanico">
                                <%=request.getSession().getAttribute("mecanicos").toString()%>
                            </select>
                        </div>                        
                        <div class="col-4" style="padding-left: 3rem">
                            <h4 style="color: #001971;">Descripción del servicio realizado</h4>
                            <textarea id="txtarea" name="descri" rows="3" cols="20" required=""></textarea>
                        </div>
    
                    </div>
                    <div class="row shadow rounded-3 bg-body m-auto" style=" background-color: white; width: 95%;  overflow-x: hidden">
                        <div class="col-6 " style="padding-top: 1rem; display: flex; justify-content: center; align-items: center ">
                            <div style="margin-bottom: 1rem;">
                                <button type="reset" class="btn btn-primary btn-lg"  style="background-color: rgb(235, 71, 71) !important; width: 20rem">Cancelar</button>
                            </div>
                        </div>    
                        <div class="col-6 " style="padding-top: 1rem; display: flex; justify-content: center; align-items: center ">
                            <div style="margin-bottom: 1rem;">
                                <input type="submit" class="btn btn-primary btn-lg"  style="background-color: #001971  !important;  width: 20rem;" value='Agregar'>
                            </div>
                        </div>    
                    </div>
            </form>
        </section>
        <script src="<%=basePath%>js/menuAdministrador.js"></script>
        <script src="<%=basePath%>js/adminRegis.js"></script>
        <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
        <script src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.11.3/js/dataTables.bootstrap5.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>


        <script>
            let serviciosJavaS = '<%=request.getSession().getAttribute("serviciosJS").toString()%>'.split(";");
            let productoJavaS = '<%=request.getSession().getAttribute("productosJS").toString()%>'.split(";");
            infoDataJSP(serviciosJavaS, productoJavaS);

            $(document).ready(function () {
            $(".selectPro").select2();
            });

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
            
            


            function eliminarServicioLista(fila){
            console.log(fila.target)
            console.log("Entra a eliminaR");
            console.log(fila);
            /*btn[arr[2]].innerHTML = "-";
            btn[arr[2]].style.backgroundColor = "#ff1";
            btn[arr[2]].disabled = true;,*/
            }

            //Productos
            const padreTemplate = document.getElementById("TablaProductosCliente").content;
            const hijoTr = padreTemplate.querySelector("tr");
            const hijoTd = hijoTr.querySelectorAll("td");
            let cntPro = 0;

            $(".selectPro").on("select2:select", function (e) { 
            
            var select_val = $(e.currentTarget).val();
            cargarProductos(select_val);
            });
            let idsProducto = [];
            function cargarProductos(value){
            var valor = value.split(",");
            let existe = idsProducto.indexOf(valor[0]);
            if(existe == -1){
            cntPro++;
            idsProducto.push(valor[0]);
            const fragment = document.createDocumentFragment();    
            let input = document.createElement("INPUT");
            input.setAttribute("name", "idp");
            input.setAttribute("value", valor[0]);
            input.setAttribute("style","display: none");

            hijoTd[0].innerHTML = valor[0];
            hijoTd[0].setAttribute("value",valor[0]);
            hijoTd[0].setAttribute("name",valor[0]);
            hijoTd[1].innerHTML = valor[1];
            hijoTd[1].setAttribute("value",valor[1]);
            hijoTr.appendChild(hijoTd[0]);
            hijoTr.appendChild(hijoTd[1]);
            hijoTr.appendChild(input);
            const clone = hijoTr.cloneNode(true);
            fragment.appendChild(clone);

            document.getElementById("tablaBody").appendChild(fragment);
            }else{
            alert("Ya tiene seleccionado este producto, elija otro");
            }
            }
            function eliminarElement(){
                element = document.getElementById(cntPro);
                
                function eliminarElemento(id){	
                padre = element.parentNode;
                
                padre.removeChild(element);
                cntPro--;
                }
            }
            
            function verificarCantidad(){
            let error = '<%=request.getSession().getAttribute("error")%>';
            console.log(error);
            if(error == "erroPro"){
            alert("No hay suficientes productos");
            }
            }
            verificarCantidad();
            
            
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