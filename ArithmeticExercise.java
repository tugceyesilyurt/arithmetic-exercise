/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication6;

/**
 *
 * @author u
 */
import java.util.Scanner;

public class ArithmeticExercise {

    public static void main(String[] args) {
        menu();
        run();
    }
    
    public static void menu(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("*****Welcome to Arithmetic Exercise Program*****");
        System.out.println("Please press any key to start...");
        String str = scanner.nextLine();
    }
    
    public static void desicion(String q){
        if(q.equals("q")){
            System.exit(0);
        }
        else{
            run();
        }
    }
    
    public static void run(){
        Scanner scanner = new Scanner(System.in);
        
        double passingTime = 0;
        int level = 1;
        int point = 0;
        int count = 0;
        //BaÅŸlangÄ±Ã§ zamanÄ±
        long startTime = System.currentTimeMillis();
        System.out.println(startTime);
        boolean ans = true;
        
        while((passingTime < 90 && level <= 2) && ans == true){
            
            System.out.println("**Level : " + level + "***");
            String question = generateQuestion(level);
            System.out.println("Question: " + question);
            System.out.print("Answer: ");
            int userAnswer = scanner.nextInt();
            int answer = answer(question, level);
            
            if(userAnswer != answer){
                System.out.println("WRONG!");
                System.out.println("Answer: " + answer + "\n");
                System.out.println("**Point : " + point + "***\n");
                ans = false;
            }
            else if(userAnswer == answer){
                count++;
                point += 10;
                System.out.println("CORRECT");
            }
            
            if(count == 5){
                count = 0;
                startTime += 5;
                level++;
            }
            
            if(point == 30){
                message(point/10);
            }
            else if(point == 60){
                message(point/10);
            }
            else if(point == 90){
                message(point/10);
            }
            
            long endTime = System.currentTimeMillis();
            passingTime = passingTime(startTime, endTime);
            
            
            if(passingTime > 90){
                System.out.println("You are as fast as a turtle!");
                System.out.println("Correct Answer: " + count);
                System.out.println("Total Question: " + count);
                System.out.println("Total Time: " + passingTime);
                System.out.println("Sorry!!! You cannot advance to the next level !");
                scanner.nextLine();
                System.out.println("Input 'q' to quit or any key to restartâ€¦");
                String q = scanner.nextLine();
                desicion(q);
                break;
            }
        }
        
        //BitiÅŸ zamanÄ±
        long endTime = System.currentTimeMillis();
        
        passingTime = passingTime(startTime, endTime);
        System.out.println("Passing Time: " + passingTime);
        if(point == 100){
            System.out.println("**Point : " + point + "***\n");
            System.out.println("Correct Answer: " + count);
            System.out.println("Total Question: " + count);
            System.out.println("Total Time: " + passingTime);
            System.out.println("Input 'q' to quit or any key to restartâ€¦");
            scanner.nextLine();
            String q = scanner.nextLine();
            desicion(q);
        }
    }
    
    //BaÅŸlama zamanÄ±ndan bitiÅŸ zamanÄ±nÄ± Ã§Ä±kararak geÃ§en sÃ¼reyi buluyoruz
    public static double passingTime(long startTime, long endTime){
        //GeÃ§en sÃ¼re 
        long passingTime = endTime - startTime;
        
        //Milisaniyeyi saniyeye Ã§evirmek iÃ§in 1000'e bÃ¶ldÃ¼k
        double time = (double)(passingTime/1000);
        return time;
    }
    
