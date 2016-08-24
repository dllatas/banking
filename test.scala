import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNotSame
import org.junit.Test

class BankingTest {
    /*Tests for Balance*/
    @Test
    def evalBalanceResult() {
        val format = new java.text.SimpleDateFormat("yyyy-MM-dd")
        val account = new BankAccount(10201, "Jonas")
        val gymJune = new Payment(format.parse("2016-06-28"), "Gym", "Payment", -50.00, "123-456")
        val gymJuly = new Payment(format.parse("2016-07-28"), "Gym", "Payment", -50.00, "123-456")
        var bankAccountDetail = new BankAccountDetail(account, gymJune)
        bankAccountDetail.addEvent(gymJuly)
        assertEquals(-100.00, bankAccountDetail.getBalance(), 0.01)
    }

    @Test
    def evalBalanceInput() {
        val format = new java.text.SimpleDateFormat("yyyy-MM-dd")
        val account = new BankAccount(10201, "Jonas")
        val gymJune = new Payment(format.parse("2016-06-28"), "Gym", "Payment", -50.00, "123-456")
        var bankAccountDetail = new BankAccountDetail(account, gymJune)
        assertNotNull("Should not be null", bankAccountDetail);
    }

    /*Tests for removing all bank account transactions*/
    @Test
    def evalRemoveOutput() {
        val format = new java.text.SimpleDateFormat("yyyy-MM-dd")
        /* Bank Account */
        val account1 = new BankAccount(10201, "Jonas")
        val account2 = new BankAccount(10202, "Jens")
        /* Transaction */
        val gym1 = new Payment(format.parse("2016-06-28"), "Gym", "Payment", -50.00, "123-456")
        val gym12 = new Payment(format.parse("2016-07-28"), "Gym", "Payment", -50.00, "123-456")
        val video1 = new BankEvent(format.parse("2016-07-23"), "Video streaming", "Transaction", -99.00 )
        val gym2 = new Payment(format.parse("2016-06-28"), "Gym", "Payment", -50.00, "123-456")
        val video2 = new BankEvent(format.parse("2016-07-23"), "Video streaming", "Transaction", -99.00 )
        /* Add to account details */
        var detail1 = new BankAccountDetail(account1, gym1)
        detail1.addEvent(gym12)
        detail1.addEvent(video1)
        var detail2 = new BankAccountDetail(account2, gym2)
        detail2.addEvent(video2)
        /* Add to list of bank account detail*/
        var detail: List[BankAccountDetail] = List(detail1)
        detail = detail2 :: detail
        var difDetail = detail
        /* Remove Transaction*/
        detail.map(_.removeEventType("Transaction"))
        val result = detail.filter(_.sizeDetail() > 1)
        assertNotSame("should not be same ", result, difDetail);
    }

    @Test
    def evalRemoveInput() {
        val format = new java.text.SimpleDateFormat("yyyy-MM-dd")
        /* Bank Account */
        val account1 = new BankAccount(10201, "Jonas")
        val account2 = new BankAccount(10202, "Jens")
        /* Transaction */
        val gym1 = new Payment(format.parse("2016-06-28"), "Gym", "Payment", -50.00, "123-456")
        val gym12 = new Payment(format.parse("2016-07-28"), "Gym", "Payment", -50.00, "123-456")
        val video1 = new BankEvent(format.parse("2016-07-23"), "Video streaming", "Transaction", -99.00 )
        val gym2 = new Payment(format.parse("2016-06-28"), "Gym", "Payment", -50.00, "123-456")
        val video2 = new BankEvent(format.parse("2016-07-23"), "Video streaming", "Transaction", -99.00 )
        /* Add to account details */
        var detail1 = new BankAccountDetail(account1, gym1)
        detail1.addEvent(gym12)
        detail1.addEvent(video1)
        var detail2 = new BankAccountDetail(account2, gym2)
        detail2.addEvent(video2)
        /* Add to list of bank account detail*/
        var detail: List[BankAccountDetail] = List(detail1)
        detail = detail2 :: detail
        assertNotNull("Should not be null", detail);
    }
}