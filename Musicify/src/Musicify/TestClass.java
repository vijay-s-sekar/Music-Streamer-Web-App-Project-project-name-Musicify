package Musicify;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;



public class TestClass extends HttpServlet{
	
	public  void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException
	{
		
		String inputJson=(String) req.getAttribute("ddp");
		String email=(String) req.getAttribute("email");
		System.out.print(inputJson);
		
		String del="truncate table abc";
		 
		ObjectMapper mapper=new ObjectMapper();
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/datastore","root","root");
	
			MainParser mp=mapper.readValue(inputJson,MainParser.class);
			float oo= (float) mp.getScore();
			
			System.out.println("this is :TestClass:  "+oo);
			
			System.out.println(email);
			if(oo>=0.8)
			{
				req.setAttribute("email", email);
				
				RequestDispatcher rd=req.getRequestDispatcher("email");
				rd.forward(req, res);
			}
			else
			{
				Statement smt=con.createStatement();
				
				
				smt.executeUpdate(del);

				
				res.sendRedirect("error.html");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}

}
