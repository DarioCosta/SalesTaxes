package org.dario.salestaxes.policies;

import org.dario.salestaxes.policies.strategies.RoundPolicyStrategy;
import org.dario.salestaxes.policies.strategies.RoundToHigher5PolicyStrategy;

public class RoundPolicy {

	private RoundPolicyStrategy strategy;

	public RoundPolicy() {
		this(null);
	}

	public RoundPolicy(RoundPolicyStrategy strategy) {
		if (strategy == null) {
			strategy = new RoundToHigher5PolicyStrategy();
		}
		this.strategy = strategy;
	}

	public double round(double in) {
		return strategy.round(in);
	}
}
