//Servicios
let servicios = [];
const listaServicio = document.querySelector('#TablaServicioCliente');
const btn = document.getElementById("btnMasServicio");
btn.addEventListener("click", (event) => {
    btn.innerHTML = "-";
    btn.style.backgroundColor = "#ff1";
    btn.disabled = true;
    let producto = event.target.parentNode.parentNode;
  cargarItemsServicio(producto);
});

function cargarItemsServicio(producto) {
    console.log(producto);
    const tr = document.createElement("TR");
    let td1 = document.createElement("TD");
    let td2 = document.createElement("TD");
    let td3 = document.createElement("TD");
    td1.value = producto.children[0].value;
    td1.innerHTML = producto.children[0].value;
    td2 = producto.children[1];
    td2.value = producto.children[1].value;
    td3.classList.add('btn', 'btn-primary', 'btn-lg');
    td3.textContent = 'X';
    td3.style.backgroundColor = 'red !important';
    td3.addEventListener('click', eliminarServicioLista);
    tr.appendChild(td1);
    tr.appendChild(td2);
    tr.appendChild(td3);
    listaServicio.appendChild(tr);
}

function eliminarServicioLista(){
    console.log("entra");

}
