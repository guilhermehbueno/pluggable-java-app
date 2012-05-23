package br.com.fragment.listener;

import java.util.EnumSet;
import java.util.HashMap;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebListener;

import br.com.fragment.filter.MainFilter;

@WebListener
public class MainListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("Executando MainListener contextDestroyed!");
		}
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("Executando MainListener contextInitialized");
		ServletContext ctx = sce.getServletContext();
		
		System.out.println("Registrando o filtro MainFilter...");
		FilterRegistration fr = ctx.addFilter("MainFilter", MainFilter.class);
		fr.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST),	true, "/admin");
		
		System.out.println("Registrando o servlet ServletDinamico...");
		ServletRegistration.Dynamic dynamic = ctx.addServlet("ServletDinamico", "br.com.fragment.servlet.ServletDinamico");
		dynamic.addMapping("/dinamico");
		dynamic.setLoadOnStartup(1);
		
		System.out.println("Registrando o servlet ServletDinamico...");
		ServletRegistration.Dynamic login = ctx.addServlet("login", "br.com.fragment.servlet.Login");
		login.addMapping("/login");
		login.setLoadOnStartup(1);	
	}

}
