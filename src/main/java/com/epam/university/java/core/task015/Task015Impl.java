package com.epam.university.java.core.task015;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation class for Task015.
 *
 * @author Sergei Titov
 */
public class Task015Impl implements Task015 {

    /**
     * Get area of intersection of two squares by Gauss-formula.
     *
     * @param first first square definition
     * @param second second square definition
     *
     * @return value of area
     */
    @Override
    public double getArea(Square first, Square second) {

        if (first.equals(second)) {
            return getAreaTwiced(first) / 2;
        }

        // get intersection area of 1st and 2nd squares
        List<Point> vertexes = getIntersectionPoints(first, second);

        int n = vertexes.size();

        // no partial intersections
        if (n < 3) {

            if (isSquireInside(first, second)) {
                return getAreaTwiced(first) / 2;
            }

            if (isSquireInside(second, first)) {
                return getAreaTwiced(second) / 2;
            }

            return 0;
        }


        double area = 0.0d;
        for (int i = 0; i < n - 1; i++) {
            area += ((Double)vertexes.get(i).getX()) * ((Double)vertexes.get(i + 1).getY())
                    - ((Double)vertexes.get(i + 1).getX()) * ((Double)vertexes.get(i).getY());
        }
        area += ((Double)vertexes.get(n - 1).getX()) * ((Double)vertexes.get(0).getY())
                - ((Double)vertexes.get(0).getX()) * ((Double)vertexes.get(n - 1).getY());

        if (area < 0) {
            area *= -1;
        }
        return area / 2.0d;
    }

    /**
     * Gets squares' intersection point collection.
     *
     * @param firstSquare square corners
     * @param secondSquare square corners
     *
     * @return list of intersection points
     */
    private List<Point> getIntersectionPoints(Square firstSquare, Square secondSquare) {

        Point<Integer>[] corners1 = getSquarePoints(firstSquare);
        Point<Integer>[] corners2 = getSquarePoints(secondSquare);

        List<Square> first = getSquareSides(corners1);
        List<Square> second = getSquareSides(corners2);

        List<Point> retList = new ArrayList<>();

        for (Square segment1 : first) {
            final int index = retList.size();
            for (Square segment2 : second) {

                ///////////////////////////////////////////////////////////////////////////////////
                // look for intersecting segments of squares
                // (х1, у1) and (х2, у2) - first segment coordinates
                // (х3, у3) and (х4, у4) - second segment coordinates
                ///////////////////////////////////////////////////////////////////////////////////

                int x1 = segment1.getFirst().getX();
                int y1 = segment1.getFirst().getY();

                int x2 = segment1.getSecond().getX();
                int y2 = segment1.getSecond().getY();

                int x3 = segment2.getFirst().getX();
                int y3 = segment2.getFirst().getY();

                int x4 = segment2.getSecond().getX();
                int y4 = segment2.getSecond().getY();

                double sy2 = (y4 - y3);

                //////////////////////////////////////////////////////////////
                // find Collision point
                double s = ((y1 - y2) * (x1 - x3) + (x2 - x1) * (y1 - y3))
                        / ((x3 - x4) * (y2 - y1) + (x2 - x1) * sy2);
                double t = ((x4 - x3) * (y1 - y3) - sy2 * (x1 - x3))
                        / ((x3 - x4) * (y2 - y1) + (x2 - x1) * sy2);

                double x; // x-coordinate of a collision point
                double y; // y-coordinate of a collision point

                if (s >= 0 && s <= 1 && t >= 0 && t <= 1) {
                    // Collision detected at the following coordinates
                    x = x1 + (t * (x2 - x1));
                    y = y1 + (t * (y2 - y1));

                    // check if endpoints of segment 1 is inside of the second polygon
                    // SEGMENT 1, BEGIN - (not need, occasionally or not)
                    // addContained(segment1.getFirst(), retList, corners2);
                    // SEGMENT 1, END
                    addContained(segment1.getSecond(), retList, corners2);
                } else {
                    continue;
                }

                // add INTERSECTION point
                Point<Double> intersectionPoint = new PointImpl<>(x, y);
                if (!retList.contains(intersectionPoint)) {
                    if (corners1[0].equals(segment1.getSecond())) {
                        retList.add(index, intersectionPoint);
                    } else {
                        retList.add(intersectionPoint);
                    }
                }

                // check if endpoints of segment 2 is inside the first squire
                // SEGMENT 2, BEGIN
                addContained(segment2.getFirst(), retList, corners1);
                // SEGMENT 2, END - (not need, occasionally or not)
                // addContained(segment2.getSecond(), retList, corners1);
            }
        }
        return retList;
    }

