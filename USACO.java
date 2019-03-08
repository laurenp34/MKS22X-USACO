import java.io.*;
import java.util.*;


public class USACO {

  public static String toString(int[][] board) {
    String result = "";
    for (int[] row: board) {
      for (int r: row) {
        result += r;
        result += " ";
      }
      result += "\n";
    }
    return result;
  }

  public static void stomp(int r_s, int c_s, int depth, int[][] land) {
    //findthe 9 squares that are occupied, find the largest value.
    int largestR=0;
    int largestC=0;
    int largest=0;
    for (int i=r_s-1;i<r_s+2;i++) {
      //System.out.println("row: "+i);
      for (int i2=c_s-1;i2<c_s+2;i2++) {
        //System.out.println("col: "+i2);

        if (land[i][i2] > largest) {
          //System.out.println(land[i][i2]);
          largestR = i;
          largestC = i2;
          largest = land[i][i2];
        }
      }
    }
    //System.out.println("largest: "+largest);
    //stomp down
    int neww = largest - depth;
    //System.out.println("neww: "+neww);
    for (int i=r_s-1;i<r_s+2;i++) {
      for (int i2=c_s-1;i2<c_s+2;i2++) {
        //System.out.println(i+", "+i2+": "+land[i][i2]);
        if (land[i][i2] > neww) {
          //System.out.println("changing "+land[i][i2]+"to "+neww+"@ "+i+","+i2);
          land[i][i2] = neww;
        }
      }
    }
  }

  public static int bronze(String filename) throws FileNotFoundException {
    int r = 0;
    int c = 0;
    int e = 0;
    int n = 0;

    int[][] land;


    File f = new File(filename);
    Scanner s = new Scanner(f);

    String lineOne = s.nextLine();
    String[] temp = lineOne.split(" ");

    r = Integer.parseInt(temp[0]);
    c = Integer.parseInt(temp[1]);
    e = Integer.parseInt(temp[2]);
    n = Integer.parseInt(temp[3]);

    land = new int[r][c];

    for (int i=0;i<r;i++) {
      String line = s.nextLine();
      temp = line.split(" ");
      //System.out.println(Arrays.toString(temp));
      for (int i2=0;i2<c;i2++) {
        land[i][i2] = Integer.parseInt(temp[i2]);
      }
    }

    while (s.hasNext()) {
      String line = s.nextLine();
      temp = line.split(" ");
      int r_s=0;
      int c_s=0;
      int d_s = 0;
      r_s = Integer.parseInt(temp[0]);
      c_s = Integer.parseInt(temp[1]);
      d_s = Integer.parseInt(temp[2]);
      //System.out.println(r_s+" "+c_s+" "+d_s);

      stomp(r_s,c_s,d_s,land);
    }
    //System.out.println(toString(land));

    int result = 0;
    for (int[] row: land) {
      for (int i: row) {
        if (e > i) {
          result += (e-i);
        }
      }
    }
    return result * 72 * 72;

    /*
    System.out.println(r+" "+c+" "+e+" "+n);
    System.out.println(toString(land));
    System.out.println(toString(land));
    */


  }

  public static int silver(String filename) throws FileNotFoundException {
    int r = 0;
    int c = 0;
    int t = 0;

    int[][] land;


    File f = new File(filename);
    Scanner s = new Scanner(f);

    String lineOne = s.nextLine();
    String[] temp = lineOne.split(" ");

    r = Integer.parseInt(temp[0]);
    c = Integer.parseInt(temp[1]);
    t = Integer.parseInt(temp[2]);
    System.out.println("r: "+r+"c: "+c);

    land = new int[r][c];

    for (int i=0;i<r;i++) {
      String line = s.nextLine();
      System.out.println(line);
      //System.out.println(Arrays.toString(temp));
      for (int i2=0;i2<c;i2++) {
        if (line.charAt(i2) == '.') land[i][i2] = 0;
        if (line.charAt(i2) == '*') land[i][i2] = -1;
      }
    }
    System.out.println(toString(land));

    return 0;
  }

  public static void main(String[] args) {
    try {
    System.out.println(silver("land.txt"));

  } catch (FileNotFoundException e) {
    System.out.println("file not found");
  }
  }

}
