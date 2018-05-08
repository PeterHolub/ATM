package atm.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Home")
public class Home extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Getting values from home page and replace all"-" symbols
        String keybordValues = request.getParameter("keybordvalues").replaceAll("[-]", "");


        request.setAttribute("test", keybordValues);
        request.getRequestDispatcher("/test.jsp").forward(request, response);
    }
}
