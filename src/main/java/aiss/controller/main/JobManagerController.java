package aiss.controller.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.restlet.data.CharacterSet;
import org.restlet.ext.jackson.JacksonRepresentation;

import aiss.montage.JobManager;
import aiss.montage.MontageJobResult;

/**
 * Servlet implementation class JobManagerController
 */
public class JobManagerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JobManagerController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jobId = request.getParameter("id");
		MontageJobResult result = JobManager.queryResult(jobId);
		if (result == null)
		{
			System.out.println("No job found with that ID");
			// Send chat query response
			response.getWriter().append("job not found");
		}
		else
		{
			JacksonRepresentation<MontageJobResult> resultRepr = new JacksonRepresentation<MontageJobResult>(result);
			resultRepr.setCharacterSet(CharacterSet.UTF_8);
			String chatJson = resultRepr.getText();
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json; charset=UTF-8");
			System.out.println(chatJson);
			// Send chat query response
			response.getWriter().append(chatJson);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
