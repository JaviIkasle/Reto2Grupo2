#Wild Project

### Descripcion del proyecto
Este proyecto se basa en una aplicacion movil que se conectara a un servidor conectado a una base de datos con la cual los usuarios podran interactuar.
El cometido de este proyecto es una aplicacion en la que se muestren distintos zoologicos repartidos por el mundo. Los usuarios clientes podran ver los zoos que hay en la aplicacion y los animales que cada uno tiene con su informacion.

Habra otro tipo de usuarios que seran los empleados, estos solo podran ver la informacion y animales de los zoos en los que trabajan, ademas de ver los eventos que tiene su zoo. Por otra parte la edicion del usuario empleado esta destinada al administrador. Un usuario que utilizara una aplicacion web para gestionar a los empleados de la aplicacion.

---

### Diagrama E-R de la base de datos:

![Diagrama E-R de la base de datos.](src\\main\\resources\\E-R_BBDD.png)

Para especificar la usabilidad del proyecto, listaremos que puede hacer cada usuario que interactue con la aplicación.

El CLIENTE podra:
 * Registrarse en la aplicacion.
 * Loguearse con en la aplicacion.
 * Modificar su contraseña.
 * Eliminar su usuario.
 * Listar todos los  __animales__ .
 * Ver un  __animal__  por su id.
 * Crear un  __billete__ .
 * Listar todas las  __especies__ .
 * Ver una  __especie__  por su id.
 * Listar todos los  __eventos__ .
 * Ver un  __evento__  por su id.
 * Listar todos los  __zoos__ .
 * Ver un  __zoo__  por su id.
 
El ADMINISTRADOR podra:
 * Loguearse en la aplicacion web.
 * Listar todos los  __usuarios__ .
 * Ver  __usuario__  por su id.
 * Modificar empleado.
 * Modificar cliente.
 * Borrar un  __usuario__  por su id.
 * Crear cualquier tipo de  __usuario__ .
 * Realizar todas las operaciones CRUD en la tabla  __zoos__ .
 * Realizar todas las operaciones CRUD en la tabla  __roles__ .

El EMPLEADO podra:
 * Loguearse con en la aplicacion.
 * Realizar todas las operaciones CRUD en la tabla  __eventos__  de su zoo.
 * Ver el  __zoo__  al que pertenece.
 * Realizar todas las operaciones CRUD en la tabla  __animales__ de su zoo.
 * Listar  __especies__  de su zoo.
  
 
Es importante saber que los usuarios empleados solo los puede crear y modificar el administrador, lo mismo pasa si se quieren crear mas administradores.

---

### Tecnologias que se han usado

 1. Spring Boot (3.0.2)
 2. Hibernate
 3. JAVA
 4. MySQL
 5. Blazor Web Client
 6. Android Studio
 7. C#
 8. Postman (para pruebas)
 
---
 
### Getting started
####Prerequisitos:
Antes de ejecutar el proyecto debe de tener cierto software instalado de antemano.

 * Debe tener instalado MySQL Workbench
 * Eclipse para importar el proyecto
 * Visual Studio para improtar la aplicacion web.
 *Android Studio para importar la aplicacion.

Una vez haya cumplido estor requisitos debera importar el proyecto en eclipse como Maven Project. Buscar en la carpeta 'src/main/resources' el 
script de creacion de la base de datos, copiarlo y ejecutarlo en MySQL workbench. Despues importar el proyecto web e importarlo en blazor.


#### Instalacion:
 1. Clonar el repositorio de github: <https://github.com/JaviIkasle/Reto2Grupo2.git>
 2. Importar nuevo proyecto Maven del repositorio local creado.

Una vez tengamos el proyecto importado en el eclipse, antes de ejecutarlo hay que completar unos pasos.

En la carpeta src/main/resources hay que modificar el fichero  _aplication.properties_  en las primeras tres lineas:

```
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/reto2db
spring.datasource.username=root
spring.datasource.password=elorrieta
```
 1. Cambiar el localhost y el puerto (3306) segun sus especificaciones de MySQL.
 2. Cambiar el username segun sus especificaciones de MySQL.
 3. Cambiar el password segun sus especificaciones de MySQL.
 
 
 Cojer el archivo BBDD_script.sql que se encuentra enn la carpeta src/main/resources, copiar el texto y pegarlo en MySQL
 para precisar de la base de datos con datos iniciales para poder ejecutarlo correctamente.
 
---

### Usage (uso)
Una vez completados el punto anterior, para iniciar el servidor debe seguir estos pasos:

 * En el paquete  __cifrados.RSA__ debe ejecutar la clase  __RunGeneratorKeys__ .
 * Ejecutar la clase  __Reto2Grupo2Application__ -
 
_Recordar que las contraseñas están cifradas en la base de datos._

---

### API Documentation
Para acceder a la documentacion API que genera el Swagger, debe acceder a este link:
<http://localhost:3100/swagger-ui/index.html>

_recordar el puerto del servidor, en este caso 3100. Ademas para su uso el servidor debe estar arrancado_

En la documentacion de la API se puede ver todos los servicios pero no se podran acceder a la mayoria si no estas autorizado, es por eso que se recomienda el uso de Postman para realizar las pruebas

---

### Contact

Iñaki (inaki.riojane@elorreita-errekamari.com)
Adrian (adrian.g.riobello@elorrieta-errekamari.com)
Javier (javier.bazdepa@elorrieta-errekamari.com)

---

### Licencia
Distributed under the MIT License.
 
 
