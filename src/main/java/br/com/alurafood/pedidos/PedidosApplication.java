package br.com.alurafood.pedidos;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class PedidosApplication {

	public static void main(String[] args) {
		SpringApplication.run(PedidosApplication.class, args);
	}

}
