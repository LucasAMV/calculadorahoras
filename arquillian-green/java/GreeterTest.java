package br.com.gridsoftware.testes.core;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.seam.Component;
import org.jboss.seam.mock.AbstractSeamTest;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.gridsoftware.persistencia.dao.ContratoAverbacaoDAO;
import br.com.gridsoftware.persistencia.entidades.ContratoAverbacao;
import br.com.gridsoftware.testes.core.util.JarsUtil;


/**
 * 1. Gerar o deploy pelo método createDeployment()
 * 2. O Arquillian injetará suas libs no deploy e subirá junto com a aplicação
 * 3. O método com @Before begin() é invocado aqui e simula um contexto para Seam.
 * 4. O método com @Test será invocado, new ComponentTest() {}.run é invocado e o teste é executado, sem o .run não funciona.
 */
@RunWith(Arquillian.class)
public class GreeterTest extends AbstractSeamTest {
	
	@Deployment
	public static WebArchive createDeployment() throws Exception {
		
		WebArchive war = ShrinkWrap.create(WebArchive.class, "greenTest.war")
				.addClass(GreeterTest.class)
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "seam.properties")
				.addAsWebInfResource("components.xml")
				.addAsWebInfResource("AwsCredentials.properties")
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
	
	private boolean seamStarted = false;

	@Before
	@Override
	public void begin() {
		try {
			if (!seamStarted) {
				//startSeam();
				//setupClass();
				seamStarted = true;
				super.setupClass();
			}
		} catch (Exception x) {
			throw new RuntimeException(x);
		}
		super.begin();
	}
	
	@Test
	public void should_create_greeting() throws Exception {
		new ComponentTest() {
			@Override
			protected void testComponents() throws Exception {
				ContratoAverbacaoDAO contratoAverbacaoDAO = (ContratoAverbacaoDAO) Component.getInstance("contratoAverbacaoDAO");
				
				ContratoAverbacao ca = contratoAverbacaoDAO.loadById(1L);
				System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
				System.out.println(ca.getDataAverbacao());
				System.out.println(ca.getNumeroContrato());
				System.out.println(ca.getColaborador().getPessoa().getNome());
				Assert.assertNotNull(ca.getDataAverbacao());
			}
		}.run();
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