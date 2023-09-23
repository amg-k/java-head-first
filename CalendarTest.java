import java.time.*;

public class CalendarTest {
    public static void main(String[] args) {
        LocalDate date = LocalDate.now();
        int month = date.getMonthValue();
        int today = date.getDayOfMonth();

        date = date.minusDays(today - 1);
        DayOfWeek day = date.getDayOfWeek();
        int dayValue = day.getValue();

        System.out.println(" Pn  Wt  Śr  Cz  Pt  Sb  Nd ");
        for (int i = 1; i < dayValue; i++) {
            System.out.print("    ");
        }
        while (date.getMonthValue() == month) {
            System.out.printf("%3d", date.getDayOfMonth());
            if (date.getDayOfMonth() == today) {
                System.out.print("*");
            } else {
                System.out.print(" ");
            }
            if (date.getDayOfWeek().getValue() == 7) System.out.println();
            date = date.plusDays(1);
        }
        if (date.getDayOfWeek().getValue() != 1) System.out.println();
    }
}
