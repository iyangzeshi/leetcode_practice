package leetcode.editor.en;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Stack;

/*

You are given a list of N tasks with each task taking S seconds to execute on
 a single CPU
 The ith task can start executing at or after start_time[i]. Given C CPU's.
 Find the minimum time at which all tasks will be executed
 
 start_time [1,3,6], s = 6, c = 2
 task 1 can be executed on CPU 1 from t = 1 to t = 7
 task 2 can be executed on CPU 2 from t = 3 to t = 9
 task 3 can be executed on CPU 1 from t = 7 to t = 13
 
 [1,7], [7,15]
 so the final execute time is 13
 */
public class Foo {
    
    public static void main(String[] args) {
        /*int[] startTime = {1, 3, 6};
        int s = 6;
        int c = 2;
        
        int res = findMinExeTime(startTime, c, s);
        System.out.println(res);*/
        System.out.println(transform("(a(bed){3}){2}e"));
    }
    
    /*
    ideas to solve the problem
    step 1: sort the startTime
    step 2: create minHeap to traverse the startTime, every time when thee
    is new task:
        if minHeap's size < c or oldest finished task in cpu is <= start[i],
            add start[i] + s to minHeap
        if minHeap's oldest finished task in cpu is > start[i]
            start task i at minTime in minHeap and add minTime + s to minHeap
            
    step 3: after traverse every task
        poll the end time in the minHeap, the last one is the time we need to
        execute all the tasks
    T(n) = O(nlog(n))
    S(n) = O(log(n) + c)
     */
    public static int findMinExeTime(int[] startTime, int c, int s) {
        // corner case
        if (startTime == null || startTime.length == 0) {
            return 0;
        }
        // step 1: sort the start time
        Arrays.sort(startTime);
        
        // step 2: use minHeap to traverse the tasks
        // store the end time of current task in minHeap
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : startTime) {
            if (minHeap.size() < c) {
                minHeap.offer(num + s);
            } else if (minHeap.peek() <= num) {
                minHeap.poll();
                minHeap.offer(num + s);
            } else { // minHeap.peek() > num
                int start = minHeap.poll();
                int end = start + s;
                minHeap.offer(end);
            }
        }
        // step 3: poll the last one's end time in the minHeap
        while (minHeap.size() != 1) {
            minHeap.poll();
        }
        return minHeap.poll();
    }
    
    /*
    problem description:
    input: a(bed){3}e
    output: abedbedbede
    expect: abedbedbede
    
    input: (a(bed){3}){2}e
    abedbedbedabedbedbede
    output: abedbedbedabedbedbede
    expect: abedbedbedabedbedbede
     */
    /*
    data structure: stack
    int num:
    
    ideas:
    step 1: traverse from left to right
    step 2: Stack<StringBuilder> stack,
        case 1: char, append current char to the StringBuilder
        case 2: number, combine digits to number
        case 3: (, stack push new StringBuilder
        case 4: ) : skip
        case 5: {: skip
        case 6: }: repeat previous stringBuilder in stack and append it back
        to the top element in the stack
     */
    public static String transform(String str) {
        // corner case
        
        Stack<StringBuilder> stack = new Stack<>();
        stack.push(new StringBuilder());
        int num = 0;
        int len = str.length();
        
        for (int i = 0; i < len; i++) {
            char ch = str.charAt(i);
            if (Character.isLetter(ch)) {
                stack.peek().append(ch);
            } else if (Character.isDigit(ch)) {
                num = 0;
                while (i < len && Character.isDigit(str.charAt(i))) {
                    num = num * 10 + (str.charAt(i) - '0');
                    i++;
                }
                i--;
            } else if (ch == '(') {
                stack.push(new StringBuilder());
            } else if (ch == ')' || ch == '{') {
                continue;
            } else if (ch == '}') {
                StringBuilder sb = new StringBuilder();
                StringBuilder prev = stack.pop();
                for (int j = 0; j < num; j++) {
                    sb.append(prev);
                }
                //
                // stack.push(sb);
                stack.peek().append(sb);
            } else { // invalid char
                continue;
            }
        }
        return stack.pop().toString();
    }
    
}