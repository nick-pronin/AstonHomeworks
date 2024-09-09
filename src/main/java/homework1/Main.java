package homework1;

public class Main {
    public static void main(String[] args) {
        ArrayList<Car> list = new ArrayList<>();

        Car car = new Car("BMW", 2.5, 2021);
        Car car2 = new Car("Mazda", 1.8, 2022);
        Car car3 = new Car("Ford", 5.0, 2021);
        Car car4 = new Car("Mercedes", 3.0, 2021);

        list.add(car);
        list.add(car2);
        list.add(car3);
        list.add(car4);

        list.display();
        list.quickSort();
        list.display();
    }
}