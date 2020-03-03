import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BingoTicket {

    public static final Integer ROWS = 3;
    public static final Integer COLUMNS = 9;
    public static final Integer MAX_NUMBERS = 15;

    private int[][] numbers = new int[ROWS][COLUMNS];

    public BingoTicket(Map<Integer, List<Integer>> columnToNumbers) {
        placeNumbers(columnToNumbers);
    }

    public int[][] getNumbers() {
        return numbers;
    }

    private void addNumber(int row, int column, int number) {
        numbers[row][column] = number;
    }

    private int numbersInRow(int row) {
        return (int) Arrays.stream(numbers[row])
                .filter(number -> number != 0)
                .count();
    }

    private void placeNumbers(Map<Integer, List<Integer>> columnToNumbers) {
        for (int column = 0; column < COLUMNS; column++) {
            Iterator<Integer> valuesIterator = columnToNumbers.get(column).iterator();

            List<Integer> integerList = IntStream.range(0, ROWS).boxed()
                    .sorted(this::randomComparator)
                    .sorted(this::rowNumbersComparator)
                    .collect(Collectors.toList());

            Iterator<Integer> rowIndexIterator = integerList.iterator();

            while (valuesIterator.hasNext()) {
                addNumber(rowIndexIterator.next(), column, valuesIterator.next());
            }
        }
    }

    private int rowNumbersComparator(Integer rowOne, Integer rowTwo) {
        return Comparator.comparing(this::numbersInRow).compare(rowOne, rowTwo);
    }

    private int randomComparator(Integer rowOne, Integer rowTwo) {
        return Comparator.comparing(i -> new Random().nextInt()).compare(rowOne, rowTwo);
    }
}
