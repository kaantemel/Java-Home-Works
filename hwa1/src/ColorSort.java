/** Sorting of balls.
 * @since 1.8
 */
public class ColorSort {

   enum Color {red, green, blue};

   public static void main (String[] param) {

      // for debugging

   }

   public static void reorder (Color[] balls) {
      // TODO!!! Your program here
      int indRed=0;
      int indBlue = 0;
      for (int y = 0;y< balls.length;y++){
         if (balls[y]==Color.red){
            indRed++;
         }
         else if (balls[y]==Color.blue){
            indBlue++;
         }
      }
      int count = 0;

      while(count != balls.length)
      {
         if (count<indRed)
         {
            balls[count]=Color.red;
         }
         else if(count < (balls.length-indBlue))
         {
            balls[count] = Color.green;
         }
         else if(count< balls.length)
         {
            balls[count] = Color.blue;
         }
         count++;
      }

   }
}