
//driving the IpsonCV board 
//Dakis Trentos & Dimitris Asvestas aka ReturnofaRat http://returnofarat.com/
//Tue, 13 Mar 2012, 12:13



//this class doing the bitwise operations and send osc messages to ipsonCV address
IpsonCV {
	classvar <>aAB, <>aCD, <>aEF, <>aGH, <>bAB, <>bCD, <>bEF, <>bGH;
	*sendAll {|aA = 0, aB = 0, aC = 0, aD = 0, aE = 0, aF = 0, aG = 0, aH = 0,
		bA = 0, bB = 0, bC = 0, bD = 0, bE = 0, bF = 0, bG = 0, bH = 0| 
		var aAB, aCD, aEF, aGH, bAB, bCD, bEF, bGH;
		//bitwise operations
		aAB = ((aA & 4095) << 16) + (aB & 4095);
		aCD = ((aC & 4095) << 16) + (aD & 4095);
		aEF = ((aE & 4095) << 16) + (aF & 4095);
		aGH = ((aG & 4095) << 16) + (aH & 4095);
		bAB = ((bA & 4095) << 16) + (bB & 4095);
		bCD = ((bC & 4095) << 16) + (bD & 4095);
		bEF = ((bE & 4095) << 16) + (bF & 4095);
		bGH = ((bG & 4095) << 16) + (bH & 4095);
		//send osc to ipson address
		NetAddr("10.1.60.91", 10001).sendMsg('/v1', aAB, aCD, aEF, aGH, bAB, bCD, bEF, bGH);
		//send values to IpsonDict
		IpsonDict.ipsonValues[\aA] = aA;
		IpsonDict.ipsonValues[\aB] = aB;
		IpsonDict.ipsonValues[\aC] = aC;
		IpsonDict.ipsonValues[\aD] = aD;
		IpsonDict.ipsonValues[\aE] = aE;
		IpsonDict.ipsonValues[\aF] = aF;
		IpsonDict.ipsonValues[\aG] = aG;
		IpsonDict.ipsonValues[\aH] = aH;
		IpsonDict.ipsonValues[\bA] = bA;
		IpsonDict.ipsonValues[\bB] = bB;
		IpsonDict.ipsonValues[\bC] = bC;
		IpsonDict.ipsonValues[\bD] = bD;
		IpsonDict.ipsonValues[\bE] = bE;
		IpsonDict.ipsonValues[\bF] = bF;
		IpsonDict.ipsonValues[\bG] = bG;
		IpsonDict.ipsonValues[\bH] = bH;
	}
	
}

//this class is for collecting the incoming values of the CV class, put them to an
//IdentityDictionary and send them to IpsonCV class. Also creates the 16 CV objects to adjust the 16 ipsonCV outputs 
IpsonDict {
	classvar <>ipsonValues;
	*initClass {
		StartUp.add {
			//an IndentityDictionary for keeping the incoming values of CV class
			ipsonValues = IdentityDictionary.new;
			ipsonValues[\aA] = 0;
			ipsonValues[\aB] = 0;
			ipsonValues[\aC] = 0;
			ipsonValues[\aD] = 0;
			ipsonValues[\aE] = 0;
			ipsonValues[\aF] = 0;
			ipsonValues[\aG] = 0;
			ipsonValues[\aH] = 0;
			ipsonValues[\bA] = 0;
			ipsonValues[\bB] = 0;
			ipsonValues[\bC] = 0;
			ipsonValues[\bD] = 0;
			ipsonValues[\bE] = 0;
			ipsonValues[\bF] = 0;
			ipsonValues[\bG] = 0;
			ipsonValues[\bH] = 0;
			//creation of CV objects for the 16 ipsonCV outputs 
			~aA = CV.new("aA");
			~aB = CV.new("aB");
			~aC = CV.new("aC");
			~aD = CV.new("aD");
			~aE = CV.new("aE");
			~aF = CV.new("aF");
			~aG = CV.new("aG");
			~aH = CV.new("aH");
			~bA = CV.new("bA");
			~bB = CV.new("bB");
			~bC = CV.new("bC");
			~bD = CV.new("bD");
			~bE = CV.new("bE");
			~bF = CV.new("bF");
			~bG = CV.new("bG");
			~bH = CV.new("bH");
		}
	} 
	*sendAll { 
		IpsonCV.sendAll(aA: ipsonValues[\aA], aB: ipsonValues[\aB], aC: ipsonValues[\aC], aD: ipsonValues[\aD], 
			aE: ipsonValues[\aE], aF: ipsonValues[\aF], aG: ipsonValues[\aG], aH: ipsonValues[\aH],
			bA: ipsonValues[\bA], bB: ipsonValues[\bB], bC: ipsonValues[\bC], bD: ipsonValues[\bD], 
			bE: ipsonValues[\bE], bF: ipsonValues[\bF], bG: ipsonValues[\bG], bH: ipsonValues[\bH]);
	}	
	
}


//this class creates instances for the 16 outputs and send the value of each one to IsponDict class
CV {
	var <out;
	 *new { |out|
		^super.newCopyArgs(out);
	}
	
	sendVal{ |value|
		IpsonDict.ipsonValues[this.out.asSymbol] = value;
		IpsonDict.sendAll;
	}
	printOn { arg stream;
		stream << this.class.name << "(" <<*
			[out]  <<")"
	}		
}