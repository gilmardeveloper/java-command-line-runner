package com.gilmardeveloper.demo.controller;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.gilmardeveloper.demo.command.CommandExecute;

@Component
public class ControllerRunning implements CommandLineRunner {

	@Autowired
	private CommandExecute command;

	@Override
	public void run(String... args) throws Exception {

		if (args.length > 0) {

			Scanner input = new Scanner(System.in);
			
			System.out.println();
			System.out.println();
			System.out.println("Seja bem vindo ...");
			System.out.println();
			System.out.println("\thelp - para listar todos os camandos");
			System.out.println("\texit - para sair");
			System.out.println();
			
			System.out.print("$ ");
			String comando = input.nextLine();
			
			
			while (!comando.equalsIgnoreCase("exit")) {

				command.exec(comando, input);
				System.out.print("$ ");
				comando = input.nextLine();

			}
		}else {
			System.err.println("digite java -jar app-1.jar init");
		}
		System.out.println("saindo do sistema");
	}
	
	

}
