package org.dario.salestaxes;

/**
 * Startegy for Round Policy. We kke it separated so we can easily change it in
 * case we decide to use a different policy.
 * 
 * @author CostaDS
 *
 */
public interface RoundPolicyStrategy {
	public double round(double in);
}
