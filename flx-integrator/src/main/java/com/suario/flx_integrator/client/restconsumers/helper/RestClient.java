/* (C) Jorge Suarez 2024 */
package com.suario.flx_integrator.client.restconsumers.helper;

public interface RestClient<R, D, I> {

	D create(R request);

	D update(I id, R request);

	void delete(I id);
}
