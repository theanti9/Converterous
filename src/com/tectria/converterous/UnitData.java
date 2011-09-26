package com.tectria.converterous;

import java.util.*;

public class UnitData {
	
	private static ArrayList<String> arrayFor(int typeindex) {
		String type = unitTypes.get(typeindex);
		final char typechar = type.charAt(0);
		switch(typechar) {
			case 'M':
				return massUnits;
			case 'V':
				return volumeUnits;
			case 'D':
				return distanceUnits;
			case 'T':
				if(type == "Temperature") {
					return temperatureUnits;
				}
				return timeUnits;
			case 'U':
				return unitTypes;
			default:
				return allUnits;
		}
	}
	
	private static ArrayList<String> arrayFor(String type) {
		final char typechar = type.charAt(0);
		switch(typechar) {
			case 'M':
				return massUnits;
			case 'V':
				return volumeUnits;
			case 'D':
				return distanceUnits;
			case 'T':
				if(type == "Temperature") {
					return temperatureUnits;
				}
				return timeUnits;
			case 'U':
				return unitTypes;
			default:
				return allUnits;
		}
	}
	public static boolean getIsSIFor(int type, int local_index) {
		String type_name = unitTypes.get(type);
		String unit = getName(type_name, local_index);
		return allUnitsSI.get(allUnits.indexOf(unit)).booleanValue();
	}

	private static ArrayList<String> arrayFor(int typeindex, boolean abv) {
		String type = unitTypes.get(typeindex);
		final char typechar = type.charAt(0);
		switch(typechar) {
			case 'M':
				return massUnitsAbv;
			case 'V':
				return volumeUnitsAbv;
			case 'D':
				return distanceUnitsAbv;
			case 'T':
				if(type == "Temperature") {
					return temperatureUnitsAbv;
				}
				return timeUnitsAbv;
			default:
				return allUnitsAbv;
		}
	}
	
	//returns number of items, given a type index
	public static int getLength(int type) {
		return arrayFor(type).size();
	}
	
	//returns number of items, given a type name
	public static int getLength(String type) {
		return arrayFor(type).size();
	}
	
	//returns abbreviation, given a unit name
	public static String getAbv(String unit) {
		return allUnitsAbv.get(allUnits.indexOf(unit));
	}
	
	//returns abbreviation, given a type and index
	public static String getAbv(int type, int index) {
		return arrayFor(type, true).get(index);
	}

	//returns unit name, given abbreviation
	public static String getName(String abv) {
		return allUnits.get(allUnitsAbv.indexOf(abv));
	}
	
	//returns unit name, given type index and index
	public static String getName(int type, int index) {
		return arrayFor(type).get(index);
	}
	
	//returns unit name, given type string and index
	public static String getName(String type, int index) {
		return arrayFor(type).get(index);
	}
	
	//returns unit name + avoided, given type string, index, and avoided
	public static String[] getName(String type, int index, int avoid) {

		if(index == avoid) {
			return new String[] {arrayFor(type).get(index + 1), "1"};
		} else {
			return new String[] {arrayFor(type).get(index), "0"};
		}
		
	}
	
	//returns unit type, given a unit name or abbreviation
	public static String getType(String unit, boolean abv) {
		if(abv) {
			return allUnitsType.get(allUnitsAbv.indexOf(unit));
		} else {
			return allUnitsType.get(allUnits.indexOf(unit));
		}
	}
	
	//returns unit type, given a type index
	public static String getType(int index) {
		return unitTypes.get(index);
	}
	
	//returns unit SI boolean, given a unit name or abbreviation
	public static boolean getSI(String unit, boolean abv) {
		if(abv) {
			return allUnitsSI.get(allUnitsAbv.indexOf(unit));
		} else {
			return allUnitsSI.get(allUnits.indexOf(unit));
		}
	}
	
	//returns unit SI boolean, given a type and index
	public static boolean getSI(int type, int index) {
		return allUnitsSI.get(allUnits.indexOf(arrayFor(type).get(index)));
	}
	
