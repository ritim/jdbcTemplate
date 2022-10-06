import java.util.Arrays;
import java.util.Random;
import java.util.Set;

/**
 * 實現標準布隆過濾器的類
 */
public class SBFilters {
  // 例項欄位
  private boolean[] bitArray; //位陣列
  private int[][] hashParams; //隨機的雜湊函式引數

  // 方法欄位
  public SBFilters(int tLen, Set<Long> iSet)
  {
    this.bitArray = new boolean[tLen];
    Arrays.fill(this.bitArray, Boolean.FALSE);
    this.constructionFilter(iSet);
  }

  private boolean constructionFilter(Set<Long> iSet)
  {
    int iSetSize = (iSet == null ? 0 :iSet.size());
    if(iSetSize == 0)
    {
      return false;
    }
    var hashNum = (int)(Math.log(2) * (this.bitArray.length / iSetSize));
    this.constructionHashParams(hashNum);
    for(var item: iSet)
    {
      for(var params: this.hashParams)
      {
        this.bitArray[hashFunction(params, item)] = true;
      }
    }
    return true;
  }

  private boolean constructionHashParams(int hashNum)
  {
    this.hashParams = new int[hashNum][3];
    var time = System.currentTimeMillis();
    var rd = new Random(time);
    for(int i = 0; i < hashNum; i++)
    {
      this.hashParams[i][0] = rd.nextInt(9999) + 1;
      this.hashParams[i][1] = rd.nextInt(9999) + 1;
      this.hashParams[i][2] = rd.nextInt(9999) + 1;
    }
    return true;
  }

  private int hashFunction(int[] params, long item)
  {
    return (int)((params[0] * Math.pow(item, 2.0) + params[1] * item + params[2]) % bitArray.length);
  }

  public boolean queryFilter(long item)
  {
    for(var params: this.hashParams)
    {
      int hashNumber = hashFunction(params, item);
      System.out.println("[item]" + item + ", [hashNumber]" + hashNumber);
      if(!this.bitArray[hashNumber])
      {
        return false;
      }
    }
    return true;
  }

}
