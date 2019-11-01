import exceptions._

class Account(val bank: Bank, initialBalance: Double) {

    class Balance(var amount: Double) {}

    val balance = new Balance(initialBalance) 
    def withdraw(amount: Double): Either[Double,String] =
      this.synchronized{
        if(amount < 0 || amount > this.getBalanceAmount){
          Right("Something went wrong")
        }
        else{
          this.balance.amount -= amount
          Left(this.getBalanceAmount)
        }
      }
    def deposit (amount: Double): Either[Double, String] =
      this.synchronized{
        if (amount < 0){
         Right("Something went wrong")
        }
        else{
          this.balance.amount += amount
          Left(this.getBalanceAmount)
        }
      }

    def getBalanceAmount: Double = this.balance.amount

    def transferTo(account: Account, amount: Double) = {
        bank addTransactionToQueue (this, account, amount)
    }
}
