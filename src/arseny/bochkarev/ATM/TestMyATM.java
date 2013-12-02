package arseny.bochkarev.ATM;

import java.util.Arrays;
import java.util.List;

import static arseny.bochkarev.util.Utilities.*;

public class TestMyATM {
    public static void main(String[] args) throws Exception{
        MyATM myatm = new MyATM();
        int[] coins = {4,7,6};
        myatm.setCoins(coins);
        List test = myatm.exchange(0);
        pList(test);

    }
}
