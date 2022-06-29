package com.securityJasonWebToken.JWT;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JwtApplication {
	
/*Objetivos:
 * 
 * -Crear un endpoint de autentificación
 * -Examinar todas las request para validar el jwt y autorizarlo
 * 
 * 
 * 
 * Primer paso:
 *  Un API endpoint /authenticate
 * 
 * -Aceptar userID y password
 * -Devolver un jwt como respuesta
 * 
 * Lo hacemos con el método createAuthenticationToken
 * con el endpoint /authenticate.
 * 
 * Segundo paso:
 * 
 * Interceptar las peticiones de vuelta
 * 
 * -Extraer el jwt del header
 * -Validarlo y colocarlo en el contexto de ejecución
 * 
 * Para ello debemos crear un filtro
 * */
	
	
	public static void main(String[] args) {
		SpringApplication.run(JwtApplication.class, args);
	}

}
