package com.tectria.converterous;

import java.util.*;

public class UnitData {
	
	//returns number of items, given a type
	public static int getLength(String type) {
		final char typechar = type.charAt(0);
		switch(typechar) {
			case 'M':
				return massUnits.size();
			case 'V':
				return volumeUnits.size();
			case 'D':
				return distanceUnits.size();
			case 'T':
				if(type == "Temperature") {
					return temperatureUnits.size();
				}
				return timeUnits.size();
			case 'U':
				return unitTypes.size();
			default:
				return allUnits.size();
		}
	}
	
	//returns abbreviation, given a unit name
	public static String getAbv(String unit) {
		return allUnitsAbv.get(allUnits.indexOf(unit));
	}

	//returns unit name, given abbreviation
	public static String getName(String abv) {
		return allUnits.get(allUnitsAbv.indexOf(abv));
	}
	
	//returns unit type, given a unit name
	public static String getType(String unit) {
		return allUnitTypes.get(allUnits.indexOf(unit));
	}
	
	//returns unit name, given type and index
		public static String[] getUnitAtIndex(String type, int index, int avoid) {
			final char typechar = type.charAt(0);
			String avoided = "0";
			
			if(index == avoid) {
				++index;
				avoided = "1";
			}
			
			switch(typechar) {
				case 'M':
					return new String[] {massUnits.get(index), avoided};
				case 'V':
					return new String[] {volumeUnits.get(index), avoided};
				case 'D':
					return new String[] {distanceUnits.get(index), avoided};
				case 'T':
					if(type == "Temperature") {
						return new String[] {temperatureUnits.get(index), avoided};
					}
					return new String[] {timeUnits.get(index), avoided};
				default:
					return new String[] {allUnits.get(index), avoided};
			}
		}
	
	//returns unit name, given type and index
	public static String getUnitAtIndex(String type, int index) {
		final char typechar = type.charAt(0);
		switch(typechar) {
			case 'M':
				return massUnits.get(index);
			case 'V':
				return volumeUnits.get(index);
			case 'D':
				return distanceUnits.get(index);
			case 'T':
				if(type == "Temperature") {
					return temperatureUnits.get(index);
				}
				return timeUnits.get(index);
			default:
				return allUnits.get(index);
		}
	}
	
	//returns unit abbreviation, given type and index
		public static String getAbvAtIndex(String type, int index) {
			final char typechar = type.charAt(0);
			switch(typechar) {
				case 'M':
					return massUnitsAbv.get(index);
				case 'V':
					return volumeUnitsAbv.get(index);
				case 'D':
					return distanceUnitsAbv.get(index);
				case 'T':
					if(type == "Temperature") {
						return temperatureUnitsAbv.get(index);
					}
					return timeUnitsAbv.get(index);
				default:
					return allUnitsAbv.get(index);
			}
		}
	
	//returns type name, given index
	public static String getTypeAtIndex(int index) {
		return unitTypes.get(index);
	}
	
	public static boolean getIsSIFor(int type, int local_index) {
		String type_name = unitTypes.get(type);
		String unit = getUnitAtIndex(type_name, local_index);
		return allUnitsSI.get(allUnits.indexOf(unit)).booleanValue();
	}
	
	//returns ArrayList of units, given a type
	public static ArrayList<String> getUnitsFor(String type) {
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
			default:
				return allUnits;
		}
	}
	
	//returns ArrayList of unit abbreviations, given a type
		public static ArrayList<String> getAbvsFor(String type) {
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
	private static final ArrayList<String> allUnitTypes = new ArrayList<String>(Arrays.asList(
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
