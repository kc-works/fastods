/* *****************************************************************************
 * FastODS - a Martin Schulz's SimpleODS fork
 *    Copyright (C) 2016 J. Férard <https://github.com/jferard>
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
 * ****************************************************************************/
package com.github.jferard.fastods.odselement;

import com.github.jferard.fastods.Table;
import com.github.jferard.fastods.odselement.config.ConfigBlock;
import com.github.jferard.fastods.util.XMLUtil;
import com.github.jferard.fastods.util.ZipUTF8Writer;

import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;

/**
 * 3.1.3.5 office:document-settings and 3.10 office:settings
 *
 * A typical {@code settings.xml} file has two {@code config-item-set}s:
 * <ul>
 *     <li>{@code ooo:view-settings}
 *     		<ul>
 *     		 	   <li>{@code config-item}s for the view settings</li>
 *     		 	   <li>{@code config-item-map-indexed} with a {@code config-item-map-entry} per view
 *     					<ul>
 *     		 	   			<li>{@code config-item}s of the wiew</li>
 *     		 	   			<li>a {@code config-item-map-named} with a {@code config-item-map-entry} per table
 * 		    					<ul>
 *     				 	   			<li>{@code config-item}s of the table in the wiew</li>
 * 		    					</ul>
 *     		 	   			</li>
 *     					</ul>
 *     		 	   </li>
 *     		</ul>
 *     	<li>{@code ooo:configuration-settings}
 *     		<ul>
 *     		    <li>{@code config-item}s for the configuration settings</li>
 *     		</ul>
 *     	</li>
 * </ul>
 * *
 *
 * @author Julien Férard
 * @author Martin Schulz
 */
@SuppressWarnings("PMD.CommentRequired")
public class SettingsElement implements OdsElement {
	private final Settings settings;
	private List<Table> tables;

	static SettingsElement create() {
		return new SettingsElement(new Settings());
	}

	SettingsElement(Settings settings) {
		this.settings = settings;
	}

	public void setTables(final List<Table> tables) {
		this.settings.setTables(tables);
	}

	@Override
	public void write(final XMLUtil util, final ZipUTF8Writer writer)
			throws IOException {
		writer.putNextEntry(new ZipEntry("settings.xml"));
		writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
		writer.write(
				"<office:document-settings xmlns:office=\"urn:oasis:names:tc:opendocument:xmlns:office:1.0\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" xmlns:config=\"urn:oasis:names:tc:opendocument:xmlns:config:1.0\" xmlns:ooo=\"http://openoffice.org/2004/office\" office:version=\"1.1\">");
		writer.write("<office:settings>");
		for (ConfigBlock block : this.settings.getRootBlocks())
			block.appendXML(util, writer);
		writer.write("</office:settings>");
		writer.write("</office:document-settings>");
		writer.flush();
		writer.closeEntry();
	}

	public void setActiveTable(Table table) {
		this.settings.setActiveTable(table);
	}

	public void setViewSettings(String viewId, String item, String value) {
		this.settings.setViewSettings(viewId, item, value);
	}
}
