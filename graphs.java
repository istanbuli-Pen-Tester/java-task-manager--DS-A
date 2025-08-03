import java.util.Arrays;

public class graphs {
    /* 
the tasks will be the vertices and they will be represented by numbers
Pay bill 2024-05-31, urgent, finance 0
Buy something 2024-06-25, urgent shopping 1
Attend appointment 2024-06-25 urgent health 2
Clean 2024-06-20 normal chores 3
Exercise 2024-06-07 normal health 4
Read book 2024-06-05 normal personal development 5
Complete assignment 2024-06-10 normal education 6
Write report 2024-06-13 normal work 7
Prepare presentation 2024-06-15 normal work 8

        */
public static void bf(int[][] matrix) {
        int V = matrix.length;
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;

        for (int i = 1; i < V; ++i) {
            for (int u = 0; u < V; ++u) {
                for (int v = 0; v < V; ++v) {
                    if (matrix[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + matrix[u][v] < dist[v]) {
                        dist[v] = dist[u] + matrix[u][v];
                    }
                }
            }
        }

        for (int u = 0; u < V; ++u) {
            for (int v = 0; v < V; ++v) {
                if (matrix[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + matrix[u][v] < dist[v]) {
                    System.out.println("Graph contains negative weight cycle");
                    return;
                }
            }
        }

        printSolution(dist);
    }

    public static void dij(int[][] matrix) {
        int V = matrix.length;
        int[] dist = new int[V];
        boolean[] sptSet = new boolean[V];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;

        for (int count = 0; count < V - 1; count++) {
            int u = minDistance(dist, sptSet);
            sptSet[u] = true;

            for (int v = 0; v < V; v++) {
                if (!sptSet[v] && matrix[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + matrix[u][v] < dist[v]) {
                    dist[v] = dist[u] + matrix[u][v];
                }
            }
        }

        printSolution(dist);
    }

    static int minDistance(int[] dist, boolean[] sptSet) {
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < dist.length; v++) {
            if (!sptSet[v] && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }
        }

        return min_index;
        //the direction is from 0 to 1 in the next example
        matrix[0][1] =5;
        matrix[0][5] =3;
        matrix[0][6] =2;
        matrix[1][2] =1;
        matrix[2][1] =2;
        matrix[3][2] =11;
        matrix[4][3] =7;
        matrix[4][6] =1;
        matrix[5][1] =10;
        matrix[5][2] =10;
        matrix[5][3] =2;
        matrix[5][4]=2;
        matrix[6][7]=3;
        matrix[6][8]=4;
        matrix[7][8]=1;
        System.out.println("Dijkstra's Algorithm:");
        long start1 = System.currentTimeMillis();
        dij(matrix);
        long end1 = System.currentTimeMillis();
        long time1 =end1 - start1;
        System.out.println("Dijkstra's time: "+time1);
        

        System.out.println("\nBellman-Ford Algorithm:");

        matrix[7][8]=-1;
        long start = System.currentTimeMillis();
        bf(matrix);
        long end = System.currentTimeMillis();
        long time =end - start;
        System.out.println("Bellman-Ford's time: "+time);


    }
}