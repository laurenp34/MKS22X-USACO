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

  private static int sumFour(int r, int c, int[][] land) {
    int[] moveR = {0,0,1,-1};
    int[] moveC = {1,-1,0,0};
    int sum = 0;

    if (land[r][c] < 0) return -1;

    for (int i=0;i<4;i++) {
      int newR = r+moveR[i];
      int newC = c+moveC[i];

      //chgeck to see if new move is in bounds
      try {
        if (land[newR][newC] > 0) sum += land[newR][newC];
      } catch (IndexOutOfBoundsException e) {

      }
    }

    return sum;

  }


  public static int silver(String filename) throws FileNotFoundException {
    int r = 0;
    int c = 0;
    int t = 0;
    int r1 = 0;
    int c1 = 0;
    int r2 = 0;
    int c2 = 0;

    int[][] land;


    File f = new File(filename);
    Scanner s = new Scanner(f);

    String lineOne = s.nextLine();
    String[] temp = lineOne.split(" ");

    r = Integer.parseInt(temp[0]);
    c = Integer.parseInt(temp[1]);
    t = Integer.parseInt(temp[2]);
    //System.out.println("r: "+r+"c: "+c);

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

    String lastLine = s.nextLine();
    temp = lastLine.split(" ");

    r1 = Integer.parseInt(temp[0]);
    c1 = Integer.parseInt(temp[1]);
    r2 = Integer.parseInt(temp[2]);
    c2 = Integer.parseInt(temp[3]);

    land[r1-1][c1-1] = 1;//placed 1 @ start.
    for (int i=0;i<t;i++) {
      //System.out.println(toString(land));
      int[][] teemp = new int[r][c]; // this will be the next iteration of the board.

      //update the temp board with next steps values.
      for (int i1=0;i1<r;i1++) {
        for (int i2=0;i2<c;i2++) {
          teemp[i1][i2] = sumFour(i1,i2,land);
        }
      }

      land = teemp;

    }

    //System.out.println("final: "+(r2-1)+", "+(c2-1));
    //System.out.println(toString(land));
    return land[r2-1][c2-1];


/*
    land[r1-1][c1-1] = 1;
    ArrayList<Integer> rows = new ArrayList<Integer>();
    ArrayList<Integer> cols = new ArrayList<Integer>();

    int[] moveR = {0,0,1,-1};
    int[] moveC = {1,-1,0,0};

    rows.add(r1-1);
    cols.add(c1-1);
    //outside array keeps track of moves taken (t)
    for (int i=1;i<t;i++) {
      ArrayList<Integer> rowsNow = new ArrayList<Integer>();
      ArrayList<Integer> colsNow = new ArrayList<Integer>();
      //inside loop keeps track of each placed move.
      for (int i2=0;i2<rows.size();i2++) {
        int curR = rows.get(i2);
        int curC = cols.get(i2);
        //this loop looks through all 4 move options.
        for (int i3=0;i3<4;i3++) {
          //check to see if next move is valid.
          int newR = curR + moveR[i3];
          int newC = curC + moveC[i3];
          if (land[newR][newC] > 0) {
            ooo
          }


        }
      }
      */

    //System.out.println(r1+" "+c1+r2+c2);
    //System.out.println(toString(land));


  }

  public static void main(String[] args) {
    /* SUM FOUR TEST CASES

    int[][] land = new int[4][4];
    int[] row1 = {0,0,1,2};
    int[] row2 = {1,-1,0,0};
    int[] row3 = {0,2,3,1};
    int[] row4 = {3,3,1,2};

    land[0] = row1;
    land[1] = row2;
    land[2] = row3;
    land[3] = row4;

    System.out.println(sumFour(0,0,land)+ " should be 1");
    System.out.println(sumFour(1,1,land)+ " should be -1");
    System.out.println(sumFour(1,2,land)+ " should be 4");
    System.out.println(sumFour(3,0,land)+ " should be 3");
    */

    try {
    System.out.println(silver("land.txt"));

  } catch (FileNotFoundException e) {
    System.out.println("file not found");
  }
  }

}
