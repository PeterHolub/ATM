package atm.dao;

public interface CardDAO {

    boolean cardExist(long cardNumber);

    boolean cardStatus(long cardNumber);

    boolean isPinMatch(long cardNumber, int pincode);

    void blockTheCard(long cardNumber);

    double getBalance(long cardNumber);

    void withdrawal(long cardNumber, double balanceUpdate);
}
