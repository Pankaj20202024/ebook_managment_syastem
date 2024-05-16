package com.user.servlet;

import java.io.IOException;

import com.DB.DBConnect;
import com.DOA.UserDAOImpl;
import com.entity.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;



@jakarta.servlet.annotation.WebServlet("/login")
public class LoginServlet extends jakarta.servlet.http.HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			UserDAOImpl dao = new UserDAOImpl(DBConnect.getConn());
			HttpSession session = req.getSession();
			
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			
			if("admin@gmail.com".equals(email) && "admin".equals(password)) {
				User us = new User();
				us.setName("Admin");
				session.setAttribute("userobj", us);
				resp.sendRedirect("admin/home.jsp");
			}
			else {
				User us = dao.login(email, password);
				if(us != null) {
					session.setAttribute("userobj", us);
					resp.sendRedirect("index.jsp");
				}
				else {
					session.setAttribute("failedMsg", "Email & Password Invalid");
					resp.sendRedirect("login.jsp");
				}
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
