package com.green.bank;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.green.bank.model.AccountModel;

//Mock object: Mockito farmework to mock such objects
@RunWith(MockitoJUnitRunner.class)
public class CreateAccountServletTest {
	CreateAccountServlet servlet = new CreateAccountServlet();
	@Mock
	HttpServletRequest request;
	@Mock
	HttpServletRequest requesta;
	@Mock
	HttpServletResponse response;
	@Mock
	HttpServletResponse response1;
	@Mock
	RequestDispatcher rd;

	// @Test
	public void negativeTestPostNullRequestParameters() throws ServletException, IOException {
		when(request.getRequestDispatcher("create_account.jsp")).thenReturn(rd);
		servlet.doPost(request, response);
		Mockito.verify(rd, times(1)).forward(request, response);
		Mockito.verify(request).setAttribute(anyString(), anyObject());
	}

	// @Test
	public void negativeTestPostEmptyRequestParameters() throws ServletException, IOException {
		when(request.getParameter(anyString())).thenReturn("");
		when(request.getRequestDispatcher("create_account.jsp")).thenReturn(rd);
		servlet.doPost(request, response);
		Mockito.verify(rd, times(1)).forward(request, response);
		Mockito.verify(request).setAttribute(anyString(), anyObject());
	}

	// @Test
	public void negativeTestPostPasswordsNotMatch() throws ServletException, IOException {

		when(request.getParameter(anyString())).thenReturn("1545432376");
		when(request.getParameter("password")).thenReturn("abc%0HGDD");
		when(request.getParameter("re_password")).thenReturn("sdjh^0HHH");
		when(request.getRequestDispatcher("create_account.jsp")).thenReturn(rd);
		servlet.doPost(request, response);
		Mockito.verify(rd, times(1)).forward(request, response);
		Mockito.verify(request, times(2)).setAttribute(anyString(), anyObject());
	}

	// @Test
	public void positveTestPost() throws ServletException, IOException {

		when(request.getParameter(anyString())).thenReturn("1545432376");
		when(request.getParameter("password")).thenReturn("abc%0HGDD");
		when(request.getParameter("re_password")).thenReturn("abc%0HGDD");
		when(request.getRequestDispatcher("create_account_progress.jsp")).thenReturn(rd);
		servlet.doPost(request, response);
		Mockito.verify(rd, times(1)).forward(request, response);
		Mockito.verify(request).setAttribute(anyString(), anyObject());
	}

	@Test
	public void testMultipleThreadsNotImpacted() throws ServletException, IOException, InterruptedException {
		RequestWrapper request1 = new RequestWrapper(request);
		when(request.getParameter(anyString())).thenReturn("1545432376");
		when(request.getParameter("password")).thenReturn("abc%0HGDD");
		when(request.getParameter("re_password")).thenReturn("abc%0HGDD");
		when(request.getRequestDispatcher("create_account_progress.jsp")).thenReturn(rd);

		RequestWrapper request2 = new RequestWrapper(requesta);
		when(requesta.getParameter(anyString())).thenReturn("1345678901");
		when(requesta.getParameter("password")).thenReturn("dbc%0HGDD");
		when(requesta.getParameter("re_password")).thenReturn("dbc%0HGDD");
		when(requesta.getRequestDispatcher("create_account_progress.jsp")).thenReturn(rd);
		Thread t1 = new Thread(() -> {
			try {
				servlet.doPost(request1, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		});
		Thread t2 = new Thread(() -> {
			try {
				servlet.doPost(request2, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		});
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		AccountModel o1 = (AccountModel) request1.getAttribute("Account_details");
		AccountModel o2 = (AccountModel) request2.getAttribute("Account_details");
		assertNotEquals(o1, o2);
		assertEquals(o1.getPassword(), "abc%0HGDD");
		assertEquals(o2.getPassword(), "dbc%0HGDD");
		assertEquals(o1.getAddress(), "1545432376");
		assertEquals(o2.getAddress(), "1345678901");
	}

}

class RequestWrapper extends HttpServletRequestWrapper {
	public RequestWrapper(HttpServletRequest request) {
		super(request);
	}

	Map<String, Object> map = new HashMap<>();

	@Override
	public void setAttribute(String name, Object o) {
		map.put(name, o);
	}

	@Override
	public Object getAttribute(String name) {
		return map.get(name);
	}
}
