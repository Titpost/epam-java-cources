package com.epam.university.java.core.task036;

import java.util.function.Function;

/**
 * Implementation class for Task036.
 *
 * @author Sergei Titov
 */
public class Task036Impl implements Task036 {

    /**
     * {@inheritDoc}
     */
    @Override
    public double integrate(Function<Double, Double> function, Integrator integrator,
                            double limitLeft, double limitRight) {

        return integrator.integrate(limitLeft, limitRight, function);
    }
}
