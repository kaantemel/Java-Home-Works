import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/** Container class to different classes, that makes the whole
 * set of classes one class formally.
 * @since 1.8
 */
public class GraphTask {

   /** Main method. */
   public static void main (String[] args) {
      GraphTask a = new GraphTask();
      a.run();
      //throw new RuntimeException ("Nothing implemented yet!"); // delete this
   }

   /** Actual main method to run examples and everything. */
   public void run() {
      long startTime = System.nanoTime();
      Graph g = new Graph ("G");
      g.createRandomSimpleGraph (2500, 25000);
      //System.out.println (g);
      ArrayList <Arc> myInt = g.longest(g);
      //System.out.println (myInt);
      //testRun1();
      System.out.println (myInt);
      long endTime   = System.nanoTime();
      long totalTime = endTime - startTime;
      float sec = (float)totalTime/1000000000;
      System.out.println (sec+" seconds for 2500 vertexes");
      // TODO!!! Your experiments here
   }

   public void testRun1() {
      Graph g = new Graph ("G");

      Vertex v3 = new Vertex("v3");
      Vertex v2 = new Vertex("v2",v3,null);
      Vertex v1 = new Vertex("v1",v2,null);

      Arc av1_v2 = new Arc("av1_v2");
      av1_v2.target = v2;
      av1_v2.startv = v1;
      av1_v2.dist = 3;

      Arc av1_v3 = new Arc("av1_v3");
      av1_v3.target = v3;
      av1_v3.startv = v1;
      av1_v3.dist = 1;

      av1_v2.next = av1_v3;
      v1.first = av1_v2;

      Arc av2_v1 = new Arc("av2_v1");
      av2_v1.target = v1;
      av2_v1.startv = v2;
      av2_v1.dist = 3;

      v2.first = av2_v1;

      Arc av3_v1 = new Arc("av3_v1");
      av3_v1.target = v1;
      av3_v1.startv = v3;
      av3_v1.dist = 1;

      v3.first = av3_v1;

      g.first = v1;
      ArrayList <Arc> test1 = g.longest(g);
      System.out.println (g);
      System.out.println (test1);
      // TODO!!! Your experiments here
   }

   // TODO!!! add javadoc relevant to your problem
   class Vertex {

      private String id;
      private Vertex next;
      private Arc first;
      private int info = 0;
      private boolean known = false;
      private int distance = -1;
      private Arc path;
      // You can add more fields, if needed

      Vertex (String s, Vertex v, Arc e) {
         id = s;
         next = v;
         first = e;
      }

      Vertex (String s) {
         this (s, null, null);
      }

      @Override
      public String toString() {
         return id;
      }

      // TODO!!! Your Vertex methods here!
   }


   /** Arc represents one arrow in the graph. Two-directional edges are
    * represented by two Arc objects (for both directions).
    */
   class Arc {

      private String id;
      private Vertex target;
      private Vertex startv;
      private Arc next;
      private int info;
      private int dist = 1;
      // You can add more fields, if needed

      Arc (String s, Vertex v, Arc a) {
         id = s;
         target = v;
         next = a;
      }

      Arc (String s) {
         this (s, null, null);
      }

      @Override
      public String toString() {
         return id;
      }

      // TODO!!! Your Arc methods here!
   } 


   /** This header represents a graph.
    */ 
   class Graph {

      private String id;
      private Vertex first;
      private int info = 0;
      // You can add more fields, if needed

      Graph (String s, Vertex v) {
         id = s;
         first = v;
      }

      Graph (String s) {
         this (s, null);
      }

      @Override
      public String toString() {
         String nl = System.getProperty ("line.separator");
         StringBuffer sb = new StringBuffer (nl);
         sb.append (id);
         sb.append (nl);
         Vertex v = first;
         while (v != null) {
            sb.append (v.toString());
            sb.append (" -->");
            Arc a = v.first;
            while (a != null) {
               sb.append (" ");
               sb.append (a.toString());
               sb.append (" weight: ");
               sb.append(a.dist);
               sb.append (" ");
               sb.append (" (");
               sb.append (v.toString());
               sb.append ("->");
               sb.append (a.target.toString());
               sb.append (")");
               a = a.next;
            }
            sb.append (nl);
            v = v.next;
         }
         return sb.toString();
      }

      public Vertex createVertex (String vid) {
         Vertex res = new Vertex (vid);
         res.next = first;
         first = res;
         return res;
      }

      public Arc createArc (String aid, Vertex from, Vertex to) {
         Arc res = new Arc (aid);
         res.next = from.first;
         from.first = res;
         res.target = to;
         res.startv = from;
         Arc tempA = to.first;
         boolean flag = false;
         while(tempA!=null)
         {
            if (tempA.target == from)
            {
               flag = true;
               break;
            }
            tempA = tempA.next;
         }
         if (flag==true)
         {
            res.dist = tempA.dist;
         }
         else
         {
            int g = (int)(Math.random()*6);
            while(g==0)
            {
               g = (int)(Math.random()*6);
            }
            res.dist = g;
         }
         return res;
      }

      /**
       * Create a connected undirected random tree with n vertices.
       * Each new vertex is connected to some random existing vertex.
       * @param n number of vertices added to this graph
       */
      public void createRandomTree (int n) {
         if (n <= 0)
            return;
         Vertex[] varray = new Vertex [n];
         for (int i = 0; i < n; i++) {
            varray [i] = createVertex ("v" + String.valueOf(n-i));
            if (i > 0) {
               int vnr = (int)(Math.random()*i);
               createArc ("a" + varray [vnr].toString() + "_"
                  + varray [i].toString(), varray [vnr], varray [i]);
               createArc ("a" + varray [i].toString() + "_"
                  + varray [vnr].toString(), varray [i], varray [vnr]);
            } else {}
         }
      }

