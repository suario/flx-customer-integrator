/* (C) Jorge Suarez 2024 */
package com.suario.flx_crm_service.jpa.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

public class DbNames {

	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	public static class CustomerCrmConst {
		public static final String TABLE_NAME = "customer_crm";
		public static final String ID = "id";
		public static final String FULL_NAME = "full_name";
		public static final String CONTACT_EMAIL = "contact_email";
		public static final String PRIMARY_PHONE = "primary_phone";
		public static final String LOCATION = "location";
	}
}
