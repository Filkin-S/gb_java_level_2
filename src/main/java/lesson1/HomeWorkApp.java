package lesson1;

public class HomeWorkApp {
    public static void main(String[] args) {

        Object competitorArray[] = new Object[3];

        competitorArray[0] = new Human();
        competitorArray[1] = new Robot();
        competitorArray[2] = new Cat();

        Object barrierArray[] = new Object[4];

        barrierArray[0] = new Track(10);
        barrierArray[1] = new Wall(1);
        barrierArray[2] = new Track(15);
        barrierArray[3] = new Wall(2);

        for (Object object : competitorArray) {
            for (Object item : barrierArray) {
                if (object instanceof Human) {
                    Human competitor = (Human) object;
                    if (item instanceof Wall) {
                        Wall barrier = (Wall) item;
                        if (!competitor.jump(barrier.getHeight())) {
                            System.out.println(competitor.getName() + " выбыл");
                            break;
                        }
                    }
                    if (item instanceof Track) {
                        Track barrier = (Track) item;
                        if (!competitor.run(barrier.getDistance())) {
                            System.out.println(competitor.getName() + " выбыл");
                            break;
                        }
                    }
                }

                if (object instanceof Robot) {
                    Robot competitor = (Robot) object;
                    if (item instanceof Wall) {
                        Wall barrier = (Wall) item;
                        if (!competitor.jump(barrier.getHeight())) {
                            System.out.println(competitor.getName() + " выбыл");
                            break;
                        }
                    }
                    if (item instanceof Track) {
                        Track barrier = (Track) item;
                        if (!competitor.run(barrier.getDistance())) {
                            System.out.println(competitor.getName() + " выбыл");
                            break;
                        }
                    }
                }

                if (object instanceof Cat) {
                    Cat competitor = (Cat) object;
                    if (item instanceof Wall) {
                        Wall barrier = (Wall) item;
                        if (!competitor.jump(barrier.getHeight())) {
                            System.out.println(competitor.getName() + " выбыл");
                            break;
                        }
                    }
                    if (item instanceof Track) {
                        Track barrier = (Track) item;
                        if (!competitor.run(barrier.getDistance())) {
                            System.out.println(competitor.getName() + " выбыл");
                            break;
                        }
                    }
                }
            }
        }
    }
}