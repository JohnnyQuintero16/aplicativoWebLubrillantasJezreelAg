const form = document.querySelector("form");
eField = form.querySelector(".cedula"),
eInput = eField.querySelector("input"),
pField = form.querySelector(".password"),
pInput = pField.querySelector("input");

form.onsubmit = (e)=>{
  e.preventDefault(); 
  
  (eInput.value == "") ? eField.classList.add("shake", "error") : checkCedula();
  (pInput.value == "") ? pField.classList.add("shake", "error") : checkPass();

  setTimeout(()=>{ 
    eField.classList.remove("shake");
    pField.classList.remove("shake");
  }, 500);

  eInput.onkeyup = ()=>{checkCedula();}
  pInput.onkeyup = ()=>{checkPass();} 

  function checkCedula(){ 
    let expresion = /^\d{8,10}$/; 
    if(!eInput.value.match(expresion)){ 
      eField.classList.add("error");
      eField.classList.remove("valid");
      let errorTxt = eField.querySelector(".error-txt");

      (eInput.value != "") ? errorTxt.innerText = "Ingrese una cédula válida" : errorTxt.innerText = "La cédula no puede estar en blanco";
    }else{ 
      eField.classList.remove("error");
      eField.classList.add("valid");
    }
  }

  function checkPass(){ 
    if(pInput.value == ""){ 
      pField.classList.add("error");
      pField.classList.remove("valid");
    }else{ 
      pField.classList.remove("error");
      pField.classList.add("valid");
    }
  }

  
  if(!eField.classList.contains("error") && !pField.classList.contains("error")){
    window.location.href = form.getAttribute("action"); //redirecting user to the specified url which is inside action attribute of form tag
  }
}
