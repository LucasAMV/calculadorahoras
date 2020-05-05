package br.com.emtest.test;

import java.io.File;

import javax.persistence.EntityManager;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.OverProtocol;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.seam.Component;
import org.jboss.seam.mock.JUnitSeamTest;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.emtest.persistence.dao.ActorDAO;
import br.com.emtest.persistence.entities.Actor;

@RunWith(Arquillian.class)
public class GreeterTest extends JUnitSeamTest {

	@Deployment
	@OverProtocol("Servlet 3.0")
	public static Archive<?> createDeployment() {
		WebArchive war = ShrinkWrap.create(WebArchive.class)
				.addClasses(GreeterTest.class)
				.addPackages(true, "br.com.emtest.persistence")
				.addAsLibraries(Maven.resolver().loadPomFromFile("pom.xml").importRuntimeDependencies().resolve().withTransitivity().asFile())
				.addAsWebInfResource(new File("C:/Nz/Dev/Eclipse201906/eclipse-workspace/SakilaSeamBasicArquillian/src/main/webapp/WEB-INF/web.xml"))
				.addAsWebInfResource(new File("C:/Nz/Dev/Eclipse201906/eclipse-workspace/SakilaSeamBasicArquillian/src/main/webapp/WEB-INF/components.xml"))
				.addAsWebInfResource(new File("C:/Nz/Dev/Eclipse201906/eclipse-workspace/SakilaSeamBasicArquillian/src/main/webapp/WEB-INF/jboss-deployment-structure.xml"))
				.addAsWebInfResource(new File("C:/Nz/Dev/Eclipse201906/eclipse-workspace/SakilaSeamBasicArquillian/src/main/resources/log4j.xml"))
				.addAsResource(new File("C:/Nz/Dev/Eclipse201906/eclipse-workspace/SakilaSeamBasicArquillian/src/main/resources/META-INF/persistence.xml"), "META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "classes/seam.properties")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "classes/beans.xml")
				;
		//	war.delete("/WEB-INF/web.xml");
		//	war.addAsWebInfResource("webapp/WEB-INF/mock-web.xml", "web.xml");
		//	WebArchive war = ShrinkWrap.createFromZipFile(WebArchive.class, new File("target/SakilaSeamBasicArquillian.war"));
		System.out.println(war.toString(true));
		return war;
	}

	@Test
	public void should_create_greeting() throws Exception {
		new ComponentTest() {

			@Override
			protected void testComponents() throws Exception {
				EntityManager em = (EntityManager) Component.getInstance("sakilaDatabase");
				System.out.println("EntityManager isOpen? " + em.isOpen());

				ActorDAO actorDAO = (ActorDAO) Component.getInstance("actorDAO");
				if (actorDAO != null) {
					Actor a = actorDAO.findById((short) 1);
					System.out.println(a.getFirstName());
				} else {
					throw new RuntimeException("PQP actorDAO TÁ NULLLLLLLLLLLLLLLLLLL >=[");
				}
			}
			
		}.run();
	}

}