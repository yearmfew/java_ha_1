package servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig
@WebServlet("/FormFileUpload")
public class FormFileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String currentDir = System.getProperty("user.dir");
		final String path = currentDir + "/uploads";
		final Part filePart = request.getPart("file");
		final String fileName = filePart.getName() + ".xlsx";
		String name = request.getParameter("file");
		OutputStream out = null;
		InputStream filecontent = null;

		out = new FileOutputStream(new File(path + File.separator + fileName));
		filecontent = filePart.getInputStream();

		int read = 0;
		final byte[] bytes = new byte[1024];

		while ((read = filecontent.read(bytes)) != -1) {
			out.write(bytes, 0, read);
		}

		request.getRequestDispatcher("konto.jsp").forward(request, response);
	}

}
