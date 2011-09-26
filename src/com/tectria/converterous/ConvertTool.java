package com.tectria.converterous;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.measure.Measure;
import javax.measure.converter.UnitConverter;
import javax.measure.unit.Unit;


import static javax.measure.unit.NonSI.*;
import static javax.measure.unit.SI.*;

public class ConvertTool {
	
	private String toUnit;
	private String fromUnit;
	//private double toNum;
	private double fromNum;
	//private boolean isSI;
	private boolean fromSI;
	private boolean toSI;
	
	public ConvertTool(String toUnit, String fromUnit, double fromNum, boolean fromSI, boolean toSI) {
		this.toUnit = toUnit;
		this.fromUnit = fromUnit;
		//this.toNum = 0.0;
		this.fromNum = fromNum;
		this.fromSI = fromSI;
		this.toSI = toSI;
	}
	
	public ConvertTool() {
		this.toUnit = null;
		this.fromUnit = null;
		//this.toNum = 0.0;
		this.fromNum = 0.0;
		this.fromSI = true;
		this.toSI = true;
	}
	
	@SuppressWarnings("rawtypes")
	public double convert() {
		//String name = (this.fromUnit + "to" + this.toUnit).replace(" ", "_").replaceAll("[^a-zA-Z0-9_]", "");
		String name = "javax.measure.unit.";
		try {
			String fromSi;
			String toSi;
			if (this.fromSI){
				fromSi = "SI";
			} else {
				fromSi = "NonSI";
			}
			if (this.toSI){
				toSi = "SI";
			} else {
				toSi = "NonSI";
			}
			Class fromUnitClass = Class.forName(name.concat(fromSi));
			Class toUnitClass = Class.forName(name.concat(toSi));
			Field fromUnitField;
			Field toUnitField;
			Unit fromUnit;
			Unit toUnit;
			fromUnitField = fromUnitClass.getField(this.fromUnit);
			toUnitField = toUnitClass.getField(this.toUnit);
			fromUnit = (Unit)fromUnitField.get(fromUnitClass);
			toUnit = (Unit)toUnitField.get(toUnitClass);
			
			return fromUnit.getConverterTo(toUnit).convert(Measure.valueOf(this.fromNum, fromUnit).doubleValue(fromUnit));
			
			//Class fromUnitClass = Class.forName(name.concat(this.fromUnit));
			//Class toUnitClass = Class.forName(name.concat(this.toUnit));
			//Class fromSubClass = Class.forName(name.concat("Dimension"));
//			UnitConverter unitConverter;
//			Class parTypes[] = new Class[1];
//			parTypes[0] = Unit.class;
//			
//			Object getConvertToObject = unitClass.getField(this.fromUnit);
//			Object toUnitClass = unitClass.getField(this.toUnit);
//			Object arg[] = new Object[1];
//			arg[0] = toUnitClass;
//			unitConverter = null;
//			//Object object = getConvertToObject
//			
//			return ((UnitConverter)getConvertToObject).convert(Measure.valueOf(this.fromNum, ));
			
			//unitConverter
			
			
			/*
			Class cla = Class.forName("com.tectria.converterous.ConvertTool");
			Class partypes[] = new Class[1]; 
			partypes[0] = Double.TYPE;
			Method method = cla.getMethod(name, partypes);
			ConvertTool invoke = this;
			Object arg[] = new Object[1];
			arg[0] = this.fromNum;
			Object object = method.invoke(invoke, arg);
			this.toNum = ((Double)object).doubleValue();
			
			return this.toNum;
			*/
			
			
			
			
		} catch (SecurityException e) {
			return 0.0;
		} catch (IllegalAccessException e) {
			return 0.0;
		} catch (IllegalArgumentException e) {
			return 0.0;
		} catch (ClassNotFoundException e) {
			return 0.0;
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			return 0.0;
		}
	}
	
	public void setFromSI(boolean b) {
		this.fromSI = b;
	}
	
	public void setToSI(boolean b) {
		this.toSI = b;
	}
	
	public void setFromNum(double n) {
		this.fromNum = n;
	}
	
	public void setFromUnit(String u) {
		this.fromUnit = u;
	}
	
	public void setToUnit(String u) {
		this.toUnit = u;
	}
	
	public void swapUnits() {
		String tmp = this.toUnit;
		this.toUnit = this.fromUnit;
		this.fromUnit = tmp;
	}
	
	/*
	 * TEMPERATURE
	 */
	
	//Celsius to Fahrenheit
	public double CtoF(double i) {
		return CELSIUS.getConverterTo(FAHRENHEIT).convert(Measure.valueOf(i, CELSIUS).doubleValue(CELSIUS));
	}
	
	//Celsius to Kelvin
	public double CtoK(double i) {
		return CELSIUS.getConverterTo(KELVIN).convert(Measure.valueOf(i, CELSIUS).doubleValue(CELSIUS));
	}
	
	//Celsius to Rankine
	public double CtoR(double i) {
		return CELSIUS.getConverterTo(RANKINE).convert(Measure.valueOf(i, CELSIUS).doubleValue(CELSIUS));
	}
	
	//Fahrenheit to Celsius
	public double FtoC(double i) {
		return FAHRENHEIT.getConverterTo(CELSIUS).convert(Measure.valueOf(i, FAHRENHEIT).doubleValue(FAHRENHEIT));
	}
	
	//Fahrenheit to Kelvin
	public double FtoK(double i) {
		return FAHRENHEIT.getConverterTo(KELVIN).convert(Measure.valueOf(i, FAHRENHEIT).doubleValue(FAHRENHEIT));
	}
	
	//Fahrenheit to Rankine
	public double FtoR(double i) {
		return FAHRENHEIT.getConverterTo(RANKINE).convert(Measure.valueOf(i, FAHRENHEIT).doubleValue(FAHRENHEIT));
	}
	
