package br.com.main.servlet;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class ServletPadrao
 */
@WebServlet(urlPatterns="/padrao")
public class ServletPadrao extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletPadrao() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Acessando doGet no ServletPadrao");
		/*
		VelocityEngine ve = new VelocityEngine();  
		try {
			Properties velocityProperties = new Properties();
			velocityProperties.setProperty("file.resource.loader.path", getServletContext().getRealPath("/"));
			ve.init(velocityProperties);
			VelocityContext vc = new VelocityContext();
			Template t = ve.getTemplate("index.vm");  
			System.out.println("TEMPLATE: " + t);
			StringWriter writer = new StringWriter();  
            t.merge(vc, writer);
            response.getWriter().print(writer.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}  
		
		//response.getWriter().print("Hello World via Servlet");
		 * */
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Acessando doPost no ServletPadrao");
	}

}
