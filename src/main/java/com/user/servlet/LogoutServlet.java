package com.user.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;



@jakarta.servlet.annotation.WebServlet("/logout")
public class LogoutServlet extends jakarta.servlet.http.HttpServlet {
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			HttpSession session = req.getSession();
			session.removeAttribute("userobj");
			
			HttpSession session2 = req.getSession();
			session.setAttribute("succMsg", "Logout Sucessfully..");
			resp.sendRedirect("login.jsp");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
