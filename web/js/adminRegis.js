/*

        ESTO ES UN AVISO IMPORTANTE, JAY DE QUIEN ME VAYA A TOCA ESTE CÓDIGO
        VAMO A TENER PROBLEMAS SERIOS DONDE DEJE DE FUNCIONAR

        BAI
*/
var infoServicios, infoProductos, ServicioArr;
var serviciosSplit = [],
  productosSplit = [];

let infoDataJSP = (servicio, productos) => {
  infoServicios = servicio;
  infoProductos = productos;

  infoServicios.forEach((element) => {
    serviciosSplit.push(element.split(","));
  });

  infoProductos.forEach((element) => {
    productosSplit.push(element.split(","));
  });
};

const listaServicio = document.querySelector("#TablaServicioCliente");
const listaProduco = document.querySelector("#tablaBodyPro");
const templaeteItemPro = document.getElementById(
  "TablaProductosCliente"
).content;
const selectOption = document.getElementById("selectProductos");
const limpiarPage = document.getElementById("limpiarPage");
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
    let deleteIndex = e.target.dataset.id;
    let pos = itemSelects.indexOf(deleteIndex);
    e.target.parentNode.parentNode.remove();
    itemSelects.splice(pos, 1);
    for (var i = 0; i < serviciosSplit.length; i++) {
      if (serviciosSplit[i][0] === deleteIndex) {
        if (serviciosSplit[i].length > 3) {
          let posicionesEliminar = [];
          for (var k = 2; k < serviciosSplit[i].length; k++) {
            itemsTiposProductos.delete(serviciosSplit[i][k]);
          }
        } else {
          itemsTiposProductos.delete(serviciosSplit[i][2]);
        }
      }
    }
  }
  if (itemSelects.length === 0) {
    itemsTiposProductos.clear();
    selectOption.disabled = true;
    tableBody.innerHTML = "";
    idsProducto = [];
    entroYa = 0;
    tableFooter.innerHTML =
      '<tr><th scope="row" colspan="4">No se ha seleccionado productos, seleccione!</th></tr>';
  }
  cargarSelectProductos();
};

var itemsTiposProductos = new Set();
let cargarTipoSeleccionado = () => {
  for (var i = 0; i < serviciosSplit.length; i++) {
    for (var j = 0; j < itemSelects.length; j++) {
      if (serviciosSplit[i][0] === itemSelects[j]) {
        if (serviciosSplit[i].length > 3) {
          for (var k = 2; k < serviciosSplit[i].length; k++) {
            itemsTiposProductos.add(serviciosSplit[i][k]);
          }
        } else {
          itemsTiposProductos.add(serviciosSplit[i][2]);
        }
      }
    }
  }
  const proUnicos = [...itemsTiposProductos];
  return proUnicos;
};

let cargarSelectProductos = () => {
  if (selectOption.disabled == true) {
    selectOption.disabled = false;
  }
  limpiarSelect();
  const option = document.createElement("option");
  let optionCargadas = cargarTipoSeleccionado();
  let arrayProductos = cargarProductosTipo(optionCargadas);
  arrayProductos.forEach((element) => {
    const option = document.createElement("option");
    option.text = element.valor;
    option.value = element.id;
    selectOption.appendChild(option);
  });
};

let cargarProductosTipo = (tiposProductos) => {
  let productosArray = [];
  for (var i = 0; i < tiposProductos.length; i++) {
    for (var j = 0; j < productosSplit.length; j++) {
      if (tiposProductos[i] == productosSplit[j][3]) {
        var pro = {
          id: "",
          valor: "",
        };
        pro.id = productosSplit[j][0];
        pro.valor = productosSplit[j][1];
        productosArray.push(pro);
      }
    }
  }
  const proUnicos = [...new Set(productosArray)];
  return proUnicos;
};

const limpiarSelect = () => {
  for (let i = selectOption.options.length; i >= 0; i--) {
    selectOption.remove(i);
  }
};

