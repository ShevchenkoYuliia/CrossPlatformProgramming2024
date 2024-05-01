package tcpWork;

public class ShowBalanceOperation extends CardOperation{
    private String serNum = null;
    public double balance = 0.0;
    public ShowBalanceOperation(String serNum){
        this.serNum = serNum;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setSerNum(String serNum) {
        this.serNum = serNum;
    }

    public String getSerNum() {
        return serNum;
    }

    public double getBalance() {
        return balance;
    }
    public String toString() {
        return "serNum='" + serNum + '\'' +
                ", balance=" + balance;
    }
}
