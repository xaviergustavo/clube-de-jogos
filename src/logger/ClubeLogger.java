package logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ClubeLogger {

	private static ClubeLogger instance;
	
	private final DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/uuuu - HH:mm");
	
	private final Path arquivo = Paths.get("log.txt");
	
	private ClubeLogger() {
		
	}
	
	public static ClubeLogger getInstance() {
		if (instance == null) {
			instance = new ClubeLogger();
		}
		return instance;
	}
	
	public void registrar(String texto) {
		try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(arquivo.toString(), true)))) {
			out.write(formatarTexto(texto));
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public void registrarComSaida(String texto) {
		registrar(texto);
		System.out.println(formatarTexto(texto));
	}
	
	public String conteudo() {
		String conteudoLog = "";
		try {
			conteudoLog = new String(Files.readAllBytes(arquivo));	
		} catch (IOException e) {
			e.printStackTrace();
		}
		return conteudoLog;
	}
	
	private String formatarTexto(String texto) {
		LocalDateTime agora = LocalDateTime.now();
		return String.format("%s: %s%n", agora.format(formatoData), texto);
	}
}
