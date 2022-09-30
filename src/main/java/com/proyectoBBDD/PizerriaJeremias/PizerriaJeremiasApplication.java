package com.proyectoBBDD.PizerriaJeremias;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class PizerriaJeremiasApplication {

	public static void main(String[] args) {
		SpringApplication.run(PizerriaJeremiasApplication.class, args);
	}

	@Bean
	public Docket api(){
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.proyectoBBDD.PizerriaJeremias"))
				.build()
				.apiInfo(apiDetails());
	}

	private ApiInfo apiDetails() {
		// TODO Auto-generated method stub
		return new ApiInfoBuilder()
				.title("API De Pizzerria Jeremias")
				.description("la api controla los siguientes servicios\n" +
						"-Informacion acerca de sus restaurante\n" +
						"-Informacion acerca de sus empleados\n" +
						"-Informacion acerca de sus clientes\n" +
						"-Informacion acerca de sus Menus\n" +
						"-Informacion acerca de sus reservaciones\n" +
						"-Informacion acerca de sus pedidos\n" +
						"-Informaicon acerca de sus facturas\n" +
						"-Entre otros")
				.build() ;
	}
}
