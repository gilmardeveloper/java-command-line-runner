package com.gilmardeveloper.demo.service;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gilmardeveloper.demo.inteface.Commands;
import com.gilmardeveloper.demo.inteface.Executable;
import com.gilmardeveloper.demo.model.Expressoes;
import com.gilmardeveloper.demo.repository.ExpressoesRepository;
import com.gilmardeveloper.demo.utils.Color;
import com.gilmardeveloper.demo.utils.Mensagem;

@Component
public class ExpressoesService implements Executable{

	@Autowired
	private ExpressoesRepository repository;
	private String cmd;
	private Scanner input;

	public void salvar(Expressoes expressoes) {
		repository.save(expressoes);
		Mensagem.println(Color.NORMAL_RESET, "\t\t" + expressoes + " foi salvo com sucesso!");
	}

	public void listarTodos() {
		repository.listAll().forEach(p -> Mensagem.println(Color.BRIGHT_YELLOW, "\t" + p.getNome()));
	}

	public void delete(String nome) {
		repository.deleteByNome(nome);
		Mensagem.println(Color.NORMAL_RESET, "\t\t" + "Todas as expressões com " + nome + " foram removidas!");
	}

	public void buscar(String value) {
		repository.listAll().stream().filter(p -> validate(p.getNome(), value)).forEach(p -> Mensagem.println(Color.BRIGHT_YELLOW, "\t" + p.toString()));
	}

	public boolean validate(String nome, String value) {

		value = value.replace("[", "").replace("]", "");
		String[] values = value.split(" ");
		String[] nomes = nome.substring(0, nome.indexOf("=")).replaceAll("[+-/*]+", " ").split(" ");

		for (int i = 0; i < values.length; i++) {
			try {
				if (!nomes[i].toLowerCase().equals(values[i].toLowerCase())) {
					return false;
				}
			} catch (ArrayIndexOutOfBoundsException e) {
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
	
		case "le":
			listarTodos();
			break;
		case "se":
			System.out.print("$ informe a nova expressão:");
			value = input.nextLine();
			salvar(new Expressoes(value));
			break;
		case "de":
			System.out.print("$ informe a expressão:");
			value = input.nextLine();
			delete(value);
			break;
		default:
			if(cmd.contains("[")) {
				buscar(cmd);
			}
		}

		
	}

}
