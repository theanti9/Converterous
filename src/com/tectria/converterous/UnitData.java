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
		"milligram",
		"gram",
		"kilogram",
		"pint",
		"quart",
		"gallon",
		"inch",
		"foot",
		"mile",
		"celsius",
		"fahrenheit",
		"kelvin"
	));
	
	/*
	 * All Units (Used for quick lookups)
	 */
	private static final ArrayList<Boolean> allUnitsSI = new ArrayList<Boolean>(Arrays.asList(
		false,
		false,
		false,
		false,
		false,
		false,
		false,
		false,
		false,
		false,
		false,
		false,
		false,
		false,
		true
	));
	
	/*
	 * All Unit Abbreviations (Used for quick lookups)
	 */
	private static final ArrayList<String> allUnitsAbv = new ArrayList<String>(Arrays.asList(
		"h",
		"m",
		"s",
		"mg",
		"mg",
		"g",
		"kg",
		"pt",
		"qt",
		"gal",
		"in",
		"ft",
		"mi",
		"°C",
		"°F",
		"°K",
		"°R"
	));
	
	/*
	 * All Unit Types (Used for quick lookups)
	 */
	private static final ArrayList<String> allUnitsType = new ArrayList<String>(Arrays.asList(
		"Time",
		"Time",
		"Time",
		"Mass",
		"Mass",
		"Mass",
		"Volume",
		"Volume",
		"Volume",
		"Distance",
		"Distance",
		"Distance",
		"Temperature",
		"Temperature",
		"Temperature",
		"Temperature",
		"Radioactivity",
		"Radioactivity",
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
		"kilogram"
	));
	
	private static final ArrayList<String> massUnitsAbv = new ArrayList<String>(Arrays.asList(
		"mg", //milligram
		"g", //gram
		"kg" //kilogram
	));
	
	
	/*
	 * Volume Units
	 */
	private static final ArrayList<String> volumeUnits = new ArrayList<String>(Arrays.asList(
		"pint",
		"quart",
		"gallon"
	));
	
	private static final ArrayList<String> volumeUnitsAbv = new ArrayList<String>(Arrays.asList(
		"pt", //pint
		"qt", //quart
		"gal" //gallon
	));
	
	
	/*
	 * Distance Units
	 */
	private static final ArrayList<String> distanceUnits = new ArrayList<String>(Arrays.asList(
		"inch",
		"foot",
		"mile"
	));
	
	private static final ArrayList<String> distanceUnitsAbv = new ArrayList<String>(Arrays.asList(
		"in", //inch
		"ft", //foot
		"mi" //mine
	));
	
	
	/*
	 * Time Units
	 */
	private static final ArrayList<String> timeUnits = new ArrayList<String>(Arrays.asList(
		"hour",
		"minute",
		"second"
	));
	
	private static final ArrayList<String> timeUnitsAbv = new ArrayList<String>(Arrays.asList(
		"h", //hour
		"m", //minute
		"s" //second
	));
}
