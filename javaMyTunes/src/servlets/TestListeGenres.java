package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoGenre;
import dao.jpa.DaoGenreJPA;
import objMetiers.Genre;

/**
 * Servlet implementation class TestListeGenres
 */
@WebServlet("/TestListeGenres")
public class TestListeGenres extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		DaoGenre dao = DaoGenreJPA.getInstance();
		List<Genre> listeGenre = dao.loadAll(); 
		
		PrintWriter out = response.getWriter();
		out.println(header);
		
		for(Genre g: listeGenre) {
			HTMLLigneTableau(out, g);
		}
		
		out.println(footer);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private static final String header =
			"<HTML>\n" +
			"	<HEAD>\n" +
			"		<TITLE>Liste des genres</TITLE>\n" +
			"	</HEAD>\n" +
			"	<BODY>\n" +
			"		<H1>Liste des genres</H1>\n" +
			"		<TABLE border=\"1\">\n" +
			"			<TR><TH>Id</TH><TH>Nom</TH></TR>\n";
	
	private static final String footer =
			"		</TABLE>\n" +
			"	</BODY>" +
			"</HTML>\n";
	
	private void HTMLLigneTableau(PrintWriter out, Genre g) {
		out.println("<TR><TD>"+g.getId()+"</TD><TD>"+g.getNom()+"</TD></TR>");
	}

}
