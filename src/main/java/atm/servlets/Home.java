package atm.servlets;

import atm.daoimpl.CardImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/Home")
public class Home extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        CardImpl cardImpl = new CardImpl();
        //Getting values from home page and replace all"-" symbols
        long cardNumber = Long.parseLong(request.getParameter("keybordvalues").replaceAll("[-]", ""));


        //checking if card is blocked
        boolean cardExist = cardImpl.cardExist(cardNumber);
        boolean cardStatus = cardImpl.cardStatus(cardNumber);
        //if false redirect to error page
        if (!cardExist) {

            request.getRequestDispatcher("/jsp/error/cardnumber.jsp").forward(request, response);
        }
        if (!cardStatus) {

            request.getRequestDispatcher("/jsp/error/blocked.jsp").forward(request, response);
        }

        //writing card number to Session to restore it in other servlets due the session
        request.getSession().setAttribute("cardNumber", cardNumber);

        request.getRequestDispatcher("/jsp/pinentry.jsp").forward(request, response);

    }
}
