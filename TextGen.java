import java.io.File;
import java.io.FileWriter; 
import java.io.FileNotFoundException;
import java.io.IOException;  
import java.util.Scanner;
import java.util.Arrays;

public class TextGen{
    public static int lineCount;
    static String[][] textGen = new String[3][4];
    
    public static void main(String[] args){
        lineCounter();
        try {
        File text = new File("testrun.txt");
        Scanner reader = new Scanner(text);
            int i = 0;
            do {
                String[] lineArray = reader.nextLine().split("@ ");

                textGen[i][0] = lineArray[0];
                textGen[i][1] = lineArray[1];
                textGen[i][2] = lineArray[2];

                switch (textGen[i][0]){
                    case "You":
                        textGen[i][3] = "blue";
                        break;
                    case "Mom":
                        textGen[i][3] = "#b32995";
                        break;
                    case "Consciousness":
                        textGen[i][3] = "gray";
                        break;
                }

                i++;
            }
            while (reader.hasNextLine());
            
            reader.close();
        }
        catch (FileNotFoundException ex){
            System.out.println("WE'RE FUCKED");
        }
        System.out.println(Arrays.deepToString(textGen));
        createFile();
    }

    public static void lineCounter(){
        try{
        File text1 = new File("testrun.txt");
        Scanner reader1 = new Scanner(text1);
        while (reader1.hasNextLine()){
            String data = reader1.nextLine();
            System.out.println(data);
            lineCount++;
        }
        reader1.close();
        System.out.println("File contains: " + lineCount + " lines");
        }
        catch (FileNotFoundException ex){
            System.out.println("WE'RE FUCKED PART 2");
        }
    }

    public static void createFile(){
        try{
            File output = new File("dialouge.txt");
            if (output.createNewFile()){
                System.out.println("File created: " + output.getName());
            }
            else {
                System.out.println("File already exists.");
            }
        }
        catch (IOException e){
            System.out.println("WE'RE FUCKED PART 3");
        }
        try{
            FileWriter dialouge = new FileWriter("dialouge.txt", true);
            int i = 0;
            do {
                switch (textGen[i][0]){
                    case "You":
                        dialouge.write("execute as @s[tag=male,tag=next,scores={scene=" + textGen[i][2] + "}] run tellraw @s [\"\",{\"text\":\"\\n\"},{\"text\":\"<" + textGen[i][0] + ">\",\"color\":\"" + textGen[i][3] + "\"},{\"text\":\" " + textGen[i][1] + "\"},{\"text\":\" [Next]\",\"color\":\"green\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/scoreboard players add @s scene 1\"}}]\n");
                        break;
                    default:
                        dialouge.write("execute as @s[tag=next,scores={scene=" + textGen[i][2] + "}] run tellraw @s [\"\",{\"text\":\"\\n\"},{\"text\":\"<" + textGen[i][0] + ">\",\"color\":\"" + textGen[i][3] + "\"},{\"text\":\" " + textGen[i][1] + "\"},{\"text\":\" [Next]\",\"color\":\"green\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/scoreboard players add @s scene 1\"}}]\n");
                        break;    
                }
                i++;
            }
            while (i < lineCount);

            dialouge.close();
            System.out.println("Successfully wrote to the file.");
        }
        catch (IOException e){
            System.out.println("WE'RE FUCKED PART 4");
        }
    }
}