	//Kelvin to Fahrenheit
	public double KtoF(double i) {
		return KELVIN.getConverterTo(FAHRENHEIT).convert(Measure.valueOf(i, KELVIN).doubleValue(KELVIN));
	}
	
	//Kelvin to Celsius
	public double KtoC(double i) {
		return KELVIN.getConverterTo(CELSIUS).convert(Measure.valueOf(i, KELVIN).doubleValue(KELVIN));
	}

	//Kelvin to Rankine
	public double KtoR(double i) {
		return KELVIN.getConverterTo(RANKINE).convert(Measure.valueOf(i, KELVIN).doubleValue(KELVIN));
	}
	
	//Rankine to Celsius
	public double RtoC(double i) {
		return RANKINE.getConverterTo(CELSIUS).convert(Measure.valueOf(i, RANKINE).doubleValue(RANKINE));
	}
	
	//Rankine to Fahrenheit
	public double RtoF(double i) {
		return RANKINE.getConverterTo(FAHRENHEIT).convert(Measure.valueOf(i, RANKINE).doubleValue(RANKINE));
	}
	
	//Rankine to Kelvin
	public double RtoK(double i) {
		return RANKINE.getConverterTo(KELVIN).convert(Measure.valueOf(i, RANKINE).doubleValue(RANKINE));
	}
	
	/*
	 * MASS
	 */
	
	/*
	 * VOLUME - DRY
	 */
	
	//Pints to quarts
	public double pttoqt(double i) {
		return PINT_DRY_US.getConverterTo(QUART_DRY_US).convert(Measure.valueOf(i, PINT_DRY_US).doubleValue(PINT_DRY_US));
	}
	
	//Pints to gallons
	public double pttogal(double i) {
		return PINT_DRY_US.getConverterTo(GALLON_DRY_US).convert(Measure.valueOf(i, PINT_DRY_US).doubleValue(PINT_DRY_US));
	}
	
	//Quarts to pints
	public double qttopt(double i) {
		return QUART_DRY_US.getConverterTo(PINT_DRY_US).convert(Measure.valueOf(i, QUART_DRY_US).doubleValue(QUART_DRY_US));
	}
	
	//Quarts to gallons
	public double qttogal(double i) {
		return QUART_DRY_US.getConverterTo(GALLON_DRY_US).convert(Measure.valueOf(i, QUART_DRY_US).doubleValue(QUART_DRY_US));

	}

	//Gallons to quarts
	public double galtoqt(double i) {
		return GALLON_DRY_US.getConverterTo(QUART_DRY_US).convert(Measure.valueOf(i, GALLON_DRY_US).doubleValue(GALLON_DRY_US));
	}
	
	//Gallons to pints
	public double galtopt(double i) {
		return GALLON_DRY_US.getConverterTo(PINT_DRY_US).convert(Measure.valueOf(i, GALLON_DRY_US).doubleValue(GALLON_DRY_US));
	}
	
	/*
	 * VOLUME - LIQUID
	 */
	
	//Pints to quarts
	public double fl_pttofl_qt(double i) {
		return PINT_LIQUID_US.getConverterTo(QUART_LIQUID_US).convert(Measure.valueOf(i, PINT_LIQUID_US).doubleValue(PINT_LIQUID_US));
	}
	
	//Pints to gallons
	public double fl_pttofl_gal(double i) {
		return PINT_LIQUID_US.getConverterTo(GALLON_LIQUID_US).convert(Measure.valueOf(i, PINT_LIQUID_US).doubleValue(PINT_LIQUID_US));
	}
	
	//Quarts to pints
	public double fl_qttofl_pt(double i) {
		return QUART_LIQUID_US.getConverterTo(PINT_LIQUID_US).convert(Measure.valueOf(i, QUART_LIQUID_US).doubleValue(QUART_LIQUID_US));
	}
	
	//Quarts to gallons
	public double fl_qttofl_gal(double i) {
		return QUART_LIQUID_US.getConverterTo(GALLON_LIQUID_US).convert(Measure.valueOf(i, QUART_LIQUID_US).doubleValue(QUART_LIQUID_US));

	}

	//Gallons to quarts
	public double fl_galtofl_qt(double i) {
		return GALLON_LIQUID_US.getConverterTo(QUART_LIQUID_US).convert(Measure.valueOf(i, GALLON_LIQUID_US).doubleValue(GALLON_LIQUID_US));
	}
	
	//Gallons to pints
	public double fl_galtofl_pt(double i) {
		return GALLON_LIQUID_US.getConverterTo(PINT_LIQUID_US).convert(Measure.valueOf(i, GALLON_LIQUID_US).doubleValue(GALLON_LIQUID_US));
	}
	
	/*
	 * DISTANCE
	 */
	
	// Inches to Feet
	public double intoft(double i) {
		return INCH.getConverterTo(FOOT).convert(Measure.valueOf(i, INCH).doubleValue(INCH));
	}
	
	// Feet to Inches
	public double fttoin(double i) {
		return FOOT.getConverterTo(INCH).convert(Measure.valueOf(i, FOOT).doubleValue(FOOT));
	}
	
	// Centimeters to Inches
	public double cmtoin(double i) {
		return CENTIMETER.getConverterTo(INCH).convert(Measure.valueOf(i, CENTIMETER).doubleValue(CENTIMETER));
	}
	
	// Inches to Centimeters
	public double intocm(double i) {
		return INCH.getConverterTo(CENTIMETER).convert(Measure.valueOf(i, INCH).doubleValue(INCH));
	}
	
	// Centimeters to Feet
	public double cmtoft(double i) {
		return CENTIMETER.getConverterTo(FOOT).convert(Measure.valueOf(i, CENTIMETER).doubleValue(CENTIMETER));
	}
	
	// Feet to Centimeters
	public double fttocm(double i) {
		return FOOT.getConverterTo(CENTIMETER).convert(Measure.valueOf(i, FOOT).doubleValue(FOOT));
	}
	
