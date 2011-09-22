package com.tectria.converterous;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
public class ConvertTool {
	private String toUnit;
	private String fromUnit;
	private Float toNum;
	private Float fromNum;
	public ConvertTool(String toUnit, String fromUnit, Float fromNum) {
		this.toUnit = toUnit;
		this.fromUnit = fromUnit;
		this.toNum = null;
		this.fromNum = fromNum;
	}
	
	public ConvertTool() {
		this.toUnit = null;
		this.fromUnit = null;
		this.toNum = null;
		this.fromNum = null;
	}
	
	public Float convert() {
		String name = this.fromUnit + "to" + this.toUnit;
		try {
			Method method = this.getClass().getMethod(name, Float.class);
			this.toNum = (Float)method.invoke(fromNum);
			return this.toNum;
		} catch (NoSuchMethodException e) {
			return new Float(0.0);
		} catch (SecurityException e) {
			return new Float(0.0);
		} catch (IllegalAccessException e) {
			return new Float(0.0);
		} catch (IllegalArgumentException e) {
			return new Float(0.0);
		} catch (InvocationTargetException e) {
			return new Float(0.0);
		}
	}
	
	public void setFromNum(Float n) {
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

	// Inches to Feet
	public Float intoft(Float i) {
		return new Float(i/12);
	}
	
	// Feet to Inches
	public Float fttoin(Float i) {
		return new Float(i*12);
	}
	
	// Centimeters to Inches
	public Float cmtoin(Float i) {
		return new Float(i*0.3937008);
	}
	
	// Inches to Centimeters
	public Float intocm(Float i) {
		return new Float(i/0.3937008);
	}
	
	// Centimeters to Feet
	public Float cmtoft(Float i) {
		return new Float(i/30.48);
	}
	
	// Feet to Centimeters
	public Float fttocm(Float i) {
		return new Float(i*30.48);
	}
	
	// Inches to Millimeters
	public Float intomm(Float i) {
		return new Float(i*25.4);
	}
	
	// Millimeters to Inches
	public Float mmtoin(Float i) {
		return new Float(i/25.4);
	}
	
	// Millimeters to Centimeters
	public Float mmtocm(Float i) {
		return new Float(i/10);
	}
	
	// Centimeters to Millimeters
	public Float cmtomm(Float i) {
		return new Float(i*10);
	}
	
	// Millimeters to Feet
	public Float mmtoft(Float i) {
		return new Float(i/304.8);
	}
	
	// Feet to Millimeters
	public Float fttommm(Float i) {
		return new Float(i*304.8);
	}
	
	// Mile to Feet
	public Float mitoft(Float i) {
		return new Float(i*5280);
	}
	
	// Feet to Mile
	public Float fttomi(Float i) {
		return new Float(i/5280);
	}
	
	// Miles to Inches
	public Float mitoin(Float i) {
		return new Float(i*63360);
	}
	
	// Inches to Miles
	public Float intomi(Float i) {
		return new Float(i/63360);
	}
	
	// Miles to Centimeters
	public Float mitocm(Float i) {
		return new Float(i*160934.4);
	}
	
	// Miles to Millimeters
	public Float mitomm(Float i) {
		return new Float(i*1609344);
	}
	
	// Millimeters to Miles
	public Float mmtomi(Float i) {
		return new Float(i/1609344);
	}
	
	// Meters to Centimeters
	public Float mtocm(Float i) {
		return new Float(i*100);
	}
	
	// Centimeters to Meters
	public Float cmtom(Float i) {
		return new Float(i/100);
	}
	
	// Meters to Feet
	public Float mtoft(Float i) {
		return new Float(i*3.280839895);
	}
	
	// Feet to Meters
	public Float fttom(Float i) {
		return new Float(i/3.280839895);
	}
	
	// Meters to Inches
	public Float mtoin(Float i) {
		return new Float(i*39.37007874);
	}
	
	// Inches to Meters
	public Float intom(Float i) {
		return new Float(i/39.37007874);
	}

	// Meters to Miles
	public Float mtomi(Float i) {
		return new Float(i/1609.344);
	}
	
	// Miles to Meters
	public Float mitom(Float i) {
		return new Float(i*1609.344);
	}
	
	// Meters to Millimeters
	public Float mtomm(Float i) {
		return new Float(i*1000);
	}
	
	// Millimeters to meters
	public Float mmtom(Float i) {
		return new Float(i/1000);
	}
	
	// Kilometers to Centimeters
	public Float kmtocm(Float i) {
		return new Float(i*100000);
	}
	
	// Centimeters to kilometers
	public Float cmtokm(Float i) {
		return new Float(i/100000);
	}
	
	// Kilometers to Feet
	public Float kmtoft(Float i) {
		return new Float(i*3280.839895);
	}
	
	// Feet to Kilometers
	public Float fttokm(Float i) {
		return new Float(i/3280.839895);
	}
	
	// Kilometers to Inches
	public Float kmtoin(Float i) {
		return new Float(i*39370.07874);
	}

	// Inches to Kilometers
	public Float intokm(Float i) {
		return new Float(i/39370.07874);
	}
	
	// Kilometers to Meters
	public Float kmtom(Float i) {
		return new Float(i*1000);
	}
	
	// Meters to Kilometers
	public Float mtokm(Float i) {
		return new Float(i/1000);
	}

	// Kilometers to Miles
	public Float kmtomi(Float i) {
		return new Float(i/1.609344);
	}
	
	// Miles to Kilometers
	public Float mitokm(Float i) {
		return new Float(i*1.609344);
	}
	
	// Kilometers to Millimeters
	public Float kmtomm(Float i) {
		return new Float(i*1000000);
	}
	
	// Millimeters to kilometers
	public Float mmtokm(Float i) {
		return new Float(i/1000000);
	}

	// Yards to Centimeters
	public Float ydtocm(Float i) {
		return new Float(i*91.44);
	}
	
	// Centimeters to Yards
	public Float cmtoyd(Float i) {
		return new Float(i/91.44);
	}
	
	// Yards to Feet
	public Float ydtoft(Float i) {
		return new Float(i*3);
	}
	
	// Feet to yards
	public Float fttoyd(Float i) {
		return new Float(i/3);
	}
	
	// Yards to Inches
	public Float ydtoin(Float i) {
		return new Float(i*36);
	}
	
	// Inches to Yards
	public Float intoyd(Float i) {
		return new Float(i/36);
	}
	
	// Yards to Kilometers
	public Float ydtokm(Float i) {
		return new Float(i/1093.6132983);
	}
	
	// Kilometers to Yards
	public Float kmtoyd(Float i) {
		return new Float(i*1093.6132983);
	}
	
	// Yards to Meters
	public Float ydtom(Float i) {
		return new Float(i/1.0936132983);
	}
	
	// Meters to Yards
	public Float mtoyd(Float i) {
		return new Float(i*1.0936132983);
	}

	// Yards to Miles
	public Float ydtomi(Float i) {
		return new Float(i/1760);
	}
	
	// Miles to Yards
	public Float mitoyd(Float i) {
		return new Float(i*1760);
	}

	// Yards to Millimeters
	public Float ydtomm(Float i) {
		return new Float(i*914.4);
	}
	
	// Millimeters to yards
	public Float mmtoyd(Float i) {
		return new Float(i/914.4);
	}
	
	
	
	
}
