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

    private int numbersInRow(int row) {
        return (int) Arrays.stream(numbers[row])
                .filter(number -> number != 0)
                .count();
    }

    private void placeNumbers(Map<Integer, List<Integer>> columnToNumbers) {
        for (int column = 0; column < COLUMNS; column++) {
            Iterator<Integer> valuesIterator = columnToNumbers.get(column).iterator();

            List<Integer> integerList = IntStream.range(0, ROWS).boxed()
                    .sorted(rowNumbersComparator())
                    .collect(Collectors.toList());

            Iterator<Integer> rowIndexIterator = integerList.iterator();

            while (valuesIterator.hasNext()) {
                numbers[rowIndexIterator.next()][column] = valuesIterator.next();
            }
        }
    }

    private Comparator<Integer> rowNumbersComparator() {
        return Comparator.comparing(this::numbersInRow).thenComparing(i -> new Random().nextInt());
    }
}
