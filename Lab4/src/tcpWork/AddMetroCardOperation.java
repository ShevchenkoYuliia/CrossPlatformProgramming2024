package tcpWork;
public class AddMetroCardOperation extends CardOperation{
    private MetroCard crd = null;
    public AddMetroCardOperation() {
        crd = new MetroCard();
    }
    public MetroCard getCrd() {
        return crd;
    }
    public AddMetroCardOperation(String serNum, User user, String college) {
        crd = new MetroCard();
        crd.setSerNum(serNum);
        crd.setUsr(user);
        crd.setCollege(college);
        crd.setBalance(0);
    }
}