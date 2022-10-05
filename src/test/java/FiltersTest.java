import java.util.HashSet;
import java.util.Set;

/**
 * 用來測試實現的布隆過濾器是否正常工作
 */
public class FiltersTest {

  public static void main(final String[] args) {
    test_counting_bloom_filters();
  }

  private static void test_counting_bloom_filters() {
    Set<Long> iSet = new HashSet<>();
    for (long i = 0; i < 10000; iSet.add(i++));
    SBFilters sbFilter = new SBFilters(999999, iSet);
    int hashCode = Math.abs("amy_alan".hashCode())/1000000;
    for (var item : new int[]{1, 3, 5, 78, 99, 100, 101, 9999, 10000, 3534, hashCode}) {
      var isIn = sbFilter.query_filter(item);
      if (!isIn) {
        System.out.printf("%d is not in the filter\n", item);
      } else {
        System.out.printf("%d is in the filter\n", item);
      }
    }
  }
}
