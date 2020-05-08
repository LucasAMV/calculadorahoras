
import org.jboss.seam.Component;
import org.jboss.seam.contexts.Contexts;
import org.jboss.seam.mock.AbstractSeamTest;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public class MyJUnitSeamTest extends AbstractSeamTest {

	private static MyJUnitSeamTest seamTest = new MyJUnitSeamTest();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		seamTest.startContainer();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		seamTest.stopContainer();
	}

	@Before
	public void setUp() throws Exception {
		setupClass();
		begin();
	}

	@After
	public void tearDown() throws Exception {
		end();
		cleanupClass();
	}

	public void startContainer() throws Exception {
		super.startSeam();
	}

	public void stopContainer() throws Exception {
		super.stopSeam();
	}
	
	protected static Usuario injetarUsuarioLogadoAdmin() throws Exception {
		AlgumDAO algumDAO = (AlgumDAO) Component.getInstance(AlgumDAO.class);
		Usuario usuario = algumDAO.loadById(1L);
		Contexts.getSessionContext().set("usuarioLogado", usuario);
		return usuario;
	}

}