## Aplicativo Web LubrillantasJezreelAg

## Tabla de contenido
1. [Resumen](#Resumen)
2. [Estructura del Repositorio](#Estructura-del-Repositorio)
3. [Ramas con restricciones](#Ramas-con-restricciones)
4. [Rama main](#Rama-main)
   1. [Permisos para fusionar la rama main](#Permisos-para-fusionar-la-rama-main)
5. [Rama Test](#Rama-Test)
    1. [¿Para que es la rama test?](#¿Para-que-es-la-rama-test?)
    2. [Permisos para fusionar la rama Test](#Permisos-para-fusionar-la-rama-Test)
    3. [Envio de cambios a la rama main](#Envio-de-cambios-a-la-rama-main)
6. [Rama Backend & Frontend](#Rama-Backend-&-Frontend)
    1. [¿Para que es?](#¿Para-que-es?)
    2. [Permisos para fusionar la rama Backend & Frontend](#Permisos-para-fusionar-la-rama-Backend-&-Frontend)
    3. [Envio de cambios a la rama Test](#Envio-de-cambios-a-la-rama-Test)
7. [Ramas Personales](#Ramas-personales)

### Resumen
#### LUBRILLANTAS JEZREEL AG es una micro-empresa que se desempeña en el sector comercial relacionado con los vehiculos, en el que ofrece todo tipo de servicios de mantenimiento de cambio de aceite, servicio de frenos de suspension, montallantas y venta de productos. Se desea tener una mayor visiblidad de sus servicios y productos por medio de un aplicativo web, el cual permita llevar un control y registro de sus clientes, por medio de fichas tecnicas ademas que los clientas tengan la opcion de agendar su cita para un mejor servicio y que puedan realizar cotizaciones para que tengan claro los precios al momento de llevar su vehiculo. 

### El aplicativo web tendra los siguientes modulos: 

* Módulo de agendamiento.

* Modulo prestacion de servicios.

* Módulo Cotización.

* Modulo de reporte y estadisticas.

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

### Rama main
#### Permisos para fusionar la rama main
Los que aprueban los cambios en esta rama son: Jarlin Fonseca y Samantha Zamora.
EL único que puede hacer pull request desde la rama [Test](https://github.com/JohnnyQuintero16/aplicativoWebLubrillantasJezreelAg/tree/Test) es: Yunior Castilla.

### Rama Test
#### ¿Para que es la rama test?
La rama test es la rama donde se va poder testear el código si esta correcto o se sugiere algún cambio, en esta rama se reciben dos pull request, que son:
1. La rama de Backend.
2. La rama de frontend.
Esta rama solo se va enviar cambios para fusionar a la rama main, es decir, la rama principal del proyecto.

#### Permisos para fusionar la rama Test

Los que aprueban los cambios en esta rama son: Jarlin Fonseca, Samantha Zamora y Johan Casadiegos. 
* EL único que puede hacer pull request desde la rama Backend es: Johnny Quintero.
* La única que puede hacer pull request desde la rama Fronent es: Susana Rojas.

#### Envio de cambios a la rama main
A continuación los pasos para hacer pull request (osea lo que debe hacer Yoel):
1. Ingresar al apartado de pull resquest [link](https://github.com/JohnnyQuintero16/aplicativoWebLubrillantasJezreelAg/pulls), click en New Pull Request.
2. En este [link](https://drive.google.com/file/d/1b063nnV-WOM20CJssUnW5CyaTySwL2Ma/view?usp=sharing) podra ver un ejemplo sobre como debe configurar los ramas para crear el pull request.
3. En este [link](https://drive.google.com/file/d/1du_5xyo5k_lhq8DXNflXDRETj-4cknB8/view?usp=sharing) podra ver la interfaz para crear la pull request, en nombre de rama va la asignada para crear el pull, en este caso, se pone test y abajo la descripción.
4. En la parte izquierda aparece para poder solicitar aprobación de los encargados, en este caso seria Jarlin y Samantha, los selecciona y listo ya le da en crear la pull request.
5. Por ultimo esperar la aprobación de los jefes.

### Rama Backend & Frontend

#### ¿Para que es?
La rama Backend y Frontend es la rama donde se va contener todo el código realizado por el team de backend 😎 y team de frontend 😥, en esta rama se reciben cualquier pull request, sin emabrgo, solo se aprobaran las ramas que son de cada participante-
Esta rama solo se va enviar cambios para fusionar a la rama Test, es decir, la rama que testea el proyecto.

#### Permisos para fusionar la rama Backend & Frontend

Los que aprueban los cambios:

| Backend| Johnny Quintero 😎 |
| -------- | -------- |
| Frontend | Jarlin Fonseca, Susana Rojas|

* EL único que puede hacer pull request para la rama Backend y Frontend es: cualquiera.

#### Envio de cambios a la rama Test
A continuación los pasos para hacer pull request (osea lo que debe hacer Johnny Quintero Y Susana Rojas):
1. Ingresar al apartado de pull resquest [link](https://github.com/JohnnyQuintero16/aplicativoWebLubrillantasJezreelAg/pulls), click en New Pull Request.
2. En este [link](https://drive.google.com/file/d/1b063nnV-WOM20CJssUnW5CyaTySwL2Ma/view?usp=sharing) podra ver un ejemplo sobre como debe configurar los ramas para crear el pull request.

| Base | Compare |
| -------- | -------- |
| Test | Backend |
| Test | Frontend |

4. En este [link](https://drive.google.com/file/d/1du_5xyo5k_lhq8DXNflXDRETj-4cknB8/view?usp=sharing) podra ver la interfaz para crear la pull request, en nombre de rama va la asignada para crear el pull, en este caso, se pone Backend  O Frontend y abajo la descripción.

6. En la parte Derecha aparece para poder solicitar aprobación de los encargados, los cuales son Jarlin Fonseca, Samantha Zamora y Johan Casadiegos los selecciona y listo ya le da en crear la pull request.
 
7. Por ultimo esperar la aprobación de los jefes.

### Ramas Personales

#### Developers 🥇 😎, esta rama es personal aqui podra hacer lo que quiera, puede trabajar tanto front 😰 como back 😎, eso si antes de hacer push aca a su rama, acutalice su repo local ya sea con front o back.

Es solo seguir los pasos de:
* [Envio de cambios a la rama Test](#Envio-de-cambios-a-la-rama-Test)

Que son los mismo solo cambian es la base y la rama a comparar, lo cual pondran de la siguiente manera:

| Base | Compare |
| -------- | -------- |
| Backend | Rama Personal |
| Frontend | Rama Personal |

Eso esta dependiendo que rol estan trabjando.

Y listo, a codear 🤙
## Nota: Es importante que se cumpa este orden de asignación sobre quien aprueba y quien puede hacer los pull request, de lo contrario surgirian conflictos.
