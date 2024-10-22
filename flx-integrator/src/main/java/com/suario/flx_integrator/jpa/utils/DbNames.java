/* (C) Jorge Suarez 2024 */
package com.suario.flx_integrator.jpa.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

public class DbNames {

	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	public static class AddressConst {
		public static final String TABLE_NAME = "address";
		public static final String ID = "id";
		public static final String STREET = "street";
		public static final String CITY = "city";
		public static final String STATE = "state";
		public static final String ZIP_CODE = "zip_code";
	}

	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	public static class CustomerConst {
		public static final String TABLE_NAME = "customer";
		public static final String CUSTOMER_ID = "customer_id";
		public static final String FIRST_NAME = "first_name";
		public static final String LAST_NAME = "last_name";
		public static final String EMAIL = "email";
		public static final String PHONE_NUMBER = "phone_number";
		public static final String ADDRESS_ID = "address_id";
	}
}
