public class Judge {

    public static void main(String[] args) {
        Judge core = new Judge();
        int [][] test1 = {{1,2}};
        int [][] test2 = {{1,3},{2,3}};
        int [][] test3 = {{1,3},{2,3},{3,1}};
        int [][] test4 = {{1,2},{2,3}};
        int [][] test5 = {{1,3},{1,4},{2,3},{2,4},{4,3}};

        int result1 = core.findJudge(2, test1);
        int result2 = core.findJudge(3, test2);
        int result3 = core.findJudge(3, test3);
        int result4 = core.findJudge(3, test4);
        int result5 = core.findJudge(4, test5);

        System.out.println("RESULTS");
        System.out.println("{{1,3}} => " + result1);
        System.out.println("{{1,3},{2,3}} => " + result2);
        System.out.println("{{1,3},{2,3},{3,1}} => " + result3);
        System.out.println("{{1,2},{2,3}} => " + result4);
        System.out.println("{{1,3},{1,4},{2,3},{2,4},{4,3}} => " + result5);
    }

    // find Judge Fun
    public int findJudge (int N, int [][] trust) {
        boolean[][] statusBool = new boolean[N+1][N+1];
        boolean reality = true;
        int bound = 0, index = 0;

        for(int k = 1; k <= N; k++) {
            for(int e = 1; e <= N; e++) {
                statusBool[k][e] = false;
            }
        }
        for(int q = 0; q < trust.length; q++) {
            statusBool[trust[q][0]][trust[q][1]] = true;
        }
        for(int p = 1; p <= N; p++) {
            index = 0;
            bound = p;
            for(int y = 1; y <= N; y++) {
                if(statusBool[y][p]==true) {
                    index++;
                    continue;
                }
            }
            if(index >= N - 1) {
                for(int u = 0; u < trust.length; u++) {
                    if(bound == trust[u][0]) {
                        // false then break
                        reality = false;
                        break;
                    }
                } if(reality) {
                    // found
                    return bound;
                }
            }
        }
        // did not find
        return -1;
    }
}