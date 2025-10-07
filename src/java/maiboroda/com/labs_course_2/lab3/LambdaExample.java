package maiboroda.com.labs_course_2.lab3;

public class LambdaExample {
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        RowSumCalculator rowSumCalc = (arr) -> {
            int[] sums = new int[arr.length];
            for (int i = 0; i < arr.length; i++) {
                int sum = 0;
                for (int j = 0; j < arr[i].length; j++) {
                    sum += arr[i][j];
                }
                sums[i] = sum;
            }
            return sums;
        };

        int[] result = rowSumCalc.calculate(matrix);

        System.out.println("Суми рядків:");
        for (int sum : result) {
            System.out.println(sum);
        }
    }
}
