package beans;

public class Data {
    private String date;
    private double x, y;
    public Data(double x, double y){
        super();
        this.x = x;
        this.y = y;
        this.date = "";
    }
    public Data(){
        this(0.0,0.0);
    }
    public String getDate(){
        return date;
    }
    public void setDate(String date){
        this.date = date;
    }
    public double getX(){
        return x;
    }
    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
    public String toString() {
        return "\n" + "Data{" +
                "date='" + date + '\'' +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
