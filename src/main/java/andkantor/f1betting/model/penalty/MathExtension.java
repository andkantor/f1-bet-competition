package andkantor.f1betting.model.penalty;

import java.util.Arrays;
import java.util.OptionalDouble;
import java.util.stream.IntStream;

public class MathExtension {

    public static double average(int[] numbers) {
        return IntStream.of(numbers)
                .average()
                .orElse(0);
    }

    public static double variance(int[] numbers) {
        if (numbers.length == 0) {
            return 0;
        }

        double average = average(numbers);

        OptionalDouble summedDeviation = Arrays.stream(numbers)
                .mapToDouble(number -> Math.pow(number - average, 2))
                .reduce((left, right) -> left + right);

        return summedDeviation.orElse(0) / numbers.length;
    }

    public static double standardDeviation(int[] numbers) {
        return Math.sqrt(variance(numbers));
    }

}
