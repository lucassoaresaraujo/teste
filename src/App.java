import java.util.*;

public class App {
    public static void main(String[] args) {
        String reservedSeatsStr = "";
        int numberOfRows = 2;
        int result = solution(numberOfRows, reservedSeatsStr);
        System.out.println(result);
    }

    public static int solution(int numberOfRows, String reservedSeatsStr) {
        HashMap<Integer, HashSet<String>> resevedSeatsByRows = getResevedSeatsByRows(reservedSeatsStr);
        int result = 0;
        for (int row = 1; row <= numberOfRows; row++) {
            int numberOfFamiliesInRow = getNumberOfFamiliesByRow(resevedSeatsByRows, row);
            result += numberOfFamiliesInRow;
        }

        return result;
    }

    private static HashMap<Integer, HashSet<String>> getResevedSeatsByRows(String reservedSeatsStr) {
        HashMap<Integer, HashSet<String>> resevedSeatsByRows = new HashMap<>();

        if (reservedSeatsStr.isEmpty() || reservedSeatsStr.isBlank()) {
            return resevedSeatsByRows;
        }

        String[] resevedSeats = reservedSeatsStr.split(" ");

        for (String s : resevedSeats) {
            Integer row = Character.getNumericValue(s.charAt(0));
            String seat = String.valueOf(s.charAt(1));

            if (resevedSeatsByRows.containsKey(row)) {
                HashSet<String> resevedSeatsInRow = resevedSeatsByRows.get(row);
                resevedSeatsInRow.add(seat);
            } else {
                resevedSeatsByRows.put(row, new HashSet<>(List.of(seat)));
            }
        }
        return resevedSeatsByRows;
    }
    // reservedSeatsInRow
    private static int getNumberOfFamiliesByRow(HashMap<Integer, HashSet<String>> resevedSeatsByRows, int row) {
        if (!resevedSeatsByRows.containsKey(row)) {
            return  2;
        }

        HashSet<String> reservedSeatsInRow = resevedSeatsByRows.get(row);

        if(isFree(reservedSeatsInRow, List.of("B", "C", "D", "E", "F", "G", "H", "J"))) {
            return 2;
        }

        if(isFree(reservedSeatsInRow, List.of("B", "C", "D", "E"))) {
            return 1;
        }

        if(isFree(reservedSeatsInRow, List.of("F", "G", "H", "J"))) {
            return 1;
        }

        if(isFree(reservedSeatsInRow, List.of("D", "E", "F", "G"))) {
            return 1;
        }

        return 0;
    }

    private static boolean isFree(HashSet<String> reservedSeatsInRow, List<String> listSeats) {
        return reservedSeatsInRow.stream().noneMatch(listSeats::contains);
    }
}