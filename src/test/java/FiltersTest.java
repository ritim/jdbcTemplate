import java.util.HashSet;
import java.util.Set;

/**
 * 用來測試實現的布隆過濾器是否正常工作
 */
public class FiltersTest {

  public static void main(final String[] args) {
    //e(Euler's number 歐拉數) = 2.7182818284.....
    System.out.println(Math.log(4) / Math.log(2));
    double x = 3;
    // when both are not infinity
    double result = Math.exp(x);
    System.out.println("Math.exp(" + x + ")=>" + result);
    //testCountingBloomFilters();
  }

  private static void testCountingBloomFilters() {

    Set<Long> iSet = new HashSet<>();
    for (long i = 0; i < 10000; iSet.add(i++));
    SBFilters sbFilter = new SBFilters(999999, iSet);
    int hashCode = Math.abs("amy_alan".hashCode())/1000000;
    for (var item : new int[]{1, 3, 5, 78, 99, 100, 101, 9999, 10000, 3534}) {
      var isIn = sbFilter.queryFilter(item);
      if (!isIn) {
        System.out.printf("%d is not in the filter\n", item);
      } else {
        System.out.printf("%d is in the filter\n", item);
      }
    }
  }
}
