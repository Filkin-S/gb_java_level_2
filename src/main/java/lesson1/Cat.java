package lesson1;

public class Cat implements Runable, Jumpable {
    private String name;
    private int runDistance;
    private int jumpHeight;


    public Cat() {
        this.name = "Барсик";
        this.runDistance = 20;
        this.jumpHeight = 2;
    }

    public boolean jump(int height) {
        System.out.println(this.name + " прыгает");
        if(this.jumpHeight < height) {
            System.out.println(this.name + " не смог перепрыгнуть препятствие");
            return false;
        }
        System.out.println(this.name + " перепрыгнул препятствие");
        return true;

    }

    public boolean run(int distance) {
        System.out.println(this.name + " бежит");
        if(this.runDistance < distance) {
            System.out.println(this.name + " не смог пробежать препятствие");
            return false;
        }
        System.out.println(this.name + " пробежал препятствие");
        return true;
    }

    public String getName() {
        return name;
    }
}
