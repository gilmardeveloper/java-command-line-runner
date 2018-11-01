package com.gilmardeveloper.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gilmardeveloper.demo.inteface.Commands;
import com.gilmardeveloper.demo.inteface.Executable;

@Component
public class HelpService implements Executable {

	@Autowired
	private PalavrasService palavras;

	@Autowired
	private PalavrasEnService palavrasEn;

	@Autowired
	private ExpressoesService expressoes;

	private String cmd;

	@Override
	public void exec(Commands command) {
		
		this.cmd = command.getCommand();
		if(cmd.equalsIgnoreCase("lt")) {

			palavras.listarTodos();
			System.out.println();
			palavrasEn.listarTodos();
			System.out.println();
			expressoes.listarTodos();
		
		}else if(cmd.equalsIgnoreCase("help")){
			comandos();
		}
		
	}

	private void comandos() {

		System.out.println("\thelp - listar todos os comandos");
		System.out.println("\tlt - listar tudo");
		System.out.println("\tlp - listar todas as palavras");
		System.out.println("\tle - listar todas as expressões");
		System.out.println("\tli - listar todas as palavras em inglês");
		System.out.println("\tsp - adiciona uma nova palavra");
		System.out.println("\tse - adiciona uma nova expressão");
		System.out.println("\tsi - adiciona uma nova palavra em inglês");
		System.out.println("\tdp - deleta palavras");
		System.out.println("\tde - deleta expressões");
		System.out.println("\tdi - deleta palavras em inglês");
		System.out.println("\texit - sair");
	}

}