	/*
	 * Array of unit types. i.e, Mass, Volume, Length, etc
	 */
	private static final ArrayList<String> unitTypes = new ArrayList<String>(Arrays.asList(
		"Mass",
		"Volume",
		"Distance",
		"Time",
		"Temperature",
		"Radioactivity"
	));
	
	
	/*
	 * All Units (Used for quick lookups)
	 */
	private static final ArrayList<String> allUnits = new ArrayList<String>(Arrays.asList(
		"hour",
		"minute",
		"second",
		"day",
		"week",
		"year",
		"milligram",
		"gram",
		"kilogram",
		"pound",
		"ton (US)",
		"ton (uk)",
		"ton (metric)",
		"ounce",
		"ounce (liquid, US)",
		"ounce (liquid, UK)",
		"pint (liquid, US)",
		"pint (liquid, UK)",
		"pint (dry, US)",
		"pint (dry, UK)",
		"quart (liquid, US)",
		"quart (liquid, Uk)",
		"quart (dry, US)",
		"quart (dry, UK)",
		"gallon (liquid, US)",
		"gallon (liquid, UK)",
		"gallon (dry, US)",
		"gallon (dry, UK)",
		"inch",
		"foot",
		"mile",
		"nautical mile",
		"angstrom",
		"astronomical unit",
		"light year",
		"parsec",
		"celsius",
		"fahrenheit",
		"kelvin",
		"rankine",
		"curie",
		"rutherford"
	));
	
	private static final ArrayList<String> allUnitsConst = new ArrayList<String>(Arrays.asList(
		"HOUR",
		"MINUTE",
		"SECOND",
		"DAY",
		"WEEK",
		"YEAR_CALENDAR",
		"MILLIGRAM",
		"GRAM",
		"KILOGRAM",
		"POUND",
		"TON_US",
		"TON_UK",
		"METRIC_TON",
		"OUNCE",
		"OUNCE_LIQUID_US",
		"OUNCE_LIQUID_UK",
		"PINT_LIQUID_US",
		"PINT_LIQUID_UK",
		"PINT_DRY_US",
		"PINT_DRY_UK",
		"QUART_LIQUID_US",
		"QUART_LIQUID_UK",
		"QUART_DRY_US",
		"QUART_DRY_UK",
		"GALLON_LIQUID_US",
		"GALLON_LIQUID_UK",
		"GALLON_DRY_US",
		"GALLON_DRY_UK",
		"INCH",
		"FOOT",
		"MILE",
		"NAUTICAL_MILE",
		"ANGSTROM",
		"ASTONOMICAL_UNIT",
		"LIGHT_YEAR",
		"PARSEC",
		"CELSIUS",
		"FAHRENHEIT",
		"KELVIN",
		"RANKINE",
		"CURIE",
		"RUTHERFORD"
	));
	
	/*
	 * All Units (Used for quick lookups)
	 */
	private static final ArrayList<Boolean> allUnitsSI = new ArrayList<Boolean>(Arrays.asList(
		false, // Hour
		false, // Minute
		false, // Second
		false, // Day
		false, // Week
		false, // Year
		true, // Milligram
		true, // Gram
		true, // Kilogram
		false, // pound
		false, // ton us
		false, // ton uk
		false, // ton metric
		false, // ounce
		false, // ounce liquid us
		false, // ounce liquid uk
		false, // pint liquid us
		false, // pint liquid uk,
		false, // pint dry us
		false, // pint dry uk
		false, // quart liquid us
		false, // quart liquid uk
		false, // quart dry us
		false, // quart dry uk
		false, // gallon liquid us
		false, // gallon liquid uk
		false, // gallon dry us
		false, // gallon dry uk
		false, // inch
		false, // foot
		false, // mile
		false, // nautical mile
		false, // angstrom
		false, // astronomical unit
		false, // light year
		false, // parsec
		true, // celsius
		false, // fahrenheit
		true, // kelvin
		false, // rankine
		false, // curie
		false // rutherford
	));
	
