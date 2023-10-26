// Call the dataTables jQuery plugin
$(document).ready(function() {
	
	cargarUsuarios();
	
  $('#usuarios').DataTable();
});

async function cargarUsuarios(){
	const request = await fetch('api/usuarios', {
		method: 'GET',
		headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json'
		},
		
	});
	const usuarios = await request.json();
	
	//Mostramos todos los usuarios de la base de datos
	
	let listadoHTML='';
	
	for(let usuario of usuarios){
		let botonEliminar='<a href="#" onclick="eliminarUsuario('+usuario.id+ ')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';
		let usuarioHtml='<tr><td>' + usuario.id + '</td><td>' + usuario.nombre + ' ' + usuario.apellido + '</td><td>'
						+ usuario.email + '</td><td>' + usuario.telefono
						+ '</td><td>' + botonEliminar + '</td></tr>';
						
		listadoHTML+=usuarioHtml;
	}
	
	document.querySelector('#usuarios tbody').outerHTML=listadoHTML;				//Mostramos el listado de usuarios de la BD en el tbody de la tabla con el id=usuarios
}

async function eliminarUsuario(id){
	if(!confirm('¿Desea eliminar este usuario?')){
		return;
	}
	
	const request = await fetch('api/usuarios/' + id, {
		method: 'DELETE',
		headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json'
		},
	})
	location.reload();
}