	// Inches to Millimeters
	public double intomm(double i) {
		return INCH.getConverterTo(MILLIMETER).convert(Measure.valueOf(i, INCH).doubleValue(INCH));
	}
	
	// Millimeters to Inches
	public double mmtoin(double i) {
		return MILLIMETER.getConverterTo(INCH).convert(Measure.valueOf(i, MILLIMETER).doubleValue(MILLIMETER));
	}
	
	// Millimeters to Centimeters
	public double mmtocm(double i) {
		return MILLIMETER.getConverterTo(CENTIMETER).convert(Measure.valueOf(i, MILLIMETER).doubleValue(MILLIMETER));
	}
	
	// Centimeters to Millimeters
	public double cmtomm(double i) {
		return CENTIMETER.getConverterTo(MILLIMETER).convert(Measure.valueOf(i, CENTIMETER).doubleValue(CENTIMETER));	}
	
	// Millimeters to Feet
	public double mmtoft(double i) {
		return MILLIMETER.getConverterTo(FOOT).convert(Measure.valueOf(i, MILLIMETER).doubleValue(MILLIMETER));
	}
	
	// Feet to Millimeters
	public double fttommm(double i) {
		return FOOT.getConverterTo(MILLIMETER).convert(Measure.valueOf(i, FOOT).doubleValue(FOOT));
	}
	
	// Mile to Feet
	public double mitoft(double i) {
		return MILE.getConverterTo(FOOT).convert(Measure.valueOf(i, MILE).doubleValue(MILE));
	}
	
	// Feet to Mile
	public double fttomi(double i) {
		return i/5280;
	}
	
	// Miles to Inches
	public double mitoin(double i) {
		return MILE.getConverterTo(INCH).convert(Measure.valueOf(i, MILE).doubleValue(MILE));
	}
	
	// Inches to Miles
	public double intomi(double i) {
		return i/63360;
	}
	
	// Miles to Centimeters
	public double mitocm(double i) {
		return MILE.getConverterTo(CENTIMETRE).convert(Measure.valueOf(i, MILE).doubleValue(MILE));
	}
	
	// Miles to Millimeters
	public double mitomm(double i) {
		return MILE.getConverterTo(MILLIMETRE).convert(Measure.valueOf(i, MILE).doubleValue(MILE));
	}
	
	// Millimeters to Miles
	public double mmtomi(double i) {
		return MILLIMETRE.getConverterTo(MILE).convert(Measure.valueOf(i, MILLIMETRE).doubleValue(MILLIMETRE));
	}
	
	// Meters to Centimeters
	public double mtocm(double i) {
		return METRE.getConverterTo(CENTIMETRE).convert(Measure.valueOf(i, METRE).doubleValue(METRE));
	}
	
	// Centimeters to Meters
	public double cmtom(double i) {
		return CENTIMETRE.getConverterTo(METRE).convert(Measure.valueOf(i, CENTIMETRE).doubleValue(CENTIMETRE));

	}
	
	// Meters to Feet
	public double mtoft(double i) {
		return METRE.getConverterTo(FOOT).convert(Measure.valueOf(i, METRE).doubleValue(METRE));

	}
	
	// Feet to Meters
	public double fttom(double i) {
		return FOOT.getConverterTo(METRE).convert(Measure.valueOf(i, FOOT).doubleValue(FOOT));
	}
	
	// Meters to Inches
	public double mtoin(double i) {
		return METRE.getConverterTo(INCH).convert(Measure.valueOf(i, METRE).doubleValue(METRE));
	}
	
	// Inches to Meters
	public double intom(double i) {
		return INCH.getConverterTo(METRE).convert(Measure.valueOf(i, INCH).doubleValue(INCH));
	}

	// Meters to Miles
	public double mtomi(double i) {
		return METRE.getConverterTo(MILE).convert(Measure.valueOf(i, METRE).doubleValue(METRE));
	}
	
	// Miles to Meters
	public double mitom(double i) {
		return MILE.getConverterTo(METRE).convert(Measure.valueOf(i, MILE).doubleValue(MILE));
	}
	
	// Meters to Millimeters
	public double mtomm(double i) {
		return METRE.getConverterTo(MILLIMETRE).convert(Measure.valueOf(i, METRE).doubleValue(METRE));
	}
	
	// Millimeters to meters
	public double mmtom(double i) {
		return MILLIMETRE.getConverterTo(METRE).convert(Measure.valueOf(i, MILLIMETRE).doubleValue(MILLIMETRE));
	}
	
	// Kilometers to Centimeters
	public double kmtocm(double i) {
		return KILOMETRE.getConverterTo(CENTIMETRE).convert(Measure.valueOf(i, KILOMETRE).doubleValue(KILOMETRE));
	}
	
	// Centimeters to kilometers
	public double cmtokm(double i) {
		return CENTIMETRE.getConverterTo(KILOMETRE).convert(Measure.valueOf(i, CENTIMETRE).doubleValue(CENTIMETRE));
	}
	
	// Kilometers to Feet
	public double kmtoft(double i) {
		return KILOMETRE.getConverterTo(FOOT).convert(Measure.valueOf(i, KILOMETRE).doubleValue(KILOMETRE));
	}
	
	// Feet to Kilometers
	public double fttokm(double i) {
		return FOOT.getConverterTo(KILOMETRE).convert(Measure.valueOf(i, FOOT).doubleValue(FOOT));
	}
	
	// Kilometers to Inches
	public double kmtoin(double i) {
		return KILOMETRE.getConverterTo(INCH).convert(Measure.valueOf(i, KILOMETRE).doubleValue(KILOMETRE));
	}

	// Inches to Kilometers
	public double intokm(double i) {
		return INCH.getConverterTo(KILOMETRE).convert(Measure.valueOf(i, INCH).doubleValue(INCH));
	}
	
	// Kilometers to Meters
	public double kmtom(double i) {
		return KILOMETRE.getConverterTo(METRE).convert(Measure.valueOf(i, KILOMETRE).doubleValue(KILOMETRE));
	}
	
