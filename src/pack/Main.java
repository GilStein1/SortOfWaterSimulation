package pack;

import canvasML.Canvas;
import canvasML.Chk;
import canvasML.Ellipse;
import gilstein.graphics.Board;
import gilstein.graphics.Rectangle;
import gilstein.util.Time;

import java.awt.*;

public class Main {
    static Time t = new Time();
    public static void main(String[] args) {

//        //Ellipse e = new Ellipse(0,0,100,100,"blue");
//        Chk k = new Chk();
//
//        canvasML.Canvas c = Canvas.getCanvas();
//        c.drawLine(0,0,100,100);

        Board b = new Board(1920,1080,"water");

        //t.setTime(5);

        Rectangle[][] rarr = new Rectangle[960][540];

        for(int i = 0; i < rarr.length; i++) {
            for(int j = 0; j < rarr[0].length; j++) {
                rarr[i][j] = new Rectangle(b,i*2,j*2,2,2, Color.BLACK);
            }
        }

        int[][] arr = new int[rarr.length][rarr[0].length];

        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr[0].length; j++) {
                arr[i][j] = (int)(Math.random()*10);
            }
        }

        int[][] arr2 = new int[rarr.length][rarr[0].length];
        int max = 0;
        int color;

        while(true) {

            max = 0;

            for(int i = 0; i < arr.length; i++) {
                for(int j = 0; j < arr[0].length; j++) {
                    arr2[i][j] = arr[i][j];
                }
            }

            for(int i = 0; i < arr.length; i++) {
                for(int j = 0; j < arr[0].length; j++) {
                    if(arr[i][j] > 0 && i > 0 && j > 0 && i < arr.length - 1 && j < arr[0].length - 1) {
                        //arr2[i][j]--;

                        if(arr[i-1][j] < arr[i][j-1] && arr[i-1][j] < arr[i+1][j] && arr[i-1][j] < arr[i][j + 1]) {
                            arr2[i][j]--;
                            arr2[i - 1][j] ++;
                        }
                        else if (arr[i+1][j] < arr[i][j-1] && arr[i+1][j] < arr[i-1][j] && arr[i+1][j] < arr[i][j + 1]) {
                            arr2[i][j]--;
                            arr2[i + 1][j] ++;
                        }
                        else if (arr[i][j-1] < arr[i][j+1] && arr[i][j-1] < arr[i-1][j] && arr[i][j-1] < arr[i+1][j]) {
                            arr2[i][j]--;
                            arr2[i][j - 1] ++;
                        }
                        else if (arr[i][j+1] < arr[i][j-1] && arr[i][j+1] < arr[i-1][j] && arr[i][j+1] < arr[i+1][j]) {
                            arr2[i][j]--;
                            arr2[i][j + 1] ++;
                        }
                    }
                }
            }

            for(int i = 0; i < arr.length; i++) {
                for(int j = 0; j < arr[0].length; j++) {
                    arr[i][j] = arr2[i][j];
                    if(arr[i][j] < 0) {
                        arr[i][j] = 0;
                    }
                    if(arr[i][j] > max) {
                        max = arr[i][j];
                    }
                }
            }

            for(int i = 0; i < arr.length; i++) {
                for(int j = 0; j < arr[0].length; j++) {
                    //System.out.println(arr[i][j]);
                    color = (int)(Math.pow(-Math.sqrt((double)(arr[i][j])/max) + 1,2)*254);
                    rarr[i][j].setColor(new Color(color,color,color));
                }
            }
            t.setTime(0.02);

        }

    }
}