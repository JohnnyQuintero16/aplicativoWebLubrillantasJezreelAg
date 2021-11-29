/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// Obtenemos el formulario.
let form = document.querySelector('.formulario');
let progressOptions = document.querySelectorAll('.progressbar__option');

const inputsOne = document.querySelectorAll('#step-1 input');
const inputTwo = document.querySelector('#step-2 input');
const selectsTwo = document.querySelectorAll('#step-2 select');
setAllowDate(inputTwo);

const expresiones = {
	placa: /^[a-zA-Z0-9\-\s]{4,10}$/, //Letras, numeros, guion, espacios.
	marca: /^[a-zA-ZÀ-ÿ\s]{1,40}$/, // Letras y espacios, pueden llevar acentos.
	modelo: /^[a-zA-Z0-9À-ÿ\s]{1,40}$/, // Letras, espacios, numeros y acentos.
	año: /^\d{4}$/, // 4 números exactos
	kilometraje: /^\d{2,10}$/, // 2 a 10 
    selects: /^(?!.*default).*$/,
	fecha: /^[0-9\-]{10}$/
}

//Escucha el evento clic, ejecute la siguiente función:
form.addEventListener('click', function(e) {
    //Identificamos en qué elemento se hizo el clic, con target.
    let element = e.target;

    // Si el elemento al que dimos clic, classList accede a todas las clases de ese elemento, contiene la clase:
    let isButtonNext = element.classList.contains('step__button--next'); //Botón Siguiente -> Retorna true si contiene la clase, false si no la tiene
    let isButtonBack = element.classList.contains('step__button--back'); //Botón Atras -> Retorna true si contiene la clase, false si no la tiene

    // Si isButtonNext o isButtonBack contiene un botón: (true)
    if (isButtonNext || isButtonBack) {
        //let p1 = isValidatedStepOne();
        /*Identificamos en que paso (step) nos encontramos 
            data-step: En que paso estamos actualmente
            data-to_step: A que paso debemos avanzar
        */

        // currentStep: Paso actual | (idStep-) + el número del data-step (element.dataset.step)
        let currentStep = document.getElementById('step-' + element.dataset.step);

        // jumpStep: Hacia donde avanzamos | (idStep-) + el número del siguiente (to_step= en el boton)
        let jumpStep = document.getElementById('step-' + element.dataset.to_step);
        
        let pasoValidado = true;

        if(element.dataset.step === "1"){
             pasoValidado = isValidatedStepOne();
        }
        else if(element.dataset.step === "2" && isButtonNext){
            pasoValidado = isValdatedStepTwo();
            setValuesStepThree();
        }
        
        if(pasoValidado){
            currentStep.addEventListener('animationend', function callback() {

                currentStep.classList.remove('active');
                jumpStep.classList.add('active');
                if (isButtonNext) {
                    currentStep.classList.add('to-left');
                    progressOptions[element.dataset.to_step - 1].classList.add('active');
                } else {
                    jumpStep.classList.remove('to-left');
                    progressOptions[element.dataset.step - 1].classList.remove('active');
                }
                currentStep.removeEventListener('animationend', callback);
            });
            currentStep.classList.add('inactive');
            jumpStep.classList.remove('inactive');
        }
        
    }
});

function isValidatedStepOne(){

    let pasoValidado = true;

    inputsOne.forEach((input) =>{
        let campoValidado = false;
        switch(input.name){
            case "placa":
                campoValidado = validarCampo(expresiones.placa, input, input.name);
            break;

            case "marca":
                campoValidado = validarCampo(expresiones.marca, input, input.name);
            break;

            case "modelo":
                campoValidado = validarCampo(expresiones.modelo, input, input.name);
            break;

            case "año":
                campoValidado = validarCampo(expresiones.año, input, input.name);
            break;

            case "kilometraje":
                campoValidado = validarCampo(expresiones.kilometraje, input, input.name);
            break;
        }

        //Si campo validado es false, pasoValidado pasa a ser false.
        //Ya que con un solo campo que no pase la validación, se invalidará todo el paso.
        if(!campoValidado){
            pasoValidado = campoValidado;
        }

    });

    return pasoValidado;
}

