package task4;

public class BabyInfo {
    private String name;
    private String gender;
    private int count;
    private int rating;
    public BabyInfo( String name, String gender, int count, int rating){
        this.name = name;
        this.gender = gender;
        this.count = count;
        this.rating = rating;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }
    public String getName() {
        return name;
    }
    public String getGender() {
        return gender;
    }
    public int getCount() {
        return count;
    }
    public int getRating() {
        return rating;
    }
    public String toString() {
        return "BabyInfo: \n" +
                "   name = " + name + "\n" +
                "   gender = " + gender + "\n" +
                "   count = " + count + "\n"+
                "   rating = " + rating+ "\n";
    }
}