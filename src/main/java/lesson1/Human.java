package lesson1;

public class Human implements Runable, Jumpable {
    private String name;
    private int runDistance;
    private int jumpHeight;


    public Human() {
        this.name = "Вася";
        this.runDistance = 10;
        this.jumpHeight = 1;
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
