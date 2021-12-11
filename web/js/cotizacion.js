var infoServicios, infoProductos, ServicioArr;
var serviciosSplit = [],
  productosSplit = [];
var productos = [];
var servicioSelect = "";
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

const modal = document.getElementById("exampleModal");
let modalHeader = document.getElementById("modal-header").content;
let modalBody = document.getElementById("modalBody").content;
let sumaProducto = 0;
modal.addEventListener("show.bs.modal", (e) => {
  productos = [];
  servicioSelect = " ";
  let padre = document.getElementById("modal-Body-Cuerpo");
  while (padre.hasChildNodes()) {
    padre.removeChild(padre.firstChild);
  }
  /*for (let i = 0; i < padre.children.length; i++) {
    document.getElementById("modal-Body-Cuerpo").children[i].remove();
  }*/
  sumaProducto = 0;
  cargarProductos(e.relatedTarget.dataset.id);
});

document.getElementById("modal-Body-Cuerpo").addEventListener("click", (e) => {
  cargarButon(e);
});

let entroYA = 0;
let cargarProductos = (idServicio) => {
  for (let i = 0; i < serviciosSplit.length; i++) {
    if (idServicio == serviciosSplit[i][0]) {
      servicioSelect = serviciosSplit[i];
      consultarTipoProductos(serviciosSplit[i][2]);
    }
  }
};
let consultarTipoProductos = (tipo) => {
  for (let i = 0; i < productosSplit.length; i++) {
    let objPro = {};
    if (productosSplit[i][3] == tipo) {
      objPro = {
        nombre: productosSplit[i][1],
        marca: productosSplit[i][2],
        precio: productosSplit[i][4],
      };
      productos.push(objPro);
    }
  }
  pintarModal();
};
let pintarModal = () => {
  pintarHeader();
  pintarBody();
};

let pintarHeader = () => {
  if (entroYA != 0) {
    document.getElementById("headerModal").children[1].remove();
    entroYA = 0;
  }
  let h4 = modalHeader.querySelector("h4");
  let span = modalHeader.querySelector("span");
  let p = modalHeader.querySelectorAll("p");
  h4.textContent = servicioSelect[1];
  span.textContent = servicioSelect[3] + "minutos";
  p[1].textContent = formatCurrency("es-CO", "COP", 2, sumaProducto);
  const clone = modalHeader.cloneNode(true);
  const fragment = document.createDocumentFragment();
  fragment.appendChild(clone);
  document.getElementById("headerModal").appendChild(fragment);
  entroYA++;
};

let entroYaPro = 0;
let pintarBody = () => {
  let padre = document.getElementById("modal-Body-Cuerpo");
  if (entroYaPro != 0) {
    while (padre.hasChildNodes()) {
      padre.removeChild(padre.firstChild);
    }
    /*for (let i = 0; i < padre.children.length; i++) {
    document.getElementById("modal-Body-Cuerpo").children[i].remove();
  }*/
    entroYaPro = 0;
  }

  const fragment = document.createDocumentFragment();
  for (let i = 0; i < productos.length; i++) {
    let p = modalBody.querySelectorAll("p");
    p[0].textContent = productos[i].nombre;
    p[1].textContent = productos[i].marca;
    p[2].textContent = formatCurrency(
      "es-CO",
      "COP",
      2,
      productos[i].precio * 1.19
    );
    const clone = modalBody.cloneNode(true);
    fragment.appendChild(clone);
  }

  document.getElementById("modal-Body-Cuerpo").appendChild(fragment);

  sumaProducto += productos[0].precio * 1.19;
  padre.querySelector("button").classList.remove("btn-outline-success");
  padre.querySelector("button").classList.add("btn-outline-danger");
  padre.querySelector("button").innerHTML = "Seleccionado";
  pintarHeader();

  entroYaPro++;
};

let cargarButon = (e) => {
  if (e.target.classList.contains("btnProducto")) {
    let nodoPadre = e.target.parentNode.parentNode;
    let nombre = nodoPadre.children[0].querySelector("p").textContent;
    let valorProducto = searchProductoValor(nombre);
    if (e.target.classList.contains("btn-outline-success")) {
      e.target.classList.remove("btn-outline-success");
      e.target.classList.add("btn-outline-danger");
      e.target.innerHTML = "Seleccionado";
      sumaProducto += valorProducto;
    } else {
      e.target.classList.remove("btn-outline-danger");
      e.target.classList.add("btn-outline-success");
      e.target.innerHTML = "Seleccionar";
      sumaProducto -= valorProducto;
    }
    pintarHeader();
  }
};

let searchProductoValor = (nombre) => {
  let valor = 0;
  for (let i = 0; i < productos.length; i++) {
    if (nombre == productos[i].nombre) {
      valor = parseInt(productos[i].precio * 1.19);
      break;
    }
  }
  return valor;
};

function formatCurrency(locales, currency, fractionDigits, number) {
  var formatted = new Intl.NumberFormat(locales, {
    style: "currency",
    currency: currency,
    minimumFractionDigits: fractionDigits,
  }).format(number);
  return formatted;
}
