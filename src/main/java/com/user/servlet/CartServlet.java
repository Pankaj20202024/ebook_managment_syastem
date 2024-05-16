package com.user.servlet;

import java.io.IOException;

import com.DB.DBConnect;
import com.DOA.BookDAOImpl;
import com.DOA.CartDAOImpl;
import com.entity.BookDtls;
import com.entity.Cart;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@jakarta.servlet.annotation.WebServlet("/cart")
public class CartServlet extends jakarta.servlet.http.HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int bid = Integer.parseInt(req.getParameter("bid"));
			int uid = Integer.parseInt(req.getParameter("uid"));

			BookDAOImpl dao = new BookDAOImpl(DBConnect.getConn());
			BookDtls b = dao.getBookById(bid);

			Cart c = new Cart();
			c.setBid(bid);
			c.setUserId(uid);
			c.setBookName(b.getBookName());
			c.setAuthor(b.getAuthor());
			c.setPrice(Double.parseDouble(b.getPrice()));
			c.setTotalPrice(Double.parseDouble(b.getPrice()));

			CartDAOImpl dao2 = new CartDAOImpl(DBConnect.getConn());
			boolean f = dao2.addCart(c);

			HttpSession session = req.getSession();

			if (f) {
				session.setAttribute("addCart", "Book Added to cart");
				resp.sendRedirect("all_new_book.jsp");

			} else {
				session.setAttribute("failed", "Something wrong on server");
				resp.sendRedirect("all_new_book.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}