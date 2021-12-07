var infoServicios, infoProductos, ServicioArr;
var serviciosSplit = [];
let infoDataJSP = (servicio, productos) => {
  infoServicios = servicio;
  infoProductos = productos;

  infoServicios.forEach((element) => {
    serviciosSplit.push(element.split(","));
  });
};

const listaServicio = document.querySelector("#TablaServicioCliente");
const listaProduco = document.querySelector("#tablaBodyPro");
const templaeteItemPro = document.getElementById(
  "TablaProductosCliente"
).content;
const selectOption = document.getElementById("selectProductos");
var index = 0;
listaServicio.addEventListener("click", (e) => {
  eliminarServicio(e);
});
const btn = document.querySelectorAll(".btnAddServicio");

var itemSelects = [];
function addServicio(servicio) {
  ServicioArr = servicio.split(";");
  var arr = ServicioArr[0].split(",");
  let existe = itemSelects.indexOf(arr[0]);
  if (existe == -1) {
    const tr = document.createElement("TR");
    let td1 = document.createElement("TD");
    let td2 = document.createElement("TD");
    let td3 = document.createElement("TD");
    let input = document.createElement("INPUT");
    let buton = document.createElement("BUTTON");
    td1.value = arr[0];
    td1.innerHTML = arr[0];
    itemSelects.push(arr[0]);
    td2.value = arr[1];
    td2.innerHTML = arr[1];
    buton.setAttribute("type", "button");
    buton.setAttribute("style", "background-color: red !important");
    buton.classList.add("btn", "btn-primary", "btn-lg", "eliminarServicio");
    buton.textContent = "-";
    buton.dataset.id = arr[0];
    input.setAttribute("name", "ids");
    input.setAttribute("value", arr[0]);
    input.setAttribute("style", "display: none");
    td3.appendChild(buton);
    tr.appendChild(td1);
    tr.appendChild(td2);
    tr.appendChild(td3);
    tr.appendChild(input);
    listaServicio.appendChild(tr);
    cargarSelectProductos();
  } else {
    alert("Ya selecciono este servicio, por favorseleccione otros");
  }
}

let eliminarServicio = (e) => {
  if (e.target.classList.contains("eliminarServicio")) {
    let pos = itemSelects.indexOf(e.target.dataset.id);
    e.target.parentNode.parentNode.remove();
    itemSelects.splice(pos, 1);
  }
};

let cargarTipoSeleccionado = (servicioSeleccionado) => {

  
  /*const proUnicos = tipoProductos.filter((valor,indice) =>{
    return tipoProductos.indexOf(valor) === indice;
  });*/
  const proUnicos = [...new Set(arrayPro)];
  return proUnicos;
};

let cargarSelectProductos = () => {
  if (selectOption.disabled == true) {
    selectOption.disabled = false;
  }
  const option = document.createElement("option");
  let optionCargadas = cargarTipoSeleccionado(itemSelects);
  console.log(optionCargadas);
};

//Productos
const padreTemplate = document.getElementById("TablaProductosCliente").content;
const hijoTr = padreTemplate.querySelector("tr");
const hijoTd = hijoTr.querySelectorAll("td");
let cntPro = 0;

let idsProducto = [];
function cargarProductos(value) {
  var valor = value.split(",");
  let existe = idsProducto.indexOf(valor[0]);
  if (existe == -1) {
    cntPro++;
    idsProducto.push(valor[0]);
    const fragment = document.createDocumentFragment();
    let input = document.createElement("INPUT");
    input.setAttribute("name", "idp");
    input.setAttribute("value", valor[0]);
    input.setAttribute("style", "display: none");

    hijoTd[0].innerHTML = valor[0];
    hijoTd[0].setAttribute("value", valor[0]);
    hijoTd[0].setAttribute("name", valor[0]);
    hijoTd[1].innerHTML = valor[1];
    hijoTd[1].setAttribute("value", valor[1]);
    hijoTr.appendChild(hijoTd[0]);
    hijoTr.appendChild(hijoTd[1]);
    hijoTr.appendChild(input);
    const clone = hijoTr.cloneNode(true);
    fragment.appendChild(clone);

    document.getElementById("tablaBody").appendChild(fragment);
  } else {
    alert("Ya tiene seleccionado este producto, elija otro");
  }
}
function eliminarElement() {
  element = document.getElementById(cntPro);

  function eliminarElemento(id) {
    padre = element.parentNode;

    padre.removeChild(element);
    cntPro--;
  }
}
