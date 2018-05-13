package atm.servlets;

import atm.daoimpl.CardImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/PinEntry")
public class PinEntry extends HttpServlet {
    //trade non-safe variable for pin enter counting
    private int attempt = 0;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        CardImpl cardImpl = new CardImpl();

        long cardNumber = (long) request.getSession().getAttribute("cardNumber");

        int pincode = Integer.parseInt(request.getParameter("keyboardValues"));

        boolean result = cardImpl.isPinMatch(cardNumber, pincode);
        if (result) {

            request.getRequestDispatcher("/jsp/operations.jsp").forward(request, response);
        }

        attempt++;

        if (attempt == 4) {
            cardImpl.blockTheCard(cardNumber);
            request.setAttribute("cardIsBlocked", "Your card is blocked now!");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        request.setAttribute("pinError", "Wrong pin code!");
        request.getRequestDispatcher("/error.jsp").forward(request, response);
    }
}
