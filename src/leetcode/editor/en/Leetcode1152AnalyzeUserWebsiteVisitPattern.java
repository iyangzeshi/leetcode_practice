//We are given some website visits: the user with name username[i] visited the weight
//ebsite website[i] at time timestamp[i]. 
//
// A 3-sequence is a list of websites of length 3 sorted in ascending order by t
//he time of their visits. (The websites in a 3-sequence are not necessarily disti
//nct.) 
//
// Find the 3-sequence visited by the largest number of users. If there is more 
//than one solution, return the lexicographically smallest such 3-sequence. 
//
// 
//
// Example 1: 
//
// 
//Input: username = ["joe","joe","joe","james","james","james","james","mary","m
//ary","mary"], timestamp = [1,2,3,4,5,6,7,8,9,10], website = ["home","about","car
//eer","home","cart","maps","home","home","about","career"]
//Output: ["home","about","career"]
//Explanation: 
//The tuples in this example are:
//["joe", 1, "home"]
//["joe", 2, "about"]
//["joe", 3, "career"]
//["james", 4, "home"]
//["james", 5, "cart"]
//["james", 6, "maps"]
//["james", 7, "home"]
//["mary", 8, "home"]
//["mary", 9, "about"]
//["mary", 10, "career"]
//The 3-sequence ("home", "about", "career") was visited at least once by 2 user
//s.
//The 3-sequence ("home", "cart", "maps") was visited at least once by 1 user.
//The 3-sequence ("home", "cart", "home") was visited at least once by 1 user.
//The 3-sequence ("home", "maps", "home") was visited at least once by 1 user.
//The 3-sequence ("cart", "maps", "home") was visited at least once by 1 user.
// 
//
// 
//
// Note: 
//
// 
// 3 <= size = username.length = timestamp.length = website.length <= 50
// 1 <= username[i].length <= 10 
// 0 <= timestamp[i] <= 10^9 
// 1 <= website[i].length <= 10 
// Both username[i] and website[i] contain only lowercase characters. 
// It is guaranteed that there is at least one user who visited at least 3 websi
//tes. 
// No user visits two websites at the same time. 
// 
// Related Topics Array Hash Table Sort 
// 👍 115 👎 1016

package leetcode.editor.en;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

// 2020-11-17 16:38:42
// Jesse Yang
public class Leetcode1152AnalyzeUserWebsiteVisitPattern{
    // Java: analyze-user-website-visit-pattern
    public static void main(String[] args) {
        Solution sol = new Leetcode1152AnalyzeUserWebsiteVisitPattern().new Solution();
        // TO TEST
        String[] username = {"joe","joe","joe","james","james","james","james","mary","mary",
                "mary"};
        int[] timestamp = {1,2,3,4,5,6,7,8,9,10};
        String[] website = {"home","about","career","home","cart","maps","home","home","about",
                "career"};
        List<String> res = sol.mostVisitedPattern(username, timestamp, website);
        System.out.println(res);
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        List<String> res = new ArrayList<>();
        // corner case
        if (username == null || username.length == 0 || timestamp == null || timestamp.length == 0
                || website == null || website.length == 0) {
            return res;
        }
        
        List<Log> logList = buildSortedLogList(username, timestamp, website); // sorted by time
        
        Map<String, List<String>> userToWebsites = buildUserWebsite(logList);
        
        // find all possible 3-sequence, every Set stores every one's all 3-sequence
        List<Set<List<String>>> userSeq = getSeqForEveryUser(userToWebsites);
    
        // find top 3 website sequence
        int max = 0; // the current count of website visited by the largest number of users.
        Map<List<String>, Integer> seqCount = new HashMap<>(); // key: sequence, value: its count
        for (Set<List<String>> set : userSeq) {
            for (List<String> seq : set) {
                seqCount.put(seq, seqCount.getOrDefault(seq, 0) + 1);
                if (seqCount.get(seq) > max) {
                    max = seqCount.get(seq);
                    res = seq;
                } else if (seqCount.get(seq) == max) {
                    res = findSmaller(seq, res);
                }
            }
        }
        return res;
    }
    
    // build Log List and sort them by time stamp
    private List<Log> buildSortedLogList(String[] username, int[] timestamp, String[] website) {
        List<Log> logList = new ArrayList<>();
        int len = username.length;
        for (int i = 0; i < len; i++) {
            logList.add(new Log(username[i], timestamp[i], website[i]));
        }
        // sort list by timestamp
        logList.sort(Comparator.comparingInt(o -> o.timestamp));
        return logList;
    }
    
