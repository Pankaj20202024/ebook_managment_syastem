package com.user.servlet;

import java.io.IOException;

import com.DB.DBConnect;
import com.DOA.BookDAOImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@jakarta.servlet.annotation.WebServlet("/delete_old_book")
public class DeleteOldBook extends jakarta.servlet.http.HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String em = req.getParameter("em");
			int id = Integer.parseInt(req.getParameter("id"));

			BookDAOImpl dao = new BookDAOImpl(DBConnect.getConn());
			boolean f = dao.oldBookDelete(em, "Old", id);
			HttpSession session = req.getSession();
			if (f) {
				session.setAttribute("succMsg", "Old Book Delete Sucessfully");
				resp.sendRedirect("old_book.jsp");
			} else {
				session.setAttribute("succMsg", "Something wrong on server");
				resp.sendRedirect("old_book.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

}