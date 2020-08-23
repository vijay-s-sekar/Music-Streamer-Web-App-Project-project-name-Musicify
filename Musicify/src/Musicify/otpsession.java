package Musicify;



import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class otpsession  extends HttpServlet{

	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException{
		String otpk=req.getParameter("number");
		String q="select otpi from otp";
		String del="truncate table otp";
		String l="select*from abc";/*
		String l="select name from abc";
		String ll="select email from abc";
		String lll="select password from abc";*/
		String dell="truncate table abc";
		

	try
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/datastore","root","root");
		

		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(q);
		rs.next();
		String s=rs.getString(1);

		int i=otpk.compareTo(s);
		System.out.print(i+"   ");
		String n,e,p;
		st.executeUpdate(del);
		if(i==0)
		{
			Statement smt=con.createStatement();
			
			//query to display all records from table employee
			
			
			//to execute query
			ResultSet rss=smt.executeQuery(l);
			while(rss.next())
			{
				n=rss.getString(1);
				e=rss.getString(2);
				p=rss.getString(3);
			
			PreparedStatement pss=con.prepareStatement("insert into def values(?,?,?)");
			pss.setString(1,n);
			pss.setString(2,e);
			pss.setString(3,p);
			pss.executeUpdate(dell);
			int y=pss.executeUpdate();
			
			req.getSession().setAttribute("name", n);
			res.sendRedirect("welcome.jsp");
			}
			
			System.out.print("sucess");
		}
		else
		{
			System.out.print("no");
			res.sendRedirect("otpincorrect.html");
		}

		System.out.print(s+"  "+otpk);
		


	}
	catch(Exception e2)
	{
		System.out.print(e2);

		
	}
		
		

	}

}