    // build a map, key is user, value is List of websites in ascending order by time stamp
    private Map<String, List<String>> buildUserWebsite(List<Log> logList) {
        Map<String, List<String>> userToWebsites = new HashMap<>();
        for (Log log : logList) {
            if (!userToWebsites.containsKey(log.username)) {
                userToWebsites.put(log.username, new ArrayList<>());
            }
            userToWebsites.get(log.username).add(log.website);
        }
        return userToWebsites;
    }
    
    /**
     * get all possible 3-sequence for every person
     * every Set is corresponding to one person's all possible 3 website sequence
     */
    private List<Set<List<String>>> getSeqForEveryUser(Map<String, List<String>> userToWebsites) {
        List<Set<List<String>>> userSeq = new ArrayList<>();
        for (String user : userToWebsites.keySet()) {
            List<String> webList = userToWebsites.get(user);
            Set<List<String>> set = new HashSet<>();
            for (int i = 0; i < webList.size() - 2; i++) {
                for (int j = i + 1; j < webList.size() - 1; j++) {
                    for (int k = j + 1; k < webList.size(); k++) {
                        List<String> seq = new ArrayList<>();
                        seq.add(webList.get(i));
                        seq.add(webList.get(j));
                        seq.add(webList.get(k));
                        set.add(seq);
                    }
                }
            }
            userSeq.add(set);
        }
        return userSeq;
    }
    
    // if the count of two sequence is same, return the smaller one order by lexicographically
    private List<String> findSmaller(List<String> seq, List<String> candidate) {
        int len = seq.size();
        for (int i = 0; i < len; i++) {
            String web1 = seq.get(i);
            String web2 = candidate.get(i);
            int compare = web1.compareTo(web2);
            if (compare < 0) {
                return seq;
            } else if (compare > 0) {
                return candidate;
            }
        }
        return candidate;
    }
    
    private class Log {
        
        private final String username;
        private final int timestamp;
        private final String website;
        
        public Log(String username, int timestamp, String website) {
            this.username = username;
            this.timestamp = timestamp;
            this.website = website;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
// Solution 1: sort by time, and count. T(n) = O(n), S(n) = O(n), n is the length of the array
// 16 ms,击败了83.94% 的Java用户, 39.7 MB,击败了75.11% 的Java用户
class Solution1 {
    
    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        List<String> res = new ArrayList<>();
        // corner case
        if (username == null || username.length == 0 || timestamp == null || timestamp.length == 0
                || website == null || website.length == 0) {
            return res;
        }
        
        List<Log> logList = buildSortedLogList(username, timestamp, website); // sorted by time
        
        Map<String, List<String>> userToWebsites = buildUserWebsite(logList);
        
        // find all possible 3-sequence, every Set stores every one's all 3-sequence
        List<Set<List<String>>> userSeq = getSeqForEveryUser(userToWebsites);
        
        // find top 3 website sequence
        int max = 0; // the current count of website visited by the largest number of users.
        Map<List<String>, Integer> seqCount = new HashMap<>(); // key: sequence, value: its count
        for (Set<List<String>> set : userSeq) {
            for (List<String> seq : set) {
                seqCount.put(seq, seqCount.getOrDefault(seq, 0) + 1);
                if (seqCount.get(seq) > max) {
                    max = seqCount.get(seq);
                    res = seq;
                } else if (seqCount.get(seq) == max) {
                    res = findSmaller(seq, res);
                }
            }
        }
        return res;
    }
    
    // build Log List and sort them by time stamp
    private List<Log> buildSortedLogList(String[] username, int[] timestamp, String[] website) {
        List<Log> logList = new ArrayList<>();
        int len = username.length;
        for (int i = 0; i < len; i++) {
            logList.add(new Log(username[i], timestamp[i], website[i]));
        }
        // sort list by timestamp
        logList.sort(Comparator.comparingInt(o -> o.timestamp));
        return logList;
    }
    
    // build a map, key is user, value is List of websites in ascending order by time stamp
    private Map<String, List<String>> buildUserWebsite(List<Log> logList) {
        Map<String, List<String>> userToWebsites = new HashMap<>();
        for (Log log : logList) {
            if (!userToWebsites.containsKey(log.username)) {
                userToWebsites.put(log.username, new ArrayList<>());
            }
            userToWebsites.get(log.username).add(log.website);
        }
        return userToWebsites;
    }
    
    /**
     * get all possible 3-sequence for every person
     * every Set is corresponding to one person's all possible 3 website sequence
     */
    private List<Set<List<String>>> getSeqForEveryUser(Map<String, List<String>> userToWebsites) {
        List<Set<List<String>>> userSeq = new ArrayList<>(); //
        for (String user : userToWebsites.keySet()) {
            List<String> webList = userToWebsites.get(user);
            Set<List<String>> set = new HashSet<>();
            for (int i = 0; i < webList.size() - 2; i++) {
                for (int j = i + 1; j < webList.size() - 1; j++) {
                    for (int k = j + 1; k < webList.size(); k++) {
                        List<String> seq = new ArrayList<>();
                        seq.add(webList.get(i));
                        seq.add(webList.get(j));
                        seq.add(webList.get(k));
                        set.add(seq);
                    }
                }
            }
            userSeq.add(set);
        }
        return userSeq;
    }
    
    // if the count of two sequence is same, return the smaller one order by lexicographically
    private List<String> findSmaller(List<String> seq, List<String> candidate) {
        int len = seq.size();
        for (int i = 0; i < len; i++) {
            String web1 = seq.get(i);
            String web2 = candidate.get(i);
            int compare = web1.compareTo(web2);
            if (compare < 0) {
                return seq;
            } else if (compare > 0) {
                return candidate;
            }
        }
        return candidate;
    }
    
    private class Log {
        
        private final String username;
        private final int timestamp;
        private final String website;
        
        public Log(String username, int timestamp, String website) {
            this.username = username;
            this.timestamp = timestamp;
            this.website = website;
        }
    }
}

// Solution 2: sort by time, and count. T(n) = O(n), S(n) = O(n), n is the length of the array
// 15 ms,击败了87.98% 的Java用户, 40.1 MB,击败了33.84% 的Java用户
class Solution2 {
    
    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        List<String> res = new ArrayList<>();
        // corner case
        if (username == null || username.length == 0 || timestamp == null || timestamp.length == 0
                || website == null || website.length == 0) {
            return res;
        }
        
