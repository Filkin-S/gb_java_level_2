package lesson2;

public class HomeWorkApp {
    public static void main(String[] args) {

        String[][] testArray = new String[4][4];
        testArray[0] = new String[]{"1", "2", "3", "4"};
        testArray[1] = new String[]{"1", "2", "3", "4"};
        testArray[2] = new String[]{"1", "2", "3", "4"};
        testArray[3] = new String[]{"1", "2", "3", "4"};

        try {
            System.out.println(myArraySummator(testArray));
        } catch (MyArraySizeException | MyArrayDataException e) {
            e.printStackTrace();
        }

    }

    static boolean isTwoDimArraySizeIsValid (String[][] array, int firstDimSize, int secondDimSize){
        if (array.length != firstDimSize) {
            return false;
        }
        for (String[] element : array) {
            if (element.length != secondDimSize) return false;
        }
        return true;
    }

    static int myArraySummator(String[][] array) throws MyArraySizeException, MyArrayDataException {
        if (!isTwoDimArraySizeIsValid(array, 4, 4)) {
            throw new MyArraySizeException("Массив неверного размера");
        }

        int result = 0;
        for (int firstDimIndex = 0; firstDimIndex < array.length; firstDimIndex++) {
            for (int secondDimIndex = 0; secondDimIndex < array[firstDimIndex].length; secondDimIndex++) {
                try {
                    result += Integer.parseInt(array[firstDimIndex][secondDimIndex]);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    throw new MyArrayDataException(String.format("В ячейке [%s][%s] данные неверного формата!",
                            firstDimIndex, secondDimIndex));
                    }
            }
        }
        return result;
    }
}
