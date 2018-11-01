package com.gilmardeveloper.demo.command;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gilmardeveloper.demo.inteface.Commands;
import com.gilmardeveloper.demo.inteface.Executable;
import com.gilmardeveloper.demo.service.ExpressoesService;
import com.gilmardeveloper.demo.service.HelpService;
import com.gilmardeveloper.demo.service.PalavrasEnService;
import com.gilmardeveloper.demo.service.PalavrasService;

@Service
public class CommandExecute {

	@Autowired
	private PalavrasService palavras;

	@Autowired
	private PalavrasEnService palavrasEn;

	@Autowired
	private ExpressoesService expressoes;

	@Autowired
	private HelpService help;

	private String cmd;

	private Scanner input;

	public CommandExecute exec(String cmd, Scanner input) {
		this.cmd = cmd;
		this.input = input;
		return this;
	}
	
	public void run() {
		run(palavras, palavrasEn, expressoes, help);
	}
	
	private void run(Executable... executable) {

		for (Executable e : executable) {
			e.exec(new Commands() {

				@Override
				public Scanner getInput() {
					return input;
				}

				@Override
				public String getCommand() {
					return cmd;
				}
			});
		}

	}

}
