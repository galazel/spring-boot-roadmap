import java.io.*;

public class Main {
    public static void main(String[] args)
    {
        try(FileInputStream fis = new FileInputStream("sample.txt"))
        {
            int lol;
            while((lol = fis.read()) != -1)
            {
                System.out.print((char)lol);
            }
        }catch(IOException e){
            System.out.println("There is something wrong with the file: "+e.getMessage());
        }

        try(FileOutputStream fos = new FileOutputStream("sample.txt"))
        {
            fos.write("Hello World!dssd".getBytes());
        }catch(IOException e){
            System.out.println("There is something wrong with the file: "+e.getMessage());
        }

    }

}
