package atm.dao;

import atm.model.Operations;
import java.util.ArrayList;

public interface OperationsDAO {

    void saveBalanceOperation(ArrayList<Operations> operationsList);

    void saveWithdrawalOperation(ArrayList<Operations> operationsList);

    ArrayList<Operations> getOperations(long cardNumber, String time);


}
