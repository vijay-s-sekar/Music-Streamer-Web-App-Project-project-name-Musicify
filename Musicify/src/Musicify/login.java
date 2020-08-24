package Musicify;


import java.io.PrintWriter; 
import java.sql.Connection;   
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
public class login extends HttpServlet{
	private String bb;
	public void doPost(HttpServletRequest req ,HttpServletResponse res)
	{
		String e=req.getParameter("email");
		String  p=req.getParameter("password");
		String q="select email,password from logy";
		String qq="select name,email,password from def";
		String del="truncate table logy";

		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/datastore","root","root");
			
			PreparedStatement ps=con.prepareStatement("insert into logy values(?,?)");

			ps.setString(1, e);
			ps.setString(2, p);
			ps.executeUpdate();
			
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(q);
			rs.next();
			String s=rs.getString(1);
			String ss=rs.getString(2);
			st.executeUpdate(del);
			//System.out.print(s+"  "+ss);
			ResultSet rss=st.executeQuery(qq);
		PrintWriter out=res.getWriter();
		
			int i=0,j=0,k=0,pp=0;
while(rss.next())
{
	bb=rss.getString(1); 
	String sss=rss.getString(2);
	String ssss=rss.getString(3);
	//System.out.print(sss+" "+sss);
	i=s.compareTo(sss);
	j=ss.compareTo(ssss);
	if(i==0)
	{
		k=1;
		break;
	}
}
	if(i!=0&&j!=0)
	{
		res.sendRedirect("register.html");
	}
if(k==0)
	{
		System.out.print("invalid email please sign up ");
		//out.print("please login for further process");
		res.sendRedirect("signup.html");
	}
 if(i==0 && j!=0)
	{
		System.out.print("invalid password");

		res.sendRedirect("error.html");
	}
	
	else
	{
		System.out.println("you have been login sucessfully");

		req.getSession().setAttribute("name", bb);
		res.sendRedirect("welcome.jsp");
		//out.print("hello");
		//res.sendRedirect("ww/welcome.html");

	}
	
			
				
			
		}
		
		catch(Exception e2)
		{
			System.out.print(e2);
		}
	}

}
  