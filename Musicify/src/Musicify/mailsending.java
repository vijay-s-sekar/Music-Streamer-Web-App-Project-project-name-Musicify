package Musicify;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Date;
import java.util.Properties;
import java.util.function.Supplier;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class mailsending extends HttpServlet {

	 private Object otp;

	public  void doPost(HttpServletRequest  req ,HttpServletResponse res) {
		Statement stmt=null;
		String email=(String) req.getAttribute("email");
		
		try{
	String h="";     
	   
		   			Supplier<String> otps=()->{
		   				String otp="";

		   				for(int i=1;i<=6;i++)
		   				{
		   				 otp=otp+(int)(Math.random()*10);
		   				}
		   					
		   					return otp;
		   					
		   			};
		   			h=otps.get();
		   
	             String host ="smtp.gmail.com" ;
	             String user = "vijayprojectdemo@gmail.com"; //Enter Your Gmail Username
	             
	             
	             
	             String pass = "9952171440";  //Enter Your Gmail Password
	             
	             
	             
	             
	             String to = email ;
	             String from = "vijay9047662558@gmail.com";
	             String subject = "otp verification:";
	             String messageText =h;
	             boolean sessionDebug = false;

	             Properties props = System.getProperties();

	             props.put("mail.smtp.starttls.enable", "true");
	             props.put("mail.smtp.host", host);
	             props.put("mail.smtp.port", "587");
	             props.put("mail.smtp.auth", "true");
	             props.put("mail.smtp.starttls.required", "true");

	             java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
	             Session mailSession = Session.getDefaultInstance(props, null);
	             mailSession.setDebug(sessionDebug);
	             Message msg = new MimeMessage(mailSession);
	             msg.setFrom(new InternetAddress(from));
	             InternetAddress[] address = {new InternetAddress(to)};
	             msg.setRecipients(Message.RecipientType.TO, address);
	             msg.setSubject(subject); msg.setSentDate(new Date());
	             msg.setText(messageText);

	            Transport transport=mailSession.getTransport("smtp");
	            transport.connect(host, user, pass);
	            transport.sendMessage(msg, msg.getAllRecipients());
	            transport.close();
	            System.out.println("message send successfully");
	        //   String otp=req.getParameter("number");
	    		//String q="select otpi from ootp";
	    	
	    	
	    		Class.forName("com.mysql.jdbc.Driver");
	    		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/datastore","root","root");
	    		
	    		PreparedStatement ps=con.prepareStatement("insert into otp values(?)");
	    		
	    		ps.setString(1, h);
	    		ps.executeUpdate();

	    		/*Statement st=con.createStatement();
	    		ResultSet rs=st.executeQuery(q);
	    		rs.next();
	    		String s=rs.getString(1);
	    		
	    		 stmt = (Statement) con.createStatement();
	    	      String query1 = "INSERT INTO otp " + "VALUES (otps.get())";
	    	      stmt.executeUpdate(query1);
	    	     // query1 = "INSERT INTO InsertDemo " + "VALUES (2, 'Carol', 42)";
	    	      stmt.executeUpdate(query1);*/
	    		
	    	res.sendRedirect("wel.html");

	    	}
	      
	            
	         catch(Exception ex)
	         {
	             System.out.println(ex);
	         }
	}

	 }