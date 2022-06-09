package stepdefinitions;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import runner.Runner;
import utility.WriteToExcel;

public class GET_API {
	Logger log = LogManager.getLogger(Runner.class.getName());
	@Test
	@Given("user takes apiname and GetApi")
	public void user_takes_apiname_and_get_api(DataTable dataTable) throws IOException {
		WriteToExcel ex = new WriteToExcel();
		List<Map<String, String>> listdata = dataTable.asMaps(String.class, String.class);
		for (Map<String, String> mapdata : listdata) {
			Response response = null;

			try {
				response = RestAssured.given().header("X-ART-API-KEY", "koPKTXm5v9PPSGHpuqifHeaTh2b8mAA7")
						.get(mapdata.get("baseURI"));

			} catch (Exception e) {
				e.printStackTrace();
			}

			int statuscode = response.getStatusCode();
			
			long responsetime = response.getTime();
			String sec = ((float) responsetime) / 1000 + " s ";

			Date date = new Date();
			String current_date = new SimpleDateFormat("yyyy-MM-dd").format(date);

			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			String current_time = sdf.format(cal.getTime());

			ex.writetoexcel(mapdata.get("name"), mapdata.get("baseURI"), statuscode, sec, current_date, current_time);
			

			log.info("API_Name : " + mapdata.get("name") + " , " + " Endpoint URI : " + mapdata.get("baseURI") + " , "
					+ " Status Code : " + statuscode + " , " + " Response Time : " + sec);

		}

	}
//	@Then("send mail")
//	public void send_mail(String from, String tos[], String subject,
//		      String text) throws MessagingException {
//		Properties props = new Properties();
//	    props.put("mail.smtp.host", "smtp.gmail.com");
//	    props.put("mail.smtp.socketFactory.port", "465");
//	    props.put("mail.smtp.socketFactory.class",
//	        "javax.net.ssl.SSLSocketFactory");
//	    props.put("mail.smtp.auth", "true");
//	    props.put("mail.smtp.port", "465");
//
//	    Session session = Session.getDefaultInstance(props,
//	        new javax.mail.Authenticator() {
//	          protected PasswordAuthentication getPasswordAuthentication() {
//	            return new PasswordAuthentication(
//	            "test01@gmail.com",
//	            "pass1234");// change accordingly
//	          }
//	        });
//
//	    // compose message
//	    try {
//	      MimeMessage message = new MimeMessage(session);
//	      message.setFrom(new InternetAddress(from));// change accordingly
//	      for (String to : tos) {
//	        message.addRecipient(Message.RecipientType.TO,
//	            new InternetAddress(to));
//	      }
//	      /*
//	       * for (String cc : ccs)
//	       * message.addRecipient(Message.RecipientType.CC,new
//	       * InternetAddress(cc));
//	       */
//	      message.setSubject(subject);
//	      // Option 1: To send normal text message
//	      // message.setText(text);
//	      // Option 2: Send the actual HTML message, as big as you like
//	      // message.setContent("<h1>This is actual message</h1></br></hr>" +
//	      // text, "text/html");
//
//	      // Set the attachment path
//	      String filename = "./TestData//Api_Details.xlsx";
//
//	      BodyPart objMessageBodyPart = new MimeBodyPart();
//	      // Option 3: Send text along with attachment
//	      objMessageBodyPart.setContent(
//	          "<h1>Mail from Selenium Project!</h1></br>" + text, "text/html");
//	      Multipart multipart = new MimeMultipart();
//	      multipart.addBodyPart(objMessageBodyPart);
//
//	      objMessageBodyPart = new MimeBodyPart();
//	      DataSource source = new FileDataSource(filename);
//	      objMessageBodyPart.setDataHandler(new DataHandler(source));
//	      objMessageBodyPart.setFileName(filename);
//	      multipart.addBodyPart(objMessageBodyPart);
//	      message.setContent(multipart);
//
//	      // send message
//	      Transport.send(message);
//
//	      System.out.println("message sent successfully");
//
//	    } catch (MessagingException e) {
//	      throw new RuntimeException(e);
//	    }
//	  }// End of SEND method
}
