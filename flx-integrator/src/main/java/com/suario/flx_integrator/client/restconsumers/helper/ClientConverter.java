/* (C) Jorge Suarez 2024 */
package com.suario.flx_integrator.client.restconsumers.helper;

public interface ClientConverter<E, R, D> {
	D toResponse(E entity);

	E toModel(D data);

	R toRequest(E data);
}
