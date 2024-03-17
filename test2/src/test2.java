import java.util.Scanner;

/**
 * 課題2
 */
public class test2 {
    public static void main(String[] args) {

        // 標準入力取得用
        Scanner scanner = new Scanner(System.in);

        // ダイエット日数
        int days = scanner.nextInt();
        // 開始時の体重
        int startWeight = scanner.nextInt();
        // 上限体重
        int limitWeight = scanner.nextInt();
        // ダイエットを行った場合に痩せる体重
        int[] minus = new int[days];
        // ダイエットを行わなかった場合に増える体重
        int[] plus = new int[days];

        // 体重の増減を配列へ格納
        for (int i = 0; i < days; i++) {
            plus[i] = scanner.nextInt();
            minus[i] = scanner.nextInt();
        }

        // ダイエット割り当て処理呼び出しと結果出力
        System.out.println(countDietPlans(days, startWeight, limitWeight, minus, plus));
    }

    /**
     * ダイエット割り当て処理
     * @param days ダイエット日数
     * @param startWeight 開始時の体重
     * @param limitWeight 上限体重
     * @param minus ダイエットを行った場合に痩せる体重
     * @param plus ダイエットを行わなかった場合に増える体重
     * @return 割り当てパターン数
     */
    public static int countDietPlans(int days, int startWeight, int limitWeight, int[] minus, int[] plus) {
        // dp[i][j]はi日目に体重jである場合の数を表す
        int[][] dp = new int[days + 1][limitWeight + 1];

        // 開始
        dp[0][startWeight] = 1;

        // ダイエット日数分
        for (int i = 1; i <= days; i++) {
            // 上限体重に達するまで
            for (int j = 0; j <= limitWeight; j++) {
                // ダイエットを行う場合（対象体重 - 痩せる体重 >= 0）
                if (j - minus[i - 1] >= 0) {
                    dp[i][j] += dp[i - 1][j - minus[i - 1]];
                }
                // ダイエットを行わない場合（対象体重 + 増える体重 <= 上限体重）
                if (j + plus[i - 1] <= limitWeight) {
                    dp[i][j] += dp[i - 1][j + plus[i - 1]];
                }
            }
        }

        // 最終日の体重が上限以下の場合の数を合計
        int count = 0;
        for (int j = 0; j <= limitWeight; j++) {
            count += dp[days][j];
        }

        return count;
    }
}
