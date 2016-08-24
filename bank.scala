import java.util.Date
import java.util.concurrent.TimeUnit

class BankAccount(numberc: Int, ownerc: String) {
    var number: Int = numberc
    var owner: String = ownerc

    def get_number() : Int = {
        return number
    }

    def get_owner() : String = {
        return owner
    }

    def print() {
        println ("Owner: " + owner + ". Account number: " + number);
    }
}

class BankEvent(dateCreatedc: Date, textc: String, categoryc: String, amountc: Double) {
    var dateCreated: Date = dateCreatedc
    var text: String = textc
    var amount: Double = amountc
    var category: String = categoryc
    def print() {
        println ("Date: " + dateCreated + ". Description: " + text + ". Amount: " + amount + ". Type: " + category);
    }
}

class Payment(val dateCreatedc: Date, val textc: String, val categoryc: String, val amountc: Double,
    recipientc: String) extends BankEvent(dateCreatedc, textc, categoryc, amountc) {
}

class BankAccountDetail(accountc: BankAccount, eventc: BankEvent) {
    var detail: List[BankEvent] = List(eventc)
    var account: BankAccount = accountc

    def sizeDetail() : Int = {
        return detail.size
    }

    def printDetail() {
        account.print()
        detail.map(_.print())
    }

    def removeEventType(eventType: String) = {
        detail = detail.filter(_.category != eventType )
    }

    def addEvent(e: BankEvent) {
        detail = e :: detail
    }

    def getBalance() : Double = {
        val balanceAmount = detail.map(_.amount)
        return balanceAmount.reduceLeft(_ + _)
    }

    def timeInterval(c: String) {
        var i: Int = 0
        var acum: Long = 0
        var result: Long = 0
        val category = detail.filter(_.category == c)
        val second = category.map(_.dateCreated.getTime())
        val secondSorted = second.sorted
        for (i <- 0 to secondSorted.size-2) {
            acum = acum + (secondSorted(i+1) - secondSorted(i))
        }
        result = TimeUnit.MILLISECONDS.toDays(acum / (secondSorted.size - 1))
        println("The average time interval for the event " + c + " is " + result + " days")
    }
}

class CreditCard(numberc: Int, namec: String) {
    var number: Int = numberc
    var name: String = namec
}

class CreditCardDetail(creditcardc: CreditCard, eventc: BankEvent) {
    var detail: List[BankEvent] = List(eventc)
    var creditCard: CreditCard = creditcardc

    def addEvent(e: BankEvent) {
        detail = e :: detail
    }

    def getBalance() : Double = {
        val balanceAmount = detail.map(_.amount)
        return balanceAmount.reduceLeft(_ + _)
    }

    def timeInterval(c: String) {
        var category = detail.filter(_.category == c)
        println(category)
    }
}

/* Bank Account Balance */
object Balance {
    def main(args: Array[String]) {
        val format = new java.text.SimpleDateFormat("yyyy-MM-dd")
        /* Bank Account */
        val account = new BankAccount(10201, "Jonas")
        /* Events */
        val gymJune = new Payment(format.parse("2016-06-28"), "Gym", "Payment", -50.00, "123-456")
        val streaming = new BankEvent(format.parse("2016-07-23"), "Video streaming", "Transaction", -99.00 )
        val gymAugust = new Payment(format.parse("2016-08-01"), "Gym", "Payment", -200.00, "123-456")
        /* Add to account details */
        val detail = new BankAccountDetail(account, gymJune)
        detail.addEvent(streaming)
        detail.addEvent(gymAugust)
        /* Print balance */
        println("The balance of the account " + account.get_number() + " is $ "+ detail.getBalance())
    }
}

/*Time interval between same type events*/
object TimeInterval {
    def main(args: Array[String]) {
        val format = new java.text.SimpleDateFormat("yyyy-MM-dd")
        /* Bank Account */
        val account = new BankAccount(10201, "Jonas")
        /* Events */
        val gymJune = new Payment(format.parse("2016-06-28"), "Gym", "Payment", -50.00, "123-456")
        val gymJuly = new Payment(format.parse("2016-07-18"), "Gym", "Payment", -200.00, "123-456")
        val gymAugust = new Payment(format.parse("2016-08-01"), "Gym", "Payment", -200.00, "123-456")
        /* Add to account details */
        val detail = new BankAccountDetail(account, gymJune)
        detail.addEvent(gymJuly)
        detail.addEvent(gymAugust)
        /* Print time interval for an event */
        detail.timeInterval("Payment")
    }
}

/*Remove events of type Transaction*/
object RemoveTransaction {
    def main(args: Array[String]) {
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
        /* Remove Transaction*/
        removeEvent(detail, "Transaction")
    }

    def removeEvent(detail: List[BankAccountDetail], event: String) {
        detail.map(_.removeEventType(event))
        val result = detail.filter(_.sizeDetail() > 1)
        if (result.size > 0) {
            printAccountDetail(result)
        }
        else {
            println("No accounts to display.")
        }
    }

    def printAccountDetail(detail: List[BankAccountDetail]) {
        detail.map(_.printDetail())
    }
}