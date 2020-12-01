package edu.wit.comp1050;

import java.util.Random;
import java.util.Scanner;

public class GameCode {

    public static final String[] colors = {"G", "B", "P", "R", "O", "Y"};

    //Randomly generates the secret code and then returns it. It is returned as a four character string
    public static String createSecretCode() {
        Random numbers = new Random();
        String result = "";
        int index;
        int codeSize = 4;
        String[] c = colors;

        for(int i = 0; i < codeSize; i++) {
            index = numbers.nextInt(c.length);
            result += c[index];
        }
        return result;
    }
    //changes the string version of the secret code into an integer secret code
    //if the secret code was "GBPR" then it would return {0,1,2,3} inside of an array
    public static int[] stringCodeToIntCode (String secretCode) {
        int[] integerCode = new int[4];
        char[] characters = secretCode.toCharArray();
        for (int i = 0; i < 4; i++) {
            switch (characters[i]) {
                case 'G' : {
                    integerCode[i] = 0;
                    break;
                }
                case 'B' : {
                    integerCode[i] = 1;
                    break;
                }
                case 'P' : {
                    integerCode[i] = 2;
                    break;
                }
                case 'R' : {
                    integerCode[i] = 3;
                    break;
                }
                case 'O' : {
                    integerCode[i] = 4;
                    break;
                }
                case 'Y' : {
                    integerCode[i] = 5;
                    break;
                }
            }
        }
        return integerCode;
    }


    //Method that tells the user if they got the code right by returning the total amount of black and white pegs
        public static int[] pegFeedback (int[] code, int[] guess) {
        int blackPegs = 0;
        int whitePegs = 0;

            //checks for possible black pegs (correct color and position)
            for (int i = 0; i < code.length; i++) {
                if (code[i] == guess[i]) {
                    blackPegs++;
                }
            }
            //checks for white pegs (correct color)
            for (int i = blackPegs; i < guess.length; i++) {
                for (int j = i; j < code.length; j++) {
                    if (guess[i] == code[j]) {
                        whitePegs++;
                    }
                }
            }
            int[] result = new int[2];
            result[0] = blackPegs;
            result[1] = whitePegs;
            return result;
        }


}
