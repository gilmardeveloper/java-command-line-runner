package com.gilmardeveloper.demo.utils;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import org.fusesource.jansi.AnsiConsole;

public class Mensagem{

	private static PrintStream out;
	
	public static void println(String color, String mensagem) {
		try {
			out = new PrintStream(System.out, true, "Cp1252");
			AnsiConsole.systemInstall();
			AnsiConsole.out.print(color);
			out.print(mensagem);
			AnsiConsole.out.print(Color.NORMAL_RESET);
			out.println();
		} catch (UnsupportedEncodingException e) {
			System.out.println("um erro ocorreu");
		}
	}
	
	public static PrintStream out() {
		try {
			out = new PrintStream(System.out, true, "Cp1252");
			return out;
		} catch (UnsupportedEncodingException e) {
			System.out.println("um erro ocorreu");
			return null;
		}
	}
	
	public static void close() {
		if(out != null) out.close();
	}
}
