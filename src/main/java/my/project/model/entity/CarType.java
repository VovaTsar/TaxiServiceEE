package my.project.model.entity;

public enum CarType {
    Eco(1, 100),
    Standard(2, 200),
    Comfort(3, 300),
    Premium(4, 400);

    int speed;
    int price;

    CarType(int speed, int price) {
        this.speed = speed;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public int getSpeed() {
        return speed;
    }
}
