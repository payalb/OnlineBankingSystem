package com.green.bank;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.green.bank.database.UserRepository;
import com.green.bank.model.AccountModel;

@RunWith(MockitoJUnitRunner.class)
public class LoginServletTest {
	
	LoginServlet servlet = new LoginServlet();
	@Mock
	HttpServletRequest request;
	@Mock 
	HttpServletResponse response;
	@Mock
	RequestDispatcher rd;
	@Mock
	UserRepository rep;
	
	@Test
	public void testUserNotPresent() throws ServletException, IOException {
		when(rep.checkUserExist(anyString(),anyString())).thenReturn(Optional.<AccountModel>empty());
		when(request.getRequestDispatcher("login.jsp")).thenReturn(rd);
		servlet.doPost(request,response);
		Mockito.verify(rd,times(1)).forward(request,response);
		Mockito.verify(request).setAttribute(anyString(),anyString());
	}
}