package desafiomv.model;

import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

public class ImprimeRelatorio {
	public static void imprimirRelatorio(String texto) {
		FileWriter arquivo;
		
		System.out.println("Arquivo .txt foi gerado!");
		try {
			arquivo = new FileWriter(new File("Arquivo.txt"));
			arquivo.write(texto);
			arquivo.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}