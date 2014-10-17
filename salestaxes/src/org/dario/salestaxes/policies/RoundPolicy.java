package org.dario.salestaxes.policies;

import org.dario.salestaxes.policies.strategies.RoundPolicyStrategy;

public class RoundPolicy {

	private RoundPolicyStrategy strategy;

	public RoundPolicy(RoundPolicyStrategy strategy) {
		this.strategy = strategy;
	}

	public double round(double in) {
		return strategy.round(in);
	}
}