//Productos
const padreTemplate = document.getElementById("TablaProductosCliente").content;
const templateFooterPrev = document.getElementById(
  "template-footerPrev"
).content;
const templateFooterNex = document.getElementById("template-footerNex").content;
const tableBody = document.getElementById("tablaBodyPro");
const tableFooter = document.getElementById("footer");
let idsProducto = [];
let preciosProductos = [];
tableBody.addEventListener("click", (e) => {
  eliminarProducto(e);
});
tableFooter.addEventListener("click", (e) => {
  calcularCostos(e);
  //limpiarCostos();
});
let cargarProductos = (value) => {
  let existe = idsProducto.indexOf(value);
  if (existe === -1) {
    idsProducto.push(value);
    let productoSeleccionado = searchProducto(value);
    const filasTable = padreTemplate.querySelectorAll("td");
    filasTable[0].textContent = value;
    filasTable[1].textContent = productoSeleccionado[1];
    padreTemplate.querySelector(".eliminarProducto").dataset.id = value;
    padreTemplate.querySelectorAll("input")[1].value = value;
    const fragment = document.createDocumentFragment();
    const clone = padreTemplate.cloneNode(true);
    fragment.appendChild(clone);
    tableBody.appendChild(fragment);
    tableFooter.innerHTML = "";
    cargarFooterPrev();
    entroYa = 0;
  } else {
    alert("Ya tiene seleccionado este producto, elija otro");
  }
};

let searchProducto = (value) => {
  for (let i = 0; i < productosSplit.length; i++) {
    if (productosSplit[i][0] == value) {
      return productosSplit[i];
    }
  }
};

let eliminarProducto = (e) => {
  if (e.target.classList.contains("eliminarProducto")) {
    let pos = idsProducto.indexOf(e.target.dataset.id);
    idsProducto.splice(pos, 1);
    e.target.parentNode.parentNode.remove();
    calcularCostos(e);
  }
};

let cargarFooterPrev = () => {
  const clone = templateFooterPrev.cloneNode(true);
  const fragment = document.createDocumentFragment();
  fragment.appendChild(clone);
  tableFooter.appendChild(fragment);
};

let entroYa = 0;
let totalSinIva = 0.0;
    let total = 0.0,
      totalDescuento = 0.0;
      let descuento = 0;
let calcularCostos = (e) => {
  if (e.target.classList.contains("calcularCostos") || (e.target.classList.contains("eliminarProducto"))) {
    if(entroYa != 0){
      limpiarCostos();
    }
    entroYa++;
    let valorDescuento = document.getElementById("descuento").value;
    let cantidadesProducto = document.getElementsByName("cantidadProducto");
    descuento = parseInt(valorDescuento, 10);
    for (let i = 0; i < cantidadesProducto.length; i++) {
      let productoSelec = searchProducto(
        cantidadesProducto[i].parentNode.parentNode.childNodes[1].textContent
      );
      let cantidadUnitaria = cantidadesProducto[i].value;
      let precioVenta = parseFloat(productoSelec[4]);
      totalSinIva += precioVenta * cantidadUnitaria;
      total += ((precioVenta * 1.19) * cantidadUnitaria);
    }
    totalDescuento = total;
    if (descuento != 0) {
      totalDescuento = (total - ((descuento / 100.0) * totalSinIva));
    }
    templateFooterNex.querySelectorAll("h5")[0].textContent = formatCurrency("es-CO", "COP", 2, Math.round(total));
    templateFooterNex.querySelectorAll("h5")[1].textContent = formatCurrency("es-CO", "COP", 2, Math.round(totalDescuento));
    const clone = templateFooterNex.cloneNode(true);
    const fragment = document.createDocumentFragment();
    fragment.appendChild(clone);
    tableFooter.appendChild(fragment);
  }
};

let limpiarCostos = () => {
  //console.log(e.target.classList.contains("inputDescuento"));
  //if ((e.target.classList.contains("inputDescuento")) || (e.target.classList.contains("calcularCostos"))) {
    if (entroYa != 0) {
      tableFooter.children[2].remove();
      entroYa = 0;
      total = 0;
      totalSinIva = 0;
      totalDescuento = 0;
    }
  //}
};

//Validar kilometraje
let inputIngresa = document.querySelectorAll('#kilometraje');
inputIngresa[1].addEventListener('change', e =>{
  let ingresa = parseInt(inputIngresa[1].value);
  let esta = parseInt(inputIngresa[0].value);
  if(ingresa < esta){
    alert("El kilometraje no puede ser menor al que esta registrado, por favor verifique");
  }
});

