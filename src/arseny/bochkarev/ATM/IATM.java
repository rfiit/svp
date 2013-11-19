package arseny.bochkarev.ATM;

import java.util.List;

public interface IATM {

    void setCoins(int[] coins) throws Exception;

    List<String> exchange(int value) throws Exception;
}