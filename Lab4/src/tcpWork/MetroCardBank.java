package tcpWork;

import java.util.ArrayList;

public class MetroCardBank {
    private ArrayList<MetroCard> store;
    public MetroCardBank(){
        store = new ArrayList<MetroCard>();
    }
    public ArrayList<MetroCard> getStore() {
        return store;
    }
    public void setStore(ArrayList<MetroCard> store) {
        this.store = store;
    }
    public int findMetroCard(String serNum){
        for (MetroCard card : store) {
            if (serNum.equals(card.getSerNum())) {
                return store.indexOf(card);
            }
        }
        return -1;
    }
    public int numCards(){
        return store.size();
    }
    public void addCard(MetroCard newCard){
        store.add(newCard);
    }
    public boolean removeCard(String serNum){
        for (MetroCard card : store) {
            if (serNum.equals(card.getSerNum())) {
                store.remove(serNum);
                return true;
            }
        }
        return false;
    }
    public boolean addMoney(String serNum, double money){
            for (MetroCard card : store) {
                if (serNum.equals(card.getSerNum())) {
                    card.setBalance(card.getBalance() + money);
                    return true;
                }
            }
        return false;
    }
    public boolean getMoney(String serNum, double money){
        for (MetroCard card : store) {
            if (serNum.equals(card.getSerNum())) {
                card.setBalance(card.getBalance() - money);
                return true;
            }
        }
        return false;
    }
    public double getBalance(String serNum){
        for (MetroCard card : store) {
            if (serNum.equals(card.getSerNum())) {
            return card.getBalance();
            }
        }
        return -1;
    }
    public String toString() {
        StringBuilder buf = new StringBuilder("List of MetroCards:");
        for (MetroCard c : store) {
            buf.append("\n\n" + c);
        }
        return buf.toString();
    }
}
