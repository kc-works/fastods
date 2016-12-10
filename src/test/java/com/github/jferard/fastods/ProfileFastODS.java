/*
 * FastODS - a Martin Schulz's SimpleODS fork
 *    Copyright (C) 2016 J. Férard
 * SimpleODS - A lightweight java library to create simple OpenOffice spreadsheets
*    Copyright (C) 2008-2013 Martin Schulz <mtschulz at users.sourceforge.net>
*
*    This program is free software: you can redistribute it and/or modify
*    it under the terms of the GNU General Public License as published by
*    the Free Software Foundation, either version 3 of the License, or
*    (at your option) any later version.
*
*    This program is distributed in the hope that it will be useful,
*    but WITHOUT ANY WARRANTY; without even the implied warranty of
*    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*    GNU General Public License for more details.
*
*    You should have received a copy of the GNU General Public License
*    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package com.github.jferard.fastods;

import java.io.IOException;
import java.util.Random;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

/**
 * @author Julien Férard
 *
 *         Usage : launch jvisualvm. mvn
 *         -Dmaven.surefire.debug="-agentpath:\"C:/Program
 *         Files/Java/visualvm_138/profiler/lib/deployed/jdk16/windows-amd64/profilerinterface.dll\"=\"C:\Program
 *         Files\Java\visualvm_138\profiler\lib\",5140"
 *         -Dtest=ProfileFastOds#testFast test
 */
public class ProfileFastODS {
	private static final int COL_COUNT = 40;
	private static final int ROW_COUNT = 80000;
	@Rule
	public TestName name = new TestName();
	private Logger logger;

	private Random random;
	private long t1;

	@Before
	public final void setUp() {
		this.logger = Logger.getLogger("OdsFileCreation");
		this.random = new Random();
		this.logger.info(this.name.getMethodName() + " : filling a "
				+ ProfileFastODS.ROW_COUNT + " rows, "
				+ ProfileFastODS.COL_COUNT + " columns spreadsheet");
		this.t1 = System.currentTimeMillis();
	}

	@After
	public final void tearDown() {
		final long t2 = System.currentTimeMillis();
		this.logger.info("Filled in " + (t2 - this.t1) + " ms");
	}

	@Test
	public final void testFast() throws IOException {
		final OdsFile file = OdsFile.create("fastods_profile.ods");
		final Table table = file.addTable("test", ProfileFastODS.ROW_COUNT,
				ProfileFastODS.COL_COUNT);

		for (int y = 0; y < ProfileFastODS.ROW_COUNT; y++) {
			final HeavyTableRow row = table.nextRow();
			final TableCellWalker walker = row.getWalker();
			for (int x = 0; x < ProfileFastODS.COL_COUNT; x++) {
				walker.lastCell();
				walker.setFloatValue(this.random.nextInt(1000));
			}
			if (y % (ProfileFastODS.ROW_COUNT / 50) == 0)
				this.logger.info("Row " + y);
		}

		file.save();
	}
	
	@Test
	public final void smallTestFast() throws IOException {
		final OdsFile file = OdsFile.create("fastods_profile.ods");
		final Table table = file.addTable("test", ProfileFastODS.ROW_COUNT/4,
				ProfileFastODS.COL_COUNT/4);

		for (int y = 0; y < ProfileFastODS.ROW_COUNT/4; y++) {
			final HeavyTableRow row = table.nextRow();
			final TableCellWalker walker = row.getWalker();
			for (int x = 0; x < ProfileFastODS.COL_COUNT/4; x++) {
				walker.lastCell();
				walker.setFloatValue(this.random.nextInt(1000));
			}
			if (y % (ProfileFastODS.ROW_COUNT/200) == 0)
				this.logger.info("Row " + y);
		}

		file.save();
	}
}