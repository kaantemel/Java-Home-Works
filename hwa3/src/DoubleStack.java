import java.util.*;

/** Stack manipulation.
 * @since 1.8
 */
public class DoubleStack {

   public static void main (String[] argum) {
      // TODO!!! Your tests here!
   }
   LinkedList<Double> myStack;
   DoubleStack() {
      myStack = new LinkedList<Double>();
      // TODO!!! Your constructor here!
   }


   @Override
   public Object clone() throws CloneNotSupportedException {
      DoubleStack tmp = new DoubleStack();

      for (int i = 0; i<this.myStack.size();i++)
      {
         tmp.myStack.add(this.myStack.get(i));
      }

      return tmp; // TODO!!! Your code here!
   }

   public boolean stEmpty() {
      boolean asd = false;
      if (this.myStack.size()==0)
      {
         asd=true;
      }
      return asd;
      // TODO!!! Your code here!
   }

   public void push (double a) {
      this.myStack.addLast(a);
      // TODO!!! Your code here!
   }

   public double pop() {
      if (stEmpty())
         throw new IndexOutOfBoundsException(" stack underflow");
      double tmp = this.myStack.getLast();
      this.myStack.removeLast();

      return tmp; // TODO!!! Your code here!
   } // pop

   public void op (String s) {
      double op2 = pop();
      double op1 = pop();
      if (s.equals("+"))
         push(op1 + op2);
      else if (s.equals("-"))
         push(op1 - op2);
      else if (s.equals("*"))
         push(op1 * op2);
      else if (s.equals("/"))
         push(op1 / op2);
      else
         throw new IllegalArgumentException("Invalid operation: " + s);

      // TODO!!!
   }

   public double tos() {
      if (stEmpty())
         throw new IndexOutOfBoundsException(" stack underflow");
      double af = this.myStack.get(this.myStack.size()-1);
      return af;
   }

   @Override
   public boolean equals (Object o) {
      boolean check = true;
      int s = ((DoubleStack) o).myStack.size();
      if (s!=this.myStack.size())
         return false;
      for (int i = 0; i<s;i++)
      {
         if (this.myStack.get(i)!=((DoubleStack) o).myStack.get(i))
         {
            check=false;
         }
      }
      return check; // TODO!!! Your code here!
   }

   @Override
   public String toString() {
      if (stEmpty())
         return "empty";
      String a = "";
      for (int i = 0; i<this.myStack.size();i++)
      {
         a = a + this.myStack.get(i)+" ";
      }

      return a; // TODO!!! Your code here!
   }

   public static double interpret (String pol) {
      double tot=0;
      DoubleStack myStack = new DoubleStack();
      String[] arrStr = pol.split(" ");
      if (arrStr.length==0)
      {
         System.out.println("Empty Expression");
         return 0;
      }
      int numD = 0;
      int numO = 0;
      for (int i = 0; i<arrStr.length; i++)
      {
         String mySt = arrStr[i];
         if (mySt!="*"||mySt!="/"||mySt!="+"||mySt!="-")
         {
            try {

               double val = Double.parseDouble(mySt);
               numD++;
            }
            catch (Exception e) {
               System.out.println("Illegal Symbol in "+mySt);
               numD--;
            }
         }
         else
            numO++;
      }
      if (numO!=0)
      {
         if (numD/numO!=2)
         {
            System.out.println("Too few numbers in "+pol);
         }
         else
         {
            for (int i = 0; i<arrStr.length; i++)
            {
               String mySt = arrStr[i];
               if (mySt!="*"||mySt!="/"||mySt!="+"||mySt!="-")
               {
                  try {
                     double val = Double.parseDouble(mySt);
                     myStack.push(val);
                  }
                  catch (Exception e) {
                  }
               }
               else
                  myStack.op(mySt);
            }
         }
      }


      return myStack.pop(); // TODO!!! Your code with full error handling here!
   }

}