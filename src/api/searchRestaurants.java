package api;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import db.DBConnection;
import db.MongoDBConnection;
import db.MySQLDBConnection;

/**
 * Servlet implementation class searchRestaurants
 */
@WebServlet("/restaurants")
public class searchRestaurants extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//private DBConnection connection = new MongoDBConnection();
	private DBConnection connection = new MySQLDBConnection();

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public searchRestaurants() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONArray array = new JSONArray();
		if (request.getParameterMap().containsKey("lat") 
			&& request.getParameterMap().containsKey("lon")) {
			String term = request.getParameter("term");
			 //String userId = request.getParameter("user_id");
			String userId = "1111";
			double lat = Double.parseDouble(request.getParameter("lat"));
			double lon = Double.parseDouble(request.getParameter("lon"));
			array = connection.searchRestaurants(userId, lat, lon, term);
		}
			 
		// return PrintWriter to front-end client side
		RpcParser.writeOutput(response, array);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
