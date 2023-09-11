import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class tasan2_lab01 {
    public static void main(String[]args) throws IOException {
        if (args.length==0){
            System.out.println("NO argumant");
        }




        Scanner scanner=new Scanner((System.in));
        BufferedReader br=null;
        String line;

        System.out.println("file name");
        try{
            br=new BufferedReader(new FileReader("C:\\Users\\Emir\\untitled3\\"+scanner.next()));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try
        {
            int linecount=0;
            int tokencount=0;
            int integers =0;
            long sum=0;
            while((line=br.readLine())!=null) {
                linecount++;
                System.out.println(line);
                java.util.StringTokenizer st=new java.util.StringTokenizer(line);

                while(st.hasMoreTokens()){
                    tokencount++;
                    String token= st.nextToken();
                    try{
                        int v = Integer.parseInt(token);
                        integers++;
                        sum+=v;
                    }
                    catch(Exception e) {}
                    }
                }





        System.out.println("tokencount:"+tokencount);
        System.out.println("linecount:"+linecount);
        System.out.println("ınteger sum:"+sum);
        System.out.println("ınteger count:"+integers);
        }
        catch(IOException ex){
            Logger.getLogger(tasan2_lab01.class.getName()).log(Level.SEVERE,null,ex);
        }finally {
            System.exit(0);
        }


    }
}
