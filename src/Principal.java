import com.dgvm.ws.helpers.DashboardGen;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: diana
 * Date: 08/12/13
 * Time: 18:05
 * To change this template use File | Settings | File Templates.
 */
public class Principal {

    public static void main(String args[]){

        DashboardGen dg = DashboardGen.getInstance();

        List<String> words = new ArrayList<String>();
        words.add("murcielago");
        words.add("caballero");
        words.add("batimovil");
        words.add("bruce");
        words.add("noche");
        words.add("alfred");




        char[][] dashboard = dg.build(words);

    }
}
