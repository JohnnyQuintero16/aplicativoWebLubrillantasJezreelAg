# Aplicativo Web LubrillantasJezreelAg

## Tabla de contenido
1. [Resumen](#Resumen)
2. [Estructura del Repositorio](#Estructura-del-Repositorio)
3. [Ramas con restricciones](#Ramas-con-restricciones)
4. [Permisos para fusionar la rama main](#Permisos-para-fusionar-la-rama-main)

### Resumen
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

### Estructura del Repositorio
El repositorio esta conformado por 12 ramas en total:
1. Main: Rama principal del proyecto
2. Test: Rama encargada de testear el código antes de fusionar con la rama principal.
3. Backend: Rama exclusivamente para manejar todo el código del equipo de backend.
4. Frontend: Rama exclusivamente para manejar todo el código del equipo de frontend.
5. Las ramas restantes, osea 8 ramas, son las que cada integrante de equipo va poder manejar.

### Ramas con restricciones
Las siguientes ramas tienen restricciones, es decir, para poder fusionar nuevos cambios deben ser aprobadas por los lideres que se han asignado.
1. Main.
2. Test.
3. Backend.
4. Frontend.

### Permisos para fusionar la rama main
Los que aprueban los cambios en esta rama son: Jarlin Fonseca y Samantha Zamora.
EL único que puede hacer pull request desde la rama [Test](https://github.com/JohnnyQuintero16/aplicativoWebLubrillantasJezreelAg/tree/Test) es: Yunior Castilla.

A continuación los pasos para hacer pull request (osea lo que debe hacer Yoel):
1. Ingresar al apartado de pull resquest [link](https://github.com/JohnnyQuintero16/aplicativoWebLubrillantasJezreelAg/pulls), click en New Pull Request.
2. En este [link](https://drive.google.com/file/d/1b063nnV-WOM20CJssUnW5CyaTySwL2Ma/view?usp=sharing) podra ver un ejemplo sobre como debe configurar los ramas para crear el pull request.
3. En este [link](https://drive.google.com/file/d/1du_5xyo5k_lhq8DXNflXDRETj-4cknB8/view?usp=sharing) podra ver la interfaz para crear la pull request, en nombre de rama va la asignada para crear el pull, en este caso, se pone test y abajo la descripción.
4. En la parte izquierda aparece para poder solicitar aprobación de los encargados, en este caso seria Jarlin y Samantha, los selecciona y listo ya le da en crear la pull request.
5. Por ultimo esperar la aprobación de los jefes.

## Nota: Es importante que se cumpa este orden de asignación sobre quien aprueba y quien puede hacer los pull request, de lo contrario surgirian conflictos.