	// Meters to Kilometers
	public double mtokm(double i) {
		return METRE.getConverterTo(KILOMETRE).convert(Measure.valueOf(i, METRE).doubleValue(METRE));
	}

	// Kilometers to Miles
	public double kmtomi(double i) {
		return KILOMETRE.getConverterTo(MILE).convert(Measure.valueOf(i, KILOMETRE).doubleValue(KILOMETRE));
	}
	
	// Miles to Kilometers
	public double mitokm(double i) {
		return MILE.getConverterTo(KILOMETRE).convert(Measure.valueOf(i, MILE).doubleValue(MILE));
	}
	
	// Kilometers to Millimeters
	public double kmtomm(double i) {
		return KILOMETRE.getConverterTo(MILLIMETRE).convert(Measure.valueOf(i, KILOMETRE).doubleValue(KILOMETRE));
	}
	
	// Millimeters to kilometers
	public double mmtokm(double i) {
		return MILLIMETRE.getConverterTo(KILOMETRE).convert(Measure.valueOf(i, MILLIMETRE).doubleValue(MILLIMETRE));
	}

	// Yards to Centimeters
	public double ydtocm(double i) {
		return YARD.getConverterTo(CENTIMETRE).convert(Measure.valueOf(i, YARD).doubleValue(YARD));
	}
	
	// Centimeters to Yards
	public double cmtoyd(double i) {
		return CENTIMETRE.getConverterTo(YARD).convert(Measure.valueOf(i, CENTIMETRE).doubleValue(CENTIMETRE));
	}
	
	// Yards to Feet
	public double ydtoft(double i) {
		return YARD.getConverterTo(FOOT).convert(Measure.valueOf(i, YARD).doubleValue(YARD));
	}
	
	// Feet to yards
	public double fttoyd(double i) {
		return FOOT.getConverterTo(YARD).convert(Measure.valueOf(i, FOOT).doubleValue(FOOT));
	}
	
	// Yards to Inches
	public double ydtoin(double i) {
		return YARD.getConverterTo(INCH).convert(Measure.valueOf(i, YARD).doubleValue(YARD));
	}
	
	// Inches to Yards
	public double intoyd(double i) {
		return INCH.getConverterTo(YARD).convert(Measure.valueOf(i, INCH).doubleValue(INCH));
	}
	
	// Yards to Kilometers
	public double ydtokm(double i) {
		return YARD.getConverterTo(KILOMETRE).convert(Measure.valueOf(i, YARD).doubleValue(YARD));
	}
	
	// Kilometers to Yards
	public double kmtoyd(double i) {
		return KILOMETRE.getConverterTo(YARD).convert(Measure.valueOf(i, KILOMETRE).doubleValue(KILOMETRE));
	}
	
	// Yards to Meters
	public double ydtom(double i) {
		return YARD.getConverterTo(METRE).convert(Measure.valueOf(i, YARD).doubleValue(YARD));
	}
	
	// Meters to Yards
	public double mtoyd(double i) {
		return METRE.getConverterTo(YARD).convert(Measure.valueOf(i, METRE).doubleValue(METRE));
	}

	// Yards to Miles
	public double ydtomi(double i) {
		return YARD.getConverterTo(MILE).convert(Measure.valueOf(i, YARD).doubleValue(YARD));
	}
	
	// Miles to Yards
	public double mitoyd(double i) {
		return MILE.getConverterTo(YARD).convert(Measure.valueOf(i, MILE).doubleValue(MILE));
	}

	// Yards to Millimeters
	public double ydtomm(double i) {
		return YARD.getConverterTo(MILLIMETRE).convert(Measure.valueOf(i, YARD).doubleValue(YARD));
	}
	
	// Millimeters to yards
	public double mmtoyd(double i) {
		return MILLIMETRE.getConverterTo(YARD).convert(Measure.valueOf(i, MILLIMETRE).doubleValue(MILLIMETRE));
	}
	
	/*
	 * RADIOACTIVITY
	 */
	
	// rad to rem
	public double radtorem(double i) {
		return RAD.getConverterTo(REM).convert(Measure.valueOf(i, RAD).doubleValue(RAD));
	}
	
	// rad to Curie
	public double radtoci(double i) {
		return RAD.getConverterTo(CURIE).convert(Measure.valueOf(i, RAD).doubleValue(RAD));
	}
	
	// rad to Rutherford
	public double radtord(double i) {
		return RAD.getConverterTo(RUTHERFORD).convert(Measure.valueOf(i, RAD).doubleValue(RAD));
	}
	
	// rem to rad
	public double remtorad(double i) {
		return REM.getConverterTo(RAD).convert(Measure.valueOf(i, REM).doubleValue(REM));
	}
	
	// rem to Curie
	public double remtoci(double i) {
		return REM.getConverterTo(CURIE).convert(Measure.valueOf(i, REM).doubleValue(REM));
	}
	
	// rem to Rutherford
	public double remtord(double i) {
		return REM.getConverterTo(RUTHERFORD).convert(Measure.valueOf(i, REM).doubleValue(REM));
	}
	
	// Curie to Rutherford
	public double citord(double i) {
		return CURIE.getConverterTo(RUTHERFORD).convert(Measure.valueOf(i, CURIE).doubleValue(CURIE));
	}
	
	// Curie to rad
	public double gitorad(double i) {
		return CURIE.getConverterTo(RAD).convert(Measure.valueOf(i, CURIE).doubleValue(CURIE));
	}
	
	// Curie to rem
	public double citorem(double i) {
		return CURIE.getConverterTo(REM).convert(Measure.valueOf(i, CURIE).doubleValue(CURIE));
	}
	
	// Rutherford to Curie
	public double rdtoci(double i) {
		return RUTHERFORD.getConverterTo(CURIE).convert(Measure.valueOf(i, RUTHERFORD).doubleValue(RUTHERFORD));
	}
	
