package br.com.gridsoftware.testes.core;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.seam.Component;
import org.jboss.seam.contexts.Contexts;
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
				.addAsWebInfResource("web.xml")
				
				//Tem como usar uma API chamada MavenResolver
				.addAsLibraries(JarsUtil.carregaArquivosLib())
				;
		
		return war;
	}
	
	@Test
	public void should_create_greeting() throws Exception {
		System.out.println(servletContext);
		System.out.println(servletContext.getContextPath());
		System.out.println("Contexto setado?????????????????????????????");
		
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
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>> super.servletContext="+super.servletContext);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>> Contexts.isApplicationContextActive()="+Contexts.isApplicationContextActive());
		super.begin();
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