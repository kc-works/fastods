/*
 * FastODS - a Martin Schulz's SimpleODS fork
 *    Copyright (C) 2016-2017 J. Férard <https://github.com/jferard>
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

package com.github.jferard.fastods.datastyle;

import com.github.jferard.fastods.TableCell;

import java.util.EnumMap;

/**
 * The {@code DataStyles} class represents a set of {@code DataStyle}s.
 */
public class DataStyles {
	private final BooleanStyle booleanDataStyle;
	private final CurrencyStyle currencyDataStyle;
	private final EnumMap<TableCell.Type, DataStyle> dataStyleByType;
	private final DateStyle dateDataStyle;
	private final FloatStyle numberDataStyle;
	private final PercentageStyle percentageDataStyle;
	private final TimeStyle timeDataStyle;

	/**
	 * @param booleanDataStyle the style for booleans
	 * @param currencyDataStyle the style for currencies
	 * @param dateDataStyle the style for dates
	 * @param numberDataStyle the style for numbers
	 * @param percentageDataStyle the style for percentages
	 * @param timeDataStyle the style for times
	 */
	public DataStyles(BooleanStyle booleanDataStyle, CurrencyStyle currencyDataStyle,
					  DateStyle dateDataStyle,
					  FloatStyle numberDataStyle, PercentageStyle percentageDataStyle, TimeStyle timeDataStyle) {
		this.booleanDataStyle = booleanDataStyle;
		this.currencyDataStyle = currencyDataStyle;
		this.dateDataStyle = dateDataStyle;
		this.numberDataStyle = numberDataStyle;
		this.percentageDataStyle = percentageDataStyle;
		this.timeDataStyle = timeDataStyle;

		this.dataStyleByType = new EnumMap<TableCell.Type, DataStyle>(TableCell.Type.class);
		this.dataStyleByType.put(TableCell.Type.BOOLEAN, this.booleanDataStyle);
		this.dataStyleByType.put(TableCell.Type.CURRENCY, this.currencyDataStyle);
		this.dataStyleByType.put(TableCell.Type.DATE, this.dateDataStyle);
		this.dataStyleByType.put(TableCell.Type.FLOAT, this.numberDataStyle);
		this.dataStyleByType.put(TableCell.Type.PERCENTAGE, this.percentageDataStyle);
		this.dataStyleByType.put(TableCell.Type.TIME, this.timeDataStyle);
	}

	/**
	 * @return the style for booleans
	 */
	public BooleanStyle getBooleanDataStyle() {
		return booleanDataStyle;
	}

	/**
	 * @return the style for currencies
	 */
	public CurrencyStyle getCurrencyDataStyle() {
		return currencyDataStyle;
	}

	/**
	 * @param type a data style type
	 * @return the data style for the given type
	 */
	public DataStyle getDataStyle(final TableCell.Type type) {
		return this.dataStyleByType.get(type);
	}

	/**
	 * @return the style for dates
	 */
	public DateStyle getDateDataStyle() {
		return dateDataStyle;
	}

	/**
	 * @return the style for numbers
	 */
	public FloatStyle getNumberDataStyle() {
		return numberDataStyle;
	}

	/**
	 * @return the style for percentages
	 */
	public PercentageStyle getPercentageDataStyle() {
		return percentageDataStyle;
	}

	/**
	 * @return the style for times
	 */
	public TimeStyle getTimeDataStyle() {
		return timeDataStyle;
	}
}