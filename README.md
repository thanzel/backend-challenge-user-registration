![Banner para Linkedin Licenciada Marketing Minimalista Beige](https://github.com/user-attachments/assets/5aa064ef-bae6-43c5-b7ad-cf048a5221e1)

<div align="center">
  
  ![Static Badge](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
  ![Static Badge](https://img.shields.io/badge/SpringbOOT-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
  ![Static Badge](https://img.shields.io/badge/H2-00000F?style=for-the-badge&logo=H2Database&logoColor=white)

  ![Static Badge](https://img.shields.io/badge/VERSION-1.0-yellow?style=flat)
  ![Static Badge](https://img.shields.io/badge/STATUS-TERMINADO-blue?style=flat)

</div>


# APIRest para Registro de Usuarios (Desafío Técnico)

  Este proyecto consiste en una API RESTful para la creación, lectura, actualización y eliminación (CRUD) de usuarios utilizando JSON Web Tokens (JWT). La API está implementada en Java 17 utilizando Spring Boot 3.3.3 y emplea una base de datos en memoria H2 para el almacenamiento de los datos. 

  
  El proyecto se enfocó en una implementación directa y sencilla de JWT para persistir con el usuario, sin la complejidad adicional de configurar Spring Security, lo que hace que la solución sea más accesible para el desafío técnico solicitado. 

  
  La API permite registrar y gestionar usuarios en la base de datos, validando que el correo electrónico tenga un formato correcto según las especificaciones (aaaaaaa@dominio.cl) y que no esté previamente registrado, junto con validación de contraseña configurable según un patrón regex.

  
  Se creó un endpoint para generar directamente el token para cuando se vence el token actual recordando que los tokens generados se persisten junto con los datos del usuario en la base de datos H2.



## Backend 

- Endpoint para Listar Usuarios
- Endpoint para Crear Usuarios
- Endpoint para Actualizar Usuarios
- Endpoint para Eliminar Usuarios
- Endpoint para buscar un Usuario
- Test unitarios con JUnit y Mockito
- Scripts de Base de Datos (archivo /resources/scriptsBD/scriptsBDuserdb.sql)
  - Al levantar el Backend en desarrollo, el archivo /resources/import.sql genera data de prueba en la tabla correspondiente
- Collección para importar en Postman con la construcción de los endpoints (archivo /resources/postman/CollectionPostmanAPIUsers.json
- JavaDoc (archivo /target/site/apidocs/index.html)

---



# Diagrama de Solución

![image](https://github.com/user-attachments/assets/d91b402b-9375-426a-b36a-bad485e72b84)

---



# Para probar la API



## Usando Swagger

<div align="center"> 
  
![image](https://github.com/user-attachments/assets/6167b0cf-32e1-48b7-81ff-dee6ff18fbb6)

</div>

1. Iniciar la aplicación:

Asegúrate de que la aplicación esté corriendo en tu entorno local. Si estás usando Spring Boot, puedes iniciar la aplicación ejecutando el comando mvn spring-boot:run o ejecutando la clase principal desde tu IDE dándole click al botón Run de Intellij Idea.

2. Acceder a Swagger UI:
   
Abre tu navegador y ve a la siguiente URL: http://localhost:8080/swagger-ui.html.
Allí encontrarás una interfaz visual para interactuar con todos los endpoints de la API. Swagger UI te permite enviar solicitudes y ver las respuestas directamente desde el navegador.

3. Probar los Endpoints:
   
Por ejemplo para registrar un usuario, selecciona el endpoint POST /api/user/create y proporciona el cuerpo del JSON con los datos del usuario, ejemplo:


```
{
  "name": "Cristal Paz",
  "email": "cristral@dominio.cl",
  "password": "Aer1&aaa",
  "phones": [
    {
      "number": "123-4567",
      "citycode": "04",
      "countrycode": "58"
    }
  ]
}
```

Puedes probar otros endpoints de CRUD (GET, PUT, DELETE) utilizando la misma interfaz.

---



   ## Usando Postman

   <div align="center"> 

   ![image](https://github.com/user-attachments/assets/6c0341c2-d16c-425a-97aa-2e4a9f761262)

   </div>

   1. Importar la colección de Postman:
      
En la carpeta /resources/postman/, encontrarás una colección de Postman preconfigurada (CollectionPostmanAPIUsers.json). Importa esta colección en tu Postman:
Abre Postman y selecciona Import, navega hasta la carpeta mencionada y selecciona el archivo .json de la colección. La colección importada aparecerá en tu área de trabajo, lista para ser utilizada.

![image](https://github.com/user-attachments/assets/be667097-4ac3-41a6-9c09-2e17ccc2789e)

   2. Probar los endpoints:
    
Dentro de la colección, encontrarás preconfigurados todos los endpoints necesarios para interactuar con la API.
Por ejemplo, selecciona el endpoint POST Crear Usuario, y en la pestaña body, elige raw y selecciona JSON en el menú desplegable.
Introduce el JSON con los datos del usuario que deseas registrar, por ejemplo:


```
{
  "name": "Cristal Paz",
  "email": "cristral@dominio.cl",
  "password": "Aer1&aaa",
  "phones": [
    {
      "number": "123-4567",
      "citycode": "04",
      "countrycode": "58"
    }
  ]
}
```


Luego click en Send para enviar la solicitud y ver la respuesta.
   

>[!TIPS]
>
> El desarrollo incluye:
>   * Script para Base de datos
>   * File para cargar data inicial
>   * JavaDoc
>   * Collección de Postman
>   * [Swagger](http://localhost:8080/swagger-ui/index.html#/)
      

    
## Desarrollador

[Yenny Chipamo](https://www.linkedin.com/in/yenny-chipamo/)
* Software Developer - Backend Java Jr. - Spring Boot
* FullStack Junior Developer Jr.: Javascript - JQuery - Typescript - HTML - CSS - Bootstrap
