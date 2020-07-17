package com.green.bank;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.green.bank.model.AccountModel;
import com.green.bank.util.AccountInvalidException;

public class CreateAccountServlet extends HttpServlet {
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String account_no, first_name, last_name, address, city, branch, zip, username, password, re_password, phone_number,
		email, account_type;
		int amount=0;
		first_name = request.getParameter("first_name");
		last_name = request.getParameter("last_name");
		address = request.getParameter("address");
		city = request.getParameter("city");
		branch = request.getParameter("branch");
		zip = request.getParameter("zip");
		username = request.getParameter("username");
		password = request.getParameter("password");
		re_password = request.getParameter("re_password");
		phone_number = request.getParameter("phone");
		email = request.getParameter("email");
		account_type = request.getParameter("account_type");
		if (request.getParameter("amount") != null && request.getParameter("amount").length() > 0) {
			amount = Integer.parseInt(request.getParameter("amount"));
		}
		// Generating account number
		Random rand = new Random();
		int random_num = 100000 + rand.nextInt(999999);
		if (first_name != null && first_name.length() > 2 && last_name != null && last_name.length() > 2) {
			account_no = first_name.substring(0, 2) + last_name.substring(0, 2) + random_num;
		// Getting Current date
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String reg_date = df.format(new Date());

		AccountModel am = new AccountModel();
		try {
			am.setAccount_no(account_no);
			am.setFirst_name(first_name);
			am.setLast_name(last_name);
			am.setAddress(address);
			am.setCity(city);
			am.setBranch(branch);
			am.setZip(zip);
			am.setUsername(username);
			am.setPassword(password);
			am.setPhone_number(phone_number);
			am.setEmail(email);
			am.setAccount_type(account_type);
			am.setAmount(amount);
			am.setReg_date(reg_date);
		} catch (AccountInvalidException e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("create_account.jsp").forward(request, response);
			return;
		}

		if (password != null && password.equals(re_password)) {
			request.setAttribute("Account_details", am);
			RequestDispatcher rd = request.getRequestDispatcher("create_account_progress.jsp");
			rd.forward(request, response);
			return;
		} else {
			request.setAttribute("not_match", "yes");
			request.setAttribute("error",
					"Passwords do not match");
			RequestDispatcher rd = request.getRequestDispatcher("create_account.jsp");
			rd.forward(request, response);
			return;
		}}
		else {
			request.setAttribute("error",
					"First name and last name cannot be blank and should be more than 3 character each");
			request.getRequestDispatcher("create_account.jsp").forward(request, response);
			return;
			// TODO send him back to the login page, displaying proper error
			// take input again..
		}


	}
}
