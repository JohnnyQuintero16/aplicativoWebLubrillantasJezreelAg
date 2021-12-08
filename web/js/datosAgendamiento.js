var select = document.getElementById("inputState");
var vehiculos;
let datosJSP = (data) => {
    vehiculos = data;
    console.log("DATA VEHICULO " + data)
};

var cnt = 0;
select.addEventListener("change", () => {
  cnt++;
  var optionSelec = select.options[select.selectedIndex].value;
  cargarContenido(optionSelec);
});
function cargarContenido(option) {
  console.log("DATA VEHICULO " + vehiculos)
  for (var i = 0; i < vehiculos.length; i++) {
    var arrVehiculo = vehiculos[i].split(",");
    if (arrVehiculo[0] == option) {
      break;
    }
  }
  var campos = document.getElementsByClassName("tmp");
  var div = document.getElementsByClassName("divs");
  document.getElementById("inputPlaca").value = option;
  document.getElementById("inputkm").value = arrVehiculo[2];
  if (cnt > 1) {
    var elem = document.getElementsByClassName("msg");
    while (elem.length > 0) {
      elem[0].remove();
    }
  }
  for (var i = 0; i < div.length; i++) {
    div[i].appendChild(campos[i].content.cloneNode(true));
    document.getElementsByClassName("msg")[i].innerHTML = arrVehiculo[i];
  }
}
