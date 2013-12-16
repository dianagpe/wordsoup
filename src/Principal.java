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
        words.add("MURCIELAGO");
        words.add("CABALLERO");
        words.add("BATIMOVIL");
        words.add("BRUCE");
        words.add("NOCHE");
        words.add("ALFRED");




        char[][] dashboard = dg.build(words);

        dg.print(dashboard);

    }
}
