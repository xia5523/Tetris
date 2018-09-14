package my_blocks;

/**
 * ��¼�ȼ�����
 * @author ����
 *
 */
public class Rank {
	/**
	 * ����
	 */
	public static int scores = 0;
	/**
	 * �ȼ�
	 */
	public static int rank = 1;
	/**
	 * RANK_ARRAY[0] == {1000,500}�����ﵽ1000�ֵ�ʱ�����������ʱ�߳�ͣ�ٵ��¼���500����
	 */
	public static final int[][] RANK_ARRAY = { { 1000, 500 }, { 2500, 400 },
			{ 4000, 300 }, { 6000, 250 }, { Integer.MAX_VALUE, 200 } };

	/**
	 * �õ���ǰ�ĵȼ�
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
