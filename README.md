<h1 align="center"> APIRest para Registro de Usuarios </h1>

<div align="center">
  
  ![Static Badge](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
  ![Static Badge](https://img.shields.io/badge/SpringbOOT-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
  ![Static Badge](https://img.shields.io/badge/H2-00000F?style=for-the-badge&logo=H2Database&logoColor=white)

  ![Static Badge](https://img.shields.io/badge/VERSION-1.0-yellow?style=flat)
  ![Static Badge](https://img.shields.io/badge/STATUS-TERMINADO-blue?style=flat)

</div>

![Banner para Linkedin Licenciada Marketing Minimalista Beige](https://github.com/user-attachments/assets/5aa064ef-bae6-43c5-b7ad-cf048a5221e1)

# Desafío Técnico

  Este proyecto consiste en una API RESTful para la creación, lectura, actualización y eliminación (CRUD) de usuarios utilizando JSON Web Tokens (JWT). La API está implementada en Java 17 utilizando Spring Boot 3.3.3 y emplea una base de datos en memoria H2 para el almacenamiento de los datos. 
  El proyecto se enfocó en una implementación directa y sencilla de JWT para la autenticación, sin la complejidad adicional de configurar Spring Security, lo que hace que la solución sea más accesible para el desafío técnico solicitado. 
  La API permite registrar y gestionar usuarios en la base de datos, validando que el correo electrónico tenga un formato correcto según las especificaciones (aaaaaaa@dominio.cl) y que no esté previamente registrado, junto con validación de contraseña configurable según un patrón regex.
  Se creó un endpoint para generar directamente el token para cuando se vence el token actual recordando que los tokens generados se persisten junto con los datos del usuario en la base de datos H2.

## Backend 

- Endpoint para Listar Usuarios
- Endpoint para Crear Usuarios
- Endpoint para Actualizar Usuarios
- Endpoint para Eliminar Usuarios
- Endpoint para buscar un Usuario
- Test unitarios con JUnit y Mockito
- Scripts de Base de Datos (archivo /resources/)
  - Al levantar el Backend en desarrollo, el archivo /resources/import.sql genera data de prueba en la tabla correspondiente
- Collección para importar en Postman con la construcción de los endpoints (archivo /resources/collectionTestPostmancollectionTestPostman/postman
- JavaDoc (archivo /target/site/apidocs/index.html)

# Diagrama de Solución

![image](https://github.com/user-attachments/assets/d91b402b-9375-426a-b36a-bad485e72b84)



>[!TIPS]
>
> El desarrollo incluye:
>   * Script para Base de datos
>   * File para cargar data inicial
>   * JavaDoc
>   * Collección de Postman
>   * [Swagger](http://localhost:8080/swagger-ui/index.html#/)
      
