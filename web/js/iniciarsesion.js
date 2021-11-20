/*
/*

*/// Step 2) Constante, accedemos al formulario: form action ...
/*const formulario = document.getElementById('formulario');
// Step 3) Constante, almacenamos todos los inputs del formulario, arreglo
const inputs = document.querySelectorAll('#formulario input');

// Step 1) Objeto expresiones regulares
const expresiones = {
	password: /^.{4,12}$/, // 4 a 12 digitos.
	cedula: /^\d{8,10}$/, // 8 a 10 numeros.
}

// Objeto, representa si un campo está válido o no.
const campos = {
	password: false,
	cedula: false,
}

/* Step 6) Función validar formulario:

	- Cuando se ejecutan los EventListener, se debe identificar cuál es el input que se quiere comprobar, ya que cada uno son diferentes
	al otro, cuentan con un name en cada input html.

	- Un switch case para identificar el caso en el que el input tenga un name X, ejecute una función..
	- validarCampo(): Compruebe que lo escrito en el input coincida con la expresión regular, por medio de su input y name.
*/
/*
const validarFormulario = (e) => {
	switch (e.target.name) { // Target identifica cuál es el campo que se está seleccionando gracias al name. 
		case "password":
			validarCampo(expresiones.password, e.target, 'password');
			validarPassword2();
		break;

		case "cedula":
			validarCampo(expresiones.cedula, e.target, 'cedula');
		break;
	}
}
/* Cambia clases de error a correcto, para los estilos css con sus colores e iconos. 
o viceversa.
*/
/*
const validarCampo = (expresion, input, campo) => {
	if(expresion.test(input.value)){
		document.getElementById(`grupo__${campo}`).classList.remove('formulario__grupo-incorrecto');
		document.getElementById(`grupo__${campo}`).classList.add('formulario__grupo-correcto');
		document.querySelector(`#grupo__${campo} i`).classList.add('fa-check-circle');
		document.querySelector(`#grupo__${campo} i`).classList.remove('fa-times-circle');
		document.querySelector(`#grupo__${campo} .formulario__input-error`).classList.remove('formulario__input-error-activo');
		campos[campo] = true;
	} else {
		document.getElementById(`grupo__${campo}`).classList.add('formulario__grupo-incorrecto');
		document.getElementById(`grupo__${campo}`).classList.remove('formulario__grupo-correcto');
		document.querySelector(`#grupo__${campo} i`).classList.add('fa-times-circle');
		document.querySelector(`#grupo__${campo} i`).classList.remove('fa-check-circle');
		document.querySelector(`#grupo__${campo} .formulario__input-error`).classList.add('formulario__input-error-activo');
		campos[campo] = false;
	}
}



/*  Step 5)
  Por cada input de nuestro formulario:

- Evento | keyup: Cuando el usuario digite algo en un input, al momento que suelta la tecla al levantarlo, ejecute función validarFormulario para
  ir comprobando los campos al mismo tiempo.

- Evento | blur: Cuando el usuario de un clic fuera del input, ejecute la función validarFormulario para ir validando los campos al mismo
  tiempo.
*/
/*
inputs.forEach((input) => {
	input.addEventListener('keyup', validarFormulario);
	input.addEventListener('blur', validarFormulario);
});

/* Step 4)
    Cuando el usuario presione el botón (type=submit), ejecute una función (tipo flecha).
   - Validar todos los campos (incluyendo el de términos y condiciones)
   - Reiniciar los campos

   Parámetro e = evento.

*//*
formulario.addEventListener('submit', (e) => {
	/* e.preventDefault(); */ //No haga nada al enviar, cuando enviemos los datos debemos quitarlo.
/*
	const terminos = document.getElementById('terminos');
	if(campos.password && campos.cedula  && terminos.checked ){
		formulario.reset(); //Reinice todos los campos del formulario.

		document.getElementById('formulario__mensaje-exito').classList.add('formulario__mensaje-exito-activo');
		// Elimine el mensaje de éxito después de 5 segundos.
		setTimeout(() => {
			document.getElementById('formulario__mensaje-exito').classList.remove('formulario__mensaje-exito-activo');
		}, 5000); 

		//Remueva todos los iconos luego de enviar.

		document.querySelectorAll('.formulario__grupo-correcto').forEach((icono) => {
			icono.classList.remove('formulario__grupo-correcto');
		});
	} else {
		// Si los campos o algunos no están completos, muestre el mensaje de error.
		document.getElementById('formulario__mensaje').classList.add('formulario__mensaje-activo');
		setTimeout(() =>{
			document.getElementById('formulario__mensaje').classList.remove('formulario__mensaje-activo');
		}, 4000);
	}
});*/
