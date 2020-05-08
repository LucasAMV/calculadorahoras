
import org.jboss.seam.Component;
import org.junit.Assert;
import org.junit.Test;

public class ContratoAverbacaoBOTest extends MyJUnitSeamTest {
	
	@Test
	public void test() throws Exception {

		try {
			new ComponentTest() {
				protected void testComponents() throws Exception {
					System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
					injetarUsuarioLogadoAdmin();
					
					Assert.assertNotNull(new Object());
				}
			}.run();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
