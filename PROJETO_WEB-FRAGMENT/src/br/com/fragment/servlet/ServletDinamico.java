package br.com.fragment.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.resource.loader.StringResourceLoader;
import org.apache.velocity.runtime.resource.util.StringResourceRepository;


public class ServletDinamico extends HttpServlet {
	
	 
	
	  /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDinamico() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processaRequisicao(request, response);
	}
	
	
	
	
	/**
	 * Get a template from the cache; if template has not been loaded, load it
	 * 
	 * @param templatePath
	 * @return
	 * @throws Exception 
	 * @throws ParseErrorException 
	 * @throws ResourceNotFoundException 
	 */
	private static Template getTemplate(final String templatePath, VelocityEngine ve) throws ResourceNotFoundException, ParseErrorException, Exception {
		if (!ve.resourceExists(templatePath)) {
			StringResourceRepository repo = StringResourceLoader.getRepository();
			repo.putStringResource(templatePath, getTemplateFromResource(templatePath));
		}
		System.out.println(ve.resourceExists(templatePath));
		return ve.getTemplate(templatePath,"UTF-8");
	}

	/**
	 * Read a template into memory
	 *
	 * @param templatePath
	 * @return
	 */
	private static String getTemplateFromResource(final String templatePath) {
		try {
			ClassLoader loader = ServletDinamico.class.getClassLoader();
			InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(templatePath);
			System.out.println(IOUtils.toString(stream, "UTF-8"));
			return IOUtils.toString(stream, "UTF-8");
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}
	
	
	public void processaRequisicao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Acessando doGet no ServletDinamico");
		VelocityEngine ve = new VelocityEngine();  
		try {
			Enumeration<String> enums =request.getParameterNames();
			
			
			while(enums.hasMoreElements()){
				System.out.println(enums.nextElement());
			}
			
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			
			System.out.println("Tentando logar com o login: "+login+" e senha: " + senha);
			
			Properties props = new Properties();
			props.put("resource.loader", "class");
			props.put("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
			ve.init(props);
			
			VelocityContext vc = new VelocityContext();
			Template t = ve.getTemplate("template.vm");  
			vc.put("login", login);
			vc.put("senha", senha);
			System.out.println("TEMPLATE: " + t);
			StringWriter writer = new StringWriter();  
            t.merge(vc, writer);
            System.out.println("[PROCESSADO] "+writer.toString());
            response.getWriter().print(writer.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}  
	}
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processaRequisicao(request, response);
	}
}