const validarCampo = (expresion, elemento, campo) => {
    
	if(expresion.test(elemento.value)){
        
		document.getElementById(`grupo__${campo}`).classList.remove('formulario__grupo-incorrecto');
		document.getElementById(`grupo__${campo}`).classList.add('formulario__grupo-correcto');
		document.getElementById(`error-${campo}`).classList.remove('formulario__input-error-activo');

        return true;
	} else {
        
		document.getElementById(`grupo__${campo}`).classList.add('formulario__grupo-incorrecto');
		document.getElementById(`grupo__${campo}`).classList.remove('formulario__grupo-correcto');
        document.getElementById(`error-${campo}`).classList.add('formulario__input-error-activo');

		return false;
	}
}

function isValdatedStepTwo(){
    let pasoValidado = true;

    let campoValidado = false;

    //Validación de los select
    selectsTwo.forEach((select) => {

        campoValidado = validarCampo(expresiones.selects, select, select.name);
           
        //Si campo validado es false, pasoValidado pasa a ser false.
        //Ya que con un solo campo que no pase la validación, se invalidará todo el paso.
        if(!campoValidado){
            pasoValidado = campoValidado;
        }

    });

    campoValidado = validateDate(); //Llama al método que valida el input de fecha.

    if(!campoValidado){
        pasoValidado = campoValidado;
    }

    return pasoValidado;

}

function validateDate(){
    let fechaString = inputTwo.value; //Se obtiene la fecha del input.

    /*Javascript tiene un bug cuando se trata de 
      convertir un string a fecha si el formato tiene guión (-) Por ej: 2021-11-25
      Por esto se aplica un replace para cambiar los guiones por "/". Por ej: 2021/11/25 */
    let fecha = new Date(fechaString.replace(/-/g,'\/')); //Obtiene la fecha completa
    let diaDeLaSemana = fecha.getDay(); //Obtiene el día de la semana

    /*Si es domingo, se coloca el valor del input fecha en null aproposito para 
     que no pase la validación.*/
    if (diaDeLaSemana == 0) 
    {
        inputTwo.value = null;
    }
    
    let campoValidado = validarCampo(expresiones.fecha, inputTwo, inputTwo.name);
    inputTwo.value = fechaString; //Se recupera el valor del input fecha.

    return campoValidado;
}

function setAllowDate(inputFecha) {
   //Obtiene la fecha de hoy con hora y zona horaria incluida.
   let fechaHoy = new Date();
   let offset = fechaHoy.getTimezoneOffset();//Se corrige la zona horaria con el offset, para evitar bug.
   fechaHoy = new Date(fechaHoy.getTime() - (offset*60*1000));//Se corrige la zona horaria con el offset, para evitar bug.

   let hoy = fechaHoy.toISOString().split('T')[0]; //Obtiene la fecha de hoy en formato yyyy-mm-dd

   //Procedimiento para sumar 7 días a la fecha actual, y así obtener la fecha máxima.
   let fecha = fechaHoy;
   fecha.setDate(fecha.getDate() + 7);
   fechaMaxima = fecha.toISOString().split('T')[0];

   //Se le asigna la fecha minima (hoy) y máxima (hoy + 7 días) al input de fecha.
   inputFecha.setAttribute('min',hoy);
   inputFecha.setAttribute('max',fechaMaxima);
}

function setValuesStepThree(){
    //Se asigna el valor a los párrafos del paso 3, para cada input del paso 1.
    inputsOne.forEach((input) => {
        setValue(input);
    });

    //Se asigna el valor a los párrafos del paso 3, para cada select del paso 2.
    selectsTwo.forEach((select) => {
        setValue(select);
    });

    //Se asigna el valor al párrafo del paso 3, para el único input del paso 2.
    setValue(inputTwo);
}

function setValue(element){
    //Busca el parrafo que tenga como id "valor" + el nombre del input o select.
    let parrafo = document.getElementById(`valor-${element.name}`); 
    //Asigna el valor del input o select al parrafo del paso 3.
    parrafo.textContent = element.value;
}
