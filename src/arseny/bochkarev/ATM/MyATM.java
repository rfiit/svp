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
        if(this.coins[0] <= 0) throw new Exception("Invalid coins");
    }

    @Override
    public List<String> exchange(int value) throws Exception {
        if (coins == null) return null;
        if(value < 0) throw new Exception("Invalid value.");
        List<String> result = new ArrayList<String>();
        createCoinsExchange(result, value, coins, null);

        if (result.isEmpty()) return null; // решить что возвращать

        return result;
    }

    private void createCoinsExchange(List<String> result, int currentvalue, int[] currentcoins, int[] numbers) {
        if (numbers == null) {
            numbers = new int[currentcoins.length];
        }
        if (currentvalue == 0) {
            result.add(coinNumbertoString(numbers));
            return;
        }
        if (currentcoins.length == 1) if (currentvalue % currentcoins[0] == 0) {
            numbers[0] = currentvalue / currentcoins[0];
            result.add(coinNumbertoString(numbers));
            return;
        } else {
            return;
        }

        int coinnumber = currentcoins.length-1;
        int coin = currentcoins[coinnumber];
        int[] newcurrentcoins = Arrays.copyOfRange(currentcoins, 0, coinnumber);

        for (int i = 0; currentvalue - i * coin >= 0; i++) {
            int[] newnumbers = Arrays.copyOf(numbers, numbers.length);
            newnumbers[coinnumber] = i;
            createCoinsExchange(result, currentvalue - i * coin, newcurrentcoins, newnumbers);
        }
    }

    private String coinNumbertoString(int[] numbers) {
        String assepted = "";
        int summ = 0;
        for (int i = 0; i < numbers.length; i++) {
            summ += numbers[i] * coins[i];
            assepted += (i > 0) ? ", " : "";
            assepted += numbers[i] + " * " + coins[i] + "_coin";
        }
        assepted += "." + "["+ summ+"]";
        return assepted;
    }



}
