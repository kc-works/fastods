/*******************************************************************************
 * FastODS - a Martin Schulz's SimpleODS fork
 *    Copyright (C) 2016 J. Férard <https://github.com/jferard>
 * SimpleODS - A lightweight java library to create simple OpenOffice spreadsheets
 *    Copyright (C) 2008-2013 Martin Schulz <mtschulz at users.sourceforge.net>
 *
 * This file is part of FastODS.
 *
 * FastODS is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FastODS is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package com.github.jferard.fastods.tool;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import com.github.jferard.fastods.util.FastOdsXMLEscaper;
import com.github.jferard.fastods.util.XMLUtil;

/**
 * A simple utility class for : default dependencies creation, open file.
 *
 * @author Julien Férard
 */
public final class FastOds {
	private static XMLUtil xmlUtil;

	/**
	 * @return the default XMLUtil.
	 */
	public static XMLUtil getXMLUtil() {
		if (FastOds.xmlUtil == null) {
			final FastOdsXMLEscaper xmlEscaper = new FastOdsXMLEscaper();
			FastOds.xmlUtil = new XMLUtil(xmlEscaper);
		}
		return FastOds.xmlUtil;
	}

	/**
	 * Opens a file with the default application.
	 *
	 * @param f
	 *            the file to open
	 * @return true if succeeded, false otherwise.
	 */
	public static boolean openFile(final File f) {
		try {
			Desktop.getDesktop().open(f);
			return true;
		} catch (final IOException e) {
			return false;
		}
	}

	private FastOds() {
	}
}