	// Rutherford to rad
	public double rdtorad(double i) {
		return RUTHERFORD.getConverterTo(RAD).convert(Measure.valueOf(i, RUTHERFORD).doubleValue(RUTHERFORD));
	}
	
	// Rutherford to rem
	public double rdtorem(double i) {
		return RUTHERFORD.getConverterTo(REM).convert(Measure.valueOf(i, RUTHERFORD).doubleValue(RUTHERFORD));
	}
	
	/*
	 * TIME
	 */
	
	//Milliseconds to Seconds
	public double mstos(double i) {
		return MILLI(SECOND).getConverterTo(SECOND).convert(Measure.valueOf(i, MILLI(SECOND)).doubleValue(MILLI(SECOND)));
	}
	
	//Milliseconds to Minutes
	public double mstom(double i) {
		return MILLI(SECOND).getConverterTo(MINUTE).convert(Measure.valueOf(i, MILLI(SECOND)).doubleValue(MILLI(SECOND)));
	}
	
	//Milliseconds to Hours
	public double mstoh(double i) {
		return MILLI(SECOND).getConverterTo(HOUR).convert(Measure.valueOf(i, MILLI(SECOND)).doubleValue(MILLI(SECOND)));
	}
	
	//Seconds to hours
	public double stoh(double i) {
		return SECOND.getConverterTo(HOUR).convert(Measure.valueOf(i, SECOND).doubleValue(SECOND));
	}
	
	//Seconds to minutes
	public double stomin(double i) {
		return SECOND.getConverterTo(MINUTE).convert(Measure.valueOf(i, SECOND).doubleValue(SECOND));
	}
	
	//Seconds to milliseconds
	public double stoms(double i) {
		return SECOND.getConverterTo(MILLI(SECOND)).convert(Measure.valueOf(i, SECOND).doubleValue(SECOND));
	}
	
	//Minutes to Milliseconds
	public double mtoms(double i) {
		return MINUTE.getConverterTo(MILLI(SECOND)).convert(Measure.valueOf(i, MINUTE).doubleValue(MINUTE));
	}
	
	//Minutes to seconds
	public double mintos(double i) {
		return MINUTE.getConverterTo(SECOND).convert(Measure.valueOf(i, MINUTE).doubleValue(MINUTE));
	}
		
	//Minutes to hours
	public double mintoh(double i) {
		return MINUTE.getConverterTo(HOUR).convert(Measure.valueOf(i, MINUTE).doubleValue(MINUTE));
	}
	
	//Hours to milliseconds
	public double htoms(double i) {
		return HOUR.getConverterTo(MILLI(SECOND)).convert(Measure.valueOf(i, HOUR).doubleValue(HOUR));
	}
	
	//Hours to seconds
	public double htos(double i) {
		return HOUR.getConverterTo(SECOND).convert(Measure.valueOf(i, HOUR).doubleValue(HOUR));
	}
	
	//Hours to minutes
	public double htomin(double i) {
		return HOUR.getConverterTo(MINUTE).convert(Measure.valueOf(i, HOUR).doubleValue(HOUR));
	}
	
	/*
	 * DATA
	 */
	
	//Bits to Bytes
	public double btoB(double i) {
		return BIT.getConverterTo(BYTE).convert(Measure.valueOf(i, BIT).doubleValue(BIT));
	}
	
	//Bits to Kilobytes
	public double btok(double i) {
		return BIT.getConverterTo(KILOBYTE).convert(Measure.valueOf(i, BIT).doubleValue(BIT));
	}
	
	//Bits to Megabytes
	public double btoM(double i) {
		return BIT.getConverterTo(MEGABYTE).convert(Measure.valueOf(i, BIT).doubleValue(BIT));
	}
	
	//Bits to Gigabytes
	public double btoG(double i) {
		return BIT.getConverterTo(GIGABYTE).convert(Measure.valueOf(i, BIT).doubleValue(BIT));
	}
	
	//Bits to Terabytes
	public double btoT(double i) {
		return BIT.getConverterTo(TERABYTE).convert(Measure.valueOf(i, BIT).doubleValue(BIT));
	}
	
	//Bits to Petabytes
	public double btoP(double i) {
		return BIT.getConverterTo(PETABYTE).convert(Measure.valueOf(i, BIT).doubleValue(BIT));
	}
	
	//Bits to Exabytes
	public double btoE(double i) {
		return BIT.getConverterTo(EXABYTE).convert(Measure.valueOf(i, BIT).doubleValue(BIT));
	}
	
	//Bits to Zettabytes
	public double btoZ(double i) {
		return BIT.getConverterTo(ZETTABYTE).convert(Measure.valueOf(i, BIT).doubleValue(BIT));
	}
	
	//Bits to Yottabytes
	public double btoY(double i) {
		return BIT.getConverterTo(YOTTABYTE).convert(Measure.valueOf(i, BIT).doubleValue(BIT));
	}
	
	//Bytes to Bits
	public double Btob(double i) {
		return BYTE.getConverterTo(BIT).convert(Measure.valueOf(i, BYTE).doubleValue(BYTE));
	}
	
	//Bytes to Kilobytes
	public double Btok(double i) {
		return BYTE.getConverterTo(KILOBYTE).convert(Measure.valueOf(i, BYTE).doubleValue(BYTE));
	}
	
	//Bytes to Megabytes
	public double BtoM(double i) {
		return BYTE.getConverterTo(MEGABYTE).convert(Measure.valueOf(i, BYTE).doubleValue(BYTE));
	}
	
	//Bytes to Gigabytes
	public double BtoG(double i) {
		return BYTE.getConverterTo(GIGABYTE).convert(Measure.valueOf(i, BYTE).doubleValue(BYTE));
	}
	
	//Bytes to Terabytes
	public double BtoT(double i) {
		return BYTE.getConverterTo(TERABYTE).convert(Measure.valueOf(i, BYTE).doubleValue(BYTE));
	}
	