      /**
       * Create an adjacency matrix of this graph.
       * Side effect: corrupts info fields in the graph
       * @return adjacency matrix
       */
      public int[][] createAdjMatrix() {
         info = 0;
         Vertex v = first;
         while (v != null) {
            v.info = info++;
            v = v.next;
         }
         int[][] res = new int [info][info];
         v = first;
         while (v != null) {
            int i = v.info;
            Arc a = v.first;
            while (a != null) {
               int j = a.target.info;
               res [i][j]++;
               a = a.next;
            }
            v = v.next;
         }
         return res;
      }

      /**
       * Create a connected simple (undirected, no loops, no multiple
       * arcs) random graph with n vertices and m edges.
       * @param n number of vertices
       * @param m number of edges
       */
      public void createRandomSimpleGraph (int n, int m) {
         if (n <= 0)
            return;
         if (n > 2500)
            throw new IllegalArgumentException ("Too many vertices: " + n);
         if (m < n-1 || m > n*(n-1)/2)
            throw new IllegalArgumentException 
               ("Impossible number of edges: " + m);
         first = null;
         createRandomTree (n);       // n-1 edges created here
         Vertex[] vert = new Vertex [n];
         Vertex v = first;
         int c = 0;
         while (v != null) {
            vert[c++] = v;
            v = v.next;
         }
         int[][] connected = createAdjMatrix();
         int edgeCount = m - n + 1;  // remaining edges
         while (edgeCount > 0) {
            int i = (int)(Math.random()*n);  // random source
            int j = (int)(Math.random()*n);  // random target
            if (i==j) 
               continue;  // no loops
            if (connected [i][j] != 0 || connected [j][i] != 0) 
               continue;  // no multiple edges
            Vertex vi = vert [i];
            Vertex vj = vert [j];
            createArc ("a" + vi.toString() + "_" + vj.toString(), vi, vj);
            connected [i][j] = 1;
            createArc ("a" + vj.toString() + "_" + vi.toString(), vj, vi);
            connected [j][i] = 1;
            edgeCount--;  // a new edge happily created
         }
      }
      public ArrayList<Arc> longest(Graph a)
      {
         Vertex vp = a.first;
         ArrayList<Integer> longestlen = new ArrayList<>();
         ArrayList<Vertex> startPointV = new ArrayList<>();
         ArrayList<Vertex> endPointV = new ArrayList<>();
         ArrayList<ArrayList<Arc>> mypath = new ArrayList<>();
         while(vp != null)
         {

            vp.distance = 0;
            vp.known = true;
            Vertex hold = vp;
            while(hold!=null)
            {
               Arc myArcT = hold.first;
               Arc myRealArc = hold.first;
               int min2 = myArcT.dist;
               while(myArcT!=null)
               {
                  if (!myArcT.target.known&&myArcT.dist<=min2)
                  {
                     myRealArc = myArcT;
                     min2 = myArcT.dist;
                  }
                  myArcT = myArcT.next;
               }
               hold = myRealArc.target;
               if (hold.known==true)
                  break;
               hold.known = true;
               if(hold.distance==-1)
               {
                  hold.distance = myRealArc.dist;
                  hold.path = myRealArc;
                  longestlen.add(hold.distance);
                  startPointV.add(vp);
                  endPointV.add(hold);
               }
               Arc ArcP = hold.first;
               int min=ArcP.dist;
               while(ArcP != null)
               {
                  if (!ArcP.target.known)
                  {
                     if (ArcP.target.distance==-1||hold.distance+ArcP.dist<ArcP.target.distance)
                     {
                        ArcP.target.distance = hold.distance+ArcP.dist;
                        ArcP.target.path = ArcP;
                     }
                  }
                  ArcP = ArcP.next;
               }
            }
            int mytry = 0;
            Vertex outV = null;
            Vertex lloop = a.first;

            while(lloop!=null)
            {
               ArrayList<Arc> myarcs = new ArrayList<>();
               if (lloop.distance>mytry)
               {
                  mytry = lloop.distance;
                  outV = lloop;
                  Arc mya = outV.path;
                  while (mya!=null)
                  {
                     myarcs.add(mya);
                     mya = mya.startv.path;
                  }
                  //mypath.add(myarcs);
               }
               lloop = lloop.next;
            }

            longestlen.add(mytry);
            startPointV.add(vp);
            endPointV.add(outV);
            ArrayList<Arc> MyArcList = new ArrayList<>();
            Arc myTempV = outV.path;
            while(myTempV != null)
            {
               MyArcList.add(myTempV);
               myTempV = myTempV.startv.path;
            }
            mypath.add(MyArcList);
            Vertex nlloop = a.first;
            while(nlloop!=null)
            {
               nlloop.distance=-1;
               nlloop.known = false;
               nlloop.path = null;
               nlloop = nlloop.next;
            }
            vp = vp.next;
         }
         int b = Collections.max(longestlen);
         int c = longestlen.indexOf(b);
         String returner = "Distance: "+Collections.max(longestlen)+" Start: "+startPointV.get(c)+" End: "+ endPointV.get(c);
         String asda = "";
         int g = 0;
         for (int i = 0; i<mypath.size();i++)
         {

            int abc = mypath.get(i).size()-1;

            if (startPointV.get(c)==mypath.get(i).get(abc).startv&&endPointV.get(c)==mypath.get(i).get(0).target)
            {
               for (int y = mypath.get(i).size()-1; y>=0;y--)
               {
                  asda = asda+"\n";
                  asda = asda + mypath.get(i).get(y).id + " ";
                  g = i;
               }
            }
         }
         returner = returner+asda;
         return mypath.get(g);
      }
      // TODO!!! Your Graph methods here! Probably your solution belongs here.
   }
} 

