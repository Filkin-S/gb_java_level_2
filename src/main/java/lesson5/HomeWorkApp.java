package lesson5;

import java.util.Arrays;

public class HomeWorkApp {
    public static void main(String[] args) throws InterruptedException {
        firstMethod();
        secondMethod();
    }


    public static void firstMethod() {
        final int SIZE = 10_000_000;
        float[] arr = new float[SIZE];
        Arrays.fill(arr, 1.0f);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println("fitstMethod time: " + (System.currentTimeMillis() - startTime) + " ms.");
    }

    public static void secondMethod() throws InterruptedException {
        int SIZE = 10_000_000;
        int HALF = SIZE / 2;
        float[] arr = new float[SIZE];
        Arrays.fill(arr, 1.0f);

        long startTime = System.currentTimeMillis();

        float[] leftHalf = new float[HALF];
        float[] rightHalf = new float[HALF];
        System.arraycopy(arr, 0, leftHalf, 0, HALF);
        System.arraycopy(arr, HALF, rightHalf, 0, HALF);

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < leftHalf.length; i++) {
                leftHalf[i] = (float) (leftHalf[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < rightHalf.length; i++) {
                rightHalf[i] = (float) (rightHalf[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        float[] mergedArr = new float[SIZE];
        System.arraycopy(leftHalf, 0, mergedArr, 0, HALF);
        System.arraycopy(rightHalf, 0, mergedArr, HALF, HALF);

        System.out.println("secondMethod time: " + (System.currentTimeMillis() - startTime) + " ms.");
    }
}

