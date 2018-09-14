package my_blocks;

/**
 * 记录等级分数
 * @author 夏忍
 *
 */
public class Rank {
	/**
	 * 分数
	 */
	public static int scores = 0;
	/**
	 * 等级
	 */
	public static int rank = 1;
	/**
	 * RANK_ARRAY[0] == {1000,500}代表，达到1000分的时候就升级，此时线程停顿的事件是500毫秒
	 */
	public static final int[][] RANK_ARRAY = { { 1000, 500 }, { 2500, 400 },
			{ 4000, 300 }, { 6000, 250 }, { Integer.MAX_VALUE, 200 } };

	/**
	 * 得到当前的等级
	 * 
	 * @param scores
	 * @return
	 */
	public static int getRank() {
		for (int i = RANK_ARRAY.length - 1; i >= 0; i--) {
			if (scores >= RANK_ARRAY[i][0]) {
				rank = i + 2;
				break;
			}
		}
		return rank;
	}

	
	
}
