import java.util.*;

public class MinimumRadius {

    public static int findRadius(int[] houses, int[] lamps) {

        Arrays.sort(houses);
        Arrays.sort(lamps);

        int radius = 0;

        for (int i = 0; i < houses.length; i++) {

            int house = houses[i];

            int left = 0;
            int right = lamps.length - 1;

            while (left < right) {

                int mid = (left + right) / 2;

                if (lamps[mid] < house) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            int dist = Math.abs(lamps[left] - house);

            if (left > 0) {
                dist = Math.min(dist, Math.abs(lamps[left - 1] - house));
            }

            radius = Math.max(radius, dist);
        }

        return radius;
    }

    public static void main(String[] args) {

        int[] houses = {1, 3, 4};
        int[] lamps = {0, 2};

        System.out.println(findRadius(houses, lamps));
    }
}