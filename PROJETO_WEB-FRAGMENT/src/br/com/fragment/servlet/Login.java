package br.com.fragment.servlet;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Acessando doGet no ServletPadrao");
		VelocityEngine ve = new VelocityEngine();  
		try {
			String login = (String) request.getAttribute("login");
			String senha = (String) request.getAttribute("senha");
			System.out.println(request.getSession().getAttribute("login"));
			
			
			System.out.println("Login: "+ login);
			System.out.println("Senha: "+ senha);
			

			Properties props = new Properties();
			props.put("resource.loader", "class");
			props.put("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
			ve.init(props);
			
			VelocityContext vc = new VelocityContext();
			vc.put("title", "Login");
			vc.put("login", "");
			vc.put("senha", "");
			
			Template t = ve.getTemplate("login.vm");  
			System.out.println("TEMPLATE: " + t);
			StringWriter writer = new StringWriter();  
            t.merge(vc, writer);
            System.out.println("[PROCESSADO] " +writer.toString());
            response.getWriter().print(writer.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
