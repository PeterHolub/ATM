package atm.servlets;

import atm.daoimpl.*;
import atm.model.Operations;
import atm.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;


@WebServlet("/Balance")
public class Balance extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String operationType = "balance";
        ArrayList<Operations> operationsList = new ArrayList<>();

        OperationsImpl operationsImpl = new OperationsImpl();

        CardImpl cardImpl = new CardImpl();

        String dateTime = Date.getDate();

        long cardid = (long) request.getSession().getAttribute("cardNumber");

        //getting balance from database
        double balance = cardImpl.getBalance(cardid);

        operationsList.add(new Operations(cardid, operationType, dateTime, balance));

        //writing operation to operation table
        operationsImpl.saveBalanceOperation(operationsList);

        //set data for
        request.setAttribute("amount", balance);

        request.setAttribute("dateTime", dateTime);

        request.getRequestDispatcher("/jsp/balance.jsp").forward(request, response);

    }
}

