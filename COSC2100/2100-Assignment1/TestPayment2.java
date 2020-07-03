/**
 * Created by mail929 on 9/5/16.
 */
public class TestPayment2
{
    public static void main(String[] args)
    {
        // Create several test classes and invoke the paymentDetails method
        CashPayment cash1 = new CashPayment(12.5);
        CashPayment cash2 = new CashPayment(63);
        CreditCardPayment credit1 = new CreditCardPayment(32.5, "George",
                "12/25/2013", "123456789");
        CreditCardPayment credit2 = new CreditCardPayment(100, "Ryan",
                "8/15/2029", "987654321");
        Payment payment = new Payment(33.33);

        credit2.setPaymentAmount(75);

        System.out.println("Cash 1 details:");
        cash1.paymentDetails();
        System.out.println();
        System.out.println("Cash 2 details:");
        cash2.paymentDetails();
        System.out.println();

        System.out.println("Credit 1 details:");
        credit1.paymentDetails();
        System.out.println();
        System.out.println("Credit 2 details:");
        credit2.paymentDetails();
        System.out.println();

        System.out.println("Payment details:");
        payment.paymentDetails();
        System.out.println();

        if (cash2.equals(cash1))
            System.out.println("cash2 and cash1 are equal");

        if (cash2.equals(credit1))
            System.out.println("cash2 and credit1 are equal");

        if (credit1.equals(credit2))
            System.out.println("credit2 and credit1 are equal");

    }

} // end of TestPayment