let returnDataJS = (tipo) =>{
  let body = "";
  let tipoMante = parseInt(tipo);
  let fecha = new Date();
  let mes = 0;
  if(tipo == 1){
    mes = 30;
  }else if(tipo == 2){
    mes = 60;
  }else{
    mes = 30;
  }
  fecha.setDate(fecha.getDate() + mes);
  let Costototal = (totalDescuento != 0)?totalDescuento:total;
  
  body = "<div clas = 'container'>" +
  "<h4>Hola! Desde lubrillantas Jezreel AG te enviamos la siguiente información relacionada con el costo de los productos que se emplearon en el servicio de tu vehiculo!</h4>"+
  "<p> En esta tabla te resumimos los productos que se emplearon en el servicio:</p>"+
  "<div style='margin: auto;'>"+
      "<style type='text/css'>"+
          ".tg  {border-collapse:collapse;border-spacing:0;}"+
          ".tg td{border-color:black;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;"+
            "overflow:hidden;padding:10px 5px;word-break:normal;}"+
          ".tg th{border-color:black;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;"+
            "font-weight:normal;overflow:hidden;padding:10px 5px;word-break:normal;}"+
          ".tg .tg-baqh{text-align:center;vertical-align:top}"+
          ".tg .tg-amwm{font-weight:bold;text-align:center;vertical-align:top}"+
          "</style>"+
          "<table class='tg' style='table-layout: fixed; width: 342px'>"+
          "<colgroup>"+
          "<col style='width: 204px'>"+
          "<col style='width: 77px'>"+
          "<col style='width: 100px'>"+
          "</colgroup>"+
          "<thead>"+
            "<tr>"+
              "<th class='tg-amwm'>Nombre Producto<br></th>"+
              "<th class='tg-amwm'>Cantidad</th>"+
              "<th class='tg-amwm'>Costo</th>"+
            "</tr>"+
          "</thead>"+
          "<tbody>"+
          cargarProductosHTML() +
          "</tbody>"+
          "<tfoot>"+
              "<tr>"+
                  "<td colspan = '2'> <h3 class='tg-amwm' >Descuento</h3> </td>"+
                  "<td colspan = '1'><h3 class='tg-amwm'>  " + descuento + "%</h3></td>"+
              "</tr>"+
              "<tr>"+
                  "<td colspan = '2'> <h3 class='tg-amwm' >Costo Total</h3> </td>"+
                  "<td colspan = '1'><h3 class='tg-amwm'>" + formatCurrency("es-CO", "COP", 2, Costototal) + "</h3></td>"+
              "</tr>"+
          "</tfoot>"+
          "</table>"+
          "<h4>Te recodamos que tu vehiculo esta listo para que pases por el! Cualquier inquietud sobre tu costo nos lo puedes hacer saber para dar solución a tu inquietud</h4>"+
          "<h3 style='color: blue;'>Por utlimo!</h3>"+
          "<h4>Te recodamos que el proximo mantenimiento de tu vehiculo es el "+ 
            "Dia " + fecha.getDate().toString() +
            " de " + mesUTC(fecha.getMonth().toString()) +
            " del año " + fecha.getFullYear().toString() +
            ", esperamos que vuelvas!</h4>"+
          "<h2 style='color: crimson;'>Feliz Navidad te deseamos la familia de Lubrillantas Jezreel AG</h2>"+
  "</div>"+
"</div>";

  return body;
}

let mesUTC = (mes) =>{
  if(mes == 0) return "Enero";
  if(mes == 1) return "Febrero";
  if(mes == 2) return "Marzo";
  if(mes == 3) return "Abril";
  if(mes == 4) return "Mayo";
  if(mes == 5) return "Junio";
  if(mes == 6) return "Julio";
  if(mes == 7) return "Agosto";
  if(mes == 8) return "Septiembre";
  if(mes == 9) return "Octubre";
  if(mes == 10) return "Noviembre";
  if(mes == 11) return "Diciembre";
}

let cargarProductosHTML = () =>{
  let html = "";
  let cantidadesProducto = document.getElementsByName("cantidadProducto");
  for(let i = 0; i < idsProducto.length; i++){
    let product = searchProducto(idsProducto[i]);
    html+="<tr>"+
    "<td class='tg-amwm' center>" + product[1] + "</td>"+
    "<td class='tg-amwm' center>" + cantidadesProducto[i].value + "</td>"+
    "<td class='tg-amwm' center>" + formatCurrency("es-CO", "COP", 2, (product[4]*1.19) * cantidadesProducto[i].value) + "</td>"+
  "</tr>";
  }
  return html;
}

function formatCurrency (locales, currency, fractionDigits, number) {
  var formatted = new Intl.NumberFormat(locales, {
    style: 'currency',
    currency: currency,
    minimumFractionDigits: fractionDigits
  }).format(number);
  return formatted;
}

limpiarPage.addEventListener("click", () =>{
  itemsTiposProductos.clear();
    selectOption.disabled = true;
    tableBody.innerHTML = "";
    idsProducto = [];
    cargarSelectProductos();
    console.log("entra");
})