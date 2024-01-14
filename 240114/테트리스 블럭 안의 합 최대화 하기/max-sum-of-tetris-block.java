import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int max = Integer.MIN_VALUE;
    static int N,M;
    static int[][] board;
    static boolean[][] visited;
    static int[] rr = {0,0,-1,1};
    static int[] rc = {-1,1,0,0};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int [N][M];
        visited = new boolean[N][M];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                visited[i][j] = true;
                dfs(i, j,board[i][j],1);
                visited[i][j] = false;
            }
        }

        System.out.println(max);
    }

    static void dfs(int row, int col, int sum, int cnt){
        if(cnt == 4) {
            max = Math.max(max, sum);
            return;
        }
        //사방탐색
        for(int i = 0; i < 4; i++){
            int nr = row + rr[i];
            int nc = col + rc[i];

            if(nr >= 0 && nr < N && nc >= 0 && nc < M && !visited[nr][nc]){
                //ㅗ 모양 블럭용 탐색
                //이 경우에는 기존 row, col에서 탐색을 재진행 하도록 한다.
                if(cnt == 2){
                    visited[nr][nc] = true;
                    dfs(row, col, sum + board[nr][nc], cnt + 1);
                    visited[nr][nc] = false;
                }
                visited[nr][nc] = true;
                dfs(nr, nc, sum + board[nr][nc], cnt + 1);
                visited[nr][nc] = false;
            }

        }

    }
}