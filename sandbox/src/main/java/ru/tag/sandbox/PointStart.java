package ru.tag.sandbox;
public class PointStart {

  public static void main(String[] args) {
    Point p1 = new Point(1, 2);
    Point p2 = new Point(2, 2);

    System.out.println("p1 = " + p1.x + ", " + p1.y);
    System.out.println("p2 = " + p2.x + ", " + p2.y);
    System.out.println("Расстояние между двумя точками = " + p1.distance(p2));

  }
}








