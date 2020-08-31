package com.speedtest.svc.starter.tokens.impl;

/**
 * Component that calculates the expiration time for
 * the currently initiated speed test
 * 
 * @author atotev
 *
 */
interface ExpirationTimeCalculation {

	public Long calculate();
}
