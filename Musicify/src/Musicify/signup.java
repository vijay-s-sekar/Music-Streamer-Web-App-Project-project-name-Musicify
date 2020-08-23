package Musicify;




import java.io.IOException; 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class signup extends HttpServlet {
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException
	{
		String n=req.getParameter("name");
		String e=req.getParameter("email");
		String p=req.getParameter("password");
		String q="select email from abc";
		String qq="select email from def ";
		String del="truncate table abc";
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/datastore","root","root");
			
			Statement smtr=con.createStatement();
			
			
			smtr.executeUpdate(del);
			
			
			PreparedStatement ps=con.prepareStatement("insert into abc values(?,?,?)");
			ps.setString(1,n);
			ps.setString(2,e);
			ps.setString(3,p);
			
			int i=ps.executeUpdate();
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(q);
			rs.next();
			
			String b=rs.getString(1);
			ResultSet rss=st.executeQuery(qq);

			 rss=st.executeQuery(qq);
			 int j=0,m=0;
			while(rss.next())
			{
				String a=rss.getString(1);
				j=a.compareTo(b);
				if(j==0)
				{
					m=1;
					//res.sendRedirect("login.html");
				}			
								
			}
			
			
			if(m!=0)
			{
				Statement smt=con.createStatement();
				
				
				smt.executeUpdate(del);
				
				
			res.sendRedirect("login.html");
			}
			if(m==0)
			{
				req.setAttribute("email", e);
				
				RequestDispatcher rd=req.getRequestDispatcher("call");
				rd.forward(req, res);
				//in.close();
				//System.out.print("now project is getting ready");
			}
	
		}
		
		catch(Exception e2)
		{
			System.out.print(e2);
		}


}
}


