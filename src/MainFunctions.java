import java.math.BigInteger;


/**
 * This class contains attributes of the main functions to be used throughout the game.
 * @author DtjiPsimfans
 * */
public class MainFunctions {
    public BigInteger triangulation(BigInteger n){
        return (n.multiply(n.add(new BigInteger("1")))).divide(new BigInteger("2"));
    }

    public int triangulation(int n){
        return (n * (n + 1)) / 2;
    }

    public long triangulation(long n){
        return (n *(n + 1)) / 2;
    }
}
