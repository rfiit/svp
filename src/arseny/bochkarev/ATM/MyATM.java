package arseny.bochkarev.ATM;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static arseny.bochkarev.util.Utilities.*;

public class MyATM implements IATM {

    private int[] coins = null;

    @Override
    public void setCoins(int[] coins) throws Exception {
        this.coins = Arrays.copyOf(coins, coins.length);
        Arrays.sort(this.coins);
        //p("MyATM.coins = " + Arrays.toString(this.coins));
    }

    @Override
    public List<String> exchange(int value) throws Exception {
        List<String> result = new ArrayList<String>();
        createCoinsExchange(result, value, coins, "");
        return result;
    }

    private void createCoinsExchange(List<String> result, int currentvalue, int[] currentcoins, String currentresult) {
        if (currentvalue == 0) {
            result.add(currentresult + ".");
            return;
        }
        if (currentcoins.length == 1) if (currentvalue % currentcoins[0] == 0) {
            String assepted = currentresult + ", " +
                    (currentvalue / currentcoins[0]) +
                    " * " + currentcoins[0] + "_coin .";
            result.add(assepted);
            return;
        } else {
            return;
        }

        int coinnumber = currentcoins.length-1;
        int coin = currentcoins[coinnumber];
        int[] newcurrentcoins = Arrays.copyOfRange(currentcoins, 0, coinnumber-1);
        for (int i = 0; currentvalue - i * coin >= 0; i++) {
            createCoinsExchange(result, currentvalue - i * coin, newcurrentcoins, currentresult + ", " + i + " * " + coin + "_coin");
        }
    }

    private String coinNumbertoString(int[] numbers) {
        return null;
    }



}
