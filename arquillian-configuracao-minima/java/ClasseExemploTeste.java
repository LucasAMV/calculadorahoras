package br.com.gridsoftware.testes.core;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.seam.Component;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.gridsoftware.persistencia.dao.ContratoAverbacaoDAO;
import br.com.gridsoftware.persistencia.entidades.ContratoAverbacao;

/**
 * 0. Nestas configurações o servidor já deve estar em execução!
 * 1. Executar pelo CMD ou "Run as -> JUnit Test".
 * 2. O deploy será gerado pelo método createDeployment()
 * 3. O Arquillian injetará suas libs no deploy e subirá junto com a aplicação no servidor.
 * 4. O método com @Before begin() é invocado aqui e simula um contexto para o Seam (Copiado de JUnitSeamTest e modificado com a orientação do Ivan).
 * 5. O método com @Test será invocado. new ComponentTest(){}.run() é invocado e o teste é executado, sem o .run não funciona.
 * 6. O teste finaliza e o deploy é automaticamente removido do servidor.
 */
@RunWith(Arquillian.class)
public class ClasseExemploTeste extends JUnitSeamArquillianTest {
	
	@Test
	public void TesteSimplesParaVerificarFuncionamento() throws Exception {
		new ComponentTest() {
			@Override
			protected void testComponents() throws Exception {
				ContratoAverbacaoDAO contratoAverbacaoDAO = (ContratoAverbacaoDAO) Component.getInstance("contratoAverbacaoDAO");
				
				ContratoAverbacao ca = contratoAverbacaoDAO.loadById(1L);
				System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
				System.out.println(ca.getDataAverbacao());
				System.out.println(ca.getNumeroContrato());
				System.out.println(ca.getColaborador().getPessoa().getNome());
				Assert.assertNotNull(ca.getDataAverbacao());
			}
		}.run();
	}

}