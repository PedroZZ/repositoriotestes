package br.sceweb.teste;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.sceweb.modelo.Convenio;
import br.sceweb.modelo.ConvenioDAO;

public class UC05CadastrarConvenio {
	
	static Convenio convenio;
	static String dataInicio;
	static ConvenioDAO convenioDAO; 

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		convenio = new Convenio();
		convenioDAO = new ConvenioDAO();
	}
	
	@Test
	public void CT01UC05CadastrarConvenio_com_sucesso_data_valida() {
		// assertTrue(convenio.validaData(dataInicio));
	} 
	
	@Test
	public void CT02UC05CadastrarConvenio_com_data_invalida() {
		dataInicio = "42/04/2015";
		assertFalse(convenio.validaData(dataInicio));
	}
	
	@Test
	public void CT03UC05CadastrarConvenio_com_sucesso_data_valida() {
		convenioDAO.adiciona(convenio);
		assertEquals(1,convenioDAO.adiciona(convenio));
		convenioDAO.exclui(convenio.getCnpj());
		dataInicio = "29/10/2015";
		convenio.setDataInicio(dataInicio);
		//	assertTrue(dataInicio.equals(convenio.getDataInicio()));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void CT04UC05CadastrarConvenio_com_data_invalida() {
		convenio.setDataInicio("242/10/2015");

	} 

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}


}
