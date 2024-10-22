/* (C) Jorge Suarez 2024 */
package com.suario.flx_crm_service.jpa.helper;

public interface DataConverter<E, D> {
	D toData(E entity);

	E toEntity(D data);
}
