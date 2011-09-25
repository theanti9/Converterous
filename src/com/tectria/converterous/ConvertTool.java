package com.tectria.converterous;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.measure.Measure;
import static javax.measure.unit.NonSI.*;
import static javax.measure.unit.SI.*;

public class ConvertTool {
	
	private String toUnit;
	private String fromUnit;
	private double toNum;
	private double fromNum;
	
	public ConvertTool(String toUnit, String fromUnit, double fromNum) {
		this.toUnit = toUnit;
		this.fromUnit = fromUnit;
		this.toNum = 0.0;
		this.fromNum = fromNum;
	}
	
	public ConvertTool() {
		this.toUnit = null;
		this.fromUnit = null;
		this.toNum = 0.0;
		this.fromNum = 0.0;
	}
	
	@SuppressWarnings("rawtypes")
	public double convert() {
		String name = (this.fromUnit + "to" + this.toUnit).replaceAll("[^a-zA-Z0-9]", "");
		
		try {
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
			
		} catch (NoSuchMethodException e) {
			return 0.0;
		} catch (SecurityException e) {
			return 0.0;
		} catch (IllegalAccessException e) {
			return 0.0;
		} catch (IllegalArgumentException e) {
			return 0.0;
		} catch (InvocationTargetException e) {
			return 0.0;
		} catch (ClassNotFoundException e) {
			return 0.0;
		}
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
	
	//Fahrenheit to Celsius
	public double FtoC(double i) {
		return FAHRENHEIT.getConverterTo(CELSIUS).convert(Measure.valueOf(i, FAHRENHEIT).doubleValue(FAHRENHEIT));
	}
	
	//Fahrenheit to Kelvin
	public double FtoK(double i) {
		return FAHRENHEIT.getConverterTo(KELVIN).convert(Measure.valueOf(i, FAHRENHEIT).doubleValue(FAHRENHEIT));
	}
	
	//Kelvin to Fahrenheit
	public double KtoF(double i) {
		return KELVIN.getConverterTo(FAHRENHEIT).convert(Measure.valueOf(i, KELVIN).doubleValue(KELVIN));
	}
	
	//Kelvin to Celsius
	public double KtoC(double i) {
		return KELVIN.getConverterTo(CELSIUS).convert(Measure.valueOf(i, KELVIN).doubleValue(KELVIN));
	}
		
	
	/*
	 * MASS
	 */
	
	/*
	 * VOLUME
	 */
	
	//Pints to quarts
	public double pttoqt(double i) {
		return i/2;
	}
	
	//Pints to gallons
	public double pttogal(double i) {
		return i/8;
	}
	
	//Quarts to pints
	public double qttopt(double i) {
		return i*2;
	}
	
	//Quarts to gallons
	public double qttogal(double i) {
		return i/4;
	}

	//Gallons to quarts
	public double galtoqt(double i) {
		return i*4;
	}
	
	//Gallons to pints
	public double galtopt(double i) {
		return i*8;
	}
	
	/*
	 * DISTANCE
	 */
	
	// Inches to Feet
	public double intoft(double i) {
		return i/12;
	}
	
	// Feet to Inches
	public double fttoin(double i) {
		return i*12;
	}
	
	// Centimeters to Inches
	public double cmtoin(double i) {
		return i*0.3937008;
	}
	
	// Inches to Centimeters
	public double intocm(double i) {
		return i/0.3937008;
	}
	
	// Centimeters to Feet
	public double cmtoft(double i) {
		return i/30.48;
	}
	
	// Feet to Centimeters
	public double fttocm(double i) {
		return i*30.48;
	}
	
	// Inches to Millimeters
	public double intomm(double i) {
		return i*25.4;
	}
	
	// Millimeters to Inches
	public double mmtoin(double i) {
		return i/25.4;
	}
	
	// Millimeters to Centimeters
	public double mmtocm(double i) {
		return i/10;
	}
	
	// Centimeters to Millimeters
	public double cmtomm(double i) {
		return i*10;
	}
	
	// Millimeters to Feet
	public double mmtoft(double i) {
		return i/304.8;
	}
	
	// Feet to Millimeters
	public double fttommm(double i) {
		return i*304.8;
	}
	
	// Mile to Feet
	public double mitoft(double i) {
		return i*5280;
	}
	
	// Feet to Mile
	public double fttomi(double i) {
		return i/5280;
	}
	
	// Miles to Inches
	public double mitoin(double i) {
		return i*63360;
	}
	
	// Inches to Miles
	public double intomi(double i) {
		return i/63360;
	}
	
	// Miles to Centimeters
	public double mitocm(double i) {
		return i*160934.4;
	}
	
	// Miles to Millimeters
	public double mitomm(double i) {
		return i*1609344;
	}
	
	// Millimeters to Miles
	public double mmtomi(double i) {
		return i/1609344;
	}
	
	// Meters to Centimeters
	public double mtocm(double i) {
		return i*100;
	}
	
	// Centimeters to Meters
	public double cmtom(double i) {
		return i/100;
	}
	
	// Meters to Feet
	public double mtoft(double i) {
		return i*3.280839895;
	}
	
	// Feet to Meters
	public double fttom(double i) {
		return i/3.280839895;
	}
	
	// Meters to Inches
	public double mtoin(double i) {
		return i*39.37007874;
	}
	
	// Inches to Meters
	public double intom(double i) {
		return i/39.37007874;
	}

	// Meters to Miles
	public double mtomi(double i) {
		return i/1609.344;
	}
	
	// Miles to Meters
	public double mitom(double i) {
		return i*1609.344;
	}
	
	// Meters to Millimeters
	public double mtomm(double i) {
		return i*1000;
	}
	
	// Millimeters to meters
	public double mmtom(double i) {
		return i/1000;
	}
	
	// Kilometers to Centimeters
	public double kmtocm(double i) {
		return i*100000;
	}
	
	// Centimeters to kilometers
	public double cmtokm(double i) {
		return i/100000;
	}
	
	// Kilometers to Feet
	public double kmtoft(double i) {
		return i*3280.839895;
	}
	
	// Feet to Kilometers
	public double fttokm(double i) {
		return i/3280.839895;
	}
	
	// Kilometers to Inches
	public double kmtoin(double i) {
		return i*39370.07874;
	}

	// Inches to Kilometers
	public double intokm(double i) {
		return i/39370.07874;
	}
	
	// Kilometers to Meters
	public double kmtom(double i) {
		return i*1000;
	}
	
	// Meters to Kilometers
	public double mtokm(double i) {
		return i/1000;
	}

	// Kilometers to Miles
	public double kmtomi(double i) {
		return i/1.609344;
	}
	
	// Miles to Kilometers
	public double mitokm(double i) {
		return i*1.609344;
	}
	
	// Kilometers to Millimeters
	public double kmtomm(double i) {
		return i*1000000;
	}
	
	// Millimeters to kilometers
	public double mmtokm(double i) {
		return i/1000000;
	}

	// Yards to Centimeters
	public double ydtocm(double i) {
		return i*91.44;
	}
	
	// Centimeters to Yards
	public double cmtoyd(double i) {
		return i/91.44;
	}
	
	// Yards to Feet
	public double ydtoft(double i) {
		return i*3;
	}
	
	// Feet to yards
	public double fttoyd(double i) {
		return i/3;
	}
	
	// Yards to Inches
	public double ydtoin(double i) {
		return i*36;
	}
	
	// Inches to Yards
	public double intoyd(double i) {
		return i/36;
	}
	
	// Yards to Kilometers
	public double ydtokm(double i) {
		return i/1093.6132983;
	}
	
	// Kilometers to Yards
	public double kmtoyd(double i) {
		return i*1093.6132983;
	}
	
	// Yards to Meters
	public double ydtom(double i) {
		return i/1.0936132983;
	}
	
	// Meters to Yards
	public double mtoyd(double i) {
		return i*1.0936132983;
	}

	// Yards to Miles
	public double ydtomi(double i) {
		return i/1760;
	}
	
	// Miles to Yards
	public double mitoyd(double i) {
		return i*1760;
	}

	// Yards to Millimeters
	public double ydtomm(double i) {
		return i*914.4;
	}
	
	// Millimeters to yards
	public double mmtoyd(double i) {
		return i/914.4;
	}
	
	/*
	 * TIME
	 */
	
	//Seconds to hours
	public double stoh(double i) {
		return i/3600;
	}
	
	//Seconds to minutes
	public double stomin(double i) {
		return i/60;
	}
	
	//Minutes to seconds
	public double mintos(double i) {
		return i*60;
	}
		
	//Minutes to hours
	public double mintoh(double i) {
		return i/60;
	}
	
	//Hours to seconds
	public double htos(double i) {
		return i*3600;
	}
	
	//Hours to minutes
	public double htomin(double i) {
		return i*60;
	}
	
}
