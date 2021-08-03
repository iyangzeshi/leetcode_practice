package leetcode.editor;

public class PokerNTimesN {


    /**
     * 所有 row， col 和 dia， 0 ~ size - 1 存卡号，比如Order是4的话分别是3，4，5，6， size ~ size - 1 存花色，分别是方，红，梅，黑
     * int[] row, 长度为size，如果order 是4，则有4个position
     * int[] col, 长度为size
     * int[] dia, 长度为2，一个左上到右下，一个右上到左下
     * int cards，记录所有卡牌，如果是一副牌的话变成long
     *
     */

    public static int solution(int size) {
        return dfs(0, 0, new int[size], new int[size], new int[2], 0, size);
    }

    private static int dfs(int i, int j, int[] row, int[] col, int[] dia, int cards, int size) {
        if (i == size && j == 0) {
            return 1;
        }

        int res = 0;
        for (int k = 0; k < size; k++) {
            // check 数字第k位有没有用过
            if ((row[i] & (1 << k)) != 0 || (col[j] & (1 << k)) != 0
                    || (i + j == size - 1 && (dia[0] & (1 << k)) != 0)
                    || (i - j == 0 && (dia[1] & (1 << k)) != 0)) {
                continue;
            }
            for (int l = size; l < size * 2; l++) {
                int card = 1 << (k * size + l - size);
                // check 花色第l位有没有用过
                if ((row[i] & (1 << l)) != 0  || (col[j] & (1 << l)) != 0
                        || (i + j == size - 1 && (dia[0] & (1 << l)) != 0)
                        || (i - j == 0 && (dia[1] & (1 << l)) != 0)
                        || (cards & card) != 0) { // check 当前卡有没有用过
                    continue;
                }

                // set
                row[i] |= 1 << k; row[i] |= 1 << l;
                col[j] |= 1 << k; col[j] |= 1 << l;
                if (i + j == size - 1) {
                    dia[0] |= 1 << k; dia[0] |= 1 << l;
                }
                if (i - j == 0) {
                    dia[1] |= 1 << k; dia[1] |= 1 << l;
                }

                res += dfs(i + (j + 1) / size, (j + 1) % size, row, col, dia, cards | card, size);

                // set back
                row[i] &= ~(1 << k); row[i] &= ~(1 << l);
                col[j] &= ~(1 << k); col[j] &= ~(1 << l);
                if (i + j == size - 1) {
                    dia[0] &= ~(1 << k); dia[0] &= ~(1 << l);
                }
                if (i - j == 0) {
                    dia[1] &= ~(1 << k); dia[1] &= ~(1 << l);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(solution(1));
    }
}
