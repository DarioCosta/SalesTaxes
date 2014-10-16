package org.dario.salestaxes.policies.strategies;

public class RoundToHigher5PolicyStrategy implements RoundPolicyStrategy {

	@Override
	public double round(double in) {
		int cents=(int)(in*100);
		int lastDigit=cents%10;
		boolean mustAddUnit=false;
		if(lastDigit>5){
			mustAddUnit=true;
			lastDigit=0;
		}else if(lastDigit>0){
			lastDigit=5;
		}else{
			lastDigit=0;
		}
		cents=cents/10;
		if(mustAddUnit){
			cents+=1;
		}
		cents=cents*10;
		cents=cents+lastDigit;
		return cents/100F;
	}

}
