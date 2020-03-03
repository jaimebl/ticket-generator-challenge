import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BingoNinetyStripGenerator {

    private static final Integer TICKETS_PER_STRIP = 6;

    public static void main(String[] args) {
        List<BingoTicket> bingoTickets = new BingoNinetyStripGenerator().generate();
        printBingoCards(bingoTickets);
    }

    private static void printBingoCards(List<BingoTicket> bingoTickets) {
        for (BingoTicket bingoTicket : bingoTickets) {
            int[][] bingoCardNumbers = bingoTicket.getNumbers();

            for (int[] row : bingoCardNumbers) {
                for (int number : row) {
                    System.out.format("%4s", number != 0 ? number : "-");
                }
                System.out.format("%n");
            }
            System.out.println(" -----------------------------------");
        }
    }

    private Map<Integer, List<Integer>> generateRandomNumbersPool() {
        return IntStream.rangeClosed(1, 90)
                .boxed()
                .collect(Collectors.groupingBy(this::numberToColumnIndex, Collectors.toList()));
    }

    private Integer numberToColumnIndex(Integer number) {
        return (number - 1) / 10;
    }

    private Integer getFirstNumberFromPool(Map<Integer, List<Integer>> numbersPool, Integer column) {
        return numbersPool.get(column).remove(0);
    }

    private List<Integer> integerRandomList(int startRange, int endRange) {
        List<Integer> integerRandomList = IntStream.range(startRange, endRange).boxed().collect(Collectors.toList());
        Collections.shuffle(integerRandomList);

        return integerRandomList;
    }


    private Map<Integer, List<Integer>> buildTicketValuesMap() {
        return IntStream.range(0, BingoTicket.COLUMNS)
                .boxed()
                .collect(Collectors.toMap(Function.identity(), ArrayList::new));
    }


    public List<BingoTicket> generate() {

        Map<Integer, List<Integer>> numbersPool = generateRandomNumbersPool();

        List<Map<Integer, List<Integer>>> ticketNumbersList = IntStream.range(0, TICKETS_PER_STRIP)
                .mapToObj(i -> buildTicketValuesMap())
                .collect(Collectors.toList());

        //add nine initial numbers per ticket
        for (Integer integer : integerRandomList(0, TICKETS_PER_STRIP)) {
            Map<Integer, List<Integer>> ticketNumbers = ticketNumbersList.get(integer);
            for (int column = 0; column < BingoTicket.COLUMNS; column++) {
                ticketNumbers.get(column).add(getFirstNumberFromPool(numbersPool, column));
            }
        }

        //add rest of the numbers
        for (int i = 0; i < 4; i++) {
            for (int column = 0; column < BingoTicket.COLUMNS; column++) {

                for (Integer integer : integerRandomList(0, TICKETS_PER_STRIP)) {
                    Map<Integer, List<Integer>> ticketNumbers = ticketNumbersList.get(integer);
                    long totalNumbers = ticketNumbers.values().stream().mapToLong(Collection::size).sum();

                    if (totalNumbers < BingoTicket.MAX_NUMBERS && ticketNumbers.get(column).size() < 3) {
                        ticketNumbers.get(column).add(getFirstNumberFromPool(numbersPool, column));
                        break;
                    }
                }
            }
        }

        return ticketNumbersList.stream()
                .map(BingoTicket::new)
                .collect(Collectors.toList());
    }
}
