package atividade1;

import java.io.*;
import java.net.*;

public class Cliente extends Thread {

	private static boolean done = false;
	private Socket conexao;

	public Cliente(Socket s) {
		conexao = s;
	}

	public static void main(String[] args) {
		try {
			Socket conexao = new Socket("localhost", 2000);
			PrintStream saida = new PrintStream(conexao.getOutputStream());
			BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Entre com seu nome: ");

			String meuNome = teclado.readLine();
			saida.println(meuNome);
			System.out.println();

			Thread t = new Cliente(conexao);
			t.start();

			String linha;

			while (true) {
				if (done) {
					break;
				}
				System.out.print("> ");
				linha = teclado.readLine();
				saida.println(linha);
			}

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void run() {
		try {
			BufferedReader entrada = new BufferedReader(new InputStreamReader(conexao.getInputStream()));

			String linha;

			while (true) {
				linha = entrada.readLine();

				if (linha.trim().equals("")) {
					System.out.println("Conexão encerrada!!!");
					break;
				}
				System.out.println();
				System.out.print("--> ");
				System.out.print(linha);
				System.out.println();


			}
			done = true;

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
