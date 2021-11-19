
function sesion(opUser){

	if(opUser == "null"){
		var padre = document.getElementById('navbarSupportedContent');
		var tmp = document.getElementById('NoSesion');
		var clone = document.importNode(tmp.content, true);
		padre.appendChild(clone);
	}else{
		var padre = document.getElementById('navbarSupportedContent');
		var tmp = document.getElementById('SiSesion');
		var clone = document.importNode(tmp.content, true);
		padre.appendChild(clone);
	}

}