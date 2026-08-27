import java.util.*;

class LongestSubarray {

    public static int longestSubarray(int[] nums, int k) {

        HashMap<Integer, Integer> map = new HashMap<>();

        map.put(0, -1);

        int prefix = 0;
        int maxLength = 0;

        for (int i = 0; i < nums.length; i++) {

            prefix += nums[i];

            int needed = prefix - k;

            if (map.containsKey(needed)) {

                int length = i - map.get(needed);

                maxLength = Math.max(maxLength, length);
            }

            if (!map.containsKey(prefix)) {
                map.put(prefix, i);
            }
        }

        return maxLength;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] nums = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        int k = sc.nextInt();

        int result = longestSubarray(nums, k);

        System.out.println(result);
    }
}