package com.epam.university.java.core.task015;

/**
 * Implementation class for PointFactory.
 *
 * @author Sergei Titov
 */
public class PointFactoryImpl<T extends Number> implements PointFactory<T> {
    /**
     * Creates new point instance.
     *
     * @param x x coordinate
     * @param y y coordinate
     * @return point instance
     */
    @Override
    public Point<T> newInstance(T x, T y) {
        return new PointImpl<>(x, y);
    }
}
