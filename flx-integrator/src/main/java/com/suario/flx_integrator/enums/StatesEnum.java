/* (C) Jorge Suarez 2024 */
package com.suario.flx_integrator.enums;

public enum StatesEnum {
	AL, AK, AS, AZ, AR, CA, CO, CT, DE, DC, FM, FL, GA, GU, HI, ID, IL, IN, IA, KS, KY, LA, ME, MH, MD, MA, MI, MN, MS, MO, MT, NE, NV, NH, NJ, NM, NY, NC, ND, MP, OH, OK, OR, PW, PA, PR, RI, SC, SD, TN, TX, UT, VT, VI, VA, WA, WV, WI, WY;

	StatesEnum() {
	}

	public static boolean isValid(String value) {
		try {
			valueOf(value);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