        Map<String, Map<Integer, String>> userData = buildUserVisit(username, timestamp, website);
        // find all possible 3-sequence, every Set stores every one's all 3-sequence
        List<Set<List<String>>> userSeq = getSeqForEveryUser(userData);
        
        // find top 3 website sequence
        int max = 0; // the current count of website visited by the largest number of users.
        Map<List<String>, Integer> seqCount = new HashMap<>(); // key: sequence, value: its count
        for (Set<List<String>> set : userSeq) {
            for (List<String> seq : set) {
                seqCount.put(seq, seqCount.getOrDefault(seq, 0) + 1);
                if (seqCount.get(seq) > max) {
                    max = seqCount.get(seq);
                    res = seq;
                } else if (seqCount.get(seq) == max) {
                    res = findSmaller(seq, res);
                }
            }
        }
        return res;
    }
    
    // build a map, key is user, value is treemap whose key is time and value is website
    private Map<String, Map<Integer, String>> buildUserVisit(String[] username, int[] timestamp,
            String[] website) {
        Map<String, Map<Integer, String>> map = new HashMap<>();
        int len = username.length;
        for (int i = 0; i < len; i++) {
            String user = username[i];
            int time = timestamp[i];
            String web = website[i];
            map.computeIfAbsent(user, k -> new TreeMap<>()).put(time, web);
        }
        return map;
    }
    
    private List<Set<List<String>>> getSeqForEveryUser(Map<String, Map<Integer, String>> userData) {
        List<Set<List<String>>> userSeq = new ArrayList<>();
        for (String user : userData.keySet()) {
            List<String> webList = new ArrayList<>(userData.get(user).values());
            Set<List<String>> set = new HashSet<>();
            for (int i = 0; i < webList.size() - 2; i++) {
                for (int j = i + 1; j < webList.size() - 1; j++) {
                    for (int k = j + 1; k < webList.size(); k++) {
                        List<String> seq = new ArrayList<>();
                        seq.add(webList.get(i));
                        seq.add(webList.get(j));
                        seq.add(webList.get(k));
                        set.add(seq);
                    }
                }
            }
            userSeq.add(set);
        }
        return userSeq;
    }
    
    // if the count of two sequence is same, return the smaller one order by lexicographically
    private List<String> findSmaller(List<String> seq, List<String> candidate) {
        int len = seq.size();
        for (int i = 0; i < len; i++) {
            String web1 = seq.get(i);
            String web2 = candidate.get(i);
            int compare = web1.compareTo(web2);
            if (compare < 0) {
                return seq;
            } else if (compare > 0) {
                return candidate;
            }
        }
        return candidate;
    }
    
}
}