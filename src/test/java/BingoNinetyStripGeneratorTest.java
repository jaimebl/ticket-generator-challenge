import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BingoNinetyStripGeneratorTest {

    private BingoNinetyStripGenerator test = new BingoNinetyStripGenerator();

    @Test
    public void bingoStripContainsSixTickets() {
        List<BingoTicket> bingoTickets = test.generate();

        assertEquals(6, bingoTickets.size());
    }

    @Test
    public void bingoTicketRowsContainsFiveNumbers() {
        List<BingoTicket> bingoTickets = test.generate();

        for (BingoTicket bingoTicket : bingoTickets) {
            for (int[] row : bingoTicket.getNumbers()) {
                List<Integer> rowNumbers = Arrays.stream(row).boxed().filter(i -> i != 0)
                        .collect(Collectors.toList());
                assertEquals(5, rowNumbers.size());
            }
        }
    }

    @Test
    public void bingoTicketColumnsContainsAtLeastOneNumber() {
        List<BingoTicket> bingoTickets = test.generate();

        for (BingoTicket bingoTicket : bingoTickets) {
            for (int i = 0; i < BingoTicket.COLUMNS; i++) {
                int row0 = bingoTicket.getNumbers()[0][i];
                int row1 = bingoTicket.getNumbers()[1][i];
                int row2 = bingoTicket.getNumbers()[2][i];

                assertTrue(row0 != 0 || row1 != 0 || row2 != 0);
            }
        }
    }

    @Test
    public void bingoTicketColumnsContainsCorrectNumbers() {
        List<BingoTicket> bingoTickets = test.generate();

        for (BingoTicket bingoTicket : bingoTickets) {
            for (int i = 0; i < BingoTicket.COLUMNS; i++) {
                int row0 = bingoTicket.getNumbers()[0][i];
                int row1 = bingoTicket.getNumbers()[1][i];
                int row2 = bingoTicket.getNumbers()[2][i];

                assertTrue(validNumber(i, row0) && validNumber(i, row1) && validNumber(i, row2));
            }
        }
    }

    private boolean validNumber(Integer column, Integer number) {
        return number == 0 || (number - 1) / 10 == column;
    }

    @Test
    public void bingoTicketsContainsNumbersOneToNinety() {
        List<BingoTicket> bingoTickets = test.generate();

        List<Integer> oneToNinetyList = IntStream.rangeClosed(1, 90).boxed().collect(Collectors.toList());

        List<Integer> generatedIntegers = new ArrayList<>();
        for (BingoTicket bingoTicket : bingoTickets) {
            for (int i = 0; i < BingoTicket.ROWS; i++) {
                for (int j = 0; j < BingoTicket.COLUMNS; j++) {
                    int number = bingoTicket.getNumbers()[i][j];
                    if (number != 0)
                        generatedIntegers.add(number);
                }
            }
        }

        assertTrue(generatedIntegers.size() == oneToNinetyList.size() &&
                generatedIntegers.containsAll(oneToNinetyList) && oneToNinetyList.containsAll(generatedIntegers));
    }
}