	//Bytes to Petabytes
	public double BtoP(double i) {
		return BYTE.getConverterTo(PETABYTE).convert(Measure.valueOf(i, BYTE).doubleValue(BYTE));
	}
	
	//Bytes to Exabytes
	public double BtoE(double i) {
		return BYTE.getConverterTo(EXABYTE).convert(Measure.valueOf(i, BYTE).doubleValue(BYTE));
	}
	
	//Bytes to Zettabytes
	public double BtoZ(double i) {
		return BYTE.getConverterTo(ZETTABYTE).convert(Measure.valueOf(i, BYTE).doubleValue(BYTE));
	}
	
	//Bytes to Yottabytes
	public double BtoY(double i) {
		return BYTE.getConverterTo(YOTTABYTE).convert(Measure.valueOf(i, BYTE).doubleValue(BYTE));
	}
	
	//Kilobytes to Bytes
	public double ktoB(double i) {
		return KILOBYTE.getConverterTo(BYTE).convert(Measure.valueOf(i, KILOBYTE).doubleValue(KILOBYTE));
	}
	
	//Kilobytes to Bits
	public double ktob(double i) {
		return KILOBYTE.getConverterTo(BIT).convert(Measure.valueOf(i, KILOBYTE).doubleValue(KILOBYTE));
	}
	
	//Kilobytes to Megabytes
	public double ktoM(double i) {
		return KILOBYTE.getConverterTo(MEGABYTE).convert(Measure.valueOf(i, KILOBYTE).doubleValue(KILOBYTE));
	}
	
	//Kilobytes to Gigabytes
	public double ktoG(double i) {
		return KILOBYTE.getConverterTo(GIGABYTE).convert(Measure.valueOf(i, KILOBYTE).doubleValue(KILOBYTE));
	}
	
	//Kilobytes to Terabytes
	public double ktoT(double i) {
		return KILOBYTE.getConverterTo(TERABYTE).convert(Measure.valueOf(i, KILOBYTE).doubleValue(KILOBYTE));
	}
	
	//Kilobytes to Petabytes
	public double ktoP(double i) {
		return KILOBYTE.getConverterTo(PETABYTE).convert(Measure.valueOf(i, KILOBYTE).doubleValue(KILOBYTE));
	}
	
	//Kilobytes to Exabytes
	public double ktoE(double i) {
		return KILOBYTE.getConverterTo(EXABYTE).convert(Measure.valueOf(i, KILOBYTE).doubleValue(KILOBYTE));
	}
	
	//Kilobytes to Zettabytes
	public double ktoZ(double i) {
		return KILOBYTE.getConverterTo(ZETTABYTE).convert(Measure.valueOf(i, KILOBYTE).doubleValue(KILOBYTE));
	}
	
	//Kilobytes to Yottabytes
	public double ktoY(double i) {
		return KILOBYTE.getConverterTo(YOTTABYTE).convert(Measure.valueOf(i, KILOBYTE).doubleValue(KILOBYTE));
	}
	
	//Megabytes to Bytes
	public double MtoB(double i) {
		return MEGABYTE.getConverterTo(BYTE).convert(Measure.valueOf(i, MEGABYTE).doubleValue(MEGABYTE));
	}
	
	//Megabytes to Bits
	public double Mtob(double i) {
		return MEGABYTE.getConverterTo(BIT).convert(Measure.valueOf(i, MEGABYTE).doubleValue(MEGABYTE));
	}
	
	//Megabytes to Kilobytes
	public double Mtok(double i) {
		return MEGABYTE.getConverterTo(KILOBYTE).convert(Measure.valueOf(i, MEGABYTE).doubleValue(MEGABYTE));
	}
	
	//Megabytes to Gigabytes
	public double MtoG(double i) {
		return MEGABYTE.getConverterTo(GIGABYTE).convert(Measure.valueOf(i, MEGABYTE).doubleValue(MEGABYTE));
	}
	
	//Megabytes to Terabytes
	public double MtoT(double i) {
		return MEGABYTE.getConverterTo(TERABYTE).convert(Measure.valueOf(i, MEGABYTE).doubleValue(MEGABYTE));
	}
	
	//Megabytes to Petabytes
	public double MtoP(double i) {
		return MEGABYTE.getConverterTo(PETABYTE).convert(Measure.valueOf(i, MEGABYTE).doubleValue(MEGABYTE));
	}
	
	//Megabytes to Exabytes
	public double MtoE(double i) {
		return MEGABYTE.getConverterTo(EXABYTE).convert(Measure.valueOf(i, MEGABYTE).doubleValue(MEGABYTE));
	}
	
	//Megabytes to Zettabytes
	public double MtoZ(double i) {
		return MEGABYTE.getConverterTo(ZETTABYTE).convert(Measure.valueOf(i, MEGABYTE).doubleValue(MEGABYTE));
	}
	
	//Megabytes to Yottabytes
	public double MtoY(double i) {
		return MEGABYTE.getConverterTo(YOTTABYTE).convert(Measure.valueOf(i, MEGABYTE).doubleValue(MEGABYTE));
	}
	
	//Gigabytes to Bytes
	public double GtoB(double i) {
		return GIGABYTE.getConverterTo(BYTE).convert(Measure.valueOf(i, GIGABYTE).doubleValue(GIGABYTE));
	}
	
	//Gigabytes to Bits
	public double Gtob(double i) {
		return GIGABYTE.getConverterTo(BIT).convert(Measure.valueOf(i, GIGABYTE).doubleValue(GIGABYTE));
	}
	
	//Gigabytes to Kilobytes
	public double Gtok(double i) {
		return GIGABYTE.getConverterTo(KILOBYTE).convert(Measure.valueOf(i, GIGABYTE).doubleValue(GIGABYTE));
	}
	
