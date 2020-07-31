import java.lang.Math;
import java.util.ArrayList;

class Point {
   int x, y;
   Point(int x,int y)
   {
	   this.x=x;
	   this.y=y;
   }
}

class Line 
{
	 Point p1, p2;
	Line(Point p1,Point p2)
	{
  this.p1=p1;
  this.p2=p2;
	}
}

public class Prog {

static boolean onLine(Line l1, Point p) {        //check whether p is on the line or not
   if(p.x <= Math.max(l1.p1.x, l1.p2.x) && p.x <= Math.min(l1.p1.x, l1.p2.x) &&
      (p.y <= Math.max(l1.p1.y, l1.p2.y) && p.y <= Math.min(l1.p1.y, l1.p2.y)))
         return true;

   return false;
}

static int direction(Point a, Point b, Point c) {
   int val = (b.y-a.y)*(c.x-b.x)-(b.x-a.x)*(c.y-b.y);
   if (val == 0)
      return 0;           //colinear
   else if(val < 0)
      return 2;          //anti-clockwise direction
      return 1;          //clockwise direction
}

static boolean isIntersect(Line l1, Line l2) {
   //four direction for two lines and points of other line
   int dir1 = direction(l1.p1, l1.p2, l2.p1);
   int dir2 = direction(l1.p1, l1.p2, l2.p2);
   int dir3 = direction(l2.p1, l2.p2, l1.p1);
   int dir4 = direction(l2.p1, l2.p2, l1.p2);

   if(dir1 != dir2 && dir3 != dir4)
      return true;           //they are intersecting
   if(dir1==0 && onLine(l1, l2.p1))        //when p2 of line2 are on the line1
      return true;
   if(dir2==0 && onLine(l1, l2.p2))         //when p1 of line2 are on the line1
      return true;
   if(dir3==0 && onLine(l2, l1.p1))       //when p2 of line1 are on the line2
      return true;
   if(dir4==0 && onLine(l2, l1.p2)) //when p1 of line1 are on the line2
      return true;
   return false;
}

static boolean checkInside(ArrayList<Point> poly, int n, Point p) {
   if(n < 3)
      return false;         //when polygon has less than 3 edge, it is not polygon
   	Point p1=new Point(9999,p.y);
   Line exline = new Line(p, p1);   //create a point at infinity, y is same as point p
   int count = 0;
   int i = 0;
   do {
      Line side = new Line(poly.get(i), poly.get((i+1)%n));     //forming a line from two consecutive points of poly
      if(isIntersect(side, exline)) {          //if side is intersects exline
         if(direction(side.p1, p, side.p2) == 0)
            return onLine(side, p);
         count++;
      }
      i = (i+1)%n;
   } while(i != 0);
      return (count % 2 == 1);             //when count is odd
}

public static void main(String args[]) {
    //Line polygon = {{{0,0},{10,0}},{{10,0},{10,10}},{{10,10},{0,10}},{{0,10},{0,0}}};
   ArrayList<Point> polygon = new ArrayList<Point>();
   Point p1=new Point(0,0);
   Point p2=new Point(10,0);
   Point p3=new Point(10,10);
   Point p4=new Point(0,10);
   polygon.add(p1);
   polygon.add(p2);
   polygon.add(p3);
   polygon.add(p4);
  
    Point p = new Point(20,20);
   int n = 4;

   if(checkInside(polygon, n, p))
	   System.out.println("Point is inside.");
      
   else
	   System.out.println("Point is outside.");
}
}