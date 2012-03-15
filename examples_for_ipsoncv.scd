
//examples...

//the IpSon OSC to ControlVoltage card provides 16 channels of control voltage output. 
//in total 16 outputs divided in two sections: 1A - 1H and 2A - 2H. In SuperCollider these names
//are a1 - aH and bA - bH respectively. 
//for information about how to connect the board go to: http://www.ipson.nl/



////////using the method sendAll///////

//send values to ipson once
IpsonCV.sendAll(aA: 0, aB: 0, aC: 0, aD: 0, aE: 0, aF: 0, aG: 0, aH: 0, bA: 0, bB: ~ia2, bC: 0, bD: 0, bE: 0, bF: 0, bG: 0, bH: 0);

//send values via routine
fork{
	10.do{
		IpsonCV.sendAll(aA: 0, aB: 0, aC: 0, aD: 0, aE: 0, aF: 0, aG: 0, aH: 0,
			bA: 0, bB: 816, bC: 0, bD: 0, bE: 0, bF: 0, bG: 0, bH: 0);
		0.23.wait;
		IpsonCV.sendAll(aA: 0, aB: 0, aC: 0, aD: 0, aE: 0, aF: 0, aG: 0, aH: 0,
			bA: 0, bB: 1020, bC: 0, bD: 0, bE: 0, bF: 0, bG: 0, bH: 0);
		0.23.wait;
		IpsonCV.sendAll(aA: 0, aB: 0, aC: 0, aD: 0, aE: 0, aF: 0, aG: 0, aH: 0,
			bA: 0, bB: 1360, bC: 0, bD: 0, bE: 0, bF: 0, bG: 0, bH: 0);
		0.23.wait;
		IpsonCV.sendAll(aA: 0, aB: 0, aC: 0, aD: 0, aE: 0, aF: 0, aG: 0, aH: 0,
			bA: 0, bB: 1632, bC: 0, bD: 0, bE: 0, bF: 0, bG: 0, bH: 0);
		0.23.wait;
	}
}
			
	
		
////////using the method sendVal//////////

//send one value to one output of ipson
~aA.sendVal(~ia2);

//send values to different outputs:
fork{
	15.do{
		~aA.sendVal(1);
		~bB.sendVal(1);
		~aF.sendVal(4095);
		~aD.sendVal(4095);
		0.23.wait;
		~aA.sendVal(816);
		~bB.sendVal(476);
		~aD.sendVal(0);
		~aF.sendVal(0);
		0.23.wait;
		~aA.sendVal(1632);
		~bB.sendVal(1632);
		~aD.sendVal(4095);
		0.23.wait;
		~aA.sendVal(2448);
		~bB.sendVal(1360);
		~aD.sendVal(0);
		0.23.wait;
		~aA.sendVal(1360);
		~bB.sendVal(1632);
		0.23.wait;	
	}
	
};



////////using both sendAll and sendVal/////////

(
//one routine only for ~aA output
fork{
	20.do{
		~aA.sendVal(1);
		0.23.wait;
		~aA.sendVal(816);
		0.23.wait;
		~aA.sendVal(1632);
		0.23.wait;
		~aA.sendVal(2448);
		0.23.wait;
		~aA.sendVal(1360);
		0.23.wait;	
	}
	
};

//one fork for the rest values
//IpsonDict.ipsonValues[\aB] takes the value for aA of the IpsonDict class
fork{
	20.do{
IpsonCV.sendAll(aA: IpsonDict.ipsonValues[\aB], aB: 0, aC: 0, aD: 0, aE: 0, aF: 0, aG: 0, aH: 0,
		bA: 0, bB: 816, bC: 0, bD: 0, bE: 0, bF: 0, bG: 0, bH: 0);		0.23.wait;
		IpsonCV.sendAll(aA: IpsonDict.ipsonValues[\aB], aB: 0, aC: 0, aD: 0, aE: 0, aF: 0, aG: 0, aH: 0,
		bA: 0, bB: 1632, bC: 0, bD: 0, bE: 0, bF: 0, bG: 0, bH: 0);
		0.23.wait;
		IpsonCV.sendAll(aA: IpsonDict.ipsonValues[\aB], aB: 0, aC: 0, aD: 0, aE: 0, aF: 0, aG: 0, aH: 0,
		bA: 0, bB: 1632, bC: 0, bD: 0, bE: 0, bF: 0, bG: 0, bH: 0);
		0.23.wait;
		IpsonCV.sendAll(aA: IpsonDict.ipsonValues[\aB], aB: 0, aC: 0, aD: 0, aE: 0, aF: 0, aG: 0, aH: 0,
		bA: 0, bB: 816, bC: 0, bD: 0, bE: 0, bF: 0, bG: 0, bH: 0);
		0.23.wait;
		IpsonCV.sendAll(aA: IpsonDict.ipsonValues[\aB], aB: 0, aC: 0, aD: 0, aE: 0, aF: 0, aG: 0, aH: 0,
		bA: 0, bB: 1360, bC: 0, bD: 0, bE: 0, bF: 0, bG: 0, bH: 0);		0.23.wait;	
	}
	
};

)




