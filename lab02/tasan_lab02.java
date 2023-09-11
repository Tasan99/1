public class tasan_lab02 {

        public static void main(String[] args)
        {
            // put some code here to check for three commandline arguments
            if(args.length !=3)
            {
                System.out.println("Error");
                System.exit(0);
            }


            // puts some code here to check that the first commandline argument starts with "b" or "t"
            if(! (args[0].startsWith("b") && args[0].startsWith("t") )){
                System.exit(0);
            }
            if(args[0].startsWith("b")){
                convertBinaryToText(args[1], args[2]);
            }
            else{
                convertTextToBinary(args[1], args[2]);
            }
        }

        private static void convertBinaryToText(String inputFilename, String outputFilename)
        {
            System.out.println("convertBinaryToText");

            try{
                java.io.FileInputStream fis = new java.io.FileInputStream(inputFilename);
                java.io.BufferedInputStream bfıs = new java.io.BufferedInputStream(fis);

                java.io.FileWriter fw = new java.io.FileWriter(outputFilename);
                java.io.PrintWriter pw = new java.io.PrintWriter(new java.io.BufferedWriter(fw));


                byte[] byt = new byte[1];
                String str=null;
                while (bfıs.read(byt)> 0)
                {
                    str = new String(byt);
                    pw.print(str);
                }

                pw.close();
                bfıs.close();
                // put your code to read a binary file and output it as a text file
            }
            catch(Exception e)
            {
                System.out.println(e.toString());
                System.exit(0);
            }
        }

        private static void convertTextToBinary(String inputFilename, String outputFilename)
        {
            System.out.println("convertTextToBinary");
            try
            {
                java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(new java.io.FileInputStream(inputFilename)));
                java.io.DataOutputStream data = new java.io.DataOutputStream(new java.io.FileOutputStream(outputFilename));

                String lines;
                java.util.ArrayList<String> inputlines = new java.util.ArrayList<>();

                while((lines = br.readLine()) != null)
                {
                    inputlines.add(lines);
                    byte[] linesBytes;
                    linesBytes = lines.getBytes();
                    data.write(linesBytes);
                    data.write(System.lineSeparator().getBytes());

                }
                data.close();
                br.close();
                // put your code to read a text file and output it as a binary file
            }
            catch(Exception e)
            {
                System.out.println(e.toString());
                System.exit(0);
            }
        }
    }

