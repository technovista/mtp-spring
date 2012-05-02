package com.array.testprep.util;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.array.testprep.dao.ServiceDAO;
import com.array.testprep.model.TestTaker;


@SuppressWarnings("serial")
public class ValidatorServlet extends HttpServlet{


	
	public void init(ServletConfig config) {


	}

	/**
	 * 
	 */
	public void destroy() {


	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		this.doGet(request, response);
	}

	/**
	 * @param request	 
	 * @param response
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		System.out.print("\n\t In do get method dfgfbg");	
		PrintWriter out = response.getWriter();
		ServiceDAO<TestTaker> testTakerdao = (ServiceDAO<TestTaker>) ContextProvider
		.getApplicationContext().getBean(Constants.TESTTAKER_DAO);
		TestTaker testTaker = new TestTaker();
		testTaker.setEmailId(request.getParameter("emailId"));
		List<TestTaker> testTakerList = testTakerdao.select(testTaker);
		System.out.println("\t\t\t\t\t  the value in ajax validation is "+testTakerList.size());
		if (testTakerList.size() > 0)
		{
			out.write("failure");
		}
	

	}
}
