package Musicify;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;


public class mail extends HttpServlet  {
	private final String apikey="916a3a98aeb2d00846cb5eff53d4af7d";
	public void doPost(HttpServletRequest req ,HttpServletResponse res)throws IOException
	{
//		String ee=req.getParameter("mails");
		String ee=(String) req.getAttribute("email");
		System.out.print("hello");

		
		try {
			String email=ee;
			String url="https://apilayer.net/api/check?access_key="+apikey+"&email="+email+"&smtp=1&format=1";
			URL urlobj=new URL(url);
			
			HttpURLConnection con=(HttpURLConnection) urlobj.openConnection();
			
			con.setRequestMethod("GET");
			
			con.setRequestProperty("User-Agent", "Mozilla/17.0");
			String inputLine;
	 
			BufferedReader in =new BufferedReader(
					new InputStreamReader(con.getInputStream()));
			StringBuffer response =new StringBuffer();
			
			while((inputLine =in.readLine())!=null)
			{
				
				response.append(inputLine);
			}
			
			String ddp=response.toString();
			System.out.println(ddp);
			req.setAttribute("ddp",ddp);
			req.setAttribute("email", email);
			
			RequestDispatcher rd=req.getRequestDispatcher("sq");
			rd.forward(req, res);
			in.close();
		}
		catch(Exception e)
		{
			e.printStackTrace(); 
		}
	
	

		
		
		
				}
	
	
	
 }