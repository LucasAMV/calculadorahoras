package br.com.emtest.test;

import java.io.File;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.OverProtocol;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.seam.Component;
import org.jboss.seam.contexts.Lifecycle;
import org.jboss.seam.mock.JUnitSeamTest;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.emtest.persistence.dao.ActorDAO;
import br.com.emtest.persistence.entities.Actor;

@RunWith(Arquillian.class)
public class GreeterTest extends JUnitSeamTest {

	@Deployment
	@OverProtocol("Servlet 3.0")
	public static WebArchive createDeployment() {
		WebArchive war = ShrinkWrap.createFromZipFile(WebArchive.class, new File("target/SakilaSeamBasicArquillian.war"));
		System.out.println(war.toString(true));
		return war;
	}

	@Before
	public void before() {
		Lifecycle.beginCall();
	}

	@After
	public void after() {
		Lifecycle.endCall();
	}

	@Test
	public void should_create_greeting() {
		System.out.println("Annie are you ok?");
		ActorDAO actorDAO = (ActorDAO) Component.getInstance(ActorDAO.class);
		Actor a = actorDAO.findById((short) 1);
		System.out.println(a.getFirstName());
	}

}