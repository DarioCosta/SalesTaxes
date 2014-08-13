package org.dario.salestaxes;

/**
 * Round up to nearest higher 0.05
 * 
 * @author CostaDS
 *
 */
public class RoundToHigher5Policy implements RoundPolicyStrategy {

	@Override
	public double round(double in) {
		int tmp=(int)(in*100);
		int lastDigit=tmp%10;
		boolean incr=false;
		if(lastDigit>5){
			incr=true;
			lastDigit=0;
		}else if(lastDigit>0){
			lastDigit=5;
		}else{
			lastDigit=0;
		}
		tmp=tmp/10;
		if(incr){
			tmp+=1;
		}
		tmp=tmp*10;
		tmp=tmp+lastDigit;
		return tmp/100F;
	}

}
