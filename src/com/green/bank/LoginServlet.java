package com.green.bank;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.green.bank.database.UserRepository;
import com.green.bank.model.AccountModel;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 2178865317863049636L;

	UserRepository rep = new UserRepository();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String UserName, password;
		
		UserName = request.getParameter("UserName");
		password = request.getParameter("password");

		if (!rep.checkUserExist(UserName, password).isPresent()) {
			request.setAttribute("isPassOK", "No");
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
			return;
		} else {
			AccountModel am = rep.checkUserExist(UserName, password).get();
			// Setting Session variable for current User
			HttpSession session = request.getSession();
			session.setAttribute("userDetails", am);

			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);

		}
	}
}
