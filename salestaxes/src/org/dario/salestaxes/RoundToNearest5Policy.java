package org.dario.salestaxes;

/**
 * Round up to nearest 0.05
 * 
 * @author CostaDS
 *
 */
public class RoundToNearest5Policy implements RoundPolicyStrategy {

	@Override
	public double round(double in) {
		
		int tmp=(int)(in*100);
		int lastDigit=tmp%10;
		boolean incr=false;
		if(lastDigit<3){
			lastDigit=0;
		}else if(lastDigit<8){
			lastDigit=5;
		}else{
			lastDigit=0;
			incr=true;
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
