package andkantor.f1betting.model.penalty;

import org.junit.Before;
import org.junit.Test;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import static andkantor.f1betting.model.penalty.MathExtension.average;
import static andkantor.f1betting.model.penalty.MathExtension.standardDeviation;
import static andkantor.f1betting.model.penalty.MathExtension.variance;
import static org.assertj.core.api.Assertions.assertThat;

public class MathExtensionTest {

    private DecimalFormat df;

    @Before
    public void setUp() {
        DecimalFormatSymbols dfs = DecimalFormatSymbols.getInstance();
        dfs.setDecimalSeparator('.');
        df = new DecimalFormat("####0.00", dfs);
    }

    @Test
    public void testAverage() {
        assertThat(df.format(average(new int[]{2, 4, 4, 4, 5, 5, 7, 9}))).isEqualTo("5.00");
        assertThat(df.format(average(new int[]{1, 1, 1}))).isEqualTo("1.00");
        assertThat(df.format(average(new int[]{1, 1, 2}))).isEqualTo("1.33");
        assertThat(df.format(average(new int[]{1, 2, 2}))).isEqualTo("1.67");
        assertThat(df.format(average(new int[]{1, 2, 3}))).isEqualTo("2.00");
        assertThat(df.format(average(new int[]{2, 4, 5}))).isEqualTo("3.67");
        assertThat(df.format(average(new int[]{2, 4, 7}))).isEqualTo("4.33");
        assertThat(df.format(average(new int[]{2, 7}))).isEqualTo("4.50");
        assertThat(df.format(average(new int[]{1, 3}))).isEqualTo("2.00");
        assertThat(df.format(average(new int[]{1, 1}))).isEqualTo("1.00");
        assertThat(df.format(average(new int[]{3}))).isEqualTo("3.00");
        assertThat(df.format(average(new int[]{}))).isEqualTo("0.00");
    }

    @Test
    public void testVariance() {
        assertThat(df.format(variance(new int[]{2, 4, 4, 4, 5, 5, 7, 9}))).isEqualTo("4.00");
        assertThat(df.format(variance(new int[]{1, 1, 1}))).isEqualTo("0.00");
        assertThat(df.format(variance(new int[]{1, 1, 2}))).isEqualTo("0.22");
        assertThat(df.format(variance(new int[]{1, 2, 2}))).isEqualTo("0.22");
        assertThat(df.format(variance(new int[]{1, 2, 3}))).isEqualTo("0.67");
        assertThat(df.format(variance(new int[]{2, 4, 5}))).isEqualTo("1.56");
        assertThat(df.format(variance(new int[]{2, 4, 7}))).isEqualTo("4.22");
        assertThat(df.format(variance(new int[]{2, 7}))).isEqualTo("6.25");
        assertThat(df.format(variance(new int[]{1, 3}))).isEqualTo("1.00");
        assertThat(df.format(variance(new int[]{1, 1}))).isEqualTo("0.00");
        assertThat(df.format(variance(new int[]{3}))).isEqualTo("0.00");
        assertThat(df.format(variance(new int[]{}))).isEqualTo("0.00");
    }

    @Test
    public void testStandardDeviation() {
        assertThat(df.format(standardDeviation(new int[]{2, 4, 4, 4, 5, 5, 7, 9}))).isEqualTo("2.00");
        assertThat(df.format(standardDeviation(new int[]{1, 1, 1}))).isEqualTo("0.00");
        assertThat(df.format(standardDeviation(new int[]{1, 1, 2}))).isEqualTo("0.47");
        assertThat(df.format(standardDeviation(new int[]{1, 2, 2}))).isEqualTo("0.47");
        assertThat(df.format(standardDeviation(new int[]{1, 2, 3}))).isEqualTo("0.82");
        assertThat(df.format(standardDeviation(new int[]{2, 4, 5}))).isEqualTo("1.25");
        assertThat(df.format(standardDeviation(new int[]{2, 4, 7}))).isEqualTo("2.05");
        assertThat(df.format(standardDeviation(new int[]{2, 7}))).isEqualTo("2.50");
        assertThat(df.format(standardDeviation(new int[]{1, 3}))).isEqualTo("1.00");
        assertThat(df.format(standardDeviation(new int[]{1, 1}))).isEqualTo("0.00");
        assertThat(df.format(standardDeviation(new int[]{3}))).isEqualTo("0.00");
        assertThat(df.format(standardDeviation(new int[]{}))).isEqualTo("0.00");
    }

}