	/*
	 * All Unit Abbreviations (Used for quick lookups)
	 */
	private static final ArrayList<String> allUnitsAbv = new ArrayList<String>(Arrays.asList(
		"h",
		"m",
		"s",
		"day",
		"week",
		"year",
		"mg",
		"g",
		"kg",
		"lbs",
		"ST",
		"ton",
		"t",
		"oz",
		"fl oz",
		"fl oz",
		"pt",
		"pt",
		"pt",
		"pt",
		"qt",
		"qt",
		"qt",
		"qt",
		"gal",
		"gal",
		"gal",
		"gal",
		"in",
		"ft",
		"mi",
		"NM",
		"Å",
		"AU",
		"ly",
		"pc",
		"°C",
		"°F",
		"°K",
		"°R",
		"Ci",
		"rd"
	));
	
	/*
	 * All Unit Types (Used for quick lookups)
	 */
	private static final ArrayList<String> allUnitsType = new ArrayList<String>(Arrays.asList(
		"Time",
		"Time",
		"Time",
		"Time",
		"Time",
		"Time",
		"Mass",
		"Mass",
		"Mass",
		"Mass",
		"Mass",
		"Mass",
		"Mass",
		"Mass",
		"Volume",
		"Volume",
		"Volume",
		"Distance",
		"Distance",
		"Distance",
		"Distance",
		"Distance",
		"Distance",
		"Distance",
		"Distance",
		"Temperature",
		"Temperature",
		"Temperature",
		"Temperature",
		"Radioactivity",
		"Radioactivity"
	));
	
	/*
	 * Temperature Units
	 */
	private static final ArrayList<String> temperatureUnits = new ArrayList<String>(Arrays.asList(
			"Fahrenheit",
			"Celsius",
			"Kelvin",
			"Rankine"
		));
	
	private static final ArrayList<String> temperatureUnitsAbv = new ArrayList<String>(Arrays.asList(
			"°F",
			"°C",
			"°K",
			"°R"
		));
	
	/*
	 * Mass Units
	 */
	private static final ArrayList<String> massUnits = new ArrayList<String>(Arrays.asList(
			"milligram",
			"gram",
			"kilogram",
			"pound",
			"ton (US)",
			"ton (uk)",
			"ton (metric)",
			"ounce"
	));
	
	private static final ArrayList<String> massUnitsAbv = new ArrayList<String>(Arrays.asList(
			"mg",
			"g",
			"kg",
			"lbs",
			"ST",
			"ton",
			"t",
			"oz"
	));
	
	
	/*
	 * Volume Units
	 */
	private static final ArrayList<String> volumeUnits = new ArrayList<String>(Arrays.asList(
			"ounce (liquid, US)",
			"ounce (liquid, UK)",
			"pint (liquid, US)",
			"pint (liquid, UK)",
			"pint (dry, US)",
			"pint (dry, UK)",
			"quart (liquid, US)",
			"quart (liquid, Uk)",
			"quart (dry, US)",
			"quart (dry, UK)",
			"gallon (liquid, US)",
			"gallon (liquid, UK)",
			"gallon (dry, US)",
			"gallon (dry, UK)"
	));
	
	private static final ArrayList<String> volumeUnitsAbv = new ArrayList<String>(Arrays.asList(
			"fl oz",
			"fl oz",
			"pt",
			"pt",
			"pt",
			"pt",
			"qt",
			"qt",
			"qt",
			"qt",
			"gal",
			"gal",
			"gal",
			"gal"
	));
	
	
	/*
	 * Distance Units
	 */
	private static final ArrayList<String> distanceUnits = new ArrayList<String>(Arrays.asList(
			"inch",
			"foot",
			"mile",
			"nautical mile",
			"angstrom",
			"astronomical unit",
			"light year",
			"parsec"
	));
	
	private static final ArrayList<String> distanceUnitsAbv = new ArrayList<String>(Arrays.asList(
			"in",
			"ft",
			"mi",
			"NM",
			"Å",
			"AU",
			"ly",
			"pc",
			"°C",
			"°F",
			"°K",
			"°R"
	));
	
	
	/*
	 * Time Units
	 */
	private static final ArrayList<String> timeUnits = new ArrayList<String>(Arrays.asList(
		"hour",
		"minute",
		"second",
		"day",
		"week",
		"year"
	));
	
	private static final ArrayList<String> timeUnitsAbv = new ArrayList<String>(Arrays.asList(
		"h", //hour
		"m", //minute
		"s", //second
		"day",
		"week",
		"year"
	));
}
