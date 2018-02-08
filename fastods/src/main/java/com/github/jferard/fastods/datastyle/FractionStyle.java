/*
 * FastODS - a Martin Schulz's SimpleODS fork
 *    Copyright (C) 2016-2018 J. Férard <https://github.com/jferard>
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

import java.io.IOException;

import com.github.jferard.fastods.Color;
import com.github.jferard.fastods.odselement.OdsElements;
import com.github.jferard.fastods.util.XMLUtil;

/**
 * WHERE ? content.xml/office:document-content/office:automatic-styles/number:
 * number-style
 * content.xml/office:document-content/office:automatic-styles/number:
 * percentage-style
 * styles.xml/office:document-styles/office:styles/number:number-style
 * styles.xml/office:document-styles/office:styles/number:percentage- style
 *
 * @author Julien Férard
 * @author Martin Schulz
 *
 */
public class FractionStyle implements NumberStyle, DataStyle {
	private final NumberStyleHelper numberStyle;
	private final int minDenominatorDigits;
	private final int minNumeratorDigits;

	/**
	 * Create a new fraction style
	 * @param numberStyle the embedded number style
	 * @param minNumeratorDigits the minimum digits for numerator
	 * @param minDenominatorDigits  the minimum digits for denominator
	 */
	FractionStyle(final NumberStyleHelper numberStyle, final int minNumeratorDigits,
                  final int minDenominatorDigits) {
		this.numberStyle = numberStyle;
		this.minNumeratorDigits = minNumeratorDigits;
		this.minDenominatorDigits = minDenominatorDigits;
	}

	@Override
	public void appendXMLRepresentation(final XMLUtil util, final Appendable appendable) throws IOException {
		final CharSequence number = this.computeNumberTag(util);
		this.numberStyle.appendXMLHelper(util, appendable, "number-style", number);
	}

	@Override
	public boolean isHidden() {
		return this.numberStyle.isHidden();
	}

	private CharSequence computeNumberTag(final XMLUtil util)
			throws IOException {
		final StringBuilder number = new StringBuilder();
		number.append("<number:fraction");
		util.appendAttribute(number, "number:min-numerator-digits",
				this.minNumeratorDigits);
		util.appendAttribute(number, "number:min-denominator-digits",
				this.minDenominatorDigits);
		this.numberStyle.appendNumberAttribute(util, number);
		number.append("/>");
		return number;
	}

	@Override
	public boolean getGroupThousands() {
		return this.numberStyle.getGroupThousands();
	}

	@Override
	public int getMinIntegerDigits() {
		return this.numberStyle.getMinIntegerDigits();
	}

	@Override
	public Color getNegativeValueColor() {
		return this.numberStyle.getNegativeValueColor();
	}

	@Override
	public boolean isVolatileStyle() {
		return this.numberStyle.isVolatileStyle();
	}

	@Override
	public String getName() {
		return this.numberStyle.getName();
	}

	@Override
	public String getCountryCode() {
		return this.numberStyle.getCountryCode();
	}

	@Override
	public String getLanguageCode() {
		return this.numberStyle.getLanguageCode();
	}

	@Override
	public void addToElements(final OdsElements odsElements) {
		odsElements.addDataStyle(this);
	}
}
