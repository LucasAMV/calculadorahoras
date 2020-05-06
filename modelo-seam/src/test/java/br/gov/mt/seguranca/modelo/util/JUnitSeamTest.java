package br.gov.mt.seguranca.modelo.util;

import org.jboss.seam.mock.AbstractSeamTest;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;

@Ignore
public class JUnitSeamTest extends AbstractSeamTest {

	private static JUnitSeamTest seamTest = new JUnitSeamTest();

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

}