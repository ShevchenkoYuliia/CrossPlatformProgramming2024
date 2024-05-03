package tcpWork;

import java.io.Serializable;

public class MetroCard implements Serializable {
    private String serNum;
    private User usr;
    private String college;
    private double balance;
    public MetroCard(){
        serNum = "00001";
        usr = new User();
        college = "University";
        balance = 0.00;
    }

    public MetroCard(String serNum, User usr, String college, double balance){
        this.serNum = serNum;
        this.usr = usr;
        this.college = college;
        this.balance = balance;
    }
    public String getSerNum(){
        return serNum;
    }
    public User getUsr(){
        return usr;
    }
    public String getCollege(){
        return college;
    }
    public double getBalance(){
        return balance;
    }
    public void setSerNum(String serNum){
        this.serNum = serNum;
    }
    public void setUsr(User usr){
        this.usr = usr;
    }
    public void setCollege(String college){
        this.college = college;
    }
    public void setBalance(double balance){
        this.balance = balance;
    }
    public String toString() {
        return "№: " + serNum + "\nUser: " + usr +
                "\nColledge: " + college +
                "\nBalance: " + balance;
    }
}
