package hva.core;

import java.util.*;
import java.util.regex.*;

public class NaturalCaseInsensitiveComparator implements Comparator<String> {
  private final Pattern pattern = Pattern.compile("(\\D+)|(\\d+)");

  /**
   * Compares two strings in a natural order, ignoring case differences.
   * The comparison is performed by splitting the strings into segments of digits and non-digits.
   * Digit segments are compared numerically, while non-digit segments are compared lexicographically, ignoring case.
   * 
   * @param s1 the first string to be compared
   * @param s2 the second string to be compared
   * @return a negative integer, zero, or a positive integer as the first string is less than, equal to, or greater than the second string
   */
  @Override
  public int compare(String s1, String s2) {
      Matcher m1 = pattern.matcher(s1);
      Matcher m2 = pattern.matcher(s2);

      while (m1.find() && m2.find()) {
          String part1 = m1.group();
          String part2 = m2.group();

          int cmp = part1.matches("\\d+") && part2.matches("\\d+")
                  ? Integer.compare(Integer.parseInt(part1), Integer.parseInt(part2))
                  : part1.compareToIgnoreCase(part2);

          if (cmp != 0) return cmp;
      }
      return Integer.compare(s1.length(), s2.length());
  }
}
