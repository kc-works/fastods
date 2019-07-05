/*
 * FastODS - A very fast and lightweight (no dependency) library for creating ODS
 *    (Open Document Spreadsheet, mainly for Calc) files in Java.
 *    It's a Martin Schulz's SimpleODS fork
 *    Copyright (C) 2016-2019 J. Férard <https://github.com/jferard>
 * SimpleODS - A lightweight java library to create simple OpenOffice spreadsheets
 *    Copyright (C) 2008-2013 Martin Schulz <mtschulz at users.sourceforge.net>
 *
 * This file is part of FastODS.
 *
 * FastODS is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * FastODS is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */

package com.github.jferard.fastods.util;

/**
 * 20.339 style:rotation-angle http://docs.oasis-open.org/office/v1.2/os/OpenDocument-v1
 * .2-os-part1.html#__RefHeading__1420142_253892949
 * @author
 */
public class Angle {
    public static final Angle NO_ROTATING = Angle.deg(0);
    public static final Angle ROTATE_90 = Angle.deg(90);
    public static final Angle ROTATE_180 = Angle.deg(180);
    public static final Angle ROTATE_270 = Angle.deg(270);

    /**
     * @param degs the number of degrees
     * @return an Angle
     */
    public static Angle deg(final double degs) {
        return new Angle(degs, Angle.Unit.DEG);
    }

    /**
     * @param grads the number of gradians
     * @return an Angle
     */
    public static Angle grad(final double grads) {
        return new Angle(grads, Angle.Unit.GRAD);
    }

    /**
     * @param rads the number of radians
     * @return an Angle
     */
    public static Angle rad(final double rads) {
        return new Angle(rads, Angle.Unit.RAD);
    }

    private final double value;
    private final Unit unit;

    /**
     * @param value the value of the angle
     * @param unit  the unit
     *              TODO: should be a double, but LO doesn't handle double for text rotating
     */
    public Angle(final double value, final Unit unit) {
        this.value = value;
        this.unit = unit;
    }

    @Override
    public String toString() {
        String ret = "";
        switch (this.unit) {
            case DEG:
                ret = String.valueOf((int) this.value); // TODO: LO bug
                break;
            case GRAD:
                ret = this.value + "grad";
                break;
            case RAD:
                ret = this.value + "rad";
                break;
        }
        return ret;
    }

    public enum Unit {
        GRAD, RAD, DEG
    }
}
