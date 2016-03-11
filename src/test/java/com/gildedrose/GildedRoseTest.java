package com.gildedrose;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Paths;

import org.junit.Ignore;
import org.junit.Test;

import com.github.approval.Approvals;

public class GildedRoseTest {

    @Test
    @Ignore
    public void foo() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("fixme", app.items[0].name);
    }

    @Test
	public void approvalTest() throws Exception {
    	ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(byteOut);
		new TexttestFixture().run(new String[]{"20"}, printStream);
		printStream.flush();
		String text = new String(byteOut.toByteArray());
		Approvals.verify(text, Paths.get("./resources/test/gildedRose.txt"));
	}

}
