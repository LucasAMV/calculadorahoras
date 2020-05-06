package br.gov.mt.seguranca.modelo.negocio;

import org.jboss.seam.Component;
import org.junit.Assert;
import org.junit.Test;

import br.gov.mt.seguranca.modelo.HomeBO;
import br.gov.mt.seguranca.modelo.util.JUnitSeamTest;

public class ExemploTest extends JUnitSeamTest {

	@Test
	public void test() throws Exception {

		try {

			new ComponentTest() {

				protected void testComponents() throws Exception {

					HomeBO homeBO = (HomeBO) Component.getInstance("homeBO");
					Assert.assertEquals("ola", homeBO.ola());

				}

			}.run();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
