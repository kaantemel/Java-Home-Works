import java.util.*;

/** This class represents fractions of form n/d where n and d are long integer
 * numbers. Basic operations and arithmetics for fractions are provided.
 */
public class Lfraction implements Comparable<Lfraction> {

   /** Main method. Different tests. */
   public static void main (String[] param) {
      // TODO!!! Your debugging tests here
   }

   // TODO!!! instance variables here

   /** Constructor.
    * @param a numerator
    * @param b denominator > 0
    */
   long numerator;
   long denominator;
   public Lfraction (long a, long b) {
      if (b<0)
      {
         a = -a;
         b = -b;
      }
      this.numerator = a;
      if(b!=0){
         this.denominator = b;
      }
      else
      {
         throw new RuntimeException("Denominator cannot be 0");
      }

      // TODO!!!
   }

   /** Public method to access the numerator field.
    * @return numerator
    */
   public long getNumerator() {
      return this.numerator; // TODO!!!
   }

   /** Public method to access the denominator field.
    * @return denominator
    */
   public long getDenominator() {
      return this.denominator; // TODO!!!
   }
   public static Lfraction smaller(Lfraction m)
   {
      long num = m.numerator;
      long dem = m.denominator;

      long div=0;

      for (long i = num;i>=1;i--)
      {
         if (num%i == 0 && dem%i==0) {
            div = i;
            break;
         }
      }
      if (div!=0)
      {
         num = num/div;
         dem = dem/div;
      }
      if (num==0)
      {
         dem = 1;
      }
      Lfraction small = new Lfraction(num,dem);
      return small;
   }

   /** Conversion to string.
    * @return string representation of the fraction
    */
   @Override
   public String toString() {
      String str1 = Long.toString(this.numerator);
      String str2 = Long.toString(this.denominator);
      String str3 = str1 + "/" + str2;
      return str3; // TODO!!!
   }

   /** Equality test.
    * @param m second fraction
    * @return true if fractions this and m are equal
    */
   @Override
   public boolean equals (Object m) {

      return this.compareTo((Lfraction) m)==0; // TODO!!!
   }

   /** Hashcode has to be the same for equal fractions and in general, different
    * for different fractions.
    * @return hashcode
    */
   @Override
   public int hashCode() {
      return java.util.Objects.hash(numerator,denominator);

   }

   /** Sum of fractions.
    * @param m second addend
    * @return this+m
    */
   public Lfraction plus (Lfraction m) {
      long num1 = m.numerator;
      long num2 = this.numerator;
      long dem1 = m.denominator;
      long dem2 = this.denominator;

      long par1= num1*dem2 + num2*dem1;
      long par2 = dem1*dem2;

      Lfraction myR = new Lfraction(par1,par2);
      myR = smaller(myR);
      return myR; // TODO!!!
   }

   /** Multiplication of fractions.
    * @param m second factor
    * @return this*m
    */
   public Lfraction times (Lfraction m) {
      Lfraction myR = new Lfraction(m.numerator*this.numerator, m.denominator*this.denominator);
      myR = smaller(myR);
      return myR; // TODO!!!
   }

   /** Inverse of the fraction. n/d becomes d/n.
    * @return inverse of this fraction: 1/this
    */
   public Lfraction inverse() {

      Lfraction myR = new Lfraction(this.denominator,this.numerator);
      return myR; // TODO!!!
   }

   /** Opposite of the fraction. n/d becomes -n/d.
    * @return opposite of this fraction: -this
    */
   public Lfraction opposite() {
      Lfraction myR = new Lfraction(-this.numerator,this.denominator);
      return myR; // TODO!!!
   }

   /** Difference of fractions.
    * @param m subtrahend
    * @return this-m
    */
   public Lfraction minus (Lfraction m) {
      long num1 = m.numerator;
      long num2 = this.numerator;
      long dem1 = m.denominator;
      long dem2 = this.denominator;

      long par1= num2*dem1 - num1*dem2;
      long par2 = dem1*dem2;

      Lfraction myR = new Lfraction(par1,par2);
      myR = smaller(myR);
      return myR; // TODO!!!
   }

   /** Quotient of fractions.
    * @param m divisor
    * @return this/m
    */
   public Lfraction divideBy (Lfraction m) {
      Lfraction myR = new Lfraction(this.numerator*m.denominator,this.denominator*m.numerator);
      myR = smaller(myR);
      return myR; // TODO!!!
   }

   /** Comparision of fractions.
    * @param m second fraction
    * @return -1 if this < m; 0 if this==m; 1 if this > m
    */
   @Override
   public int compareTo (Lfraction m)
   {
      long l1 = this.numerator*m.denominator;
      long l2 = m.numerator*this.denominator;
      int flag=0;
      if (l1>l2)
         flag=1;
      else if (l2>l1)
         flag=-1;
      return flag; // TODO!!!
   }

   /** Clone of the fraction.
    * @return new fraction equal to this
    */
   @Override
   public Object clone() throws CloneNotSupportedException {
      Lfraction myR = new Lfraction(this.numerator,this.denominator);
      return myR; // TODO!!!
   }

   /** Integer part of the (improper) fraction.
    * @return integer part of this fraction
    */
   public long integerPart() {
      long l1 = this.numerator/this.denominator;
      return l1; // TODO!!!
   }

   /** Extract fraction part of the (improper) fraction
    * (a proper fraction without the integer part).
    * @return fraction part of this fraction
    */
   public Lfraction fractionPart() {
      long l1 = this.numerator%this.denominator;
      Lfraction myR = new Lfraction(l1,this.denominator);
      return myR; // TODO!!!
   }

   /** Approximate value of the fraction.
    * @return real value of this fraction
    */
   public double toDouble() {
      double myR = (double)(this.numerator)/(double)(this.denominator);

      return myR; // TODO!!!
   }

   /** Double value f presented as a fraction with denominator d > 0.
    * @param f real number
    * @param d positive denominator for the result
    * @return f as an approximate fraction of form n/d
    */
   public static Lfraction toLfraction (double f, long d)
   {
      long l1 = (long) (Math.round(f*d));
      Lfraction myR = new Lfraction(l1,d);
      return myR; // TODO!!!
   }

   /** Conversion from string to the fraction. Accepts strings of form
    * that is defined by the toString method.
    * @param s string form (as produced by toString) of the fraction
    * @return fraction represented by s
    */
   public static Lfraction valueOf (String s)
   {
      String[] l = s.split("/",2);
      try{
         long ab = Long.parseLong(l[1]);
      }catch(NumberFormatException e){
         System.out.println(e);
      }
      Lfraction myR = new Lfraction(Long.parseLong(l[0]),Long.parseLong(l[1]));
      return myR; // TODO!!!
   }
}

