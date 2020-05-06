package br.com.gridsoftware.testes.core;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.seam.mock.AbstractSeamTest;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Before;

import br.com.gridsoftware.testes.core.util.JarsUtil;

/**
 * 0. Nestas configurações o servidor já deve estar em execução!
 * 1. Executar pelo CMD ou "Run as -> JUnit Test".
 * 2. O deploy será gerado pelo método createDeployment()
 * 3. O Arquillian injetará suas libs no deploy e subirá junto com a aplicação no servidor.
 * 4. O método com @Before begin() é invocado aqui e simula um contexto para o Seam (Copiado de JUnitSeamTest e modificado com a orientação do Ivan).
 * 5. O método com @Test será invocado. new ComponentTest(){}.run() é invocado e o teste é executado, sem o .run não funciona.
 * 6. O teste finaliza e o deploy é automaticamente removido do servidor.
 */
public class JUnitSeamArquillianTest extends AbstractSeamTest {
	
	/** Método padrão do Arquillian, criado a partir da documentação. **/
	@Deployment
	public static WebArchive createDeployment() throws Exception {
		
		WebArchive war = ShrinkWrap.create(WebArchive.class, "greenTest.war")
				.addClass(JUnitSeamArquillianTest.class)
				//Arquivos em src/test/resources
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "seam.properties")
				.addAsWebInfResource("components.xml")
				.addAsWebInfResource("test-persistence.xml", "classes/META-INF/persistence.xml")
				.addAsWebInfResource("ejb-jar.xml")
				.addAsWebInfResource("jboss-deployment-structure.xml")
				.addAsWebInfResource("log4j.xml", "classes/log4j.xml")
				.setWebXML("web.xml")
				
				//Tem como usar uma API chamada MavenResolver do Arquillian e ler do pom.xml para não ter que fazer na mão.
				.addAsLibraries(JarsUtil.carregaArquivosLib())
				;
		
		return war;
	}
	
	@Before
	@Override
	public void begin() {
		try {
			super.setupClass();
			super.begin();
		} catch (Exception x) {
			throw new RuntimeException(x);
		}
	}
	
	@After
	@Override
	public void end() {
		try {
			super.end();
			super.cleanupClass();
		} catch (Exception x) {
			throw new RuntimeException(x);
		}
	}
	
	/**
	 * Call this method within a test method to end the previous mock session and
	 * start another one.
	 */
	public void reset() {
		end();
		begin();
	}

}
