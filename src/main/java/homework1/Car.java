package homework1;

import java.util.Comparator;

public class Car implements Comparable<Car> {
    private String mark;
    private double engine_volume;
    private int age;

    public Car(String mark, double engine_volume, int age) {
        this.mark = mark;
        this.engine_volume = engine_volume;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public double getEngine_volume() {
        return engine_volume;
    }

    public void setEngine_volume(double engine_volume) {
        this.engine_volume = engine_volume;
    }

    @Override
    public String toString() {
        return "Марка: " + this.getMark() + ", " + "объем двигателя: " + this.getEngine_volume() + ", " + "Год выпуска: " + this.getAge();
    }

    @Override
    public int compareTo(Car car) {
        return Double.compare(this.engine_volume, car.engine_volume);
    }

    public static class AgeComparator implements Comparator<Car> {
        @Override
        public int compare(Car car1, Car car2) {
            return car1.getAge() - car2.getAge();
        }
    }
}