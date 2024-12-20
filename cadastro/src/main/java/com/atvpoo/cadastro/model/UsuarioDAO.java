package com.atvpoo.cadastro.model;


import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;



@Repository
public class UsuarioDAO {
    @Autowired DataSource dataSource;
	
	JdbcTemplate jdbc;
	
	@PostConstruct
	private void Initialize() {
		jdbc = new JdbcTemplate(dataSource);
	}

    public String inserirUsuario(Usuario user){
        try{
            String sql = "INSERT INTO Usuario(nome,senha) VALUES (?,?)";
            Object[] obj = new Object[2];
            obj[0] = user.getNome();
            obj[1] = user.getSenha();
            jdbc.update(sql, obj);
            return "cadastroRealizado";  
        } catch (DataIntegrityViolationException e) {
            return "cadastroInvalido";
        }
    }

    public List<Map<String, Object>> listarUsuario() {
    	String sql = "SELECT * FROM Usuario";
    	return jdbc.queryForList(sql);
    }

    public List<Map<String,Object>> obterUsuario(int id){
		String sql = "SELECT * FROM Usuario WHERE id = ? ";
		Object[] obj = new Object[1];
		obj[0] = id;
		return jdbc.queryForList(sql, obj); 
	}

    public void atualizarUsuario(int id, Usuario user){
		String sql = "UPDATE Usuario SET nome = ?, senha = ? WHERE id = ?";
		Object[] obj = new Object[3];
		obj[0] = user.getNome();
		obj[1] = user.getSenha();
		obj[2] = id;
		jdbc.update(sql, obj);
	}

	public String autenticarUsuario(Usuario user){
		String sql = "SELECT * FROM Usuario WHERE nome = ? AND senha = ?";
		Object[] obj = new Object[2];
		obj[0] = user.getNome();
		obj[1] = user.getSenha();

		List<Map<String, Object>> resultado = jdbc.queryForList(sql, obj);

		if(resultado.size() == 1){
			return user.getNome();
		} else{
			return "";
		}
	}
}
