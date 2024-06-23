/**
There is an authentication system that works with authentication tokens. For 
each session, the user will receive a new authentication token that will expire 
timeToLive seconds after the currentTime. If the token is renewed, the expiry time 
will be extended to expire timeToLive seconds after the (potentially different) 
currentTime. 

 Implement the AuthenticationManager class: 

 
 AuthenticationManager(int timeToLive) constructs the AuthenticationManager and 
sets the timeToLive. 
 generate(string tokenId, int currentTime) generates a new token with the given 
tokenId at the given currentTime in seconds. 
 renew(string tokenId, int currentTime) renews the unexpired token with the 
given tokenId at the given currentTime in seconds. If there are no unexpired tokens 
with the given tokenId, the request is ignored, and nothing happens. 
 countUnexpiredTokens(int currentTime) returns the number of unexpired tokens 
at the given currentTime. 
 

 Note that if a token expires at time t, and another action happens on time t (
renew or countUnexpiredTokens), the expiration takes place before the other 
actions. 

 
 Example 1: 
 
 
Input
["AuthenticationManager", "renew", "generate", "countUnexpiredTokens", 
"generate", "renew", "renew", "countUnexpiredTokens"]
[[5], ["aaa", 1], ["aaa", 2], [6], ["bbb", 7], ["aaa", 8], ["bbb", 10], [15]]
Output
[null, null, null, 1, null, null, null, 0]
 

Explanation
AuthenticationManager authenticationManager = new AuthenticationManager(5); // 
Constructs the AuthenticationManager with timeToLive = 5 seconds.
authenticationManager.renew("aaa", 1); // No token exists with tokenId "aaa" at 
time 1, so nothing happens.
authenticationManager.generate("aaa", 2); // Generates a new token with tokenId 
"aaa" at time 2.
authenticationManager.countUnexpiredTokens(6); // The token with tokenId "aaa" 
is the only unexpired one at time 6, so return 1.
authenticationManager.generate("bbb", 7); // Generates a new token with tokenId 
"bbb" at time 7.
authenticationManager.renew("aaa", 8); // The token with tokenId "aaa" expired 
at time 7, and 8 >= 7, so at time 8 the renew request is ignored, and nothing 
happens.
authenticationManager.renew("bbb", 10); // The token with tokenId "bbb" is 
unexpired at time 10, so the renew request is fulfilled and now the token will 
expire at time 15.
authenticationManager.countUnexpiredTokens(15); // The token with tokenId "bbb" 
expires at time 15, and the token with tokenId "aaa" expired at time 7, so 
currently no token is unexpired, so return 0.


 
 Constraints: 

 
 1 <= timeToLive <= 10⁸ 
 1 <= currentTime <= 10⁸ 
 1 <= tokenId.length <= 5 
 tokenId consists only of lowercase letters. 
 All calls to generate will contain unique values of tokenId. 
 The values of currentTime across all the function calls will be strictly 
increasing. 
 At most 2000 calls will be made to all functions combined. 
 

 Related Topics Hash Table Design 👍 367 👎 48

*/
package leetcode.editor.en;

import java.util.HashMap;
import java.util.Map;

// 2024-06-22 23:29:19
// Jesse Yang
public class Leetcode1797DesignAuthenticationManager{
    // Java: design-authentication-manager
    public static void main(String[] args) {
        AuthenticationManager sol =
                new Leetcode1797DesignAuthenticationManager().new AuthenticationManager(1);
        // TO TEST
        
        System.out.println();
    }
//leetcode submit region begin(Prohibit modification and deletion)
/*
T(n) = O(n), S(n) = O(n)
思路是 hashmap。这道题不难，hashmap 就能解决，
但是注意 renew 的规则，如果给定 tokenId 对应的验证码不存在或已过期，什么都不要做。
注意 countUnexpiredTokens() 函数，我们统计有效 token 的时候，不能把无效的 token 删除，这样会报一个多线程的错误。
所以只能通过判断当前 token 是否过期来决定他是否有效，没必要删除。
 */
class AuthenticationManager {
    
        private int timeToLive;
        private Map<String, Integer> map = new HashMap<>();
        
    public AuthenticationManager(int timeToLive) {
        this.timeToLive = timeToLive;
        map = new HashMap<>();
    }
    
    public void generate(String tokenId, int currentTime) {
        map.put(tokenId, currentTime + timeToLive);
    }
    
    public void renew(String tokenId, int currentTime) {
        if (map.containsKey(tokenId) && map.get(tokenId) > currentTime) {
            map.put(tokenId, currentTime + timeToLive);
        }
    }
    
    public int countUnexpiredTokens(int currentTime) {
        int count = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() > currentTime) {
                count++;
            }
        }
        return count;
    }
}

/**
 * Your AuthenticationManager object will be instantiated and called as such:
 * AuthenticationManager obj = new AuthenticationManager(timeToLive);
 * obj.generate(tokenId,currentTime);
 * obj.renew(tokenId,currentTime);
 * int param_3 = obj.countUnexpiredTokens(currentTime);
 */
//leetcode submit region end(Prohibit modification and deletion)

}