	//Gigabytes to Megabytes
	public double GtoM(double i) {
		return GIGABYTE.getConverterTo(MEGABYTE).convert(Measure.valueOf(i, GIGABYTE).doubleValue(GIGABYTE));
	}
	
	//Gigabytes to Terabytes
	public double GtoT(double i) {
		return GIGABYTE.getConverterTo(TERABYTE).convert(Measure.valueOf(i, GIGABYTE).doubleValue(GIGABYTE));
	}
	
	//Gigabytes to Petabytes
	public double GtoP(double i) {
		return GIGABYTE.getConverterTo(PETABYTE).convert(Measure.valueOf(i, GIGABYTE).doubleValue(GIGABYTE));
	}
	
	//Gigabytes to Exabytes
	public double GtoE(double i) {
		return GIGABYTE.getConverterTo(EXABYTE).convert(Measure.valueOf(i, GIGABYTE).doubleValue(GIGABYTE));
	}
	
	//Gigabytes to Zettabytes
	public double GtoZ(double i) {
		return GIGABYTE.getConverterTo(ZETTABYTE).convert(Measure.valueOf(i, GIGABYTE).doubleValue(GIGABYTE));
	}
	
	//Gigabytes to Yottabytes
	public double GtoY(double i) {
		return GIGABYTE.getConverterTo(YOTTABYTE).convert(Measure.valueOf(i, GIGABYTE).doubleValue(GIGABYTE));
	}
	
	//Terabytes to Bytes
	public double TtoB(double i) {
		return TERABYTE.getConverterTo(BYTE).convert(Measure.valueOf(i, TERABYTE).doubleValue(TERABYTE));
	}
	
	//Terabytes to Bits
	public double Ttob(double i) {
		return TERABYTE.getConverterTo(BIT).convert(Measure.valueOf(i, TERABYTE).doubleValue(TERABYTE));
	}
	
	//Terabytes to Kilobytes
	public double Ttok(double i) {
		return TERABYTE.getConverterTo(KILOBYTE).convert(Measure.valueOf(i, TERABYTE).doubleValue(TERABYTE));
	}
	
	//Terabytes to Megabytes
	public double TtoM(double i) {
		return TERABYTE.getConverterTo(MEGABYTE).convert(Measure.valueOf(i, TERABYTE).doubleValue(TERABYTE));
	}
	
	//Terabytes to Gigabytes
	public double TtoG(double i) {
		return TERABYTE.getConverterTo(GIGABYTE).convert(Measure.valueOf(i, TERABYTE).doubleValue(TERABYTE));
	}
	
	//Terabytes to Petabytes
	public double TtoP(double i) {
		return TERABYTE.getConverterTo(PETABYTE).convert(Measure.valueOf(i, TERABYTE).doubleValue(TERABYTE));
	}
	
	//Terabytes to Exabytes
	public double TtoE(double i) {
		return TERABYTE.getConverterTo(EXABYTE).convert(Measure.valueOf(i, TERABYTE).doubleValue(TERABYTE));
	}
	
	//Terabytes to Zettabytes
	public double TtoZ(double i) {
		return TERABYTE.getConverterTo(ZETTABYTE).convert(Measure.valueOf(i, TERABYTE).doubleValue(TERABYTE));
	}
	
	//Terabytes to Yottabytes
	public double TtoY(double i) {
		return TERABYTE.getConverterTo(YOTTABYTE).convert(Measure.valueOf(i, TERABYTE).doubleValue(TERABYTE));
	}
	
	//Petabytes to Bytes
	public double PtoB(double i) {
		return PETABYTE.getConverterTo(BYTE).convert(Measure.valueOf(i, PETABYTE).doubleValue(PETABYTE));
	}
	
	//Petabytes to Bits
	public double Ptob(double i) {
		return PETABYTE.getConverterTo(BIT).convert(Measure.valueOf(i, PETABYTE).doubleValue(PETABYTE));
	}
	
	//Petabytes to Kilobytes
	public double Ptok(double i) {
		return PETABYTE.getConverterTo(KILOBYTE).convert(Measure.valueOf(i, PETABYTE).doubleValue(PETABYTE));
	}
	
	//Petabytes to Megabytes
	public double PtoM(double i) {
		return PETABYTE.getConverterTo(MEGABYTE).convert(Measure.valueOf(i, PETABYTE).doubleValue(PETABYTE));
	}
	
	//Petabytes to Gigabytes
	public double PtoG(double i) {
		return PETABYTE.getConverterTo(GIGABYTE).convert(Measure.valueOf(i, PETABYTE).doubleValue(PETABYTE));
	}
	
	//Petabytes to Terabytes
	public double PtoT(double i) {
		return PETABYTE.getConverterTo(TERABYTE).convert(Measure.valueOf(i, PETABYTE).doubleValue(PETABYTE));
	}
	
	//Petabytes to Exabytes
	public double PtoE(double i) {
		return PETABYTE.getConverterTo(EXABYTE).convert(Measure.valueOf(i, PETABYTE).doubleValue(PETABYTE));
	}
	
	//Petabytes to Zettabytes
	public double PtoZ(double i) {
		return PETABYTE.getConverterTo(ZETTABYTE).convert(Measure.valueOf(i, PETABYTE).doubleValue(PETABYTE));
	}
	
	//Petabytes to Yottabytes
	public double PtoY(double i) {
		return PETABYTE.getConverterTo(YOTTABYTE).convert(Measure.valueOf(i, PETABYTE).doubleValue(PETABYTE));
	}
	
	//Exabytes to Bytes
	public double EtoB(double i) {
		return EXABYTE.getConverterTo(BYTE).convert(Measure.valueOf(i, EXABYTE).doubleValue(EXABYTE));
	}
	
	//Exabytes to Bits
	public double Etob(double i) {
		return EXABYTE.getConverterTo(BIT).convert(Measure.valueOf(i, EXABYTE).doubleValue(EXABYTE));
	}
	
