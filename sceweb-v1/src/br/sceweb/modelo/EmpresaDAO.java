package br.sceweb.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.sceweb.servico.FabricaDeConexoes;

public class EmpresaDAO {

	public int adiciona(Empresa empresa) {

		PreparedStatement ps;
		int codigoRetorno = 0;

		try (Connection conn = new FabricaDeConexoes().getConnection()) {
			ps = conn
					.prepareStatement("INSERT INTO Empresa(cnpj,"
							+ " nomeDaEmpresa, nomeFantasia, endereco, telefone) VALUES(?,?,?,?,?)");
			ps.setString(1, empresa.getCnpj());
			ps.setString(2, empresa.getNomeDaEmpresa());
			ps.setString(3, empresa.getNomeFantasia());
			ps.setString(4, empresa.getEndereco());
			ps.setString(5, empresa.getTelefone());
			codigoRetorno = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return codigoRetorno;
	}
	
	public List<Empresa> consultaEmpresas() {
		List<Empresa> empresas = new ArrayList<Empresa>();
		PreparedStatement ps;
		try (Connection conn = new FabricaDeConexoes().getConnection()) {
			ps = conn.prepareStatement("select * from empresa");
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				Empresa empresa = new Empresa();
				empresa.setNomeDaEmpresa(resultSet.getString("nomeDaEmpresa"));
				//os outros atributos nao foram implementados
				empresas.add(empresa);
			}
			resultSet.close();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return empresas;
	}
	
	public int exclui(String cnpj){
		Connection conn = new FabricaDeConexoes().getConnection();
		int codigoRetorno = 0;
		try {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM"
					+ " Empresa WHERE cnpj = ?");
			ps.setString(1, cnpj);
			codigoRetorno = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return codigoRetorno;
		
	}

	public Empresa consultaEmpresas(String cnpj) {
		Empresa empresa = new Empresa();
		
		PreparedStatement ps;
		try (Connection conn = new FabricaDeConexoes().getConnection()) {
			ps = conn.prepareStatement("select * from empresa where cnpj = ?");
			ps.setString(1, cnpj);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				empresa.setCnpj(resultSet.getString("cnpj"));
				empresa.setNomeDaEmpresa(resultSet.getString("nomeDaEmpresa"));
				empresa.setNomeFantasia(resultSet.getString("nomeFantasia"));
				empresa.setEndereco(resultSet.getString("endereco"));
				empresa.setTelefone(resultSet.getString("telefone"));
				
			}
			resultSet.close();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return empresa;
	}

}
