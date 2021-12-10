<%-- 
    Document   : agendarCitaUsu
    Created on : 28/11/2021, 10:05:58 p. m.
    Author     : Acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" +     
        request.getServerPort() + path + "/";
%>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lubrillantas Jezreel AG</title>
    
    <!-- Fuente de google: Open Sans - Regular 400 -->
    <link href="https://fonts.googleapis.com/css?family=Poppins:400,500,600,700&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    
    <!--Normallize css: proyecto que corrige estilos predeterminados de los diferentes navegadores, para evitar usar el selector universal
    en la hoja de estilos CSS. -->
    <link rel="stylesheet" href="https://necolas.github.io/normalize.css/8.0.1/normalize.css">
    
    <!--Importar CSS -->
    <link href="<%=basePath%>css/menu.css" rel="stylesheet" type="text/css"/>
    <link href="<%=basePath%>css/agendarCita.css" rel="stylesheet" type="text/css"/>
    <link href="<%=basePath%>css/footer.css" rel="stylesheet" type="text/css"/>
    
    </head>
    <body onload="sesion('<%=request.getSession().getAttribute("usuario")%>')">
        
        <!-- Inicio menú-->
	<nav class="navbar navbar-expand-lg sticky-top navbar-dark bg-primary">
		<div class="container-fluid">

			<a class="navbar-brand" href="<%=basePath%>/index.jsp">
				<img src="<%=basePath%>/img/LogoLJAG.png" alt="" width="140px" height="120px"/>
			</a>

		  <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		  </button>
		  
		  <div class="collapse navbar-collapse" id="navbarSupportedContent">

			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
			  <li class="nav-item">
				<a class="nav-link" href="<%=basePath%>index.jsp">INICIO</a>
			  </li>
			  <li class="nav-item">
				<a class="nav-link" href="<%=basePath%>jsp/nosotros.jsp">NOSOTROS</a>
			  </li>
			  <li class="nav-item">
				<a class="nav-link" href="<%=basePath%>MostrarServicios.do">SERVICIOS</a>
			  </li>
			  <li class="nav-item">
				<a class="nav-link" href="<%=basePath%>MostrarProductos.do">PRODUCTOS</a>
			  </li>
                          <li class="nav-item">
                            <a class="nav-link" href="<%=basePath%>MostrarServiciosCotizacion.do">COTIZA YA!</a>
                        </li>
			</ul>

			<template id="NoSesion">
                        <ul class="navbar-nav ml-auto m-4">
                            <li class="nav-item">
                                <a class="nav-link" href="<%=basePath%>/jsp/iniciarsesion.jsp">INICIAR SESIÓN</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="<%=basePath%>/jsp/registrarse.jsp">REGISTRARSE</a>
                            </li>
                        </ul>
                    </template>
		  </div>
		</div>
	</nav>
	<!-- Fin menú -->

    <main>
        <!-- Inicio formulario-->
        <section class="registro">
            <form action="InsertarCita.do" class="formulario" id="formulario">
                <div class="form-register__header">
                    <h1 class="form-register__title">Agenda tu servicio</h1>
                    <ul class="progressbar">
                        <li class="progressbar__option active">Datos</li>
                        <li class="progressbar__option">Selecciona un horario</li>
                        <li class="progressbar__option">Confirmar</li>
                    </ul>
                </div>

                <div class="form-register__body">
                    <div class="step active" id="step-1">

                        <div class="step__header">
                            <h2 class="step__title">Datos del vehículo</h2>
                        </div>

                        <div class="step__body">

                            <!-- Grupo: Número de placa -->
                            <div class="formulario__grupo" id="grupo__placa">
                                <label for="placa" class="formulario__label">No. Placa</label>
                                <div class="formulario__grupo-input">
                                    <input type="text" class="formulario__input" name="placa" id="placa" placeholder="Número de placa" required>
                                </div>
                            </div>

                            <!-- Grupo: Marca -->
                            <div class="formulario__grupo" id="grupo__marca">
                                <label for="marca" class="formulario__label">Marca</label>
                                <div class="formulario__grupo-input">
                                    <input type="text" class="formulario__input" name="marca" id="marca" placeholder="Marca del vehículo" required>
                                </div>
                            </div>

                            <!-- Grupo: Modelo -->
                            <div class="formulario__grupo" id="grupo__modelo">
                                <label for="modelo" class="formulario__label">Modelo</label>
                                <div class="formulario__grupo-input">
                                    <input type="text" class="formulario__input" name="modelo" id="modelo" placeholder="Modelo del vehículo" required>
                                </div>
                            </div>

                            <!-- Grupo: Año -->
                            <div class="formulario__grupo" id="grupo__año">
                                <label for="año" class="formulario__label">Año</label>
                                <div class="formulario__grupo-input">
                                    <input type="text" class="formulario__input" name="año" id="año" placeholder="Año del vehículo" required>
                                </div>
                            </div>

                            <!-- Grupo: Kilometraje -->
                            <div class="formulario__grupo formulario__grupo-kilo" id="grupo__kilometraje">
                                <label for="kilometraje" class="formulario__label">Kilometraje</label>
                                <div class="formulario__grupo-input">
                                    <input type="text" class="formulario__input" name="kilometraje" id="kilometraje" placeholder="Último kilometraje" required>
                                </div>
                            </div>

                        </div>

                        <div class="step__footer formulario__grupo formulario__grupo-btn-enviar">
                            <button type="button" class="formulario__btn step__button step__button--next" data-to_step="2" data-step="1">Siguiente</button>
                        </div>
                        <div class="alert alert-danger formulario__input-error" id="error-placa" role="alert">
                            No.Placa: no puede quedar vacio, solo puede contener letras, números y guion.
                        </div>
                        <div class="alert alert-danger formulario__input-error" id="error-marca" role="alert">
                            Marca: no puede quedar vacio, solo puede contener letras.
                        </div>
                        <div class="alert alert-danger formulario__input-error" id="error-modelo" role="alert">
                            Modelo: no puede quedar vacio, solo puede contener letras y números.
                        </div>
                        <div class="alert alert-danger formulario__input-error" id="error-año" role="alert">
                            Año: no puede quedar vacio, solo puede contener números.
                        </div>
                        <div class="alert alert-danger formulario__input-error" id="error-kilometraje" role="alert">
                             Kilometraje: no puede quedar vacio, solo puede contener números.
                        </div>
                    </div>

                    <div class="step" id="step-2">

                        <div class="step__header">
                            <h2 class="step__title">Selecciona una fecha, hora y el servicio que requieres</h2>
                        </div>

                        <div class="step__body">

                            <!-- Grupo: Fecha -->
                            <div class="formulario__grupo" id="grupo__fecha">
                                <label for="fecha" class="formulario__label">Seleccione la fecha</label>
                                <div class="formulario__grupo-input">
                                    <input type="date" class="formulario__input" onchange="sele();" name="fecha" id="fecha" placeholder="Fecha" required>
                                </div>
                            </div>
                            <!-- Grupo: Hora -->
                            <div class="formulario__grupo" id="grupo__hora">
                                <label for="hora" class="formulario__label">Seleccione la hora</label>
                                <div class="formulario__grupo-input">
                                    <select class="formulario__select" name="hora" id="hora">
                                        <option value="default" selected disabled>Horarios disponibles</option>
                                    </select>
                                </div>
                            </div>

                            <!-- Grupo: Servicio -->
                            <div class="formulario__grupo" id="grupo__servicio">
                                <label for="servicio" class="formulario__label">Seleccione el servicio</label>
                                <div class="formulario__grupo-input">
                                    <select class="formulario__select" name="servicio" id="servicio">
                                        
                                        <option value="default" selected disabled>Servicios disponibles</option>
                                        <%=request.getSession().getAttribute("servAct").toString()%>
                                    </select>
                                </div>
                            </div>

                        </div>

                        <div class="step__footer formulario__grupo formulario__grupo-btn-enviar">
                            <button type="button" class="formulario__btn step__button step__button--back" data-to_step="1" data-step="2">Regresar</button>
                            <button type="button" class="formulario__btn step__button step__button--next" data-to_step="3" data-step="2">Siguiente</button>
                        </div>

                        <div class="alert alert-danger formulario__input-error" id="error-fecha" role="alert">
                            Debe seleccionar una fecha para su cita. Recuerde que el día no puede ser domingo.
                        </div>
                        <div class="alert alert-danger formulario__input-error" id="error-hora" role="alert">
                            Debe seleccionar una hora para su cita.
                        </div>
                        <div class="alert alert-danger formulario__input-error" id="error-servicio" role="alert">
                            Debe seleccionar un servicio para su cita.
                        </div>

                    </div>

                    <div class="step" id="step-3">

                        <div class="step__header">
                            <h2 class="step__title">Confirma tu servicio</h2>
                        </div>

                        <div class="step__body">
                            
                            <p class="negrita parrafo1">Estos son los datos para tu servicio, revisalos antes de confirmar tu cita: </p>
                            <br><br>

                            <div class="formulario__texto">
                                <p class="negrita">No. Placa: </p>
                                <p id="valor-placa">Default</p>
                            </div>
                            
                            <div class="formulario__texto">
                                <p class="negrita">Marca: </p>
                                <p id="valor-marca">Default</p>
                            </div>

                            <div class="formulario__texto">
                                <p class="negrita">Modelo: </p>
                                <p id="valor-modelo">Default</p>
                            </div>

                            <div class="formulario__texto">
                                <p class="negrita">Año: </p>
                                <p id="valor-año">Default</p>
                            </div>

                            <div class="formulario__texto">
                                <p class="negrita">Kilometraje: </p>
                                <p id="valor-kilometraje">Default</p>
                            </div>
                            
                            <div class="formulario__texto">
                                <p class="negrita">Fecha: </p>
                                <p id="valor-fecha">Default</p>
                            </div>

                            <div class="formulario__texto">
                                <p class="negrita">Hora: </p>
                                <p id="valor-hora">Default</p>
                            </div>

                            <div class="formulario__texto">
                                <p class="negrita">Servicio: </p>
                                <p id="valor-servicio">Default</p>
                            </div>
                            
                            <br><br>
                            <p class="negrita parrafo2">Tu servicio se realizará en Lubrillantas Jezreel AG, Av 5 #0N-54 Barrio la merced </p>
                            
                        </div>

                        <div class="step__footer formulario__grupo formulario__grupo-btn-enviar">
                            <button type="button" class="formulario__btn step__button step__button--back" data-to_step="2" data-step="3">Regresar</button>
                            <button type="submit" class="formulario__btn green step__button">Confirmar cita</button>
                        </div>
                        
                    </div>

                </div>

            
            </form>
        </section>
    </main>
        <script>
            function sele(){
                let dias = ['MONDAY','TUESDAY','WEDNESDAY','THURSDAY','FRIDAY','SATURDAY','SUNDAY'];
                let fecha = document.getElementById('fecha');
                let diaNum = new Date(fecha.value);
                let num = diaNum.getDay();
                let diaSeleccionado = dias[num];
                let horaActual = new Date();
                let horarios = '<%=request.getSession().getAttribute("horarios").toString()%>'.split(';');
                var select = document.getElementById('hora');
                select.innerHTML ="<option value=\"default\" selected disabled>Horarios disponibles</option>";
                
                if(diaSeleccionado!=='SUNDAY'){
                    
                    for(let i =0; i<horarios.length; i++){         //TOMO SEMANA POR SEMANA
                    let horarioDia = horarios[i].split(',');
                    
                    
                    if(horarioDia[0]===diaSeleccionado){  //horario = lunes,7,8,9..
                        
                        for(let k =1; k<horarioDia.length; k++){
                             
                             var option = document.createElement("option");
                           
                             let hora = horarioDia[k]; 
                             if(diaSeleccionado===dias[horaActual.getDay()-1]){
                                 
                                if(hora>horaActual.getHours()){ 

                                       let formatHora = hora>12?(hora-12)+':00 p.m':hora+':00 a.m';
                                       option.value = horarioDia[0]+" , "+formatHora; //value sera dia y hora
                                       option.innerHTML = formatHora;
                                       select.options.add(option);
                               }
                            }else{
                                       let formatHora = hora>12?(hora-12)+':00 p.m':hora+':00 a.m';
                                       option.value = horarioDia[0]+" , "+formatHora; //value sera dia y hora
                                       option.innerHTML = formatHora;
                                       select.options.add(option);
                            }
                             
                        }
                       break;
                    }
                  }
                }

            }
                                
                            </script>
         <!--FOOTER-->
 <footer>
            <div class="container-fluid">
              <div class="row ">
                <div class="col-12 redes" style="background-color: #00114e;">
                  <a href="https://api.whatsapp.com/send/?phone=573112810082&text&app_absent=0"><img src="<%=basePath%>/img/whatsapp.png" ></a>
                  <a href="https://web.facebook.com/profile.php?id=100075532121136"><img src="<%=basePath%>/img/facebook.png" ></a>
                  <a href="https://www.instagram.com/lubrillantasjag/"><img src="<%=basePath%>/img/instagram.png" ></a>
                </div>
              </div>
              <div class="row" style="background-color: #001971;">
        
                <div class="col-12 col-sm-4 col-md-4 col-lg-4">
                  <img src="<%=basePath%>/img/LogoLJAG.png" alt="Logo Jezreel" id="imgFooter">
                </div>
        
                <div class="col-12  col-sm-4 col-md-4 col-lg-4 horario" >
                  <h4 >HORARIOS DE ATENCIÓN</h4>
                  <p>Lunes a Viernes</p>
                  <p>7:30 AM a 6:00 PM</p>
                  <p>Sábado</p>
                  <p>7:30 AM a 5:00 PM</p>
                </div>
      
                <div class="col-12  col-sm-4 col-md-4 col-lg-4 footer-contacto" >
                  <h4 > CONTACTO </h4>
                  <P>Av 5 # 0N-54 Barrio La Merced</P>
                  <p>San José de Cúcuta - Colombia</p>
                  <p>albeirofonseca74@gmail.com</p>
                  <p>+57 3112810082</p>
                </div>
      
              </div>
            </div>
          </footer>
  <!--FIN FOOTER-->

        <!-- Importamos el javascript -->
        <script src="<%=basePath%>js/agendarCita.js" type="text/javascript"></script>
       
        <!-- Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
        <script src="<%=basePath%>js/sesion.js"></script>
    </body>
</html>