    //Ãœretilen sorunun cevabÄ±nÄ± buluyoruz
    public static int answer(String question, int level){
        String[] quest = new String[question.length()];
        int conclusion = 0;
        int operatorCount = 0;
        
        //Sorunun opertÃ¶rlerini ve operantlarÄ±nÄ± diziye atÄ±yoruz
        for (int i = 0; i < quest.length; i++) {
            //chardan Stringe Ã§eviriyoruz
            quest[i] = String.valueOf(question.charAt(i));
            
            if(quest[i].equals("+") || quest[i].equals("-") || quest[i].equals("*") || quest[i].equals("/")){
                operatorCount++;
            }
        }
        
        int[] operant = new int[operatorCount+1];
        String[] operator = new String[operatorCount];
        
        operatorCount = 0;
        int operantCount = 0;
        
        String str = "";
        
        for (int i = 0; i < quest.length; i++) {
            if(!(quest[i].equals("+") || quest[i].equals("-") || quest[i].equals("*") || quest[i].equals("/"))){
                str += quest[i]; 
            }
            else{
                operator[operatorCount] = quest[i];
                operatorCount++;
                operant[operantCount] = Integer.parseInt(str);
                operantCount++;
                str = "";
            }
        }
        operant[operantCount] = Integer.parseInt(str);
        
        switch (level) {
            case 1:
                switch (operator[0]) {
                    case "*":
                        conclusion += operant[0] * operant[1];
                        break;
                    case "/":
                        conclusion += operant[0] / operant[1];
                        break;
                    case "+":
                        conclusion += operant[0] + operant[1];
                        break;
                    case "-":
                        conclusion += operant[0] - operant[1];
                        break;
                    default:
                        throw new AssertionError();
                }
                break;
            case 2:
                switch (operator[0]) {
                    case "*":
                        conclusion += operant[0] * operant[1];
                        switch (operator[1]) {
                            case "*":
                                conclusion *= operant[2];
                                break;
                            case "/":
                                conclusion /= operant[2];
                                break;
                            case "+":
                                conclusion += operant[2];
                                break;
                            case "-":
                                conclusion -= operant[2];
                                break;
                            default:
                                throw new AssertionError();
                        }
                        break;
                    case "/":
                        conclusion += operant[0] / operant[1];
                        switch (operator[1]) {
                            case "*":
                                conclusion *= operant[2];
                                break;
                            case "/":
                                conclusion /= operant[2];
                                break;
                            case "+":
                                conclusion += operant[2];
                                break;
                            case "-":
                                conclusion -= operant[2];
                                break;
                            default:
                                throw new AssertionError();
                        }
                        break;
                    case "+":
                        if(!operator[1].equals("*") || !operator[1].equals("/")){
                            conclusion += operant[0] + operant[1];
                            switch (operator[1]) {
                                case "*":
                                    conclusion *= operant[2];
                                    break;
                                case "/":
                                    conclusion /= operant[2];
                                    break;
                                case "+":
                                    conclusion += operant[2];
                                    break;
                                case "-":
                                    conclusion -= operant[2];
                                    break;
                                default:
                                    throw new AssertionError();
                            }
                        }
                        break;
                    case "-":
                        if(!operator[1].equals("*") || !operator[1].equals("/")){
                            conclusion += operant[0] - operant[1];
                            switch (operator[1]) {
                                case "*":
                                    conclusion *= operant[2];
                                    break;
                                case "/":
                                    conclusion /= operant[2];
                                    break;
                                case "+":
                                    conclusion += operant[2];
                                    break;
                                case "-":
                                    conclusion -= operant[2];
                                    break;
                                default:
                                    throw new AssertionError();
                            }
                        }
                            
                        break;                        
                    default:
                        throw new AssertionError();
                }
        }
        
        return conclusion;
    }
    
    public static void message(int count){
        
        //DÃ¼zgÃ¼n Ã§alÄ±ÅŸmÄ±yor
        switch (count) {
            case 3:
                System.out.println("*** good ***");
                for(int i=0;i<count;i++){
                       
                    for (int x = 0; x < count; x++) {
                        for(int j=0;j<i;j++){
                            System.out.print(" ");
                        }
                        for(int k=(2*i+1); k <= count; k++){
                            System.out.print("*");
                        }
                        for(int j=0;j<i;j++){
                            System.out.print(" ");
                        }
                        System.out.print(" ");
                    }
                    System.out.println();
                }
                break;
            case 6:
                System.out.println("******      very good      ******");
                for(int i=0;i<count;i++){
                       
                    for (int x = 0; x < count; x++) {
                        for(int j=0;j<i;j++){
                            System.out.print(" ");
                        }
                        for(int k=(2*i+1); k <= count; k++){
                            System.out.print("*");
                        }
                        for(int j=0;j<i;j++){
                            System.out.print(" ");
                        }
                        System.out.print(" ");
                    }
                    System.out.println();
                }
                break;
            case 9:
                System.out.println("*********                               excellent                               *********");
                for(int i=0;i<count;i++){
                       
                    for (int x = 0; x < count; x++) {
                        for(int j=0;j<i;j++){
                            System.out.print(" ");
                        }
                        for(int k=(2*i+1); k <= count; k++){
                            System.out.print("*");
                        }
                        for(int j=0;j<i;j++){
                            System.out.print(" ");
                        }
                        System.out.print(" ");
                    }
                    System.out.println();
                }
                break;
            default:
                throw new AssertionError();
        }
    }
    
    //Soru Ã¼rettiÄŸimiz method
    //Seviyeye gÃ¶re soru Ã¼retiyoruz
    //level true ise level1 false ise level2
    public static String generateQuestion(int level){
        String question = "";
        String[] operators = {"+", "-", "*", "/"};
        
        if(level == 1){
            //1-20 arasÄ± bir sayÄ± Ã¼retiyoruz ve deÄŸiÅŸkene ekliyoruz
            question += String.valueOf((int)(Math.random()*20)+1);
            //operators dizisinden rastgele bir operatÃ¶r seÃ§iyoruz
            question += operators[(int)(Math.random()*4)];
            //Ä°kinci sayÄ±yÄ± ekliyoruz
            question += String.valueOf((int)(Math.random()*20)+1);
        }
        else{
            //1-20 arasÄ± bir sayÄ± Ã¼retiyoruz ve deÄŸiÅŸkene ekliyoruz
            question += String.valueOf((int)(Math.random()*20)+1);
            //operators dizisinden rastgele bir operatÃ¶r seÃ§iyoruz
            question += operators[(int)(Math.random()*4)];
            //Ä°kinci sayÄ±yÄ± ekliyoruz
            question += String.valueOf((int)(Math.random()*20)+1);
            //operators dizisinden rastgele bir operatÃ¶r seÃ§iyoruz
            question += operators[(int)(Math.random()*4)];
            //ÃœÃ§Ã¼ncÃ¼ sayÄ±yÄ± ekliyoruz
            question += String.valueOf((int)(Math.random()*20)+1);
        }
        return question;
    }

}
