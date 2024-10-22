/* (C) Jorge Suarez 2024 */
package com.suario.flx_integrator.client.restconsumers.helper;

import java.util.function.Function;

public abstract class ClientAdapter<M, R, D, I, C extends RestClient<R, D, I>> {

	protected C client;
	protected ClientConverter<M, R, D> mapper;
	private final Function<D, M> toModelFn;

	protected ClientAdapter(C client, ClientConverter<M, R, D> mapper, Function<D, M> toModelFn) {
		this.client = client;
		this.mapper = mapper;
		this.toModelFn = toModelFn;
	}

	protected D toResponse(M entity) {
		return mapper.toResponse(entity);
	}

	protected M toModel(D data) {
		return data != null ? toModelFn.apply(data) : null;
	}

	public M save(M model) {
		R request = this.mapper.toRequest(model);
		return toModel(client.create(request));
	}

	public M update(I id, M model) {
		R request = this.mapper.toRequest(model);
		return toModel(client.update(id, request));
	}

	public void deleteById(I id) {
		client.delete(id);
	}
}
