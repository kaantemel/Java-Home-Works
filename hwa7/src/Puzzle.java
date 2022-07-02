import java.util.*;

/** Word puzzle.
 * @since 1.8
 */
public class Puzzle {

   /** Solve the word puzzle.
    * @param args three words (addend1 addend2 sum)
    */

   static int mytot=0;
   static ArrayList <Long> myInts = new ArrayList<Long>();
   public static void main(String[] args)
   {
      mytot=0;
      SolutionFunc(args);
      System.out.println(mytot);
      if (!myInts.isEmpty())
      {
         System.out.println(myInts.get(0)+"+"+myInts.get(1)+"="+myInts.get(2));
      }
      myInts.clear();
   }
   public static boolean SolutionFunc(String[] args)
   {
      String[] tmp = new String[]{args[0],args[1]};
      String[] tmp1 = args;
      String result = args[2];
      args = tmp;

      int alp[] = new int[26];
      int array_usd[] = new int[10];


      int Hash[] = new int[26];
      int CharAtfront[] = new int[26];

      Arrays.fill(alp, -1);
      Arrays.fill(array_usd, 0);
      Arrays.fill(Hash, 0);
      Arrays.fill(CharAtfront, 0);


      StringBuilder uniq = new StringBuilder();

      for (String word : args) {


         for (int i = 0; i < word.length(); i++) {

            char ch = word.charAt(i);

            Hash[ch - 'A'] += (int)Math.pow(
                    10, word.length() - i - 1);

            if (alp[ch - 'A'] == -1) {

               alp[ch - 'A'] = 0;
               uniq.append((char)ch);
            }

            if (i == 0 && word.length() > 1) {

               CharAtfront[ch - 'A'] = 1;
            }
         }
      }

      for (int i = 0; i < result.length(); i++) {

         char ch = result.charAt(i);

         Hash[ch - 'A'] -= (int)Math.pow(
                 10, result.length() - i - 1);

         if (alp[ch - 'A'] == -1) {
            alp[ch - 'A'] = 0;
            uniq.append((char)ch);
         }

         if (i == 0 && result.length() > 1) {
            CharAtfront[ch - 'A'] = 1;
         }
      }

      Arrays.fill(alp, -1);

      return rec_solve(uniq, 0, 0, alp, array_usd, Hash,
              CharAtfront, tmp1);
   }

   public static boolean rec_solve(
           StringBuilder words, int i,
           int N, int[] mp, int[] used,
           int[] Hash,
           int[] CharAtfront, String[] args)
   {
      if (i == words.length())

         return (N == 0);

      char ch = words.charAt(i);

      int val = mp[words.charAt(i) - 'A'];

      if (val != -1) {

         return rec_solve(words, i + 1,
                 N + val * Hash[ch - 'A'],
                 mp, used,
                 Hash, CharAtfront,args);
      }

      boolean x = false;

      for (int l = 0; l < 10; l++) {

         if (CharAtfront[ch - 'A'] == 1
                 && l == 0)
            continue;

         if (used[l] == 1)
            continue;

         mp[ch - 'A'] = l;

         used[l] = 1;
         if (cond(args, mp)) {
            mytot = mytot + 1;
         }
         // Recursive function call
         x |= rec_solve(words, i + 1,
                 N + l * Hash[ch - 'A'],
                 mp, used, Hash, CharAtfront,args);

         mp[ch - 'A'] = -1;
         used[l] = 0;
      }
      return x;
   }
   public static boolean cond(String[] args , int[] nums)
   {
      boolean check = false;
      ArrayList<Long> myNums = new ArrayList<Long>();
      int at = 0;
      for (int u = 0; u<nums.length; u++)
      {
         if (nums[u]!=-1)
         {
            at=at+1;
         }
      }
      ArrayList<Character> letters = new ArrayList<Character>();
      for (int i =0; i< args.length;i++)
      {
         for (int y = 0; y<args[i].length();y++)
         {
            if (!letters.contains(args[i].charAt(y)))
            {
               letters.add(args[i].charAt(y));
            }
         }
      }
      int ab = letters.size();
      if (ab!=at)
      {
         return false;
      }
      for (int i = 0;i<args.length;i++)
      {
         String mynum ="";
         for (int y = 0; y<args[i].length();y++)
         {
            char a = args[i].charAt(y);
            int h = a-'A';
            int g = nums[h];
            mynum = mynum+Integer.toString(g);
         }
         myNums.add(Long.valueOf(mynum));
      }
      if (myNums.get(0)+myNums.get(1)==myNums.get(2))
      {
         myInts = myNums;
         return true;
      }
      return false;
   }
}