	//Exabytes to Kilobytes
	public double Etok(double i) {
		return EXABYTE.getConverterTo(KILOBYTE).convert(Measure.valueOf(i, EXABYTE).doubleValue(EXABYTE));
	}
	
	//Exabytes to Megabytes
	public double EtoM(double i) {
		return EXABYTE.getConverterTo(MEGABYTE).convert(Measure.valueOf(i, EXABYTE).doubleValue(EXABYTE));
	}
	
	//Exabytes to Gigabytes
	public double EtoG(double i) {
		return EXABYTE.getConverterTo(GIGABYTE).convert(Measure.valueOf(i, EXABYTE).doubleValue(EXABYTE));
	}
	
	//Exabytes to Terabytes
	public double EtoT(double i) {
		return EXABYTE.getConverterTo(TERABYTE).convert(Measure.valueOf(i, EXABYTE).doubleValue(EXABYTE));
	}
	
	//Exabytes to Petabytes
	public double EtoP(double i) {
		return EXABYTE.getConverterTo(PETABYTE).convert(Measure.valueOf(i, EXABYTE).doubleValue(EXABYTE));
	}
	
	//Exabytes to Zettabytes
	public double EtoZ(double i) {
		return EXABYTE.getConverterTo(ZETTABYTE).convert(Measure.valueOf(i, EXABYTE).doubleValue(EXABYTE));
	}
	
	//Exabytes to Yottabytes
	public double EtoY(double i) {
		return EXABYTE.getConverterTo(YOTTABYTE).convert(Measure.valueOf(i, EXABYTE).doubleValue(EXABYTE));
	}
	
	//Zettabytes to Bytes
	public double ZtoB(double i) {
		return ZETTABYTE.getConverterTo(BYTE).convert(Measure.valueOf(i, ZETTABYTE).doubleValue(ZETTABYTE));
	}
	
	//Zettabytes to Bits
	public double Ztob(double i) {
		return ZETTABYTE.getConverterTo(BIT).convert(Measure.valueOf(i, ZETTABYTE).doubleValue(ZETTABYTE));
	}
	
	//Zettabytes to Kilobytes
	public double Ztok(double i) {
		return ZETTABYTE.getConverterTo(KILOBYTE).convert(Measure.valueOf(i, ZETTABYTE).doubleValue(ZETTABYTE));
	}
	
	//Zettabytes to Megabytes
	public double ZtoM(double i) {
		return ZETTABYTE.getConverterTo(MEGABYTE).convert(Measure.valueOf(i, ZETTABYTE).doubleValue(ZETTABYTE));
	}
	
	//Zettabytes to Gigabytes
	public double ZtoG(double i) {
		return ZETTABYTE.getConverterTo(GIGABYTE).convert(Measure.valueOf(i, ZETTABYTE).doubleValue(ZETTABYTE));
	}
	
	//Zettabytes to Terabytes
	public double ZtoT(double i) {
		return ZETTABYTE.getConverterTo(TERABYTE).convert(Measure.valueOf(i, ZETTABYTE).doubleValue(ZETTABYTE));
	}
	
	//Zettabytes to Petabytes
	public double ZtoP(double i) {
		return ZETTABYTE.getConverterTo(PETABYTE).convert(Measure.valueOf(i, ZETTABYTE).doubleValue(ZETTABYTE));
	}
	
	//Zettabytes to Exobytes
	public double ZtoE(double i) {
		return ZETTABYTE.getConverterTo(EXABYTE).convert(Measure.valueOf(i, ZETTABYTE).doubleValue(ZETTABYTE));
	}
	
	//Zettabytes to Yottabytes
	public double ZtoY(double i) {
		return ZETTABYTE.getConverterTo(YOTTABYTE).convert(Measure.valueOf(i, ZETTABYTE).doubleValue(ZETTABYTE));
	}
	
	//Yottabytes to Bytes
	public double YtoB(double i) {
		return YOTTABYTE.getConverterTo(BYTE).convert(Measure.valueOf(i, YOTTABYTE).doubleValue(YOTTABYTE));
	}
	
	//Yottabytes to Bits
	public double Ytob(double i) {
		return YOTTABYTE.getConverterTo(BIT).convert(Measure.valueOf(i, YOTTABYTE).doubleValue(YOTTABYTE));
	}
	
	//Yottabytes to Kilobytes
	public double Ytok(double i) {
		return YOTTABYTE.getConverterTo(KILOBYTE).convert(Measure.valueOf(i, YOTTABYTE).doubleValue(YOTTABYTE));
	}
	
	//Yottabytes to Megabytes
	public double YtoM(double i) {
		return YOTTABYTE.getConverterTo(MEGABYTE).convert(Measure.valueOf(i, YOTTABYTE).doubleValue(YOTTABYTE));
	}
	
	//Yottabytes to Gigabytes
	public double YtoG(double i) {
		return YOTTABYTE.getConverterTo(GIGABYTE).convert(Measure.valueOf(i, YOTTABYTE).doubleValue(YOTTABYTE));
	}
	
	//Yottabytes to Terabytes
	public double YtoT(double i) {
		return YOTTABYTE.getConverterTo(TERABYTE).convert(Measure.valueOf(i, YOTTABYTE).doubleValue(YOTTABYTE));
	}
	
	//Yottabytes to Petabytes
	public double YtoP(double i) {
		return YOTTABYTE.getConverterTo(PETABYTE).convert(Measure.valueOf(i, YOTTABYTE).doubleValue(YOTTABYTE));
	}
	
	//Yottabytes to Exobytes
	public double YtoE(double i) {
		return YOTTABYTE.getConverterTo(EXABYTE).convert(Measure.valueOf(i, YOTTABYTE).doubleValue(YOTTABYTE));
	}
	
	//Yottabytes to Zettabytes
	public double YtoZ(double i) {
		return YOTTABYTE.getConverterTo(ZETTABYTE).convert(Measure.valueOf(i, YOTTABYTE).doubleValue(YOTTABYTE));
	}
	
}