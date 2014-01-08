package com.dgvm.ws.helpers;

import com.dgvm.ws.utils.LengthComparator;

import java.util.Collections;
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

    private final int W = 11;
    private final int H = 11;

    private int fit(int x, int y, int length, int angle){

        switch (angle){
            case 0:
                if (y - length >= 0) return angle;
            case 1: //45g
                if (y - length >= 0 && x + length < W) return angle;
            case 2: //0
                if (x + length < W) return angle;
            case 3: //5
                if (x + length < W && y + length < H)return angle;
            case 4: //
                if(y + length < H)return angle;
            case 5:
                if(y + length < H && x - length >= 0)return angle;
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

    public Point getInc(int angle){
        Point inc = new Point(0,0);
        switch (angle){
            case 0: inc.y = -1; break;
            case 1: inc.x = 1; inc.y = -1; break;
            case 2: inc.x = 1; break;
            case 3: inc.x = 1; inc.y = 1; break;
            case 4: inc.y = 1; break;
            case 5: inc.x = -1; inc.y = 1; break;
            case 6: inc.x= -1; break;
            case 7: inc.x = -1; inc.y = -1;
        }
        return inc;
    }

    public void placeWord(char[][] darshboard, String word, int angle, int x, int y){
     //   int ix = 0, iy = 0;
        Point inc = getInc(angle);

     /*   switch (angle){
            case 0: iy = -1; break;
            case 1: ix = 1; iy = -1; break;
            case 2: ix = 1; break;
            case 3: ix = 1; iy = 1; break;
            case 4: iy = 1; break;
            case 5: ix = -1; iy = 1; break;
            case 6: ix= -1; break;
            case 7: ix = -1; iy = -1;
        }  */

        for (char letter : word.toCharArray()){
            darshboard[y][x] = letter;
            x += inc.x;
            y += inc.y;
        }
    }

    public boolean checkPlace(char[][] darshboard, String word, int angle, int x, int y){
      //  int ix = 0, iy = 0;
        Point inc = getInc(angle);
    /*
        switch (angle){
            case 0: iy = -1; break;
            case 1: ix = 1; iy = -1; break;
            case 2: ix = 1; break;
            case 3: ix = 1; iy = 1; break;
            case 4: iy = 1; break;
            case 5: ix = -1; iy = 1; break;
            case 6: ix= -1; break;
            case 7: ix = -1; iy = -1;
        }   */

        for (char letter : word.toCharArray()){
            if(darshboard[y][x] != '-' &&  darshboard[y][x] != letter)
                return false;
            x += inc.x;
            y += inc.y;
        }
        return true;
    }


    public char[][] build(List<String> words){

        char[][] dashboard = new char[W][H];

        //inicializo la matriz
        for(int i = 0; i < W; i ++)
            for (int j = 0; j< H;j++)
                dashboard[i][j]='-';

        Random r = new Random();

        Collections.sort(words, Collections.reverseOrder(new LengthComparator()));

        int x = 0,y = 0;
        String word = words.get(0);

        //obtener una dirección manecilla 0 2 4 6
        int angle = r.nextInt(4) * 2;
        // sacar un random en el rectangulo que se forma
        int length = word.length(); // tamanio de la primera palabra

        //primera vez
        switch(angle){
            case 0:
                x = r.nextInt(W);
                y = r.nextInt(H - length) + length;
                break;
            case 2:
                y = r.nextInt(H);
                x = r.nextInt(W - length);
                break;
            case 4:
                x = r.nextInt(W);
                y = r.nextInt(H - length);
                break;
            case 6:
                y = r.nextInt(H);
                x = r.nextInt(W - length) + length;
                break;
        }

        placeWord(dashboard, word, angle, x, y);

        this.print(dashboard);

        //ahora sí a poner el resto de palabras

        angle = r.nextInt(8);
        Point from, to;

       // for(String word2 : words.subList(1, words.size())){
        for(int k = 1 ; k < words.size() ;){
            word = words.get(k);

            from = new Point(0, 0);
            to = new Point(W, H);
            length = word.length();

            switch(angle){
                case 0:
                    from.y = length - 1; break;
                case 1:
                    to.x = W - length; from.y = length - 1; break;
                case 2:
                    to.x = W - length; break;
                case 3:
                    to.x = W - length; to.y = H - length; break;
                case 4:
                    to.y = H - length; break;
                case 5:
                    from.x = length - 1; to.y = H - length; break;
                case 6:
                    from.x = length - 1; break;
                case 7:
                    from.x = length - 1; from.y = length - 1; break;
            }

            System.out.println("word:"+word+" angle:"+angle+" from ("+from.x+", "+from.y+") to("+to.x+", "+to.y+")");
            // ya que tengo los limites voy a recorrer
            boolean isPlace = false;
            for ( int j = from.y ; j < to.y && !isPlace; j++){
                for(int i = from.x ; i < to.x && !isPlace ; i++ ){
                    if(isPlace = checkPlace(dashboard, word, angle, i, j)) {
                        placeWord(dashboard, word, angle, i, j);
                        k++;
                    }


                }
            }
            this.print(dashboard);
            angle = (angle == 7) ? 0 : angle+1;
        }

        //rellenar guiones
        for(int i = 0; i < W; i ++)
            for (int j = 0; j< H;j++)
                if(dashboard[i][j]=='-') dashboard[i][j] = (char)(r.nextInt(25)+97);

      // int a = 'z';
      //  System.out.println("--------"+a);


        return dashboard;


    }

    public void print(char[][] dashboard){
        System.out.println(" ");
        for(int i = 0; i<dashboard.length; i++){
            for(int j = 0; j<dashboard[i].length; j++){
                System.out.print(dashboard[i][j] + " ");
            }
            System.out.println("");
        }

    }

    public static class Point{
        public int x;
        public int y;

        public Point(int x, int y){
            this.x = x;
            this.y = y;

        }
    }
}
