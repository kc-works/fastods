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

package com.github.jferard.fastods.tool;

import com.github.jferard.fastods.OdsDocument;
import com.github.jferard.fastods.odselement.ScriptEvent;
import com.github.jferard.fastods.odselement.ScriptEventListener;
import com.github.jferard.fastods.util.MacroLibrary;
import com.github.jferard.fastods.util.MacroLibraryContainer;
import com.github.jferard.fastods.util.MacroModule;
import com.github.jferard.fastods.util.XMLUtil;

import java.io.IOException;

/**
 * A helper
 */
public class MacroHelper {
    /**
     * @param document the document
     */
    public void addRefreshMacro(final OdsDocument document) throws IOException {
        MacroLibraryContainer.create(MacroLibrary.builder().modules(MacroModule
                .createBasic("FastODS",
                        "REM FastODS (C) J. Férard\n" + "REM Auto update macro\n\n" +
                                "Sub Refresh\n" +
                                "\tfor each oElem in ThisComponent.DatabaseRanges\n" +
                                "\t\toElem.refresh\n" + "\tnext oElem\n" + "End Sub\n")).build())
                .add(document);
        document.addEvents(ScriptEventListener.create(ScriptEvent.ON_LOAD, "Standard.FastODS.Refresh"));
    }
}
