package arseny.bochkarev.ATM;

import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: linker
 * Date: 11.11.13
 * Time: 19:47
 * To change this template use File | Settings | File Templates.
 */
public class MyATM implements IATM {

    private int[] coins = null;

    @Override
    public void setCoins(int[] coins) throws Exception {
        this.coins = coins;
        Arrays.sort(this.coins);
    }

    @Override
    public List<int[]> exchange(int value) throws Exception {
        return null;
    }
}
