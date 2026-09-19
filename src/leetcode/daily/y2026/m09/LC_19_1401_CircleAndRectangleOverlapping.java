package leetcode.daily.y2026.m09;

public class LC_19_1401_CircleAndRectangleOverlapping {
    public boolean checkOverlap_editorial2(
            int radius,
            int xCenter,
            int yCenter,
            int x1,
            int y1,
            int x2,
            int y2
    ) {
        double dist = 0;
        if (xCenter < x1 || xCenter > x2) {
            dist += Math.min(
                    Math.pow(x1 - xCenter, 2),
                    Math.pow(x2 - xCenter, 2)
            );
        }
        if (yCenter < y1 || yCenter > y2) {
            dist += Math.min(
                    Math.pow(y1 - yCenter, 2),
                    Math.pow(y2 - yCenter, 2)
            );
        }
        return dist <= radius * radius;
    }
    public boolean checkOverlap_editorial1(
            int radius,
            int xCenter,
            int yCenter,
            int x1,
            int y1,
            int x2,
            int y2
    ) {
        /* The center of the circle is inside the rectangle */
        if (x1 <= xCenter && xCenter <= x2 && y1 <= yCenter && yCenter <= y2) {
            return true;
        }
        /* The center of the circle is above the rectangle */
        if (
                x1 <= xCenter &&
                        xCenter <= x2 &&
                        y2 <= yCenter &&
                        yCenter <= y2 + radius
        ) {
            return true;
        }
        /* The center of the circle is below the rectangle */
        if (
                x1 <= xCenter &&
                        xCenter <= x2 &&
                        y1 - radius <= yCenter &&
                        yCenter <= y1
        ) {
            return true;
        }
        /* The center of the circle is to the left of the rectangle */
        if (
                x1 - radius <= xCenter &&
                        xCenter <= x1 &&
                        y1 <= yCenter &&
                        yCenter <= y2
        ) {
            return true;
        }
        /* The center of the circle is to the right of the rectangle */
        if (
                x2 <= xCenter &&
                        xCenter <= x2 + radius &&
                        y1 <= yCenter &&
                        yCenter <= y2
        ) {
            return true;
        }
        /* The upper-left corner of the rectangle */
        if (distance(xCenter, yCenter, x1, y2) <= radius * radius) {
            return true;
        }
        /* The lower-left corner of the rectangle */
        if (distance(xCenter, yCenter, x1, y1) <= radius * radius) {
            return true;
        }
        /* The upper-right corner of the rectangle */
        if (distance(xCenter, yCenter, x2, y2) <= radius * radius) {
            return true;
        }
        /* The lower-right corner of the rectangle */
        if (distance(xCenter, yCenter, x2, y1) <= radius * radius) {
            return true;
        }
        /* No intersection */
        return false;
    }

    public long distance(int ux, int uy, int vx, int vy) {
        return (long) Math.pow(ux - vx, 2) + (long) Math.pow(uy - vy, 2);
    }

    public boolean checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        if(xCenter >= x1 && xCenter <= x2 && yCenter >= y1 && yCenter <= y2)
            return true;
        double minDistance = 1000000000d;
        for(int i = x1; i <= x2; i++){
            double distance1 = Math.sqrt(((xCenter - i) * (xCenter - i))
                    + ((yCenter - y1) * (yCenter - y1)));
            double distance2 = Math.sqrt(((xCenter - i) * (xCenter - i))
                    + ((yCenter - y2) * (yCenter - y2)));
            minDistance = Math.min(minDistance, distance1);
            minDistance = Math.min(minDistance, distance2);
        }
        for(int i = y1; i <= y2; i++){
            double distance1 = Math.sqrt(((xCenter - x1) * (xCenter - x1))
                    + ((yCenter - i) * (yCenter - i)));
            double distance2 = Math.sqrt(((xCenter - x2) * (xCenter - x2))
                    + ((yCenter - i) * (yCenter - i)));
            minDistance = Math.min(minDistance, distance1);
            minDistance = Math.min(minDistance, distance2);
        }
        return minDistance <= radius;
    }
}
