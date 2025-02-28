import java.util.Arrays;

public class GaleShapley {
    static int N = 4; // Number of men and women
    
    // Function to implement the Gale-Shapley algorithm
    public static int[] stableMatching(int[][] menPref, int[][] womenPref) {
        int[] womenPartner = new int[N]; // Store the partner of each woman
        boolean[] menFree = new boolean[N]; // Track free men
        
        Arrays.fill(womenPartner, -1); // Initially, no women are engaged
        
        
        int freeMenCount = N;

        while (freeMenCount > 0) {
            int man;
            for ( man = 0; man < N; man++) {
                if (!menFree[man]) {
                    break;
                }
            }

            for (int i = 0; i < N && !menFree[man]; i++) {
                int woman = menPref[man][i];

                // If woman is free, engage with this man
                if (womenPartner[woman] == -1) {
                    womenPartner[woman] = man;
                    menFree[man] = true;
                    freeMenCount--;
                } else {
                    int currentPartner = womenPartner[woman];

                    // Check if the woman prefers new man over current partner
                    if (prefersNewMan(womenPref[woman], man, currentPartner)) {
                        womenPartner[woman] = man;
                        menFree[man] = true;
                        menFree[currentPartner] = false;
                    }
                }
            }
        }

        int[] menPartner = new int[N];
        for (int woman = 0; woman < N; woman++) {
            menPartner[womenPartner[woman]] = woman;
        }
        return menPartner;
    }

    // Function to check if a woman prefers new man over her current partner
    private static boolean prefersNewMan(int[] womanPref, int newMan, int currentMan) {
        for (int man : womanPref) {
            if (man == newMan) return true;
            if (man == currentMan) return false;
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] menPref = {
            {0, 1, 2, 3},
            {1, 0, 3, 2},
            {2, 3, 1, 0},
            {3, 2, 0, 1}
        };

        int[][] womenPref = {
            {3, 1, 2, 0},
            {0, 1, 2, 3},
            {1, 2, 0, 3},
            {2, 3, 1, 0}
        };

         int[] matches = stableMatching(menPref, womenPref);

        System.out.println("Stable Matches:");
        for (int man = 0; man < N; man++) {
            System.out.println("Man " + man + " is matched with Woman " + matches[man]);
        }
    }
}