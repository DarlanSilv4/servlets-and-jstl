package br.com.agenda.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.agenda.dao.ContactDao;
import br.com.agenda.model.Contact;

@WebServlet("/addContact")
public class AddContactServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		
		String name = req.getParameter("name");
		String address = req.getParameter("address");
		String email = req.getParameter("email");
		String birthdayString = req.getParameter("birthday");
		Calendar birthday = null;
		
		try {
			Date date = new SimpleDateFormat("dd/MM/yyyy").parse(birthdayString);
			birthday = Calendar.getInstance();
			birthday.setTime(date);
		}catch(ParseException e) {
			out.println("Cannot convert date");
			return;
		}
		
		Contact contact = new Contact();
		contact.setName(name);
		contact.setAddress(address);
		contact.setEmail(email);
		contact.setBirthday(birthday);
		
		ContactDao dao = new ContactDao();
		dao.add(contact);
		
		out.println("<html>");
		out.println("<body>");
		out.println("Contact " + contact.getName() +
		" contact successfully added!");
		out.println("</body>");
		out.println("</html>");
	}
}
