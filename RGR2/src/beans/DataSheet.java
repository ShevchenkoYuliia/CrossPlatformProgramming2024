package beans;

import java.util.ArrayList;

public class DataSheet {
    private String name;
    private ArrayList<Data> dataSheet = null;
    public DataSheet(){
        super();
        name = "name";
        dataSheet = new ArrayList<Data>();

    }
    public int size(){
        return dataSheet.size();
    }

    public Data getDataItem(int num){
        return dataSheet.get(num);
    }
    public void changeDataItem(Data data, int num){
        dataSheet.set(num, data);
    }
    public void addDataItem(Data data){
        dataSheet.add(data);
    }
    public void removeDataItem(int num){
        dataSheet.remove(num);
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String toString() {
        return "DataSheet{" +
                "name='" + name + '\'' + "\n"+
                ", dataSheet=" + dataSheet +
                '}'+ '\n';
    }
}