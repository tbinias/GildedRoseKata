package com.gildedrose;

class GildedRose {

	private static final int MAX_QUALITY = 50;
	private static final int MIN_QUALITY = 0;

	private static final String CONJURED = "Conjured Mana Cake";
	private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
	private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
	private static final String AGED_BRIE = "Aged Brie";

	Item[] items;

	public GildedRose(Item[] items) {
		this.items = items;
	}

	public void updateQuality() {
		for (Item item : items) {
			if (isLegendary(item)) {
				continue;
			}

			item.sellIn = item.sellIn - 1;
			updateQuality(item);
		}
	}

	private void updateQuality(Item item) {
		int qualityChange = computeQualityChange(item);

		if (isIncreasingQuality(item)) {
			item.quality += qualityChange;
		} else {
			item.quality -= qualityChange;
		}

		ensureQualityLimits(item);
	}

	private int computeQualityChange(Item item) {
		if (item.name.equals(BACKSTAGE_PASSES)) {
			if (isSellByDatePassed(item)) {
				return -item.quality;
			} else if (item.sellIn < 5) {
				return 3;
			} else if (item.sellIn < 10) {
				return 2;
			}
		} else if (item.name.equals(CONJURED)) {
			return 2;
		}

		if (isSellByDatePassed(item)) {
			return 2;
		}
		return 1;
	}

	private boolean isSellByDatePassed(Item item) {
		return item.sellIn < 0;
	}

	private boolean isLegendary(Item item) {
		return item.name.equals(SULFURAS);
	}

	private void ensureQualityLimits(Item item) {
		if (item.quality < MIN_QUALITY) {
			item.quality = MIN_QUALITY;
		} else if (item.quality > MAX_QUALITY) {
			item.quality = MAX_QUALITY;
		}
	}

	private boolean isIncreasingQuality(Item item) {
		return item.name.equals(AGED_BRIE) || item.name.equals(BACKSTAGE_PASSES);
	}
}
