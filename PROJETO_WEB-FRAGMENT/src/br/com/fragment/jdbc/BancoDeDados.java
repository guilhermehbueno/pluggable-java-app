package br.com.fragment.jdbc;

import javax.activation.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class BancoDeDados {
	
	private JdbcTemplate jdbcTemplate = new JdbcTemplate();
	
	public void recuperaInformacoes(){
		/*http://www.vogella.de/articles/SpringJDBC/article.html#jdbc_usage_dao*/
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.apache.derby.jdbc.EmbeddedDriver");
		dataSource.setUrl("jdbc:derby:c:\\temp\\database\\test01;create=true");
		dataSource.setUsername("root");
		dataSource.setPassword("");
		
		jdbcTemplate.setDataSource(dataSource);
	}

}
