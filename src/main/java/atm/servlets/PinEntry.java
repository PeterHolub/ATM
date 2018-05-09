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
        int pincode = Integer.parseInt(request.getParameter("keybordvalues"));

        boolean result = cardImpl.isPinMatch(cardNumber, pincode);
        if (result) {

            request.getRequestDispatcher("/jsp/operations.jsp").forward(request, response);
        }

        attempt++;

        if (attempt == 4) {
            cardImpl.blockTheCard(cardNumber);
            request.getRequestDispatcher("/jsp/error/cardisblocked.jsp").forward(request, response);
        }

        request.getRequestDispatcher("/jsp/error/wrongpin.jsp").forward(request, response);
    }
}
