package com.epam.university.java.core.task015;

/**
 * Point factory.
 */
public interface PointFactory<T extends Number> {
    /**
     * Creates new point instance.
     * @param x x coordinate
     * @param y y coordinate
     * @return point instance
     */
    Point<T> newInstance(T x, T y);
}
