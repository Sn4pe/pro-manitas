# pro-manitas

## Configurar Spring Datasource, JPA, App properties
Abrir `src/main/resources/application.properties`
modificar usuario y contraseña si es necesario
```properties

spring.datasource.username= root
spring.datasource.password= PracticaRoot

```
## Creamos bbdd vacía con el nombre `promanitas`

Dentro de Backend hacemos maveninstall

Ejecutamos la aplicacion de springboot

## SQL Insertamos los siguientes roles 
```
INSERT INTO roles(name) VALUES('ROLE_PROVIDER');
INSERT INTO roles(name) VALUES('ROLE_CUSTOMER');
INSERT INTO roles(name) VALUES('ROLE_GUEST');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');
```

entramos en frontend y hacemos un `npm install`

y para ejecutarlo `npm run serve`
