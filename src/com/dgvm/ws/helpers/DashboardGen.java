package com.dgvm.ws.helpers;

import java.util.List;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: diana
 * Date: 08/12/13
 * Time: 17:20
 * To change this template use File | Settings | File Templates.
 */
public class DashboardGen {


    private static DashboardGen ourInstance = new DashboardGen();

    public static DashboardGen getInstance() {
        return ourInstance;
    }

    private DashboardGen() {

    }

    //this words are sorted

    private final int w = 11;
    private final int h = 11;

    private int fit(int x, int y, int length, int angle){

        switch (angle){
            case 0:
                if (y - length >= 0) return angle;
            case 1: //45g
                if (y - length >= 0 && x + length < w) return angle;
            case 2: //0
                if (x + length < w) return angle;
            case 3: //5
                if (x + length < w && y + length < h)return angle;
            case 4: //
                if(y + length < h)return angle;
            case 5:
                if(y + length < h && x - length >= 0)return angle;
            case 6:
                if(x - length > 0)return angle;
            case 7:
                if(x - length > 0 && y - length > 0)return angle;
        }
        return -1;
    }

    private int nextAngle(int angle){
        return ( angle + 1 ) >= 8 ? 0 : angle + 1;
    }

    public void placeWord(char[][] darshboard, String word, int angle, int x, int y){
        int ix = 0, iy = 0;

        switch (angle){
            case 0: iy = -1; break;
            case 1: ix = 1; iy = -1; break;
            case 2: ix = 1; break;
            case 3: ix = 1; iy = 1; break;
            case 4: iy = 1; break;
            case 5: ix = -1; iy = -1; break;
            case 6: ix= -1; break;
            case 7: ix = -1; iy = -1;
        }

        for (char letter : word.toCharArray()){
            darshboard[x][y] = letter;
            x += ix;
            y += iy;
        }
    }

    public char[][] build(List<String> words){
        char[][] dashboard = new char[w][h];

        for(int i = 0; i < w; i ++)
            for (int j = 0; j< h;j++)
                dashboard[i][j]='-';

        int angle = new Random().nextInt(8);

        //ask si fit

        String word = words.get(0);

        int x = (int)Math.random() % (w - word.length());
        int y = (int)Math.random() % (h - word.length());


        for(int a = angle;(angle = fit(x, y, word.length(), a)) < 0; a = (angle+1 > 8)?0:angle+1)



        placeWord(dashboard, word, angle, x, y);

        //defino el angulo que sigue

        // ya que tengo el angulo de donde a donde se va a recorrer                             (limites)



        //  recorro mientras no este ocupado







        return dashboard;


    }
}
