package com.leetcode.keyao.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by keyao on 12/10/16.
 * Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
 * Note:
 *   1. idea is simple, starting from a given point, calculate diag for every pair of points.
 *      points are on the same line if diag are same.
 *   2. diag can be represented by integer (x0, y0), where x0,y0 can be compuated using greasted common divider
 *

 */
public class Q149MaxPointOnALine {
    static public class Point  {
        public int x;
        public int y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public int maxPoints(Point[] points) {
        if (points==null) return 0;
        if (points.length<=2) return points.length;

        Map<Integer,Map<Integer,Integer>> map = new HashMap<Integer,Map<Integer,Integer>>();
        int result=0;
        for (int i=0;i<points.length;i++){
            map.clear();
            int overlap=0,max=0;
            for (int j=i+1;j<points.length;j++){
                int x=points[j].x-points[i].x;
                int y=points[j].y-points[i].y;
                if (x==0&&y==0){
                    overlap++;
                    continue;
                }
                int gcd=generateGCD(x,y);
                if (gcd!=0){
                    x/=gcd;
                    y/=gcd;
                }

                if (map.containsKey(x)){
                    if (map.get(x).containsKey(y)){
                        map.get(x).put(y, map.get(x).get(y)+1);
                    }else{
                        map.get(x).put(y, 1);
                    }
                }else{
                    Map<Integer,Integer> m = new HashMap<Integer,Integer>();
                    m.put(y, 1);
                    map.put(x, m);
                }
                max=Math.max(max, map.get(x).get(y));
            }
            result=Math.max(result, max+overlap+1);
        }
        return result;


    }
    private int generateGCD(int a,int b){

        if (b==0) return a;
        else return generateGCD(b,a%b);

    }

    static public void main(String[] arg) {
        Point p0 = new Point(0,0);
        Point p1 = new Point(1,1);
        Point p2 = new Point(0,1);
        Point p3 = new Point(1,2);
        Point p4 = new Point(2,2);


        Point[] points = new Point[5];
        points[0] = p0;
        points[1] = p1;
        points[2] = p2;
        points[3] = p3;
        points[4] = p4;

        Q149MaxPointOnALine solution = new Q149MaxPointOnALine();
        int ret = solution.maxPoints(points);
        System.out.println("result = " + ret);

    }
}
