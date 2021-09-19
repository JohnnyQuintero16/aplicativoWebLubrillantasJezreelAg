# aplicativoWebLubrillantasJezreelAg
#### LUBRILLANTAS JEZREEL AG es una micro-empresa que se desempeña en el sector comercial relacionado con los vehiculos, en el que ofrece todo tipo de servicios de mantenimiento de cambio de aceite, servicio de frenos de suspension, montallantas y venta de productos. Se desea tener una mayor visiblidad de sus servicios y productos por medio de un aplicativo web, el cual permita llevar un control y registro de sus clientes, por medio de fichas tecnicas ademas que los clientas tengan la opcion de agendar su cita para un mejor servicio y que puedan realizar cotizaciones para que tengan claro los precios al momento de llevar su vehiculo. 

### El aplicativo web tendra los siguientes modulos: 

* Módulo de agendamiento: En este modulo los clientes de la empresa podran agendar la cita mediante un cronograma que muestre las fechas y horas disponibles, en el cuál también incluirán el servicio que necesite.

* Modulo prestacion de servicios: el cliente del negocio al momento de ingresar su vehiculo a la zona del servicio primero se revisara si tiene una ficha tecnica, si en este caso no tiene entonces el administrador del sistemas debera registrar el nuevo cliente con su datos personales y del vehiculo(Modelo, placa, kilometraje, etc).

Luego que el vehiculo este registrado, se continua con la revisión o el cliente del negocio mencionará que servicio quiere que le realicen a su vehiculo, lo anterior es si el cliente no viene con una cita de agendamiento, luego de saber que servicio se realizará, el administrador del sistema registrará el servicio ademas se agregaran los productos utilzados, este servicio queda registrado en la ficha tecnica del cliente.

El cliente del negocio podra revisar el historial de los servicios que ha realizado al momento de loguearse, tendra un apartado donde le aparece una lista de todos los servicios, con su fecha, precio ademas al escoger un servicio, le muestra los insumos y productos usados con su respectivo nombre, precio, referencia, y al final el precio total.

Al finalizar el servicio, el cliente del negocio podrá realizar una valoración y calificación del servicio prestado.

Los clientes del negocio recibirán notificaciones por correo electronico, en donde se les recuerda que revisen su kilometraje para que realicen el mantenimiento del cambio de aceite.

* Módulo Cotización: Los clientes del negocio podran cotizar los servicios que ofrece la empresa por medio de un apartado tipo simulacion que le da la opcion de esocger los servicios y le muestra los productos que se usaran y el precio del servicio y los insumos utilizados.

* Modulo de reporte y estadisticas: en este modulo el adminitrador del sistema podra consultar y obtener reportes de los agendamientos,clientes y servicios realizados en el mes, asi como realizar comparaciones entre varios meses y  mostrar gráficos estadisticos.

## Intalación

#### El repositorio se creo de manera  privada, por tanto deben seguir los siguientes pasos para que pueda tener el repositorio localmente:

1. Dar click en la parte derecha superior, precisamente en la foto de perfil, les aparecera un menu despegable.
2. Seleccionar la opción Settings
3. Buscar la opción Developer settings, seguidamente dar click en Personal access tokens
   1. Si aparece que ya tiene un token, es decir, les debe aparecer un cuadro con una descripción y en la parte derecha de ese cuadro aparece el boton "delete". Entonces pueden    dar click en el nombres que les aparece en azul, en la parte izquierda.
      1. Al entrar puede dar click en el boton "Regenerate token".
      2. Les va salir que expiración va tener el token, selecciona "No expiration".
      3. Seguidamente les aparece una llave ssh que es el token que van a guardar, copiar y pegar. Es importante que lo haga para poder usar el repositorio.
      4. Seguidamente debe marcar todas las casillas para que esa misma llave pueda usarse para cualquier cosa a futuro.
      5. Por ultimo le da click en "Update token"
      6. Listo completado el primer paso. :smile:

   2. Si NO le aparece que ya tiene un token, es decir, les aparece vacio
      1. Le da click en "Generate new token".
      2. Le saldra un cuadro que tiene "note" en ese cuadro puede poner cualquuier nombre a su token.
      3. Seguidamente seleccionara que la "NO expiration"
      4. Seguidamente debe marcar todas las casillas para que esa misma llave pueda usarse para cualquier cosa a futuro.
      5. Por ultimo le da click en "Generate token"
      6. Seguidamente les aparece una llave ssh que es el token que van a guardar, copiar y pegar. Es importante que lo haga para poder usar el repositorio.
      7. Listo completado el primer paso. :smile:

4. teniendo el token, se dirige a su carpeta local en el que desea guardar el repositorio.
5. Abre la terminal para que escriba el siguiente comando: 

git clone [https://github.com/JohnnyQuintero16/aplicativoWebLubrillantasJezreelAg.git](https://github.com/JohnnyQuintero16/aplicativoWebLubrillantasJezreelAg.git)

7. Aparecera lo siguiente: 

Cloning into 'aplicativoWebLubrillantasJezreelAg'...

Username for 'https://github.com': Username de github

Password for 'https://JohnnyQuintero16@github.com': el token que se genero, lo copian y pega, la consola no lo deja ver


* En username y password, se llenan los datos, seguidamente se termina la clonación con exito:

remote: Enumerating objects: 3, done.

remote: Counting objects: 100% (3/3), done.

remote: Total 3 (delta 0), reused 0 (delta 0), pack-reused 0

Unpacking objects: 100% (3/3), 631 bytes | 631.00 KiB/s, done.

7. Por ulitmo independientemente del editor de texto que usen, posiblemente les pida una clave al abrir la carpeta de manera local, la clave es la de su github.
8. Listo, felicitaciones ya tiene agregado el repositorio de manera local. :wink:
