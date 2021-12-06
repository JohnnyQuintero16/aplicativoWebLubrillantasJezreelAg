var infoServicios, infoProductos, ServicioArr;

let infoDataJSP = (servicio, productos) => {
  infoServicios = servicio;
  infoProductos = productos;
};

const listaServicio = document.querySelector("#TablaServicioCliente");
var index = 0;
listaServicio.addEventListener("click", (e) => {
  index++;
  eliminarServicio(e, index);
});
const btn = document.querySelectorAll(".btnAddServicio");

function addServicio(servicio) {
  const tr = document.createElement("TR");
  let td1 = document.createElement("TD");
  let td2 = document.createElement("TD");
  let td3 = document.createElement("TD");
  let input = document.createElement("INPUT");
  let buton = document.createElement("BUTTON");
  ServicioArr = servicio.split(";");
  var arr = ServicioArr[0].split(",");
  td1.value = arr[0];
  td1.innerHTML = arr[0];
  btn[arr[2]].innerHTML = "-";
  btn[arr[2]].style.backgroundColor = "#ff1";
  btn[arr[2]].disabled = true;
  btn[arr[2]].dataset.id= arr[0];
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
}
let eliminarServicio = (e, index) => {
  if (e.target.classList.contains("eliminarServicio")) {
      console.log(e.target.classList("eliminarServicio"));
      var arr = ServicioArr[0].split(",");
      btn[arr[2]].dataset.id
      btn[arr[2]].innerHTML = "+";
      btn[arr[2]].style.backgroundColor = "green !important";
      btn[arr[2]].disabled = false;
      e.target.parentNode.parentNode.remove();
  }
};
