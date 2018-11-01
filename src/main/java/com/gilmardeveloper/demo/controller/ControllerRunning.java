package com.gilmardeveloper.demo.controller;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.gilmardeveloper.demo.command.CommandExecute;
import com.gilmardeveloper.demo.utils.Color;
import com.gilmardeveloper.demo.utils.Mensagem;

@Component
public class ControllerRunning implements CommandLineRunner {

	@Autowired
	private CommandExecute command;
	

	@Override
	public void run(String... args) throws Exception {
		
		if (args.length > 0) {

			Scanner input = new Scanner(System.in, "Cp1252");
			
			Mensagem.out().println();
			Mensagem.out().println();
			Mensagem.println(Color.BRIGHT_YELLOW, "Seja bem vindo ...");
			Mensagem.out().println();
			Mensagem.println(Color.BRIGHT_YELLOW, "\thelp - para listar todos os camandos");
			Mensagem.println(Color.BRIGHT_YELLOW, "\texit - para sair");
			Mensagem.out().println();
			
			Mensagem.out().print("$ ");
			String comando = input.nextLine();
			
			
			while (!comando.equalsIgnoreCase("exit")) {

				command.exec(comando, input).run();
				Mensagem.out().print("$ ");
				comando = input.nextLine();

			}
			
			input.close();
			Mensagem.close();
		}else {
			System.err.println("digite java -jar app-1.jar init");
		}
		
		System.out.println("saindo do sistema");
		Mensagem.close();
	}

}
