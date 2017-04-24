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

package com.github.jferard.fastods.style;

public class TableStyleBuilder {
	private PageStyle pageStyle;
	private final String name;

	/**
	 * @param name the name of the table style (19.498.2)
	 */
	TableStyleBuilder(final String name) {
		if (name == null)
			throw new IllegalArgumentException();

		this.name = name;
		this.pageStyle = PageStyle.DEFAULT_MASTER_PAGE_STYLE;
	}

	public TableStyle build() {
		return new TableStyle(this.name, this.pageStyle);
	}

	public TableStyleBuilder pageStyle(
			final PageStyle masterPageStyle) {
		this.pageStyle = masterPageStyle;
		return this;
	}
}