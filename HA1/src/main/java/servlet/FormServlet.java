package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FormServlet
 */
@WebServlet("/FormServlet")
public class FormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("HEllo");
		System.out.println(request);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String loginname = request.getParameter("username");
		String password = request.getParameter("password");
		if (loginname.equals(password)) {
			System.out.println("Erfolgreicher Login");
			request.setAttribute("loginname", loginname);
			request.getRequestDispatcher("output.jsp").forward(request, response);

		} else {
			request.getRequestDispatcher("login.jsp").forward(request, response);

		}

	}

}
