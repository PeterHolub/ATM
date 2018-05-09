package atm.servlets;

import atm.daoimpl.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import atm.model.Operations;
import atm.util.Date;

@WebServlet("/Withdrawals")
public class Withdrawals extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ArrayList<Operations> operationsList = new ArrayList<>();

        OperationsImpl operationsImpl = new OperationsImpl();
        long cardNumber = (long) request.getSession().getAttribute("cardNumber");

        CardImpl cardImpl = new CardImpl();

        double amount = Double.parseDouble(request.getParameter("keybordvalues"));

        double balance = cardImpl.getBalance(cardNumber);

        if (amount > balance) {
            request.getRequestDispatcher("/jsp/error/amountexceed.jsp").forward(request, response);
        } else {
//
            double balanceUpdate = balance - amount;

            cardImpl.withdrawal(cardNumber, balanceUpdate);

            String dateTime = Date.getDate();


            //writing operation to database


            request.setAttribute("dateTime", dateTime);

            request.setAttribute("amountWithdrawn", amount);

            request.setAttribute("balance", balanceUpdate);

            request.getRequestDispatcher("/jsp/operationreports.jsp").forward(request, response);
        }
    }
}
