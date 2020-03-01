import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Problem 1421: Archery
 * Determine whether a line can intersect all line segments
 * @author Nathan Rice
 */
public class Archery
{
  public static void main(String[] args) throws FileNotFoundException
  {
    File inputFile = new File("input.txt");
    Scanner in = new Scanner(inputFile);

    int testCases = in.nextInt();
    String answer = "";
    System.out.println("There are "+testCases+" test cases"); //SELFHELP
    int archerLine;
    int numberOfPapers;
    // String[] thisPaper = new String[3];
    for(int tc = 0; tc < testCases; tc++)
    {
      System.out.println("Test case " + tc + ":"); //SELFHELP
      archerLine = in.nextInt();
      System.out.println("Archer line width is "+archerLine+"."); //SELFHELP
      numberOfPapers = in.nextInt();
      System.out.println("There are "+numberOfPapers+" papers."); //SELFHELP
      int[] d = new int[numberOfPapers];
      int[] l = new int[numberOfPapers];
      int[] r = new int[numberOfPapers];
      for(int paper = 0; paper < numberOfPapers; paper++)
      {
        // line = br.readLine();
        // thisPaper = line.split(" ");
        d[paper] = in.nextInt();
        l[paper] = in.nextInt();
        r[paper] = in.nextInt();
      }

      // SELFHELP
      for(int paper = 0; paper < numberOfPapers; paper++)
      {
        System.out.println("Paper "+paper+":\n"+d[paper]+ " "+l[paper]+" "+r[paper]);
      }
      // SELFHELP

      //Sort d from greatest to least.
      //-Whatever changes you make to d, do to l and r as well.
      for(int currentIndex = 0; currentIndex < d.length - 1; currentIndex++)
      {
        int maxIndex = currentIndex;
        //maxVal = d[maxIndex];
        for(int testIndex = currentIndex + 1; testIndex < d.length; testIndex++)
        {
          if(d[testIndex] > d[maxIndex])
          {
            maxIndex = testIndex;
          }
        }
        //Switch all 3 arrays
        int temp = d[currentIndex];
        d[currentIndex] = d[maxIndex];
        d[maxIndex] = temp;
        temp = l[currentIndex];
        l[currentIndex] = l[maxIndex];
        l[maxIndex] = temp;
        temp = r[currentIndex];
        r[currentIndex] = r[maxIndex];
        r[maxIndex] = temp;
      }

      // SELFHELP
      System.out.println("After sorting the papers:");
      for(int paper = 0; paper < numberOfPapers; paper++)
      {
        System.out.println("Paper "+paper+":\n"+d[paper]+ " "+l[paper]+" "+r[paper]);
      }
      // SELFHELP

      //Make a line using the top two papers' left bounds
      int rightX = d[0];
      int rightY = l[0];
      int leftX = d[1];
      int leftY = l[1];

      int numM = rightY - leftY;
      int denM = rightX - leftX;

      int numB = denM*rightY - numM*rightX;
      //int denB = denM;

      //Go through the rest of the papers and the archer line, making the best line you can
      double interceptY;
      for(int paper = 2; paper < numberOfPapers; paper++)
      {
        //Adjust line if necessary

        //Determine Y intercept of this paper
        interceptY = ((double)(numM*d[paper] + numB)) / denM;

        if(interceptY < l[paper])
        {
          //Paper is above current line
          System.out.println("Paper's above");//SELFHELP
          leftX = d[paper];
          leftY = l[paper];
        }
        else if(interceptY > r[paper])
        {
          //Paper is below current line
          System.out.println("Paper's below");//SELFHELP
          rightX = leftX;
          rightY = leftY;
          leftX = d[paper];
          leftY = r[paper];
        }

        //Update line
        numM = rightY - leftY;
        denM = rightX - leftX;

        numB = denM*rightY - numM*rightX;
        //denB = denM;
      }

      //Go through archer line
      //Adjust line if necessary

      //Determine Y intercept of archerLine
      interceptY = ((double)numB) / denM;

      if(interceptY < 0)
      {
        //Archer line is above current line
        leftX = 0;
        leftY = 0;
      }
      else if(interceptY > archerLine)
      {
        //Archer line is below current line
        rightX = leftX;
        rightY = leftY;
        leftX = 0;
        leftY = archerLine;
      }

      //Update line
      numM = rightY - leftY;
      denM = rightX - leftX;

      numB = denM*rightY - numM*rightX;
      //denB = denM;

      // SELFHELP
      System.out.println("Final line:");
      System.out.println("y = (" + numM + "/"+denM+")x + ("+numB+"/"+denM+")");
      // SELFHELP

      //Test to see if line goes through all the papers
      boolean fails = false;
      for(int paper = 0; paper < numberOfPapers && !fails; paper++)
      {
        //Determine Y intercept
        interceptY = ((double)(numM*d[paper] + numB)) / denM;

        // SELFHELP
        System.out.println("y = " + interceptY + " at x = " + d[paper]);
        System.out.println("Is " + interceptY + " less than " + l[paper] +
        " or greater than " + r[paper] + "?");
        // SELFHELP
        if(interceptY < l[paper] || interceptY > r[paper])
        {
          System.out.println("Yes, therefore you can't hit all the papers.");//SELFHELP
          fails = true;
        }
        //SELFHELP
        else
        {
          System.out.println("No, so this paper gets hit!");
        }
        //SELFHELP
      }
      //We do not need to test archer line; it is the last line we adjusted to.

      if( fails )
      {
        answer+="NO\n";
      }
      else
      {
        answer+="YES\n";
      }
    }

    System.out.println(answer);
  }
}
