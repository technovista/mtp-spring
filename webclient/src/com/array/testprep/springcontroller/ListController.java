
package com.array.testprep.springcontroller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import junit.framework.Test;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.array.testprep.model.TestTaker;
import com.array.testprep.springservice.Service;
import com.array.testprep.util.Constants;
/**
 * ListController
 * 
 * @version
 * @author mtshah
 */
public class ListController implements Controller {

	/** Logger for this class and subclasses */
	protected final Logger logger = Logger.getLogger(getClass());

	private Service service;

	private String view;

	private String entity;

	private boolean sessionRequired;

	/**
	 * Public accessors : getter and setters
	 * 
	 */
	public void setService(Service service) {
		this.service = service;
	}

	public void setView(String view) {
		this.view = view;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public boolean isSessionRequired() {
		return sessionRequired;
	}

	public void setSessionRequired(boolean sessionRequired) {
		this.sessionRequired = sessionRequired;
	}
	/**
	 * This method handles request of user.  
	 * @param request:Servlet
	 *            Request
	 * @param response:Servlet
	 *            Response
	 * @throws ServletException.IOException
	 */
	@SuppressWarnings("unchecked")
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
			logger.info("Entering handle requeset of list controller ");

			String action = Constants.OPERATION_LIST;
			Object obj = null;

			if (request.getParameter(Constants.ACTION_SEARCH) != null) {
				action = request.getParameter(Constants.ACTION_SEARCH).trim()
						.toLowerCase();
			}
			logger.info("<<Object = "+obj);
			logger.info("<<entity>> = "+entity);
			if(entity.equalsIgnoreCase(Constants.ENTITY_CUSTOMETEST))
			{
				HttpSession session=request.getSession();
				logger.info("CustomeTest obj = "+session.getAttribute("customeTest"));
				obj = (Object)session.getAttribute("customeTest");
				
				
			}if(entity.equalsIgnoreCase(Constants.ALL_CUSTOMETEST))
			{
				HttpSession session=request.getSession();
				//CustomeTest test = new CustomeTest();
				com.array.testprep.model.CustomeTest customeTest = new com.array.testprep.model.CustomeTest();
				
				customeTest.setTestTakerId(  ((TestTaker)session.getAttribute("testTaker")).getTestTakerId() );
				logger.info("CustomeTest obj = "+session.getAttribute("test"));
				obj = (Object)customeTest;
				
				
			}
			
			/* Get parameter for testTypeId */
//			Enumeration e=request.getParameterNames();
//			while(e.hasMoreElements())
//			{
//				logger.info("\n para = "+e.nextElement().toString());
//			}
//			logger.info("\n para ADD = "+request.getParameter("Add"));
//			if(request.getParameter("questionTypeId")!=null)
//			{
//				logger.info("<<in check BOX = "+request.getParameter("questionTypeId")+">>");
//				obj=null;
//				String value[]=request.getParameterValues("questionType");
//				for(int i=0;i< value.length;i++)
//				{
//				obj=obj+value[i]+",";
//				}
//			}
			if (request.getParameter("ID") != null) {
				obj = request.getParameter("ID").trim().toLowerCase();
			}
			/* Get parameter for isApproved */
			if (request.getParameter("ISAPPROVED") != null) {
				obj = request.getParameter("ISAPPROVED").trim().toLowerCase();
			}
			logger.info("<<Object = "+obj);
			service.processRequest(action, entity, obj);
		
			HttpSession session = request.getSession();
			logger.info("list controller session emailid " +session.getAttribute("emailId"));

			/* check session required for page or not */
			if (!sessionRequired) {

				if (session.getAttribute("emailId") == null) {

					return new ModelAndView("errorPage");
				}
			}
			if (entity.equalsIgnoreCase(Constants.ENTITY_LOGOUT)) {
				session.removeAttribute("testTaker");
				session.removeAttribute("emailId");
			}

			Map<String, Object> modelMap = service.getModelMap();
			//logger.info("<<<             list size = "+modelMap.size());
			//logger.info("Name testList: "+modelMap.containsKey("testList"));
			/*if(modelMap.containsKey("testList"))
			{
				logger.info("In IF loop retruning new view");
				Map<String, Object> model = new HashMap();
				List<String> list  = new ArrayList<String>();
				list.add("SCJP");
				list.add("SEVEN");
				list.add("EIGHT");
				model.put("testList", list);
				ModelAndView mv = new ModelAndView(view, "model", model);
				System.out.println("Only view = "+view+"Model View = "+mv.getView());
				System.out.println("GET MODEL()"+ mv.getModel());
				return mv;
				
			}*/
			
			logger.info("Exiting handle request of list controller modelMap: " +modelMap);

			if (modelMap == null) {
				logger.info("model map is null");
				return new ModelAndView(view);
			} else {
				logger.info("model map is not null");
				ModelAndView mv = new ModelAndView(view, "model", modelMap);
				System.out.println("GET MODEL()"+ mv.getModel());
				return mv;
				
			}
		} catch (Exception e) {
			
			logger.error(e.toString());
			e.printStackTrace();
			e.getStackTrace();
			return new ModelAndView("\n\n\t\t exception in List Controller ");
		}
	}
}