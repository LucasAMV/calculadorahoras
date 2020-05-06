package br.com.gridsoftware.testes.core.util;

import java.io.File;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.exporter.ZipExporter;
import org.jboss.shrinkwrap.api.importer.ArchiveImportException;
import org.jboss.shrinkwrap.api.importer.ZipImporter;
import org.jboss.shrinkwrap.api.spec.JavaArchive;

public class JarsUtil {
	
	/** Diretório onde se espera estar todas as libs (.jar) empacotadas pelo Maven **/
	public static String DIRETORIO_LIBS = "C:\\Dev\\temp\\greenLibs\\";
	public static String LOCAL_EXPORTACAO = "target/deployments/";
	public static String JAR_NAME = "jarZao.jar";
	
	public static void gerarJarZaoDasLibsParaArquivo(JavaArchive jarZaoEmMemoria) {
		jarZaoEmMemoria.as(ZipExporter.class).exportTo(new File(LOCAL_EXPORTACAO+JAR_NAME), true);
		System.out.println("jarZao criado em disco em "+LOCAL_EXPORTACAO);
	}
	
	public static JavaArchive gerarJarZaoDasLibsEmMemoria() {
		System.out.println("Buscando libs em "+DIRETORIO_LIBS);
		JavaArchive jarZao = ShrinkWrap.create(JavaArchive.class, JAR_NAME);
		for (File resource : carregaArquivosLib()) {
			jarZao.addAsResource(resource);
		}
		return jarZao;
	}
	
	public static JavaArchive carregarJarZaoPreCriado() {
		JavaArchive jarZao;
		try {
			jarZao = ShrinkWrap
					  .create(ZipImporter.class, JAR_NAME)
					  .importFrom(new File(LOCAL_EXPORTACAO+JAR_NAME))
					  .as(JavaArchive.class);
			System.out.println("jarZão pré criado encontrado.");
		} catch (ArchiveImportException e) {
			return naoEncontrouPreCriado();
		} catch (IllegalArgumentException e) {
			return naoEncontrouPreCriado();
		}
		return jarZao;
	}
	
	private static JavaArchive naoEncontrouPreCriado() {
		System.out.println("Não encontrou o jarZao pré criado, um novo será gerado.");
		JavaArchive jarZaoGerado = gerarJarZaoDasLibsEmMemoria();
		gerarJarZaoDasLibsParaArquivo(jarZaoGerado);
		return jarZaoGerado;
	}
	
	public static File[] carregaArquivosLib() {
		File[] arquivosEncontrados = new File(DIRETORIO_LIBS).listFiles();

		if (arquivosEncontrados == null || arquivosEncontrados.length == 0)
			throw new RuntimeException("Arquivos Lib não encontrados no diretório apontado: " + DIRETORIO_LIBS);
		
		return arquivosEncontrados;
	}

	public static void main(String[] args) {
		carregarJarZaoPreCriado();
	}
	
}
