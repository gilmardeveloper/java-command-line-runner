package com.gilmardeveloper.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gilmardeveloper.demo.model.Expressoes;
import com.gilmardeveloper.demo.repository.ExpressoesRepository;

@Component
public class ExpressoesService {

	@Autowired
	private ExpressoesRepository repository;

	public void salvar(Expressoes expressoes) {
		repository.save(expressoes);
		System.out.println(expressoes + " foi salvo com sucesso!");
	}

	public void listarTodos() {
		repository.listAll().forEach(p -> System.out.println(p.getNome()));
	}

	public void delete(String nome) {
		repository.deleteByNome(nome);
		System.out.println("Todas as expressões com " + nome + " foram removidas!");
	}

	public void buscar(String value) {
		repository.listAll().stream().filter(p -> validate(p.getNome(), value)).forEach(p -> System.out.println(p));
	}

	public boolean validate(String nome, String value) {
		
		value = value.replace("[", "").replace("]", "");
		String[] values = value.split(" ");
		for (int i = 0; i < values.length; i++) {
			if (!nome.toLowerCase().contains(values[i].toLowerCase())) {
				return false;
			}
		}

		return true;
	}

}
