import java.io.*;
import java.util.*;


public class USACO {

  public static int bronze(String filename) {
    int r = 0;
    int c = 0;
    int e = 0;
    int n = 0;

    int[][] land;

    try {
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

      //System.out.println(Arrays.deepToString(land));
    }catch (FileNotFoundException error) {
      System.out.println("File not found: "+filename);
    }

    System.out.println(r+" "+c+" "+e+" "+n);
    //System.out.println(Arrays.deepToString(land));
    return 0;


  }

  public static int silver(String filename) {
    return 0;
  }

  public static void main(String[] args) {
    bronze("lake.txt");
  }

}
