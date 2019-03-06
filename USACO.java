import java.io.*;
import java.util.*;


public class USACO {

  public static int bronze(String filename) {
    int r = 0;
    int c = 0;
    int e = 0;
    int n = 0;

    try {
      File f = new File(filename);
      Scanner s = new Scanner(f);

      String lineOne = s.nextLine();
      String[] temp = lineOne.split(" ");

      r = Integer.parseInt(temp[0]);
      c = Integer.parseInt(temp[1]);
      e = Integer.parseInt(temp[2]);
      n = Integer.parseInt(temp[3]);
    }catch (FileNotFoundException error) {
      System.out.println("File not found: "+filename);
    }

    System.out.println(r+" "+c+" "+e+" "+n);
    return 0;


  }

  public static int silver(String filename) {
    return 0;
  }

  public static void main(String[] args) {
    bronze("lake.txt");
  }

}
