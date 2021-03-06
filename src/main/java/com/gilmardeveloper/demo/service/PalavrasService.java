package com.gilmardeveloper.demo.service;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gilmardeveloper.demo.inteface.Commands;
import com.gilmardeveloper.demo.inteface.Executable;
import com.gilmardeveloper.demo.model.Palavras;
import com.gilmardeveloper.demo.repository.PalavrasRepository;
import com.gilmardeveloper.demo.utils.Color;
import com.gilmardeveloper.demo.utils.Mensagem;

@Component
public class PalavrasService implements Executable {

	private String cmd;
	private Scanner input;

	@Autowired
	private PalavrasRepository repository;

	public void salvar(Palavras palavras) {
		repository.save(palavras);
		Mensagem.println(Color.NORMAL_RESET, "\t\t" + palavras + " foi salvo com sucesso!");
	}

	public void listarTodos() {
		repository.listAll().forEach(p -> Mensagem.println(Color.BRIGHT_RED, "\t" + p.getNome()));
	}

	public void delete(String palavras) {
		repository.deleteByNome(palavras);
		Mensagem.println(Color.NORMAL_RESET, "\t\tTodas as palavras com " + palavras + " foram removidas!");
	}

	public void buscar(String value) {
		repository.listAll().stream().filter(p -> validate(p.getNome(), value))
				.forEach(p -> Mensagem.println(Color.BRIGHT_RED, "\t" + p.toString()));
	}

	public boolean validate(String nome, String value) {

		for (int i = 0; i < value.length(); i++) {
			if (!nome.toLowerCase().contains(String.valueOf(value.toLowerCase().charAt(i)))) {
				return false;
			}
		}

		return true;
	}

	public void exec(Commands command) {

		this.cmd = command.getCommand();
		this.input = command.getInput();
		String value = "";

		switch (cmd) {

		case "lp":
			listarTodos();
			break;
		
		case "sp":
			System.out.print("$ informe a nova palavra:");
			value = input.nextLine();
			salvar(new Palavras(value));
			break;
		
		case "dp":
			System.out.print("$ informe a palavra:");
			value = input.nextLine();
			delete(value);
			break;

		default:
			if (!cmd.contains("[") && cmd.length() > 2) {
				buscar(cmd);
			}
		}
	}

}
