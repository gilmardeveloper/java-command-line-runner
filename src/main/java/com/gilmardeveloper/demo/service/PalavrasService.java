package com.gilmardeveloper.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gilmardeveloper.demo.model.Palavras;
import com.gilmardeveloper.demo.repository.PalavrasRepository;

@Component
public class PalavrasService {

	@Autowired
	private PalavrasRepository repository;

	public void salvar(Palavras palavras) {
		try {
			repository.save(palavras);
			System.out.println(palavras + " foi salvo com sucesso!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void listarTodos() {
		repository.listAll().forEach(p -> System.out.println(p.getNome()));
	}

	public void delete(String palavras) {
		repository.deleteByNome(palavras);
		System.out.println("Todas as palavras com " + palavras + " foram removidas!");
	}

	public void buscar(String value) {
		repository.listAll().stream().filter(p -> validate(p.getNome(), value)).forEach(p -> System.out.println(p));
	}

	public boolean validate(String nome, String value) {

		for (int i = 0; i < value.length(); i++) {
			if (!nome.toLowerCase().contains(String.valueOf(value.toLowerCase().charAt(i)))) {
				return false;
			}
		}

		return true;
	}

}
