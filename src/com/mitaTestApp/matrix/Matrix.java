package com.mitaTestApp.matrix;

import java.util.LinkedList;
import java.util.Queue;

public class Matrix {

    public static void main(String[] args) {
        char[][] grid = { { '0', '*', '0', 's' },
                { '*', '0', '*', '*' },
                { '0', '*', '*', '*' },
                { 'd', '*', '*', '*' } };

        System.out.println(minDistance(grid));
    }
    static class  QItem{
        int row;
        int col;
        int dist;
        public QItem(int row, int col, int dist){
            this.row = row;
            this.col = col;
            this.dist = dist;
        }
    }
    private static int minDistance(char[][] grid) {
        QItem source  = new QItem(0,0,0);
        firstloop:
        for(int i =0;i<grid.length;i++){
            for(int j =0;j<grid[0].length;j++){
                if(grid[i][j] =='s'){
                    source.row =i;
                    source.col =j;
                    break firstloop;
                }
            }
        }
        Queue<QItem> queue = new LinkedList<>();
        queue.add(new QItem(source.row,source.col, 0));

        boolean[][] visited = new boolean[grid.length][grid[0].length];
        visited[source.row][source.col] = true;

        while(!queue.isEmpty()){
           QItem p = queue.remove();
            if(grid[p.row][p.col] == 'd')
                return p.dist;

            if(isValid(p.row-1, p.col,grid, visited)){
                queue.add(new QItem(p.row-1, p.col,p.dist+1));
                visited[p.row-1][p.col] = true;
            }
            if(isValid(p.row+1, p.col,grid, visited)){
                queue.add(new QItem(p.row+1, p.col,p.dist+1));
                visited[p.row+1][p.col] = true;
            }
            if(isValid(p.row, p.col-1,grid, visited)){
                queue.add(new QItem(p.row, p.col-1,p.dist+1));
                visited[p.row][p.col-1] = true;
            }
            if(isValid(p.row, p.col+1,grid, visited)){
                queue.add(new QItem(p.row, p.col+1,p.dist+1));
                visited[p.row][p.col+1] = true;
            }
        }
        return -1;

    }
    private static boolean isValid(int row, int col, char[][] grid, boolean[][] visited) {
       if(row <=0 || col <=0 || row >= grid.length||col >= grid[0].length|| grid[row][col] =='0'|| visited[row][col] == true)
           return  false;
       return true;
    }


}

