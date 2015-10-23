package br.sceweb.teste;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.sceweb.modelo.Empresa;
import br.sceweb.modelo.EmpresaDAO;

public class UC02ConsultarEmpresa {
	
	static public EmpresaDAO empresaDao;
	static public Empresa empresa;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		empresaDao = new EmpresaDAO();
		empresa = new Empresa();
		
		empresa.setNomeDaEmpresa("Empresa 1");
		empresa.setCnpj("89424232000180");
		empresa.setNomeFantasia("Empr 1");
		empresa.setEndereco("Rua da Empresa 1, SP");
		empresa.setTelefone("2722-4595");
		
	}

	@Test
	public void test() {
		empresaDao.adiciona(empresa);
		assertTrue(empresa.equals(empresaDao.consultaEmpresas("89424232000180")));
		empresaDao.exclui("89424232000180");
	}
	
	@Test
	public void CT02UC02A1ConsultarEmpresas_com_sucesso(){
		empresaDao.adiciona(empresa);
		assertEquals(1, empresaDao.consultaEmpresas().size());
		empresaDao.exclui("89424232000180");
	}
	
	@Test
	public void CT02UC02A1ConsultarEmpresas_sem_sucesso(){
		empresaDao.adiciona(empresa);
		assertFalse(empresaDao.consultaEmpresas().size() == 2);
		empresaDao.exclui("89424232000180");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
//		empresaDao.exclui("89424232000180");
	}


}
