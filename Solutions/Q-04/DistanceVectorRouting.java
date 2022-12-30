// 4. Simulate and implement distance vector routing algorithm. 

import java.io.*;

public class DistanceVectorRouting  {
    static int graph[][];
    static int via[][];
    static int rt[][];
    static int v;
    static int e;

// Initializes Table
static void init_tables() {
    for(int i = 0; i < v; i++) {
        for(int j = 0; j < v; j++) {
            if(i == j) {
                rt[i][j] = 0;
                via[i][j] = i;
            }
            else {
                rt[i][j] = 9999;
                via[i][j] = 100;
            }
        }
    }
}

// Update Tables
static void internallyUpdateTable(int source) {
    for(int i = 0; i < v; i++) {
        if(graph[source][i] != 9999) {
            int dist = graph[source][i];
            for(int j = 0; j < v; j++) {
                int inter_dist = rt[i][j];
                if(via[i][j] == source)
                inter_dist = 9999;
                if(dist + inter_dist < rt[source][j]) {
                    rt[source][j] = dist + inter_dist;
                    via[source][j] = i;
                }
            }
        }
    }
}


static void updateTables() {      // To be called
    int k = 0;
    for(int i = 0; i < 4*v; i++) {
        internallyUpdateTable(k);
        k++;
        if(k == v)
            k = 0;
    }
}


// Printing the Table
static void printTables() {
    for(int i = 0; i < v; i++) {
        for(int j = 0; j < v; j++) {
            System.out.print("Dist: " + rt[i][j] + "    ");
        }
    System.out.println();
    }
}


// DVR Calcualtion Display
static void dvrCalculationDisplay(String message) {
    System.out.println();
    init_tables();
    updateTables();
    System.out.println(message);
    printTables();
    System.out.println();
}
   
   

public static void main(String args[]) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    System.out.print("Enter the number of Vertices: ");
    v = Integer.parseInt(br.readLine());    
    System.out.print("Enter the number of Edges: ");
    e = Integer.parseInt(br.readLine());

        graph = new int[v][v];
        via = new int[v][v];
        rt = new int[v][v];

            for(int i = 0; i < v; i++)
                for(int j = 0; j < v; j++) {
                    if(i == j)
                        graph[i][j] = 0;
                    else
                        graph[i][j] = 9999;
            }

    for(int i = 0; i < e; i++) {
        System.out.println("Enter data for Edge " + (i + 1) + ":");
        System.out.print("Source: ");
        int s = Integer.parseInt(br.readLine());
        s--;

        System.out.print("Destination: ");
        int d = Integer.parseInt(br.readLine());    
        d--;
        
        System.out.print("Cost: ");
        int c = Integer.parseInt(br.readLine());
        
        graph[s][d] = c;
        graph[d][s] = c;
    }

    dvrCalculationDisplay("The Initial Routing Tables are: ");

    System.out.print("Enter the Source Node for the edge whose cost has changed: ");
    int s = Integer.parseInt(br.readLine());
    s--;
    System.out.print("Enter the Destination Node for the edge whose cost has changed: ");
    int d = Integer.parseInt(br.readLine());
    d--;
    System.out.print("Enter the new cost: ");
    int c = Integer.parseInt(br.readLine());
    graph[s][d] = c;
    graph[d][s] = c;

    dvrCalculationDisplay("The new Routing Tables are: ");
    }
}