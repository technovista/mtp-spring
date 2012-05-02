package com.array.testprep.springcontroller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import junit.framework.Test;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import com.array.testprep.dao.ServiceDAO;
import com.array.testprep.model.CustomeTestQuestion;
import com.array.testprep.model.QuestionAnswerRelationship;
import com.array.testprep.model.QuestionBank;
import com.array.testprep.model.QuestionType;
import com.array.testprep.model.TestObjective;
import com.array.testprep.model.TestTaker;
import com.array.testprep.springservice.Service;
import com.array.testprep.util.Constants;
import com.array.testprep.util.ContextProvider;

/**
 * FormController
 * 
 * @author mtshah chaged from megha
 * 
 */
public class FormController extends SimpleFormController {

	protected final Logger logger = Logger.getLogger(getClass());

	private boolean prePopulate;

	private Service service;

	private String entity;

	private boolean sessionRequired;

	private static List<String> answers = new ArrayList<String>();

	/**
	 * 
	 * Public accessors: getters n setters
	 */
	public void setService(Service service) {
		this.service = service;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public boolean isPrePopulate() {
		return prePopulate;
	}

	public void setPrePopulate(boolean prePopulate) {
		this.prePopulate = prePopulate;
	}

	public boolean isSessionRequired() {
		return sessionRequired;
	}

	public void setSessionRequired(boolean sessionRequired) {
		this.sessionRequired = sessionRequired;
	}

	/**
	 * @param request
	 *            : Servlet Request
	 * @param response
	 *            : Servlet Response
	 * @param cmd
	 *            : Command Object
	 * @param errors
	 *            : Binded Errors
	 */
	@SuppressWarnings("unchecked")
	public ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object cmd, BindException errors) {

		try {
			logger.info("Entering onSubmit of form controller");
			String action = request.getParameter(Constants.ACTION_PARA).trim()
					.toLowerCase();
			HttpSession session = request.getSession();

			/* Where test is ending */
			if (action.equalsIgnoreCase("END")) {
				// last question result
				logger.info("After Click END");
				int index = Integer.parseInt(request.getParameter("index"));
				logger.info("After get index");
				String ans = request.getParameter("answer");
				logger.info("After get answer");
				setSelectedAnswers(index, ans, session);
				com.array.testprep.model.CustomeTest customeTest = new com.array.testprep.model.CustomeTest();
				customeTest
						.setCustomeTestQuestionsList((List<CustomeTestQuestion>) session
								.getAttribute("mydatalist"));
				// TestTaker tt = (TestTaker)session.getAttribute("testTaker");
				customeTest.setTestTakerId(((TestTaker) session
						.getAttribute("testTaker")).getTestTakerId());
				logger.info("custometest list "
						+ (List<CustomeTestQuestion>) session
								.getAttribute("mydatalist"));
				cmd = customeTest;// session.getAttribute("mydatalist");
				logger.info("CustomeTest obj = " + customeTest.toString());
				session.setAttribute("customeTest", customeTest);

			}

			logger.info(" action = " + action);
			logger.info(" Form object = " + cmd);
			logger.info(" entity = " + entity);
			// TODO replace n ADD constant ENTITY_TESTTAKER
			if (action.equalsIgnoreCase(Constants.LOGIN)
					|| action
							.equalsIgnoreCase(Constants.ENTITY_TESTTAKER_FRENCH)) {
				logger.debug("Set session for " + Constants.LOGIN + " page ");

				session = request.getSession();
				String emailId = request.getParameter("emailId");
				logger.debug("get email ID1 : " + emailId);
				String password = request.getParameter("password");
				session.setAttribute("testTaker", (TestTaker) cmd);
				session.setAttribute("emailId", emailId);
				session.setAttribute("password", password);
				// session.setAttribute("testTakerId", );

				logger.debug("Set email ID11112 : "
						+ session.getAttribute("emailId"));
			}
			String tempEntity = entity;
			if (action.equalsIgnoreCase("start test")) {

				if (request.getParameter("qTypeCheck") != null) {
					logger.debug("<<in check BOX = "
							+ request.getParameter("qTypeCheck") + ">>");

					String value[] = request.getParameterValues("qTypeCheck");
					entity = "";
					for (int i = 0; i < value.length; i++) {
						logger.debug("<<in check BOX = " + value[i] + ">>");

						entity = entity + value[i];
					}
				}

			}
			logger.info(" action = " + action);
			logger.info(" Form object = " + cmd);

			logger.info("Sevice= " + service);
			service.processRequest(action, entity, cmd);
			entity = tempEntity;
			String successView = getSuccessview(getSuccessView(), action);
			Map<String, Object> modelMap = service.getModelMap();

			List<QuestionAnswerRelationship> qaList1 = null;
			if (action.equalsIgnoreCase("start test")) {

				qaList1 = (List<QuestionAnswerRelationship>) modelMap
						.get("myTestList");

				logger.info(" list session size= " + qaList1.size());
				session.setAttribute("mydatalist", qaList1);

			}

			/*
			 * when login successful store login object into session very first
			 * time
			 */
			if (action.equalsIgnoreCase(Constants.LOGIN)) {
				logger.debug("set testTaker object into session");
				List l = (List) modelMap.get("testTakerList");
				if (l != null) {
					TestTaker testTaker = (TestTaker) l.get(0);
					session.setAttribute("testTaker", testTaker);

				}
			}

			logger.info("Exiting onSubmit of form controller");
			if (modelMap == null) {
				logger.info("Model Map == NULL");
				return new ModelAndView(new RedirectView(successView));
			} else {
				logger.info("Model Map != NULL");
				logger.info("Success view = " + successView);
				logger.info("Model Map =" + modelMap.size());

				return new ModelAndView(new RedirectView(successView), "model",
						modelMap);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			System.err.print(e.getMessage());
			logger.error(e.getStackTrace());
			System.out.println(e.toString());
			e.getStackTrace();
			if (!sessionRequired) {
				return new ModelAndView("exception");
			} else {
				return new ModelAndView("errorPage");
			}
		}

	}

	/**
	 * @param request
	 *            :Servlet Request
	 * @throws ServletException
	 *             ,InstantiationException:
	 */
	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws ServletException, InstantiationException,
			IllegalAccessException {

		logger.info("Entering formBackingObject of form controller");

		String id = request.getParameter("id");
		if (null != id && !id.equals("")) {

			String s = request.getParameter("action");
			// set action - delete user : only for admin
			request.setAttribute(Constants.OPERATION_DELETE_USER, s);
			try {
				return service.findById(Integer.parseInt(id));
			} catch (NumberFormatException nfe) {
				logger.error(nfe.getMessage());
				nfe.getStackTrace();

			} catch (Exception e) {
				logger.error(e.getMessage());
				e.getStackTrace();
			}
		}
		logger.info("Exiting formBackingObject of form controller");
		return BeanUtils.instantiateClass(getCommandClass());
	}

	/**
	 * @param request
	 *            :Servlet Request
	 */
	@SuppressWarnings("unchecked")
	protected Map referenceData(HttpServletRequest request) {
		logger.info("\n\t\tEntering reference data of form controller");

		//		
		if (prePopulate) {

			Map modelMap = new HashMap<String, List>();
			logger.info("<<Entity = >>" + entity);

			if (entity.equalsIgnoreCase(Constants.ENTITY_QUESTION)) {
				ServiceDAO<QuestionBank> questionBankdao = (ServiceDAO<QuestionBank>) ContextProvider
						.getApplicationContext().getBean(
								Constants.QUESTIONBANK_DAO);

				// populate question list from database tables.
				List<QuestionBank> questionList = questionBankdao
						.selectAll("QuestionBank");

				modelMap.put("questionBank", questionList);
				logger.debug("list size = s" + questionList.size());

				ServiceDAO<QuestionType> questionTypedao = (ServiceDAO<QuestionType>) ContextProvider
						.getApplicationContext().getBean(
								Constants.QUESTIONBANK_DAO);
				// populate question type list from database tables.
				List<QuestionType> questiontypeList = questionTypedao
						.selectAll("QuestionType");
				modelMap.put("questionType", questiontypeList);
				logger.debug("list size = s" + questiontypeList.size());

				ServiceDAO<TestObjective> testObjectivedao = (ServiceDAO<TestObjective>) ContextProvider
						.getApplicationContext().getBean(
								Constants.QUESTIONBANK_DAO);
				// populate testObjective  list from database tables.
				List<TestObjective> testObjectiveList = testObjectivedao
						.selectAll("TestObjective");
				modelMap.put("testObjective", testObjectiveList);
				logger.debug("list size = s" + testObjectiveList.size());

			}
			if (request.getParameter("action") != null) {
				if (request.getParameter("action").equalsIgnoreCase(
						Constants.OPERATION_ADD)) {
					/*
					 * ServiceDAO<AnswerBank> answerBankdao =
					 * (ServiceDAO<AnswerBank>) ContextProvider
					 * .getApplicationContext().getBean(
					 * Constants.ANSWERBANK_DAO); // populate list from databse
					 * tables. List<AnswerBank> answerList = answerBankdao
					 * .selectAll("AnswerBank");
					 * 
					 * modelMap.put("answerBank", answerList);
					 * logger.debug("list size = s" + answerList.size());
					 */

				}
			}
			if (this.entity.equalsIgnoreCase(Constants.ENTITY_TEST)
					|| this.entity
							.equalsIgnoreCase(Constants.ENTITY_TESTOBJECTIVE)) {
				ServiceDAO<Test> testdao = (ServiceDAO<Test>) ContextProvider
						.getApplicationContext().getBean(Constants.TEST_DAO);

				// populate list from database tables.
				List<Test> testList = testdao.selectAll("Test");

				modelMap.put("testType", testList);
				logger.debug("list size = s" + testList.size());
			}
			if (this.entity.equalsIgnoreCase(Constants.ENTITY_TESTOBJECTIVE)) {
				logger.info("In if  :::");
				HttpSession session = request.getSession();
				TestObjective testObj = new TestObjective();
					
				Enumeration  enu = request.getAttributeNames();
				while(enu.hasMoreElements())
				{
					String str = enu.nextElement().toString();
					logger.info("Request para : "+str+" = "+ request.getAttribute(str));
				}
				if (request.getParameter("testId") != null) {
					logger.info("In if testid != null  :::");
					testObj.setTestId(Integer.parseInt(request
							.getParameter("testId")));
					session.setAttribute("testId", request.getParameter("testId"));
				}
				if(request.getParameter("testId")==null && session.getAttribute("testId")!=null)
				{
					testObj.setTestId(Integer.parseInt(session
							.getAttribute("testId").toString()));
					
				}
				ServiceDAO<TestObjective> testObjectivedao = (ServiceDAO<TestObjective>) ContextProvider
						.getApplicationContext().getBean(Constants.TEST_DAO);

				// populate list from database tables.
				List<TestObjective> testObjectiveList = testObjectivedao
						.select(testObj);
				modelMap.put("testObjectiveList", testObjectiveList);
			}
			logger.debug("<<<< query string   = " + request.getQueryString());
			request.getQueryString();
			if (request.getParameter("model") != null) {
				logger.debug("<<<< model  = " + request.getParameter("model"));

				String[] qList = request.getParameterValues("model");
				for (int i = 0; i < qList.length; i++) {
					logger.info("\n\t\t<<<<  ctestq= " + qList[i]);

				}
				logger.debug("Maps : = " + request.getParameterMap());

				modelMap.put("qar", request.getParameterMap());
			}
			logger.debug("model list size = ");

			HttpSession session = request.getSession();

			if (session.getAttribute("mydatalist") != null) {
				if (request.getParameter("id") != null
						&& request.getParameter("ans") != null) {
					int index = Integer.parseInt(request.getParameter("id"));
					String ans = request.getParameter("ans");
					setSelectedAnswers(index, ans, session);
				}

				if (request.getParameter("ans") != null) {
					answers.add(request.getParameter("ans").toString());
					// session.setAttribute("answerList", answers);
					logger.info("selected result= "
							+ request.getParameter("ans").toString());
				}

				// modelMap = new HashMap<String, Object>();
				modelMap.put("myTestList", session.getAttribute("mydatalist"));

			}
			logger.info("Exiting referenceData of form controller");

			return modelMap;
		} else {
			logger.info("Exiting referenceData of form controller #in else ");
			return null;
		}

	}

	/**
	 * @param request
	 *            :Servlet Request
	 * @param response
	 *            :Servlet Response
	 * @throws Exception
	 */
	public ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		logger.info("Entering into handleRequest of FromController ");
		/*
		 * if(session.getAttribute("mydatalist")!=null &&
		 * this.entity.equalsIgnoreCase("CustomeTest")) {
		 * if(request.getParameter("answer")!=null){
		 * answers.add(request.getParameter("answer"));
		 * logger.info("selected result= "+request.getParameter("answer")); }
		 * Map<String, Object> modelMap = new HashMap<String, Object>();
		 * modelMap.put("myTestList", session.getAttribute("mydatalist"));
		 * logger
		 * .info("Exiting from handleRequest of FromController for my qa list "
		 * ); return new ModelAndView(new RedirectView(getSuccessView()),
		 * "model", modelMap); }
		 */
		if (!sessionRequired) {
			logger.info(" \n\n\t \t Entering from  another url ---------- ");
			if (session.getAttribute("emailId") == null) {

				logger
						.info(" \n\n\t \tEntering from  another url without testTaker  ---------- ");
				return new ModelAndView("errorPage");
			}
		}
		return super.handleRequestInternal(request, response);
	}

	/**
	 * 
	 * @param view
	 *            : successview of controller
	 * @param action
	 *            : action which is concate into query String
	 * @return successView with qurey String
	 */
	private String getSuccessview(String view, String action) {

		String successView = view + "?" + Constants.STATUS_MSG + "=" + action;

		return successView;
	}

	public void setSelectedAnswers(int index, String ans, HttpSession s) {
		HttpSession session = s;
		List<CustomeTestQuestion> tqList = (List<CustomeTestQuestion>) session
				.getAttribute("mydatalist");
		CustomeTestQuestion tq = tqList.get(index);
		tq.setSelectedAnswer(ans);
		tqList.set(index, tq);
		session.setAttribute("mydatalist", tqList);
	}

	public List<String> getAnswers() {
		return answers;
	}

	public void setAnswers(List<String> answers) {
		this.answers = answers;
	}
}
