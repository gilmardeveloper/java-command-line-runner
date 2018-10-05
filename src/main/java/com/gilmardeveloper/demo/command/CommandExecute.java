package com.gilmardeveloper.demo.command;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gilmardeveloper.demo.inteface.Commands;
import com.gilmardeveloper.demo.model.Expressoes;
import com.gilmardeveloper.demo.model.Palavras;
import com.gilmardeveloper.demo.service.ExpressoesService;
import com.gilmardeveloper.demo.service.PalavrasService;

@Service
public class CommandExecute implements Commands {

	@Autowired
	private PalavrasService palavras;
	
	@Autowired
	private ExpressoesService expressoes;

	@Override
	public void exec(String cmd, Scanner imput) {
		String value = "";
		switch (cmd) {

		case "lt":
			palavras.listarTodos();
			expressoes.listarTodos();
			break;
		case "lp":
			palavras.listarTodos();
			break;
		case "le":
			expressoes.listarTodos();
			break;
		case "sp":
			System.out.print("$ informe a nova palavra:");
			value = imput.nextLine();
			palavras.salvar(new Palavras(value));
			break;
		case "se":
			System.out.print("$ informe a nova expressão:");
			value = imput.nextLine();
			expressoes.salvar(new Expressoes(value));
			break;	
		case "dp":
			System.out.print("$ informe a palavra:");
			value = imput.nextLine();
			palavras.delete(value);
			break;
		case "de":
			System.out.print("$ informe a expressão:");
			value = imput.nextLine();
			expressoes.delete(value);
			break;	
		case "help":
			comandos();
			break;
		default:
			if(cmd.contains("[")) {
				expressoes.buscar(cmd);
			}else {
				palavras.buscar(cmd);
			}
		}

	}
	
	
	private void comandos() {

		System.out.println("help - listar todos os comandos");
		System.out.println("lt - listar tudo");
		System.out.println("lp - listar todas as palavras");
		System.out.println("le - listar todas as expressões");
		System.out.println("sp - adiciona uma nova palavra");
		System.out.println("se - adiciona uma nova expressão");
		System.out.println("dp - deleta palavras");
		System.out.println("dp - deleta expressões");
		System.out.println("exit - sair");
	}

}
