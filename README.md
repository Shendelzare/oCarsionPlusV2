# Getting Started

Se pide:
	1. Construir una API Rest Spring Boot securizada con los endpoints básicos CRUD para los
		cuatro objetos anteriores.
		 - Hecho. Sin comentarios adicionales
		a. Se puede utilizar una base de datos en memoria
		b. No es necesario desarrollar tests para todas las funcionalidades, pero al menos
		debería haber 1-2 ejemplos de test. Valorar el tipo de test más relevante a
		implementar.
		- Se añaden 2 tests al controlador de coches
		c. Total libertad a la hora de utilizar librerías para ayudar al desarrollo de la aplicación:
		versionado de base de datos, mapeo, etc…
		- Se ha utilizado modelmapper
		d. Deberá quedar registro tanto de la fecha de creación como de la fecha de última
		modificación de cualquier entrada en base de datos.
		- Se utilizan anotaciones inherentes
		e. Las consultas a base de datos estarán optimizadas para realizar el mínimo número
		de consultas necesarias.
		- Se sirve de implementar Specification
		f. La aplicación deberá estar securizada mediante Keycloak. Se dispone de un servidor
		con el que hacer las pruebas:
		i. https://desarrollo.teralco.com/auth/admin/prueba-tecnica/console
		ii. pruebatecnica/pruebatecnica
		- Java doc inexistente, y la documentación que tienen no es muy completa, entorno de teralco indisponible desde el lunes.
		g. La aplicación debe estar preparada para ejecutarse con Docker
		- Ejecutar en la raiz
			mvnw package spring-boot:repackage && copy "target\ocarsionplus-0.0.1-SNAPSHOT.jar" "src\main\docker\app.jar"
		- Ejecutar en src/main/docker
			docker-compose up --build 
		para la conexión a keycloak tengo un servidor en local, pero para probar tendriamos que avisarnos para que levante la imagen.
		h. Junto con el código se deberá entregar una colección de postman con ejemplos de
		llamada a los distintos endpoints. 
		Se añade coleccion postman y de environment para el token
	2. Será necesario crear un endpoint para el registro de usuarios.
		- Hecho sin comentarios adicionles
	3. El endpoint que devuelve todos los coches debe aceptar un parámetro “filtro” con las
		condiciones con las que hacer la búsqueda. Puede haber de 0 a N condiciones del tipo (no
		es necesario implementar el uso de paréntesis):
		color eq ROJO AND potencia gt 100
		- Hecho sin comentarios adicionles
	4. La respuesta de los endpoints de consulta de coches debe mostrar los extras como texto
		separado por comas: A/C, ELEVALUNAS, CLIMATIZADOR
		- Hecho sin comentarios adicionles
	5. El endpoint de consulta de marcas debe mostrar todos los coches relacionados. Por el
		contrario, precios y extras no deben mostrar sus elementos relacionados.
		- Hecho sin comentarios adicionles