    // add a point if it is contained inside the square
    private void addContained(Point<Integer> point, List<Point> list, Point<Integer>[] corners) {

        Point<Double> corner = new PointImpl<>(
                point.getX().doubleValue(),
                point.getY().doubleValue());

        if (!list.contains(corner) && isPointInside(point,
                corners[0].getX(), corners[0].getY(),
                corners[1].getX(), corners[1].getY(),
                corners[2].getX(), corners[2].getY(),
                corners[3].getX(), corners[3].getY())) {
            list.add(corner);
        }
    }

    /**
     * Gets coordinates of all the squire's vertexes.
     *
     * @param line - coordinates of square's diagonal
     *
     * @return - list with coordinates squire's vertexes
     */
    private static Point<Integer>[] getSquarePoints(Square line) {
        final int vertAx = line.getFirst().getX();
        final int vertAy = line.getFirst().getY();
        final int vertCx = line.getSecond().getX();
        final int vertCy = line.getSecond().getY();

        final int dx = vertCx - vertAx;
        final int dy = vertAy - vertCy;
        final int d = (dx - dy) / 2;

        @SuppressWarnings("unchecked")
        Point<Integer>[] points = new PointImpl[4];

        points[0] = new PointImpl<>(vertAx, vertAy);
        points[1] = new PointImpl<>(vertCx - d, vertAy + d);
        points[2] = new PointImpl<>(vertCx, vertCy);
        points[3] = new PointImpl<>(vertAx + d, vertCy - d);

        return points;
    }

    /**
     * Gets coordinates of all the squire's vertexes.
     *
     * @param corners - coordinates of the square
     *
     * @return - list with coordinates squire's vertexes
     */
    private List<Square> getSquareSides(Point<Integer>[] corners) {

        List<Square> retList = new ArrayList<>();
        // AB side
        retList.add(new SquareImpl(corners[0], corners[1]));
        // BC side
        retList.add(new SquareImpl(corners[1], corners[2]));
        // CD side
        retList.add(new SquareImpl(corners[2], corners[3]));
        // DA side
        retList.add(new SquareImpl(corners[3], corners[0]));

        return retList;
    }

    // isPointInside
    private static boolean isPointInside(Point<Integer> point,
                                         int x1, int y1, int x2, int y2,
                                         int x3, int y3, int x4, int y4) {
        // distance to AB
        double dx = x2 - x1;
        double dy = y2 - y1;
        final double d1 = ((y1 - point.getY()) * dx + (point.getX() - x1) * dy)
                                                          / (dy * dy + dx * dx);

        // distance to BC
        dx = x3 - x2;
        dy = y3 - y2;
        final double d2 = ((y2 - point.getY()) * dx + (point.getX() - x2) * dy)
                                                         / (dy * dy + dx * dx);

        // distance to CD
        dx = x4 - x3;
        dy = y4 - y3;
        final double d3 = ((y3 - point.getY()) * dx + (point.getX() - x3) * dy)
                                                         / (dy * dy + dx * dx);

        // distance to DA
        dx = x1 - x4;
        dy = y1 - y4;
        final double d4 = ((y4 - point.getY()) * dx + (point.getX() - x4) * dy)
                                                          / (dy * dy + dx * dx);

        // distances to each of the segments must be of one sign
        if (d1 > 0) {
            return d2 > 0 && d3 > 0 && d4 > 0;
        } else {
            return d2 < 0 && d3 < 0 && d4 < 0;
        }
    }

    // isPointInside
    private static boolean isPointInside(Point<Integer> point, Point<Integer>[] corners) {
        return isPointInside(point,
                corners[0].getX(), corners[0].getY(),
                corners[1].getX(), corners[1].getY(),
                corners[2].getX(), corners[2].getY(),
                corners[3].getX(), corners[3].getY()
        );
    }


    // isSquireInside
    private static boolean isSquireInside(Square square1, Square square2) {

        Point<Integer>[] corners = getSquarePoints(square2);
        if (isPointInside(square1.getFirst(), corners)
                || isPointInside(square1.getSecond(), corners)) {
            return true;
        } else {

            return false;
        }
    }

    // getSquare
    private double getAreaTwiced(Square diagonal) {
        double a = diagonal.getFirst().getX() - diagonal.getSecond().getX();
        double b = diagonal.getFirst().getY() - diagonal.getSecond().getY();

        return a * a + b * b;
    }
}