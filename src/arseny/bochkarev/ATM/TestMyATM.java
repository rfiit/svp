package arseny.bochkarev.ATM;

import java.util.Arrays;
import java.util.List;

import static arseny.bochkarev.util.Utilities.*;

public class TestMyATM {
    public static void main(String[] args) throws Exception{
        IATM myatm = new MyATM();
        int[] coins = {-40,7,6};
        myatm.setCoins(coins);
        List test = myatm.exchange(-500);
        pList(test);

    }
}
