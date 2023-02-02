#Wild Project

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
 
 
---









### Licencia
Distributed under the MIT License.
 
 
 # __(controlar cuando el admin va a modificar un empleado y mete la id de un cliente y viceversa)__
## subtitulo

### subtitulo 3

#### subtitulo 4

##### subtitulo 5

###### subtitulo 6



*Esto es cursiva*

_Esto tambien es cursiva_

**Esto es negrita**

__Esto tambien es negrita__

*puedes **combinar** estilos* de esta manera.

Alguien dijo:
> Esto es una cita
> y la cita sigue aqui

Lista 1

* Elemento uno
* elemento dos
* elemento tres

Lista 2

1. elementos
2. elemento dos
3. elemento tres



[link]<https://es.wikipedia.org/wiki/Wikipedia:Portada>


Bloque de codigo

...

@GetMapping("/animales")
	public ResponseEntity<List<AnimalServiceModel>> getAnimales() {
		
		List<AnimalServiceModel> response = animalService.getAllAnimales();
		return new ResponseEntity<List<AnimalServiceModel>>(response, HttpStatus.OK);
	}
...

---
Linea que separa