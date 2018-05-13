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

        String operationType = "withdrawn";

        ArrayList<Operations> operationsList = new ArrayList<>();

        CardImpl cardImpl = new CardImpl();
        OperationsImpl operationsImpl = new OperationsImpl();
        OperationsImpl operationsImpl1 = new OperationsImpl();

        String dateTime = Date.getDate();
        long cardId = (long) request.getSession().getAttribute("cardNumber");
        double amountWithdrawn = Double.parseDouble(request.getParameter("keyboardValues"));
        double balance = cardImpl.getBalance(cardId);


        if (amountWithdrawn > balance) {
            request.setAttribute("amountExceed","Entered amount exceed your balance");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        } else {
            double balanceUpdate = balance - amountWithdrawn;

            //writing balance changes
            cardImpl.balanceUpdate(cardId, balanceUpdate);

            operationsList.add(new Operations(cardId, operationType, dateTime, balanceUpdate, amountWithdrawn));

            //writing operation to database
            operationsImpl.saveWithdrawalOperation(operationsList);

            //null the list before second use
            operationsList=null;

            operationsList = operationsImpl1.getOperations(cardId, dateTime);


            request.setAttribute("operationsList", operationsList);

            request.getRequestDispatcher("/jsp/operationreports.jsp").forward(request, response);
        }
    }
}
