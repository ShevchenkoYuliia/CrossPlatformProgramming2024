package tcpWork;

public class GetCardInfoOperation extends CardOperation{

    public String serNum = null;
    public GetCardInfoOperation(String serNum){
        this.serNum = serNum;
    }
    public GetCardInfoOperation(){

    }
    public String getSerNum(){
        return serNum;
    }
    public void setSerNum(String serNum){
        this.serNum = serNum;
    }
}
