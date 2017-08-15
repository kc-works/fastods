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

package com.github.jferard.fastods.it;

import com.github.jferard.fastods.*;
import com.github.jferard.fastods.style.TableCellStyle;
import com.github.jferard.fastods.style.TableColumnStyle;
import com.github.jferard.fastods.style.TableRowStyle;
import com.github.jferard.fastods.util.SimpleLength;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;
import java.util.logging.Logger;

/**
 * @author Julien Férard
 */
public class OdsFileCreationWithThreeTablesAndFlushIT {
	@BeforeClass
	public static final void beforeClass() {
		final File generated_files = new File("generated_files");
		if (generated_files.exists())
			return;

		generated_files.mkdir();
	}
	private Logger logger;
	private OdsFactory odsFactory;
	private Random random;

	@Before
	public void setUp() {
		this.logger = Logger.getLogger("OdsFileCreation");
		this.odsFactory = OdsFactory.create(this.logger, Locale.US);
		this.random = new Random(0L);
	}

	@Test
	public final void test3Tables() throws FastOdsException, IOException {
		this.logger.info("Filling a 3 tables, 5 rows, 5 columns spreadsheet");
		final long t1 = System.currentTimeMillis();

		final OdsFileWriter writer =
				this.odsFactory.createWriter(new File("generated_files", "fastods_3_tables_with_flush.ods"));
		final OdsDocument document = writer.document();

		try {
			document.setViewSetting("View1", "ZoomValue", "200");

			final TableRowStyle trs = TableRowStyle.builder("rr").rowHeight(SimpleLength.cm(5.0))
					.buildHidden();
			final TableCellStyle tcls = TableCellStyle.builder("cc")
					.backgroundColor("#dddddd").fontWeightBold().build();
			final TableColumnStyle tcns = TableColumnStyle.builder("ccs")
					.columnWidth(SimpleLength.cm(10.0)).defaultCellStyle(tcls).buildHidden();
			final TableCellStyle tcs0 = TableCellStyle.builder("tcs0")
					.backgroundColor("#0000ff").build();
			final TableCellStyle tcs1 = TableCellStyle.builder("tcs1")
					.backgroundColor("#00FF00").build();
			final TableCellStyle tcs2 = TableCellStyle.builder("tcs2")
					.fontWeightBold().build();
			final TableCellStyle tcs3 = TableCellStyle.builder("tcs3")
					.fontStyleItalic().build();
			document.addObjectStyle(trs);
			document.addObjectStyle(tcls);
			document.addObjectStyle(tcns);
			document.addObjectStyle(tcs0);
			document.addObjectStyle(tcs1);
			document.addObjectStyle(tcs2);
			document.addObjectStyle(tcs3);
			document.addChildCellStyle(TableCell.Type.FLOAT);
			document.addChildCellStyle(tcs0, TableCell.Type.FLOAT);
			document.addChildCellStyle(tcs1, TableCell.Type.FLOAT);
			document.addChildCellStyle(tcs2, TableCell.Type.FLOAT);
			document.addChildCellStyle(tcs3, TableCell.Type.FLOAT);
			document.freezeStyles();

			for (int i = 0; i < 3; i++) {
				final Table table = document.addTable("test" + i, 5, 5);
				table.setSettings("View1", "ZoomValue", "200");
				table.setColumnStyle(0, tcns);
				TableRow row = table.getRow(0);
				row.setStyle(trs);
				row.setDefaultCellStyle(tcls);

				row = table.getRow(0);
				row.getOrCreateCell(0).setStringValue("éèà");
				row.getOrCreateCell(1).setStringValue("€€€€");
				row.getOrCreateCell(2).setStringValue("£");
				for (int y = 1; y < 5; y++) {
					row = table.getRow(y);
					final TableCellWalker walker = row.getWalker();
					for (int x = 0; x < 5; x++) {
						walker.setFloatValue(this.random.nextInt(1000));
						if ((y + 1) % 3 == 0) {
							switch (x) {
								case 0:
									walker.setStyle(tcs0);
									break;
								case 1:
									walker.setStyle(tcs1);
									break;
								case 2:
									walker.setStyle(tcs2);
									break;
								case 3:
									walker.setStyle(tcs3);
									break;
								default:
									break;
							}
						} else if (y == 6) {
							switch (x) {
								case 0:
									walker.setBooleanValue(true);
									break;
								case 1:
									walker.setCurrencyValue(150.5, "EUR");
									walker.setTooltip("That's a tooltip !");
									break;
								case 2:
									walker.setDateValue(Calendar.getInstance());
									break;
								case 3:
									walker.setPercentageValue(70.3);
									break;
								case 4:
									walker.setStringValue("foobar");
									break;
								default:
									break;
							}
						} else if (y == 9) {
							switch (x) {
								case 0:
									walker.setColumnsSpanned(2);
									break;
								case 2:
									walker.setCurrencyValue(-150.5, "€");
									break;
								case 3:
									walker.setStyle(tcls);
									break;
								default:
									walker.setTimeValue(x * 60 * 1000);
							}
						}
						walker.next();
					}
				}
			}
		} finally {
			document.save();
		}
		final long t2 = System.currentTimeMillis();
		this.logger.info("Filled in " + (t2 - t1) + " ms");
	}
}