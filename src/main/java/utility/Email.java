package utility;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

public class Email {

	static public void sendMail() {

		EmailAttachment attachment = new EmailAttachment();
		String path1 = "./TestReport/ShipGlobal_Report.html";
	
		String reportPath = path1;
		attachment.setPath(reportPath);
		MultiPartEmail email = new MultiPartEmail();
		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(587);
     
		email.setStartTLSEnabled(true);
		email.setAuthentication("chandan.k@shipglobal.in", "zfbi qpsg ewkg lzmn");

		try {

			email.addTo("chandangupta11july@gmail.com", "chandan");
		//	email.addTo("manish@shipglobal.in", "manish");

		} catch (EmailException e) {
			e.printStackTrace();
		}
		try {
			email.setFrom("chandan.k@shipglobal.in", "chandan kumar gupta");
			email.setSubject("Vendor Panel Test Report");
		} catch (EmailException e) {

			e.printStackTrace();
		}

		try {

			email.setMsg("Kindly find ShipGlobal Test Report.");

		} catch (EmailException e) {

			e.printStackTrace();
		}

		// add the attachment

		try {
			email.attach(attachment);
		} catch (EmailException e) {

			e.printStackTrace();

		}

		try {
			email.send();
			System.out.println("mail send sucessfully");
		} catch (EmailException e) {

			e.printStackTrace();
			System.out.println("mail  not send ");

		}
		

	}
}
