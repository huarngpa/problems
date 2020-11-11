import java.util.*;

/**
 * Analyze User Website Visit Pattern
 * 
 * We are given some website visits: the user with name username[i] visited the
 * website website[i] at time timestamp[i].
 * 
 * A 3-sequence is a list of websites of length 3 sorted in ascending order by
 * the time of their visits. (The websites in a 3-sequence are not necessarily
 * distinct.)
 * 
 * Find the 3-sequence visited by the largest number of users. If there is more
 * than one solution, return the lexicographically smallest such 3-sequence.
 */
public class WebsitePatternsSolution {

  public static class Visit {
    String username;
    int timestamp;
    String website;

    Visit(String username, int timestamp, String website) {
      this.username = username;
      this.timestamp = timestamp;
      this.website = website;
    }

    public String toString() {
      return "(" + username + ", " + timestamp + ", " + website + ")";
    }
  }

  public static List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
    // Step 1: sort
    List<Visit> list = new ArrayList<>();
    for (int i = 0; i < username.length; i++) {
      Visit visit = new Visit(username[i], timestamp[i], website[i]);
      list.add(visit);
    }
    Collections.sort(list, (l, r) -> l.timestamp - r.timestamp);
    System.out.println(list);
    // Step 2: group user by sequence
    Map<String, List<String>> map = new HashMap<>();
    for (Visit visit : list) {
      String user = visit.username;
      List<String> sites = map.getOrDefault(user, new ArrayList<>());
      sites.add(visit.website);
      map.put(user, sites);
    }
    System.out.println(map);
    // Step 3: find the max three sequence
    String max = null;
    Map<String, Integer> counts = new HashMap<>();
    for (String user : map.keySet()) {
      List<String> sites = map.get(user);
      Set<String> combos = new HashSet<>();
      // Key idea is that we need to try every combo
      for (int i = 0; i < sites.size() - 2; i++) {
        for (int j = i + 1; j < sites.size() - 1; j++) {
          for (int k = j + 1; k < sites.size(); k++) {
            String threeSeq = sites.get(i) + "-" + sites.get(j) + "-" + sites.get(k);
            combos.add(threeSeq);
          }
        }
      }
      for (String combo : combos) {
        int newCount = counts.getOrDefault(combo, 0) + 1;
        counts.put(combo, newCount);
        if (max == null) {
          max = combo;
        } else {
          int maxCount = counts.get(max);
          if (newCount > maxCount) {
            max = combo;
          } else if (newCount == maxCount && combo.compareTo(max) < 0) {
            max = combo;
          }
        }
      }
    }
    System.out.println(counts);
    return new ArrayList<String>(Arrays.asList(max.split("-")));
  }

  public static void main(String[] args) {
    String[] username = new String[] { "joe", "joe", "joe", "james", "james", "james", "james", "mary", "mary",
        "mary" };
    int[] timestamp = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    String[] website = new String[] { "home", "about", "career", "home", "cart", "maps", "home", "home", "about",
        "career" };
    System.out.println(mostVisitedPattern(username, timestamp, website));
  }
}
