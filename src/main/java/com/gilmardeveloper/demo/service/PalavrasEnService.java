package com.gilmardeveloper.demo.service;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gilmardeveloper.demo.inteface.Commands;
import com.gilmardeveloper.demo.inteface.Executable;
import com.gilmardeveloper.demo.model.PalavrasEn;
import com.gilmardeveloper.demo.repository.PalavrasEnRepository;
import com.gilmardeveloper.demo.utils.Color;
import com.gilmardeveloper.demo.utils.Mensagem;

@Component
public class PalavrasEnService implements Executable {

	@Autowired
	private PalavrasEnRepository repository;
	private String cmd;
	private Scanner input;

	public void salvar(PalavrasEn palavras) {
		repository.save(palavras);
		Mensagem.println(Color.NORMAL_RESET, "\t\t" + palavras + " foi salvo com sucesso!");
	}

	public void listarTodos() {
		repository.listAll().forEach(p -> Mensagem.println(Color.BRIGHT_GREEN, "\t" + p.getNome()));
	}

	public void delete(String palavras) {
		repository.deleteByNome(palavras);
		Mensagem.println(Color.NORMAL_RESET, "\t\tTodas as palavras com " + palavras + " foram removidas!");
	}

	public void buscar(String value) {
		repository.listAll().stream().filter(p -> validate(p.getNome(), value))
				.forEach(p -> Mensagem.println(Color.BRIGHT_GREEN, "\t" + p.toString()));
	}

	public boolean validate(String nome, String value) {

		for (int i = 0; i < value.length(); i++) {
			if (!nome.toLowerCase().contains(String.valueOf(value.toLowerCase().charAt(i)))) {
				return false;
			}
		}

		return true;
	}

	@Override
	public void exec(Commands command) {

		this.cmd = command.getCommand();
		this.input = command.getInput();
		
		String value = "";
		switch (cmd) {

		case "li":
			listarTodos();
			break;
		case "si":
			System.out.print("$ informe a nova palavra em inglês:");
			value = input.nextLine();
			salvar(new PalavrasEn(value));
			break;
		case "di":
			System.out.print("$ informe a palavra em inglês:");
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
