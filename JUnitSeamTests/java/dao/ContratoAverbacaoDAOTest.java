
import java.util.List;

import org.jboss.seam.Component;
import org.junit.Assert;
import org.junit.Test;

public class ContratoAverbacaoDAOTest extends MyJUnitSeamTest {
	
	@Test
	public void test() throws Exception {

		try {
			new ComponentTest() {
				protected void testComponents() throws Exception {
					System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
					
					injetarUsuarioLogadoAdmin();
					
					Assert.assertTrue(true);
				}
			}.run();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
