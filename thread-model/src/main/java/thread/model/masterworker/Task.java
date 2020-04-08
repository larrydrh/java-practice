package thread.model.masterworker;


public class Task {
    private int id;
    private int price ;

    public Task(int id, int price) {
        super();
        this.id = id;
        this.price = price;
    }

    public Task(){}

    public int getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    //set ,get
}