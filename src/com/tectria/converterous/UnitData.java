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
		return allUnitClasses.get(allUnits.indexOf(unit)).booleanValue();
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
	private static final ArrayList<Boolean> allUnitClasses = new ArrayList<Boolean>(Arrays.asList(
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
	private static final ArrayList<String> allUnitTypes = new ArrayList<String>(Arrays.asList(
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
