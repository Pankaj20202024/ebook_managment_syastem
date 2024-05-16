package com.admin.servlet;

import java.io.IOException;

import com.DB.DBConnect;
import com.DOA.BookDAOImpl;
import com.entity.BookDtls;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@jakarta.servlet.annotation.WebServlet("/delete")

public class BooksDeleteServlet extends jakarta.servlet.http.HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int id = Integer.parseInt(req.getParameter("id"));
			BookDAOImpl dao = new BookDAOImpl(DBConnect.getConn());
			boolean f = dao.deleteBooks(id);
			HttpSession session = req.getSession();
			
			if(f) {
				session.setAttribute("succMsg", "Book Delete Successfully..");
				resp.sendRedirect("admin/all_books.jsp");
			}
			else {
				session.setAttribute("failedMsg", "Something wrong on server..");
				resp.sendRedirect("admin/all_books.jsp");
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	

}
